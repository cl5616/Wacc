package waccVM.VmVisitor;

import waccVM.VMInstruction.*;

public interface VMVisitor<T>
{
  T visitArrayAccess(VMArrayAccess vmCode);
  T visitLabel(VMLabel vmCode);
  T visitUncondJmp(VMUncondJmp vmCode);
  T visitAssign(VMAssign vmCode);
  T visitCondJmp(VMCondJmp vmCode);
  T visitLoadStore(VMLoadStore vmCode);
  T visitLoadString(VMLoadString vmCode);
  T visitUnOp(VMUnOp vmCode);
  T visitBinOp(VMBinOp vmCode);
  T visitPairAccess(VMPairAccess vmCode);
  T visitCallDirect(VMCallDirect vmCode);
  T visitCallVirtual(VMCallVirtual vmCode);
  T visitClassAccess(VMClassAccess vmCode);
  T visitNewClass(VMNewClass vmCode);
  T visitRetn(VMRetn vmCode);
  T visitExit(VMExit vmCode);
  T visitFree(VMFree vmCode);
  T visitPrint(VMPrint vmCode);
  T visitRead(VMRead vmCode);
  T visitNewPair(VMNewPair vmCode);
  T visitNewArray(VMNewArray vmCode);
}
