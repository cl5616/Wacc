package Information.ASTTypes;

import Information.ASTTypes.ASTTypeVisitor.TypeVisitor;
import waccVM.VMInstruction.ElemSize;

public class BoolType extends Type {

  private Boolean value;

  public BoolType(String varName, boolean boolExp) {
    super(varName);
    this.value = boolExp;
  }

  public BoolType(boolean bool) {
    this.value = bool;
  }

  public Boolean getValue() {
    return value;
  }

  @Override
  public boolean isSameType(Type type) {
    return type instanceof BoolType;
  }

  @Override
  public ElemSize getByteSize() {
    return ElemSize.BYTE;
  }

  @Override
  public Type getElemType() {
    return this;
  }

  public BoolType(){
    super();
  }

  @Override
  public <T> T accept(TypeVisitor<T> visitor) {
    return visitor.visitBoolType(this);
  }
}
