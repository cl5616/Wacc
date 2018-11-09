package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

public class VMRead extends VMCode
{
  public VMRead(int idxDstReg, ReadType readType) {
    this.idxDstReg = idxDstReg;
    this.readType = readType;
  }

  public int getIdxDstReg() {
    return idxDstReg;
  }

  public ReadType getReadType() {
    return readType;
  }

  @Override
  public <T> T accept(VMVisitor<T> visitor) {
    return visitor.visitRead(this);
  }

  public enum ReadType
  {
    INT("int"),CHAR("char");
    private String funcPost;

    ReadType(String funcPost)
    {
      this.funcPost = funcPost;
    }
    @Override
    public String toString()
    {
      return funcPost;
    }
  }
  private int idxDstReg;
  private ReadType readType;
}
