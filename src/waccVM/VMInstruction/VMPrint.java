package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMPrint extends VMCode
{
  private PrintType printType;
  private int idxReg;
  private boolean ifLn;

  public PrintType getPrintType() {
    return printType;
  }

  public int getIdxReg() {
    return idxReg;
  }

  public boolean isIfLn() {
    return ifLn;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitPrint(this);
  }

  public enum PrintType
  {
    INT,BOOL,CHAR,STRING,ARRAY_PAIR
  }
  public VMPrint(PrintType printType, int idxReg, boolean ifLn)
  {
    this.printType = printType;
    this.idxReg = idxReg;
    this.ifLn = ifLn;
  }
}
