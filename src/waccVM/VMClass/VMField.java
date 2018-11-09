package waccVM.VMClass;

import Information.ASTTypes.Type;
import Information.Information;

public class VMField extends Information
{
  private Type type;
  private String name;

  public VMField(Type type, String name) {
    this.type = type;
    this.name = name;
  }

  public Type getType() {
    return type;
  }

  public String getName() {
    return name;
  }
}
