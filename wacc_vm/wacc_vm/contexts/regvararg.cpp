#include "regvararg.h"

#define CTX_SINGLETON_CPP(classname) \
classname classname::ins;\
classname& classname::getIns()\
{\
	return ins;\
}\
classname::classname(){}\
classname::classname(const classname& ctx){}			


CTX_SINGLETON_CPP(variables)
CTX_SINGLETON_CPP(registers)
CTX_SINGLETON_CPP(arguments)