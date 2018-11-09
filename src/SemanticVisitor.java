import Information.ASTTypes.*;
import Information.ASTTypes.ClassType.Access;
import Information.Information;
import antlr.BasicParser.*;
import Information.OpInfo.*;
import Information.Stat.*;
import Information.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import antlr.BasicParserBaseVisitor;
import java.util.Map;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;


public class SemanticVisitor extends BasicParserBaseVisitor<Information> {

  private SymbolTable<Type> st;

  public Map<String, ClassType> getClassTable() {
    return classTable;
  }

  public void setClassTable(Map<String, ClassType> classTable) {
    this.classTable = classTable;
  }

  private Map<String, ClassType> classTable = new HashMap<>();
  private boolean isCheckingFuncs = true;
  private boolean isCheckingUnary = false;
  private boolean isCheckingClass = false;
  private boolean isCheckingDestructor = false;
  private boolean isCheckingConstructor = false;
  private ClassType currentClass;


  @Override
  public Information visitExpr_this(@NotNull Expr_thisContext ctx) {
    return currentClass;
  }

  @Override
  public Information visitAssignL_field_ident(@NotNull AssignL_field_identContext ctx) {
    return visit(ctx.field_ident());
  }

  @Override
  public Information visitEncaps_PRIVATE(@NotNull Encaps_PRIVATEContext ctx) {
    return null; // no need for visit since it can be accessed by getText() on all encaps.
  }

  @Override
  public Information visitNew_object(@NotNull New_objectContext ctx) {

    String objectName = ctx.IDENT().getText();

    if (!classTable.containsKey(objectName)) {
      error_exit(ctx, "Undeclared class", 200);
    }

    ClassType classType = classTable.get(objectName);

    if (ctx.arg_list() == null) {
      if (classType.contains(objectName)) {
        FuncType constr = (FuncType) classType.lookForObject(objectName);
        if (constr.getParaTypes().size() != 0) { // the class actually has a constructor with non-zero arguments.
          error_exit(ctx, "Unknown constructor for class " + objectName, 200);
        }
      }// else the class does not have a constructor -> go to default constructor
    }
    else {
      if (!classType.contains(objectName)) { //There is only default constructor in the target class;
        error_exit(ctx, "Unknown constructor for class " + objectName, 200);
      }
      else {
        FuncType constr = (FuncType) classType.lookForObject(objectName);
        if (!constr.isSameArgList((FuncType) visit(ctx.arg_list()))) { //the constructor mismatches the class constructor
          error_exit(ctx, "Unknown constructor for class " + objectName, 200);
        }
      }
    }
    return classTable.get(objectName);
  }

  @Override
  public Information visitStat_super(@NotNull Stat_superContext ctx) {

    if (!isCheckingConstructor) {
      error_exit(ctx, "super() can only be called inside constructor method", 200);
    }

    ClassType parent = currentClass.getParent();

    if (parent == null) {
      error_exit(ctx, "The class " + currentClass.getClassName()
              + " does not have parent class, so super method is illegal", 200);
    }

    String parentConstrName;

    FuncType constr = null;

    while (parent != null && constr == null) {
      // check whether the parent has the constructor, if not, go to ancestor. If constr is set then break;

      parentConstrName = parent.getClassName();

      if (parent.contains(parentConstrName)) { // parentConstr exists, allowing us to call super();

        if (parent.lookForEncaps(parentConstrName) != Access.PRIVATE) {//have access to super constr;

          constr = (FuncType) parent.lookForObject(parentConstrName);

        }
        else { //unable to access private constr in parent class

          error_exit(ctx, "Unable to access private constructor in parent class: "
                  + parentConstrName, 200);

        }
      }
      parent = parent.getParent();
    }

    if (constr == null) { //default constructor expecting super() without arg_list;
      if (ctx.arg_list() != null) {
        error_exit(ctx, "expecting default super method with no arguments", 200);
      }
    }
    else {
      if (!constr.isSameArgList((FuncType) visit(ctx.arg_list()))) { // arg_list mismatch;
        error_exit(ctx, "mismatching parameters when calling super method", 200);
      }
    }
    return new Stat(StatInfo.SUPER);
  }

  @Override
  public Information visitClass_body_destr(@NotNull Class_body_destrContext ctx) {
    return visit(ctx.class_destr());
  }

  @Override
  public Information visitClass_body_func(@NotNull Class_body_funcContext ctx) {
    return visit(ctx.class_func());
  }

  @Override
  public Information visitWacc_class(@NotNull Wacc_classContext ctx) {

    st = st.generateNew();
    isCheckingClass = true;

    String className = ctx.IDENT(0).getText();

    if (st.contain(className)) {
      error_exit(ctx, "Class name " + className + " already declared.", 200);
    }

    ClassType classType;

    if (ctx.IDENT().size() == 2) {
      String superClass = ctx.IDENT(1).getText();

      if (!classTable.containsKey(superClass)) {
        error_exit(ctx, "Unexpected class extending: " + superClass, 200);
      }

      ClassType parent = classTable.get(superClass);

      classType = new ClassType(className, parent);
    }
    else {
      classType = new ClassType(className);
    }

    //put the initialized class into classTable.
    classTable.put(className, classType);

    currentClass = classType;

    if (ctx.class_field() != null) {

      for (Class_fieldContext fieldInfo : ctx.class_field()) {

        String encapsString = fieldInfo.encaps().getText();

        String ident = fieldInfo.IDENT().getText();

        if (ident.equals(className)) {
          error_exit(ctx, "Field name is the same as class name: " + ident, 200);
        }

        Type type = (Type) visit(fieldInfo.wacc_type());

        if (classType.contains(ident)) {
          error_exit(ctx, "Multiple definition of field: " + ident, 200);
        }

        classType.addNewEncaps(ident, encapsString);

        classType.addNewType(ident, type);

        //st.addSymbol(ident, type);
      }
    }

    if (ctx.class_body() != null) {

      for (Class_bodyContext bodyInfo : ctx.class_body()) {
        visit(bodyInfo);
      }
    }

    if (classType.getParent() != null) {
      classType.inheritParent();
    }

    isCheckingClass = false;
    currentClass = null;

    st = st.restore();

    return classType;
  }

