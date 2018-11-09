package waccVM;

import waccVM.VmVisitor.VMFuncArguments;

import java.util.ArrayList;

public class VMFuncDeclares
{
  public VMFuncDeclares() {
    this.functions = new ArrayList<>();
  }

  public void insertFuncDeclares(VMFuncArguments args, String funcName) {
    this.functions.add(new VMFuncDeclare(args, funcName));
  }

  private class VMFuncDeclare
  {
    private VMFuncArguments args;
    private String funcName;

    public VMFuncDeclare(VMFuncArguments args, String funcName)
    {
      this.args = args;
      this.funcName = funcName;
    }

  }

  private ArrayList<VMFuncDeclare> functions;

  public boolean isArgByte(int idxFunc, int idxArg)
  {
    return functions.get(idxFunc).args.isGivenArgByte(idxArg);
  }

  public int getFuncArgSize(int idxFunc)
  {
    return functions.get(idxFunc).args.getArgsSize();
  }

  public String getFuncName(int idxFunc)
  {
    return functions.get(idxFunc).funcName;
  }
}
