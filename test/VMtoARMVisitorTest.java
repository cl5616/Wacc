import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

import Information.ASTTypes.*;
import Information.OpInfo.BinOpInfo;
import Information.OpInfo.UnOpInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import waccVM.VMInstruction.*;
import waccVM.VmVisitor.VMFuncArguments;
import waccVM.VmVisitor.VMFuncVariables;
import waccVM.VmVisitor.VMtoARMVisitor;

import java.util.ArrayList;

public class VMtoARMVisitorTest
{
  private ArrayList<Type> var_argList = new ArrayList<>();
  private VMFuncVariables variables;
  private VMFuncArguments arguments;
  private VMtoARMVisitor vMtoARMVisitor;
  @Before
  public void init()
  {
    var_argList.add(new BoolType());
    var_argList.add(new CharType());
    var_argList.add(new IntType());
    var_argList.add(new ArrayType(null, new IntType(), 5));
    var_argList.add(new BoolType());
    var_argList.add(new CharType());
    var_argList.add(new CharType());
    var_argList.add(new PairType());
    variables = new VMFuncVariables(var_argList);
    arguments = new VMFuncArguments(var_argList);
    //vMtoARMVisitor = new VMtoARMVisitor(15, variables, arguments, null, "function");
  }
  /*
  11 * 4 = 44;
  20 vars = 64
  4 bufRead; 68
  4 lr; 72
  4 * 4 saved regs; 88
  args
  */

  @Test
  public void localVarArgTest()
  {
    Assert.assertEquals(variables.idxToDisplacementFromVReg(0), 0);
    Assert.assertEquals(variables.idxToDisplacementFromVReg(1), 1);
    Assert.assertEquals(variables.idxToDisplacementFromVReg(4), 2);
    Assert.assertEquals(variables.idxToDisplacementFromVReg(5), 3);
    Assert.assertEquals(variables.idxToDisplacementFromVReg(6), 4);
    //jump to 8 for alignment
    Assert.assertEquals(variables.idxToDisplacementFromVReg(2), 8);
    Assert.assertEquals(variables.idxToDisplacementFromVReg(3), 12);
    Assert.assertEquals(variables.idxToDisplacementFromVReg(7), 16);

    Assert.assertEquals(variables.sizeAligned, 20);

    Assert.assertEquals(arguments.getArgsSize(), 17);
    Assert.assertEquals(arguments.getArgsAlignSize(), 20);

  }

