import CodeGenArgs.*;
import Information.*;
import Information.ASTTypes.*;
import Information.OpInfo.*;
import Information.OpInfo.UnOpInfo.UnOp;
import antlr.BasicParser.*;
import antlr.BasicParserBaseVisitor;

import java.lang.reflect.Array;
import java.util.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import waccVM.VMClass.VMClass;
import waccVM.VMClass.VMClassDeclare;
import waccVM.VMClass.VMClassNull;
import waccVM.VMClass.VMField;
import waccVM.VMClass.VMMethod;
import waccVM.VMClass.VMVirFunc;
import waccVM.VMFuncDeclares;
import waccVM.VMFunction;
import waccVM.VMInstruction.*;
import waccVM.VMInstruction.VMLoadStore.*;
import waccVM.VMInstruction.VMPairAccess.FstOrSnd;
import waccVM.VMInstruction.VMPrint.PrintType;
import waccVM.VMInstruction.VMRead.ReadType;
import waccVM.VMProgram;
import waccVM.VmVisitor.VMFuncArguments;


public class CodeGenWaccVisitor extends BasicParserBaseVisitor<Information> {
  private final VMProgram vmProgram = new VMProgram();

  private final Stack<RegisterList> registerListParam = new Stack<>();
  //used to pass the register list as parameters when do
  private BinOpRegister binOpParam = null;
  private UnOpRegister unOpRegister = null;
  private LoadStore operation = null;
  private AssignRegister assignRegister = null;

  private SymbolTable<Integer> regSt = new SymbolTable<Integer>();
  private SymbolTable<Type> typeSt = new SymbolTable<Type>();

  private VMFunction currFun = null;
  private SymbolTable<Integer> currFunVars = new SymbolTable<Integer>();
  private List<String> currFunArgs = new ArrayList<>();
  private ArrayList<Type> currFunArgsT = new ArrayList<>();
  private ArrayList<Type> currFunVarsT = new ArrayList<>();

  private int label_num = 0;
  private int var_num = 0;
  private List<String> funcs = new ArrayList<>();
  private Type declaredType = null;
  private Map<String, ArrayList<Type>> declaredFuncs = new HashMap<>();
  private SemanticVisitor typeChecker = new SemanticVisitor(typeSt);
  private boolean isVisitingUnary = false;
  private String intLiter;

  private ArrayList<VMClass> declaredClass = new ArrayList<>();
  private ArrayList<String> declaredClassNames = new ArrayList<>();
  private int classIdx = 1;
  private String currClassName = "";
  private VMClassDeclare currClass = null;

  public CodeGenWaccVisitor(SemanticVisitor semanticVisitor) {
    typeChecker.setClassTable(semanticVisitor.getClassTable());
    registerListParam.push(new RegisterList());
  }

  public VMProgram getVmProgram(){
    return vmProgram;
  }

  private int getArgIdx(String name) {
    int idx = -1;
    for (int i = 0; i < currFunArgs.size(); i++) {
      if (currFunArgs.get(i).equals(name)) {
        idx = i;
      }
    }

    return idx;
  }


  private void enterNewScope() {
    regSt = regSt.generateNew();
    typeSt = typeSt.generateNew();
    typeChecker.updateSt(typeSt);
    currFunVars = currFunVars.generateNew();
  }

  private void exitScope() {
    currFunVars = currFunVars.restore();
    regSt = regSt.restore();
    typeSt = typeSt.restore();
    typeChecker.updateSt(typeSt);
  }

  private void exitFunc() {
    currFunArgs = new ArrayList<>();
    //currFunVars = new SymbolTable<Integer>();
    currFunArgsT = new ArrayList<>();
    currFunVarsT = new ArrayList<>();
    var_num = 0;
  }


  @Override
  public Information visitExpr_this(@NotNull Expr_thisContext ctx) {
    RegisterList registerList = registerListParam.peek();

    VMLoadStore loadThis = new VMLoadStore(registerList.getFstReg(), 0, LoadStore.LOAD, VarOrArg.ARG);

    return new VmCodesInfo(loadThis);
  }

  @Override
  public Information visitAssignR_CALL_member_func(@NotNull AssignR_CALL_member_funcContext ctx) {

    RegisterList registerList = registerListParam.peek();
    RegisterList fstRegRemoved = registerList.removeFstReg();
    RegisterList paramRegisters = fstRegRemoved.removeFstReg();
    int fstFreeReg = fstRegRemoved.getFstReg();

    List<TerminalNode> idents = ctx.field_ident().IDENT();

    int size = idents.size();
    VmCodesInfo ret = new VmCodesInfo();


    //calculate all the args and load them on the regs.
    List<ExprContext> args = ctx.arg_list() == null ? new ArrayList<>() : ctx.arg_list().expr();
    VmCodesInfo transArgs = new VmCodesInfo();
    ArrayList<Integer> argRegs = new ArrayList<>();
    for (ExprContext arg : args) {

      registerListParam.push(paramRegisters);
      assignRegister = new AssignRegister(paramRegisters.getFstReg());
      VmCodesInfo transExpr = (VmCodesInfo) arg.accept(this);
      registerListParam.pop();

      transArgs.insertBackInstruction(transExpr);
      argRegs.add(paramRegisters.getFstReg());

      paramRegisters = paramRegisters.removeFstReg();
    }

    VMClassDeclare temp = currClass;
    VMLoadStore loadObj;
    //load objects on the register
    if (ctx.field_ident().THIS() != null) {
      loadObj = new VMLoadStore(fstFreeReg, 0, LoadStore.LOAD, VarOrArg.ARG);
    } else {
      VarOrArg varOrArg;
      if (currFunVars.contain(idents.get(0).getText())) {
        varOrArg = VarOrArg.VAR;
      } else {
        varOrArg = VarOrArg.ARG;
      }
      loadObj = new VMLoadStore(fstFreeReg, currFunVars.lookForAll(idents.get(0).getText()), LoadStore.LOAD, varOrArg);
      currClass = getNextClass((ClassType) typeSt.lookForAll(idents.get(0).getText()));
    }
    ret.insertBackInstruction(new VmCodesInfo(loadObj));

    if (size > 1) {
      for (int i = 1; i < size - 1; i++) {
        String name = idents.get(i).getText();
        VMClassAccess loadField = new VMClassAccess(fstFreeReg, fstFreeReg, getFieldIdx(name),
            declaredClassNames.indexOf(currClass.getClassName()) , LoadStore.LOAD);
        ret.insertBackInstruction(new VmCodesInfo(loadField));
        currClass = getNextClass((ClassType) typeSt.lookForAll(name));
      }
    }

    String funcName = idents.get(size - 1).getText();
    int funcIdx = funcs.indexOf("m_" + currClass.getClassName() + "_" + funcName);


    VMCallVirtual callVir = new VMCallVirtual(registerList.getFstReg(),
        declaredClassNames.indexOf("m_" + currClass.getClassName()), getVirIdx( currClass.getClassName()
                                          + "_" + idents.get(size - 1).getText()));

    //add args
    callVir.addArg(fstFreeReg);

    for (Integer arg : argRegs) {
      callVir.addArg(arg);
    }
    currClass = temp;
    ret.insertBackInstruction(transArgs);
    ret.insertBackInstruction(new VmCodesInfo(callVir));
    return ret;
    //VMCallDirect funcCall = new VMCallDirect(, registerList.getFstReg());
  }

  private VMClassDeclare getNextClass(ClassType w) {
    String name = w.getClassName();

    for (VMClass classDeclare : declaredClass) {
      if (classDeclare instanceof VMClassNull) {
        continue;
      }
      if (((VMClassDeclare) classDeclare).getClassName().equals(name)) {
        return (VMClassDeclare) classDeclare;
      }
    }

    return null;
  }

  private int getVirIdx(String name) {
    ArrayList<VMVirFunc> vtable = currClass.getVtable();
    int idx = -1;
    for (VMVirFunc func : vtable) {
      idx++;
      if (func.getFuncName().equals(name)) {
        return idx;
      }
    }
    return idx;
  }

  private int getFieldIdx(String name) {
    ArrayList<VMField> fields = currClass.getFields();

    int idx = -1;

    for (VMField field : fields) {
      idx++;
      if (field.getName().equals(name)) {
        return idx;
      }
    }

    return -1;
  }

  @Override
  public Information visitExpr_field(@NotNull Expr_fieldContext ctx) {
    this.operation = LoadStore.LOAD;
    assignRegister = new AssignRegister(registerListParam.peek().getFstReg());
    return ctx.field_ident().accept(this);
  }

