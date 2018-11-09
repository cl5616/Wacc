package Information.ASTTypes;

import Information.ASTTypes.ASTTypeVisitor.TypeVisitor;
import waccVM.VMInstruction.ElemSize;

public class ArrayType extends Type{

  private final Type elemType;
  int length;

  public ArrayType(String varname, Type elemType, int length) {//{{{1},{1,2}}}
    super(varname);
    this.elemType = elemType;
    this.length = length;
  }

  public Type getElemType() {
    return elemType;
  }

  @Override
  public <T> T accept(TypeVisitor<T> visitor) {
    return visitor.visitArrayType(this);
  }

  @Override
  public boolean isSameType(Type type) {
    if (type instanceof ArrayType)
    {
      ArrayType at = (ArrayType)type;
      if (at.elemType == null || this.elemType == null) {
        return true;
      }
      return at.elemType.isSameType(this.elemType);
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
}