  @Override
  public Information visitField_ident(@NotNull Field_identContext ctx) {

    String headIdent = ctx.IDENT(0).getText();


    if (ctx.THIS() != null) {
      if (currentClass == null) { //illegal to use "this" when not visiting class;
        error_exit(ctx, "Unexpected \"this\" keyword", 200);
      }
    }
    else {
      if (!st.contain(headIdent)) {
        error_exit(ctx, "Unbounded variable " + headIdent, 200);
      }
    }

    if (ctx.IDENT().size() == 1) {
      if (st.lookForAll(headIdent) != null){
        return st.lookForAll(headIdent);
      } else {
        if (currentClass.lookForObject(headIdent) != null){
          return currentClass.lookForObject(headIdent);
        }
        error_exit(ctx, "Unbounded member found.", 200);
      }
    }

    ClassType headClassType;

    if (currentClass != null && ctx.THIS() != null) {
      headClassType = (ClassType) currentClass.lookForObject(headIdent);
    }
    else {
      headClassType = (ClassType) st.lookForAll(headIdent);
    }

    int index = 1;
    int subMembers = ctx.IDENT().size();

    while (index < subMembers) {
      String currentMember = ctx.IDENT(index).getText();

      if (!headClassType.contains(currentMember)) {
        error_exit(ctx, "Undeclared member " +
                currentMember + " in class " + headClassType.getClassName(), 200);
      }

      Access memberAccess = headClassType.lookForEncaps(currentMember);
      Type memberType = headClassType.lookForObject(currentMember);

      if (memberAccess == Access.PRIVATE && currentClass == null) {
        error_exit(ctx, "Private usage of "
                + currentMember + " in class " + headClassType.getClassName(), 200);
      }
      if (memberAccess == Access.PRIVATE &&
              !currentClass.getClassName().equals(headClassType.getClassName())) {
        error_exit(ctx, "Private usage of "
                + currentMember + " in class " + headClassType.getClassName(), 200);
      } else { // public and protected

        if (!(memberType instanceof ClassType)) {
          if (index + 1 < subMembers) { //current Object should not expect more dot members
            error_exit(ctx, "Illegal usage of \".\" after " + currentMember, 200);
          } else {
            return memberType;
          }
        }
        headClassType = (ClassType) memberType;
        index++;
      }
    }
    return headClassType;
  }

  @Override
  public Information visitEncaps_PROTECTED(@NotNull Encaps_PROTECTEDContext ctx) {
    return null;
  }

  @Override
  public Information visitClass_body_constr(@NotNull Class_body_constrContext ctx) {
    return visit(ctx.class_constr());
  }

  @Override
  public Information visitClass_constr(@NotNull Class_constrContext ctx) {

    if (currentClass.checkAlreadyHaveConstr()) {
      error_exit(ctx, "Constructor of " + currentClass.getClassName()
              + " already declared.", 200);
    }
    currentClass.setConstr();

    st = st.generateNew();

    String constrName = ctx.IDENT().getText();

    if (!constrName.equals(currentClass.getClassName())) {
      error_exit(ctx, "Illegal constructor name: " + constrName, 200);
    }

    currentClass.addNewEncaps(constrName, ctx.encaps().getText());

    ParamList param_list = new ParamList(new ArrayList<>());

    if (ctx.param_list() != null){
      param_list = (ParamList) visit(ctx.param_list());
      for (int i = 0; i < param_list.size(); i++){
        Type type = param_list.getParam(i);
        st.addSymbol(type.getVarName(), type);
      }
    }
    isCheckingConstructor = true;
    visit(ctx.stat());
    isCheckingConstructor = false;

    st = st.restore();
    currentClass.addNewType(constrName, new FuncType(constrName, currentClass, param_list));
    return null;
  }

  @Override
  public Information visitExpr_object(@NotNull Expr_objectContext ctx) {
    return visit(ctx.new_object());
  }