  @Override
  public Information visitClass_destr(@NotNull Class_destrContext ctx) {

    VMMethod destr;

    String funcName = "m_" + currClassName + "_d_" + currClassName;

    this.funcs.add(funcName);

    VMFunction func = new VMFunction(funcName,
        new ArrayList<>(), new ArrayList<>(),
        new VMFuncDeclares(), new ArrayList<>());

    currFun = func;

    currFunArgsT.add(typeChecker.getClassTable().get(ctx.IDENT().getText()));
    currFunArgs.add("this");

    this.declaredFuncs.put(funcName, currFunArgsT);

    VmCodesInfo funcBody = (VmCodesInfo) ctx.stat().accept(this);

    VMFunction funcToAdd = new VMFunction(funcName, currFunArgsT,
        currFunVarsT, buildFuncDeclares(), declaredClass);//todo add ArrayList<VMClass>

    funcToAdd.addInstructions(funcBody);

    vmProgram.addFunction(funcToAdd);

    destr = new VMMethod(funcs.size() - 1, funcName);

    return destr;
  }

  @Override
  public Information visitWaccT_class_type(@NotNull WaccT_class_typeContext ctx) {
    return typeChecker.getClassTable().get(ctx.IDENT().getText());
  }


  @Override
  public Information visitStat_delete(@NotNull Stat_deleteContext ctx) {

    VMClassDeclare temp = currClass;
    RegisterList registerList = registerListParam.peek();
    RegisterList fstRemoved = registerList.removeFstReg();
    int fstReg = registerList.getFstReg();

    VMLoadStore load;
    String varName = ctx.IDENT().getText();

    if (currFunVars.contain(varName)) {
      load = new VMLoadStore(fstRemoved.getFstReg(), currFunVars.lookForAll(varName), LoadStore.LOAD, VarOrArg.VAR);
    } else {
      load = new VMLoadStore(fstRemoved.getFstReg(), currFunArgs.indexOf(varName), LoadStore.LOAD, VarOrArg.ARG);
    }

    VmCodesInfo ret = new VmCodesInfo(load);

    currClass = getNextClass((ClassType) typeSt.lookForAll(varName));
    int virIdx = getVirIdx("m_" + currClass.getClassName() + "_d_" + currClass.getClassName());
    VMCallVirtual callDestr = new VMCallVirtual(fstReg, declaredClass.indexOf(getNextClass((ClassType) typeSt.lookForAll(varName))),
                                virIdx);
    callDestr.addArg(fstRemoved.getFstReg());

    VMFree freeVar = new VMFree(fstRemoved.getFstReg());
    if (virIdx > -1) {
      ret.insertBackInstruction(new VmCodesInfo(callDestr));
    }
    ret.insertBackInstruction(new VmCodesInfo(freeVar));
    currClass = temp;


    return ret;
  }


  @Override
  public Information visitEncaps_PRIVATE(@NotNull Encaps_PRIVATEContext ctx) {
    return null;
  }


  @Override
  public Information visitClass_body_destr(@NotNull Class_body_destrContext ctx) {
    return ctx.class_destr().accept(this);
  }


  @Override
  public Information visitWacc_class(@NotNull Wacc_classContext ctx) {



    VMClassDeclare classDeclare = new VMClassDeclare();

    this.vmProgram.addClasses(classDeclare);

    currClass = classDeclare;
    currClassName = ctx.IDENT(0).getText();

    declaredClass.add(classDeclare);
    declaredClassNames.add(ctx.IDENT(0).getText());

        /*
        public VMClassDeclare(String className,
                        int classInherit,
                        VMProgram program,
                        int myIdx,
                        ArrayList<VMField> fields,
                        ArrayList<VMMethod> functions, int constructor)
         */

    List<TerminalNode> className = ctx.IDENT();

    int classInherit = 0;

    String name = ctx.IDENT(0).getText();

    if (className.size() > 1) {
      classInherit = declaredClassNames.indexOf(className.get(1).getText());
    }

    int currIdx = classIdx++;

    List<Class_fieldContext> fields = ctx.class_field() == null ? new ArrayList<>() : ctx.class_field();
    ArrayList<VMField> vmFields = new ArrayList<>();

    for (Class_fieldContext field : fields) {
      VMField fieldInfo = (VMField) field.accept(this);

      vmFields.add(fieldInfo);
    }

    currClass.setFields(vmFields);
    currClass.setProgram(this.vmProgram);

    currClass.setClassInherit(classInherit);

    List<Class_bodyContext> funcs = ctx.class_body() == null ? new ArrayList<>() : ctx.class_body();
    ArrayList<VMMethod> vmFuncs = new ArrayList<>();

    for (Class_bodyContext func : funcs) {
      enterNewScope();
      VMMethod vmMethod = (VMMethod) func.accept(this);
      exitScope();
      exitFunc();
      vmFuncs.add(vmMethod);
    }

    if (!this.funcs.contains("m_" + name + "_" + name)) {

      RegisterList registerList = registerListParam.peek();
      int fstReg = registerList.getFstReg();
      VMLoadStore loadThis = new VMLoadStore(fstReg ,0, LoadStore.LOAD, VarOrArg.ARG);
      VMRetn retnThis = new VMRetn(fstReg);
      VmCodesInfo loadNRetnThis = new VmCodesInfo(loadThis);
      loadNRetnThis.insertBackInstruction(new VmCodesInfo(retnThis));

      ArrayList<Type> args = new ArrayList<>();
      args.add(typeChecker.getClassTable().get(currClassName));

      VMFunction defaultConstr = new VMFunction("m_" + name + "_" + name, args, new ArrayList<>(),
        buildFuncDeclares(), declaredClass);
      this.funcs.add("m_" + name + "_" + name);
      this.declaredFuncs.put("m_" + name + "_" + name,  new ArrayList<>());
      this.vmProgram.addFunction(defaultConstr);
      defaultConstr.addInstructions(loadNRetnThis);
    }

    classDeclare.setAtts(name, classInherit, this.vmProgram, currIdx, vmFields, vmFuncs, this.funcs.indexOf(
      "m_" + name + "_" + name));

    return null;
  }

  @Override
  public Information visitField_ident(@NotNull Field_identContext ctx) {

    RegisterList registerList = registerListParam.peek();
    RegisterList fstRemoved = registerList.removeFstReg();
    int fstFreeReg = fstRemoved.getFstReg();

    VmCodesInfo ret = new VmCodesInfo();

    VMLoadStore loadObj;
    VMClassDeclare temp = currClass;

    if (ctx.THIS() != null) {
      loadObj = new VMLoadStore(fstFreeReg, 0, LoadStore.LOAD, VarOrArg.ARG);
    } else {
      VarOrArg varOrArg;
      if (currFunVars.contain(ctx.IDENT(0).getText())) {
        varOrArg = VarOrArg.VAR;
      } else {
        varOrArg = VarOrArg.ARG;
      }
      loadObj = new VMLoadStore(fstFreeReg, currFunVars.lookForAll(ctx.IDENT(0).getText()), LoadStore.LOAD, varOrArg);
      currClass = getNextClass((ClassType) typeSt.lookForAll(ctx.IDENT(0).getText()));
    }

    ClassType currClassT = typeChecker.getClassTable().get(currClass.getClassName());
    for (int i = 1; i < ctx.IDENT().size() - 1; i++) {
      String name = ctx.IDENT(i).getText();
      VMClassAccess loadField = new VMClassAccess(fstFreeReg, fstFreeReg, getFieldIdx(name),
          declaredClassNames.indexOf(currClass.getClassName()), LoadStore.LOAD);
      ret.insertBackInstruction(new VmCodesInfo(loadField));

      ClassType subType = (ClassType) getSubClass(currClassT, name);
      currClass = getNextClass(subType);
      currClassT = typeChecker.getClassTable().get(currClass.getClassName());
    }

    String field = ctx.IDENT(ctx.IDENT().size() - 1).getText();
    int fieldIdx = getFieldIdx(field);

    ret.insertFrontInstruction(new VmCodesInfo(loadObj));

    VMClassAccess strOrLdr;
    if (operation.equals(LoadStore.STORE)) {
      strOrLdr = new VMClassAccess(fstFreeReg, assignRegister.getSrcOrdstReg(),
          fieldIdx, declaredClass.indexOf(currClass), LoadStore.STORE);
    } else {
      strOrLdr = new VMClassAccess(fstFreeReg, assignRegister.getSrcOrdstReg(), fieldIdx,
          declaredClass.indexOf(currClass), LoadStore.LOAD);
    }

    ret.insertBackInstruction(new VmCodesInfo(strOrLdr));

    return ret;

  }

  private Type getSubClass(ClassType c, String name) {
    Type sub = c.lookForObject(name);
    return sub;
  }


  @Override
  public Information visitClass_body_func(@NotNull Class_body_funcContext ctx) {
    return ctx.class_func().accept(this);
  }


