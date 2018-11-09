// Generated from ./BasicParser.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BasicParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BasicParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BasicParser#array_elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_elem(@NotNull BasicParser.Array_elemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignR_NEWPAIR}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignR_NEWPAIR(@NotNull BasicParser.AssignR_NEWPAIRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_IF}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_IF(@NotNull BasicParser.Stat_IFContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignL_array_elem}
	 * labeled alternative in {@link BasicParser#assign_lhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignL_array_elem(@NotNull BasicParser.AssignL_array_elemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intSign_PLUS}
	 * labeled alternative in {@link BasicParser#int_sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntSign_PLUS(@NotNull BasicParser.IntSign_PLUSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intSign_MINUS}
	 * labeled alternative in {@link BasicParser#int_sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntSign_MINUS(@NotNull BasicParser.IntSign_MINUSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_unaryOper}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_unaryOper(@NotNull BasicParser.Expr_unaryOperContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pElem_PAIRTYPE}
	 * labeled alternative in {@link BasicParser#pair_elem_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPElem_PAIRTYPE(@NotNull BasicParser.PElem_PAIRTYPEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_ident}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_ident(@NotNull BasicParser.Expr_identContext ctx);
	/**
	 * Visit a parse tree produced by the {@code epxr_char}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEpxr_char(@NotNull BasicParser.Epxr_charContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignL_pair_elem}
	 * labeled alternative in {@link BasicParser#assign_lhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignL_pair_elem(@NotNull BasicParser.AssignL_pair_elemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseT_BOOLTYPE}
	 * labeled alternative in {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseT_BOOLTYPE(@NotNull BasicParser.BaseT_BOOLTYPEContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#new_object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNew_object(@NotNull BasicParser.New_objectContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#char_liter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChar_liter(@NotNull BasicParser.Char_literContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_arithMulDivMod}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_arithMulDivMod(@NotNull BasicParser.Expr_arithMulDivModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code encaps_PUBLIC}
	 * labeled alternative in {@link BasicParser#encaps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEncaps_PUBLIC(@NotNull BasicParser.Encaps_PUBLICContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_variable_ASSIGN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_variable_ASSIGN(@NotNull BasicParser.Stat_variable_ASSIGNContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unOp_sign}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnOp_sign(@NotNull BasicParser.UnOp_signContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOp_GTE}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp_GTE(@NotNull BasicParser.BinOp_GTEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pairElem_FST}
	 * labeled alternative in {@link BasicParser#pair_elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElem_FST(@NotNull BasicParser.PairElem_FSTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_delete}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_delete(@NotNull BasicParser.Stat_deleteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code waccT_array}
	 * labeled alternative in {@link BasicParser#wacc_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWaccT_array(@NotNull BasicParser.WaccT_arrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#array_liter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_liter(@NotNull BasicParser.Array_literContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_FREE}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_FREE(@NotNull BasicParser.Stat_FREEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code waccT_class_type}
	 * labeled alternative in {@link BasicParser#wacc_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWaccT_class_type(@NotNull BasicParser.WaccT_class_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_RETURN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_RETURN(@NotNull BasicParser.Stat_RETURNContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_field}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_field(@NotNull BasicParser.Expr_fieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#class_destr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_destr(@NotNull BasicParser.Class_destrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignR_CALL}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignR_CALL(@NotNull BasicParser.AssignR_CALLContext ctx);
	/**
	 * Visit a parse tree produced by the {@code waccT_pair_type}
	 * labeled alternative in {@link BasicParser#wacc_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWaccT_pair_type(@NotNull BasicParser.WaccT_pair_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOo_OR}
	 * labeled alternative in {@link BasicParser#logicOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOo_OR(@NotNull BasicParser.BinOo_ORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_PRINTLN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_PRINTLN(@NotNull BasicParser.Stat_PRINTLNContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOp_LT}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp_LT(@NotNull BasicParser.BinOp_LTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignR_pair_elem}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignR_pair_elem(@NotNull BasicParser.AssignR_pair_elemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOp_PLUS}
	 * labeled alternative in {@link BasicParser#arithMinusPlus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp_PLUS(@NotNull BasicParser.BinOp_PLUSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_arithMinusPlus}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_arithMinusPlus(@NotNull BasicParser.Expr_arithMinusPlusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseT_STRINGTYPE}
	 * labeled alternative in {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseT_STRINGTYPE(@NotNull BasicParser.BaseT_STRINGTYPEContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#int_liter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt_liter(@NotNull BasicParser.Int_literContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#wacc_class}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWacc_class(@NotNull BasicParser.Wacc_classContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#field_ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_ident(@NotNull BasicParser.Field_identContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseT_INTTYPE}
	 * labeled alternative in {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseT_INTTYPE(@NotNull BasicParser.BaseT_INTTYPEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOp_DIV}
	 * labeled alternative in {@link BasicParser#arithMulDivMod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp_DIV(@NotNull BasicParser.BinOp_DIVContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_BEGIN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_BEGIN(@NotNull BasicParser.Stat_BEGINContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignR_array}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignR_array(@NotNull BasicParser.AssignR_arrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOp_MINUS}
	 * labeled alternative in {@link BasicParser#arithMinusPlus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp_MINUS(@NotNull BasicParser.BinOp_MINUSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code encaps_PROTECTED}
	 * labeled alternative in {@link BasicParser#encaps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEncaps_PROTECTED(@NotNull BasicParser.Encaps_PROTECTEDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code class_body_constr}
	 * labeled alternative in {@link BasicParser#class_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_body_constr(@NotNull BasicParser.Class_body_constrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unOp_LEN}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnOp_LEN(@NotNull BasicParser.UnOp_LENContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(@NotNull BasicParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_EXIT}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_EXIT(@NotNull BasicParser.Stat_EXITContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#str_liter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr_liter(@NotNull BasicParser.Str_literContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignL_IDENT}
	 * labeled alternative in {@link BasicParser#assign_lhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignL_IDENT(@NotNull BasicParser.AssignL_IDENTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_compare}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_compare(@NotNull BasicParser.Expr_compareContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#class_constr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_constr(@NotNull BasicParser.Class_constrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_object}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_object(@NotNull BasicParser.Expr_objectContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#class_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_func(@NotNull BasicParser.Class_funcContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignR_expr}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignR_expr(@NotNull BasicParser.AssignR_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignL_field_ident}
	 * labeled alternative in {@link BasicParser#assign_lhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignL_field_ident(@NotNull BasicParser.AssignL_field_identContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(@NotNull BasicParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOp_MUL}
	 * labeled alternative in {@link BasicParser#arithMulDivMod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp_MUL(@NotNull BasicParser.BinOp_MULContext ctx);
	/**
	 * Visit a parse tree produced by the {@code encaps_PRIVATE}
	 * labeled alternative in {@link BasicParser#encaps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEncaps_PRIVATE(@NotNull BasicParser.Encaps_PRIVATEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bool_TRUE}
	 * labeled alternative in {@link BasicParser#bool_liter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool_TRUE(@NotNull BasicParser.Bool_TRUEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_super}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_super(@NotNull BasicParser.Stat_superContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unOp_ORD}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnOp_ORD(@NotNull BasicParser.UnOp_ORDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOp_GT}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp_GT(@NotNull BasicParser.BinOp_GTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOp_NEQ}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp_NEQ(@NotNull BasicParser.BinOp_NEQContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_logicOper}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_logicOper(@NotNull BasicParser.Expr_logicOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#class_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_field(@NotNull BasicParser.Class_fieldContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_PRINT}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_PRINT(@NotNull BasicParser.Stat_PRINTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignR_CALL_member_func}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignR_CALL_member_func(@NotNull BasicParser.AssignR_CALL_member_funcContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unOp_NOT}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnOp_NOT(@NotNull BasicParser.UnOp_NOTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code class_body_destr}
	 * labeled alternative in {@link BasicParser#class_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_body_destr(@NotNull BasicParser.Class_body_destrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_str}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_str(@NotNull BasicParser.Expr_strContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_array_elem}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_array_elem(@NotNull BasicParser.Expr_array_elemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pElem_base_type}
	 * labeled alternative in {@link BasicParser#pair_elem_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPElem_base_type(@NotNull BasicParser.PElem_base_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code waccT_base_type}
	 * labeled alternative in {@link BasicParser#wacc_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWaccT_base_type(@NotNull BasicParser.WaccT_base_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code class_body_func}
	 * labeled alternative in {@link BasicParser#class_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_body_func(@NotNull BasicParser.Class_body_funcContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pElem_wacc_type}
	 * labeled alternative in {@link BasicParser#pair_elem_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPElem_wacc_type(@NotNull BasicParser.PElem_wacc_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pairElem_SND}
	 * labeled alternative in {@link BasicParser#pair_elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElem_SND(@NotNull BasicParser.PairElem_SNDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_expr}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_expr(@NotNull BasicParser.Expr_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOp_MOD}
	 * labeled alternative in {@link BasicParser#arithMulDivMod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp_MOD(@NotNull BasicParser.BinOp_MODContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_WHILE}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_WHILE(@NotNull BasicParser.Stat_WHILEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_bool}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_bool(@NotNull BasicParser.Expr_boolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unOp_CHR}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnOp_CHR(@NotNull BasicParser.UnOp_CHRContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#pair_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair_type(@NotNull BasicParser.Pair_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(@NotNull BasicParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_pair}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_pair(@NotNull BasicParser.Expr_pairContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_SKIP}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_SKIP(@NotNull BasicParser.Stat_SKIPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_SEMICOLON}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_SEMICOLON(@NotNull BasicParser.Stat_SEMICOLONContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOp_AND}
	 * labeled alternative in {@link BasicParser#logicOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp_AND(@NotNull BasicParser.BinOp_ANDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOp_LTE}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp_LTE(@NotNull BasicParser.BinOp_LTEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOp_EQ}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp_EQ(@NotNull BasicParser.BinOp_EQContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_this}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_this(@NotNull BasicParser.Expr_thisContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#pair_liter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair_liter(@NotNull BasicParser.Pair_literContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#arg_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg_list(@NotNull BasicParser.Arg_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#param_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_list(@NotNull BasicParser.Param_listContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_int}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_int(@NotNull BasicParser.Expr_intContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_READ}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_READ(@NotNull BasicParser.Stat_READContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseT_CHARTYPE}
	 * labeled alternative in {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseT_CHARTYPE(@NotNull BasicParser.BaseT_CHARTYPEContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(@NotNull BasicParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_lr_ASSIGN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_lr_ASSIGN(@NotNull BasicParser.Stat_lr_ASSIGNContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bool_FALS}
	 * labeled alternative in {@link BasicParser#bool_liter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool_FALS(@NotNull BasicParser.Bool_FALSContext ctx);
}