  @Test
  public void testBinOpTranslate()
  {
    String arm = vMtoARMVisitor.visitBinOp(new VMBinOp(2,3,0, BinOpInfo.BinOp.ADD));
    Assert.assertTrue(arm.equals("ADDS R4,R6,R7\n" +
            "BLVS p_throw_overflow_error\n"));
    arm = vMtoARMVisitor.visitBinOp(new VMBinOp(13,14,8,  BinOpInfo.BinOp.ADD));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#36]\n" +
            "LDR R1,[SP,#40]\n" +
            "ADDS R2,R0,R1\n" +
            "BLVS p_throw_overflow_error\n" +
            "STR R2,[SP,#16]\n"));
   }

  @Test
  public void testUnOpTranslate()
  {
    String arm = vMtoARMVisitor.visitUnOp(new VMUnOp(0, 1, UnOpInfo.UnOp.NEG));
    Assert.assertTrue(arm.equals("RSBS R5,R4,#0\n" +
            "BLVS p_throw_overflow_error\n"));

    arm = vMtoARMVisitor.visitUnOp(new VMUnOp(4, 5, UnOpInfo.UnOp.NEG));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#0]\n" +
            "RSBS R2,R0,#0\n" +
            "BLVS p_throw_overflow_error\n" +
            "STR R2,[SP,#4]\n"));

    arm = vMtoARMVisitor.visitUnOp(new VMUnOp(4, 3, UnOpInfo.UnOp.NEG));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#0]\n" +
            "RSBS R7,R0,#0\n" +
            "BLVS p_throw_overflow_error\n"
            ));

    arm = vMtoARMVisitor.visitUnOp(new VMUnOp(0, 5, UnOpInfo.UnOp.CHR));
    Assert.assertTrue(arm.equals("MOV R2,R4\n" +
            "STR R2,[SP,#4]\n"));

    arm = vMtoARMVisitor.visitUnOp(new VMUnOp(4,5, UnOpInfo.UnOp.ORD));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#0]\n" +
            "MOV R2,R0\n" +
            "STR R2,[SP,#4]\n"));

    arm = vMtoARMVisitor.visitUnOp(new VMUnOp(5,5, UnOpInfo.UnOp.ORD));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#4]\n" +
            "MOV R2,R0\n" +
            "STR R2,[SP,#4]\n"));//no optimization...

    arm = vMtoARMVisitor.visitUnOp(new VMUnOp(0,0, UnOpInfo.UnOp.CHR));
    Assert.assertTrue(arm.equals(""));
  }

  @Test
  public void testArrayAccess()
  {
    String arm;
    arm = vMtoARMVisitor.visitArrayAccess(new VMArrayAccess(0,0,0,
            VMLoadStore.LoadStore.LOAD, ElemSize.DWORD));
    Assert.assertTrue(arm.equals("MOV r0, r4\n" +
            "MOV r1, r4\n" +
            "BL p_check_array_bounds\n" +
            "ADD r0,r4,#4\n" +
            "ADD r0,r0,r4, LSL #2\n" +
            "LDR r4, [r0]\n"));

    arm = vMtoARMVisitor.visitArrayAccess(new VMArrayAccess(4,5,6,
            VMLoadStore.LoadStore.LOAD, ElemSize.DWORD));
    Assert.assertTrue(arm.equals("LDR R1,[SP,#4]\n" +
            "LDR R0,[SP,#8]\n" +
            "MOV r3,r0\n" +
            "BL p_check_array_bounds\n" +
            "LDR R1,[SP,#4]\n" +
            "ADD r0,r1,#4\n" +
            "ADD r0,r0,r3, LSL #2\n" +
            "LDR r2, [r0]\n" +
            "STR R2,[SP,#0]\n"));

    arm = vMtoARMVisitor.visitArrayAccess(new VMArrayAccess(4,5,6,
            VMLoadStore.LoadStore.STORE, ElemSize.BYTE));
    Assert.assertTrue(arm.equals(
            "LDR R1,[SP,#4]\n" +
            "LDR R0,[SP,#8]\n" +
            "LDR R2,[SP,#0]\n" +
            "MOV r3,r0\n" +
            "BL p_check_array_bounds\n" +
            "LDR R1,[SP,#4]\n" +
            "ADD r0,r1,#4\n" +
            "ADD r0,r0,r3\n" +
            "STRB r2, [r0]\n"));
  }

  @Test
  public void testPairAccess()
  {
    String arm;
    arm = vMtoARMVisitor.visitPairAccess(new VMPairAccess(0,
            VMPairAccess.FstOrSnd.FST, 0, VMLoadStore.LoadStore.LOAD));
    Assert.assertTrue(arm.equals("MOV r0, r4\n" +
            "BL p_check_null_pointer\n" +
            "LDR R4,[R4,#0]\n"));

    arm = vMtoARMVisitor.visitPairAccess(new VMPairAccess(4,
            VMPairAccess.FstOrSnd.SND, 5, VMLoadStore.LoadStore.STORE));
    Assert.assertTrue(arm.equals("LDR R1,[SP,#0]\n" +
            "MOV r0, r1\n" +
            "BL p_check_null_pointer\n" +
            "LDR R2,[SP,#4]\n" +
            "STR R2,[R1,#4]\n"));

  }

  @Test
  public void testNewArray()
  {
    String arm;
    arm = vMtoARMVisitor.visitNewArray(new VMNewArray(10, 0,
            ElemSize.DWORD));
    Assert.assertTrue(arm.equals(
            "LDR r0, =44\n" +
            "BL malloc\n" +
            "MOV r4, r0\n" +
            "LDR r0, =10\n" +
            "STR r0, [r4]\n"));
    arm = vMtoARMVisitor.visitNewArray(new VMNewArray(0, 4,
            ElemSize.BYTE));
    Assert.assertTrue(arm.equals(
            "LDR r0, =4\n" +
            "BL malloc\n" +
            "MOV r2, r0\n" +
            "LDR r0, =0\n" +
            "STR r0, [r2]\n" +
            "STR R2,[SP,#0]\n"));
  }

  @Test
  public void testNewPair()
  {
    String arm;
    arm = vMtoARMVisitor.visitNewPair(new VMNewPair(3,4,1));
    Assert.assertTrue(arm.equals(
            "LDR r0, =8\n" +
            "BL malloc\n" +
            "STR R7,[r0]\n" +
            "LDR R1,[SP,#0]\n" +
            "STR R1,[r0,#4]\n" +
            "MOV R5,R0\n"));
  }
