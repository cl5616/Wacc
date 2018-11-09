package waccVM.VmVisitor;

import Information.ASTTypes.Type;

import java.util.ArrayList;

public class VMFuncArguments
{
  private final ArrayList<Type> idxToArgType;
  private final ArrayList<Integer> argIdxToDisplacement;
  private final int argsSize;
  private final int argAlignSize;
  public VMFuncArguments(ArrayList<Type> idxToArgType) {
    this.idxToArgType = idxToArgType;
    int argDispl = 0;
    argIdxToDisplacement = new ArrayList<>();
    for (int i = 0; i < idxToArgType.size(); i++)
    {
      argIdxToDisplacement.add(argDispl);
      argDispl+=4;
    }
    argAlignSize = argsSize = argDispl;
  }

  public int getDisplacement(int idx)
  {
    return argIdxToDisplacement.get(idx);
  }

  public boolean isGivenArgByte(int idx)
  {
    return VMFuncVariables.isByte(idxToArgType.get(idx));
  }

  public int getArgsAlignSize() {
    return argAlignSize;
  }
  public int getArgsSize()
  {
    return argsSize;
  }
  public int getArgNum(){return idxToArgType.size();}
  public void addArg(Type t) {
    idxToArgType.add(t);
  }
}