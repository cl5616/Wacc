package waccVM.VMByteCode;

public class Utils
{
  public static int concatIntLE(byte[] ret, int idx, int x)
  {
    ret[idx++] = (byte)(x & 0xff);
    ret[idx++] = (byte)((x >> 8) & 0xff);
    ret[idx++] = (byte)((x >> 16) & 0xff);
    ret[idx] = (byte)((x >> 24) & 0xff);
    return 4;
  }

  public static int concatShortLE(byte[] ret, int idx, int x)
  {
    ret[idx++] = (byte)(x & 0xff);
    ret[idx] = (byte)((x >> 8) & 0xff);
    return 2;
  }

  public static int concatString(byte[] ret, int idx, String x)
  {
    int i;
    for (i = 0; i < x.length(); i++)
    {
      ret[idx + i] = (byte)x.charAt(i);
    }
    ret[idx+i] = 0;
    return i + 1;
  }
}
