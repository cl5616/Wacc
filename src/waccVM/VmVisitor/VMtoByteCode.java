package waccVM.VmVisitor;

import waccVM.VMInstruction.*;
import waccVM.VmVisitor.VMEncoders.VMEncoder;

import static waccVM.VMByteCode.VMByteCodeMacros.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

public class VMtoByteCode implements VMVisitor<ArrayList<Byte>>{
  private VMEncoder encoder;
  private TreeMap<String, Long> labelToDispl;
  private final ArrayList<Byte> ret = new ArrayList<>();

  public static void u2toLittleEndian(ArrayList<Byte> ret, int u2)
  {
    if (u2 > 0xffff || u2 < 0)
      throw new RuntimeException("u2 is too big");
    ret.add((byte)(u2 & 0xff));
    ret.add((byte)(u2 >> 8));
  }

  public static void u4toLittleEndian(ArrayList<Byte> ret, long u4)
  {

    if (u4 > 0xffffffffL)
      throw new RuntimeException("u4 is too big");
    ret.add((byte)(u4 & 0xffL));
    ret.add((byte)(u4 >> 8 & 0xffL));
    ret.add((byte)(u4 >> 16 & 0xffL));
    ret.add((byte)(u4 >> 24 & 0xffL));
  }

  public ArrayList<Byte> getRet(){return ret;}


  public VMtoByteCode(VMEncoder encoder, TreeMap<String, Long> labelToDispl)
  {
    this.encoder = encoder;

    this.labelToDispl = labelToDispl;
  }

