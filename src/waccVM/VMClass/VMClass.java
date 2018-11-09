package waccVM.VMClass;

import java.util.ArrayList;
import java.util.Collection;

abstract public class VMClass
{

  abstract public VMField getField(int idx);

  abstract public int vtSize();

  abstract VMVirFunc getVFunction(int idx);

  abstract ArrayList<Integer> getFieldIdxToDispl();

  public abstract int getSize();

  abstract int getMaxFieldSize();

  public abstract int getFieldDispl(int fieldReferenceIdx);

  public abstract int getParentIdx();

  public abstract int fieldNum();

  public abstract VMVirFunc getvt(int j);
}