  @Override
  public Information visitStat_super(@NotNull Stat_superContext ctx) {
    RegisterList registerList = registerListParam.peek();
    RegisterList fstRegRemoved = registerList.removeFstReg();
    RegisterList fstNRegRemoved = fstRegRemoved.removeFstReg();

    int currClassIdx = declaredClassNames.indexOf(currClassName);

    String parentName = declaredClassNames.get(currClass.getParentIdx());
    // get the parent's name to get constructor's function index.
    String parentConstr = "m_" + parentName + "_" + parentName;

    //VMNewClass newObj = new VMNewClass(fstRegRemoved.getFstReg(), currClassIdx);

    VMLoadStore loadObj = new VMLoadStore(fstRegRemoved.getFstReg(), 0 ,LoadStore.LOAD, VarOrArg.ARG);

    VMCall superConstructor = new VMCallDirect(funcs.indexOf(parentConstr), registerList.getFstReg());
    superConstructor.addArg(fstRegRemoved.getFstReg());
    VmCodesInfo ret = new VmCodesInfo(superConstructor);

    VmCodesInfo transArgs = new VmCodesInfo();
    List<ExprContext> args = ctx.arg_list() == null ? new ArrayList<>() : ctx.arg_list().expr();

    for (ExprContext arg : args) {
      registerListParam.push(fstNRegRemoved);
      assignRegister = new AssignRegister(fstNRegRemoved.getFstReg());
      VmCodesInfo transExpr = (VmCodesInfo) visit(arg);
      registerListParam.pop();

      superConstructor.addArg(fstNRegRemoved.getFstReg());
      transArgs.insertBackInstruction(transExpr);

      fstNRegRemoved = fstNRegRemoved.removeFstReg();
    }

    ret.insertFrontInstruction(transArgs);
    ret.insertFrontInstruction(new VmCodesInfo(loadObj));
    return ret;
  }

  @Override
  public Information visitNew_object(@NotNull New_objectContext ctx) {
    RegisterList registerList = registerListParam.peek();
    RegisterList fstRemoved = registerList.removeFstReg();
    int obj = fstRemoved.getFstReg();
    RegisterList paramRegs = fstRemoved.removeFstReg();

    String funcName = ctx.IDENT().getText();
    VMNewClass newObj = new VMNewClass(obj, declaredClassNames.indexOf(ctx.IDENT().getText()));


    List<ExprContext> args = ctx.arg_list() == null ? new ArrayList<>() : ctx.arg_list().expr();

    VmCodesInfo transArgs = new VmCodesInfo();

    ArrayList<Integer> argRegs = new ArrayList<>();

    for (ExprContext arg : args) {

      registerListParam.push(paramRegs);
      assignRegister = new AssignRegister(paramRegs.getFstReg());
      VmCodesInfo transExpr = (VmCodesInfo) arg.accept(this);
      registerListParam.pop();

      argRegs.add(paramRegs.getFstReg());
      transArgs.insertBackInstruction(transExpr);

      paramRegs = paramRegs.removeFstReg();
    }

    String className = ctx.IDENT().getText();
    int classIdx = funcs.indexOf("m_" + className + "_" + className);

    VMCallDirect constr = new VMCallDirect(classIdx, registerList.getFstReg());
    constr.addArg(obj);

    for (Integer arg : argRegs) {
      constr.addArg(arg);
    }

    VmCodesInfo ret = new VmCodesInfo(newObj);

    ret.insertBackInstruction(transArgs);

    ret.insertBackInstruction(new VmCodesInfo(constr));

    return ret;
  }


  @Override
  public Information visitAssignL_field_ident(@NotNull AssignL_field_identContext ctx) {
    operation = LoadStore.STORE;
    return ctx.field_ident().accept(this);
  }

  @Override
  public Information visitClass_field(@NotNull Class_fieldContext ctx) {

    VMField field;
    /*
    public VMField(Type type, String name)
     */
    Type type = (Type) ctx.wacc_type().accept(typeChecker);
    String name = ctx.IDENT().getText();

    typeSt.addSymbol(name, type);

    field = new VMField(type, name);

    return field;
  }

  @Override
  public Information visitClass_constr(@NotNull Class_constrContext ctx) {

    VMMethod constr;

    /* class method
    public VMMethod(int function, String methodName)
     */

    /* vmfunction
    public VMFunction(String functionName, ArrayList<Type> argTypes,
                    ArrayList<Type> varTypes, VMFuncDeclares functions,
                    ArrayList<VMClass> classes)
     */

    String funcName = "m_" + currClassName + "_" + ctx.IDENT().getText();
    this.funcs.add(funcName);
    currFunArgsT.add(typeChecker.getClassTable().get(currClassName));
    currFunArgs.add("this");
    this.declaredFuncs.put(funcName, currFunArgsT);

    VMFunction func = new VMFunction(funcName,
        new ArrayList<>(), new ArrayList<>(),
        new VMFuncDeclares(), new ArrayList<VMClass>());

    currFun = func;

    List<ParamContext> params = ctx.param_list() == null ? new ArrayList<>() : ctx.param_list().param();

    for (ParamContext param : params) {
      Type t = (Type) param.wacc_type().accept(typeChecker);
      String name = param.IDENT().getText();
      typeSt.addSymbol(name, t);
      currFunArgs.add(name);
      currFunArgsT.add(t);
      currFun.addArg(t);
    }

    VmCodesInfo funcBody = (VmCodesInfo) ctx.stat().accept(this);

    VMFunction funcToAdd = new VMFunction(funcName, currFunArgsT,
        currFunVarsT, buildFuncDeclares(), declaredClass);//todo add ArrayList<VMClass>

    RegisterList registerList = registerListParam.peek();
    int fstReg = registerList.getFstReg();
    VMLoadStore loadThis = new VMLoadStore(fstReg ,0, LoadStore.LOAD, VarOrArg.ARG);
    VMRetn retnThis = new VMRetn(fstReg);
    funcBody.insertBackInstruction(new VmCodesInfo(loadThis));
    funcBody.insertBackInstruction(new VmCodesInfo(retnThis));


    funcToAdd.addInstructions(funcBody);

    this.vmProgram.addFunction(funcToAdd);

    constr = new VMMethod(funcs.size() - 1, funcName);

    return constr;
  }

  @Override
  public Information visitExpr_object(@NotNull Expr_objectContext ctx) {
    return ctx.new_object().accept(this);
  }

  @Override
  public Information visitClass_func(@NotNull Class_funcContext ctx) {
    VMMethod classFunc;
    FuncContext funcContext = ctx.func();

    String funcName = currClassName + "_" + funcContext.IDENT().getText();

    this.funcs.add(funcName);

    VMFunction func = new VMFunction(funcName,
        new ArrayList<>(), new ArrayList<>(),
        new VMFuncDeclares(), new ArrayList<>());

    currFun = func;

    List<ParamContext> params = funcContext.param_list() == null ? new ArrayList<>() :
        funcContext.param_list().param();

    for (ParamContext param : params) {
      Type type = (Type) typeChecker.visit(param.wacc_type());
      String name = param.IDENT().getText();
      typeSt.addSymbol(name, type);
      currFunArgs.add(name);
      currFunArgsT.add(type);
      currFun.addArg(type);
    }

    currFunArgsT.add(0, typeChecker.getClassTable().get(currClass.getClassName()));
    currFunArgs.add(0, "this");

    declaredFuncs.put(funcName, currFunArgsT);

    VmCodesInfo funcBody = (VmCodesInfo) funcContext.stat().accept(this);

    VMFunction funcToAdd = new VMFunction(funcName, currFunArgsT,
        currFunVarsT, buildFuncDeclares(), declaredClass);//todo add ArrayList<VMClass>

    funcToAdd.addInstructions(funcBody);

    this.vmProgram.addFunction(funcToAdd);

    classFunc = new VMMethod(funcs.size() - 1, funcName);

    return classFunc;
  }

  @Override
  public Information visitEncaps_PROTECTED(@NotNull Encaps_PROTECTEDContext ctx) {
    return null;
  }

  @Override
  public Information visitClass_body_constr(@NotNull Class_body_constrContext ctx) {
    return ctx.class_constr().accept(this);
  }

  @Override
  public Information visitEncaps_PUBLIC(@NotNull Encaps_PUBLICContext ctx) {
    return null;
  }

  @Override
  public Information visitAssignL_array_elem(@NotNull AssignL_array_elemContext ctx) {
    operation = LoadStore.STORE;

    return ctx.array_elem().accept(this);
  }

  @Override
  public Information visitAssignR_expr(@NotNull AssignR_exprContext ctx) {
    operation = LoadStore.LOAD;
    assignRegister = new AssignRegister(registerListParam.peek().getFstReg());
    return ctx.expr().accept(this);
  }


