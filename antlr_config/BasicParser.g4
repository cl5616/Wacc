parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

field_ident: (THIS DOT)? IDENT (DOT IDENT)*;

pair_liter: NULL;

array_liter: OPEN_SQUARE_BRACKET (expr (COMMA expr)*)? CLOSE_SQUARE_BRACKET;

str_liter: STR;

char_liter: CHAR;

bool_liter: TRUE   #bool_TRUE
  | FALSE         #bool_FALS
  ;

int_sign: PLUS      #intSign_PLUS
  | MINUS           #intSign_MINUS
  ;

int_liter: (int_sign)? INTEGER;

new_object: NEW IDENT OPEN_PARENTHESES (arg_list)? CLOSE_PARENTHESES;

array_elem: IDENT (OPEN_SQUARE_BRACKET expr CLOSE_SQUARE_BRACKET)+;

unaryOper : NOT           #unOp_NOT
  | LEN                   #unOp_LEN
  | ORD                   #unOp_ORD
  | CHR                   #unOp_CHR
  | int_sign              #unOp_sign
  ;
arithMinusPlus: PLUS       #binOp_PLUS
| MINUS               #binOp_MINUS
;

arithMulDivMod: MUL                 #binOp_MUL
| DIV                 #binOp_DIV
| MOD                 #binOp_MOD
;


compare:  GT                  #binOp_GT
  | GTE                 #binOp_GTE
  | LT                  #binOp_LT
  | LTE                 #binOp_LTE
  | EQ                  #binOp_EQ
  | NEQ                 #binOp_NEQ
  ;

logicOper : AND         #binOp_AND
  | OR                  #binOo_OR
  ;

expr: int_liter                                   #expr_int
  | bool_liter                                    #expr_bool
  | char_liter                                    #epxr_char
  | str_liter                                     #expr_str
  | pair_liter                                    #expr_pair
  | IDENT                                         #expr_ident
  | array_elem                                    #expr_array_elem
  | unaryOper expr                                #expr_unaryOper
  | expr arithMulDivMod expr                      #expr_arithMulDivMod
  | expr arithMinusPlus expr                      #expr_arithMinusPlus
  | expr compare expr                             #expr_compare
  | expr logicOper expr                           #expr_logicOper
  | OPEN_PARENTHESES expr CLOSE_PARENTHESES       #expr_expr
  | field_ident                                   #expr_field
  | new_object                                    #expr_object
  | THIS                                          #expr_this
  ;

pair_elem_type: base_type                               #pElem_base_type
  | wacc_type OPEN_SQUARE_BRACKET CLOSE_SQUARE_BRACKET  #pElem_wacc_type
  | PAIRTYPE                                            #pElem_PAIRTYPE
  ;

stat: SKIP                                #stat_SKIP
  | wacc_type IDENT ASSIGN assign_rhs     #stat_variable_ASSIGN
  | assign_lhs ASSIGN assign_rhs          #stat_lr_ASSIGN
  | READ assign_lhs                       #stat_READ
  | FREE expr                             #stat_FREE
  | RETURN expr                           #stat_RETURN
  | EXIT expr                             #stat_EXIT
  | PRINT expr                            #stat_PRINT
  | PRINTLN expr                          #stat_PRINTLN
  | IF expr THEN stat ELSE stat FI        #stat_IF
  | WHILE expr DO stat DONE               #stat_WHILE
  | BEGIN stat END                        #stat_BEGIN
  | stat SEMICOLON stat                   #stat_SEMICOLON
  | DELETE IDENT                          #stat_delete
  | SUPER OPEN_PARENTHESES (arg_list)? CLOSE_PARENTHESES    #stat_super
  ;

pair_type: PAIRTYPE OPEN_PARENTHESES pair_elem_type COMMA pair_elem_type CLOSE_PARENTHESES;

base_type: INTTYPE                    #baseT_INTTYPE
  | BOOLTYPE                          #baseT_BOOLTYPE
  | CHARTYPE                          #baseT_CHARTYPE
  | STRINGTYPE                        #baseT_STRINGTYPE
  ;

wacc_type: base_type                                    #waccT_base_type
  | pair_type                                           #waccT_pair_type
  | wacc_type OPEN_SQUARE_BRACKET CLOSE_SQUARE_BRACKET  #waccT_array
  | IDENT                                               #waccT_class_type
  ;

pair_elem: FST expr                  #pairElem_FST
| SND expr                          #pairElem_SND
;

arg_list: expr (COMMA expr)*;

assign_rhs: expr                                                #assignR_expr
  | array_liter                                                 #assignR_array
  | NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES  #assignR_NEWPAIR
  | pair_elem                                                   #assignR_pair_elem
  | CALL IDENT OPEN_PARENTHESES (arg_list)? CLOSE_PARENTHESES   #assignR_CALL
  | CALL field_ident OPEN_PARENTHESES (arg_list)? CLOSE_PARENTHESES #assignR_CALL_member_func
  ;

assign_lhs: IDENT       #assignL_IDENT
  | array_elem          #assignL_array_elem
  | pair_elem           #assignL_pair_elem
  | field_ident         #assignL_field_ident
  ;

param: wacc_type IDENT;

param_list: param (COMMA param)*;

func: wacc_type IDENT OPEN_PARENTHESES (param_list)? CLOSE_PARENTHESES IS stat END;

encaps: PRIVATE        #encaps_PRIVATE
  | PUBLIC             #encaps_PUBLIC
  | PROTECTED          #encaps_PROTECTED
  ;

class_field: encaps wacc_type IDENT SEMICOLON;

class_constr: encaps IDENT OPEN_PARENTHESES (param_list)? CLOSE_PARENTHESES IS stat END;

class_destr: encaps DESTR IDENT OPEN_PARENTHESES CLOSE_PARENTHESES IS stat END;

class_func: encaps func;

class_body: class_constr    #class_body_constr
  | class_func              #class_body_func
  | class_destr             #class_body_destr
  ;

wacc_class: CLASS IDENT (EXTENDS IDENT)? IS (class_field)* (class_body)* END;

prog: BEGIN (func)* (wacc_class)* stat END;

main: prog EOF;
