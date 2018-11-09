package waccVM.VMByteCode;
import static waccVM.VMByteCode.Utils.*;
/*
typedef struct _wacc_function
{
	u2 argumentsNum;		//number of arguments of a function
	u2 variablesNum;		//number of variables of a function
	u4 instructions;		//offset to instruction (wacc_instruction)
}wacc_function;
*/
public class VMWaccFunction
{
  private short argumentNum;
  private short variableNum;
  private int instruction_off;
  public VMWaccFunction(short argumentNum, short variableNum)
  {
    this.argumentNum = argumentNum;
    this.variableNum = variableNum;
  }
  public static int sizeof()
  {
    return 8;
  }
  public void setInstruction_off(int instruction_off) {
    this.instruction_off = instruction_off;
  }
  public int getBin(byte[] ret, int off)
  {
    off += concatShortLE(ret, off, argumentNum);
    off += concatShortLE(ret, off, variableNum);
    off += concatIntLE(ret, off, instruction_off);
    return off;
  }
}
