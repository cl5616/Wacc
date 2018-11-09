package waccVM.VmVisitor;

import waccVM.VMInstruction.*;

public class VMGetByteCodeSize implements VMVisitor<Integer>
{
  private static final int TYPE_SIZE = 1;
  private static final int REG_IDX_SIZE = 2;
  private static final int STACK_IDX_SIZE = 2;
  private static final int U4_SIZE = 4;
  private static final int FUNC_IDX_SIZE = 4;
  private static final int VT_IDX_SIZE = 2;
  private static final int CLASS_IDX_SIZE = 2;
  private static final int FIELD_IDX_SIZE = 2;
  private static final int OPCODE_SIZE = 1;
  private static final int STRING_IDX_SIZE = 4;
  private static final int NUM_OF_ARG_SIZE = 1;

  private static VMGetByteCodeSize ins = new VMGetByteCodeSize();
  private VMGetByteCodeSize(){}
  public static VMGetByteCodeSize getIns()
  {
    return ins;
  }
  @Override
  public Integer visitArrayAccess(VMArrayAccess vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE * 3 + TYPE_SIZE;
  }

  @Override
  public Integer visitLabel(VMLabel vmCode) {
    return 0;
  }

  @Override
  public Integer visitUncondJmp(VMUncondJmp vmCode) {
    return OPCODE_SIZE + U4_SIZE;
  }

  @Override
  public Integer visitAssign(VMAssign vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE + U4_SIZE;
  }

  @Override
  public Integer visitCondJmp(VMCondJmp vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE + U4_SIZE;
  }

  @Override
  public Integer visitLoadStore(VMLoadStore vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE + STACK_IDX_SIZE;
  }

  @Override
  public Integer visitLoadString(VMLoadString vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE + STRING_IDX_SIZE;
  }

  @Override
  public Integer visitUnOp(VMUnOp vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE * 2;
  }

  @Override
  public Integer visitBinOp(VMBinOp vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE * 3;
  }

  @Override
  public Integer visitPairAccess(VMPairAccess vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE * 2;
  }

  @Override
  public Integer visitCallDirect(VMCallDirect vmCode) {
    if (vmCode.getArgs().size() > 0xff)
      throw new RuntimeException("max num of args is 255");
    return OPCODE_SIZE + NUM_OF_ARG_SIZE + FUNC_IDX_SIZE
            + REG_IDX_SIZE * (vmCode.getArgs().size() + 1);
  }

  @Override
  public Integer visitCallVirtual(VMCallVirtual vmCode) {
    if (vmCode.getArgs().size() > 0xff)
      throw new RuntimeException("max num of args is 255");
    return OPCODE_SIZE + NUM_OF_ARG_SIZE + VT_IDX_SIZE
            + REG_IDX_SIZE * (vmCode.getArgs().size() + 1);
  }

  @Override
  public Integer visitClassAccess(VMClassAccess vmCode) {
    return OPCODE_SIZE + CLASS_IDX_SIZE + FIELD_IDX_SIZE
            + REG_IDX_SIZE + REG_IDX_SIZE;
  }

  @Override
  public Integer visitNewClass(VMNewClass vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE + CLASS_IDX_SIZE;
  }

  @Override
  public Integer visitRetn(VMRetn vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE;
  }

  @Override
  public Integer visitExit(VMExit vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE;
  }

  @Override
  public Integer visitFree(VMFree vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE;
  }

  @Override
  public Integer visitPrint(VMPrint vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE;
  }

  @Override
  public Integer visitRead(VMRead vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE;
  }

  @Override
  public Integer visitNewPair(VMNewPair vmCode) {
    return OPCODE_SIZE + REG_IDX_SIZE * 3;
  }

  @Override
  public Integer visitNewArray(VMNewArray vmCode) {
    return OPCODE_SIZE + TYPE_SIZE + REG_IDX_SIZE + U4_SIZE;
  }
}