  @Override
  public Information visitClass_func(@NotNull Class_funcContext ctx) {
    String funcName = ctx.func().IDENT().getText();

    if (currentClass.getClassName().equals(funcName)) {
      error_exit(ctx, "Function name " + funcName + " clashes with class name.", 200);
    }

    if (currentClass.contains(funcName)) {
      error_exit(ctx, "Function name " + funcName + " already declared in this class.", 200);
    }

    if (st.contain(funcName)) {
      error_exit(ctx, "Function name " + funcName + " already declared in main.", 200);
    }

    currentClass.addNewEncaps(funcName, ctx.encaps().getText());

    Type expectedReturnType = (Type) visit(ctx.func().wacc_type());

    st = st.generateNew();

    ParamList param_list = new ParamList(new ArrayList<>());

    if (ctx.func().param_list() != null){
      param_list = (ParamList) visit(ctx.func().param_list());
      for (int i = 0; i < param_list.size(); i++){
        Type type = param_list.getParam(i);
        st.addSymbol(type.getVarName(), type);
      }
    }
    Stat finalStat = (Stat) visit(ctx.func().stat());

    if (!(finalStat.getStat() == StatInfo.RETURN || finalStat.getStat() == StatInfo.EXIT)){
      error_exit(ctx, "Missing Return statement.", 100);
    }

    if (!(finalStat.getType()).isSameType(expectedReturnType)
            && finalStat.getStat() == StatInfo.RETURN) {
      error_exit(ctx, "Type mismatch found.", 200);
      return null;
    }

    if (!(finalStat.getType()).isSameType(new IntType())
            && finalStat.getStat() == StatInfo.EXIT){
      error_exit(ctx, "Incompatible type found.", 200);
    }

    st = st.restore();
    currentClass.addNewType(funcName, new FuncType(funcName, expectedReturnType, param_list));
    return null;
  }

  @Override
  public Information visitEncaps_PUBLIC(@NotNull Encaps_PUBLICContext ctx) {
    return null;
  }

  @Override
  public Information visitClass_field(@NotNull Class_fieldContext ctx) {
    return null;
  }

  @Override
  public Information visitStat_delete(@NotNull Stat_deleteContext ctx) {

    String objectToBeRemoved = ctx.IDENT().getText();

    if (!st.contain(objectToBeRemoved)) {
      error_exit(ctx, "Deleting target " + objectToBeRemoved + " does not exist", 200);
    }

    Type object = st.lookForAll(objectToBeRemoved);

    if (!(object instanceof ClassType)) {
      error_exit(ctx, "\'Delete\' can only be applied on class instance", 200);
    }

    ClassType srcClass = (ClassType) object;

    Access destrAccess = srcClass.lookForEncaps("~" + srcClass.getClassName());

    if (destrAccess != null && destrAccess == Access.PRIVATE) {
      //private destructor in either parent or its own class
      error_exit(ctx, "Destructor for class " +
              srcClass.getClassName() + " "
              + "is private, so calling delete method is illegal." , 200);
    }

    st.remove(objectToBeRemoved);

    return new Stat(StatInfo.DELETE);
  }

  @Override
  public Information visitWaccT_class_type(@NotNull WaccT_class_typeContext ctx) {
    String declaredClass = ctx.IDENT().getText();
    if (!classTable.containsKey(declaredClass)) {
      error_exit(ctx, "Unknown class declared " + declaredClass, 200);
    }
    return classTable.get(declaredClass);
  }

  @Override
  public Information visitExpr_field(@NotNull Expr_fieldContext ctx) {
    return visit(ctx.field_ident());
  }

  @Override
  public Information visitClass_destr(@NotNull Class_destrContext ctx) {

    if (currentClass.checkAlreadyHaveDestr()) {
      error_exit(ctx, "Destructor of " + currentClass.getClassName()
              + " already declared.", 200);
    }
    currentClass.setDestr();

    st = st.generateNew();

    String destructorName = "~" + ctx.IDENT().getText();

    if (!destructorName.equals("~" + currentClass.getClassName())) {
      error_exit(ctx, "Illegal Destructor name: " + destructorName, 200);
    }

    currentClass.addNewEncaps(destructorName, ctx.encaps().getText());

    isCheckingDestructor = true;
    visit(ctx.stat());
    isCheckingDestructor = false;

    st = st.restore();
    currentClass.addNewType(destructorName, new FuncType(destructorName, new BoolType(true),
            new ParamList(new ArrayList<>())));
    return null;
  }

  public void updateSt(SymbolTable<Type> s){
    st = s;
  }

  @Override
  public Information visitAssignR_CALL_member_func(@NotNull AssignR_CALL_member_funcContext ctx) {
    FuncType calledType = null;

    try {
      calledType = (FuncType) visit(ctx.field_ident());
    } catch (ClassCastException e) {
      error_exit(ctx, ctx.field_ident().getText() + " is not a function to be called",
              200);
    }

    FuncType argList;
    if (ctx.arg_list() != null) {
      argList = (FuncType) visit(ctx.arg_list());
    } else {
      argList = new FuncType(null, null, new ParamList(new ArrayList<>()));
    }

    if (!argList.isSameArgList(calledType)) {
      error_exit(ctx, ctx.field_ident().getText() + " has unmatched argument list",
              200);
    }

    return calledType.getReturnType();
  }

  @Override
  public Information visitAssignR_NEWPAIR(@NotNull AssignR_NEWPAIRContext ctx) {
    List<ExprContext> exprs = ctx.expr();
    List<Type> checkedType = new ArrayList<>();

    for (int i = 0; i < exprs.size(); i++) {
      Type type = (Type) visit(exprs.get(i));
      checkedType.add(i, type);
    }
    return new PairType("", checkedType.get(0), checkedType.get(1));
  }

  @Override
  public Information visitAssignL_array_elem(@NotNull AssignL_array_elemContext ctx) {
    return visit(ctx.array_elem());
  }

