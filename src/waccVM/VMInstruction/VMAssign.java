package waccVM.VMInstruction;

import Information.ASTTypes.Type;
import waccVM.VmVisitor.VMVisitor;

public class VMAssign extends VMCode
{
  private int lhsIdx;
  private int rhs;
  private final Type rhsType;

  public int getRhs() {
    return rhs;
  }

  public VMAssign(int lhsIdx, int rhs, Type rhsType)
  {
    this.lhsIdx = lhsIdx;
    this.rhs = rhs;
    this.rhsType = rhsType;
  }

  public int getLhsIdx() {
    return lhsIdx;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitAssign(this);
  }

  public Type getRhsType() {
    return rhsType;
  }
}