  @Override
  public Information visitMain(@NotNull MainContext ctx) {
    return ctx.prog().accept(this);
  }

  @Override
  public Information visitPElem_PAIRTYPE(@NotNull PElem_PAIRTYPEContext ctx) {
    return new PairType();
  }

  @Override
  public Information visitEpxr_char(@NotNull Epxr_charContext ctx) {
    return ctx.char_liter().accept(this);
  }

  @Override
  public Information visitAssignL_pair_elem(@NotNull AssignL_pair_elemContext ctx) {
    operation = LoadStore.STORE;
    return ctx.pair_elem().accept(this);
  }

  @Override
  public Information visitBaseT_BOOLTYPE(@NotNull BaseT_BOOLTYPEContext ctx) {
    return new BoolType();
  }

  @Override
  public Information visitBool_TRUE(@NotNull Bool_TRUEContext ctx) {
    RegisterList rl = registerListParam.peek();
    VMCode code = new VMAssign(rl.getFstReg(), 1, new BoolType());
    return new VmCodesInfo(code);
  }


  @Override
  public Information visitBool_FALS(@NotNull Bool_FALSContext ctx) {
    RegisterList rl = registerListParam.peek();
    VMCode code = new VMAssign(rl.getFstReg(), 0, new BoolType());
    return new VmCodesInfo(code);
  }

  @Override
  public Information visitChar_liter(@NotNull Char_literContext ctx) {
    RegisterList rl = registerListParam.peek();
    VMCode code = new VMAssign(rl.getFstReg(), VMProgram.unescapeWacc(ctx.getText()).charAt(0),
        new CharType());
    return new VmCodesInfo(code);
  }

  @Override
  public Information visitWaccT_array(WaccT_arrayContext ctx) {
    return new ArrayType("", (Type) ctx.wacc_type().accept(typeChecker), -1);
  }

  @Override
  public Information visitExpr_str(@NotNull Expr_strContext ctx) {
    return ctx.str_liter().accept(this);
  }

  @Override
  public Information visitWaccT_pair_type(@NotNull WaccT_pair_typeContext ctx) {
    return visit(ctx.pair_type());
  }

  @Override
  public Information visitExpr_array_elem(@NotNull Expr_array_elemContext ctx) {
    operation = LoadStore.LOAD;
    return ctx.array_elem().accept(this);
  }

  @Override
  public Information visitPElem_base_type(@NotNull PElem_base_typeContext ctx) {
    return ctx.base_type().accept(this);
  }

  @Override
  public Information visitStat_PRINTLN(Stat_PRINTLNContext ctx) {

    RegisterList rl = registerListParam.peek();

    registerListParam.push(new RegisterList(rl));
    Information exprInfo = ctx.expr().accept(typeChecker);
    VmCodesInfo transExpr = (VmCodesInfo) ctx.expr().accept(this);
    registerListParam.pop();

    VmCodesInfo ret = new VmCodesInfo(new VMPrint(getPrintType(exprInfo),
        rl.getFstReg(), true));

    ret.insertFrontInstruction(transExpr);

    return ret;
  }

  @Override
  public Information visitWaccT_base_type(@NotNull WaccT_base_typeContext ctx) {
    return visit(ctx.base_type());
  }


  @Override
  public Information visitPElem_wacc_type(PElem_wacc_typeContext ctx) {
    return ctx.wacc_type().accept(this);
  }

  @Override
  public Information visitExpr_expr(@NotNull Expr_exprContext ctx) {
    return ctx.expr().accept(this);
  }

  @Override
  public Information visitAssignR_pair_elem(@NotNull AssignR_pair_elemContext ctx) {
    operation = LoadStore.LOAD;
    assignRegister = new AssignRegister(registerListParam.peek().getFstReg());
    return ctx.pair_elem().accept(this);
  }


  @Override
  public Information visitExpr_bool(@NotNull Expr_boolContext ctx) {
    return ctx.bool_liter().accept(this);
  }

  @Override
  public Information visitBaseT_STRINGTYPE(@NotNull BaseT_STRINGTYPEContext ctx) {
    return new StringType();
  }

  @Override
  public Information visitInt_liter(@NotNull Int_literContext ctx) {
    if (isVisitingUnary) {
      intLiter = ctx.getText();
      return new VmCodesInfo();
    }

    RegisterList rl = registerListParam.peek();
    VMCode int_val = new VMAssign(rl.getFstReg(), Integer.parseInt(ctx.getText()), new IntType());
    return new VmCodesInfo(int_val);
  }

  @Override
  public Information visitPair_type(@NotNull Pair_typeContext ctx) {
    return ctx.accept(typeChecker);
  }

  @Override
  public Information visitParam(@NotNull ParamContext ctx) {
    Type type = (Type) ctx.wacc_type().accept(typeChecker);
    type.setVarName(ctx.IDENT().getText());
    return type;
  }

  @Override
  public Information visitBaseT_INTTYPE(@NotNull BaseT_INTTYPEContext ctx) {
    return new IntType();
  }

  @Override
  public Information visitExpr_pair(@NotNull Expr_pairContext ctx) {
    return ctx.pair_liter().accept(this);
  }

  @Override
  public Information visitStat_SKIP(@NotNull Stat_SKIPContext ctx) {
    //do nothing during skip
    return new VmCodesInfo();
  }

  @Override
  public Information visitPair_liter(@NotNull Pair_literContext ctx) {
    RegisterList registerList = registerListParam.peek();
    VMAssign zeroAssign = new VMAssign(registerList.getFstReg(), 0, new PairType());
    return new VmCodesInfo(zeroAssign);
  }

  @Override
  public Information visitBaseT_CHARTYPE(@NotNull BaseT_CHARTYPEContext ctx) {
    return new CharType();
  }

  @Override
  public Information visitStr_liter(@NotNull Str_literContext ctx) {
    RegisterList rl = registerListParam.peek();
    vmProgram.addString(ctx.STR().getText());
    int idx = vmProgram.stringTableSize() - 1;
    VMCode code = new VMLoadString(idx, rl.getFstReg());
    return new VmCodesInfo(code);
  }

  @Override
  public Information visitArray_elem(@NotNull Array_elemContext ctx) {

    RegisterList registerList = registerListParam.peek();

    List<ExprContext> exprs = ctx.expr();
    Integer size = exprs.size();


    String name = ctx.IDENT().getText();
    Type arrayT = typeSt.lookForAll(ctx.IDENT().getText());
    Type elemT = typeSt.lookForAll(ctx.IDENT().getText());

    for (int i = 0; i < exprs.size(); i++){
      elemT = elemT.getElemType();
    }

    ElemSize varSize = elemT.getByteSize();

    Integer fstFreeReg = registerList.getFstReg();

    RegisterList fstRemoved = registerList.removeFstReg();

    VmCodesInfo load = null;
    VmCodesInfo transExpr;

    int arrIdx;
    VarOrArg varOrArg;
    if (currFunArgs.contains(name)) {
      arrIdx = currFunVars.lookForAll(name);
      varOrArg = VarOrArg.ARG;
    } else {
      arrIdx = currFunVars.lookForAll(name);
      varOrArg = VarOrArg.VAR;
    }

    if (size > 1 || operation.equals(LoadStore.LOAD)) {
      registerListParam.push(fstRemoved);
      transExpr = (VmCodesInfo) exprs.get(0).accept(this);
      registerListParam.pop();

      load = new VmCodesInfo(new VMLoadStore(fstFreeReg, currFunVars.lookForAll(name), LoadStore.LOAD, varOrArg));

      VMArrayAccess vmArrayAccess = new VMArrayAccess(fstFreeReg, fstFreeReg, fstRemoved.getFstReg(),
          LoadStore.LOAD, arrayT.getElemType().getByteSize());

      load.insertFrontInstruction(transExpr);
      load.insertBackInstruction(new VmCodesInfo(vmArrayAccess));
    }

    if (operation.equals(LoadStore.STORE)){
      size--;
    }

    for (int i = 1; i < size; i++) {
      registerListParam.push(fstRemoved);
      transExpr = (VmCodesInfo) exprs.get(i).accept(this);
      registerListParam.pop();

      VMArrayAccess arrAcc = new VMArrayAccess(fstFreeReg, fstFreeReg,
          fstRemoved.getFstReg(), LoadStore.LOAD, varSize);

      load.insertBackInstruction(transExpr);
      load.insertBackInstruction(new VmCodesInfo(arrAcc));
    }

    if (operation.equals(LoadStore.STORE)){
      registerListParam.push(fstRemoved);
      transExpr = (VmCodesInfo) exprs.get(size).accept(this);
      registerListParam.pop();

      VMCode loadArr = new VMLoadStore(fstFreeReg, arrIdx, LoadStore.LOAD, varOrArg);

      VMArrayAccess arrAcc = new VMArrayAccess(assignRegister.getSrcOrdstReg(), fstFreeReg,
          fstRemoved.getFstReg(), LoadStore.STORE, varSize);

      VmCodesInfo ret = new VmCodesInfo(arrAcc);
      ret.insertFrontInstruction(transExpr);

      ret.insertFrontInstruction(new VmCodesInfo(loadArr));
      if (load != null){
        ret.insertFrontInstruction(load);
      }

      return ret;
    }

    return load;
  }

