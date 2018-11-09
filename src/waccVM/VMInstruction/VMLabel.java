package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMLabel extends VMCode
{
  private String label;

  public VMLabel(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitLabel(this);
  }
}
