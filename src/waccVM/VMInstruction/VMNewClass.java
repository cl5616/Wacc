package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMNewClass extends VMCode
{
  private int objectReg;
  private int classIdx;

  public VMNewClass(int objectReg, int classIdx) {
    this.objectReg = objectReg;
    this.classIdx = classIdx;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitNewClass(this);
  }

  public int getObjectReg() {
    return objectReg;
  }

  public int getClassIdx() {
    return classIdx;
  }

}
