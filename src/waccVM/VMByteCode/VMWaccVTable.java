package waccVM.VMByteCode;
import static waccVM.VMByteCode.Utils.*;
import java.util.ArrayList;

public class VMWaccVTable
{
  private int size;
  private ArrayList<Integer> funcIdxes;
  public VMWaccVTable(){size = 0; funcIdxes = new ArrayList<>();}
  public void addVTFuncIdx(int funcIdx)
  {
    size++;
    funcIdxes.add(funcIdx);
  }
  public int sizeof()
  {
    return 4 + funcIdxes.size() * 4;
  }

  public int getBin(byte[] ret, int off) {
    off += concatIntLE(ret, off, size);
    for (int func : funcIdxes)
    {
      off +=concatIntLE(ret, off, func);
    }
    return off;
  }
}
