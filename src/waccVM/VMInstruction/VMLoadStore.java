package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMLoadStore extends VMCode
{
  private int regIdx;
  private int stackIdx;

  public LoadStore getLoad_store() {
    return load_store;
  }

  public VarOrArg getVar_arg() {
    return var_arg;
  }

  public int getRegIdx() {
    return regIdx;
  }

  public int getStackIdx() {
    return stackIdx;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitLoadStore(this);
  }

  public enum VarOrArg
  {
    VAR,ARG
  }
  public enum LoadStore
  {
    LOAD("LDR"),STORE("STR");
    public LoadStore invert()
    {
      return this == LOAD ? STORE : LOAD;
    }
    private String instruction;
    @Override
    public String toString()
    {
      return instruction;
    }
    LoadStore(String instruction)
    {
      this.instruction = instruction;
    }
  }
  private LoadStore load_store;
  private VarOrArg var_arg;
  public VMLoadStore(int regIdx, int stackIdx, LoadStore load_store, VarOrArg var_arg)
  {
    this.regIdx = regIdx;
    this.stackIdx = stackIdx;
    this.load_store = load_store;
    this.var_arg = var_arg;
  }
}
