package Information.ASTTypes.ASTTypeVisitor;

import Information.ASTTypes.*;
import static waccVM.VMByteCode.VMByteCodeMacros.*;
import java.util.ArrayList;

public class EncodeType implements TypeVisitor<ArrayList<Byte>> {

  @Override
  public ArrayList<Byte> visitArrayType(ArrayType type) {
    ArrayList<Byte> ret = new ArrayList<>();
    ret.add(TYPE_ARRAY);
    ret.addAll(type.getElemType().accept(this));
    return ret;
  }

  @Override
  public ArrayList<Byte> visitBoolType(BoolType type) {
    ArrayList<Byte> ret = new ArrayList<>();
    ret.add(TYPE_BOOL);
    return ret;
  }

  @Override
  public ArrayList<Byte> visitCharType(CharType type) {
    ArrayList<Byte> ret = new ArrayList<>();
    ret.add(TYPE_CHAR);
    return ret;
  }

  @Override
  public ArrayList<Byte> visitFuncType(FuncType type) {
    assert false : "cannot encode func type!";
    return null;
  }

  @Override
  public ArrayList<Byte> visitIntType(IntType type) {
    ArrayList<Byte> ret = new ArrayList<>();
    ret.add(TYPE_INT);
    return ret;
  }

  @Override
  public ArrayList<Byte> visitPairType(PairType type) {
    ArrayList<Byte> ret = new ArrayList<>();
    ret.add(TYPE_PAIR);
    ret.addAll(type.getFstType().accept(this));
    ret.addAll(type.getSndType().accept(this));
    return ret;
  }

  @Override
  public ArrayList<Byte> visitClassType() {
    return null;
  }
/*
  @Override
  public ArrayList<Byte> visitStringType(StringType type) {
    ArrayList<Byte> ret = new ArrayList<>();
    ret.add(TYPE_ARRAY);
    ret.add(TYPE_CHAR);
    return ret;
  }*/
}
