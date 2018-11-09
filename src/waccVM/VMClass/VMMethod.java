package waccVM.VMClass;

import Information.Information;

public class VMMethod extends Information
{
  private final int functionIdx;
  private final String methodName;

  public VMMethod(int function, String methodName) {
    this.functionIdx = function;
    this.methodName = methodName;
  }

  public int getFunctionIdx() {
    return functionIdx;
  }

  public String getMethodName() {
    return methodName;
  }
}
