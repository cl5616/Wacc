package Information.ASTTypes.ASTTypeVisitor;

import Information.ASTTypes.*;

public interface TypeVisitor<T>
{
  T visitArrayType(ArrayType type);
  T visitBoolType(BoolType type);
  T visitCharType(CharType type);
  T visitFuncType(FuncType type);
  T visitIntType(IntType type);
  T visitPairType(PairType type);
  T visitClassType();//todo
}


