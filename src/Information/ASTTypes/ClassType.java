package Information.ASTTypes;

import Information.ASTTypes.ASTTypeVisitor.TypeVisitor;
import java.util.HashMap;
import java.util.Map;
import waccVM.VMInstruction.ElemSize;

public class ClassType extends Type {

  public enum Access {
    PUBLIC, PROTECTED, PRIVATE
  }

  private final String className;
  private final ClassType parent;
  private boolean alreadyHaveDestr = false;
  private boolean alreadyHaveConstr = false;
  private final Map<String, Access> encapsMap = new HashMap<>();
  // encapsulations of all members in class body.
  private Map<String, Type> typeTable = new HashMap<>();
  // type-map of all fields.



  @Override
  public <T> T accept(TypeVisitor<T> visitor) {
    return null;
  }

  public ClassType(String className) {
    this.className = className;
    this.parent = null;
  }

  public ClassType(String className, ClassType parent) {
    this.className = className;
    this.parent = parent;
  }

  public Access lookForEncaps(String name) {
    if (name.equals(className) || name.equals("~" + className)) { //constructor or destructor
      boolean isConstructor = name.equals(className);
      Access result = encapsMap.get(name);
      ClassType currentParent = parent;

      while (result == null) {
        if (currentParent == null) {
          return null;
        }
        name = isConstructor ? currentParent.className : "~" + currentParent.className;
        result = currentParent.encapsMap.get(name);
        currentParent = currentParent.getParent();
      }
      return result;
    }
    else {
      Access result = encapsMap.get(name);
      ClassType currentParent = parent;

      while (result == null) {
        if (currentParent == null) {
          return null;
        }
        result = currentParent.encapsMap.get(name);
        currentParent = currentParent.getParent();
      }
      return result;
    }
  }

  public Type lookForObject(String name) {
    if (name.equals(className) || name.equals("~" + className)) { //constructor or destructor
      boolean isConstructor = name.equals(className);
      Type result = typeTable.get(name);
      ClassType currentParent = parent;

      while (result == null) {
        if (currentParent == null) {
          return null;
        }
        //constr and destr changes their names with their bounded class. e.g class apple -> apple();
        name = isConstructor ? currentParent.className : "~" + currentParent.className;
        result = currentParent.typeTable.get(name);
        currentParent = currentParent.getParent();
      }
      return result;
    }
    else {
      Type result = typeTable.get(name);
      ClassType currentParent = parent;

      while (result == null) {
        if (currentParent == null) {
          return null;
        }
        result = currentParent.typeTable.get(name);
        currentParent = currentParent.getParent();
      }
      return result;
    }
  }

  @Override
  public boolean isSameType(Type type) {

    if (type instanceof ClassType) {

      ClassType classType = (ClassType) type;

      while (classType != null) {
        if (classType.className.equals(this.className)) {
          return true;
        }
        classType = classType.parent;
      }
    }
    return false;
  }

  @Override
  public ElemSize getByteSize() {
    return ElemSize.DWORD;
  }

  @Override
  public Type getElemType() {
    return null;
  }

  public void addNewEncaps(String ident, String encapsString) {

    Access encapsType = null;

    switch (encapsString) {
      case "public":
        encapsType = Access.PUBLIC;
        break;
      case "private":
        encapsType = Access.PRIVATE;
        break;
      case "protected":
        encapsType = Access.PROTECTED;
    }

    encapsMap.put(ident, encapsType);
  }

  public void addNewType(String ident, Type type) {
    typeTable.put(ident, type);
  }

  public boolean contains(String ident) {
    return typeTable.containsKey(ident);
  }

  public String getClassName() {
    return className;
  }

  public ClassType getParent() {
    return parent;
  }

  public void setDestr() {
    this.alreadyHaveDestr = true;
  }

  public void setConstr() {
    this.alreadyHaveConstr = true;
  }

  public boolean checkAlreadyHaveDestr() {
    return alreadyHaveDestr;
  }

  public boolean checkAlreadyHaveConstr() {
    return alreadyHaveConstr;
  }

  public void inheritParent() {
    // merge the members of parent into sub-class.
    for (Map.Entry<String, Type> entry : parent.typeTable.entrySet()) {
      String ident = entry.getKey();

      if (!this.typeTable.containsKey(ident)) {
        // if the inherited does not get overrided here.

        if (parent.encapsMap.get(ident) != Access.PRIVATE) {
          // if the sub-class has proper access to the inherited.

          this.typeTable.put(ident, entry.getValue());
          this.encapsMap.put(ident, parent.encapsMap.get(ident));
        }
      }
    }
  }
}
