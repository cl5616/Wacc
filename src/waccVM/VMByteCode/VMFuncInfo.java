package waccVM.VMByteCode;

import java.util.ArrayList;

public class VMFuncInfo
{
  private final int argNum;
  private final int varNum;
  private final int regNum;
  private final ArrayList<Byte> byteCodes;

  public static boolean isSmallerThan0xffff(int x)
  {
    return x >= 0 && x <= 0xffff;
  }

  public VMFuncInfo(int argNum, int varNum,
                    int regNum, ArrayList<Byte> byteCodes) {
    if (!(isSmallerThan0xffff(argNum) && isSmallerThan0xffff(varNum) && isSmallerThan0xffff(regNum)))
      throw new RuntimeException("arg/var/reg number is too big");
    this.argNum = argNum;
    this.varNum = varNum;
    this.regNum = regNum;
    this.byteCodes = new ArrayList<>();
    this.byteCodes.addAll(byteCodes);
  }

  public int getArgNum() {
    return argNum;
  }

  public ArrayList<Byte> getByteCodes(){
    ArrayList<Byte> ret = new ArrayList<>();
    ret.addAll(byteCodes);
    return ret;
  }
  public int getVarNum() {
    return varNum;
  }

  public int getRegNum() {
    return regNum;
  }
}
