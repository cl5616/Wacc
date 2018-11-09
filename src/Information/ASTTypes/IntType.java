package Information.ASTTypes;

import Information.ASTTypes.ASTTypeVisitor.TypeVisitor;
import waccVM.VMInstruction.ElemSize;

public class IntType extends Type {

  private String valueRaw;

  public IntType(String varName, String value) {
    super(varName);
    this.valueRaw = value;
  }

  public IntType() {
    valueRaw = "";
  }

  @Override
  public <T> T accept(TypeVisitor<T> visitor) {
    return visitor.visitIntType(this);
  }

  public String getValue() {
    return valueRaw;
  }

  @Override
  public boolean isSameType(Type type) {
    return type instanceof IntType;
  }

  @Override
  public ElemSize getByteSize() {
    return ElemSize.DWORD;
  }

  @Override
  public Type getElemType() {
    return this;
  }

  public IntType(String value) {
    this.valueRaw = value;
  }
}
