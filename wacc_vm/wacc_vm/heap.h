#ifndef HEAP_H
#define HEAP_H
#include "waccc.h"
//another way: use C++ method
//however, here I prefer to use C
typedef struct _wacc_pair
{
	u4 type;
	u4 fst;
	u4 snd;
}wacc_pair;

typedef struct _wacc_array
{
	u2 type;
	u2 elemType;
	u4 length;
	u1 content[0];
}wacc_array;
#endif

typedef struct _wacc_class_h
{
	u2 type;
	u2 classIdx;
	u1 content[0];
}wacc_class_h;
