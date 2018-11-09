package waccVM.VmVisitor.VMEncoders;
import waccVM.VMInstruction.ElemSize;

public interface VMEncoder
{//supposed to be non-side-effect
  /*
  take one bytecode,
  give another bytecode
  */
  byte byteCodeEnc(byte bytecode);
  /*
  take index of register/var/arg
  give the encoded register/var/arg
  must in range 0-65535
  */
  int regIdxEnc(int reg);
  int varIdxEnc(int var);
  int argIdxEnc(int arg);
  /*
  take elem, give byte
  */
  byte elemSizeEnc(ElemSize elemSize);
  /*
  take index of register/var/arg
  give the encoded register/var/arg
  must in range 0-(2^32-1)
  */
  long jmpDisplEnc(long displ);
  long immEnc(int imm);//signed for this one!!!
  long stringIdxEnc(int stringIdx);
  long functionIdxEnc(int functionIdx);
  int vtableIdxEnc(int vtableIdx);
  byte numberOfArgEncode(byte numOfArg);
  int classIdxEnc(int classIdx);
  int fieldIdx(int fieldIdx);
  long lengthEnc(int length);
}