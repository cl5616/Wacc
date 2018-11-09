package Information.ASTTypes;

import Information.ASTTypes.ASTTypeVisitor.TypeVisitor;
import Information.Information;
import waccVM.VMInstruction.ElemSize;

public abstract class Type extends Information {

  public Type() {

  }

  abstract public <T> T accept(TypeVisitor<T> visitor);

  private String varName;

  public Type(String varName) {
    this.varName = varName;
  }

  public abstract boolean isSameType(Type type);

  public String getVarName() {
    return varName;
  }

  public void setVarName(String varName) {
    this.varName = varName;
  }

  abstract public ElemSize getByteSize();

  abstract public Type getElemType();
}
