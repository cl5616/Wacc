package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

abstract public class VMJmp extends VMCode
{
  private String dst;
  public VMJmp(String dst) {
    this.dst = dst;
  }

  public String getDst() {
    return dst;
  }
}
