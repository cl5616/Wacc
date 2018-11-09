package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMPairAccess extends VMCode {
  private int pairIdx;

  public VMLoadStore.LoadStore getLoad_store() {
    return load_store;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitPairAccess(this);
  }

  public enum FstOrSnd
  {
    FST(0),SND(4);

    private int heapDispl;

    FstOrSnd(int heapDispl) {
      this.heapDispl = heapDispl;
    }

    public int getHeapDispl() {
      return heapDispl;
    }
  }
  private FstOrSnd fstOrSnd;
  private int dst_srcIdx;

  public VMPairAccess(int pairIdx, FstOrSnd fstOrSnd, int dst_srcIdx, VMLoadStore.LoadStore load_store) {
    this.pairIdx = pairIdx;
    this.fstOrSnd = fstOrSnd;
    this.dst_srcIdx = dst_srcIdx;
    this.load_store = load_store;
  }

  private VMLoadStore.LoadStore load_store;

  public int getPairIdx() {
    return pairIdx;
  }

  public FstOrSnd getFstOrSnd() {
    return fstOrSnd;
  }

  public int getDst_srcIdx() {
    return dst_srcIdx;
  }
}