  @Override
  public Information visitAssignR_expr(@NotNull AssignR_exprContext ctx) {
    return visit(ctx.expr());
  }


  @Override
  public Information visitIntSign_MINUS(@NotNull IntSign_MINUSContext ctx) {
    return null;
  }

  @Override
  public Information visitIntSign_PLUS(@NotNull IntSign_PLUSContext ctx) {
    return null;
  }

  @Override
  public Information visitMain(@NotNull MainContext ctx) {
    return visit(ctx.prog());
  }

  @Override
  public Information visitPElem_PAIRTYPE(@NotNull PElem_PAIRTYPEContext ctx) {
    return new PairType();
  }

  @Override
  public Information visitBinOp_MUL(@NotNull BinOp_MULContext ctx) {
    return new BinOpInfo(BinOpInfo.BinOp.MUL);
  }

  @Override
  public Information visitEpxr_char(@NotNull Epxr_charContext ctx) {
    return visit(ctx.char_liter());
  }

  @Override
  public Information visitAssignL_pair_elem(@NotNull AssignL_pair_elemContext ctx) {
    return visit(ctx.pair_elem());
  }

  @Override
  public Information visitBaseT_BOOLTYPE(@NotNull BaseT_BOOLTYPEContext ctx) {
    return new BoolType();
  }

  @Override
  public Information visitBool_TRUE(@NotNull Bool_TRUEContext ctx) {
    return new BoolType(true);
  }

  @Override
  public Information visitBool_FALS(@NotNull Bool_FALSContext ctx) {
    return new BoolType(false);
  }

  @Override
  public Information visitChar_liter(@NotNull Char_literContext ctx) {
    return new CharType("", ctx.CHAR().getText().charAt(0));
  }

  @Override
  public Information visitBinOp_GT(@NotNull BinOp_GTContext ctx) {
    return new BinOpInfo(BinOpInfo.BinOp.GT);
  }

  @Override
  public Information visitUnOp_ORD(@NotNull UnOp_ORDContext ctx) {
    return new UnOpInfo(UnOpInfo.UnOp.ORD);
  }

  @Override
  public Information visitBinOp_GTE(@NotNull BinOp_GTEContext ctx) {
    return new BinOpInfo(BinOpInfo.BinOp.GTE);
  }

  @Override
  public Information visitUnOp_sign(@NotNull UnOp_signContext ctx) {
    if (ctx.getText().equals("-")) {
      return new UnOpInfo(UnOpInfo.UnOp.NEG);
    }
    return new UnOpInfo(UnOpInfo.UnOp.PLUS);
  }

  @Override
  public Information visitBinOp_NEQ(@NotNull BinOp_NEQContext ctx) {
    return new BinOpInfo(BinOpInfo.BinOp.NEQ);
  }

  @Override
  public Information visitUnOp_NOT(@NotNull UnOp_NOTContext ctx) {
    return new UnOpInfo(UnOpInfo.UnOp.NOT);
  }

  @Override
  public Information visitExpr_str(@NotNull Expr_strContext ctx) {
    return visit(ctx.str_liter());
  }

  @Override
  public Information visitWaccT_pair_type(@NotNull WaccT_pair_typeContext ctx) {
    return visit(ctx.pair_type());
  }

  @Override
  public Information visitExpr_array_elem(@NotNull Expr_array_elemContext ctx) {
    return visit(ctx.array_elem());
  }

  @Override
  public Information visitPElem_base_type(@NotNull PElem_base_typeContext ctx) {
    return visit(ctx.base_type());
  }

  @Override
  public Information visitBinOo_OR(@NotNull BinOo_ORContext ctx) {
    return new BinOpInfo(BinOpInfo.BinOp.OR);
  }

  @Override
  public Information visitBinOp_LT(@NotNull BinOp_LTContext ctx) {
    return new BinOpInfo(BinOpInfo.BinOp.LT);
  }

  @Override
  public Information visitWaccT_base_type(@NotNull WaccT_base_typeContext ctx) {
    return visit(ctx.base_type());
  }

  @Override
  public Information visitExpr_expr(@NotNull Expr_exprContext ctx) {
    return visit(ctx.expr());
  }

  @Override
  public Information visitAssignR_pair_elem(@NotNull AssignR_pair_elemContext ctx) {
    return visit(ctx.pair_elem());
  }

  @Override
  public Information visitBinOp_PLUS(@NotNull BinOp_PLUSContext ctx) {
    return new BinOpInfo(BinOpInfo.BinOp.ADD);
  }

  @Override
  public Information visitBinOp_MOD(@NotNull BinOp_MODContext ctx) {
    return new BinOpInfo(BinOpInfo.BinOp.MOD);
  }

  @Override
  public Information visitExpr_bool(@NotNull Expr_boolContext ctx) {
    return visit(ctx.bool_liter());
  }

  @Override
  public Information visitBaseT_STRINGTYPE(@NotNull BaseT_STRINGTYPEContext ctx) {
    return new StringType();
  }

  @Override
  public Information visitInt_liter(@NotNull Int_literContext ctx) {
    String number = ctx.getText();
    return new IntType(number);
  }

  @Override
  public Information visitUnOp_CHR(@NotNull UnOp_CHRContext ctx) {
    return new UnOpInfo(UnOpInfo.UnOp.CHR);
  }