  @Override
  public Information visitAssignR_NEWPAIR(@NotNull AssignR_NEWPAIRContext ctx) {
    RegisterList registerList = registerListParam.peek();
    RegisterList fst1RegRemoved = registerList.removeFstReg();
    RegisterList fst2RegRemoved = fst1RegRemoved.removeFstReg();

    List<ExprContext> fstNSnd = ctx.expr() == null ? new ArrayList<>() : ctx.expr();

    VmCodesInfo transFst;
    VmCodesInfo transSnd;
    registerListParam.push(fst1RegRemoved);
    try {
      transFst = (VmCodesInfo) fstNSnd.get(0).accept(this);
    } catch (ClassCastException e){
      VMAssign zeroAssign = new VMAssign(fst1RegRemoved.getFstReg(), 0, new PairType());
      transFst = new VmCodesInfo(zeroAssign);
    }
    // pair(pair, int) a = newpair(null, 1);
    registerListParam.pop();

    registerListParam.push(fst2RegRemoved);
    try {
      transSnd = (VmCodesInfo) fstNSnd.get(1).accept(this);
    } catch (ClassCastException e) {
      VMAssign zeroAssign = new VMAssign(fst2RegRemoved.getFstReg(), 0, new PairType());
      transSnd = new VmCodesInfo(zeroAssign);
    }
    registerListParam.pop();

    VMNewPair newPair = new VMNewPair(fst1RegRemoved.getFstReg(), fst2RegRemoved.getFstReg(),
        registerList.getFstReg());

    VmCodesInfo ret = new VmCodesInfo(newPair);

    ret.insertFrontInstruction(transSnd);
    ret.insertFrontInstruction(transFst);

    return ret;
  }

  @Override
  public Information visitStat_IF(@NotNull Stat_IFContext ctx) {
    RegisterList reg_list = registerListParam.peek();
    VmCodesInfo predicate = (VmCodesInfo) visit(ctx.expr());

    VMCode jmp_true = new VMCondJmp(reg_list.getFstReg(), "L" + label_num);
    // final boolean after evaluation stored to first reg. in reg_list.

    VmCodesInfo condition = new VmCodesInfo(jmp_true); // initiate for the whole conditional block.
    condition.insertFrontInstruction(predicate); // to front b/c predicate always evaluated first.

    enterNewScope();
    VmCodesInfo if_label = new VmCodesInfo(new VMLabel("L" + (label_num++)));
    // increment the label number after a declaration.
    VmCodesInfo if_block = (VmCodesInfo) visit(ctx.stat(0));
    exitScope();


    enterNewScope();
    VmCodesInfo else_block = (VmCodesInfo) visit(ctx.stat(1));
    exitScope();
    VmCodesInfo jmp_end = new VmCodesInfo(new VMUncondJmp("L" + (label_num)));
    VmCodesInfo end_label = new VmCodesInfo(new VMLabel("L" + (label_num++)));
    //jump over to label after if_true block

    condition.insertBackInstruction(else_block);
    condition.insertBackInstruction(jmp_end);
    condition.insertBackInstruction(if_label);
    condition.insertBackInstruction(if_block);
    condition.insertBackInstruction(end_label);

    //e.g CMP r4, #0       # predicate
    //    BEQ L0           # if true jump to if-block
    //    ...              # if false, execute else-block here
    //    B L1             # after exe. of else block, jump, unconditionally, over if-block to end
    //L0:
    //    ...              # if-block
    //L1: ...              # if-else block ended, onto rest of the code

    return condition;
  }

  @Override
  public Information visitExpr_ident(@NotNull Expr_identContext ctx) {

    RegisterList registerList = registerListParam.peek();

    VarOrArg varOrArg;
    String name = ctx.IDENT().getText();
    //System.out.println(name);
    if (currFunArgs.contains(ctx.IDENT().getText())){
      varOrArg = VarOrArg.ARG;
    } else {
      varOrArg = VarOrArg.VAR;
    }
    VMLoadStore load;
    if (varOrArg.equals(VarOrArg.ARG)) {
      load = new VMLoadStore(registerList.getFstReg(),
          getArgIdx(ctx.IDENT().getText()), LoadStore.LOAD, varOrArg);
    } else {
      load = new VMLoadStore(registerList.getFstReg(),
          currFunVars.lookForAll(ctx.IDENT().getText()), LoadStore.LOAD, varOrArg);
    }
    return new VmCodesInfo(load);
  }

  @Override
  public Information visitExpr_logicOper(@NotNull Expr_logicOperContext ctx) {
    RegisterList registerList = registerListParam.peek();
    RegisterList withFstRemoved;
    ExprContext expr1 = ctx.expr(0);
    ExprContext expr2 = ctx.expr(1);

    registerListParam.push(new RegisterList(registerList));
    VmCodesInfo transExpr1 = (VmCodesInfo) visit(expr1);
    registerListParam.pop();

    withFstRemoved = registerList.removeFstReg();
    registerListParam.push(withFstRemoved);
    VmCodesInfo transExpr2 = (VmCodesInfo) visit(expr2);
    registerListParam.pop();

    binOpParam = new BinOpRegister(registerList.getFstReg(),
        registerList.getFstReg(), withFstRemoved.getFstReg());
    VmCodesInfo binOpInstr = (VmCodesInfo) visit(ctx.logicOper());

    binOpInstr.insertFrontInstruction(transExpr2);
    binOpInstr.insertFrontInstruction(transExpr1);
    return binOpInstr;
  }

  @Override
  public Information visitExpr_compare(@NotNull Expr_compareContext ctx) {
    RegisterList registerList = registerListParam.peek();
    RegisterList withFstRemoved;
    ExprContext expr1 = ctx.expr(0);
    ExprContext expr2 = ctx.expr(1);

    registerListParam.push(new RegisterList(registerList));
    VmCodesInfo transExpr1 = (VmCodesInfo) visit(expr1);
    registerListParam.pop();

    withFstRemoved = registerList.removeFstReg();
    registerListParam.push(withFstRemoved);
    VmCodesInfo transExpr2 = (VmCodesInfo) visit(expr2);
    registerListParam.pop();

    binOpParam = new BinOpRegister(registerList.getFstReg(),
        registerList.getFstReg(), withFstRemoved.getFstReg());
    VmCodesInfo binOpInstr = (VmCodesInfo) visit(ctx.compare());

    binOpInstr.insertFrontInstruction(transExpr2);
    binOpInstr.insertFrontInstruction(transExpr1);
    return binOpInstr;
  }

  @Override
  public Information visitUnOp_sign(@NotNull UnOp_signContext ctx) {
    return visit(ctx.int_sign());
  }

  @Override
  public Information visitIntSign_MINUS(@NotNull IntSign_MINUSContext ctx) {
    VmCodesInfo ret;
    if (intLiter != null) {
      RegisterList registerList = registerListParam.peek();
      VMAssign assign = new VMAssign(registerList.getFstReg(),
          Integer.parseInt("-" + intLiter), new IntType());
      VMCode neg = new VMUnOp(registerList.getFstReg(), unOpRegister.getReg(),
          UnOp.PLUS);

      ret = new VmCodesInfo(neg);
      ret.insertFrontInstruction(new VmCodesInfo(assign));
    } else {
      ret = new VmCodesInfo(new VMUnOp(unOpRegister.getRegDst(), unOpRegister.getReg(),
          UnOp.NEG));
    }
    intLiter = null;
    return ret;
  }

  @Override
  public Information visitIntSign_PLUS(@NotNull IntSign_PLUSContext ctx) {
    VmCodesInfo ret;
    if (intLiter != null) {
      RegisterList registerList = registerListParam.peek();
      VMAssign assign = new VMAssign(registerList.getFstReg(),
          Integer.parseInt(intLiter), new IntType());
      VMCode pls = new VMUnOp(registerList.getFstReg(), unOpRegister.getReg(),
          UnOp.PLUS);

      ret = new VmCodesInfo(pls);
      ret.insertFrontInstruction(new VmCodesInfo(assign));
    } else {
      ret = new VmCodesInfo(new VMUnOp(unOpRegister.getRegDst(), unOpRegister.getReg(),
          UnOp.PLUS));
    }
    intLiter = null;
    return ret;
  }

