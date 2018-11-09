package CodeGenArgs;

public class BinOpRegister {
  public int regDst;
  public int reg1;
  public int reg2;

  public BinOpRegister(int regDst, int reg1, int reg2) {
    this.regDst = regDst;
    this.reg1 = reg1;
    this.reg2 = reg2;
  }

  public int getRegDst() {
    return regDst;
  }

  public int getReg1() {
    return reg1;
  }

  public int getReg2() {
    return reg2;
  }
}
