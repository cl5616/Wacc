package waccVM.VMInstruction;

import waccVM.VmVisitor.VMVisitor;

abstract public class VMCode
{
  abstract public<T> T accept(VMVisitor<T> visitor);
}