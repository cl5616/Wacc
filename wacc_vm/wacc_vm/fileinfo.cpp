#include "fileinfo.h"
#include <memory.h>
#include <vector>
#include <string.h>
#include <iostream>
#include "instruction.h"
#include "heap.h"
using namespace std;

WaccProgram WaccProgram::ins;

inline u2 parseU2(const u1* ins)
{
	return *((u2*)ins);
}
inline u4 parseU4(const u1* ins)
{
	return *((u4*)ins);
}

inline type parseType(u2 type)
{
	if (type == 1)
		return BYTE_WACC;
	else if (type == 4)
		return DWORD_WACC;
	else
		return INVALID_WACC;
}

inline type parseType(const u1* ins)
{
	return parseType(*ins);
}

inline heap_type parseHeapType(const u1* ins)
{
	u2 tmp = parseU2(ins);
	if (tmp == ARRAY_WACC || tmp == CLASS_WACC || tmp == PAIR_WACC)
	{
		return (heap_type)tmp;
	}
	else
	{
		return INVALIDH_WACC;
	}
}

WaccFunction::WaccFunction(u2 argNum, u2 varNum, u2 regNum,
	u1* instru, u4 instrLen)
{
	this->argumentsNum = argNum;
	this->variablesNum = varNum;
	this->registersNum = regNum;
	this->instructions = new u1[instrLen];
	memcpy(instructions, instru, instrLen);
	this->instructionLen = instrLen;
}
WaccFunction::~WaccFunction()
{
	delete[] this->instructions;
}
u2	WaccFunction::getArgNum() const
{
	return argumentsNum;
}
u2	WaccFunction::getVarNum() const
{
	return variablesNum;
}
u2	WaccFunction::getRegNum() const
{
	return registersNum;
}
const u1* WaccFunction::getInstruction(u4 pc) const
{
	return instructions + pc;
}


WaccPC::WaccPC(u4 funcIdx, u2 ret)
{
	this->retReg = ret;
	this->funIdx = funcIdx;
	this->pc = 0;
}
void WaccPC::setPC(u4 newPC)
{
	this->pc = newPC;
}

WaccProgram::WaccProgram()
{
	//heap.push_back(nullptr);
}


WaccProgram::~WaccProgram()
{
	freeInfos();
}
void WaccProgram::freeInfos()
{
	while(this->pcStack.size())
	{
		delete pcStack.top();
		pcStack.pop();
	}
	for(WaccFunction* f : waccFuncs)
	{
		delete f;
	}
	waccFuncs.clear();
	for (WaccClass* c : waccClasses)
	{
		delete c;
	}
	waccClasses.clear();
	for (string* str : waccString)
	{
		delete str;
	}
	waccString.clear();
	for (void* h : heap)
	{
		delete[] (u1*)h;
	}
	heap.clear();
	heap.push_back(nullptr);
}


#define INC_PC(ins_size) pc->setPC(funcPc+(ins_size))

#define CHECK_REG(regIdx)\
	if ((regIdx) >= regs.getBlockNum())\
				throw WaccException(REG_OUT_OF_BOUND, *pc);

#define LOAD_CHECK_ARRAY_ARG \
			u2 regIdx = parseU2(instruction+1);\
			u2 arrayRegIdx = parseU2(instruction + 3);\
			u2 elemIdxRegIdx = parseU2(instruction + 5);\
			type elemSize = parseType(instruction + 7);\
			CHECK_REG(regIdx)\
			CHECK_REG(arrayRegIdx)\
			CHECK_REG(elemIdxRegIdx)\
			u1* pHeap = (u1*)getAddr(arrayRegIdx);\
			if(parseHeapType(pHeap) != ARRAY_WACC)\
				throw WaccException(HEAP_CONTENT_UNMATCH, *pc);\
			pHeap += 2;\
			type actual = parseType(pHeap);\
			if(actual != elemSize)\
				throw WaccException(HEAP_CONTENT_UNMATCH, *pc);\
			pHeap += 2;\
			u4 arrayLen = parseU4(pHeap);\
			pHeap += 4;\
			u4 idx = regs[elemIdxRegIdx];\
			if (idx >= arrayLen)\
				throw WaccException(ARRAY_OUT_OF_BOUND, *pc);

