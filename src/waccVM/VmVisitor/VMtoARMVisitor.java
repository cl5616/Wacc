package waccVM.VmVisitor;

import Information.OpInfo.BinOpInfo;
import Information.OpInfo.UnOpInfo;
import waccVM.VMClass.VMClass;
import waccVM.VMClass.VMClassDeclare;
import waccVM.VMFuncDeclares;
import waccVM.VMInstruction.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

import static waccVM.VMClass.VMClassNull.VT_SIZE;

public class VMtoARMVisitor implements VMVisitor<String>{
  public static final int MAX_NUM_OF_GEN_REG = 4;
  public static final int NUM_OF_RESERVED_REGS = 4;
  private static final int BUF_REG_R0 = 0;
  private static final int BUF_REG_R1 = 1;
  private static final int BUF_REG_R2 = 2;
  private static final int BUF_REG_R3 = 3;
  private static final int DWORD_SIZE = 4;
  private static final int BYTE_SIZE = 1;

  public static final String BUILTIN_FUNC =
      "p_check_divide_by_zero:\n" +
          "\tPUSH {lr}\n" +
          "\tCMP r1, #0\n" +
          "\tLDREQ r0, =msg_1\n" +
          "\tBLEQ p_throw_runtime_error\n" +
          "\tPOP {pc}\n" +
          "p_check_array_bounds:\n" +
          "\tPUSH {lr}\n" +
          "\tCMP r0, #0\n" +
          "\tLDRLT r0, =msg_2\n" +
          "\tBLLT p_throw_runtime_error\n" +
          "\tLDR r1, [r1]\n" +
          "\tCMP r0, r1\n" +
          "\tLDRCS r0, =msg_3\n" +
          "\tBLCS p_throw_runtime_error\n" +
          "\tPOP {pc}\n" +
          "p_free_pair:\n" +
          "\tPUSH {lr}\n" +
          "\tCMP r0, #0\n" +
          "\tLDREQ r0, =msg_4\n" +
          "\tBEQ p_throw_runtime_error\n" +
          "\tPUSH {r0}\n" +
          "\tLDR r0, [r0]\n" +
          "\tBL free\n" +
          "\tLDR r0, [sp]\n" +
          "\tLDR r0, [r0, #4]\n" +
          "\tBL free\n" +
          "\tPOP {r0}\n" +
          "\tBL free\n" +
          "\tPOP {pc}\n" +
          "p_print_ln:\n" +
          "\tPUSH {lr}\n" +
          "\tLDR r0, =msg_5\n" +
          "\tADD r0, r0, #4\n" +
          "\tBL puts\n" +
          "\tMOV r0, #0\n" +
          "\tBL fflush\n" +
          "\tPOP {pc}\n" +
          "p_print_int:\n" +
          "\tPUSH {lr}\n" +
          "\tMOV r1, r0\n" +
          "\tLDR r0, =msg_6\n" +
          "\tADD r0, r0, #4\n" +
          "\tBL printf\n" +
          "\tMOV r0, #0\n" +
          "\tBL fflush\n" +
          "\tPOP {pc}\n" +
          "p_print_bool:\n" +
          "\tPUSH {lr}\n" +
          "\tCMP r0, #0\n" +
          "\tLDRNE r0, =msg_7\n" +
          "\tLDREQ r0, =msg_8\n" +
          "\tADD r0, r0, #4\n" +
          "\tBL printf\n" +
          "\tMOV r0, #0\n" +
          "\tBL fflush\n" +
          "\tPOP {pc}\n" +
          "p_print_string:\n" +
          "\tPUSH {lr}\n" +
          "\tLDR r1, [r0]\n" +
          "\tADD r2, r0, #4\n" +
          "\tLDR r0, =msg_9\n" +
          "\tADD r0, r0, #4\n" +
          "\tBL printf\n" +
          "\tMOV r0, #0\n" +
          "\tBL fflush\n" +
          "\tPOP {pc}\n" +
          "p_print_reference:\n" +
          "\tPUSH {lr}\n" +
          "\tMOV r1, r0\n" +
          "\tLDR r0, =msg_10\n" +
          "\tADD r0, r0, #4\n" +
          "\tBL printf\n" +
          "\tMOV r0, #0\n" +
          "\tBL fflush\n" +
          "\tPOP {pc}\n" +
          "p_read_int:\n" +
          "\tPUSH {lr}\n" +
          "\tMOV r1, r0\n" +
          "\tLDR r0, =msg_11\n" +
          "\tADD r0, r0, #4\n" +
          "\tBL scanf\n" +
          "\tPOP {pc}\n" +
          "p_read_char:\n" +
          "\tPUSH {lr}\n" +
          "\tMOV r1, r0\n" +
          "\tLDR r0, =msg_12\n" +
          "\tADD r0, r0, #4\n" +
          "\tBL scanf\n" +
          "\tPOP {pc}\n" +
          "p_throw_runtime_error:\n" +
          "\tBL p_print_string\n" +
          "\tMOV r0, #-1\n" +
          "\tBL exit\n" +
          "p_check_null_pointer:\n" +
          "\tPUSH {lr}\n" +
          "\tCMP r0, #0\n" +
          "\tLDREQ r0, =msg_4\n" +
          "\tBLEQ p_throw_runtime_error\n" +
          "\tPOP {pc}\n" +
          "p_throw_overflow_error:\n" +
          "\tLDR r0, =msg_13\n" +
          "\tBL p_throw_runtime_error\n" +
          "p_free_array:\n" +
          "\tPUSH {lr}\n" +
          "\tCMP r0, #0\n" +
          "\tLDREQ r0, =msg_4\n" +
          "\tBEQ p_throw_runtime_error\n" +
          "\tBL free\n" +
          "\tPOP {pc}\n"
      ;//from reference compiler

