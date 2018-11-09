package CodeGenArgs;

import java.util.Deque;
import java.util.LinkedList;

public class RegisterList {
  private Deque<Integer> prevRegs;
  private int finalRegs;

  public RegisterList() {
    init();
  }

  public RegisterList(RegisterList registerList) {
    init();
    this.prevRegs.addAll(registerList.prevRegs);
    this.finalRegs = registerList.finalRegs;
  }

  private void init() {
    this.prevRegs = new LinkedList<>();
    this.finalRegs = 0;
  }

  public RegisterList removeFstReg() {
    RegisterList ret;
    if (prevRegs.size() == 0) {
      ret = new RegisterList();
      ret.finalRegs = this.finalRegs + 1;
    } else {
      ret = new RegisterList(this);
      ret.prevRegs.removeFirst();
    }
    return ret;
  }

  public RegisterList swapFst2Reg() {
    RegisterList ret = new RegisterList(this);
    if (ret.prevRegs.size() == 0) {
      int tmp = ret.finalRegs;
      ret.finalRegs += 2;
      ret.prevRegs.addFirst(tmp);
      ret.prevRegs.addFirst(tmp + 1);
    } else if (ret.prevRegs.size() == 1) {
      int tmp = ret.finalRegs;
      ret.finalRegs += 1;
      ret.prevRegs.addFirst(tmp);
    }
    else //sizeAligned is bigger than 1
    {
      int fst = ret.prevRegs.removeFirst();
      int snd = ret.prevRegs.removeFirst();
      prevRegs.addFirst(fst);
      prevRegs.addFirst(snd);
    }
    return ret;
  }

  public int getFstReg() {
    return prevRegs.peekFirst() == null ? finalRegs : prevRegs.peekFirst();
  }
}
