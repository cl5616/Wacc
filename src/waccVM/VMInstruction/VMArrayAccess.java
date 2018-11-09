package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMArrayAccess extends VMCode {
  private int srcdstIdx;
  private int arrayIdx;
  private int index_of_elem;
  private VMLoadStore.LoadStore load_store;

  public ElemSize getElemSize() {
    return elemSize;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitArrayAccess(this);
  }

  private ElemSize elemSize;

  public VMArrayAccess(int srcdstIdx, int arrayIdx,
                       int index_of_elem,
                       VMLoadStore.LoadStore load_store,
                       ElemSize elemSize)
  {
    this.srcdstIdx = srcdstIdx;
    this.arrayIdx = arrayIdx;
    this.load_store = load_store;
    this.index_of_elem = index_of_elem;
    this.elemSize = elemSize;
  }

  public int getArrayIdx() {
    return arrayIdx;
  }

  public int getSrcDstIdx() {
    return srcdstIdx;
  }

  public int getIndex_of_elem() {
    return index_of_elem;
  }

  public VMLoadStore.LoadStore getLoad_store() {
    return load_store;
  }
}
