package waccVM.VMByteCode;

public class VMByteCodeMacros
{
  public static final byte TYPE_INT = 1;
  public static final byte TYPE_BOOL = 2;
  public static final byte TYPE_CHAR = 3;
  public static final byte TYPE_ARRAY = 4; //followed by 1 type
  public static final byte TYPE_PAIR = 5; //followed by 2 type
  public static final byte TYPE_CLASS = 6; //followed by index of the class, u4

  public static final byte EXIT = 0;
  public static final byte ARRAY_ACCESS_LOAD = 1; //followed by 3 u2
  public static final byte ARRAY_ACCESS_STORE = 2;
  public static final byte ASSIGN = 3;//followed by u2 u4 u4
  public static final byte BINOP_ADD = 4;
  public static final byte BINOP_SUB = 5;
  public static final byte BINOP_MUL = 6;
  public static final byte BINOP_DIV = 7;
  public static final byte BINOP_MOD = 8;
  public static final byte BINOP_EQ = 9;
  public static final byte BINOP_GT = 10;
  public static final byte BINOP_LT = 11;
  public static final byte BINOP_GTE = 12;
  public static final byte BINOP_LTE = 13;
  public static final byte BINOP_NEQ = 14;
  public static final byte BINOP_AND = 15;
  public static final byte BINOP_OR = 16;
  public static final byte CALL_DIRECT = 17;//u1 num of arg//u4 funcIdx//u2*(num+1) arg and ret
  public static final byte CALL_VIRTUAL = 18;//u1 num of arg//u4 vtIdx//u2*(num+1) arg and ret
  public static final byte CLASS_ACCESS_LOAD = 19;
  public static final byte CLASS_ACCESS_STORE = 20;
  public static final byte COND_JMP = 21;
  public static final byte UNCOND_JMP = 22;
  public static final byte FREE = 23;
  public static final byte LOAD_VAR = 24;
  public static final byte STORE_VAR = 25;
  public static final byte NEW_ARRAY = 26;
  public static final byte NEW_CLASS = 27;
  public static final byte NEW_PAIR = 28;
  public static final byte PAIR_ACCESS_FST_LOAD = 29;
  public static final byte PAIR_ACCESS_FST_STORE = 30;
  public static final byte PAIR_ACCESS_SND_LOAD = 31;
  public static final byte PAIR_ACCESS_SND_STORE = 32;
  public static final byte PRINT_INT = 33;
  public static final byte PRINT_BOOL = 34;
  public static final byte PRINT_CHAR = 35;
  public static final byte PRINT_STRING = 36;
  public static final byte PRINT_ARRAY_PAIR = 37;
  public static final byte PRINTLN_INT = 38;
  public static final byte PRINTLN_BOOL = 39;
  public static final byte PRINTLN_CHAR = 40;
  public static final byte PRINTLN_STRING = 41;
  public static final byte PRINTLN_ARRAY_PAIR = 42;
  public static final byte READ_INT = 43;
  public static final byte READ_CHAR = 44;
  public static final byte RETN = 45;
  public static final byte UNOP_NOT = 46;
  public static final byte UNOP_NEG = 47;
  public static final byte UNOP_LEN = 48;
  public static final byte UNOP_ORD = 49;
  public static final byte UNOP_CHR = 50;
  public static final byte UNOP_PLUS = 51;
  public static final byte LOAD_ARG = 52;
  public static final byte STORE_ARG = 53;
  public static final byte LOAD_STRING = 54;
}