  @Override
  public Information visitPair_type(@NotNull Pair_typeContext ctx) {
    Type first = (Type) visit(ctx.pair_elem_type(0));
    Type second = (Type) visit(ctx.pair_elem_type(1));
    return new PairType("", first, second);
  }

  @Override
  public Information visitParam(@NotNull ParamContext ctx) {
    Type type = (Type) visit(ctx.wacc_type());
    type.setVarName(ctx.IDENT().getText());
    return type;
  }

  @Override
  public Information visitBaseT_INTTYPE(@NotNull BaseT_INTTYPEContext ctx) {
    return new IntType();
  }

  @Override
  public Information visitBinOp_DIV(@NotNull BinOp_DIVContext ctx) {
    return new BinOpInfo(BinOpInfo.BinOp.DIV);
  }

  @Override
  public Information visitExpr_pair(@NotNull Expr_pairContext ctx) {
    return visit(ctx.pair_liter());
  }

  @Override
  public Information visitStat_SKIP(@NotNull Stat_SKIPContext ctx) {
    return new Stat(Stat.StatInfo.SKIP);
  }

  @Override
  public Information visitAssignR_array(@NotNull AssignR_arrayContext ctx) {
    return visit(ctx.array_liter());
  }

  @Override
  public Information visitBinOp_MINUS(@NotNull BinOp_MINUSContext ctx) {
    return new BinOpInfo(BinOpInfo.BinOp.SUB);
  }

  @Override
  public Information visitBinOp_AND(@NotNull BinOp_ANDContext ctx) {
    return new BinOpInfo(BinOpInfo.BinOp.AND);
  }

  @Override
  public Information visitBinOp_LTE(@NotNull BinOp_LTEContext ctx) {
    return new BinOpInfo(BinOpInfo.BinOp.LTE);
  }

  @Override
  public Information visitBinOp_EQ(@NotNull BinOp_EQContext ctx) {
    return new BinOpInfo(BinOpInfo.BinOp.EQ);
  }

  @Override
  public Information visitUnOp_LEN(@NotNull UnOp_LENContext ctx) {
    return new UnOpInfo(UnOpInfo.UnOp.LEN);
  }

  @Override
  public Information visitPair_liter(@NotNull Pair_literContext ctx) {
    return new PairType();
  }

  @Override
  public Information visitBaseT_CHARTYPE(@NotNull BaseT_CHARTYPEContext ctx) {
    return new CharType();
  }

  @Override
  public Information visitStr_liter(@NotNull Str_literContext ctx) {
    return new StringType("", ctx.STR().getText());
  }

  private static void printErrorLine(ParserRuleContext ctx, int exitCode) {
    String errorType = "";
    if (exitCode == 100) {
      errorType = "Syntactic";
    } else if (exitCode == 200) {
      errorType = "Semantic";
    } else {
      System.err.println("Unrecognized exit code, please check again.");
      System.exit(exitCode);
    }
    System.err.println(errorType + "error in line " + ctx.getStart().getLine() + ":");
  }

  public static void error_exit(ParserRuleContext ctx, String errorMsg, int exitCode) {
    printErrorLine(ctx, exitCode);
    System.err.println(errorMsg);
    System.exit(exitCode);
  }

  public SemanticVisitor(SymbolTable<Type> st) {
    super();
    this.st = st;
  }

  @Override
  public Information visitArray_elem(@NotNull Array_elemContext ctx) {
    List<ExprContext> exprs = ctx.expr();

    Type track = st.lookForAll(ctx.IDENT().getText());

    if (track == null){
      error_exit(ctx, "Unbounded variable found", 200);
    }
    for (ExprContext expr : exprs){
      if (!(visit(expr) instanceof IntType)){
        error_exit(ctx, "Incompatible type, expecting Int type.", 200);
      }
      ArrayType current = null;
      try{
        current = (ArrayType) track;
      } catch (ClassCastException e){
        error_exit(ctx, "Incompatible type, expecting Array type.", 200);
      }
      track = current.getElemType();
    }
    return track;
  }

  @Override
  public Information visitStat_IF(@NotNull Stat_IFContext ctx) {
    if (!(visit(ctx.expr()) instanceof BoolType)){
      error_exit(ctx, "Incompatible type, expecting Bool type.", 200);
    }

    st = st.generateNew();
    Stat returnStatFst = (Stat) visit(ctx.stat(0));
    st = st.restore();

    st = st.generateNew();
    Stat returnStatSnd = (Stat) visit(ctx.stat(1));
    st = st.restore();

    return returnStatFst.getStat() == StatInfo.RETURN ||
            returnStatFst.getStat() == StatInfo.EXIT ? returnStatSnd : returnStatFst;
  }


  @Override
  public Information visitExpr_unaryOper(@NotNull Expr_unaryOperContext ctx) {
    isCheckingUnary = true;
    String op = ctx.unaryOper().getText();
    ExprContext expr = ctx.expr();
    Type exprT = (Type) visit(expr);
    if (op.equals("len")){
      if (exprT instanceof ArrayType){
        isCheckingUnary = false;
        return new IntType();
      }
    }

    if (op.equals("ord")){
      if (exprT instanceof CharType){
        isCheckingUnary = false;
        return new IntType();
      }
    }

    if (op.equals("chr")){
      if (exprT instanceof IntType){
        isCheckingUnary = false;
        return new CharType();
      }
    }

    if (op.equals("!")){
      if (exprT instanceof BoolType){
        isCheckingUnary = false;
        return new BoolType();
      }
    }

    if (op.equals("-") || op.equals("+")) {
      if (exprT instanceof IntType) {
        String value = ((IntType) exprT).getValue();
        if (value.equals("")) {
          isCheckingUnary = false;
          return new IntType();
        }
        try {
          Integer.parseInt(op + value);
        } catch (NumberFormatException e){
          error_exit(ctx, "Integer overflow found", 100);
        }
        isCheckingUnary = false;
        return new IntType();
      } else {
        error_exit(ctx, "Operator " + op +
                " cannot be applied to type that is not Int or Char.", 100);
      }
    }
    error_exit(ctx, "Undefined unary operator found.", 200);

    isCheckingUnary = false;
    return null;
  }

