package waccVM.VMClass;
import java.util.List;
import waccVM.VMProgram;

import java.util.ArrayList;
import java.util.HashSet;


public class VMClassDeclare extends VMClass
{
  private String className;

  private int classInherit;

  private VMProgram program;

  private ArrayList<VMField> fields;
  private ArrayList<VMMethod> functions;

  private ArrayList<VMVirFunc> vtable;

  private ArrayList<Integer> fieldIdxToDispl;
  private int constructor; //function index of constructor

  private int size;

  private int myIdx;

  public void setProgram(VMProgram program) {
    this.program = program;
  }


  public void setFields(ArrayList<VMField> fields) {
    this.fields = fields;
  }

  public void setClassInherit(int classInherit) {
    this.classInherit = classInherit;
    fillFieldDispl();
    addAllParentField();
  }
  public ArrayList<VMField> getFields() {
    return fields;
  }


  public ArrayList<VMVirFunc> getVtable() {
    return vtable;
  }

  public VMClassDeclare() {

  }

  public void setAtts(String className,
                      int classInherit,
                      VMProgram program,
                      int myIdx,
                      ArrayList<VMField> fields,
                      ArrayList<VMMethod> functions, int constructor) {
    this.className = className;
    this.classInherit = classInherit;
    this.program = program;
    this.myIdx = myIdx;
    this.fields = fields;
    this.functions = functions;
    this.constructor = constructor;
    fillFieldDispl();
    fillVT();
  }


  public VMClassDeclare(String className,
                        int classInherit,
                        VMProgram program,
                        int myIdx,
                        ArrayList<VMField> fields,
                        ArrayList<VMMethod> functions, int constructor) {
    this.className = className;
    this.classInherit = classInherit;
    this.program = program;
    this.myIdx = myIdx;
    this.fields = fields;
    this.functions = functions;
    this.constructor = constructor;
    fillFieldDispl();
    fillVT();
  }

  public VMField getField(int idx)
  {
    return fields.get(idx);
  }

  /*
  rule of alignment in class
  1. the displacement of each field from start must be the multiple of its size
  2. the size of the class must be multiple of the biggest size of all fields
  3. for the inheritance case, the child class must be "behind" the parent class
  reference: https://bbs.pediy.com/thread-222967.htm
  */
  private void fillFieldDispl()
  {
    fieldIdxToDispl = new ArrayList<>();
    VMClass parent = getParent();
    fieldIdxToDispl.addAll(parent.getFieldIdxToDispl());
    int pos = parent.getSize();
    for (VMField field : fields)
    {
      int fieldSize = field.getType().getByteSize().toInt();
      int displ = getAlignDispl(pos, fieldSize);
      fieldIdxToDispl.add(displ);
      pos = displ + fieldSize;
    }
    size = getAlignDispl(pos, Math.max(
            parent.getMaxFieldSize(), this.getMaxFieldSize()));
  }

  private void addAllParentField() {
    VMClass parent = getParent();
    if (parent instanceof VMClassDeclare) {
      List<VMField> parentField = ((VMClassDeclare) parent).getFields();
      for (int i = parentField.size() - 1; i >= 0; i--) {
        fields.add(0, parentField.get(i));
      }
    }
  }

  public int fieldNum()
  {
    return fields.size();
  }

  public int getSize()
  {
    return size;
  }

  public int getMaxFieldSize()
  {
    int size = VMClassNull.VT_SIZE;
    for (VMField field : fields)
    {
      int fieldSize = field.getType().getByteSize().toInt();
      if (size < fieldSize)
        size = fieldSize;
    }
    return size;
  }

  public int getFieldDispl(int fieldIdx)
  {
    return fieldIdxToDispl.get(fieldIdx);
  }

  ArrayList<Integer> getFieldIdxToDispl() {
    return fieldIdxToDispl;
  }

  private void fillVT()
  {
    vtable = new ArrayList<>();
    HashSet<Integer> overrideFuncs = new HashSet<>();
    VMClass parent = program.getClass(classInherit);
    for (int i = 0; i < parent.vtSize(); i++)
    {//traverse parent vt
      VMVirFunc virFunc = parent.getVFunction(i);
      String vFuncName = getMemberName(virFunc);
      int memberIdx = this.getMemberIdx(vFuncName);
      if (memberIdx != -1)
      {//replace the virtual table elem
        vtable.add(new VMVirFunc(myIdx, memberIdx, vFuncName,
                functions.get(memberIdx).getFunctionIdx()));
        overrideFuncs.add(memberIdx);
      }
      else
      {//don't change the elem
        vtable.add(virFunc);
      }
    }

    //for the virtual function that is not in the parent
    for (int i = 0; i < functions.size(); i++)
    {
      if (!overrideFuncs.contains(i))
      {
        vtable.add(new VMVirFunc(myIdx, i, functions.get(i).getMethodName(),
                functions.get(i).getFunctionIdx()));
      }
    }
  }

  public VMVirFunc getvt(int idx)
  {
    return vtable.get(idx);
  }

  VMVirFunc getVFunction(int idx)
  {//pre: idx is valid
    return vtable.get(idx);
  }//post: the idx of the

  public int vtSize()
  {
    return vtable.size();
  }

  private int getMemberIdx(String memberName)
  {
    for (int i = 0; i < functions.size(); i++)
    {
      if (functions.get(i).getMethodName().equals(memberName))
        return i;
    }
    return -1;
  }

  private String getMemberName(VMVirFunc func)
  {
    VMClassDeclare classDeclare = (VMClassDeclare) program.getClass(func.getClassIdx());
    return classDeclare.functions.get(func.getFuncIdx()).getMethodName();
  }

  private VMClass getParent()
  {
    return program.getClass(classInherit);
  }

  public int getParentIdx()
  {
    return classInherit;
  }

  private static int getAlignDispl(int pos, int size)
  {//pre: 2 args > 0

    return pos%size == 0 ? pos : (pos/size + 1)*size;
  }

  public String getClassName() {
    return className;
  }
}
