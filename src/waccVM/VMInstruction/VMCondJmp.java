package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMCondJmp extends VMJmp
{
  private int ifJmpIdx;//true->jmp false->no jmp

  public VMCondJmp(int ifJmpIdx, String dst) {
    super(dst);
    this.ifJmpIdx = ifJmpIdx;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitCondJmp(this);
  }

  public int getIfJmpIdx() {
    return ifJmpIdx;
  }
}