  public static final String BUILTIN_DATA = "msg_1:\n" +
      "\t.word 45\n" +
      "\t.ascii\t\"DivideByZeroError: divide or modulo by zero\\n\\0\"\n" +
      "msg_2:\n" +
      "\t.word 44\n" +
      "\t.ascii\t\"ArrayIndexOutOfBoundsError: negative index\\n\\0\"\n" +
      "msg_3:\n" +
      "\t.word 45\n" +
      "\t.ascii\t\"ArrayIndexOutOfBoundsError: index too large\\n\\0\"\n" +
      "msg_4:\n" +
      "\t.word 50\n" +
      "\t.ascii\t\"NullReferenceError: dereference a null reference\\n\\0\"\n" +
      "msg_5:\n" +
      "\t.word 1\n" +
      "\t.ascii\t\"\\0\"\n" +
      "msg_6:\n" +
      "\t.word 3\n" +
      "\t.ascii\t\"%d\\0\"\n" +
      "msg_7:\n" +
      "\t.word 5\n" +
      "\t.ascii\t\"true\\0\"\n" +
      "msg_8:\n" +
      "\t.word 6\n" +
      "\t.ascii\t\"false\\0\"\n" +
      "msg_9:\n" +
      "\t.word 5\n" +
      "\t.ascii\t\"%.*s\\0\"\n" +
      "msg_10:\n" +
      "\t.word 3\n" +
      "\t.ascii\t\"%p\\0\"\n" +
      "msg_11:\n" +
      "\t.word 3\n" +
      "\t.ascii\t\"%d\\0\"\n" +
      "msg_12:\n" +
      "\t.word 4\n" +
      "\t.ascii\t\" %c\\0\"\n" +
      "msg_13:\n" +
      "\t.word 82\n" +
      "\t.ascii\t\"OverflowError: the result is too small/large to store in a 4-byte signed-integer.\\n\"\n";
  //from reference compiler


  private final int regOnStackNum;
  private final VMFuncVariables vmStackToArmStack;
  private final int registerNum;
  private VMFuncArguments idxToArgType;
  private VMFuncDeclares functions;
  private final String functionName;
  private ArrayList<VMClass> classes;

  public VMtoARMVisitor(int registerNum, VMFuncVariables idxToVarType,
      VMFuncArguments argType, VMFuncDeclares functions,
      String functionName, ArrayList<VMClass> classes)
  {
    this.registerNum = registerNum;
    this.functions = functions;
    this.regOnStackNum = registerNum - MAX_NUM_OF_GEN_REG > 0 ?
        registerNum - MAX_NUM_OF_GEN_REG : 0;
    this.vmStackToArmStack = idxToVarType;
    this.idxToArgType = argType;
    this.functionName = functionName;
    this.classes = classes;
  }

