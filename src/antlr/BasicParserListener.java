// Generated from ./BasicParser.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BasicParser}.
 */
public interface BasicParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BasicParser#array_elem}.
	 * @param ctx the parse tree
	 */
	void enterArray_elem(@NotNull BasicParser.Array_elemContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#array_elem}.
	 * @param ctx the parse tree
	 */
	void exitArray_elem(@NotNull BasicParser.Array_elemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignR_NEWPAIR}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void enterAssignR_NEWPAIR(@NotNull BasicParser.AssignR_NEWPAIRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignR_NEWPAIR}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void exitAssignR_NEWPAIR(@NotNull BasicParser.AssignR_NEWPAIRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_IF}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_IF(@NotNull BasicParser.Stat_IFContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_IF}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_IF(@NotNull BasicParser.Stat_IFContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignL_array_elem}
	 * labeled alternative in {@link BasicParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void enterAssignL_array_elem(@NotNull BasicParser.AssignL_array_elemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignL_array_elem}
	 * labeled alternative in {@link BasicParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void exitAssignL_array_elem(@NotNull BasicParser.AssignL_array_elemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intSign_PLUS}
	 * labeled alternative in {@link BasicParser#int_sign}.
	 * @param ctx the parse tree
	 */
	void enterIntSign_PLUS(@NotNull BasicParser.IntSign_PLUSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intSign_PLUS}
	 * labeled alternative in {@link BasicParser#int_sign}.
	 * @param ctx the parse tree
	 */
	void exitIntSign_PLUS(@NotNull BasicParser.IntSign_PLUSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intSign_MINUS}
	 * labeled alternative in {@link BasicParser#int_sign}.
	 * @param ctx the parse tree
	 */
	void enterIntSign_MINUS(@NotNull BasicParser.IntSign_MINUSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intSign_MINUS}
	 * labeled alternative in {@link BasicParser#int_sign}.
	 * @param ctx the parse tree
	 */
	void exitIntSign_MINUS(@NotNull BasicParser.IntSign_MINUSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_unaryOper}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_unaryOper(@NotNull BasicParser.Expr_unaryOperContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_unaryOper}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_unaryOper(@NotNull BasicParser.Expr_unaryOperContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pElem_PAIRTYPE}
	 * labeled alternative in {@link BasicParser#pair_elem_type}.
	 * @param ctx the parse tree
	 */
	void enterPElem_PAIRTYPE(@NotNull BasicParser.PElem_PAIRTYPEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pElem_PAIRTYPE}
	 * labeled alternative in {@link BasicParser#pair_elem_type}.
	 * @param ctx the parse tree
	 */
	void exitPElem_PAIRTYPE(@NotNull BasicParser.PElem_PAIRTYPEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_ident}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_ident(@NotNull BasicParser.Expr_identContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_ident}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_ident(@NotNull BasicParser.Expr_identContext ctx);
	/**
	 * Enter a parse tree produced by the {@code epxr_char}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEpxr_char(@NotNull BasicParser.Epxr_charContext ctx);
	/**
	 * Exit a parse tree produced by the {@code epxr_char}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEpxr_char(@NotNull BasicParser.Epxr_charContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignL_pair_elem}
	 * labeled alternative in {@link BasicParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void enterAssignL_pair_elem(@NotNull BasicParser.AssignL_pair_elemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignL_pair_elem}
	 * labeled alternative in {@link BasicParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void exitAssignL_pair_elem(@NotNull BasicParser.AssignL_pair_elemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code baseT_BOOLTYPE}
	 * labeled alternative in {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 */
	void enterBaseT_BOOLTYPE(@NotNull BasicParser.BaseT_BOOLTYPEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code baseT_BOOLTYPE}
	 * labeled alternative in {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 */
	void exitBaseT_BOOLTYPE(@NotNull BasicParser.BaseT_BOOLTYPEContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#new_object}.
	 * @param ctx the parse tree
	 */
	void enterNew_object(@NotNull BasicParser.New_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#new_object}.
	 * @param ctx the parse tree
	 */
	void exitNew_object(@NotNull BasicParser.New_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#char_liter}.
	 * @param ctx the parse tree
	 */
	void enterChar_liter(@NotNull BasicParser.Char_literContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#char_liter}.
	 * @param ctx the parse tree
	 */
	void exitChar_liter(@NotNull BasicParser.Char_literContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_arithMulDivMod}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_arithMulDivMod(@NotNull BasicParser.Expr_arithMulDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_arithMulDivMod}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_arithMulDivMod(@NotNull BasicParser.Expr_arithMulDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code encaps_PUBLIC}
	 * labeled alternative in {@link BasicParser#encaps}.
	 * @param ctx the parse tree
	 */
	void enterEncaps_PUBLIC(@NotNull BasicParser.Encaps_PUBLICContext ctx);
	/**
	 * Exit a parse tree produced by the {@code encaps_PUBLIC}
	 * labeled alternative in {@link BasicParser#encaps}.
	 * @param ctx the parse tree
	 */
	void exitEncaps_PUBLIC(@NotNull BasicParser.Encaps_PUBLICContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_variable_ASSIGN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_variable_ASSIGN(@NotNull BasicParser.Stat_variable_ASSIGNContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_variable_ASSIGN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_variable_ASSIGN(@NotNull BasicParser.Stat_variable_ASSIGNContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unOp_sign}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 */
	void enterUnOp_sign(@NotNull BasicParser.UnOp_signContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unOp_sign}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 */
	void exitUnOp_sign(@NotNull BasicParser.UnOp_signContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOp_GTE}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 */
	void enterBinOp_GTE(@NotNull BasicParser.BinOp_GTEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOp_GTE}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 */
	void exitBinOp_GTE(@NotNull BasicParser.BinOp_GTEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pairElem_FST}
	 * labeled alternative in {@link BasicParser#pair_elem}.
	 * @param ctx the parse tree
	 */
	void enterPairElem_FST(@NotNull BasicParser.PairElem_FSTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pairElem_FST}
	 * labeled alternative in {@link BasicParser#pair_elem}.
	 * @param ctx the parse tree
	 */
	void exitPairElem_FST(@NotNull BasicParser.PairElem_FSTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_delete}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_delete(@NotNull BasicParser.Stat_deleteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_delete}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_delete(@NotNull BasicParser.Stat_deleteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code waccT_array}
	 * labeled alternative in {@link BasicParser#wacc_type}.
	 * @param ctx the parse tree
	 */
	void enterWaccT_array(@NotNull BasicParser.WaccT_arrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code waccT_array}
	 * labeled alternative in {@link BasicParser#wacc_type}.
	 * @param ctx the parse tree
	 */
	void exitWaccT_array(@NotNull BasicParser.WaccT_arrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#array_liter}.
	 * @param ctx the parse tree
	 */
	void enterArray_liter(@NotNull BasicParser.Array_literContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#array_liter}.
	 * @param ctx the parse tree
	 */
	void exitArray_liter(@NotNull BasicParser.Array_literContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_FREE}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_FREE(@NotNull BasicParser.Stat_FREEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_FREE}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_FREE(@NotNull BasicParser.Stat_FREEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code waccT_class_type}
	 * labeled alternative in {@link BasicParser#wacc_type}.
	 * @param ctx the parse tree
	 */
	void enterWaccT_class_type(@NotNull BasicParser.WaccT_class_typeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code waccT_class_type}
	 * labeled alternative in {@link BasicParser#wacc_type}.
	 * @param ctx the parse tree
	 */
	void exitWaccT_class_type(@NotNull BasicParser.WaccT_class_typeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_RETURN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_RETURN(@NotNull BasicParser.Stat_RETURNContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_RETURN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_RETURN(@NotNull BasicParser.Stat_RETURNContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_field}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_field(@NotNull BasicParser.Expr_fieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_field}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_field(@NotNull BasicParser.Expr_fieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#class_destr}.
	 * @param ctx the parse tree
	 */
	void enterClass_destr(@NotNull BasicParser.Class_destrContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#class_destr}.
	 * @param ctx the parse tree
	 */
	void exitClass_destr(@NotNull BasicParser.Class_destrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignR_CALL}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void enterAssignR_CALL(@NotNull BasicParser.AssignR_CALLContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignR_CALL}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void exitAssignR_CALL(@NotNull BasicParser.AssignR_CALLContext ctx);
	/**
	 * Enter a parse tree produced by the {@code waccT_pair_type}
	 * labeled alternative in {@link BasicParser#wacc_type}.
	 * @param ctx the parse tree
	 */
	void enterWaccT_pair_type(@NotNull BasicParser.WaccT_pair_typeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code waccT_pair_type}
	 * labeled alternative in {@link BasicParser#wacc_type}.
	 * @param ctx the parse tree
	 */
	void exitWaccT_pair_type(@NotNull BasicParser.WaccT_pair_typeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOo_OR}
	 * labeled alternative in {@link BasicParser#logicOper}.
	 * @param ctx the parse tree
	 */
	void enterBinOo_OR(@NotNull BasicParser.BinOo_ORContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOo_OR}
	 * labeled alternative in {@link BasicParser#logicOper}.
	 * @param ctx the parse tree
	 */
	void exitBinOo_OR(@NotNull BasicParser.BinOo_ORContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_PRINTLN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_PRINTLN(@NotNull BasicParser.Stat_PRINTLNContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_PRINTLN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_PRINTLN(@NotNull BasicParser.Stat_PRINTLNContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOp_LT}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 */
	void enterBinOp_LT(@NotNull BasicParser.BinOp_LTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOp_LT}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 */
	void exitBinOp_LT(@NotNull BasicParser.BinOp_LTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignR_pair_elem}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void enterAssignR_pair_elem(@NotNull BasicParser.AssignR_pair_elemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignR_pair_elem}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void exitAssignR_pair_elem(@NotNull BasicParser.AssignR_pair_elemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOp_PLUS}
	 * labeled alternative in {@link BasicParser#arithMinusPlus}.
	 * @param ctx the parse tree
	 */
	void enterBinOp_PLUS(@NotNull BasicParser.BinOp_PLUSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOp_PLUS}
	 * labeled alternative in {@link BasicParser#arithMinusPlus}.
	 * @param ctx the parse tree
	 */
	void exitBinOp_PLUS(@NotNull BasicParser.BinOp_PLUSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_arithMinusPlus}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_arithMinusPlus(@NotNull BasicParser.Expr_arithMinusPlusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_arithMinusPlus}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_arithMinusPlus(@NotNull BasicParser.Expr_arithMinusPlusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code baseT_STRINGTYPE}
	 * labeled alternative in {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 */
	void enterBaseT_STRINGTYPE(@NotNull BasicParser.BaseT_STRINGTYPEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code baseT_STRINGTYPE}
	 * labeled alternative in {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 */
	void exitBaseT_STRINGTYPE(@NotNull BasicParser.BaseT_STRINGTYPEContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#int_liter}.
	 * @param ctx the parse tree
	 */
	void enterInt_liter(@NotNull BasicParser.Int_literContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#int_liter}.
	 * @param ctx the parse tree
	 */
	void exitInt_liter(@NotNull BasicParser.Int_literContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#wacc_class}.
	 * @param ctx the parse tree
	 */
	void enterWacc_class(@NotNull BasicParser.Wacc_classContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#wacc_class}.
	 * @param ctx the parse tree
	 */
	void exitWacc_class(@NotNull BasicParser.Wacc_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#field_ident}.
	 * @param ctx the parse tree
	 */
	void enterField_ident(@NotNull BasicParser.Field_identContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#field_ident}.
	 * @param ctx the parse tree
	 */
	void exitField_ident(@NotNull BasicParser.Field_identContext ctx);
	/**
	 * Enter a parse tree produced by the {@code baseT_INTTYPE}
	 * labeled alternative in {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 */
	void enterBaseT_INTTYPE(@NotNull BasicParser.BaseT_INTTYPEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code baseT_INTTYPE}
	 * labeled alternative in {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 */
	void exitBaseT_INTTYPE(@NotNull BasicParser.BaseT_INTTYPEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOp_DIV}
	 * labeled alternative in {@link BasicParser#arithMulDivMod}.
	 * @param ctx the parse tree
	 */
	void enterBinOp_DIV(@NotNull BasicParser.BinOp_DIVContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOp_DIV}
	 * labeled alternative in {@link BasicParser#arithMulDivMod}.
	 * @param ctx the parse tree
	 */
	void exitBinOp_DIV(@NotNull BasicParser.BinOp_DIVContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_BEGIN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_BEGIN(@NotNull BasicParser.Stat_BEGINContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_BEGIN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_BEGIN(@NotNull BasicParser.Stat_BEGINContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignR_array}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void enterAssignR_array(@NotNull BasicParser.AssignR_arrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignR_array}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void exitAssignR_array(@NotNull BasicParser.AssignR_arrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOp_MINUS}
	 * labeled alternative in {@link BasicParser#arithMinusPlus}.
	 * @param ctx the parse tree
	 */
	void enterBinOp_MINUS(@NotNull BasicParser.BinOp_MINUSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOp_MINUS}
	 * labeled alternative in {@link BasicParser#arithMinusPlus}.
	 * @param ctx the parse tree
	 */
	void exitBinOp_MINUS(@NotNull BasicParser.BinOp_MINUSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code encaps_PROTECTED}
	 * labeled alternative in {@link BasicParser#encaps}.
	 * @param ctx the parse tree
	 */
	void enterEncaps_PROTECTED(@NotNull BasicParser.Encaps_PROTECTEDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code encaps_PROTECTED}
	 * labeled alternative in {@link BasicParser#encaps}.
	 * @param ctx the parse tree
	 */
	void exitEncaps_PROTECTED(@NotNull BasicParser.Encaps_PROTECTEDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code class_body_constr}
	 * labeled alternative in {@link BasicParser#class_body}.
	 * @param ctx the parse tree
	 */
	void enterClass_body_constr(@NotNull BasicParser.Class_body_constrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code class_body_constr}
	 * labeled alternative in {@link BasicParser#class_body}.
	 * @param ctx the parse tree
	 */
	void exitClass_body_constr(@NotNull BasicParser.Class_body_constrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unOp_LEN}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 */
	void enterUnOp_LEN(@NotNull BasicParser.UnOp_LENContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unOp_LEN}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 */
	void exitUnOp_LEN(@NotNull BasicParser.UnOp_LENContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull BasicParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull BasicParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_EXIT}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_EXIT(@NotNull BasicParser.Stat_EXITContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_EXIT}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_EXIT(@NotNull BasicParser.Stat_EXITContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#str_liter}.
	 * @param ctx the parse tree
	 */
	void enterStr_liter(@NotNull BasicParser.Str_literContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#str_liter}.
	 * @param ctx the parse tree
	 */
	void exitStr_liter(@NotNull BasicParser.Str_literContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignL_IDENT}
	 * labeled alternative in {@link BasicParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void enterAssignL_IDENT(@NotNull BasicParser.AssignL_IDENTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignL_IDENT}
	 * labeled alternative in {@link BasicParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void exitAssignL_IDENT(@NotNull BasicParser.AssignL_IDENTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_compare}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_compare(@NotNull BasicParser.Expr_compareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_compare}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_compare(@NotNull BasicParser.Expr_compareContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#class_constr}.
	 * @param ctx the parse tree
	 */
	void enterClass_constr(@NotNull BasicParser.Class_constrContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#class_constr}.
	 * @param ctx the parse tree
	 */
	void exitClass_constr(@NotNull BasicParser.Class_constrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_object}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_object(@NotNull BasicParser.Expr_objectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_object}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_object(@NotNull BasicParser.Expr_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#class_func}.
	 * @param ctx the parse tree
	 */
	void enterClass_func(@NotNull BasicParser.Class_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#class_func}.
	 * @param ctx the parse tree
	 */
	void exitClass_func(@NotNull BasicParser.Class_funcContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignR_expr}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void enterAssignR_expr(@NotNull BasicParser.AssignR_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignR_expr}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void exitAssignR_expr(@NotNull BasicParser.AssignR_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignL_field_ident}
	 * labeled alternative in {@link BasicParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void enterAssignL_field_ident(@NotNull BasicParser.AssignL_field_identContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignL_field_ident}
	 * labeled alternative in {@link BasicParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void exitAssignL_field_ident(@NotNull BasicParser.AssignL_field_identContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(@NotNull BasicParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(@NotNull BasicParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOp_MUL}
	 * labeled alternative in {@link BasicParser#arithMulDivMod}.
	 * @param ctx the parse tree
	 */
	void enterBinOp_MUL(@NotNull BasicParser.BinOp_MULContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOp_MUL}
	 * labeled alternative in {@link BasicParser#arithMulDivMod}.
	 * @param ctx the parse tree
	 */
	void exitBinOp_MUL(@NotNull BasicParser.BinOp_MULContext ctx);
	/**
	 * Enter a parse tree produced by the {@code encaps_PRIVATE}
	 * labeled alternative in {@link BasicParser#encaps}.
	 * @param ctx the parse tree
	 */
	void enterEncaps_PRIVATE(@NotNull BasicParser.Encaps_PRIVATEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code encaps_PRIVATE}
	 * labeled alternative in {@link BasicParser#encaps}.
	 * @param ctx the parse tree
	 */
	void exitEncaps_PRIVATE(@NotNull BasicParser.Encaps_PRIVATEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bool_TRUE}
	 * labeled alternative in {@link BasicParser#bool_liter}.
	 * @param ctx the parse tree
	 */
	void enterBool_TRUE(@NotNull BasicParser.Bool_TRUEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bool_TRUE}
	 * labeled alternative in {@link BasicParser#bool_liter}.
	 * @param ctx the parse tree
	 */
	void exitBool_TRUE(@NotNull BasicParser.Bool_TRUEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_super}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_super(@NotNull BasicParser.Stat_superContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_super}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_super(@NotNull BasicParser.Stat_superContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unOp_ORD}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 */
	void enterUnOp_ORD(@NotNull BasicParser.UnOp_ORDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unOp_ORD}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 */
	void exitUnOp_ORD(@NotNull BasicParser.UnOp_ORDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOp_GT}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 */
	void enterBinOp_GT(@NotNull BasicParser.BinOp_GTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOp_GT}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 */
	void exitBinOp_GT(@NotNull BasicParser.BinOp_GTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOp_NEQ}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 */
	void enterBinOp_NEQ(@NotNull BasicParser.BinOp_NEQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOp_NEQ}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 */
	void exitBinOp_NEQ(@NotNull BasicParser.BinOp_NEQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_logicOper}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_logicOper(@NotNull BasicParser.Expr_logicOperContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_logicOper}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_logicOper(@NotNull BasicParser.Expr_logicOperContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#class_field}.
	 * @param ctx the parse tree
	 */
	void enterClass_field(@NotNull BasicParser.Class_fieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#class_field}.
	 * @param ctx the parse tree
	 */
	void exitClass_field(@NotNull BasicParser.Class_fieldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_PRINT}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_PRINT(@NotNull BasicParser.Stat_PRINTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_PRINT}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_PRINT(@NotNull BasicParser.Stat_PRINTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignR_CALL_member_func}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void enterAssignR_CALL_member_func(@NotNull BasicParser.AssignR_CALL_member_funcContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignR_CALL_member_func}
	 * labeled alternative in {@link BasicParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void exitAssignR_CALL_member_func(@NotNull BasicParser.AssignR_CALL_member_funcContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unOp_NOT}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 */
	void enterUnOp_NOT(@NotNull BasicParser.UnOp_NOTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unOp_NOT}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 */
	void exitUnOp_NOT(@NotNull BasicParser.UnOp_NOTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code class_body_destr}
	 * labeled alternative in {@link BasicParser#class_body}.
	 * @param ctx the parse tree
	 */
	void enterClass_body_destr(@NotNull BasicParser.Class_body_destrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code class_body_destr}
	 * labeled alternative in {@link BasicParser#class_body}.
	 * @param ctx the parse tree
	 */
	void exitClass_body_destr(@NotNull BasicParser.Class_body_destrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_str}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_str(@NotNull BasicParser.Expr_strContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_str}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_str(@NotNull BasicParser.Expr_strContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_array_elem}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_array_elem(@NotNull BasicParser.Expr_array_elemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_array_elem}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_array_elem(@NotNull BasicParser.Expr_array_elemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pElem_base_type}
	 * labeled alternative in {@link BasicParser#pair_elem_type}.
	 * @param ctx the parse tree
	 */
	void enterPElem_base_type(@NotNull BasicParser.PElem_base_typeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pElem_base_type}
	 * labeled alternative in {@link BasicParser#pair_elem_type}.
	 * @param ctx the parse tree
	 */
	void exitPElem_base_type(@NotNull BasicParser.PElem_base_typeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code waccT_base_type}
	 * labeled alternative in {@link BasicParser#wacc_type}.
	 * @param ctx the parse tree
	 */
	void enterWaccT_base_type(@NotNull BasicParser.WaccT_base_typeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code waccT_base_type}
	 * labeled alternative in {@link BasicParser#wacc_type}.
	 * @param ctx the parse tree
	 */
	void exitWaccT_base_type(@NotNull BasicParser.WaccT_base_typeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code class_body_func}
	 * labeled alternative in {@link BasicParser#class_body}.
	 * @param ctx the parse tree
	 */
	void enterClass_body_func(@NotNull BasicParser.Class_body_funcContext ctx);
	/**
	 * Exit a parse tree produced by the {@code class_body_func}
	 * labeled alternative in {@link BasicParser#class_body}.
	 * @param ctx the parse tree
	 */
	void exitClass_body_func(@NotNull BasicParser.Class_body_funcContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pElem_wacc_type}
	 * labeled alternative in {@link BasicParser#pair_elem_type}.
	 * @param ctx the parse tree
	 */
	void enterPElem_wacc_type(@NotNull BasicParser.PElem_wacc_typeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pElem_wacc_type}
	 * labeled alternative in {@link BasicParser#pair_elem_type}.
	 * @param ctx the parse tree
	 */
	void exitPElem_wacc_type(@NotNull BasicParser.PElem_wacc_typeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pairElem_SND}
	 * labeled alternative in {@link BasicParser#pair_elem}.
	 * @param ctx the parse tree
	 */
	void enterPairElem_SND(@NotNull BasicParser.PairElem_SNDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pairElem_SND}
	 * labeled alternative in {@link BasicParser#pair_elem}.
	 * @param ctx the parse tree
	 */
	void exitPairElem_SND(@NotNull BasicParser.PairElem_SNDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_expr}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_expr(@NotNull BasicParser.Expr_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_expr}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_expr(@NotNull BasicParser.Expr_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOp_MOD}
	 * labeled alternative in {@link BasicParser#arithMulDivMod}.
	 * @param ctx the parse tree
	 */
	void enterBinOp_MOD(@NotNull BasicParser.BinOp_MODContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOp_MOD}
	 * labeled alternative in {@link BasicParser#arithMulDivMod}.
	 * @param ctx the parse tree
	 */
	void exitBinOp_MOD(@NotNull BasicParser.BinOp_MODContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_WHILE}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_WHILE(@NotNull BasicParser.Stat_WHILEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_WHILE}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_WHILE(@NotNull BasicParser.Stat_WHILEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_bool}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_bool(@NotNull BasicParser.Expr_boolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_bool}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_bool(@NotNull BasicParser.Expr_boolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unOp_CHR}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 */
	void enterUnOp_CHR(@NotNull BasicParser.UnOp_CHRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unOp_CHR}
	 * labeled alternative in {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 */
	void exitUnOp_CHR(@NotNull BasicParser.UnOp_CHRContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#pair_type}.
	 * @param ctx the parse tree
	 */
	void enterPair_type(@NotNull BasicParser.Pair_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#pair_type}.
	 * @param ctx the parse tree
	 */
	void exitPair_type(@NotNull BasicParser.Pair_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(@NotNull BasicParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(@NotNull BasicParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_pair}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_pair(@NotNull BasicParser.Expr_pairContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_pair}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_pair(@NotNull BasicParser.Expr_pairContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_SKIP}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_SKIP(@NotNull BasicParser.Stat_SKIPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_SKIP}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_SKIP(@NotNull BasicParser.Stat_SKIPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_SEMICOLON}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_SEMICOLON(@NotNull BasicParser.Stat_SEMICOLONContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_SEMICOLON}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_SEMICOLON(@NotNull BasicParser.Stat_SEMICOLONContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOp_AND}
	 * labeled alternative in {@link BasicParser#logicOper}.
	 * @param ctx the parse tree
	 */
	void enterBinOp_AND(@NotNull BasicParser.BinOp_ANDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOp_AND}
	 * labeled alternative in {@link BasicParser#logicOper}.
	 * @param ctx the parse tree
	 */
	void exitBinOp_AND(@NotNull BasicParser.BinOp_ANDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOp_LTE}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 */
	void enterBinOp_LTE(@NotNull BasicParser.BinOp_LTEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOp_LTE}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 */
	void exitBinOp_LTE(@NotNull BasicParser.BinOp_LTEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binOp_EQ}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 */
	void enterBinOp_EQ(@NotNull BasicParser.BinOp_EQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binOp_EQ}
	 * labeled alternative in {@link BasicParser#compare}.
	 * @param ctx the parse tree
	 */
	void exitBinOp_EQ(@NotNull BasicParser.BinOp_EQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_this}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_this(@NotNull BasicParser.Expr_thisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_this}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_this(@NotNull BasicParser.Expr_thisContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#pair_liter}.
	 * @param ctx the parse tree
	 */
	void enterPair_liter(@NotNull BasicParser.Pair_literContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#pair_liter}.
	 * @param ctx the parse tree
	 */
	void exitPair_liter(@NotNull BasicParser.Pair_literContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#arg_list}.
	 * @param ctx the parse tree
	 */
	void enterArg_list(@NotNull BasicParser.Arg_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#arg_list}.
	 * @param ctx the parse tree
	 */
	void exitArg_list(@NotNull BasicParser.Arg_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#param_list}.
	 * @param ctx the parse tree
	 */
	void enterParam_list(@NotNull BasicParser.Param_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#param_list}.
	 * @param ctx the parse tree
	 */
	void exitParam_list(@NotNull BasicParser.Param_listContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_int}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_int(@NotNull BasicParser.Expr_intContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_int}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_int(@NotNull BasicParser.Expr_intContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_READ}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_READ(@NotNull BasicParser.Stat_READContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_READ}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_READ(@NotNull BasicParser.Stat_READContext ctx);
	/**
	 * Enter a parse tree produced by the {@code baseT_CHARTYPE}
	 * labeled alternative in {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 */
	void enterBaseT_CHARTYPE(@NotNull BasicParser.BaseT_CHARTYPEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code baseT_CHARTYPE}
	 * labeled alternative in {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 */
	void exitBaseT_CHARTYPE(@NotNull BasicParser.BaseT_CHARTYPEContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(@NotNull BasicParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(@NotNull BasicParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stat_lr_ASSIGN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat_lr_ASSIGN(@NotNull BasicParser.Stat_lr_ASSIGNContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stat_lr_ASSIGN}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat_lr_ASSIGN(@NotNull BasicParser.Stat_lr_ASSIGNContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bool_FALS}
	 * labeled alternative in {@link BasicParser#bool_liter}.
	 * @param ctx the parse tree
	 */
	void enterBool_FALS(@NotNull BasicParser.Bool_FALSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bool_FALS}
	 * labeled alternative in {@link BasicParser#bool_liter}.
	 * @param ctx the parse tree
	 */
	void exitBool_FALS(@NotNull BasicParser.Bool_FALSContext ctx);
}