  @Override
  public Information visitUnOp_ORD(@NotNull UnOp_ORDContext ctx) {
    VMCode ord = new VMUnOp(unOpRegister.getRegDst(), unOpRegister.getReg(),
        UnOpInfo.UnOp.ORD);
    return new VmCodesInfo(ord);
  }

  @Override
  public Information visitUnOp_NOT(@NotNull UnOp_NOTContext ctx) {
    VMCode not = new VMUnOp(unOpRegister.getRegDst(), unOpRegister.getReg(),
        UnOpInfo.UnOp.NOT);
    return new VmCodesInfo(not);
  }

  @Override
  public Information visitUnOp_CHR(@NotNull UnOp_CHRContext ctx) {
    VmCodesInfo ret;
    if (intLiter != null) {
      RegisterList registerList = registerListParam.peek();
      VMAssign assign = new VMAssign(registerList.getFstReg(),
          Integer.parseInt(intLiter), new IntType());
      VMCode chr = new VMUnOp(registerList.getFstReg(), unOpRegister.getReg(),
          UnOp.CHR);

      ret = new VmCodesInfo(chr);
      ret.insertFrontInstruction(new VmCodesInfo(assign));
    } else {
      ret = new VmCodesInfo(new VMUnOp(unOpRegister.getRegDst(), unOpRegister.getReg(),
          UnOp.CHR));
    }
    intLiter = null;
    return ret;
  }

  @Override
  public Information visitUnOp_LEN(@NotNull UnOp_LENContext ctx) {
    VMCode len = new VMUnOp(unOpRegister.getRegDst(), unOpRegister.getReg(),
        UnOpInfo.UnOp.LEN);
    return new VmCodesInfo(len);
  }

  @Override
  public Information visitBinOp_MUL(@NotNull BinOp_MULContext ctx) {
    VMCode mul = new VMBinOp(binOpParam.getReg1(), binOpParam.getReg2(),
        binOpParam.getRegDst(), BinOpInfo.BinOp.MUL);
    return new VmCodesInfo(mul);
  }

  @Override
  public Information visitBinOp_DIV(@NotNull BinOp_DIVContext ctx) {
    VMCode div = new VMBinOp(binOpParam.getReg1(), binOpParam.getReg2(),
        binOpParam.getRegDst(), BinOpInfo.BinOp.DIV);
    return new VmCodesInfo(div);
  }

  @Override
  public Information visitBinOp_MOD(@NotNull BinOp_MODContext ctx) {
    VMCode mod = new VMBinOp(binOpParam.getReg1(), binOpParam.getReg2(),
        binOpParam.getRegDst(), BinOpInfo.BinOp.MOD);
    return new VmCodesInfo(mod);
  }

  @Override
  public Information visitBinOp_MINUS(@NotNull BinOp_MINUSContext ctx) {
    VMCode sub = new VMBinOp(binOpParam.getReg1(), binOpParam.getReg2(),
        binOpParam.getRegDst(), BinOpInfo.BinOp.SUB);
    return new VmCodesInfo(sub);
  }

  @Override
  public Information visitBinOp_PLUS(@NotNull BinOp_PLUSContext ctx) {
    VMCode add = new VMBinOp(binOpParam.getReg1(), binOpParam.getReg2(),
        binOpParam.getRegDst(), BinOpInfo.BinOp.ADD);
    return new VmCodesInfo(add);
  }

  @Override
  public Information visitBinOp_AND(@NotNull BinOp_ANDContext ctx) {
    VMCode and = new VMBinOp(binOpParam.getReg1(), binOpParam.getReg2(),
        binOpParam.getRegDst(), BinOpInfo.BinOp.AND);
    return new VmCodesInfo(and);
  }

  @Override
  public Information visitBinOo_OR(@NotNull BinOo_ORContext ctx) {
    VMCode or = new VMBinOp(binOpParam.getReg1(), binOpParam.getReg2(),
        binOpParam.getRegDst(), BinOpInfo.BinOp.OR);
    return new VmCodesInfo(or);
  }

  @Override
  public Information visitBinOp_LT(@NotNull BinOp_LTContext ctx) {
    VMCode lt = new VMBinOp(binOpParam.getReg1(), binOpParam.getReg2(),
        binOpParam.getRegDst(), BinOpInfo.BinOp.LT);
    return new VmCodesInfo(lt);
  }

  @Override
  public Information visitBinOp_LTE(@NotNull BinOp_LTEContext ctx) {
    VMCode lte = new VMBinOp(binOpParam.getReg1(), binOpParam.getReg2(),
        binOpParam.getRegDst(), BinOpInfo.BinOp.LTE);
    return new VmCodesInfo(lte);
  }

  @Override
  public Information visitBinOp_EQ(@NotNull BinOp_EQContext ctx) {
    VMCode eq = new VMBinOp(binOpParam.getReg1(), binOpParam.getReg2(),
        binOpParam.getRegDst(), BinOpInfo.BinOp.EQ);
    return new VmCodesInfo(eq);
  }

  @Override
  public Information visitBinOp_GT(@NotNull BinOp_GTContext ctx) {
    VMCode gt = new VMBinOp(binOpParam.getReg1(), binOpParam.getReg2(),
        binOpParam.getRegDst(), BinOpInfo.BinOp.GT);
    return new VmCodesInfo(gt);
  }//

  @Override
  public Information visitBinOp_GTE(@NotNull BinOp_GTEContext ctx) {
    VMCode gte = new VMBinOp(binOpParam.getReg1(), binOpParam.getReg2(),
        binOpParam.getRegDst(), BinOpInfo.BinOp.GTE);
    return new VmCodesInfo(gte);
  }

  @Override
  public Information visitBinOp_NEQ(@NotNull BinOp_NEQContext ctx) {
    VMCode neq = new VMBinOp(binOpParam.getReg1(), binOpParam.getReg2(),
        binOpParam.getRegDst(), BinOpInfo.BinOp.NEQ);
    return new VmCodesInfo(neq);
  }

  @Override
  public Information visitExpr_unaryOper(@NotNull Expr_unaryOperContext ctx) {
    isVisitingUnary = true;

    RegisterList registerList = registerListParam.peek();
    ExprContext expr = ctx.expr();

    registerListParam.push(new RegisterList(registerList));
    VmCodesInfo transExpr = (VmCodesInfo) expr.accept(this);
    registerListParam.pop();

    unOpRegister = new UnOpRegister(registerList.getFstReg(),
        registerList.getFstReg());
    VmCodesInfo unOpInstr = (VmCodesInfo) ctx.unaryOper().accept(this);

    unOpInstr.insertFrontInstruction(transExpr);
    isVisitingUnary = false;
    return unOpInstr;
  }

  @Override
  public Information visitExpr_arithMinusPlus(@NotNull Expr_arithMinusPlusContext ctx) {
    RegisterList registerList = registerListParam.peek();
    RegisterList withFstRemoved;
    ExprContext expr1 = ctx.expr(0);
    ExprContext expr2 = ctx.expr(1);

    registerListParam.push(new RegisterList(registerList));
    VmCodesInfo transExpr1 = (VmCodesInfo) expr1.accept(this);
    registerListParam.pop();

    withFstRemoved = registerList.removeFstReg();
    registerListParam.push(withFstRemoved);
    VmCodesInfo transExpr2 = (VmCodesInfo) expr2.accept(this);
    registerListParam.pop();

    binOpParam = new BinOpRegister(registerList.getFstReg(),
        registerList.getFstReg(), withFstRemoved.getFstReg());
    VmCodesInfo binOpInstr = (VmCodesInfo) ctx.arithMinusPlus().accept(this);

    binOpInstr.insertFrontInstruction(transExpr2);
    binOpInstr.insertFrontInstruction(transExpr1);
    return binOpInstr;
  }

  @Override
  public Information visitExpr_arithMulDivMod(@NotNull Expr_arithMulDivModContext ctx) {

    RegisterList registerList = registerListParam.peek();
    RegisterList withFstRemoved;
    ExprContext expr1 = ctx.expr(0);
    ExprContext expr2 = ctx.expr(1);

    registerListParam.push(new RegisterList(registerList));
    VmCodesInfo transexpr1 = (VmCodesInfo) expr1.accept(this);
    registerListParam.pop();

    withFstRemoved = registerList.removeFstReg();
    registerListParam.push(withFstRemoved);
    VmCodesInfo transexpr2 = (VmCodesInfo) expr2.accept(this);
    registerListParam.pop();

    binOpParam = new BinOpRegister(registerList.getFstReg(),
        registerList.getFstReg(), withFstRemoved.getFstReg());
    VmCodesInfo binopInstr = (VmCodesInfo) ctx.arithMulDivMod().accept(this);

    binopInstr.insertFrontInstruction(transexpr2);
    binopInstr.insertFrontInstruction(transexpr1);
    return binopInstr;
  }