  private static int vRegToArmReg(int vReg)
  {
    return NUM_OF_RESERVED_REGS + vReg;
  }
  private int getReadBufferDisplacement()
  {
    return regOnStackNum * DWORD_SIZE +
        vmStackToArmStack.sizeAligned;
  }
  private static int regToSpDisplacement(int vReg)
  {
    assert vRegToArmReg(vReg) >= MAX_NUM_OF_GEN_REG :
        "error: register index must be bigger than MAX_NUM_OF_GEN_REG";
    return (vReg - MAX_NUM_OF_GEN_REG) * DWORD_SIZE;
  }
  private int localVarIdxToDisplacement(int idx)
  {
    return vmStackToArmStack.idxToDisplacementFromVReg(idx)
        + regOnStackNum * DWORD_SIZE;
  }
  private int argIdxToDisplacement(int idx)
  {
    return regOnStackNum * DWORD_SIZE +
        vmStackToArmStack.sizeAligned +
        DWORD_SIZE * 2/*read buffer
                            {lr}*/ +
        Math.min(registerNum, MAX_NUM_OF_GEN_REG) * DWORD_SIZE+
        //saved registers
        idxToArgType.getDisplacement(idx);
  }

  //pre: register in arm form
  //so no virtual register on the stack here
  static private String unOpToArmAsm(UnOpInfo.UnOp unOp, int dstReg, int regIdx)
  {
    switch (unOp)
    {
      case NOT:
        return "CMP R"+regIdx+",#0\nMOVEQ R"+dstReg+",#1\nMOVNE R"+dstReg+",#0\n";
      case NEG:
        return "RSBS R"+dstReg+",R"+regIdx+",#0\n" +
            "BLVS p_throw_overflow_error\n";
      case LEN://reg must be array or string type
        return "LDR R"+dstReg+",[R"+regIdx+"]\n";
      case CHR:
      case ORD:
        return dstReg == regIdx ? "" : "MOV R"+dstReg+",R"+regIdx+"\n";
      case PLUS:
        return "MOV R"+dstReg+",R"+regIdx+"\n";
    }
    return null;//cannot reach here
  }

  //pre: register in arm form
  //so no virtual register on the stack here
  static private String binOpToArmAsm(BinOpInfo.BinOp binOp, int dstReg, int reg1, int reg2)
  {//reg idx < MAX_NUM_OF_GEN_REG
    switch (binOp)
    {
      case ADD:
        return "ADDS R"+dstReg+",R"+reg1+",R"+reg2+"\nBLVS p_throw_overflow_error\n";
      case SUB:
        return "SUBS R"+dstReg+",R"+reg1+",R"+reg2+"\nBLVS p_throw_overflow_error\n";
      case MUL:
        return "SMULL R"+dstReg+",R"+ BUF_REG_R3 +",R"+reg1+",R"+reg2+"\n" +
            "CMP R"+ BUF_REG_R3 +",R"+dstReg+",ASR #31\n" +
            "BLNE p_throw_overflow_error\n";
      case DIV:
        return (reg1 == 0 ? "" : "MOV R0,R"+reg1+"\n") +
            (reg2 == 1 ? "" : "MOV R1,R"+reg2+"\n") +
            "BL p_check_divide_by_zero\n" +
            "BL __aeabi_idiv\n" +
            "MOV R"+dstReg+",R0\n";
      case MOD:
        return (reg1 == 0 ? "" : "MOV R0,R"+reg1+"\n") +
            (reg2 == 1 ? "" : "MOV R1,R"+reg2+"\n") +
            "BL p_check_divide_by_zero\n" +
            "BL __aeabi_idivmod\n" +
            "MOV R"+dstReg+",R1\n";
      case EQ:
        return "CMP R"+reg1+",R"+reg2+"\nMOVNE R"+dstReg+",#0\n" +
            "MOVEQ R"+dstReg+",#1\n";
      case GT:
        return "CMP R"+reg1+",R"+reg2+"\nMOVLE R"+dstReg+",#0\n" +
            "MOVGT R"+dstReg+",#1\n";
      case LT:
        return "CMP R"+reg1+",R"+reg2+"\nMOVGE R"+dstReg+",#0\n" +
            "MOVLT R"+dstReg+",#1\n";
      case GTE:
        return "CMP R"+reg1+",R"+reg2+"\nMOVGE R"+dstReg+",#1\n" +
            "MOVLT R"+dstReg+",#0\n";
      case LTE:
        return "CMP R"+reg1+",R"+reg2+"\nMOVLE R"+dstReg+",#1\n" +
            "MOVGT R"+dstReg+",#0\n";
      case NEQ:
        return "CMP R"+reg1+",R"+reg2+"\nMOVNE R"+dstReg+",#1\n" +
            "MOVEQ R"+dstReg+",#0\n";
      case AND://pre: reg1 and reg2 are 0 or 1
        return "AND R"+dstReg+",R"+reg1+",R"+reg2+"\n";
      case OR:
        return "ORR R"+dstReg+",R"+reg1+",R"+reg2+"\n";
    }
    return null;//can't reach here
  }