#define BIN_OP(op) \
			u2 dstReg = parseU2(instruction + 1);\
			u2 arg1Reg = parseU2(instruction + 3);\
			u2 arg2Reg = parseU2(instruction + 5);\
			CHECK_REG(dstReg);\
			CHECK_REG(arg1Reg);\
			CHECK_REG(arg2Reg);\
			regs[dstReg] = (regs[arg1Reg] op regs[arg2Reg]);\
			INC_PC(BINOP_SIZE);


#define BIN_OP_CHECKZERO(op) \
			u2 dstReg = parseU2(instruction + 1);\
			u2 arg1Reg = parseU2(instruction + 3);\
			u2 arg2Reg = parseU2(instruction + 5);\
			CHECK_REG(dstReg);\
			CHECK_REG(arg1Reg);\
			CHECK_REG(arg2Reg);\
			if (regs[arg2Reg] == 0) throw WaccException(DIVIDE_BY_ZERO, *pc);\
			regs[dstReg] = (regs[arg1Reg] op regs[arg2Reg]);\
			INC_PC(BINOP_SIZE);



#define PREV_CLASS_ACCESS\
			u2 classIdx = parseU2(instruction + 1);\
			u2 fieldIdx = parseU2(instruction + 3);\
			u2 regIdx = parseU2(instruction + 5);\
			u2 objIdx = parseU2(instruction + 7);\
			isClassIdxValid(classIdx);\
			isFieldIdxValid(classIdx, fieldIdx);\
			/*parse infos*/\
			wacc_class_h* objAddr = (wacc_class_h*)getAddr(objIdx);\
			if (objAddr->type != CLASS_WACC) \
				throw WaccException(HEAP_CONTENT_UNMATCH,*pc);\
			wacc_field_info fieldInfo = waccClasses[classIdx]\
				->getFieldsInfo(fieldIdx);\
			u1* fieldAddr = objAddr->content + fieldInfo.offset;\
			if (fieldInfo.offset + fieldInfo.type > waccClasses[objAddr->classIdx]->getSize())\
				throw WaccException(CLASS_ACCESS_OUT_OF_BOUND, *pc);

#define PREV_LOAD_STORE\
			u2 regIdx = parseU2(instruction + 1);\
			u2 stackIdx = parseU2(instruction + 3);\
			CHECK_REG(regIdx);

#define PREV_FST \
			u2 pairRegIdx = parseU2(instruction + 1);\
			u2 src_dst = parseU2(instruction + 3);\
			u4* fst = (u4*)((u1*)getAddr(pairRegIdx) + 4);

#define PREV_SND \
			u2 pairRegIdx = parseU2(instruction + 1);\
			u2 src_dst = parseU2(instruction + 3);\
			u4* snd = (u4*)((u1*)getAddr(pairRegIdx) + 8);


inline void nextStackFrame(u2 regNum, u2 varNum, vector<u4> args)
{
	registers::getIns().newStackFrame(regNum);
	variables::getIns().newStackFrame(varNum);
	arguments::getIns().newStackFrame(args);
}

inline void prevStackFrame()
{
	registers::getIns().prevStackFrame();
	variables::getIns().prevStackFrame();
	arguments::getIns().prevStackFrame();
}

#define UN_OP(op)\
			u2 dstReg = parseU2(instruction + 1);\
			u2 argReg = parseU2(instruction + 3);\
			regs[dstReg] = op regs[argReg];\
			INC_PC(UNOP_SIZE);



u4 WaccProgram::addHeap(u1* heapP)
{
	u4 idx = getFreeHeapIdx();
	if (idx == this->heap.size())
	{
		this->heap.push_back(heapP);
	}
	else
	{
		this->heap[idx] = heapP;
	}
	return idx;
}

u4 WaccProgram::getFreeHeapIdx()
{
	size_t count = heap.size();
	for (size_t i = 1; i < count; ++i)
	{
		if (heap[i] == nullptr)
			return i;
	}
	return count;
}

