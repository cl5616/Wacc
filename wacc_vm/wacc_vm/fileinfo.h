#ifndef FILEINFO_H
#define FILEINFO_H
#include "waccc.h"
#include "WaccException.h"
#include <vector>
#include <stack>
#include <map>

#include "contexts/regvararg.h"
class WaccFunction
{
private:
	u2 argumentsNum;
	u2 variablesNum;
	u2 registersNum;
	u1* instructions;
	u4 instructionLen;
public:
	WaccFunction(u2 argNum, u2 varNum, u2 regNum,
		u1* instru, u4 instrLen);
	~WaccFunction();
	u2 getArgNum() const;
	u2 getVarNum() const;
	u2 getRegNum() const;
	const u1* getInstruction(u4 pc) const;
	inline u4 getInstructionLength()
	{
		return instructionLen;
	}
};

class WaccPC //program counter
{
private:
	u4 funIdx;
	u4 pc;
	u2 retReg;//the register in the previous stack frame to put return value
public:
	WaccPC(u4 funcIdx, u2 retReg);
	void setPC(u4 newPC);
	inline u4 getPC() const
	{
		return pc;
	}
	inline u4 getFunIdx() const
	{
		return funIdx;
	}
	inline u2 getRetReg() const 
	{
		return retReg;
	}
};

class WaccClass
{
private:
	u2 parentIdx;//probably no use at all
	u2 size;
	std::vector<wacc_field_info> fields;
	std::vector<u4> vtable;
public:
	WaccClass(u2 parentIdx, u2 size);
	void addField(wacc_field_info f);
	void addvt(u4 vt);
	inline u2 getVtNum()
	{
		return (u2)vtable.size();
	}
	inline u4 getFuncIdx(u4 vtidx)
	{
		return vtable[vtidx];
	}
	inline wacc_field_info getFieldsInfo(u4 fieldIdx)
	{
		return fields[fieldIdx];
	}
	inline u2 getSize()
	{
		return this->size;
	}
	inline u2 getFieldNum()
	{
		return (u2)fields.size();
	}
};

class WaccProgram
{
private:
	std::stack<WaccPC*> pcStack;
	std::vector<WaccFunction*> waccFuncs;
	std::vector<WaccClass*> waccClasses;
	std::vector<std::string*> waccString;
	std::vector<u1*> heap;

	inline void isClassIdxValid(u2 classIdx)
	{
		if (classIdx >= waccClasses.size())
			throw WaccException(INVALID_CLASS_INDEX, *pcStack.top());
	}

	inline void isFieldIdxValid(u2 classIdx, u2 fieldIdx)
	{//pre: class index is valid
		if (fieldIdx >= waccClasses[classIdx]->getFieldNum())
			throw WaccException(INVALID_FIELD_INDEX, *pcStack.top());
	}

	inline void isFuncIdxValid(u4 funcIdx)
	{
		if (funcIdx >= waccFuncs.size())
			throw WaccException(INVALID_FUNC_IDX, *pcStack.top());
	}

	inline void isVtIdxValid(u2 classIdx, u2 vtIdx)
	{//pre: class index valid
		if (vtIdx >= waccClasses[classIdx]->getVtNum())
			throw WaccException(INVALID_VT_INDEX, *pcStack.top());
	}

	registers& regs = registers::getIns();
	variables& vars = variables::getIns();
	arguments& args = arguments::getIns();

	u4 addHeap(u1*);

	inline u1* getAddr(u2 objRegIdx)
	{
		u4 v = regs[objRegIdx];
		if (v == 0)
			throw WaccException(NULL_POINTER, *pcStack.top());
		if (v >= heap.size())
			throw WaccException(NO_SUCH_REFERENCE, *pcStack.top());
		u1* ret = (u1*)heap[v];
		if (ret == nullptr)
			throw WaccException(NO_SUCH_REFERENCE, *pcStack.top());
		return ret;
	}
	u4 getFreeHeapIdx();
	WaccProgram();
	WaccProgram(WaccProgram&);
	void freeInfos();
	static WaccProgram ins;
public:
	~WaccProgram();
	s4 execute();
	static void loadFile(FILE* file);
	inline static WaccProgram& getIns()
	{
		return ins;
	}
};
#endif