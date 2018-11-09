#pragma once
#include <string>
#include <exception>
enum WaccExceptionType
{
	ARRAY_OUT_OF_BOUND, 
	NULL_POINTER,
	NO_SUCH_REFERENCE,
	INVALID_CLASS_INDEX,
	INVALID_FIELD_INDEX,
	INVALID_VT_INDEX,
	CLASS_ACCESS_OUT_OF_BOUND,
	PC_OUT_OF_BOUND, 
	INVALID_FUNC_IDX,
	HEAP_CONTENT_UNMATCH,
	FUNC_ARGS_UNMATCH,
	REG_OUT_OF_BOUND,
	VAR_OUT_OF_BOUND,
	ARG_OUT_OF_BOUND,
	STR_OUT_OF_BOUND,
	UNRECOGNIZED_INSTR,
	DIVIDE_BY_ZERO
};

std::string waccExcepToString(const WaccExceptionType e);

class WaccPC;
class WaccException : public std::exception
{
private:
	WaccExceptionType type;
	WaccPC* pc;
public:
	WaccException(WaccExceptionType type, const WaccPC& pc);
	~WaccException();
	std::string getErrorMsg();
};