  @Override
  public Information visitExpr_ident(@NotNull Expr_identContext ctx) {
    String key = ctx.IDENT().getText();
    if (st.lookForAll(key) != null){
      return st.lookForAll(key);
    }
    else {
      error_exit(ctx, "Unbounded variable " +
              key + " found at line " + ctx.start.getLine(), 200);
      return null;
    }
  }


  @Override
  public Information visitExpr_compare(@NotNull Expr_compareContext ctx) {
    Type type1 = (Type) visit(ctx.expr(0));
    Type type2 = (Type) visit(ctx.expr(1));
    BinOpInfo binOpInfo = (BinOpInfo) visit(ctx.compare());
    Type ret;

    switch (binOpInfo.binOpType) {
      case EQ:
      case NEQ:
      {
        if (!type1.isSameType(type2))
        {//int_int or char_char
          error_exit(ctx, "Operator " +
                  binOpInfo.binOptoString(binOpInfo.binOpType) +
                  " cannot be applied to type that is not Int or Char.", 200);
        }
        ret = new BoolType();
      }
      break;

      case GT:
      case LT:
      case GTE:
      case LTE:

      {
        IntType intType = new IntType();
        CharType charType = new CharType();

        if (!((intType.isSameType(type1) && intType.isSameType(type2))
                ||(charType.isSameType(type1) && charType.isSameType(type2))))
        {//int_int or char_char
          error_exit(ctx, "Operator " +
                  binOpInfo.binOptoString(binOpInfo.binOpType) +
                  " cannot be applied to type that is not Int or Char.", 200);
        }
        ret = new BoolType();
      }
      break;
      default:
        ret = null;//impossible
        break;
    }
    return ret;
  }

  @Override
  public Information visitExpr_arithMulDivMod(@NotNull Expr_arithMulDivModContext ctx) {
    Type type1 = (Type) visit(ctx.expr(0));
    Type type2 = (Type) visit(ctx.expr(1));
    BinOpInfo binOpInfo = (BinOpInfo) visit(ctx.arithMulDivMod());


    IntType intType = new IntType();
    if (!(intType.isSameType(type1) && intType.isSameType(type2)))
    {//one of the type is not int...
      error_exit(ctx, "Operator " +
              binOpInfo.binOptoString(binOpInfo.binOpType) +
              " cannot be applied to type that is not int!", 200);
    }
    return new IntType();
  }

  @Override
  public Information visitExpr_logicOper(@NotNull Expr_logicOperContext ctx) {
    Type type1 = (Type) visit(ctx.expr(0));
    Type type2 = (Type) visit(ctx.expr(1));
    BinOpInfo binOpInfo = (BinOpInfo) visit(ctx.logicOper());

    BoolType boolType = new BoolType();
    if (!(boolType.isSameType(type1) && boolType.isSameType(type2)))
    {
      //bool_bool
      error_exit(ctx, "Operator " +
              binOpInfo.binOptoString(binOpInfo.binOpType) +
              " cannot be applied to type that is not bool!", 200);
    }
    return new BoolType();
  }

  @Override
  public Information visitExpr_arithMinusPlus(@NotNull Expr_arithMinusPlusContext ctx) {
    Type type1 = (Type) visit(ctx.expr(0));
    Type type2 = (Type) visit(ctx.expr(1));
    BinOpInfo binOpInfo = (BinOpInfo) visit(ctx.arithMinusPlus());

    IntType intType = new IntType();
    if (!(intType.isSameType(type1) && intType.isSameType(type2)))
    {//one of the type is not int...
      error_exit(ctx, "Operator " +
              binOpInfo.binOptoString(binOpInfo.binOpType) +
              " cannot be applied to type that is not int!", 200);
    }
    return new IntType();
  }

  @Override
  public Information visitStat_variable_ASSIGN(@NotNull Stat_variable_ASSIGNContext ctx) {
    Type declaredType = (Type) visit(ctx.wacc_type());

    String name = ctx.IDENT().getText();

    if (st.contain(name)) {
      error_exit(ctx, "Variable declared before.", 200);
    }

    Type assignType = (Type) visit(ctx.assign_rhs());

    if (!declaredType.isSameType(assignType)){
      String lhsType = declaredType.getClass().getName();
      String rhsType = assignType.getClass().getName();
      if (assignType instanceof ClassType && declaredType instanceof ClassType) {
        lhsType = ((ClassType) declaredType).getClassName();
        rhsType = ((ClassType) assignType).getClassName();
      }
      error_exit(ctx, "Incompatible type found with declared : "
              + lhsType + " and assigned : "
              + rhsType, +  200);
    }

    if (assignType.isSameType(new PairType())) {
      st.addSymbol(name, declaredType);
    } else {
      st.addSymbol(name, assignType);
    }

    return new Stat(StatInfo.VAR_DECLARE);
  }

