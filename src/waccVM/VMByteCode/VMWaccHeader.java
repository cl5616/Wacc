package waccVM.VMByteCode;
import static waccVM.VMByteCode.Utils.*;
/*
typedef struct _wacc_header
{
	u1 ident[IDENT_LEN];	//"WACC_VM\x00"
	u4 file_size;				//file_size of wacc file
	u4 version;				//version of wacc 1 now
	u4 function_off;		//offset of function table (wacc_function)
	u4 function_size;		//number of functions
	u4 class_off;			//offset of class table (wacc_class)
	u4 class_size;			//number of classes
}wacc_header;
*/
public class VMWaccHeader
{
  private final String ident = "WACC_VM";
  private int file_size;
  private int version = 1;
  private final int function_off  = sizeof();
  private int function_size;
  private int class_off;
  private int class_size;
  private int string_off;
  private int string_size;

  public VMWaccHeader(int function_size, int class_size, int string_size)
  {
    this.string_off = sizeof() + VMWaccFunction.sizeof() * function_size
                  + VMWaccClass.sizeof() * class_size;
    this.string_size = string_size;
    setFunction_size(function_size);
    setClass_size(class_size);
  }

  public void setFile_size(int file_size) {
    this.file_size = file_size;
  }

  private void setFunction_size(int function_size) {
    this.function_size = function_size;
    this.class_off = function_size *
            VMWaccFunction.sizeof() + VMWaccHeader.sizeof();
  }

  private void setClass_size(int class_size) {
    this.class_size = class_size;
  }

  public static int sizeof()
  {
    return 40;
  }

  public int getBin(byte[] ret, int start)
  {
    start += concatString(ret, start, ident);
    start += concatIntLE(ret, start, file_size);
    start += concatIntLE(ret, start, version);
    start += concatIntLE(ret, start, function_off);
    start += concatIntLE(ret, start, function_size);
    start += concatIntLE(ret, start, class_off);
    start += concatIntLE(ret, start, class_size);
    start += concatIntLE(ret, start, string_off);
    start += concatIntLE(ret, start, string_size);
    return start;
  }

}