  static private void calculateElemAddr(StringBuilder ret,
      int array, int indexAccess,
      int arrayV)
  {//except the lsl step
    //post: BUF_REG_R0 is the addr of elem
    boolean isReload = false;
    if (indexAccess != BUF_REG_R0)
    {
      ret.append("MOV r0, r");
      ret.append(indexAccess);
      ret.append('\n');
    }
    else
    {
      ret.append("MOV r");
      ret.append(BUF_REG_R3);
      ret.append(",r");
      ret.append(indexAccess);
      ret.append('\n');
      indexAccess = BUF_REG_R3;
      //if is r0, bl will change the value of r0
      //thus, store it into BUF_REG_R3
    }
    if (array != BUF_REG_R1)
    {
      ret.append("MOV r1, r");
      ret.append(array);
      ret.append('\n');
    }
    else
    {//if array is in r1, that is, array is loaded from stack
      isReload = true;
    }
    ret.append("BL p_check_array_bounds\n");
    if (isReload)
    {
      convertToArmReg(BUF_REG_R1, arrayV, ret,
          VMLoadStore.LoadStore.LOAD);
    }//since BL will corrupt R1, so reload if array is virtual register in stack
    ret.append("ADD r");
    ret.append(BUF_REG_R0);//buf reg is going to be the address of the elem
    ret.append(",r");
    ret.append(array);
    ret.append(",#4\n");
    ret.append("ADD r");
    ret.append(BUF_REG_R0);
    ret.append(",r");
    ret.append(BUF_REG_R0);
    ret.append(",r");
    ret.append(indexAccess);
  }

  //MOV
  @Override
  public String visitArrayAccess(VMArrayAccess vmCode) {
    int indexAccess = vmCode.getIndex_of_elem();
    int dst_srcReg = vmCode.getSrcDstIdx();
    int arrayV = vmCode.getArrayIdx();
    VMLoadStore.LoadStore load_store = vmCode.getLoad_store();
    StringBuilder ret = new StringBuilder();
    StringBuilder store = new StringBuilder();

    int array = convertToArmReg(BUF_REG_R1, arrayV, ret,
        VMLoadStore.LoadStore.LOAD);
    indexAccess = convertToArmReg(BUF_REG_R0,
        indexAccess, ret, VMLoadStore.LoadStore.LOAD);

    if (load_store == VMLoadStore.LoadStore.LOAD)
    {//load into vreg
      dst_srcReg = convertToArmReg(BUF_REG_R2, dst_srcReg, store,
          VMLoadStore.LoadStore.STORE);
      calculateElemAddr(ret, array, indexAccess, arrayV);
      if (vmCode.getElemSize() == ElemSize.DWORD)
        ret.append(", LSL #2\nLDR");
      else
        ret.append("\nLDRSB");
    }
    else//store from vreg to array elem
    {
      dst_srcReg = convertToArmReg(BUF_REG_R2, dst_srcReg, ret,
          VMLoadStore.LoadStore.LOAD);
      calculateElemAddr(ret, array, indexAccess, arrayV);
      if (vmCode.getElemSize() == ElemSize.DWORD)
        ret.append(", LSL #2\nSTR");
      else
        ret.append("\nSTRB");
    }
    ret.append(" r");
    ret.append(dst_srcReg);
    ret.append(", [r");
    ret.append(BUF_REG_R0);
    ret.append("]\n");
    ret.append(store);
    return ret.toString();
  }


