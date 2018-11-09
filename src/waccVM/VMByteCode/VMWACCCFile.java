package waccVM.VMByteCode;

import waccVM.VMClass.VMClass;
import waccVM.VMClass.VMClassDeclare;

import java.util.ArrayList;

public class VMWACCCFile
{
  private VMWaccHeader header;
  private ArrayList<VMWaccFunction> funcTable;
  private ArrayList<VMWaccClass> classTable;
  private ArrayList<Integer> stringTab;
  private ArrayList<VMWaccInstr> instructions;
  private ArrayList<VMWaccFields> fields;
  private ArrayList<VMWaccVTable> vTables;

  private int curOffset = 0;

  private ArrayList<VMFuncInfo> funcInfos;
  private ArrayList<VMClass> classInfos;
  private ArrayList<String> stringTable;
  public VMWACCCFile(ArrayList<VMFuncInfo> funcInfos,
                     ArrayList<VMClass> classInfos, ArrayList<String> stringTable) {
    this.funcInfos = funcInfos;
    this.classInfos = classInfos;
    this.stringTable = stringTable;
    this.fillFileInfos();
    //fillFileInfos();
  }

  public byte[] getFileContent()
  {
    byte[] ret = new byte[curOffset];
    int off = 0;
    off = header.getBin(ret, off);
    for (VMWaccFunction func : funcTable)
    {
      off = func.getBin(ret, off);
    }
    for (VMWaccClass waccClass : classTable)
    {
      off = waccClass.getBin(ret, off);
    }
    for (int stringOff : stringTab)
    {
      off += Utils.concatIntLE(ret, off, stringOff);
    }
    for (VMWaccInstr instr : instructions)
    {
      off = instr.getBin(ret, off);
    }
    for (VMWaccFields fields : fields)
    {
      off = fields.getBin(ret, off);
    }
    for (VMWaccVTable vt : vTables)
    {
      off = vt.getBin(ret, off);
    }
    for (String str : stringTable)
    {
      off += Utils.concatString(ret, off, str);
    }
    return ret;
  }

  private void fillFileInfos() {
    header = new VMWaccHeader(funcInfos.size(), classInfos.size(), stringTable.size());
    funcTable = new ArrayList<>();
    classTable = new ArrayList<>();
    instructions = new ArrayList<>();
    fields = new ArrayList<>();
    vTables = new ArrayList<>();
    stringTab = new ArrayList<>();
    curOffset = 0;
    curOffset += VMWaccHeader.sizeof();
    for (VMFuncInfo func : funcInfos)
    {
      funcTable.add(new VMWaccFunction((short)func.getArgNum(), (short)func.getVarNum()));
      curOffset += VMWaccFunction.sizeof();
    }
    for (VMClass classDeclare : classInfos)
    {
      classTable.add(new VMWaccClass((short)classDeclare.getParentIdx(), (short)classDeclare.getSize()));
      curOffset += VMWaccClass.sizeof();
    }
    curOffset += 4 * stringTable.size();//4 is the size of string table

    for (int i = 0; i < funcTable.size(); i++)
    {
      funcTable.get(i).setInstruction_off(curOffset);
      instructions.add(new VMWaccInstr((short)funcInfos.get(i).getRegNum(),
              funcInfos.get(i).getByteCodes()));
      curOffset += instructions.get(i).sizeof();
    }

    for (int i = 0; i < classInfos.size(); i++)
    {
      VMClass classDeclare = classInfos.get(i);
      VMWaccFields waccClassFields = new VMWaccFields();
      for (int j = 0; j < classDeclare.fieldNum();j++)
      {
        waccClassFields.addFieldInfo((short) classDeclare.getFieldDispl(j),
                (byte)classDeclare.getField(j).getType().getByteSize().toInt());
      }
      fields.add(waccClassFields);
      classTable.get(i).setField_off(curOffset);
      curOffset += waccClassFields.sizeof();
    }
    for (int i = 0; i < classInfos.size(); i++)
    {
      VMClass classDeclare = classInfos.get(i);
      VMWaccVTable waccVTable = new VMWaccVTable();
      for (int j = 0; j < classDeclare.vtSize(); j++) {
        waccVTable.addVTFuncIdx(classDeclare.getvt(j).getFuncIdxMain());
      }
      vTables.add(waccVTable);
      classTable.get(i).setVtable_off(curOffset);
      curOffset += waccVTable.sizeof();
    }
    for (String str : stringTable)
    {
      stringTab.add(curOffset);
      curOffset += str.length() + 1;
    }
    header.setFile_size(curOffset);
  }

}
