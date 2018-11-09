package waccVM.VmVisitor;

import Information.ASTTypes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//to optimize the stack allocation
//given list of type, allocate the displacement to minimize the space
//and still obey alignment of dword
public class VMFuncVariables
{
  private Map<Integer, Integer> idxToDisplBytes;
  private Map<Integer, Integer> idxToDisplDwords;
  private final ArrayList<Type> varType;
  private ArrayList<Boolean> isByte;
  public final int sizeAligned;

  public static boolean isByte(Type t) {
    return t instanceof BoolType || t instanceof CharType;
  }
  public boolean isByte(int idx)
  {
    return this.isByte.get(idx);
  }
  public int idxToDisplacementFromVReg(int idx)
  {
    if (isByte(idx))
    {
      return idxToDisplBytes.get(idx);
    } else {
      return idxToDisplDwords.get(idx);
    }
  }
  public int getVarNum()
  {
    return varType.size();
  }
  public VMFuncVariables(ArrayList<Type> idxToType)
  {
    this.varType = idxToType;
    idxToDisplBytes = new HashMap<>();
    idxToDisplDwords = new HashMap<>();
    isByte = new ArrayList<>();
    int num_of_bytes = 0;
    for (Type t : idxToType) {
      if (isByte(t)) {
        num_of_bytes++;
        isByte.add(true);
      } else {
        isByte.add(false);
      }
    }

    num_of_bytes = (num_of_bytes & 3) == 0 ? num_of_bytes : (num_of_bytes / 4 + 1) * 4;
    //if not the multiple of 4, take the floor

    int i = 0;
    int byteDispl = 0;
    int dwordDispl = num_of_bytes;
    for (Type t : idxToType) {
      if (isByte(t)) {
        idxToDisplBytes.put(i, byteDispl);
        byteDispl++;
      } else {
        idxToDisplDwords.put(i, dwordDispl);
        dwordDispl += 4;
      }
      i++;
    }
    this.sizeAligned = dwordDispl;
  }

  public Type getVarType(int idx) {
    return varType.get(idx);
  }

  public void addVar(Type t){
    varType.add(t);
  }
}
