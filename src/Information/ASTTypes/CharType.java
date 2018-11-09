package Information.ASTTypes;

import Information.ASTTypes.ASTTypeVisitor.TypeVisitor;
import waccVM.VMInstruction.ElemSize;

public class CharType extends Type{

  private char chr;

  public CharType(String varName, char c) {
    super(varName);
    this.chr = c;
  }

  public char getChr() {
    return chr;
  }

  @Override
  public boolean isSameType(Type type) {
    return type instanceof CharType;
  }

  @Override
  public ElemSize getByteSize() {
    return ElemSize.BYTE;
  }

  @Override
  public Type getElemType() {
    return this;
  }

  public CharType() {

  }

  @Override
  public <T> T accept(TypeVisitor<T> visitor) {
    return visitor.visitCharType(this);
  }
}
