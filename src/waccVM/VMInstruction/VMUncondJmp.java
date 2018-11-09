package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMUncondJmp extends VMJmp
{
  public VMUncondJmp(String dst) {
    super(dst);
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitUncondJmp(this);
  }

}
