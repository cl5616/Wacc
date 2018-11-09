package Information.ASTTypes;

import Information.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Information.ASTTypes.ASTTypeVisitor.TypeVisitor;
import waccVM.VMInstruction.ElemSize;

public class FuncType extends Type{

  private final Type returnType;
  private final List<Type> paraTypes;

  public FuncType(String varName, Type returnType, ParamList paramList) {
    super(varName);
    if (paramList != null) {
      this.paraTypes = paramList.getParams();
    } else {
      this.paraTypes = new ArrayList<>();
    }
    this.returnType = returnType;
  }

  public Type getReturnType(){
    return returnType;
  }

  public List<Type> getParaTypes() {
    return paraTypes;
  }

  public boolean isSameArgList(FuncType ft)
  {
    if (paraTypes.size() == ft.paraTypes.size())
    {
      Iterator<Type> iter1 = paraTypes.iterator();
      Iterator<Type> iter2 = ft.paraTypes.iterator();
      while (iter1.hasNext())
      {
        if (!iter1.next().isSameType(iter2.next()))
        {
          return false;
        }
      }
      return true;
    }
    else
    {
      return false;
    }
  }

  @Override
  public <T> T accept(TypeVisitor<T> visitor) {
    return visitor.visitFuncType(this);
  }

  @Override
  public boolean isSameType(Type type) {
    if (type instanceof FuncType)
    {
      FuncType ft = (FuncType) type;
      return this.isSameArgList(ft) && returnType.isSameType(ft.returnType);
    }
    else
    {
      return false;
    }
  }

  @Override
  public ElemSize getByteSize() {
    return null;
  }

  @Override
  public Type getElemType() {
    return this;
  }
}