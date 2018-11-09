package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMExit extends VMCode
{
  private int idxExitCode;//register index

  public VMExit(int idxExitCode) {
    this.idxExitCode = idxExitCode;
  }

  public int getIdxExitCode() {
    return idxExitCode;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitExit(this);
  }
}
