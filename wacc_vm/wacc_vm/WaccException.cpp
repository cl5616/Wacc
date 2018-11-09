#include "WaccException.h"
#include "fileinfo.h"
std::string waccExcepToString(const WaccExceptionType e)
{
	switch (e)
	{
	case ARRAY_OUT_OF_BOUND:
		return "array index out of bound";
	case NULL_POINTER:
		return "null pointer";
	case NO_SUCH_REFERENCE:
		return "no such reference";
	case INVALID_CLASS_INDEX:
		return "invalid class index";
	case INVALID_FIELD_INDEX:
		return "invalid field index";
	case INVALID_VT_INDEX:
		return "invalid virtual table index";
	case CLASS_ACCESS_OUT_OF_BOUND:
		return "field access is out of bound";
	default:
		return "";
	case PC_OUT_OF_BOUND:
		return "program counter is out of bound";
	case INVALID_FUNC_IDX:
		return "invalid function index";
	case HEAP_CONTENT_UNMATCH:
		return "heap content is not matched";
	case FUNC_ARGS_UNMATCH:
		return "function arguments are unmatch";
	case REG_OUT_OF_BOUND:
		return "register index is out of bound";
	case VAR_OUT_OF_BOUND:
		return "variable index is out of bound";
	case ARG_OUT_OF_BOUND:
		return "argument index is out of bound";
	case STR_OUT_OF_BOUND:
		return "string table index is out of bound";
	case UNRECOGNIZED_INSTR:
		return "unrecognized operation code";
	case DIVIDE_BY_ZERO:
		return "divide by zero";
	}
}

WaccException::WaccException(WaccExceptionType type, const WaccPC & pc)
{
	this->pc = new WaccPC(pc.getFunIdx(), pc.getRetReg());
	this->pc->setPC(pc.getPC());
	this->type = type;
}

WaccException::~WaccException()
{
	delete pc;
}

std::string WaccException::getErrorMsg()
{
	using namespace std;
	string ret = waccExcepToString(this->type);
	ret.append("\nat function: ");
	ret.append(to_string(pc->getFunIdx()));
	ret.append("\nat position: ");
	ret.append(to_string(pc->getPC()));
	return ret;
}
