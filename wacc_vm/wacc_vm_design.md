# Design of the .waccc format

```c
#define IDENT_LEN 8
typedef unsigned char u1;
typedef unsigned short u2;
typedef unsigned int u4;

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
	u1 instructions[0];		//wacc bytecode
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
	wacc_field_info f[0];	//field info
}wacc_fields;

typedef struct _wacc_vtable
{
	u4 size;				//size of virtual table(number of virtual functions)
	u4 functionIdx[0];		//function index in function table
}wacc_vtable;


```



# Design of the wacc byte code format

## ArrayAccess

opcode(load/store) + src_dstIdx(u2) + arrayRegIdx(u2) + index_of_elem(u2) + elemsize(u1)

*opcode* is the operation code that identifies the instruction, there are 2 types of ArrayAccess, LOAD and STORE, which are loading the element in array into register and storing the stuff in register into array element respectively

*src_dstIdx* is the index of virtual regsiter that is the destination when loading and the source when storing.

*arrayRegIdx* is the index of virtual register that holds the address of array

*index_of_elem* is the index of virtual register that holds the index of the element in that array to be accessed.

*elemsize* is the size of the element, which is a macro, can be BYTE or DWORD

## Unconditional Jmp

opcode + displacement of the destination compared to header of the function(u4)

##  Conditional Jmp

opcode + idisplacement of the destination compared to header of the function(u4) + fJmpIdx(u2)

*ifJmpIdx* is virutal register index, if the content in this register is non-zero, we jump. If zero, we don't jump.

## Assign

opcode + dstIdx(u2) + imm number(u4)

dstIdx is the index of the register

imm number is the immidiate number

## Load and Store

opcode(loadvar/storevar/loadarg/storearg) + regIdx(u2) + stackIdx(u2)

regIdx is the destination of source register.

stackIdx is the variable index or the argument index.

opcode can have 4 options, as shown.

## Load String

opcode + regIdx(u2) + StringIndex(u4)

regIdx is the desitination register.

StringIndex is the index of string in the string table

## Unary Op

opcode + dstRegIdx(u2) + argRegIdx(u2)

argRegIdx is the register of unary argument.

## Binary Op

opcode  + dstRegIdx(u2) + argRegIdx(u2) × 2

There are 2 argument registers.

## Pair Access

opcode(fstload/fststore/sndload/sndstore) + pairRegIdx(u2) + regIdx(u2)

regIdx is the destination of source register.

pairRegIdx is the register containing pair pointer.

## Direct Call

opcode + num of args(u1) + funcIdx(u4) + retRegIdx(u2) + args(u2) * num

num of args is number of arguments.

funcIdx is the index of the funtion.

retRegIdx is the register to contain return value.

args are registers containning the arguments, number is designated by num of args.

## Virtual Call

opcode + num of args(u1) + vtIdx(u2) + retRegIdx(u2) + args(u2) * num

almost same as direct call, but vtIdx is the index of function in virtual table.

## Class Access

opcode(Load/Store) + classIdx(u2) + fieldIdx(u2) + srcdstReg(u2) + objectRegIdx(u2)

classIdx is the index of class in class table

srcdstReg is source of destination register.

objectRegIdx is register containing the pointer of object.

## New Class

opcode + classIdx(u2) + dstIdx(u2)

dstIdx is the destination register to have pointer to new instance.

classIdx is the class to be created.

## Retn

opcode + retRegIdx(u2)

retRegIdx is the register containing the return value.

## Free

opcode + regIdx(u2)

regIdx is the register containing the pointer to free.

## Print

opcode (manytypes and ln or not) + regIdx

print has many options:

PRINT_INT
PRINT_BOOL
PRINT_CHAR
PRINT_STRING
PRINT_ARRAY_PAIR
PRINTLN_INT
PRINTLN_BOOL
PRINTLN_CHAR
PRINTLN_STRING
PRINTLN_ARRAY_PAIR

regIdx is the register containing the argument

## Read

opcode(int/char) + regIdx

regIdx is register to obtain the calue being read

## New Pair

opcode + dstIdx + fst + snd

dstIdx is register to obtain the pointer to new pair

fst and snd are registers to contain 2 arguments that are fst and snd respectively.

## New Array

opcode + elemsize(u1) + regIdx(u2) + length(u4)

elemsize can only be 1 or 4, which correspond to byte and dword.

dstIdx is register to obtain the pointer to new array

length is the length of new array

## EXIT

opcode + regIdx

regIdx is register containing exit code

# In the Heap

## Array

u2 type array: enumeration

u2 elem type: only 1 or 4

u4 arrayLength: length of array

data

## Object

u2 type object: enumeration

u2 class index: index of class

data

## Pair

u2 type pair: enumeration

u2 _padding: for alignment

data