#ifndef WACCC_H
#define WACCC_H

#define IDENT_LEN 8
typedef unsigned char u1;
typedef unsigned short u2;
typedef unsigned int u4;
typedef int s4;
/*
1. header
2. function table
3. class table
4. string table(u4 offset only)
5. wacc_instruction
6. wacc_fields
7. wacc_vtable
8. strings
*/

typedef struct _wacc_header
{
	u1 ident[IDENT_LEN];	//"WACC_VM\x00"
	u4 size;				//size of wacc file
	u4 version;				//version of wacc 1 now
	u4 function_off;		//offset of function table (wacc_function)
	u4 function_size;		//number of functions
	u4 class_off;			//offset of class table (wacc_class)
	u4 class_size;			//number of classes
	u4 string_off;			//offset of string table
	u4 string_size;			//number of strings
}wacc_header;


typedef struct _wacc_function
{
	u2 argumentsNum;		//number of arguments of a function
	u2 variablesNum;		//number of variables of a function
	u4 instructions;		//offset to instruction (wacc_instruction)
}wacc_function;

typedef struct _wacc_instruction
{
	u4 instructionLength;	//length of instruction
	u2 registersSize;		//number of register used
	u1 instructions[1];		//wacc bytecode
}wacc_instruction;

typedef struct _wacc_class
{
	u2 parentIdx;			//index of parent class
	u2 size;
	u4 fieldOffset;			//offset to fields (wacc_fields)
	u4 vtableOffset;		//offset to virtual table of the class (wacc_vtable)
}wacc_class;

typedef struct _wacc_field_info
{
	u2 offset;				//map filed index to the offset compared to header
	u2 type;				//size of the field
}wacc_field_info;

typedef struct _wacc_fields
{
	u4 size;				//number of fields
	wacc_field_info f[1];	//field info
}wacc_fields;

typedef struct _wacc_vtable
{
	u4 size;				//size of virtual table(number of virtual functions)
	u4 functionIdx[1];		//function index in function table
}wacc_vtable;


#endif