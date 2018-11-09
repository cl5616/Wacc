package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMRetn extends VMCode
{
  private int idxRetReg;

  public VMRetn(int idxRetReg) {
    this.idxRetReg = idxRetReg;
  }

  public int getIdxRetReg() {
    return idxRetReg;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitRetn(this);
  }
}