  static private int convertToArmReg(int bufreg, int reg, StringBuilder asm, VMLoadStore.LoadStore load_store)
  {
    int ret;
    String instruction = load_store.toString();
    if (reg >= MAX_NUM_OF_GEN_REG)
    {
      asm.append(instruction);
      asm.append(" R");
      asm.append(bufreg);
      asm.append(",[SP,#");
      asm.append(regToSpDisplacement(reg));
      asm.append("]\n");
      ret = bufreg;
    }
    else
    {
      ret = vRegToArmReg(reg);
    }
    return ret;
  }

  @Override
  public String visitLabel(VMLabel vmCode) {
    return vmCode.getLabel() + ":\n";
  }

  @Override
  public String visitUncondJmp(VMUncondJmp vmCode) {
    return "B " + vmCode.getDst() + '\n';
  }

  @Override
  public String visitAssign(VMAssign vmCode)
  {
    StringBuilder ret = new StringBuilder();
    StringBuilder store = new StringBuilder();
    int dstIdx = convertToArmReg(BUF_REG_R2, vmCode.getLhsIdx(),
        store, VMLoadStore.LoadStore.STORE);

    ret.append("LDR R");
    ret.append(dstIdx);
    ret.append(",=");
    ret.append(vmCode.getRhs());

    ret.append('\n');
    ret.append(store);
    return ret.toString();
  }

  @Override
  public String visitCondJmp(VMCondJmp vmCode)
  {
    StringBuilder ret = new StringBuilder();
    int ifJmp = convertToArmReg(BUF_REG_R0, vmCode.getIfJmpIdx(),
        ret, VMLoadStore.LoadStore.LOAD);
    ret.append("TST R");
    ret.append(ifJmp);
    ret.append(",R");
    ret.append(ifJmp);
    ret.append("\nBNE ");
    ret.append(vmCode.getDst());
    ret.append('\n');
    return ret.toString();
  }

  @Override
  public String visitLoadStore(VMLoadStore vmCode)
  {
    StringBuilder ret = new StringBuilder();
    StringBuilder storeVReg = new StringBuilder();

    int vregIdx = vmCode.getRegIdx();
    int var_argIdx = vmCode.getStackIdx();

    int spDisplacement;
    VMLoadStore.VarOrArg var_arg = vmCode.getVar_arg();
    if (var_arg == VMLoadStore.VarOrArg.VAR)
    {
      spDisplacement = localVarIdxToDisplacement(var_argIdx);
    }
    else//ARG
    {
      spDisplacement = argIdxToDisplacement(var_argIdx);
    }
    int regIdx;
    if (vmCode.getLoad_store() == VMLoadStore.LoadStore.LOAD)
    {//LOAD to vregister, so store if vreg is on stack
      regIdx = convertToArmReg(BUF_REG_R2, vregIdx,
          storeVReg, VMLoadStore.LoadStore.STORE);
      if (var_arg == VMLoadStore.VarOrArg.VAR)
        ret.append(vmStackToArmStack.isByte(var_argIdx) ?
            "LDRSB" : "LDR");
      else//ARG
        ret.append(idxToArgType.isGivenArgByte(var_argIdx) ?
            "LDRSB" : "LDR");
    }
    else//STORE
    {//STORE from vreg to stack
      regIdx = convertToArmReg(BUF_REG_R0, vregIdx,
          ret, VMLoadStore.LoadStore.LOAD);
      if (var_arg == VMLoadStore.VarOrArg.VAR)
        ret.append(vmStackToArmStack.isByte(var_argIdx) ?
            "STRB" : "STR");
      else//ARG
        ret.append(idxToArgType.isGivenArgByte(var_argIdx) ?
            "STRB" : "STR");
    }
    ret.append(" R");
    ret.append(regIdx);
    ret.append(",[sp,#");
    ret.append(spDisplacement);
    ret.append("]\n");
    ret.append(storeVReg);
    return ret.toString();
  }

  @Override
  public String visitLoadString(VMLoadString vmCode) {
    StringBuilder ret = new StringBuilder();
    StringBuilder store = new StringBuilder();
    int dstRegIdx = convertToArmReg(BUF_REG_R2,
        vmCode.getDstRegIdx(), store, VMLoadStore.LoadStore.STORE);
    ret.append("LDR r");
    ret.append(dstRegIdx);
    ret.append(", =string_");
    ret.append(vmCode.getStringIdx());
    ret.append('\n');
    ret.append(store);
    return ret.toString();
  }

