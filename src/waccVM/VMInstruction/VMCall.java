package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

import java.util.LinkedList;


abstract public class VMCall extends VMCode
{

  private LinkedList<Integer> args;
  private int retIdx;

  public VMCall(int retIdx) {
    this.retIdx = retIdx;
    this.args = new LinkedList<>();
  }

  public int getRetIdx() {
    return retIdx;
  }

  public LinkedList<Integer> getArgs()
  {
    return args;
  }
  public void addArg(int regIdx)
  {
    args.add(regIdx);
  }

}
