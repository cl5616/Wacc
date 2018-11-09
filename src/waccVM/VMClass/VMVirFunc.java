package waccVM.VMClass;

public class VMVirFunc
{
  private final int classIdx;
  private final int funcIdx;
  private final String funcName;
  private final int funcIdxMain;
  public VMVirFunc(int classIdx, int funcIdx, String funcName, int funcIdxMain) {
    this.classIdx = classIdx;
    this.funcIdx = funcIdx;
    this.funcName = funcName;
    this.funcIdxMain = funcIdxMain;
  }

  public int getClassIdx() {
    return classIdx;
  }

  public int getFuncIdx() {
    return funcIdx;
  }

  public String getFuncName() {
    return funcName;
  }

  public int getFuncIdxMain() {
    return funcIdxMain;
  }
}
