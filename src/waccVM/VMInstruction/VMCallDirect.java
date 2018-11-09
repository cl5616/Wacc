package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMCallDirect extends VMCall{

  private int funcIdx;
  public VMCallDirect(int funcIdx, int retIdx) {
    super(retIdx);
    this.funcIdx = funcIdx;
  }

  public int getFuncIdx() {
    return funcIdx;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitCallDirect(this);
  }
}
