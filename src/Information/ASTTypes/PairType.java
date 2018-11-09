package Information.ASTTypes;

import Information.ASTTypes.ASTTypeVisitor.TypeVisitor;
import waccVM.VMInstruction.ElemSize;

public class PairType extends Type{

  private Type fstType;
  private Type sndType;

  public PairType(String varName, Type fstType, Type sndType) {
    super(varName);
    this.fstType = fstType;
    this.sndType = sndType;
  }

  public Type getFstType() {
    return fstType;
  }

  public Type getSndType() {
    return sndType;
  }

  @Override
  public boolean isSameType(Type type)
  {
    if (type instanceof PairType)
    {
      if(isUncertain() || ((PairType) type).isUncertain()){
        return true;
      }
      PairType pt = (PairType)type;

      return pt.getFstType().isSameType(this.getFstType())
              && pt.getSndType().isSameType(this.getSndType());
    }
    else
    {
      return false;
    }
  }

  @Override
  public ElemSize getByteSize() {
    return ElemSize.DWORD;
  }

  @Override
  public Type getElemType() {
    return this;
  }

  private boolean isUncertain(){
    return fstType == null && sndType == null;
  }

  public PairType(){

  }

  @Override
  public <T> T accept(TypeVisitor<T> visitor) {
    return visitor.visitPairType(this);
  }
}
