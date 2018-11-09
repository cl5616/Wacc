package waccVM;

import Information.ASTTypes.Type;
import Information.VmCodesInfo;
import waccVM.VMClass.VMClass;
import waccVM.VMInstruction.VMCode;
import waccVM.VMInstruction.VMLabel;
import waccVM.VmVisitor.*;
import waccVM.VmVisitor.VMEncoders.VMNoEncode;

import java.util.*;
import java.util.function.Consumer;

public class VMFunction {
  private final Deque<VMCode> instructions = new LinkedList<>();

  private String functionName;
  private VMFuncArguments args;
  private VMFuncVariables vars;
  private VMFuncDeclares functions;
  private ArrayList<VMClass> classes;

  public VMFuncDeclares getFunctions() {
    return functions;
  }

  public VMFunction(String functionName, ArrayList<Type> argTypes,
                    ArrayList<Type> varTypes, VMFuncDeclares functions,
                    ArrayList<VMClass> classes)
  {
    this.functionName = functionName;
    this.args = new VMFuncArguments(argTypes);
    this.vars = new VMFuncVariables(varTypes);
    this.functions = functions;
    this.classes = classes;
  }
  public void addInstruction(VMCode vmCode)
  {
    instructions.add(vmCode);
  }
  public int getRegNum()
  {
    VMGetRegNumVisitor getRegNum = new VMGetRegNumVisitor();
    int regNum = 0;
    for (VMCode ins : instructions)
    {
      int newRegNum = ins.accept(getRegNum);
      if (newRegNum > regNum)
      {
        regNum = newRegNum;
      }
    }
    return regNum;
  }
  private static String getSavedRegs(int nvReg)
  {
    nvReg = Math.min(VMtoARMVisitor.MAX_NUM_OF_GEN_REG, nvReg);
    if (nvReg == 0)
      return "";
    if (nvReg == 1)
      return "R" + VMtoARMVisitor.NUM_OF_RESERVED_REGS + ',';
    return "R" + VMtoARMVisitor.NUM_OF_RESERVED_REGS + "-R"
            + (VMtoARMVisitor.NUM_OF_RESERVED_REGS + nvReg - 1) + ',';
  }
  public String toArmAsm()
  {
    int vregNum = getRegNum();
    int stackSpace = vars.sizeAligned + 4 +
      (vregNum > VMtoARMVisitor.MAX_NUM_OF_GEN_REG ?
      (vregNum - VMtoARMVisitor.MAX_NUM_OF_GEN_REG) : 0) * 4;
    String regSaved = getSavedRegs(vregNum);
    StringBuilder ret = new StringBuilder();


    ret.append("push {");
    ret.append(regSaved);
    ret.append("lr}\n");//save registers

    ret.append("LDR r1,=");
    ret.append(stackSpace);
    ret.append('\n');
    ret.append("sub sp,sp,r1\n");//allocate space for local variables

    VMtoARMVisitor visitor = new VMtoARMVisitor(vregNum, vars, args, functions, functionName, classes);
    for (VMCode code : instructions)
    {
      ret.append(code.accept(visitor));
    }
    ret.append(functionName);
    ret.append("_retn:\n");

    ret.append("LDR r1,=");
    ret.append(stackSpace);
    ret.append('\n');
    ret.append("add sp,sp,r1\n");

    ret.append("pop {");
    ret.append(regSaved);
    ret.append("lr}\n");

    ret.append("bx lr\n");
    ret.append(".ltorg\n");
    return ret.toString();
  }

  public String getFunctionName() {
    return functionName;
  }
  public boolean isArgByte(int argIdx)
  {
    return args.isGivenArgByte(argIdx);
  }
  public int getArgSize()
  {//for adjust sp after calling a function
    return args.getArgsSize();
  }
  public int getArgNum(){return args.getArgNum();}
  public int getVarNum()
  {
    return vars.getVarNum();
  }

  public void addInstructions(VmCodesInfo vmCodesInfo){
    Deque<VMCode> instructions = vmCodesInfo.getVmCodes();

    for (Iterator iterator = instructions.iterator(); iterator.hasNext();) {
      addInstruction((VMCode) iterator.next());
    }
  }
  public void addVar(Type t) {
    vars.addVar(t);
  }
  public void addArg(Type t) {
    args.addArg(t);
  }
  public void addFun(VMFuncArguments a, String funcName){
    functions.insertFuncDeclares(a, funcName);
  }
  public ArrayList<Byte> toByteCode()
  {
    TreeMap<String, Long> labelDispl = new TreeMap<>();
    VMGetByteCodeSize sizeVisitor = VMGetByteCodeSize.getIns();
    long offset = 0L;
    for (VMCode code : instructions)
    {
      if (code instanceof VMLabel)
      {
        labelDispl.put(((VMLabel) code).getLabel(), offset);
      }
      offset += code.accept(sizeVisitor);
    }
    VMtoByteCode visitor = new VMtoByteCode(new VMNoEncode(), labelDispl);
    for (VMCode code : instructions)
    {
      code.accept(visitor);
    }
    return visitor.getRet();
  }

  @Override
  public String toString() {
    return functionName;
  }
}