s4 WaccProgram::execute()
{
	u4 mainIdx = waccFuncs.size() - 1;
	pcStack.push(new WaccPC(mainIdx, 0));
	WaccFunction* waccmain =  this->waccFuncs.at(mainIdx);
	nextStackFrame(waccmain->getRegNum(), waccmain->getVarNum(), vector<u4>());
while(true)
{
	WaccPC* pc = pcStack.top();
	u4 funcIdx = pc->getFunIdx();
	u4 funcPc = pc->getPC();//fetch PC info
	const u1* instruction = waccFuncs[funcIdx]
			->getInstruction(funcPc);//get instruction

	if (funcPc >= waccFuncs[funcIdx]->getInstructionLength())
	{
		throw WaccException(PC_OUT_OF_BOUND, *pc);
	}

	switch (*instruction)
	{
		case EXIT:
		{
			return true;
		}
		break;
		case ARRAY_ACCESS_LOAD:
		{
			LOAD_CHECK_ARRAY_ARG;
			switch (elemSize)
			{
				case DWORD_WACC:
				regs[regIdx] = ((u4*)pHeap)[idx];
				break;
				case BYTE_WACC:
				regs[regIdx] = ((u1*)pHeap)[idx];
				break;
				case INVALID_WACC:
					throw WaccException(HEAP_CONTENT_UNMATCH, *pc);
			}
			INC_PC(ARRAY_ACCESS_SIZE);
		}
		break;
		case ARRAY_ACCESS_STORE:
		{
			LOAD_CHECK_ARRAY_ARG;
			switch (elemSize)
			{
				case DWORD_WACC:
				((u4*)pHeap)[idx] = regs[regIdx];
				break;
				case BYTE_WACC:
				((u1*)pHeap)[idx] = regs[regIdx];
				break;
				case INVALID_WACC:
					throw WaccException(HEAP_CONTENT_UNMATCH, *pc);
			}
			INC_PC(ARRAY_ACCESS_SIZE);
		}
		break;
		case ASSIGN:
		{
			u2 dstReg = parseU2(instruction + 1);
			u4 imm = parseU4(instruction + 3);
			CHECK_REG(dstReg);
			regs[dstReg] = imm;
			INC_PC(ASSIGN_SIZE);
		}
		break;
		case BINOP_ADD:
		{
			BIN_OP(+);
		}
		break;
		case BINOP_SUB:
		{
			BIN_OP(-);
		}
		break;
		case BINOP_MUL:
		{
			BIN_OP(*);
		}
		break;
		case BINOP_DIV:
		{
			BIN_OP_CHECKZERO(/);
		}
		break;
		case BINOP_MOD:
		{
			BIN_OP_CHECKZERO(%);
		}
		break;
		case BINOP_EQ:
		{
			BIN_OP(==);
		}
		break;
		case BINOP_GT:
		{
			BIN_OP(>);
		}
		break;
		case BINOP_LT:
		{
			BIN_OP(<);
		}
		break;
		case BINOP_GTE:
		{
			BIN_OP(>=);
		}
		break;
		case BINOP_LTE:
		{
			BIN_OP(<=);
		}
		break;
		case BINOP_NEQ:
		{
			BIN_OP(!=);
		}
		break;
		case BINOP_AND:
		{
			BIN_OP(&&);
		}
		break;
		case BINOP_OR:
		{
			BIN_OP(||);
		}
		break;
		case CALL_DIRECT:
		{
			u1 argNum = *(instruction + 1);
			u4 funcIdx = parseU4(instruction + 2);
			isFuncIdxValid(funcIdx);
			u2 retRegIdx = parseU2(instruction + 6);
			CHECK_REG(retRegIdx);
			vector<u4> args;
			for (u4 i = 0; i < argNum; ++i)
			{
				u2 regIdx = parseU2(instruction + 8 + i * 2);
				CHECK_REG(regIdx);
				u4 argV = regs[regIdx];
				args.push_back(argV);
			}//fetch information

			INC_PC(CALL_DIRECT_SIZE(argNum));
			pcStack.push(new WaccPC(funcIdx, retRegIdx));
			u2 defArgNum = waccFuncs[funcIdx]->getArgNum();
			u2 regNum = waccFuncs[funcIdx]->getRegNum();
			u2 varNum = waccFuncs[funcIdx]->getVarNum();
			if (defArgNum != argNum)
				throw WaccException(FUNC_ARGS_UNMATCH, *pc);

			nextStackFrame(regNum, varNum, args);
		}
		break;
		case CALL_VIRTUAL:
		{
			vector<u4> args;
			u1 argNum = *(instruction + 1);
			u2 vtIdx = parseU2(instruction + 2);
			u2 retRegIdx = parseU2(instruction + 4);
			CHECK_REG(retRegIdx);
			u2 objIdx = parseU2(instruction + 6);//object
			CHECK_REG(objIdx);
			//fetch info and check

			u4 objPointer = regs[objIdx];
			wacc_class_h* class_h = (wacc_class_h*)getAddr(objIdx);
			if (class_h->type != CLASS_WACC)
				throw WaccException(HEAP_CONTENT_UNMATCH, *pc);
			u2 classIdx = class_h->classIdx;
			//get content in the heap

			isVtIdxValid(classIdx, vtIdx);//check vt index validity
			u4 funcIdx = waccClasses[classIdx]->getFuncIdx(vtIdx);
			//get function index for vt, funcIdx must be valid

			args.push_back(objPointer);
			for (u4 i = 1; i < argNum; ++i)
			{
				u2 regIdx = parseU2(instruction + 6 + i * 2);
				CHECK_REG(regIdx);
				u4 argV = regs[regIdx];
				args.push_back(argV);
			}//push args

			INC_PC(CALL_VIRTUAL_SIZE(argNum));//increment PC for return purpose
			
			pcStack.push(new WaccPC(funcIdx, retRegIdx));
			u2 defArgNum = waccFuncs[funcIdx]->getArgNum();
			u2 regNum = waccFuncs[funcIdx]->getRegNum();
			u2 varNum = waccFuncs[funcIdx]->getVarNum();
			if (defArgNum != argNum)
				throw WaccException(FUNC_ARGS_UNMATCH, *pc);
			nextStackFrame(regNum, varNum, args);
			//new stack frame
		}
		break;
		case CLASS_ACCESS_LOAD:
		{
			PREV_CLASS_ACCESS;
			switch(parseType(fieldInfo.type))
			{
				case BYTE_WACC:
					regs[regIdx] = *(u1*)fieldAddr;
				break;
				case DWORD_WACC:
					regs[regIdx] = *(u4*)fieldAddr;
				break;
				default:
				return false;//impossible, should check this when loading classes
			}
			INC_PC(CLASS_ACCESS_SIZE);
		}
		break;
		case CLASS_ACCESS_STORE:
		{
			PREV_CLASS_ACCESS;
			switch(parseType(fieldInfo.type))
			{
				case BYTE_WACC:
					*(u1*)fieldAddr = (u1)regs[regIdx];
				break;
				case DWORD_WACC:
					*(u4*)fieldAddr = regs[regIdx];
				break;
				default:
				return false;//same, as above
			}
			INC_PC(CLASS_ACCESS_SIZE);
		}
		break;
		case COND_JMP:
		{
			u2 regIdx = parseU2(instruction + 1);
			u4 displ = parseU4(instruction + 3);
			CHECK_REG(regIdx);
			if (regs[regIdx])
				pcStack.top()->setPC(displ);
			else
				INC_PC(COND_JMP_SIZE);
		}
		break;
		case UNCOND_JMP:
		{
			u4 displ = parseU4(instruction + 1);
			pcStack.top()->setPC(displ); 
		}
		break;
		case FREE:
		{
			u2 regIdx = parseU2(instruction + 1);
			if (regs[regIdx] != 0)
			{
				delete[] (u1*)getAddr(regIdx);
				heap[regs[regIdx]] = nullptr;
			}INC_PC(UN_COMMAND_SIZE);
		}
		break;
		case LOAD_VAR:
		{
			PREV_LOAD_STORE;
			if ((stackIdx) >= vars.getBlockNum())
				throw WaccException(VAR_OUT_OF_BOUND, *pc);
			regs[regIdx] = vars[stackIdx];
			INC_PC(LOAD_STORE_SIZE);
		}
		break;
		case STORE_VAR:
		{
			PREV_LOAD_STORE;
			if ((stackIdx) >= vars.getBlockNum())
				throw WaccException(VAR_OUT_OF_BOUND, *pc);
			vars[stackIdx] = regs[regIdx];
			INC_PC(LOAD_STORE_SIZE);
		}
		break;
		case LOAD_STRING:
		{
			u2 regIdx = parseU2(instruction + 1);
			u4 stringIdx = parseU4(instruction + 3);
			CHECK_REG(regIdx);
			if (stringIdx >= waccString.size())
				throw WaccException(STR_OUT_OF_BOUND, *pc);
			size_t strLen = waccString[stringIdx]->size();
			
			wacc_array* h = (wacc_array*)new u1[sizeof(wacc_array) + strLen + 1];
			u4 freeHeap = addHeap((u1*)h);//8 header, 1 '\0'
			h->type = ARRAY_WACC;
			h->elemType = BYTE_WACC;
			h->length = strLen;
			strcpy_s((char*)h->content, strLen + 1,
				waccString[stringIdx]->c_str());
			regs[regIdx] = freeHeap;
			INC_PC(LOAD_STRING_SIZE);
		}
		break;
		case NEW_ARRAY:
		{
			type t = parseType(*(instruction + 1));
			u2 regIdx = parseU2(instruction + 2);
			u4 length = parseU4(instruction + 4);
			wacc_array* h = (wacc_array*)new u1[sizeof(wacc_array) + length * t];
			u4 freeHeap = addHeap((u1*)h);//8 header, 1 '\0'
			h->type = ARRAY_WACC;
			h->elemType = t;
			h->length = length;
			memset((char*)(h->content), 0, length * t);
			regs[regIdx] = freeHeap;
			INC_PC(NEW_ARRAY_SIZE);
		}
		break;
		case NEW_CLASS:
		{
			u2 classIdx = parseU2(instruction + 1);
			u2 dstReg = parseU2(instruction + 3);
			CHECK_REG(dstReg);
			wacc_class_h* h = (wacc_class_h*)new u1[(waccClasses
				[classIdx]->getSize() + sizeof(wacc_class_h))];
			u4 freeHeap = addHeap((u1*)h);
			h->type = CLASS_WACC;
			h->classIdx = classIdx;
			memset(h->content, 0, waccClasses[classIdx]->getSize());
			regs[dstReg] = freeHeap;
			INC_PC(NEW_CLASS_SIZE);
		}
		break;
		case NEW_PAIR:
		{
			u2 dstIdx = parseU2(instruction + 1);
			u2 fstIdx = parseU2(instruction + 3);
			u2 sndIdx = parseU2(instruction + 5);
			CHECK_REG(dstIdx);
			CHECK_REG(fstIdx);
			CHECK_REG(sndIdx);
			wacc_pair* h = (wacc_pair*)new u1[sizeof(wacc_pair)];
			u4 freeHeap = addHeap((u1*)h);
			h->type = PAIR_WACC;
			h->fst = regs[fstIdx];
			h->snd = regs[sndIdx];
			regs[dstIdx] = freeHeap;
			INC_PC(NEW_PAIR_SIZE);
		}
		break;
		case PAIR_ACCESS_FST_LOAD:
		{
			PREV_FST;
			regs[src_dst] = *fst;
			INC_PC(PAIR_ACCESS_SIZE);
		}
		break;
		case PAIR_ACCESS_FST_STORE:
		{
			PREV_FST;
			*fst = regs[src_dst];
			INC_PC(PAIR_ACCESS_SIZE);
		}
		break;
		case PAIR_ACCESS_SND_LOAD:
		{
			PREV_SND;
			regs[src_dst] = *snd;
			INC_PC(PAIR_ACCESS_SIZE);
		}
		break;
		case PAIR_ACCESS_SND_STORE:
		{
			PREV_SND;
			*snd = regs[src_dst];
			INC_PC(PAIR_ACCESS_SIZE);
		}
		break;
		case PRINT_INT:
		{
			u2 regIdx = parseU2(instruction + 1);
			cout << (int)regs[regIdx];
			INC_PC(UN_COMMAND_SIZE);
		}
		break;
		case PRINT_BOOL:
		{
			u2 regIdx = parseU2(instruction + 1);
			if (regs[regIdx])
				cout << "true";
			else
				cout << "false";
			INC_PC(UN_COMMAND_SIZE);
		}
		break;
		case PRINT_CHAR:
		{
			u2 regIdx = parseU2(instruction + 1);
			cout << (char)regs[regIdx];
			INC_PC(UN_COMMAND_SIZE);
		}
		break;
		case PRINT_STRING:
		{
			u2 regIdx = parseU2(instruction + 1);
			cout << (char*)((u1*)getAddr(regIdx) + 8);
			INC_PC(UN_COMMAND_SIZE);
		}
		break;
		case PRINT_ARRAY_PAIR:
		{
			u2 regIdx = parseU2(instruction + 1);
			if (regs[regIdx] == 0)//null pointer
				cout << "null";
			else
				cout << "0x" << (void*)(getAddr(regIdx));
			INC_PC(UN_COMMAND_SIZE);
		}
		break;
		case PRINTLN_INT:
		{
			u2 regIdx = parseU2(instruction + 1);
			cout << (int)regs[regIdx] << endl;
			INC_PC(UN_COMMAND_SIZE);
		}
		break;
		case PRINTLN_BOOL:
		{
			u2 regIdx = parseU2(instruction + 1);
			if (regs[regIdx])
				cout << "true" << endl;
			else
				cout << "false" << endl;
			INC_PC(UN_COMMAND_SIZE);
		}
		break;
		case PRINTLN_CHAR:
		{
			u2 regIdx = parseU2(instruction + 1);
			cout << (char)regs[regIdx] << endl;
			INC_PC(UN_COMMAND_SIZE);
		}
		break;
		case PRINTLN_STRING:
		{
			u2 regIdx = parseU2(instruction + 1);
			cout << (char*)((u1*)getAddr(regIdx) + 8) << endl;
			INC_PC(UN_COMMAND_SIZE);
		}
		break;
		case PRINTLN_ARRAY_PAIR:
		{
			u2 regIdx = parseU2(instruction + 1);
			if (regs[regIdx] == 0)//null pointer
				cout << "null" << endl;
			else
				cout << "0x" << (void*)(getAddr(regIdx)) << endl;
			INC_PC(UN_COMMAND_SIZE);
		}
		break;
		case READ_INT:
		{
			u2 dstIdx = parseU2(instruction + 1);
			int in;
			cin >> in;
			regs[dstIdx] = (u4)in;
			INC_PC(UN_COMMAND_SIZE);
		}
		break;
		case READ_CHAR:
		{
			u2 dstIdx = parseU2(instruction + 1);
			char in;
			cin >> in;
			regs[dstIdx] = (u4)in;
			INC_PC(UN_COMMAND_SIZE);
		}
		break;
		case RETN:
		{
			u2 regIdx = parseU2(instruction + 1);
			CHECK_REG(regIdx);
			u4 retValue = regs[regIdx];
			WaccPC* pc = pcStack.top();
			u2 retReg = pc->getRetReg();
			delete pc;
			pcStack.pop();
			if (pcStack.size() == 0)
				return true;//main function return
			prevStackFrame();
			regs[retReg] = retValue;
		}
		break;
		case UNOP_NOT:
		{
			UN_OP(!);
		}
		break;
		case UNOP_NEG:
		{
			UN_OP((u4)-(s4));
		}
		break;
		case UNOP_LEN:
		{
			u2 dstReg = parseU2(instruction + 1);
			u2 argReg = parseU2(instruction + 3);
			wacc_array* h = (wacc_array*)getAddr(argReg);
			if (h->type != ARRAY_WACC)
				throw WaccException(HEAP_CONTENT_UNMATCH, *pc);
			regs[dstReg] = h->length;
			INC_PC(UNOP_SIZE);
		}
		break;
		case UNOP_ORD:
		case UNOP_CHR:
		{
			UN_OP((u4)(u1));
		}
		break;
		case UNOP_PLUS:
		{
			UN_OP(+);
		}
		break;
		case LOAD_ARG:
		{
			PREV_LOAD_STORE;
			if ((stackIdx) >= args.getBlockNum())
				throw WaccException(ARG_OUT_OF_BOUND, *pc);
			regs[regIdx] = args[stackIdx];
			INC_PC(LOAD_STORE_SIZE);
		}
		break;
		case STORE_ARG:
		{
			PREV_LOAD_STORE;
			if ((stackIdx) >= args.getBlockNum())
				throw WaccException(ARG_OUT_OF_BOUND, *pc);
			args[stackIdx] = regs[regIdx];
			INC_PC(LOAD_STORE_SIZE);
		}
		break;
		default:
			throw WaccException(UNRECOGNIZED_INSTR, *pc);
	}
}
	return true;
}

