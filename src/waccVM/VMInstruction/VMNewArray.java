package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMNewArray extends VMCode
{
  private int length;
  private int dstIdx;
  private ElemSize elemSize;

  public VMNewArray(int length, int dstIdx, ElemSize elemSize) {
    this.length = length;
    this.dstIdx = dstIdx;
    this.elemSize = elemSize;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor)
  {
    return visitor.visitNewArray(this);
  }

  public int getLength() {
    return length;
  }
  public int getDstIdx() {
    return dstIdx;
  }

  public ElemSize getElemSize() {
    return elemSize;
  }
}