  @Override
  public String visitUnOp(VMUnOp vmCode) {
    int vm_reg_var = vmCode.getArg();
    int dst = vmCode.getDstIdx();
    StringBuilder ret = new StringBuilder();
    StringBuilder store = new StringBuilder();
    int arm_reg_displ;
    arm_reg_displ = convertToArmReg(BUF_REG_R0, vm_reg_var,
        ret, VMLoadStore.LoadStore.LOAD);
    dst = convertToArmReg(BUF_REG_R2, dst,
        store, VMLoadStore.LoadStore.STORE);
    ret.append(unOpToArmAsm(vmCode.getUnOp(), dst, arm_reg_displ));
    ret.append(store);
    return ret.toString();
  }

  @Override
  public String visitBinOp(VMBinOp vmCode) {
    int reg1 = vmCode.getLhs();
    int reg2 = vmCode.getRhs();
    int dst = vmCode.getDstIdx();
    StringBuilder ret = new StringBuilder();
    StringBuilder store = new StringBuilder();

    reg1 = convertToArmReg(BUF_REG_R0, reg1, ret, VMLoadStore.LoadStore.LOAD);
    reg2 = convertToArmReg(BUF_REG_R1, reg2, ret, VMLoadStore.LoadStore.LOAD);

    dst = convertToArmReg(BUF_REG_R2, dst, store, VMLoadStore.LoadStore.STORE);

    ret.append(binOpToArmAsm(vmCode.getBinOp(), dst, reg1, reg2));
    ret.append(store);
    return ret.toString();
  }

  private String visitHeapStuff(int regPointer, int dst_src_reg,
      int displacement, VMLoadStore.LoadStore load_store,
      ElemSize size)
  {

    String instruction = load_store.toString();

    StringBuilder ret = new StringBuilder();
    StringBuilder for_stack_dst_src = new StringBuilder();
    //StringBuilder init

    regPointer = convertToArmReg(BUF_REG_R1, regPointer, ret, VMLoadStore.LoadStore.LOAD);
    //the register containing pair address

    ret.append("MOV r0, r");
    ret.append(regPointer);
    ret.append("\nBL p_check_null_pointer\n");
    //check null pointer

    dst_src_reg = convertToArmReg(BUF_REG_R2, dst_src_reg, for_stack_dst_src,
        load_store.invert());
    //the register that is src or dst

    if (load_store == VMLoadStore.LoadStore.STORE)
      ret.append(for_stack_dst_src);

    ret.append(instruction);
    ret.append(size.getLdrstrSuffix());
    ret.append(" R");
    ret.append(dst_src_reg);
    ret.append(",[R");
    ret.append(regPointer);
    ret.append(",#");
    ret.append(displacement);
    ret.append("]\n");

    //ldr/str r?,[rpair,#displacement] //core step
    if (load_store == VMLoadStore.LoadStore.LOAD)
      ret.append(for_stack_dst_src);
    return ret.toString();
  }

  @Override
  public String visitPairAccess(VMPairAccess vmCode) {
    int pairReg = vmCode.getPairIdx();
    int dst_src_reg = vmCode.getDst_srcIdx();
    int displacement = vmCode.getFstOrSnd().getHeapDispl();
    VMLoadStore.LoadStore load_store = vmCode.getLoad_store();
    //get basic data

    return visitHeapStuff(pairReg, dst_src_reg, displacement,
        load_store, ElemSize.DWORD);

  }

  private String visitCall(VMCall vmCode, Consumer<StringBuilder> call_resotre)
  {
    LinkedList<Integer> args = vmCode.getArgs();
    Iterator<Integer> regIdxs = args.descendingIterator();

    StringBuilder ret = new StringBuilder();
    StringBuilder storeRet = new StringBuilder();
    int retReg = convertToArmReg(BUF_REG_R2, vmCode.getRetIdx(),
        storeRet, VMLoadStore.LoadStore.STORE);

    while (regIdxs.hasNext())
    {
      int regIdx = regIdxs.next();
      int displacement;
      displacement = DWORD_SIZE;
      regIdx = convertToArmReg(BUF_REG_R0,
          regIdx, ret, VMLoadStore.LoadStore.LOAD);
      ret.append("STR");
      ret.append(" r");
      ret.append(regIdx);
      ret.append(",[sp, #-");
      ret.append(displacement);//always 4
      ret.append("]!\n");
    }

    call_resotre.accept(ret);

    ret.append("ADD sp, sp, #");
    ret.append(args.size() * 4);
    ret.append("\nMOV r");
    ret.append(retReg);
    ret.append(", r0\n");
    ret.append(storeRet);
    return ret.toString();
  }

