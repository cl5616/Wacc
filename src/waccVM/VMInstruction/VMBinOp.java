package waccVM.VMInstruction;

import Information.OpInfo.BinOpInfo;
import waccVM.VmVisitor.VMVisitor;

public class VMBinOp extends VMCode
{
  private int lhs;
  private int rhs;

  private int dstIdx;

  //these two can be both var or imm, depending on the configuration
  private BinOpInfo.BinOp binOp;

  public VMBinOp(int lhs, int rhs, int dstIdx, BinOpInfo.BinOp binOp) {
    this.lhs = lhs;
    this.rhs = rhs;
    this.dstIdx = dstIdx;
    this.binOp = binOp;
  }

  public int getLhs() {
    return lhs;
  }

  public int getRhs() {
    return rhs;
  }

  public int getDstIdx() {
    return dstIdx;
  }

  public BinOpInfo.BinOp getBinOp() {
    return binOp;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitBinOp(this);
  }
}