inline WaccFunction* waccFunctionFactory
		(wacc_function wacc_func,
	wacc_instruction* instructions)
{
	return new WaccFunction(wacc_func.argumentsNum
			, wacc_func.variablesNum, instructions->registersSize
			, instructions->instructions, instructions->instructionLength);
}

WaccClass::WaccClass(u2 parentIdx, u2 size)
{
	this->size = size;
	this->parentIdx = parentIdx;
}

void WaccClass::addField(wacc_field_info f)
{
	fields.push_back(f);
}
void WaccClass::addvt(u4 vt)
{
	vtable.push_back(vt);
}

inline WaccClass* waccClassFactory(
			wacc_class* waccClass,
			wacc_fields* waccFields,
			wacc_vtable* waccVT)
{
	WaccClass* ret = new WaccClass(waccClass->parentIdx, waccClass->size);
	u4 fieldNum = waccFields->size;
	for (u4 i = 0; i < fieldNum; ++i)
	{
		ret->addField(waccFields->f[i]);
	}
	u4 vtNum = waccVT->size;
	for (u4 i = 0; i < vtNum; ++i)
	{
		ret->addvt(waccVT->functionIdx[i]);
	}
	return ret;
}

void WaccProgram::loadFile(FILE* file)
{
	ins.freeInfos();
	fseek(file, 0, SEEK_END);
	long int fileSize = ftell(file);
	fseek(file, 0, SEEK_SET);
	char* fileBuffer = new char[fileSize];
	fread(fileBuffer,
		1, fileSize, file);
	wacc_header* fileHeader = (wacc_header*)fileBuffer;
	u4 funcOff = fileHeader->function_off;
	u4 funcSize = fileHeader->function_size;

	for (u4 i = 0; i < funcSize; ++i)
	{
		wacc_function* p_wacc_function = (wacc_function*)(fileBuffer +
				funcOff + sizeof(wacc_function) * i);
		ins.waccFuncs.push_back(waccFunctionFactory
			(*p_wacc_function,
			(wacc_instruction*)(fileBuffer + p_wacc_function->instructions)));
	}
	u4 classOff = fileHeader->class_off;
	u4 classSize = fileHeader->class_size;
	for (u4 i = 0; i < classSize; ++i)
	{
		wacc_class* p_wacc_class = (wacc_class*)(fileBuffer +
			classOff + sizeof(wacc_class) * i);
		wacc_fields* p_wacc_fields = (wacc_fields*)(fileBuffer +
			p_wacc_class->fieldOffset);
		wacc_vtable* p_wacc_vt = (wacc_vtable*)(fileBuffer +
			p_wacc_class->vtableOffset);
		ins.waccClasses.push_back(waccClassFactory(
			p_wacc_class, p_wacc_fields, p_wacc_vt));
	}
	u4 stringOff = fileHeader->string_off;
	u4 stringSize = fileHeader->string_size;
	for (u4 i = 0; i < stringSize; ++i)
	{
		u4 strOff = *(u4*)(fileBuffer + stringOff + sizeof(u4) * i);
		char* test = (char*)(fileBuffer + strOff);
		ins.waccString.push_back(new string((char*)(fileBuffer + strOff)));
	}
	delete[] fileBuffer;
}