  @Override
  public Information visitPairElem_FST(@NotNull PairElem_FSTContext ctx) {
    PairType t = null;
    try {
      t = (PairType) visit(ctx.expr());
    }
    catch (ClassCastException e)//if not pair type
    {
      error_exit(ctx, "First element of the pair is not a legal pair-type.", 200);
    }
    return t.getFstType();
  }

  @Override
  public Information visitWaccT_array(@NotNull WaccT_arrayContext ctx) {
    Type t = (Type) visit(ctx.wacc_type());
    return new ArrayType("", t, -1);
  }

  @Override
  public Information visitArray_liter(@NotNull Array_literContext ctx) {
    List<Type> types = new LinkedList<>();
    List<ExprContext> exprs = ctx.expr();
    if (exprs.isEmpty())
    {
      return new ArrayType(null, null/*any type*/, 0);
    }
    for (ExprContext ec : exprs)
    {
      types.add((Type) visit(ec));
    }
    Iterator<Type> iter = types.iterator();
    Type type = iter.next();
    while (iter.hasNext())
    {
      if (!iter.next().isSameType(type))
      {
        error_exit(ctx, "Array element is not legal to fit in the array.", 200);
      }
    }//check all expr has the same type
    return new ArrayType(null, type, types.size());
  }

  @Override
  public Information visitStat_FREE(@NotNull Stat_FREEContext ctx) {
    Information refType = visit(ctx.expr());

    if (!(refType instanceof PairType || refType instanceof ArrayType)) {
      error_exit(ctx, "\'Free\' cannot be applied on non-reference type", 200);
    }

    return new Stat(StatInfo.FREE);
  }

  @Override
  public Information visitStat_PRINT(@NotNull Stat_PRINTContext ctx) {
    visit(ctx.expr());
    return new Stat(StatInfo.PRINT);
  }

  @Override
  public Information visitStat_RETURN(@NotNull Stat_RETURNContext ctx) {
    if (isCheckingConstructor || isCheckingDestructor) {
      error_exit(ctx, "Constructor/Destructor of " + currentClass.getClassName()
              + " has return statement in them.", 200);
    }
    return new Stat(StatInfo.RETURN, (Type) visit(ctx.expr()));
  }

  @Override
  public Information visitAssignR_CALL(@NotNull AssignR_CALLContext ctx) {
    FuncType type = null;
    try {
      type = (FuncType) st.lookForAll(ctx.IDENT().getText());
    }
    catch (ClassCastException e) { //cast to FuncType fail, so the ident is not a function!
      error_exit(ctx, ctx.IDENT().getText() + " is not a function", 200);
    }
    if (type == null) { //cannot find type
      error_exit(ctx, ctx.IDENT().getText() + " has not been defined", 200);
    }

    FuncType argListType;
    if (ctx.arg_list() != null) {
      argListType = (FuncType) visit(ctx.arg_list());
    } else {
      argListType = new FuncType(null, null, new ParamList(new ArrayList<>()));
    }

    if (!argListType.isSameArgList(type))
    {
      error_exit(ctx, ctx.IDENT().getText() + " has unmatched argument list",
              200);
    }
    return type.getReturnType();//no, cant have null pointer
  }

  @Override
  public Information visitStat_PRINTLN(@NotNull Stat_PRINTLNContext ctx) {
    visit(ctx.expr());
    return new Stat(StatInfo.PRINTLN);
  }

  @Override
  public Information visitPElem_wacc_type(@NotNull PElem_wacc_typeContext ctx) {
    return new ArrayType("", (Type) visit(ctx.wacc_type()), -1);
  }

  @Override
  public Information visitPairElem_SND(@NotNull PairElem_SNDContext ctx) {
    PairType t = null;
    try
    {
      t = (PairType) visit(ctx.expr());
    }
    catch (ClassCastException e)//if not pair type
    {
      error_exit(ctx, "Second element of the pair is not a legal pair-type", 200);
    }
    return t.getSndType();
  }

  @Override
  public Information visitStat_WHILE(@NotNull Stat_WHILEContext ctx) {
    st = st.generateNew();

    if (!(visit(ctx.expr()) instanceof BoolType)) {
      error_exit(ctx, "Incompatible type, expecting Bool type.", 200);
    }
    visit(ctx.stat());
    st = st.restore();
    return new Stat(StatInfo.WHILE);
  }

  @Override
  public Information visitStat_SEMICOLON(@NotNull Stat_SEMICOLONContext ctx) {
    Information info = visit(ctx.stat(0));
    StatInfo retStat = ((Stat) info).getStat();
    if (retStat == StatInfo.RETURN) {
      if (!isCheckingFuncs) {
        error_exit(ctx, "Early Return/Exit found.", 200);
      } else {
        error_exit(ctx, "Early Return/Exit found in function.", 100);
      }
    }
    return visit(ctx.stat(1));
  }

  @Override
  public Information visitStat_BEGIN(@NotNull Stat_BEGINContext ctx) {
    st = st.generateNew();
    visit(ctx.stat());
    Stat retStat = new Stat(StatInfo.BEGIN);
    st = st.restore();
    return retStat;
  }

