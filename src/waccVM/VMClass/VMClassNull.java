package waccVM.VMClass;


import java.util.ArrayList;

public class VMClassNull extends VMClass
{
  public static final int VT_SIZE = 4;
  private final static String nullField = "no fields in null class";

  private static final VMClassNull nullObject = new VMClassNull();
  @Override
  public VMField getField(int idx) {
    throw new ArrayIndexOutOfBoundsException(nullField);
  }

  @Override
  public int vtSize() {
    return 0;
  }

  @Override
  VMVirFunc getVFunction(int idx) {
    throw new ArrayIndexOutOfBoundsException("no virtual function in null class");
  }

  @Override
  ArrayList<Integer> getFieldIdxToDispl() {
    return new ArrayList<>();
  }

  @Override
  public int getSize() {
    return VT_SIZE;
  }

  @Override
  int getMaxFieldSize() {
    return VT_SIZE;
  }

  @Override
  public int getFieldDispl(int fieldReferenceIdx)
  {
    throw new ArrayIndexOutOfBoundsException(nullField);
  }

  @Override
  public int getParentIdx() {
    return 0;
  }

  @Override
  public int fieldNum() {
    return 0;
  }

  @Override
  public VMVirFunc getvt(int j) {
    throw new ArrayIndexOutOfBoundsException("no vt in class null");
  }

  private VMClassNull(){}

  public static VMClassNull getNullObject() {
    return nullObject;
  }
}
