#ifndef REGISTERS_H
#define REGISTERS_H

#include "context.h"

#define CTX_SINGLETON(classname) 				\
class classname : public context				\
{												\
private:										\
	static classname ins;						\
	classname();								\
	classname(const classname& ctx);			\
	classname& operator=(const classname& ctx);	\
public:											\
	static classname& getIns();					\
}

CTX_SINGLETON(registers);
CTX_SINGLETON(variables);
CTX_SINGLETON(arguments);

#endif
