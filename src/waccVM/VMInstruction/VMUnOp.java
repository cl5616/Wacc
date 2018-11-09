package waccVM.VMInstruction;

import Information.OpInfo.UnOpInfo;
import waccVM.VmVisitor.VMVisitor;


public class VMUnOp extends VMCode
{
  private int arg;
  private int dstIdx;
  private UnOpInfo.UnOp unOp;

  public VMUnOp(int arg, int dstIdx, UnOpInfo.UnOp unOp) {
    this.arg = arg;
    this.dstIdx = dstIdx;
    this.unOp = unOp;
  }

  public int getArg() {
    return arg;
  }

  public int getDstIdx() {
    return dstIdx;
  }

  public UnOpInfo.UnOp getUnOp() {
    return unOp;
  }


  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitUnOp(this);
  }
}
