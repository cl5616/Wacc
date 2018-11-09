package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMLoadString extends VMCode
{
  private int stringIdx;
  private int dstRegIdx;

  public VMLoadString(int stringIdx, int dstRegIdx) {
    this.stringIdx = stringIdx;
    this.dstRegIdx = dstRegIdx;
  }

  public int getStringIdx() {
    return stringIdx;
  }

  public int getDstRegIdx() {
    return dstRegIdx;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitLoadString(this);
  }
}
