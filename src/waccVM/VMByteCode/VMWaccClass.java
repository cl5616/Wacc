package waccVM.VMByteCode;
import static waccVM.VMByteCode.Utils.*;
/*
typedef struct _wacc_class
{
	u2 parentIdx;			//index of parent class
	u2 _paddings;
	u4 fieldOffset;			//offset to fields (wacc_fields)
	u4 vtableOffset;		//offset to virtual table of the class (wacc_vtable)
}wacc_class;

*/
public class VMWaccClass
{
  private short parentIdx;
  private short size;
  private int field_off;
  private int vtable_off;

  public VMWaccClass(short parentIdx, short size) {
    this.parentIdx = parentIdx;
    this.size = size;
  }

  public void setField_off(int field_off) {
    this.field_off = field_off;
  }

  public void setVtable_off(int vtable_off) {
    this.vtable_off = vtable_off;
  }

  public static int sizeof()
  {
    return 12;
  }

  public int getBin(byte[] ret, int off) {
    off += concatShortLE(ret, off, parentIdx);
    off += concatShortLE(ret, off, size);
    off += concatIntLE(ret, off, field_off);
    off += concatIntLE(ret, off, vtable_off);
    return off;
  }
}
