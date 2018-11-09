package CodeGenArgs;

public class UnOpRegister {
  public int regDst;
  public int reg;

  public UnOpRegister(int regDst, int reg) {
    this.regDst = regDst;
    this.reg = reg;
  }

  public int getRegDst() {
    return regDst;
  }

  public int getReg() {
    return reg;
  }
}