  @Override
  public Information visitProg(@NotNull ProgContext ctx) {
    List<FuncContext> funcs = ctx.func();
    if (funcs != null){
      for (FuncContext func : funcs){
        String name = func.IDENT().getText();
        ParamList params;
        if (func.param_list() != null) {
          params = (ParamList) visit(func.param_list());
        }
        else {
          params = new ParamList(new ArrayList<>());
        }
        Type returnT = (Type) visit(func.wacc_type());
        if (st.contain(name)) {
          error_exit(ctx, "Function has been declared before.", 200);
        }
        st.addSymbol(name, new FuncType(name, returnT, params));
      }

      for (FuncContext func : funcs){
        visit(func);
      }
    }
    isCheckingFuncs = false;

    List<Wacc_classContext> classes = ctx.wacc_class();
    if (classes!= null) {
      for (Wacc_classContext wacc_classContext : classes) {
        ClassType classType = (ClassType) visit(wacc_classContext);
        classTable.put(classType.getClassName(), classType);
      }
    }

    st = st.generateNew();

    return visit(ctx.stat());
  }

  @Override
  public Information visitArg_list(@NotNull Arg_listContext ctx) {
    List<ExprContext> args = ctx.expr();
    FuncType ft = new FuncType(null, null, null);
    List<Type> paramTypes = ft.getParaTypes();
    for (ExprContext arg : args)
    {
      Type type = (Type) visit(arg);
      paramTypes.add(type);
    }
    return ft;
  }

  @Override
  public Information visitParam_list(@NotNull Param_listContext ctx) {

    List<Type> param_list = new ArrayList<>();
    for (int i = 0; i < ctx.param().size(); i++) {
      param_list.add((Type) visit(ctx.param(i)));
    }

    return new ParamList(param_list);
  }

  @Override
  public Information visitExpr_int(@NotNull Expr_intContext ctx) {
    if (!isCheckingUnary) {
      IntType retInt = (IntType) visit(ctx.int_liter());
      if (!retInt.getValue().equals("")) {
        try {
          Integer.parseInt(retInt.getValue());
        } catch (NumberFormatException e) {
          error_exit(ctx, "Integer overflow found.", 100);
        }
      }
      return new IntType();
    }
    return visit(ctx.int_liter());
  }

  @Override
  public Information visitStat_READ(@NotNull Stat_READContext ctx) {
    Information readType = visit(ctx.assign_lhs());

    if (!(readType instanceof CharType || readType instanceof IntType)) {
      error_exit(ctx, "\'Read\' can only be applied on a program variable, "
              + "an array-elem or a pair element", 200);
    }
    return new Stat(StatInfo.READ);
  }

  @Override
  public Information visitFunc(@NotNull FuncContext ctx) {
    Type expectedReturnType = (Type) visit(ctx.wacc_type());
    String funcName = ctx.IDENT().getText();

    st = st.generateNew();

    ParamList param_list = new ParamList(new ArrayList<>());

    if (ctx.param_list() != null){
      param_list = (ParamList) visit(ctx.param_list());
      for (int i = 0; i < param_list.size(); i++){
        Type type = param_list.getParam(i);
        st.addSymbol(type.getVarName(), type);
      }
    }
    Stat finalStat = (Stat) visit(ctx.stat());

    if (!(finalStat.getStat() == StatInfo.RETURN || finalStat.getStat() == StatInfo.EXIT)){
      error_exit(ctx, "Missing Return statement.", 100);
    }

    if (!(finalStat.getType()).isSameType(expectedReturnType)
            && finalStat.getStat() == StatInfo.RETURN) {
      error_exit(ctx, "Type mismatch found.", 200);
      return null;
    }

    if (!(finalStat.getType()).isSameType(new IntType())
            && finalStat.getStat() == StatInfo.EXIT){
      error_exit(ctx, "Incompatible type found.", 200);
    }

    st = st.restore();
    st.addSymbol(funcName, new FuncType(funcName, expectedReturnType, param_list));
    return null;
  }

  @Override
  public Information visitStat_lr_ASSIGN(@NotNull Stat_lr_ASSIGNContext ctx) {
    Information lhs = visit(ctx.assign_lhs());
    Information rhs = visit(ctx.assign_rhs());

    if (lhs == null || rhs == null){
      return new Stat(StatInfo.ASSIGN); //Type is uncertain when compiling
    }

    if (!((Type)lhs).isSameType((Type)rhs)){
      error_exit(ctx, "Incompatible type found.", 200);
    }
    return new Stat(StatInfo.ASSIGN);
  }

  @Override
  public Information visitStat_EXIT(@NotNull Stat_EXITContext ctx) {
    Information code = visit(ctx.expr());
    if (isCheckingConstructor || isCheckingDestructor) {
      error_exit(ctx, "Constructor/Destructor of " + currentClass.getClassName()
              + " has exit statement in them.", 200);
    }
    if (!(code instanceof IntType) ) {
      error_exit(ctx, "Incompatible type found.", 200);
    }
    return new Stat(StatInfo.EXIT, (Type) code);
  }

  @Override
  public Information visitAssignL_IDENT(@NotNull AssignL_IDENTContext ctx) {
    String ident = ctx.IDENT().getText();
    Type info = st.lookForAll(ident);
    if (info == null) {
      error_exit(ctx, "Identifier " + ident
              + " not found in previous context.", 200);
    }
    return info;
  }
}
