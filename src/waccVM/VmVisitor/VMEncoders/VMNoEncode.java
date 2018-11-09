package waccVM.VmVisitor.VMEncoders;

import waccVM.VMInstruction.ElemSize;

public class VMNoEncode implements VMEncoder
{
  @Override
  public byte byteCodeEnc(byte bytecode) {
    return bytecode;
  }

  @Override
  public int regIdxEnc(int reg) {
    return reg;
  }

  @Override
  public int varIdxEnc(int var) {
    return var;
  }

  @Override
  public int argIdxEnc(int arg) {
    return arg;
  }

  @Override
  public byte elemSizeEnc(ElemSize elemSize) {
    return (byte) elemSize.toInt();
  }

  @Override
  public long jmpDisplEnc(long displ) {
    return displ;
  }

  @Override
  public long immEnc(int imm) {
    return imm;
  }

  @Override
  public long stringIdxEnc(int stringIdx) {
    return stringIdx;
  }

  @Override
  public long functionIdxEnc(int functionIdx) {
    return functionIdx;
  }

  @Override
  public int vtableIdxEnc(int vtableIdx) {
    return vtableIdx;
  }

  @Override
  public byte numberOfArgEncode(byte numOfArg) {
    return numOfArg;
  }

  @Override
  public int classIdxEnc(int classIdx) {
    return classIdx;
  }

  @Override
  public int fieldIdx(int fieldIdx) {
    return fieldIdx;
  }

  @Override
  public long lengthEnc(int length) {
    return length;
  }
}
