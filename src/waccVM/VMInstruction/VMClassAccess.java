package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMClassAccess extends VMCode
{
  private int object;
  private int src_dstIdx;
  private int fieldReferenceIdx;//not register index
  private int classIdx;
  private VMLoadStore.LoadStore load_store;

  public VMClassAccess(int object, int src_dstIdx,
                       int fieldReferenceIdx, int classIdx,
                       VMLoadStore.LoadStore load_store) {
    this.object = object;
    this.src_dstIdx = src_dstIdx;
    this.fieldReferenceIdx = fieldReferenceIdx;
    this.classIdx = classIdx;
    this.load_store = load_store;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor)
  {
    return visitor.visitClassAccess(this);
  }

  public int getObject() {
    return object;
  }

  public int getSrc_dstIdx() {
    return src_dstIdx;
  }

  public int getFieldReferenceIdx() {
    return fieldReferenceIdx;
  }

  public VMLoadStore.LoadStore getLoad_store() {
    return load_store;
  }

  public int getClassIdx() {
    return classIdx;
  }
}