  @Override
  public Information visitStat_variable_ASSIGN(@NotNull Stat_variable_ASSIGNContext ctx) {

    RegisterList registerList = registerListParam.peek();

    declaredType = (Type) ctx.wacc_type().accept(typeChecker);
    String name = ctx.IDENT().getText();
    typeSt.addSymbol(name, declaredType);
    currFunVars.addSymbol(name, var_num++);
    currFunVarsT.add(declaredType);
    currFun.addVar(declaredType);

    VmCodesInfo val = (VmCodesInfo) ctx.assign_rhs().accept(this);

    VMLoadStore decl = new VMLoadStore(registerList.getFstReg(), currFunVars.lookForAll(name), LoadStore.STORE,
        VarOrArg.VAR);

    VmCodesInfo ret = new VmCodesInfo(decl);

    ret.insertFrontInstruction(val);

    String varName = ctx.IDENT().getText();

    regSt.addSymbol(varName, registerList.getFstReg());

    return ret;
  }

  @Override
  public Information visitPairElem_FST(@NotNull PairElem_FSTContext ctx) {

    RegisterList registerList = registerListParam.peek();

    RegisterList fstRemoved = registerList.removeFstReg();

    registerListParam.push(fstRemoved);
    VmCodesInfo transExp = (VmCodesInfo) ctx.expr().accept(this);
    registerListParam.pop();

    VMPairAccess pairAcc;

    if (operation.equals(LoadStore.LOAD)) {
      pairAcc = new VMPairAccess(fstRemoved.getFstReg(), FstOrSnd.FST, assignRegister.getSrcOrdstReg(),
          LoadStore.LOAD);
    }
    else {
      pairAcc = new VMPairAccess(fstRemoved.getFstReg(), FstOrSnd.FST, assignRegister.getSrcOrdstReg(),
          LoadStore.STORE);
    }

    VmCodesInfo ret = new VmCodesInfo(pairAcc);
    ret.insertFrontInstruction(transExp);

    return ret;
  }


  @Override
  public Information visitArray_liter(@NotNull Array_literContext ctx) {

    RegisterList registerList = registerListParam.peek();
    RegisterList fstRegRemoved = registerList.removeFstReg();

    List<ExprContext> contents = ctx.expr() == null ? new ArrayList<>() : ctx.expr();

    if (declaredType instanceof ArrayType) {
      declaredType = declaredType.getElemType();
    }

    VMNewArray vmNewArray = new VMNewArray(contents.size(), registerList.getFstReg(), declaredType.getByteSize());
    VmCodesInfo ret = new VmCodesInfo(vmNewArray);

    int elemIdx = 0;
    VmCodesInfo arrayStore = new VmCodesInfo();
    int regElemIdx = fstRegRemoved.getFstReg();
    fstRegRemoved = fstRegRemoved.removeFstReg();
    for (ExprContext expr : contents) {

      registerListParam.push(fstRegRemoved);
      VmCodesInfo transExpr = (VmCodesInfo) expr.accept(this);
      registerListParam.pop();


      VMCode loadElemIdx = new VMAssign(regElemIdx, elemIdx++, new IntType());
      VMArrayAccess storeElem = new VMArrayAccess(fstRegRemoved.getFstReg(),
          registerList.getFstReg(), regElemIdx, LoadStore.STORE,
          declaredType.getByteSize());

      arrayStore.insertBackInstruction(new VmCodesInfo(loadElemIdx));
      arrayStore.insertBackInstruction(transExpr);
      arrayStore.insertBackInstruction(new VmCodesInfo(storeElem));
    }

    ret.insertBackInstruction(arrayStore);

    return ret;
  }

  @Override
  public Information visitStat_FREE(@NotNull Stat_FREEContext ctx) {

    RegisterList rl = registerListParam.peek();

    registerListParam.push(new RegisterList(rl));
    VmCodesInfo exprInfo = (VmCodesInfo) visit(ctx.expr());
    registerListParam.pop();

    VmCodesInfo ret = new VmCodesInfo(new VMFree(rl.getFstReg()));

    ret.insertFrontInstruction(exprInfo);

    return ret;
  }

  @Override
  public Information visitStat_PRINT(Stat_PRINTContext ctx) {

    RegisterList rl = registerListParam.peek();

    registerListParam.push(new RegisterList(rl));
    Information exprInfo = ctx.expr().accept(typeChecker);
    VmCodesInfo transExpr = (VmCodesInfo) ctx.expr().accept(this);
    registerListParam.pop();

    VmCodesInfo ret = new VmCodesInfo(new VMPrint(getPrintType(exprInfo),
        rl.getFstReg(), false));

    ret.insertFrontInstruction(transExpr);

    return ret;

  }


  private PrintType getPrintType(Information exprInfo) {

    if (exprInfo instanceof IntType) {
      return PrintType.INT;
    } else if (exprInfo instanceof CharType) {
      return PrintType.CHAR;
    } else if (exprInfo instanceof BoolType) {
      return PrintType.BOOL;
    } else if (exprInfo instanceof ArrayType && ((ArrayType) exprInfo).getElemType() instanceof CharType ){
      return PrintType.STRING;
    } else if (exprInfo instanceof StringType) {
      return PrintType.STRING;
    } else if (exprInfo instanceof ArrayType || exprInfo instanceof PairType) {
      return PrintType.ARRAY_PAIR;
    }
    return null;
  }

  @Override
  public Information visitStat_RETURN(Stat_RETURNContext ctx) {

    RegisterList registerList = registerListParam.peek();

    registerListParam.push(new RegisterList(registerList));
    VmCodesInfo exprInfo = (VmCodesInfo) visit(ctx.expr());
    registerListParam.pop();

    VmCodesInfo ret = new VmCodesInfo(new VMRetn(registerList.getFstReg()));

    ret.insertFrontInstruction(exprInfo);

    return ret;
  }

  @Override
  public Information visitAssignR_CALL(@NotNull AssignR_CALLContext ctx) {
    operation = LoadStore.LOAD;

    RegisterList registerList = registerListParam.peek();
    RegisterList fstNRegRemoved = registerList.removeFstReg();

    String funcName = ctx.IDENT().getText();

    List<ExprContext> args = ctx.arg_list() == null ? new ArrayList<>() : ctx.arg_list().expr();

    VMCall funcCall = new VMCallDirect(funcs.indexOf("f_" + funcName), registerList.getFstReg());
    VmCodesInfo ret = new VmCodesInfo(funcCall);

    VmCodesInfo transArgs = new VmCodesInfo();

    for (ExprContext arg : args) {

      registerListParam.push(fstNRegRemoved);
      assignRegister = new AssignRegister(fstNRegRemoved.getFstReg());
      VmCodesInfo transExpr = (VmCodesInfo) arg.accept(this);
      registerListParam.pop();

      funcCall.addArg(fstNRegRemoved.getFstReg());
      transArgs.insertBackInstruction(transExpr);

      fstNRegRemoved = fstNRegRemoved.removeFstReg();
    }

    ret.insertFrontInstruction(transArgs);

    return ret;
  }

  @Override
  public Information visitPairElem_SND(@NotNull PairElem_SNDContext ctx) {
    RegisterList registerList = registerListParam.peek();

    RegisterList fstRemoved = registerList.removeFstReg();

    registerListParam.push(fstRemoved);
    VmCodesInfo transExp = (VmCodesInfo) ctx.expr().accept(this);
    registerListParam.pop();

    VMPairAccess pairAcc;

    ctx.expr().accept(this);

    if (operation.equals(LoadStore.LOAD)) {
      pairAcc = new VMPairAccess(fstRemoved.getFstReg(), FstOrSnd.SND, assignRegister.getSrcOrdstReg(),
          LoadStore.LOAD);
    }
    else {
      pairAcc = new VMPairAccess(fstRemoved.getFstReg(), FstOrSnd.SND, assignRegister.getSrcOrdstReg(),
          LoadStore.STORE);
    }

    VmCodesInfo ret = new VmCodesInfo(pairAcc);
    ret.insertFrontInstruction(transExp);

    return ret;
  }

