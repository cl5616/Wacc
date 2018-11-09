package waccVM.VMByteCode;
import static waccVM.VMByteCode.Utils.*;
import java.util.ArrayList;

public class VMWaccInstr
{
  private int instructionLenth;
  private short registerSize;
  private ArrayList<Byte> instructions;

  public VMWaccInstr(short registerSize,
                     ArrayList<Byte> instructions) {
    this.registerSize = registerSize;
    this.instructions = instructions;
    instructionLenth = instructions.size();
  }
  public int sizeof()
  {
    return 6 + instructionLenth;
  }

  public int getBin(byte[] ret, int off) {
    off += concatIntLE(ret, off, instructionLenth);
    off += concatShortLE(ret, off, registerSize);
    for (byte b : instructions)
    {
      ret[off++] = b;
    }
    return off;
  }
}
