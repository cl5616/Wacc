package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMNewPair extends VMCode
{
  private int idxFst;
  private int idxSnd;
  private int dstIdx;

  public VMNewPair(int idxFst, int idxSnd, int dstIdx) {
    this.idxFst = idxFst;
    this.idxSnd = idxSnd;
    this.dstIdx = dstIdx;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitNewPair(this);
  }

  public int getIdxFst() {
    return idxFst;
  }

  public int getIdxSnd() {
    return idxSnd;
  }

  public int getDstIdx() {
    return dstIdx;
  }
}
