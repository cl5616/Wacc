package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMFree extends VMCode
{
  private int idxPairArray;//register index

  public VMFree(int idxPairArray) {
    this.idxPairArray = idxPairArray;
  }

  public int getIdxPairArray() {
    return idxPairArray;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitFree(this);
  }
}
