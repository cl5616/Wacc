package waccVM;

import waccVM.VMByteCode.VMFuncInfo;
import waccVM.VMByteCode.VMWACCCFile;
import waccVM.VMClass.VMClass;
import waccVM.VMClass.VMClassDeclare;
import waccVM.VMClass.VMClassNull;
import waccVM.VMClass.VMVirFunc;
import waccVM.VmVisitor.VMFuncVariables;
import waccVM.VmVisitor.VMtoARMVisitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VMProgram {

  private ArrayList<String> stringTable = new ArrayList<>();
  private final ArrayList<VMFunction> functions = new ArrayList<>();
  private final ArrayList<VMClass> classes = new ArrayList<>();

  public void addClasses(VMClass newClass) {
    classes.add(newClass);
  }

  public void addFunction(VMFunction function) {
    functions.add(function);
  }

  public void addString(String string) {
    stringTable.add(string);
  }

  public VMClass getClass(int idx)
  {//pre: idx valid
    return classes.get(idx);
  }

  public int indexOfString(String string) {
    return stringTable.indexOf(string);
  }

  public static String unescapeWacc(String str)
  {
    String ret = str.substring(1, str.length() - 1);//remove "
    ret = ret.replaceAll("\\\\0", "\0");
    ret = ret.replaceAll("\\\\b", "\b");
    ret = ret.replaceAll("\\\\t", "\t");
    ret = ret.replaceAll("\\\\n", "\n");
    ret = ret.replaceAll("\\\\f", "\f");
    ret = ret.replaceAll("\\\\r", "\r");
    ret = ret.replaceAll("\\\\\"", "\"");
    ret = ret.replaceAll("\\\\\'", "\'");
    ret = ret.replaceAll("\\\\\\\\", "\\\\");
    return ret;
  }


  public int stringTableSize() {
    return stringTable.size();
  }

  public String toArmProgram() {
    StringBuilder ret = new StringBuilder();
    ret.append(".data\n");
    ret.append(VMtoARMVisitor.BUILTIN_DATA);

    ret.append("\n");
    //vtable
    for (VMClass classDeclare : classes)
    {
      if (classDeclare instanceof VMClassNull) {
        continue;
      }
      VMtoARMVisitor.getClassVTName((VMClassDeclare) classDeclare, ret);
      ret.append(":\n");
      for (int i = 0; i < classDeclare.vtSize(); i++)
      {
        ret.append(".word ");
        VMVirFunc virFunc = ((VMClassDeclare)classDeclare).getvt(i);
        ret.append(virFunc.getFuncName());
        ret.append("\n");
      }

    }

    for (int i = 0; i < stringTable.size(); i++)
    {
      String escaped = unescapeWacc(stringTable.get(i));
      ret.append("string_");
      ret.append(i);
      ret.append(":\n");
      ret.append("\t.word ");
      ret.append(escaped.length());
      ret.append("\n");
      ret.append("\t.ascii\t");
      ret.append(stringTable.get(i));
      ret.append('\n');
    }

    ret.append(".text\n.global main\n");
    for (VMFunction function : functions) {
      ret.append(function.getFunctionName());
      ret.append(":\n");
      ret.append(function.toArmAsm());
    }
    ret.append(VMtoARMVisitor.BUILTIN_FUNC);
    return ret.toString();

  }

  public void moveMainOn1()
  {
    if (functions.get(0).getFunctionName().equals("main"))
      return;
    for (int i = 0; i < functions.size(); i++)
    {
      if (functions.get(i).getFunctionName().equals("main"))
      {
        VMFunction main = functions.get(i);
        VMFunction zero = functions.get(0);
        functions.set(0, main);
        functions.set(i, zero);
        break;
      }
    }
  }

  public byte[] toVMByteCodes()
  {
    ArrayList<VMFuncInfo> funcInfos = new ArrayList<>();
    ArrayList<String> unescapeStrings = new ArrayList<>();
    for (String str : stringTable)
    {
      unescapeStrings.add(unescapeWacc(str));
    }
    for (VMFunction func : functions)
    {
      int argSize = func.getArgNum();
      int varSize = func.getVarNum();
      int regSize = func.getRegNum();
      ArrayList<Byte> codes = func.toByteCode();
      funcInfos.add(new VMFuncInfo(argSize, varSize, regSize, codes));
    }
    VMWACCCFile file = new VMWACCCFile(funcInfos, classes, unescapeStrings);
    return file.getFileContent();
  }
}
