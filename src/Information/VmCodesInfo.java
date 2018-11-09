package Information;

import waccVM.VMInstruction.VMCode;

import java.util.*;

public class VmCodesInfo extends Information {
  private Deque<VMCode> vmCodes;

  public VmCodesInfo() {
    vmCodes = new LinkedList<>();
  }

  public VmCodesInfo(VMCode initial) {//pre: initial being newed directly
    vmCodes = new LinkedList<>();
    vmCodes.addFirst(initial);
  }

  public void insertFrontInstruction(VmCodesInfo vmCodes) {
    Iterator<VMCode> riter = vmCodes.vmCodes.descendingIterator();
    while (riter.hasNext()) {
      this.vmCodes.addFirst(riter.next());
    }
  }

  public void insertBackInstruction(VmCodesInfo vmCodes) {
    Iterator<VMCode> iter = vmCodes.vmCodes.iterator();
    while (iter.hasNext()) {
      this.vmCodes.addLast(iter.next());
    }
  }

  public Deque<VMCode> getVmCodes() {
    return vmCodes;
  }
}