  @Override
  public Information visitStat_WHILE(@NotNull Stat_WHILEContext ctx) {

    RegisterList reg_list = registerListParam.peek();

    int loopStatLabelNum = label_num++;

    int condLabelNum = label_num++;

    VmCodesInfo jmpToWhile = new VmCodesInfo(new VMUncondJmp("L" + condLabelNum));

    VmCodesInfo loopStatLabel = new VmCodesInfo(new VMLabel("L" + loopStatLabelNum));

    enterNewScope();
    VmCodesInfo loopStat = (VmCodesInfo) visit(ctx.stat());
    exitScope();

    VmCodesInfo condLabel = new VmCodesInfo(new VMLabel("L" + condLabelNum));

    VmCodesInfo predicate = (VmCodesInfo) visit(ctx.expr());

    VmCodesInfo jmpToLoop = new VmCodesInfo(new VMCondJmp(reg_list.getFstReg(),
        "L" + loopStatLabelNum));

    jmpToWhile.insertBackInstruction(loopStatLabel);
    jmpToWhile.insertBackInstruction(loopStat);
    jmpToWhile.insertBackInstruction(condLabel);
    jmpToWhile.insertBackInstruction(predicate);
    jmpToWhile.insertBackInstruction(jmpToLoop);

    /*
    		B L0          jmpToWhile
    L1:               loopStatLabel
                      loopStat
    L0:               condLabel
		    MOV r4, #0    predicate example
		    CMP r4, #1    predicate example
		    BEQ L1        jmpToLoop
    */

    return jmpToWhile;
  }

  @Override
  public Information visitStat_SEMICOLON(@NotNull Stat_SEMICOLONContext ctx) {

    VmCodesInfo firstExprInfor = (VmCodesInfo) visit(ctx.stat(0));

    VmCodesInfo secondExprInfo = (VmCodesInfo) visit(ctx.stat(1));

    secondExprInfo.insertFrontInstruction(firstExprInfor);

    return secondExprInfo;
  }

  @Override
  public Information visitStat_BEGIN(@NotNull Stat_BEGINContext ctx) {

    enterNewScope();

    VmCodesInfo ret = (VmCodesInfo) visit(ctx.stat());

    exitScope();
    return ret;
  }

  @Override
  public Information visitAssignR_array(@NotNull AssignR_arrayContext ctx) {
    operation = LoadStore.LOAD;
    assignRegister = new AssignRegister(registerListParam.peek().getFstReg());
    return ctx.array_liter().accept(this);
  }

  @Override
  public Information visitProg(@NotNull ProgContext ctx) {
    List<FuncContext> funcs = ctx.func();

    if (funcs != null) {

      for (FuncContext func : funcs) {
        String name = func.IDENT().getText();
        List<ParamContext> args = func.param_list() == null ? new ArrayList<>() : func.param_list().param();
        ArrayList<Type> argTypes = new ArrayList<>();
        for (ParamContext arg : args) {
          argTypes.add((Type) arg.wacc_type().accept(typeChecker));
        }
        declaredFuncs.put("f_" + name, argTypes);
        this.funcs.add("f_" + name);
      }
      
      for (FuncContext func : funcs) {
        enterNewScope();
        func.accept(this);
        exitScope();
        exitFunc();
      }
    }

    // traverse classes
    List<Wacc_classContext> classes = ctx.wacc_class() == null ? new ArrayList<>() : ctx.wacc_class();
    declaredClassNames.add("");
    VMClassNull classZero = VMClassNull.getNullObject();
    declaredClass.add(classZero);
    this.vmProgram.addClasses(classZero);
    for (Wacc_classContext waccClass : classes) {
      enterNewScope();
      waccClass.accept(this);
      exitScope();
    }
    // main
    VMFunction main = new VMFunction("main", new ArrayList<Type>(),
        new ArrayList<Type>(), new VMFuncDeclares(), new ArrayList<VMClass>());

    currFun = main;

    VmCodesInfo mainBody = (VmCodesInfo) ctx.stat().accept(this);

    VMFunction mainToAdd = new VMFunction("main", currFunArgsT, currFunVarsT,
        buildFuncDeclares(), declaredClass);//todo add ArrayList<VMClass>

    VMCode load0 = new VMAssign(0, 0, new IntType());
    VMCode retn = new VMRetn(0);
    mainBody.insertBackInstruction(new VmCodesInfo(load0));
    mainBody.insertBackInstruction(new VmCodesInfo(retn));


    mainToAdd.addInstructions(mainBody);

    vmProgram.addFunction(mainToAdd);
    return null;
  }

  private VMFuncDeclares buildFuncDeclares() {
    VMFuncDeclares funcDeclares = new VMFuncDeclares();
    for (String func : funcs) {
      funcDeclares.insertFuncDeclares(new VMFuncArguments(declaredFuncs.get(func)), func);
    }
    return funcDeclares;
  }

  @Override
  public Information visitArg_list(@NotNull Arg_listContext ctx) {
    return null;
  }

  @Override
  public Information visitParam_list(@NotNull Param_listContext ctx) {
    List<ParamContext> params = ctx.param();

    if (params == null) {
      return null;
    }

    for (ParamContext param : params) {
      Type t = (Type) param.accept(typeChecker);
      currFun.addArg(t);
      String name = param.IDENT().getText();
      typeSt.addSymbol(name, t);
    }

    return null;
  }

  @Override
  public Information visitExpr_int(@NotNull Expr_intContext ctx) {
    return visit(ctx.int_liter());
  }

  @Override
  public Information visitStat_READ(@NotNull Stat_READContext ctx) {

    RegisterList rl = registerListParam.peek();
    RegisterList fstRemoved = rl.removeFstReg();

    Information readType = ctx.assign_lhs().accept(typeChecker);//a[0]

    ReadType type = readType instanceof IntType ? ReadType.INT : ReadType.CHAR;

    VMRead vmRead = new VMRead(rl.getFstReg(), type);
    assignRegister = new AssignRegister(rl.getFstReg());

    registerListParam.push(fstRemoved);
    VmCodesInfo transLhs = (VmCodesInfo) ctx.assign_lhs().accept(this);
    registerListParam.pop();

    VmCodesInfo ret = new VmCodesInfo(vmRead);
    ret.insertBackInstruction(transLhs);

    return ret;
  }

  @Override
  public Information visitFunc(@NotNull FuncContext ctx) {
    VMFunction func = new VMFunction(ctx.IDENT().getText(), new ArrayList<>(), new ArrayList<>(),
        new VMFuncDeclares(), new ArrayList<VMClass>());

    currFun = func;

    List<ParamContext> params = ctx.param_list() == null ? new ArrayList<>() : ctx.param_list().param();

    for (ParamContext param : params) {
      Type t = (Type) param.wacc_type().accept(typeChecker);
      String name = param.IDENT().getText();
      typeSt.addSymbol(name, t);
      currFunArgs.add(name);
      currFunArgsT.add(t);
      currFun.addArg(t);
    }

    VmCodesInfo funcBody = (VmCodesInfo) ctx.stat().accept(this);
    if (ctx.param_list() != null) {
      ctx.param_list().accept(this);
    }

    VMFunction funcToAdd = new VMFunction("f_" + ctx.IDENT().getText(), currFunArgsT,
        currFunVarsT, buildFuncDeclares(), declaredClass);//todo add ArrayList<VMClass>

    funcToAdd.addInstructions(funcBody);

    vmProgram.addFunction(funcToAdd);

    return funcBody;
  }

  @Override
  public Information visitStat_lr_ASSIGN(@NotNull Stat_lr_ASSIGNContext ctx) {
    RegisterList registerList = registerListParam.peek();
    VmCodesInfo evalRhs = (VmCodesInfo) visit(ctx.assign_rhs());
    assignRegister = new AssignRegister(registerList.getFstReg());
    // pass register that stores rhs result to field, so visible to lhs visit.

    RegisterList fstRemoved = registerList.removeFstReg();
    registerListParam.push(fstRemoved);
    VmCodesInfo evalLhs = (VmCodesInfo) ctx.assign_lhs().accept(this);
    registerListParam.pop();

    VmCodesInfo ret = new VmCodesInfo();
    ret.insertBackInstruction(evalRhs);
    ret.insertBackInstruction(evalLhs);

    return ret;
  }

  @Override
  public Information visitStat_EXIT(@NotNull Stat_EXITContext ctx) {

    RegisterList rl = registerListParam.peek();

    VmCodesInfo transExpr = (VmCodesInfo) ctx.expr().accept(this);

    VmCodesInfo ret = new VmCodesInfo(new VMExit(rl.getFstReg()));

    ret.insertFrontInstruction(transExpr);

    return ret;
  }

  @Override
  public Information visitAssignL_IDENT(@NotNull AssignL_IDENTContext ctx) {

    String varName = ctx.IDENT().getText();

    VMLoadStore store;

    if (currFunArgs.contains(varName)) {
      store = new VMLoadStore(assignRegister.getSrcOrdstReg(), getArgIdx(varName), LoadStore.STORE, VarOrArg.ARG);
    }
    else {

      store = new VMLoadStore(assignRegister.getSrcOrdstReg(), currFunVars.lookForAll(varName),
          LoadStore.STORE, VarOrArg.VAR);
    }
    return new VmCodesInfo(store);
  }
}
