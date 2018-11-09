package waccVM.VMByteCode;
import static waccVM.VMByteCode.Utils.*;

import java.util.ArrayList;

public class VMWaccFields
{
  private int size;
  private ArrayList<VMFieldInfo> fieldInfo;

  public VMWaccFields() {
    size = 0;
    fieldInfo = new ArrayList<>();
  }

  public int getBin(byte[] ret, int off) {
    off += concatIntLE(ret, off, size);
    for (VMFieldInfo f : fieldInfo)
    {
      off += concatShortLE(ret, off, f.offset);
      off += concatShortLE(ret, off, f.type_size);
    }
    return off;
  }

  private class VMFieldInfo
  {
    public short offset;
    public byte type_size;
    VMFieldInfo(short offset, byte type_size)
    {
      this.offset = offset;
      this.type_size = type_size;
    }
  }

  public void addFieldInfo(short offset, byte size)
  {
    this.size++;
    this.fieldInfo.add(new VMFieldInfo(offset, size));
  }
  public int sizeof()
  {
    return 4 + size * 4;
  }
}