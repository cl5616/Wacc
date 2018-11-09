package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMCallVirtual extends VMCall
{
  private int vtIdx;//index of virtual table//not reg index

  public VMCallVirtual(int retIdx, int classIdx, int vtIdx) {
    super(retIdx);
    this.vtIdx = vtIdx;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitCallVirtual(this);
  }

  public int getVtIdx() {
    return vtIdx;
  }
}