  @Override
  public ArrayList<Byte> visitArrayAccess(VMArrayAccess vmCode) {
    switch (vmCode.getLoad_store())
    {
      case LOAD:
        ret.add(ARRAY_ACCESS_LOAD);
        break;
      case STORE:
        ret.add(ARRAY_ACCESS_STORE);
        break;
    }
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getSrcDstIdx()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getArrayIdx()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getIndex_of_elem()));
    ret.add(encoder.elemSizeEnc(vmCode.getElemSize()));


    return ret;
  }

  @Override
  public ArrayList<Byte> visitLabel(VMLabel vmCode) {
    return ret;
  }

  @Override
  public ArrayList<Byte> visitUncondJmp(VMUncondJmp vmCode) {

    ret.add(UNCOND_JMP);
    u4toLittleEndian(ret, encoder.jmpDisplEnc(labelToDispl.get(vmCode.getDst())));

    

    return ret;
  }

  @Override
  public ArrayList<Byte> visitAssign(VMAssign vmCode) {

    ret.add(ASSIGN);
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getLhsIdx()));
    u4toLittleEndian(ret, encoder.immEnc(vmCode.getRhs()));

    

    return ret;
  }

  @Override
  public ArrayList<Byte> visitCondJmp(VMCondJmp vmCode) {

    ret.add(COND_JMP);
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getIfJmpIdx()));
    u4toLittleEndian(ret, encoder.jmpDisplEnc(labelToDispl.get(vmCode.getDst())));
    return ret;
  }

  @Override
  public ArrayList<Byte> visitLoadStore(VMLoadStore vmCode) {

    switch (vmCode.getVar_arg())
    {
      case VAR:
        switch (vmCode.getLoad_store())
        {
          case LOAD:
          {
            ret.add(LOAD_VAR);
          }
          break;
          case STORE:
          {
            ret.add(STORE_VAR);
          }
          break;
        }
        u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getRegIdx()));
        u2toLittleEndian(ret, encoder.varIdxEnc(vmCode.getStackIdx()));
        break;
      case ARG:
        switch (vmCode.getLoad_store())
        {
          case LOAD:
          {
            ret.add(LOAD_ARG);
          }
          break;
          case STORE:
          {
            ret.add(STORE_ARG);
          }
          break;
        }
        u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getRegIdx()));
        u2toLittleEndian(ret, encoder.argIdxEnc(vmCode.getStackIdx()));
        break;
    }
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitLoadString(VMLoadString vmCode) {
    ret.add(LOAD_STRING);
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getDstRegIdx()));
    u4toLittleEndian(ret, encoder.stringIdxEnc(vmCode.getStringIdx()));
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitUnOp(VMUnOp vmCode) {
    switch (vmCode.getUnOp())
    {
      case NOT:
        ret.add(UNOP_NOT);
        break;
      case NEG:
        ret.add(UNOP_NEG);
        break;
      case LEN:
        ret.add(UNOP_LEN);
        break;
      case ORD:
        ret.add(UNOP_ORD);
        break;
      case CHR:
        ret.add(UNOP_CHR);
        break;
      case PLUS:
        ret.add(UNOP_PLUS);
        break;
    }
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getDstIdx()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getArg()));
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitBinOp(VMBinOp vmCode) {
    switch (vmCode.getBinOp())
    {
      case ADD:
        ret.add(BINOP_ADD);
        break;
      case SUB:
        ret.add(BINOP_SUB);
        break;
      case MUL:
        ret.add(BINOP_MUL);
        break;
      case DIV:
        ret.add(BINOP_DIV);
        break;
      case MOD:
        ret.add(BINOP_MOD);
        break;
      case EQ:
        ret.add(BINOP_EQ);
        break;
      case GT:
        ret.add(BINOP_GT);
        break;
      case LT:
        ret.add(BINOP_LT);
        break;
      case GTE:
        ret.add(BINOP_GTE);
        break;
      case LTE:
        ret.add(BINOP_LTE);
        break;
      case NEQ:
        ret.add(BINOP_NEQ);
        break;
      case AND:
        ret.add(BINOP_AND);
        break;
      case OR:
        ret.add(BINOP_OR);
        break;
    }
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getDstIdx()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getLhs()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getRhs()));
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitPairAccess(VMPairAccess vmCode) {
    switch (vmCode.getLoad_store())
    {
      case LOAD:
      {
        switch (vmCode.getFstOrSnd())
        {
          case FST:
            ret.add(PAIR_ACCESS_FST_LOAD);
            break;
          case SND:
            ret.add(PAIR_ACCESS_SND_LOAD);
            break;
        }
      }
        break;
      case STORE:
      {
        switch (vmCode.getFstOrSnd())
        {
          case FST:
            ret.add(PAIR_ACCESS_FST_STORE);
            break;
          case SND:
            ret.add(PAIR_ACCESS_SND_STORE);
            break;
        }
      }
        break;
    }
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getPairIdx()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getDst_srcIdx()));
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitCallDirect(VMCallDirect vmCode) {
    LinkedList<Integer> args = vmCode.getArgs();
    if (args.size() > 0xff)
    {
      throw new RuntimeException("number of argument cannot be higher than 255");
    }
    ret.add(CALL_DIRECT);
    ret.add(encoder.numberOfArgEncode((byte)args.size()));
    u4toLittleEndian(ret, encoder.functionIdxEnc(vmCode.getFuncIdx()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getRetIdx()));
    for (Integer arg : args)
    {
      u2toLittleEndian(ret, encoder.regIdxEnc(arg));
    }
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitCallVirtual(VMCallVirtual vmCode) {
    LinkedList<Integer> args = vmCode.getArgs();
    if (args.size() > 0xff)
    {
      throw new RuntimeException("number of argument cannot be higher than 255");
    }
    ret.add(CALL_VIRTUAL);
    ret.add(encoder.numberOfArgEncode((byte)args.size()));
    u2toLittleEndian(ret, encoder.vtableIdxEnc(vmCode.getVtIdx()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getRetIdx()));
    for (Integer arg : args)
    {
      u2toLittleEndian(ret, encoder.regIdxEnc(arg));
    }
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitClassAccess(VMClassAccess vmCode) {
    switch (vmCode.getLoad_store())
    {
      case LOAD:
        ret.add(CLASS_ACCESS_LOAD);
        break;
      case STORE:
        ret.add(CLASS_ACCESS_STORE);
        break;
    }
    u2toLittleEndian(ret, encoder.classIdxEnc(vmCode.getClassIdx()));
    u2toLittleEndian(ret, encoder.fieldIdx(vmCode.getFieldReferenceIdx()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getSrc_dstIdx()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getObject()));
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitNewClass(VMNewClass vmCode) {
    ret.add(NEW_CLASS);
    u2toLittleEndian(ret, encoder.classIdxEnc(vmCode.getClassIdx()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getObjectReg()));
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitRetn(VMRetn vmCode) {
    ret.add(RETN);
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getIdxRetReg()));
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitExit(VMExit vmCode) {
    ret.add(EXIT);
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getIdxExitCode()));
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitFree(VMFree vmCode) {
    ret.add(FREE);
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getIdxPairArray()));
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitPrint(VMPrint vmCode) {
    if (vmCode.isIfLn())//ln
    {
      switch (vmCode.getPrintType())
      {
        case INT:
          ret.add(PRINTLN_INT);
          break;
        case BOOL:
          ret.add(PRINTLN_BOOL);
          break;
        case CHAR:
          ret.add(PRINTLN_CHAR);
          break;
        case STRING:
          ret.add(PRINTLN_STRING);
          break;
        case ARRAY_PAIR:
          ret.add(PRINTLN_ARRAY_PAIR);
          break;
      }
    }
    else//not ln
    {
      switch (vmCode.getPrintType())
      {
        case INT:
          ret.add(PRINT_INT);
          break;
        case BOOL:
          ret.add(PRINT_BOOL);
          break;
        case CHAR:
          ret.add(PRINT_CHAR);
          break;
        case STRING:
          ret.add(PRINT_STRING);
          break;
        case ARRAY_PAIR:
          ret.add(PRINT_ARRAY_PAIR);
          break;
      }
    }
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getIdxReg()));
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitRead(VMRead vmCode) {
    switch (vmCode.getReadType())
    {
      case INT:
        ret.add(READ_INT);
        break;
      case CHAR:
        ret.add(READ_CHAR);
        break;
    }
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getIdxDstReg()));
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitNewPair(VMNewPair vmCode) {
    ret.add(NEW_PAIR);
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getDstIdx()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getIdxFst()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getIdxSnd()));
    
    return ret;
  }

  @Override
  public ArrayList<Byte> visitNewArray(VMNewArray vmCode) {
    ret.add(NEW_ARRAY);
    ret.add(encoder.elemSizeEnc(vmCode.getElemSize()));
    u2toLittleEndian(ret, encoder.regIdxEnc(vmCode.getDstIdx()));
    u4toLittleEndian(ret, encoder.lengthEnc(vmCode.getLength()));
    
    return ret;
  }
}