  @Override
  public String visitCallVirtual(VMCallVirtual vmCode)
  {
    int vtIdx = vmCode.getVtIdx();
    int this_reg = vmCode.getArgs().get(0);//this
    return visitCall(vmCode, (StringBuilder ret)->{
      int reg = convertToArmReg(BUF_REG_R0, this_reg,
          ret, VMLoadStore.LoadStore.LOAD);
      ret.append("MOV R0,R");
      ret.append(reg);
      ret.append("\nldr r0,[r0]\n");
      ret.append("mov r1,#");
      ret.append(vtIdx);
      ret.append("\nldr r0,[r0,r1,lsl #2]\nblx r0\n");
    });
  }

  @Override
  public String visitCallDirect(VMCallDirect vmCode)
  {//pre: the register list correspond to the function types of arguments
    int funcIdx = vmCode.getFuncIdx();
    return visitCall(vmCode, (StringBuilder ret) -> {
      ret.append("BL ");
      ret.append(functions.getFuncName(funcIdx));
      ret.append("\n");
    });
  }


  @Override
  public String visitClassAccess(VMClassAccess vmCode) {
    int classIdx = vmCode.getClassIdx();
    VMClass classDeclare = classes.get(classIdx);
    int fieldDispl = classDeclare.getFieldDispl(
        vmCode.getFieldReferenceIdx());
    int regObject = vmCode.getObject();
    int src_dst = vmCode.getSrc_dstIdx();
    VMLoadStore.LoadStore load_store = vmCode.getLoad_store();

    return visitHeapStuff(regObject, src_dst, fieldDispl,
        load_store, classDeclare.getField(
            vmCode.getFieldReferenceIdx()).getType().getByteSize());
  }

  public static void getClassVTName(VMClassDeclare classDeclare, StringBuilder ret)
  {
    ret.append("vt_");
    ret.append(classDeclare.getClassName());
    //class A, virtual table has name in arm A@v_table@:
  }

  @Override
  public String visitNewClass(VMNewClass vmCode) {
    int objReg = vmCode.getObjectReg();
    StringBuilder store = new StringBuilder();
    StringBuilder ret = new StringBuilder();

    objReg = convertToArmReg(BUF_REG_R0, objReg, store,
        VMLoadStore.LoadStore.STORE);

    int i = vmCode.getClassIdx();
    VMClassDeclare class_ = (VMClassDeclare)classes.get(vmCode.getClassIdx());
    int size = class_.getSize();

    ret.append("ldr r0,=");
    ret.append(size);
    ret.append("\nbl malloc\nmov r");
    ret.append(objReg);
    ret.append(",r0\n");
    ret.append(store);//malloc and store it into designated register

    //content processing
    ret.append("ldr r1,=");
    getClassVTName(class_,ret);
    ret.append("\nstr r1,[r0],#");
    ret.append(VT_SIZE);
    ret.append("\nmov r1,#0\n");
    ret.append("ldr r2,=");
    ret.append(size - VT_SIZE);
    ret.append("\nbl __aeabi_memset\n");//todo not sure

    return ret.toString();
  }

  @Override
  public String visitRetn(VMRetn vmCode) {
    StringBuilder ret = new StringBuilder();
    int retReg = convertToArmReg(BUF_REG_R0, vmCode.getIdxRetReg(),
        ret, VMLoadStore.LoadStore.LOAD);
    if (retReg != BUF_REG_R0)
    {
      ret.append("MOV R0,R");
      ret.append(retReg);
      ret.append('\n');
    }
    ret.append("B ");
    ret.append(functionName);
    ret.append("_retn\n");
    return ret.toString();
  }


  private static void loadR0ForSyscall(StringBuilder ret, int idxReg)
  {
    if (idxReg != BUF_REG_R0)
    {
      ret.append("MOV R0,R");
      ret.append(idxReg);
      ret.append('\n');
    }
    ret.append("BL ");
  }
  private static String unarySyscall(String syscallName, int idxVReg)
  {
    StringBuilder ret = new StringBuilder();
    int idxReg = convertToArmReg(BUF_REG_R0,
        idxVReg, ret, VMLoadStore.LoadStore.LOAD);
    loadR0ForSyscall(ret, idxReg);
    ret.append(syscallName);
    ret.append("\n");
    return ret.toString();
  }
  @Override
  public String visitExit(VMExit vmCode) {
    return unarySyscall("exit", vmCode.getIdxExitCode());
  }