/*
  @Test
  public void testAssign()
  {
    String arm;
    arm = vMtoARMVisitor.visitAssign(new VMAssign(4, VMArgType.VAR, 5));
    Assert.assertTrue(arm.equals(
                    "LDR R0,[SP,#4]\n" +
                    "MOV R2,R0\n" +
                    "STR R2,[SP,#0]\n"));
    arm = vMtoARMVisitor.visitAssign(new VMAssign(4, VMArgType.IMM, 5));
    Assert.assertTrue(arm.equals(
                    "LDR R2,=5\n" +
                    "STR R2,[SP,#0]\n"));
    arm = vMtoARMVisitor.visitAssign(new VMAssign(0,VMArgType.VAR, 0));
    Assert.assertTrue(arm.equals("MOV R4,R4\n"));
  }
*/
  @Test
  public void testLoadStore()
  {
    String arm;
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,0,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("LDRSB R4,[sp,#44]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,1,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("LDRSB R4,[sp,#45]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,2,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("LDR R4,[sp,#52]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,3,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("LDR R4,[sp,#56]\n"));

    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,0,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("LDRSB R4,[sp,#91]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,1,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("LDRSB R4,[sp,#92]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,2,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("LDR R4,[sp,#93]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,3,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("LDR R4,[sp,#97]\n"));

    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,0,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("LDRSB R2,[sp,#44]\nSTR R2,[SP,#0]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,1,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("LDRSB R2,[sp,#45]\nSTR R2,[SP,#0]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,2,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("LDR R2,[sp,#52]\nSTR R2,[SP,#0]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,3,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("LDR R2,[sp,#56]\nSTR R2,[SP,#0]\n"));

    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,0,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("LDRSB R2,[sp,#91]\nSTR R2,[SP,#0]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,1,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("LDRSB R2,[sp,#92]\nSTR R2,[SP,#0]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,2,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("LDR R2,[sp,#93]\nSTR R2,[SP,#0]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,3,
            VMLoadStore.LoadStore.LOAD, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("LDR R2,[sp,#97]\nSTR R2,[SP,#0]\n"));


    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,0,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("STRB R4,[sp,#44]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,1,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("STRB R4,[sp,#45]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,2,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("STR R4,[sp,#52]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,3,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("STR R4,[sp,#56]\n"));

    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,0,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("STRB R4,[sp,#91]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,1,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("STRB R4,[sp,#92]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,2,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("STR R4,[sp,#93]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(0,3,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("STR R4,[sp,#97]\n"));

    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,0,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#0]\nSTRB R0,[sp,#44]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,1,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#0]\nSTRB R0,[sp,#45]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,2,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#0]\nSTR R0,[sp,#52]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,3,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.VAR));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#0]\nSTR R0,[sp,#56]\n"));

    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,0,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#0]\nSTRB R0,[sp,#91]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,1,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#0]\nSTRB R0,[sp,#92]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,2,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#0]\nSTR R0,[sp,#93]\n"));
    arm = vMtoARMVisitor.visitLoadStore(new VMLoadStore(4,3,
            VMLoadStore.LoadStore.STORE, VMLoadStore.VarOrArg.ARG));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#0]\nSTR R0,[sp,#97]\n"));

  }

  @Test
  public void testRead()
  {
    String arm;
    arm = vMtoARMVisitor.visitRead(new VMRead(0, VMRead.ReadType.INT));
    Assert.assertTrue(arm.equals("ADD r0,sp,#64\n" +
            "BL p_read_int\n" +
            "LDR R4,[sp,#64]\n"));

    arm = vMtoARMVisitor.visitRead(new VMRead(4, VMRead.ReadType.CHAR));
    Assert.assertTrue(arm.equals("ADD r0,sp,#64\n" +
            "BL p_read_char\n" +
            "LDRSB R2,[sp,#64]\n" +
            "STR R2,[SP,#0]\n"));
  }

  @Test
  public void testRetn()
  {
    String arm;
    arm = vMtoARMVisitor.visitRetn(new VMRetn(5));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#4]\n"));

    arm = vMtoARMVisitor.visitRetn(new VMRetn(2));
    Assert.assertTrue(arm.equals("MOV R0,R6\n"));

  }

  @Test
  public void testPrint()
  {
    String arm;
    arm = vMtoARMVisitor.visitPrint(new VMPrint(VMPrint.PrintType.INT, 4, false));
    Assert.assertTrue(arm.equals("LDR R0,[SP,#0]\n" +
            "BL p_print_int\n"));

    arm = vMtoARMVisitor.visitPrint(new VMPrint(VMPrint.PrintType.CHAR, 0,false));
    Assert.assertTrue(arm.equals("MOV R0,R4\n" +
            "BL putchar\n"));

  }
}
