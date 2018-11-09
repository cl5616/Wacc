package waccVM.VmVisitor;

import waccVM.VMInstruction.*;

import java.util.Collections;

public class VMGetRegNumVisitor implements VMVisitor<Integer>
{
  @Override
  public Integer visitArrayAccess(VMArrayAccess vmCode) {
    return Math.max(Math.max(vmCode.getArrayIdx(), vmCode.getIndex_of_elem()),
        vmCode.getSrcDstIdx()) + 1;
    //+1 for get number of registers instead of max index
  }

  @Override
  public Integer visitLabel(VMLabel vmCode) {
    return 0;
  }

  @Override
  public Integer visitUncondJmp(VMUncondJmp vmCode) {
    return 0;
  }

  @Override
  public Integer visitAssign(VMAssign vmCode) {
      return vmCode.getLhsIdx() + 1;
  }

  @Override
  public Integer visitCondJmp(VMCondJmp vmCode) {
    return vmCode.getIfJmpIdx() + 1;
  }

  @Override
  public Integer visitLoadStore(VMLoadStore vmCode) {
    return vmCode.getRegIdx() + 1;
  }

  @Override
  public Integer visitLoadString(VMLoadString vmCode) {
    return vmCode.getDstRegIdx() + 1;
  }

  @Override
  public Integer visitUnOp(VMUnOp vmCode) {
    return Math.max(vmCode.getArg(), vmCode.getDstIdx()) + 1;
  }

  @Override
  public Integer visitBinOp(VMBinOp vmCode) {
    return Math.max(Math.max(vmCode.getDstIdx(),
        vmCode.getLhs()),vmCode.getRhs()) + 1;
  }

  @Override
  public Integer visitPairAccess(VMPairAccess vmCode) {
    return Math.max(vmCode.getPairIdx(),vmCode.getDst_srcIdx()) + 1;
  }

  @Override
  public Integer visitCallDirect(VMCallDirect vmCode) {
    if (vmCode.getArgs().size() == 0) {
      return vmCode.getRetIdx() +  1;
    }

    return Math.max(vmCode.getRetIdx(),
        Collections.max(vmCode.getArgs())) + 1;
  }

  @Override
  public Integer visitCallVirtual(VMCallVirtual vmCode) {
    if (vmCode.getArgs().size() == 0) {
      return vmCode.getRetIdx() +  1;
    }

    return Math.max(vmCode.getRetIdx(),
        Collections.max(vmCode.getArgs())) + 1;
  }

  @Override
  public Integer visitClassAccess(VMClassAccess vmCode) {
    return Math.max(vmCode.getObject(), vmCode.getSrc_dstIdx()) + 1;
  }

  @Override
  public Integer visitNewClass(VMNewClass vmCode) {
    return vmCode.getObjectReg() + 1;
  }


  @Override
  public Integer visitRetn(VMRetn vmCode) {
    return vmCode.getIdxRetReg() + 1;
  }

  @Override
  public Integer visitExit(VMExit vmCode) {
    return vmCode.getIdxExitCode() + 1;
  }

  @Override
  public Integer visitFree(VMFree vmCode) {
    return vmCode.getIdxPairArray() + 1;
  }

  @Override
  public Integer visitPrint(VMPrint vmCode) {
    return vmCode.getIdxReg() + 1;
  }

  @Override
  public Integer visitRead(VMRead vmCode) {
    return vmCode.getIdxDstReg() + 1;
  }

  @Override
  public Integer visitNewPair(VMNewPair vmCode) {
    return Math.max(Math.max(vmCode.getIdxFst(),
        vmCode.getIdxSnd()),vmCode.getDstIdx()) + 1;
  }

  @Override
  public Integer visitNewArray(VMNewArray vmCode) {
    return vmCode.getDstIdx() + 1;
  }
}