  @Override
  public String visitFree(VMFree vmCode) {
    return unarySyscall("p_free_array", vmCode.getIdxPairArray());
  }

  @Override
  public String visitPrint(VMPrint vmCode) {
    StringBuilder ret = new StringBuilder();
    int idxReg = convertToArmReg(BUF_REG_R0, vmCode.getIdxReg(),
        ret, VMLoadStore.LoadStore.LOAD);
    loadR0ForSyscall(ret, idxReg);
    switch (vmCode.getPrintType())
    {
      case INT:
        ret.append("p_print_int");
        break;
      case BOOL:
        ret.append("p_print_bool");
        break;
      case CHAR:
        ret.append("putchar");
        break;
      case STRING:
        ret.append("p_print_string");
        break;
      case ARRAY_PAIR:
        ret.append("p_print_reference");
        break;
    }
    ret.append('\n');
    if (vmCode.isIfLn())
      ret.append("BL p_print_ln\n");
    return ret.toString();
  }

  @Override
  public String visitRead(VMRead vmCode) {
    int regIdx = vmCode.getIdxDstReg();
    StringBuilder store = new StringBuilder();
    StringBuilder ret = new StringBuilder();
    regIdx = convertToArmReg(BUF_REG_R2, regIdx,
        store, VMLoadStore.LoadStore.STORE);
    //store to stack if is vreg
    ret.append("ADD r0,sp,#");
    ret.append(getReadBufferDisplacement());
    ret.append("\nBL p_read_");
    ret.append(vmCode.getReadType().toString());
    if (vmCode.getReadType() == VMRead.ReadType.INT)
    {
      ret.append("\nLDR R");
    }
    else
    {
      ret.append("\nLDRSB R");
    }
    ret.append(regIdx);
    ret.append(",[sp,#");
    ret.append(getReadBufferDisplacement());
    ret.append("]\n");
    ret.append(store);
    return ret.toString();
  }

  @Override
  public String visitNewPair(VMNewPair vmCode)
  {
    StringBuilder ret = new StringBuilder();
    StringBuilder store = new StringBuilder();
    ret.append("LDR r0, =8\n");
    ret.append("BL malloc\n");
    int tmpReg = convertToArmReg(BUF_REG_R1,
        vmCode.getIdxFst(), ret, VMLoadStore.LoadStore.LOAD);
    ret.append("STR R");
    ret.append(tmpReg);
    ret.append(",[r0]\n");//fst store
    tmpReg = convertToArmReg(BUF_REG_R1,
        vmCode.getIdxSnd(), ret, VMLoadStore.LoadStore.LOAD);
    ret.append("STR R");
    ret.append(tmpReg);
    ret.append(",[r0,#4]\n");//snd store
    tmpReg = convertToArmReg(BUF_REG_R2,
        vmCode.getDstIdx(), store, VMLoadStore.LoadStore.STORE);
    ret.append("MOV R");
    ret.append(tmpReg);//store into vreg
    ret.append(",R0\n");
    ret.append(store);
    return ret.toString();
  }

  @Override
  public String visitNewArray(VMNewArray vmCode) {
    int arrayLen = vmCode.getLength();
    int elemSize = vmCode.getElemSize().toInt();
    StringBuilder store = new StringBuilder();
    StringBuilder ret = new StringBuilder();
    int dstIdx = convertToArmReg(BUF_REG_R2, vmCode.getDstIdx(),
        store, VMLoadStore.LoadStore.STORE);
    ret.append("LDR r0, =");
    ret.append(arrayLen * elemSize + DWORD_SIZE);
    //DWORD SIZE is the size of the array
    ret.append("\n");
    ret.append("BL malloc\n");
    ret.append("MOV r");
    ret.append(dstIdx);
    ret.append(", r0\n");
    ret.append("LDR r0, =");
    ret.append(arrayLen);
    ret.append("\nSTR r0, [r");
    ret.append(dstIdx);
    ret.append("]\n");
    ret.append(store);
    return ret.toString();
  }

}
