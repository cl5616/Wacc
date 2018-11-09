package waccVM.VMInstruction;

public enum ElemSize
{
  BYTE(1,"b"),DWORD(4,"");

  private int num;
  private String ldrstrSuffix;

  public int toInt()
  {
    return num;
  }
  ElemSize(int num, String ldrstrSuffix)
  {
    this.num = num;
    this.ldrstrSuffix = ldrstrSuffix;
  }

  public String getLdrstrSuffix() {
    return ldrstrSuffix;
  }
}
