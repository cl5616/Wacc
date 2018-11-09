// Generated from ./BasicParser.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PRINT=39, INTTYPE=53, NEW=66, EXTENDS=69, LT=8, NEWPAIR=41, DO=49, CLASS=62, 
		CHR=17, SINGLEQUOTE=25, MINUS=2, STR=61, SEMICOLON=18, ELSE=46, BOOLTYPE=54, 
		PAIRTYPE=57, IF=44, CLOSE_SQUARE_BRACKET=22, INTEGER=28, DONE=50, NULL=58, 
		MUL=3, FST=51, DOT=26, TRUE=29, IS=33, EQ=10, DESTR=68, READ=35, NOT=14, 
		AND=12, PUBLIC=63, THIS=71, END=32, THEN=45, OPEN_SQUARE_BRACKET=21, LTE=9, 
		EXIT=38, PLUS=1, CLOSE_PARENTHESES=20, ORD=16, QUOTE=24, CALL=42, FI=47, 
		PRINTLN=40, OPEN_PARENTHESES=19, SND=52, CHAR=60, BEGIN=31, ASSIGN=43, 
		FREE=36, COMMENT=59, RETURN=37, DELETE=67, CHARTYPE=55, SKIP=34, PROTECTED=65, 
		COMMA=23, WHITE=27, SUPER=70, MOD=5, OR=13, GT=6, PRIVATE=64, DIV=4, LEN=15, 
		IDENT=72, GTE=7, FALSE=30, WHILE=48, NEQ=11, STRINGTYPE=56;
	public static final String[] tokenNames = {
		"<INVALID>", "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'>='", "'<'", 
		"'<='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'len'", "'ord'", "'chr'", 
		"';'", "'('", "')'", "'['", "']'", "','", "'\"'", "'''", "'.'", "WHITE", 
		"INTEGER", "'true'", "'false'", "'begin'", "'end'", "'is'", "'skip'", 
		"'read'", "'free'", "'return'", "'exit'", "'print'", "'println'", "'newpair'", 
		"'call'", "'='", "'if'", "'then'", "'else'", "'fi'", "'while'", "'do'", 
		"'done'", "'fst'", "'snd'", "'int'", "'bool'", "'char'", "'string'", "'pair'", 
		"'null'", "COMMENT", "CHAR", "STR", "'class'", "'public'", "'private'", 
		"'protected'", "'new'", "'delete'", "'~'", "'extends'", "'super'", "'this'", 
		"IDENT"
	};
	public static final int
		RULE_field_ident = 0, RULE_pair_liter = 1, RULE_array_liter = 2, RULE_str_liter = 3, 
		RULE_char_liter = 4, RULE_bool_liter = 5, RULE_int_sign = 6, RULE_int_liter = 7, 
		RULE_new_object = 8, RULE_array_elem = 9, RULE_unaryOper = 10, RULE_arithMinusPlus = 11, 
		RULE_arithMulDivMod = 12, RULE_compare = 13, RULE_logicOper = 14, RULE_expr = 15, 
		RULE_pair_elem_type = 16, RULE_stat = 17, RULE_pair_type = 18, RULE_base_type = 19, 
		RULE_wacc_type = 20, RULE_pair_elem = 21, RULE_arg_list = 22, RULE_assign_rhs = 23, 
		RULE_assign_lhs = 24, RULE_param = 25, RULE_param_list = 26, RULE_func = 27, 
		RULE_encaps = 28, RULE_class_field = 29, RULE_class_constr = 30, RULE_class_destr = 31, 
		RULE_class_func = 32, RULE_class_body = 33, RULE_wacc_class = 34, RULE_prog = 35, 
		RULE_main = 36;
	public static final String[] ruleNames = {
		"field_ident", "pair_liter", "array_liter", "str_liter", "char_liter", 
		"bool_liter", "int_sign", "int_liter", "new_object", "array_elem", "unaryOper", 
		"arithMinusPlus", "arithMulDivMod", "compare", "logicOper", "expr", "pair_elem_type", 
		"stat", "pair_type", "base_type", "wacc_type", "pair_elem", "arg_list", 
		"assign_rhs", "assign_lhs", "param", "param_list", "func", "encaps", "class_field", 
		"class_constr", "class_destr", "class_func", "class_body", "wacc_class", 
		"prog", "main"
	};

	@Override
	public String getGrammarFileName() { return "BasicParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BasicParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Field_identContext extends ParserRuleContext {
		public List<TerminalNode> DOT() { return getTokens(BasicParser.DOT); }
		public TerminalNode IDENT(int i) {
			return getToken(BasicParser.IDENT, i);
		}
		public TerminalNode THIS() { return getToken(BasicParser.THIS, 0); }
		public List<TerminalNode> IDENT() { return getTokens(BasicParser.IDENT); }
		public TerminalNode DOT(int i) {
			return getToken(BasicParser.DOT, i);
		}
		public Field_identContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterField_ident(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitField_ident(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitField_ident(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Field_identContext field_ident() throws RecognitionException {
		Field_identContext _localctx = new Field_identContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_field_ident);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			_la = _input.LA(1);
			if (_la==THIS) {
				{
				setState(74); match(THIS);
				setState(75); match(DOT);
				}
			}

			setState(78); match(IDENT);
			setState(83);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(79); match(DOT);
					setState(80); match(IDENT);
					}
					} 
				}
				setState(85);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Pair_literContext extends ParserRuleContext {
		public TerminalNode NULL() { return getToken(BasicParser.NULL, 0); }
		public Pair_literContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair_liter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPair_liter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPair_liter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPair_liter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pair_literContext pair_liter() throws RecognitionException {
		Pair_literContext _localctx = new Pair_literContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_pair_liter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); match(NULL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Array_literContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode OPEN_SQUARE_BRACKET() { return getToken(BasicParser.OPEN_SQUARE_BRACKET, 0); }
		public TerminalNode CLOSE_SQUARE_BRACKET() { return getToken(BasicParser.CLOSE_SQUARE_BRACKET, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public Array_literContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_liter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterArray_liter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitArray_liter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArray_liter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_literContext array_liter() throws RecognitionException {
		Array_literContext _localctx = new Array_literContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_array_liter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88); match(OPEN_SQUARE_BRACKET);
			setState(97);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << CHAR) | (1L << STR))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (NEW - 66)) | (1L << (THIS - 66)) | (1L << (IDENT - 66)))) != 0)) {
				{
				setState(89); expr(0);
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(90); match(COMMA);
					setState(91); expr(0);
					}
					}
					setState(96);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(99); match(CLOSE_SQUARE_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Str_literContext extends ParserRuleContext {
		public TerminalNode STR() { return getToken(BasicParser.STR, 0); }
		public Str_literContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_str_liter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStr_liter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStr_liter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStr_liter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Str_literContext str_liter() throws RecognitionException {
		Str_literContext _localctx = new Str_literContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_str_liter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); match(STR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Char_literContext extends ParserRuleContext {
		public TerminalNode CHAR() { return getToken(BasicParser.CHAR, 0); }
		public Char_literContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_char_liter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterChar_liter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitChar_liter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitChar_liter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Char_literContext char_liter() throws RecognitionException {
		Char_literContext _localctx = new Char_literContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_char_liter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); match(CHAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bool_literContext extends ParserRuleContext {
		public Bool_literContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_liter; }
	 
		public Bool_literContext() { }
		public void copyFrom(Bool_literContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Bool_TRUEContext extends Bool_literContext {
		public TerminalNode TRUE() { return getToken(BasicParser.TRUE, 0); }
		public Bool_TRUEContext(Bool_literContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBool_TRUE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBool_TRUE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBool_TRUE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Bool_FALSContext extends Bool_literContext {
		public TerminalNode FALSE() { return getToken(BasicParser.FALSE, 0); }
		public Bool_FALSContext(Bool_literContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBool_FALS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBool_FALS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBool_FALS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bool_literContext bool_liter() throws RecognitionException {
		Bool_literContext _localctx = new Bool_literContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_bool_liter);
		try {
			setState(107);
			switch (_input.LA(1)) {
			case TRUE:
				_localctx = new Bool_TRUEContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(105); match(TRUE);
				}
				break;
			case FALSE:
				_localctx = new Bool_FALSContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(106); match(FALSE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Int_signContext extends ParserRuleContext {
		public Int_signContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_sign; }
	 
		public Int_signContext() { }
		public void copyFrom(Int_signContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IntSign_PLUSContext extends Int_signContext {
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public IntSign_PLUSContext(Int_signContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterIntSign_PLUS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitIntSign_PLUS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitIntSign_PLUS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntSign_MINUSContext extends Int_signContext {
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public IntSign_MINUSContext(Int_signContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterIntSign_MINUS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitIntSign_MINUS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitIntSign_MINUS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Int_signContext int_sign() throws RecognitionException {
		Int_signContext _localctx = new Int_signContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_int_sign);
		try {
			setState(111);
			switch (_input.LA(1)) {
			case PLUS:
				_localctx = new IntSign_PLUSContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(109); match(PLUS);
				}
				break;
			case MINUS:
				_localctx = new IntSign_MINUSContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(110); match(MINUS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Int_literContext extends ParserRuleContext {
		public Int_signContext int_sign() {
			return getRuleContext(Int_signContext.class,0);
		}
		public TerminalNode INTEGER() { return getToken(BasicParser.INTEGER, 0); }
		public Int_literContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_liter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterInt_liter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitInt_liter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitInt_liter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Int_literContext int_liter() throws RecognitionException {
		Int_literContext _localctx = new Int_literContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_int_liter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(113); int_sign();
				}
			}

			setState(116); match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class New_objectContext extends ParserRuleContext {
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public Arg_listContext arg_list() {
			return getRuleContext(Arg_listContext.class,0);
		}
		public TerminalNode NEW() { return getToken(BasicParser.NEW, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public New_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_new_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterNew_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitNew_object(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitNew_object(this);
			else return visitor.visitChildren(this);
		}
	}

	public final New_objectContext new_object() throws RecognitionException {
		New_objectContext _localctx = new New_objectContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_new_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118); match(NEW);
			setState(119); match(IDENT);
			setState(120); match(OPEN_PARENTHESES);
			setState(122);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << CHAR) | (1L << STR))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (NEW - 66)) | (1L << (THIS - 66)) | (1L << (IDENT - 66)))) != 0)) {
				{
				setState(121); arg_list();
				}
			}

			setState(124); match(CLOSE_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Array_elemContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> OPEN_SQUARE_BRACKET() { return getTokens(BasicParser.OPEN_SQUARE_BRACKET); }
		public TerminalNode OPEN_SQUARE_BRACKET(int i) {
			return getToken(BasicParser.OPEN_SQUARE_BRACKET, i);
		}
		public List<TerminalNode> CLOSE_SQUARE_BRACKET() { return getTokens(BasicParser.CLOSE_SQUARE_BRACKET); }
		public TerminalNode CLOSE_SQUARE_BRACKET(int i) {
			return getToken(BasicParser.CLOSE_SQUARE_BRACKET, i);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public Array_elemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_elem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterArray_elem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitArray_elem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArray_elem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_elemContext array_elem() throws RecognitionException {
		Array_elemContext _localctx = new Array_elemContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_array_elem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(126); match(IDENT);
			setState(131); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(127); match(OPEN_SQUARE_BRACKET);
					setState(128); expr(0);
					setState(129); match(CLOSE_SQUARE_BRACKET);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(133); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryOperContext extends ParserRuleContext {
		public UnaryOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOper; }
	 
		public UnaryOperContext() { }
		public void copyFrom(UnaryOperContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UnOp_ORDContext extends UnaryOperContext {
		public TerminalNode ORD() { return getToken(BasicParser.ORD, 0); }
		public UnOp_ORDContext(UnaryOperContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterUnOp_ORD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitUnOp_ORD(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitUnOp_ORD(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnOp_signContext extends UnaryOperContext {
		public Int_signContext int_sign() {
			return getRuleContext(Int_signContext.class,0);
		}
		public UnOp_signContext(UnaryOperContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterUnOp_sign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitUnOp_sign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitUnOp_sign(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnOp_NOTContext extends UnaryOperContext {
		public TerminalNode NOT() { return getToken(BasicParser.NOT, 0); }
		public UnOp_NOTContext(UnaryOperContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterUnOp_NOT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitUnOp_NOT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitUnOp_NOT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnOp_CHRContext extends UnaryOperContext {
		public TerminalNode CHR() { return getToken(BasicParser.CHR, 0); }
		public UnOp_CHRContext(UnaryOperContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterUnOp_CHR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitUnOp_CHR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitUnOp_CHR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnOp_LENContext extends UnaryOperContext {
		public TerminalNode LEN() { return getToken(BasicParser.LEN, 0); }
		public UnOp_LENContext(UnaryOperContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterUnOp_LEN(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitUnOp_LEN(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitUnOp_LEN(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOperContext unaryOper() throws RecognitionException {
		UnaryOperContext _localctx = new UnaryOperContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_unaryOper);
		try {
			setState(140);
			switch (_input.LA(1)) {
			case NOT:
				_localctx = new UnOp_NOTContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(135); match(NOT);
				}
				break;
			case LEN:
				_localctx = new UnOp_LENContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(136); match(LEN);
				}
				break;
			case ORD:
				_localctx = new UnOp_ORDContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(137); match(ORD);
				}
				break;
			case CHR:
				_localctx = new UnOp_CHRContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(138); match(CHR);
				}
				break;
			case PLUS:
			case MINUS:
				_localctx = new UnOp_signContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(139); int_sign();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithMinusPlusContext extends ParserRuleContext {
		public ArithMinusPlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithMinusPlus; }
	 
		public ArithMinusPlusContext() { }
		public void copyFrom(ArithMinusPlusContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BinOp_PLUSContext extends ArithMinusPlusContext {
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public BinOp_PLUSContext(ArithMinusPlusContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinOp_PLUS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinOp_PLUS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOp_PLUS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinOp_MINUSContext extends ArithMinusPlusContext {
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public BinOp_MINUSContext(ArithMinusPlusContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinOp_MINUS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinOp_MINUS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOp_MINUS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithMinusPlusContext arithMinusPlus() throws RecognitionException {
		ArithMinusPlusContext _localctx = new ArithMinusPlusContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_arithMinusPlus);
		try {
			setState(144);
			switch (_input.LA(1)) {
			case PLUS:
				_localctx = new BinOp_PLUSContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(142); match(PLUS);
				}
				break;
			case MINUS:
				_localctx = new BinOp_MINUSContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(143); match(MINUS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithMulDivModContext extends ParserRuleContext {
		public ArithMulDivModContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithMulDivMod; }
	 
		public ArithMulDivModContext() { }
		public void copyFrom(ArithMulDivModContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BinOp_DIVContext extends ArithMulDivModContext {
		public TerminalNode DIV() { return getToken(BasicParser.DIV, 0); }
		public BinOp_DIVContext(ArithMulDivModContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinOp_DIV(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinOp_DIV(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOp_DIV(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinOp_MODContext extends ArithMulDivModContext {
		public TerminalNode MOD() { return getToken(BasicParser.MOD, 0); }
		public BinOp_MODContext(ArithMulDivModContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinOp_MOD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinOp_MOD(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOp_MOD(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinOp_MULContext extends ArithMulDivModContext {
		public TerminalNode MUL() { return getToken(BasicParser.MUL, 0); }
		public BinOp_MULContext(ArithMulDivModContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinOp_MUL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinOp_MUL(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOp_MUL(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithMulDivModContext arithMulDivMod() throws RecognitionException {
		ArithMulDivModContext _localctx = new ArithMulDivModContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_arithMulDivMod);
		try {
			setState(149);
			switch (_input.LA(1)) {
			case MUL:
				_localctx = new BinOp_MULContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(146); match(MUL);
				}
				break;
			case DIV:
				_localctx = new BinOp_DIVContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(147); match(DIV);
				}
				break;
			case MOD:
				_localctx = new BinOp_MODContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(148); match(MOD);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompareContext extends ParserRuleContext {
		public CompareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compare; }
	 
		public CompareContext() { }
		public void copyFrom(CompareContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BinOp_GTContext extends CompareContext {
		public TerminalNode GT() { return getToken(BasicParser.GT, 0); }
		public BinOp_GTContext(CompareContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinOp_GT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinOp_GT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOp_GT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinOp_GTEContext extends CompareContext {
		public TerminalNode GTE() { return getToken(BasicParser.GTE, 0); }
		public BinOp_GTEContext(CompareContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinOp_GTE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinOp_GTE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOp_GTE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinOp_NEQContext extends CompareContext {
		public TerminalNode NEQ() { return getToken(BasicParser.NEQ, 0); }
		public BinOp_NEQContext(CompareContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinOp_NEQ(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinOp_NEQ(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOp_NEQ(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinOp_LTEContext extends CompareContext {
		public TerminalNode LTE() { return getToken(BasicParser.LTE, 0); }
		public BinOp_LTEContext(CompareContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinOp_LTE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinOp_LTE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOp_LTE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinOp_EQContext extends CompareContext {
		public TerminalNode EQ() { return getToken(BasicParser.EQ, 0); }
		public BinOp_EQContext(CompareContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinOp_EQ(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinOp_EQ(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOp_EQ(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinOp_LTContext extends CompareContext {
		public TerminalNode LT() { return getToken(BasicParser.LT, 0); }
		public BinOp_LTContext(CompareContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinOp_LT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinOp_LT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOp_LT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompareContext compare() throws RecognitionException {
		CompareContext _localctx = new CompareContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_compare);
		try {
			setState(157);
			switch (_input.LA(1)) {
			case GT:
				_localctx = new BinOp_GTContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(151); match(GT);
				}
				break;
			case GTE:
				_localctx = new BinOp_GTEContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(152); match(GTE);
				}
				break;
			case LT:
				_localctx = new BinOp_LTContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(153); match(LT);
				}
				break;
			case LTE:
				_localctx = new BinOp_LTEContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(154); match(LTE);
				}
				break;
			case EQ:
				_localctx = new BinOp_EQContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(155); match(EQ);
				}
				break;
			case NEQ:
				_localctx = new BinOp_NEQContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(156); match(NEQ);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicOperContext extends ParserRuleContext {
		public LogicOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicOper; }
	 
		public LogicOperContext() { }
		public void copyFrom(LogicOperContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BinOp_ANDContext extends LogicOperContext {
		public TerminalNode AND() { return getToken(BasicParser.AND, 0); }
		public BinOp_ANDContext(LogicOperContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinOp_AND(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinOp_AND(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOp_AND(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinOo_ORContext extends LogicOperContext {
		public TerminalNode OR() { return getToken(BasicParser.OR, 0); }
		public BinOo_ORContext(LogicOperContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinOo_OR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinOo_OR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOo_OR(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicOperContext logicOper() throws RecognitionException {
		LogicOperContext _localctx = new LogicOperContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_logicOper);
		try {
			setState(161);
			switch (_input.LA(1)) {
			case AND:
				_localctx = new BinOp_ANDContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(159); match(AND);
				}
				break;
			case OR:
				_localctx = new BinOo_ORContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(160); match(OR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Expr_compareContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CompareContext compare() {
			return getRuleContext(CompareContext.class,0);
		}
		public Expr_compareContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_compare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_compare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_compare(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_exprContext extends ExprContext {
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public Expr_exprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_objectContext extends ExprContext {
		public New_objectContext new_object() {
			return getRuleContext(New_objectContext.class,0);
		}
		public Expr_objectContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_object(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_object(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_logicOperContext extends ExprContext {
		public LogicOperContext logicOper() {
			return getRuleContext(LogicOperContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Expr_logicOperContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_logicOper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_logicOper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_logicOper(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_thisContext extends ExprContext {
		public TerminalNode THIS() { return getToken(BasicParser.THIS, 0); }
		public Expr_thisContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_this(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_this(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_this(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_arithMinusPlusContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArithMinusPlusContext arithMinusPlus() {
			return getRuleContext(ArithMinusPlusContext.class,0);
		}
		public Expr_arithMinusPlusContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_arithMinusPlus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_arithMinusPlus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_arithMinusPlus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_unaryOperContext extends ExprContext {
		public UnaryOperContext unaryOper() {
			return getRuleContext(UnaryOperContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_unaryOperContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_unaryOper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_unaryOper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_unaryOper(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_boolContext extends ExprContext {
		public Bool_literContext bool_liter() {
			return getRuleContext(Bool_literContext.class,0);
		}
		public Expr_boolContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_bool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_bool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_bool(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_identContext extends ExprContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public Expr_identContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_ident(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_ident(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_ident(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Epxr_charContext extends ExprContext {
		public Char_literContext char_liter() {
			return getRuleContext(Char_literContext.class,0);
		}
		public Epxr_charContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterEpxr_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitEpxr_char(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitEpxr_char(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_intContext extends ExprContext {
		public Int_literContext int_liter() {
			return getRuleContext(Int_literContext.class,0);
		}
		public Expr_intContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_int(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_fieldContext extends ExprContext {
		public Field_identContext field_ident() {
			return getRuleContext(Field_identContext.class,0);
		}
		public Expr_fieldContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_field(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_field(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_field(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_strContext extends ExprContext {
		public Str_literContext str_liter() {
			return getRuleContext(Str_literContext.class,0);
		}
		public Expr_strContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_str(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_str(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_str(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_array_elemContext extends ExprContext {
		public Array_elemContext array_elem() {
			return getRuleContext(Array_elemContext.class,0);
		}
		public Expr_array_elemContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_array_elem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_array_elem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_array_elem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_arithMulDivModContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ArithMulDivModContext arithMulDivMod() {
			return getRuleContext(ArithMulDivModContext.class,0);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Expr_arithMulDivModContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_arithMulDivMod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_arithMulDivMod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_arithMulDivMod(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_pairContext extends ExprContext {
		public Pair_literContext pair_liter() {
			return getRuleContext(Pair_literContext.class,0);
		}
		public Expr_pairContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr_pair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr_pair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr_pair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				_localctx = new Expr_unaryOperContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(164); unaryOper();
				setState(165); expr(9);
				}
				break;
			case 2:
				{
				_localctx = new Expr_intContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(167); int_liter();
				}
				break;
			case 3:
				{
				_localctx = new Expr_boolContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(168); bool_liter();
				}
				break;
			case 4:
				{
				_localctx = new Epxr_charContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(169); char_liter();
				}
				break;
			case 5:
				{
				_localctx = new Expr_strContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(170); str_liter();
				}
				break;
			case 6:
				{
				_localctx = new Expr_pairContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(171); pair_liter();
				}
				break;
			case 7:
				{
				_localctx = new Expr_identContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(172); match(IDENT);
				}
				break;
			case 8:
				{
				_localctx = new Expr_array_elemContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(173); array_elem();
				}
				break;
			case 9:
				{
				_localctx = new Expr_exprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(174); match(OPEN_PARENTHESES);
				setState(175); expr(0);
				setState(176); match(CLOSE_PARENTHESES);
				}
				break;
			case 10:
				{
				_localctx = new Expr_fieldContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(178); field_ident();
				}
				break;
			case 11:
				{
				_localctx = new Expr_objectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(179); new_object();
				}
				break;
			case 12:
				{
				_localctx = new Expr_thisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(180); match(THIS);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(201);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(199);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new Expr_arithMulDivModContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(183);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(184); arithMulDivMod();
						setState(185); expr(9);
						}
						break;
					case 2:
						{
						_localctx = new Expr_arithMinusPlusContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(187);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(188); arithMinusPlus();
						setState(189); expr(8);
						}
						break;
					case 3:
						{
						_localctx = new Expr_compareContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(191);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(192); compare();
						setState(193); expr(7);
						}
						break;
					case 4:
						{
						_localctx = new Expr_logicOperContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(195);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(196); logicOper();
						setState(197); expr(6);
						}
						break;
					}
					} 
				}
				setState(203);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Pair_elem_typeContext extends ParserRuleContext {
		public Pair_elem_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair_elem_type; }
	 
		public Pair_elem_typeContext() { }
		public void copyFrom(Pair_elem_typeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PElem_wacc_typeContext extends Pair_elem_typeContext {
		public TerminalNode OPEN_SQUARE_BRACKET() { return getToken(BasicParser.OPEN_SQUARE_BRACKET, 0); }
		public TerminalNode CLOSE_SQUARE_BRACKET() { return getToken(BasicParser.CLOSE_SQUARE_BRACKET, 0); }
		public Wacc_typeContext wacc_type() {
			return getRuleContext(Wacc_typeContext.class,0);
		}
		public PElem_wacc_typeContext(Pair_elem_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPElem_wacc_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPElem_wacc_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPElem_wacc_type(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PElem_base_typeContext extends Pair_elem_typeContext {
		public Base_typeContext base_type() {
			return getRuleContext(Base_typeContext.class,0);
		}
		public PElem_base_typeContext(Pair_elem_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPElem_base_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPElem_base_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPElem_base_type(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PElem_PAIRTYPEContext extends Pair_elem_typeContext {
		public TerminalNode PAIRTYPE() { return getToken(BasicParser.PAIRTYPE, 0); }
		public PElem_PAIRTYPEContext(Pair_elem_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPElem_PAIRTYPE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPElem_PAIRTYPE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPElem_PAIRTYPE(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pair_elem_typeContext pair_elem_type() throws RecognitionException {
		Pair_elem_typeContext _localctx = new Pair_elem_typeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_pair_elem_type);
		try {
			setState(210);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new PElem_base_typeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(204); base_type();
				}
				break;
			case 2:
				_localctx = new PElem_wacc_typeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(205); wacc_type(0);
				setState(206); match(OPEN_SQUARE_BRACKET);
				setState(207); match(CLOSE_SQUARE_BRACKET);
				}
				break;
			case 3:
				_localctx = new PElem_PAIRTYPEContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(209); match(PAIRTYPE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Stat_IFContext extends StatContext {
		public TerminalNode FI() { return getToken(BasicParser.FI, 0); }
		public TerminalNode ELSE() { return getToken(BasicParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(BasicParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode THEN() { return getToken(BasicParser.THEN, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public Stat_IFContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_IF(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_IF(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_IF(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_deleteContext extends StatContext {
		public TerminalNode DELETE() { return getToken(BasicParser.DELETE, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public Stat_deleteContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_delete(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_delete(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_delete(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_FREEContext extends StatContext {
		public TerminalNode FREE() { return getToken(BasicParser.FREE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Stat_FREEContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_FREE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_FREE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_FREE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_WHILEContext extends StatContext {
		public TerminalNode DONE() { return getToken(BasicParser.DONE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DO() { return getToken(BasicParser.DO, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(BasicParser.WHILE, 0); }
		public Stat_WHILEContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_WHILE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_WHILE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_WHILE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_PRINTContext extends StatContext {
		public TerminalNode PRINT() { return getToken(BasicParser.PRINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Stat_PRINTContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_PRINT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_PRINT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_PRINT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_RETURNContext extends StatContext {
		public TerminalNode RETURN() { return getToken(BasicParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Stat_RETURNContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_RETURN(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_RETURN(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_RETURN(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_READContext extends StatContext {
		public TerminalNode READ() { return getToken(BasicParser.READ, 0); }
		public Assign_lhsContext assign_lhs() {
			return getRuleContext(Assign_lhsContext.class,0);
		}
		public Stat_READContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_READ(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_READ(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_READ(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_lr_ASSIGNContext extends StatContext {
		public TerminalNode ASSIGN() { return getToken(BasicParser.ASSIGN, 0); }
		public Assign_rhsContext assign_rhs() {
			return getRuleContext(Assign_rhsContext.class,0);
		}
		public Assign_lhsContext assign_lhs() {
			return getRuleContext(Assign_lhsContext.class,0);
		}
		public Stat_lr_ASSIGNContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_lr_ASSIGN(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_lr_ASSIGN(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_lr_ASSIGN(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_EXITContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EXIT() { return getToken(BasicParser.EXIT, 0); }
		public Stat_EXITContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_EXIT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_EXIT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_EXIT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_PRINTLNContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PRINTLN() { return getToken(BasicParser.PRINTLN, 0); }
		public Stat_PRINTLNContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_PRINTLN(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_PRINTLN(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_PRINTLN(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_SKIPContext extends StatContext {
		public TerminalNode SKIP() { return getToken(BasicParser.SKIP, 0); }
		public Stat_SKIPContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_SKIP(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_SKIP(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_SKIP(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_superContext extends StatContext {
		public TerminalNode SUPER() { return getToken(BasicParser.SUPER, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public Arg_listContext arg_list() {
			return getRuleContext(Arg_listContext.class,0);
		}
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public Stat_superContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_super(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_super(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_super(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_SEMICOLONContext extends StatContext {
		public TerminalNode SEMICOLON() { return getToken(BasicParser.SEMICOLON, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public Stat_SEMICOLONContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_SEMICOLON(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_SEMICOLON(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_SEMICOLON(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_BEGINContext extends StatContext {
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public Stat_BEGINContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_BEGIN(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_BEGIN(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_BEGIN(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stat_variable_ASSIGNContext extends StatContext {
		public TerminalNode ASSIGN() { return getToken(BasicParser.ASSIGN, 0); }
		public Assign_rhsContext assign_rhs() {
			return getRuleContext(Assign_rhsContext.class,0);
		}
		public Wacc_typeContext wacc_type() {
			return getRuleContext(Wacc_typeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public Stat_variable_ASSIGNContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat_variable_ASSIGN(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat_variable_ASSIGN(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat_variable_ASSIGN(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		return stat(0);
	}

	private StatContext stat(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatContext _localctx = new StatContext(_ctx, _parentState);
		StatContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_stat, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				_localctx = new Stat_SKIPContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(213); match(SKIP);
				}
				break;
			case 2:
				{
				_localctx = new Stat_variable_ASSIGNContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(214); wacc_type(0);
				setState(215); match(IDENT);
				setState(216); match(ASSIGN);
				setState(217); assign_rhs();
				}
				break;
			case 3:
				{
				_localctx = new Stat_lr_ASSIGNContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(219); assign_lhs();
				setState(220); match(ASSIGN);
				setState(221); assign_rhs();
				}
				break;
			case 4:
				{
				_localctx = new Stat_READContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(223); match(READ);
				setState(224); assign_lhs();
				}
				break;
			case 5:
				{
				_localctx = new Stat_FREEContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(225); match(FREE);
				setState(226); expr(0);
				}
				break;
			case 6:
				{
				_localctx = new Stat_RETURNContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(227); match(RETURN);
				setState(228); expr(0);
				}
				break;
			case 7:
				{
				_localctx = new Stat_EXITContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(229); match(EXIT);
				setState(230); expr(0);
				}
				break;
			case 8:
				{
				_localctx = new Stat_PRINTContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(231); match(PRINT);
				setState(232); expr(0);
				}
				break;
			case 9:
				{
				_localctx = new Stat_PRINTLNContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(233); match(PRINTLN);
				setState(234); expr(0);
				}
				break;
			case 10:
				{
				_localctx = new Stat_IFContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(235); match(IF);
				setState(236); expr(0);
				setState(237); match(THEN);
				setState(238); stat(0);
				setState(239); match(ELSE);
				setState(240); stat(0);
				setState(241); match(FI);
				}
				break;
			case 11:
				{
				_localctx = new Stat_WHILEContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(243); match(WHILE);
				setState(244); expr(0);
				setState(245); match(DO);
				setState(246); stat(0);
				setState(247); match(DONE);
				}
				break;
			case 12:
				{
				_localctx = new Stat_BEGINContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(249); match(BEGIN);
				setState(250); stat(0);
				setState(251); match(END);
				}
				break;
			case 13:
				{
				_localctx = new Stat_deleteContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(253); match(DELETE);
				setState(254); match(IDENT);
				}
				break;
			case 14:
				{
				_localctx = new Stat_superContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(255); match(SUPER);
				setState(256); match(OPEN_PARENTHESES);
				setState(258);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << CHAR) | (1L << STR))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (NEW - 66)) | (1L << (THIS - 66)) | (1L << (IDENT - 66)))) != 0)) {
					{
					setState(257); arg_list();
					}
				}

				setState(260); match(CLOSE_PARENTHESES);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(268);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Stat_SEMICOLONContext(new StatContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(263);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(264); match(SEMICOLON);
					setState(265); stat(4);
					}
					} 
				}
				setState(270);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Pair_typeContext extends ParserRuleContext {
		public List<Pair_elem_typeContext> pair_elem_type() {
			return getRuleContexts(Pair_elem_typeContext.class);
		}
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode PAIRTYPE() { return getToken(BasicParser.PAIRTYPE, 0); }
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public Pair_elem_typeContext pair_elem_type(int i) {
			return getRuleContext(Pair_elem_typeContext.class,i);
		}
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public Pair_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPair_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPair_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPair_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pair_typeContext pair_type() throws RecognitionException {
		Pair_typeContext _localctx = new Pair_typeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_pair_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271); match(PAIRTYPE);
			setState(272); match(OPEN_PARENTHESES);
			setState(273); pair_elem_type();
			setState(274); match(COMMA);
			setState(275); pair_elem_type();
			setState(276); match(CLOSE_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_typeContext extends ParserRuleContext {
		public Base_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_type; }
	 
		public Base_typeContext() { }
		public void copyFrom(Base_typeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BaseT_BOOLTYPEContext extends Base_typeContext {
		public TerminalNode BOOLTYPE() { return getToken(BasicParser.BOOLTYPE, 0); }
		public BaseT_BOOLTYPEContext(Base_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBaseT_BOOLTYPE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBaseT_BOOLTYPE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBaseT_BOOLTYPE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BaseT_CHARTYPEContext extends Base_typeContext {
		public TerminalNode CHARTYPE() { return getToken(BasicParser.CHARTYPE, 0); }
		public BaseT_CHARTYPEContext(Base_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBaseT_CHARTYPE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBaseT_CHARTYPE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBaseT_CHARTYPE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BaseT_INTTYPEContext extends Base_typeContext {
		public TerminalNode INTTYPE() { return getToken(BasicParser.INTTYPE, 0); }
		public BaseT_INTTYPEContext(Base_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBaseT_INTTYPE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBaseT_INTTYPE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBaseT_INTTYPE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BaseT_STRINGTYPEContext extends Base_typeContext {
		public TerminalNode STRINGTYPE() { return getToken(BasicParser.STRINGTYPE, 0); }
		public BaseT_STRINGTYPEContext(Base_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBaseT_STRINGTYPE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBaseT_STRINGTYPE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBaseT_STRINGTYPE(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Base_typeContext base_type() throws RecognitionException {
		Base_typeContext _localctx = new Base_typeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_base_type);
		try {
			setState(282);
			switch (_input.LA(1)) {
			case INTTYPE:
				_localctx = new BaseT_INTTYPEContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(278); match(INTTYPE);
				}
				break;
			case BOOLTYPE:
				_localctx = new BaseT_BOOLTYPEContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(279); match(BOOLTYPE);
				}
				break;
			case CHARTYPE:
				_localctx = new BaseT_CHARTYPEContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(280); match(CHARTYPE);
				}
				break;
			case STRINGTYPE:
				_localctx = new BaseT_STRINGTYPEContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(281); match(STRINGTYPE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Wacc_typeContext extends ParserRuleContext {
		public Wacc_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wacc_type; }
	 
		public Wacc_typeContext() { }
		public void copyFrom(Wacc_typeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class WaccT_pair_typeContext extends Wacc_typeContext {
		public Pair_typeContext pair_type() {
			return getRuleContext(Pair_typeContext.class,0);
		}
		public WaccT_pair_typeContext(Wacc_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterWaccT_pair_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitWaccT_pair_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitWaccT_pair_type(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WaccT_arrayContext extends Wacc_typeContext {
		public TerminalNode OPEN_SQUARE_BRACKET() { return getToken(BasicParser.OPEN_SQUARE_BRACKET, 0); }
		public TerminalNode CLOSE_SQUARE_BRACKET() { return getToken(BasicParser.CLOSE_SQUARE_BRACKET, 0); }
		public Wacc_typeContext wacc_type() {
			return getRuleContext(Wacc_typeContext.class,0);
		}
		public WaccT_arrayContext(Wacc_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterWaccT_array(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitWaccT_array(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitWaccT_array(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WaccT_class_typeContext extends Wacc_typeContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public WaccT_class_typeContext(Wacc_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterWaccT_class_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitWaccT_class_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitWaccT_class_type(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WaccT_base_typeContext extends Wacc_typeContext {
		public Base_typeContext base_type() {
			return getRuleContext(Base_typeContext.class,0);
		}
		public WaccT_base_typeContext(Wacc_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterWaccT_base_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitWaccT_base_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitWaccT_base_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Wacc_typeContext wacc_type() throws RecognitionException {
		return wacc_type(0);
	}

	private Wacc_typeContext wacc_type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Wacc_typeContext _localctx = new Wacc_typeContext(_ctx, _parentState);
		Wacc_typeContext _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_wacc_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			switch (_input.LA(1)) {
			case INTTYPE:
			case BOOLTYPE:
			case CHARTYPE:
			case STRINGTYPE:
				{
				_localctx = new WaccT_base_typeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(285); base_type();
				}
				break;
			case PAIRTYPE:
				{
				_localctx = new WaccT_pair_typeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(286); pair_type();
				}
				break;
			case IDENT:
				{
				_localctx = new WaccT_class_typeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(287); match(IDENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(295);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new WaccT_arrayContext(new Wacc_typeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_wacc_type);
					setState(290);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(291); match(OPEN_SQUARE_BRACKET);
					setState(292); match(CLOSE_SQUARE_BRACKET);
					}
					} 
				}
				setState(297);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Pair_elemContext extends ParserRuleContext {
		public Pair_elemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair_elem; }
	 
		public Pair_elemContext() { }
		public void copyFrom(Pair_elemContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PairElem_FSTContext extends Pair_elemContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FST() { return getToken(BasicParser.FST, 0); }
		public PairElem_FSTContext(Pair_elemContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPairElem_FST(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPairElem_FST(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairElem_FST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PairElem_SNDContext extends Pair_elemContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SND() { return getToken(BasicParser.SND, 0); }
		public PairElem_SNDContext(Pair_elemContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPairElem_SND(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPairElem_SND(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairElem_SND(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pair_elemContext pair_elem() throws RecognitionException {
		Pair_elemContext _localctx = new Pair_elemContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_pair_elem);
		try {
			setState(302);
			switch (_input.LA(1)) {
			case FST:
				_localctx = new PairElem_FSTContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(298); match(FST);
				setState(299); expr(0);
				}
				break;
			case SND:
				_localctx = new PairElem_SNDContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(300); match(SND);
				setState(301); expr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arg_listContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public Arg_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterArg_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitArg_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArg_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arg_listContext arg_list() throws RecognitionException {
		Arg_listContext _localctx = new Arg_listContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_arg_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304); expr(0);
			setState(309);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(305); match(COMMA);
				setState(306); expr(0);
				}
				}
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assign_rhsContext extends ParserRuleContext {
		public Assign_rhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_rhs; }
	 
		public Assign_rhsContext() { }
		public void copyFrom(Assign_rhsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignR_NEWPAIRContext extends Assign_rhsContext {
		public TerminalNode NEWPAIR() { return getToken(BasicParser.NEWPAIR, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public AssignR_NEWPAIRContext(Assign_rhsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignR_NEWPAIR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignR_NEWPAIR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignR_NEWPAIR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignR_CALLContext extends Assign_rhsContext {
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode CALL() { return getToken(BasicParser.CALL, 0); }
		public Arg_listContext arg_list() {
			return getRuleContext(Arg_listContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public AssignR_CALLContext(Assign_rhsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignR_CALL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignR_CALL(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignR_CALL(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignR_pair_elemContext extends Assign_rhsContext {
		public Pair_elemContext pair_elem() {
			return getRuleContext(Pair_elemContext.class,0);
		}
		public AssignR_pair_elemContext(Assign_rhsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignR_pair_elem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignR_pair_elem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignR_pair_elem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignR_exprContext extends Assign_rhsContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignR_exprContext(Assign_rhsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignR_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignR_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignR_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignR_arrayContext extends Assign_rhsContext {
		public Array_literContext array_liter() {
			return getRuleContext(Array_literContext.class,0);
		}
		public AssignR_arrayContext(Assign_rhsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignR_array(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignR_array(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignR_array(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignR_CALL_member_funcContext extends Assign_rhsContext {
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode CALL() { return getToken(BasicParser.CALL, 0); }
		public Field_identContext field_ident() {
			return getRuleContext(Field_identContext.class,0);
		}
		public Arg_listContext arg_list() {
			return getRuleContext(Arg_listContext.class,0);
		}
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public AssignR_CALL_member_funcContext(Assign_rhsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignR_CALL_member_func(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignR_CALL_member_func(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignR_CALL_member_func(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assign_rhsContext assign_rhs() throws RecognitionException {
		Assign_rhsContext _localctx = new Assign_rhsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_assign_rhs);
		int _la;
		try {
			setState(337);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				_localctx = new AssignR_exprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(312); expr(0);
				}
				break;
			case 2:
				_localctx = new AssignR_arrayContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(313); array_liter();
				}
				break;
			case 3:
				_localctx = new AssignR_NEWPAIRContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(314); match(NEWPAIR);
				setState(315); match(OPEN_PARENTHESES);
				setState(316); expr(0);
				setState(317); match(COMMA);
				setState(318); expr(0);
				setState(319); match(CLOSE_PARENTHESES);
				}
				break;
			case 4:
				_localctx = new AssignR_pair_elemContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(321); pair_elem();
				}
				break;
			case 5:
				_localctx = new AssignR_CALLContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(322); match(CALL);
				setState(323); match(IDENT);
				setState(324); match(OPEN_PARENTHESES);
				setState(326);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << CHAR) | (1L << STR))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (NEW - 66)) | (1L << (THIS - 66)) | (1L << (IDENT - 66)))) != 0)) {
					{
					setState(325); arg_list();
					}
				}

				setState(328); match(CLOSE_PARENTHESES);
				}
				break;
			case 6:
				_localctx = new AssignR_CALL_member_funcContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(329); match(CALL);
				setState(330); field_ident();
				setState(331); match(OPEN_PARENTHESES);
				setState(333);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << CHAR) | (1L << STR))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (NEW - 66)) | (1L << (THIS - 66)) | (1L << (IDENT - 66)))) != 0)) {
					{
					setState(332); arg_list();
					}
				}

				setState(335); match(CLOSE_PARENTHESES);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assign_lhsContext extends ParserRuleContext {
		public Assign_lhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_lhs; }
	 
		public Assign_lhsContext() { }
		public void copyFrom(Assign_lhsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignL_pair_elemContext extends Assign_lhsContext {
		public Pair_elemContext pair_elem() {
			return getRuleContext(Pair_elemContext.class,0);
		}
		public AssignL_pair_elemContext(Assign_lhsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignL_pair_elem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignL_pair_elem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignL_pair_elem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignL_array_elemContext extends Assign_lhsContext {
		public Array_elemContext array_elem() {
			return getRuleContext(Array_elemContext.class,0);
		}
		public AssignL_array_elemContext(Assign_lhsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignL_array_elem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignL_array_elem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignL_array_elem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignL_field_identContext extends Assign_lhsContext {
		public Field_identContext field_ident() {
			return getRuleContext(Field_identContext.class,0);
		}
		public AssignL_field_identContext(Assign_lhsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignL_field_ident(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignL_field_ident(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignL_field_ident(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignL_IDENTContext extends Assign_lhsContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public AssignL_IDENTContext(Assign_lhsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignL_IDENT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignL_IDENT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignL_IDENT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assign_lhsContext assign_lhs() throws RecognitionException {
		Assign_lhsContext _localctx = new Assign_lhsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_assign_lhs);
		try {
			setState(343);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				_localctx = new AssignL_IDENTContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(339); match(IDENT);
				}
				break;
			case 2:
				_localctx = new AssignL_array_elemContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(340); array_elem();
				}
				break;
			case 3:
				_localctx = new AssignL_pair_elemContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(341); pair_elem();
				}
				break;
			case 4:
				_localctx = new AssignL_field_identContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(342); field_ident();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public Wacc_typeContext wacc_type() {
			return getRuleContext(Wacc_typeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345); wacc_type(0);
			setState(346); match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_listContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public Param_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterParam_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitParam_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParam_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Param_listContext param_list() throws RecognitionException {
		Param_listContext _localctx = new Param_listContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_param_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348); param();
			setState(353);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(349); match(COMMA);
				setState(350); param();
				}
				}
				setState(355);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncContext extends ParserRuleContext {
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode IS() { return getToken(BasicParser.IS, 0); }
		public Param_listContext param_list() {
			return getRuleContext(Param_listContext.class,0);
		}
		public Wacc_typeContext wacc_type() {
			return getRuleContext(Wacc_typeContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356); wacc_type(0);
			setState(357); match(IDENT);
			setState(358); match(OPEN_PARENTHESES);
			setState(360);
			_la = _input.LA(1);
			if (((((_la - 53)) & ~0x3f) == 0 && ((1L << (_la - 53)) & ((1L << (INTTYPE - 53)) | (1L << (BOOLTYPE - 53)) | (1L << (CHARTYPE - 53)) | (1L << (STRINGTYPE - 53)) | (1L << (PAIRTYPE - 53)) | (1L << (IDENT - 53)))) != 0)) {
				{
				setState(359); param_list();
				}
			}

			setState(362); match(CLOSE_PARENTHESES);
			setState(363); match(IS);
			setState(364); stat(0);
			setState(365); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EncapsContext extends ParserRuleContext {
		public EncapsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_encaps; }
	 
		public EncapsContext() { }
		public void copyFrom(EncapsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Encaps_PRIVATEContext extends EncapsContext {
		public TerminalNode PRIVATE() { return getToken(BasicParser.PRIVATE, 0); }
		public Encaps_PRIVATEContext(EncapsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterEncaps_PRIVATE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitEncaps_PRIVATE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitEncaps_PRIVATE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Encaps_PUBLICContext extends EncapsContext {
		public TerminalNode PUBLIC() { return getToken(BasicParser.PUBLIC, 0); }
		public Encaps_PUBLICContext(EncapsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterEncaps_PUBLIC(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitEncaps_PUBLIC(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitEncaps_PUBLIC(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Encaps_PROTECTEDContext extends EncapsContext {
		public TerminalNode PROTECTED() { return getToken(BasicParser.PROTECTED, 0); }
		public Encaps_PROTECTEDContext(EncapsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterEncaps_PROTECTED(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitEncaps_PROTECTED(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitEncaps_PROTECTED(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EncapsContext encaps() throws RecognitionException {
		EncapsContext _localctx = new EncapsContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_encaps);
		try {
			setState(370);
			switch (_input.LA(1)) {
			case PRIVATE:
				_localctx = new Encaps_PRIVATEContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(367); match(PRIVATE);
				}
				break;
			case PUBLIC:
				_localctx = new Encaps_PUBLICContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(368); match(PUBLIC);
				}
				break;
			case PROTECTED:
				_localctx = new Encaps_PROTECTEDContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(369); match(PROTECTED);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_fieldContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(BasicParser.SEMICOLON, 0); }
		public Wacc_typeContext wacc_type() {
			return getRuleContext(Wacc_typeContext.class,0);
		}
		public EncapsContext encaps() {
			return getRuleContext(EncapsContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public Class_fieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterClass_field(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitClass_field(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitClass_field(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_fieldContext class_field() throws RecognitionException {
		Class_fieldContext _localctx = new Class_fieldContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_class_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372); encaps();
			setState(373); wacc_type(0);
			setState(374); match(IDENT);
			setState(375); match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_constrContext extends ParserRuleContext {
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode IS() { return getToken(BasicParser.IS, 0); }
		public Param_listContext param_list() {
			return getRuleContext(Param_listContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public EncapsContext encaps() {
			return getRuleContext(EncapsContext.class,0);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public Class_constrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_constr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterClass_constr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitClass_constr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitClass_constr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_constrContext class_constr() throws RecognitionException {
		Class_constrContext _localctx = new Class_constrContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_class_constr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377); encaps();
			setState(378); match(IDENT);
			setState(379); match(OPEN_PARENTHESES);
			setState(381);
			_la = _input.LA(1);
			if (((((_la - 53)) & ~0x3f) == 0 && ((1L << (_la - 53)) & ((1L << (INTTYPE - 53)) | (1L << (BOOLTYPE - 53)) | (1L << (CHARTYPE - 53)) | (1L << (STRINGTYPE - 53)) | (1L << (PAIRTYPE - 53)) | (1L << (IDENT - 53)))) != 0)) {
				{
				setState(380); param_list();
				}
			}

			setState(383); match(CLOSE_PARENTHESES);
			setState(384); match(IS);
			setState(385); stat(0);
			setState(386); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_destrContext extends ParserRuleContext {
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode IS() { return getToken(BasicParser.IS, 0); }
		public TerminalNode DESTR() { return getToken(BasicParser.DESTR, 0); }
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public EncapsContext encaps() {
			return getRuleContext(EncapsContext.class,0);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public Class_destrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_destr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterClass_destr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitClass_destr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitClass_destr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_destrContext class_destr() throws RecognitionException {
		Class_destrContext _localctx = new Class_destrContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_class_destr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388); encaps();
			setState(389); match(DESTR);
			setState(390); match(IDENT);
			setState(391); match(OPEN_PARENTHESES);
			setState(392); match(CLOSE_PARENTHESES);
			setState(393); match(IS);
			setState(394); stat(0);
			setState(395); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_funcContext extends ParserRuleContext {
		public FuncContext func() {
			return getRuleContext(FuncContext.class,0);
		}
		public EncapsContext encaps() {
			return getRuleContext(EncapsContext.class,0);
		}
		public Class_funcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_func; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterClass_func(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitClass_func(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitClass_func(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_funcContext class_func() throws RecognitionException {
		Class_funcContext _localctx = new Class_funcContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_class_func);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397); encaps();
			setState(398); func();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_bodyContext extends ParserRuleContext {
		public Class_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_body; }
	 
		public Class_bodyContext() { }
		public void copyFrom(Class_bodyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Class_body_constrContext extends Class_bodyContext {
		public Class_constrContext class_constr() {
			return getRuleContext(Class_constrContext.class,0);
		}
		public Class_body_constrContext(Class_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterClass_body_constr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitClass_body_constr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitClass_body_constr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Class_body_destrContext extends Class_bodyContext {
		public Class_destrContext class_destr() {
			return getRuleContext(Class_destrContext.class,0);
		}
		public Class_body_destrContext(Class_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterClass_body_destr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitClass_body_destr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitClass_body_destr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Class_body_funcContext extends Class_bodyContext {
		public Class_funcContext class_func() {
			return getRuleContext(Class_funcContext.class,0);
		}
		public Class_body_funcContext(Class_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterClass_body_func(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitClass_body_func(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitClass_body_func(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_bodyContext class_body() throws RecognitionException {
		Class_bodyContext _localctx = new Class_bodyContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_class_body);
		try {
			setState(403);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				_localctx = new Class_body_constrContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(400); class_constr();
				}
				break;
			case 2:
				_localctx = new Class_body_funcContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(401); class_func();
				}
				break;
			case 3:
				_localctx = new Class_body_destrContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(402); class_destr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Wacc_classContext extends ParserRuleContext {
		public List<Class_bodyContext> class_body() {
			return getRuleContexts(Class_bodyContext.class);
		}
		public TerminalNode IS() { return getToken(BasicParser.IS, 0); }
		public TerminalNode IDENT(int i) {
			return getToken(BasicParser.IDENT, i);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public TerminalNode CLASS() { return getToken(BasicParser.CLASS, 0); }
		public List<Class_fieldContext> class_field() {
			return getRuleContexts(Class_fieldContext.class);
		}
		public Class_fieldContext class_field(int i) {
			return getRuleContext(Class_fieldContext.class,i);
		}
		public List<TerminalNode> IDENT() { return getTokens(BasicParser.IDENT); }
		public TerminalNode EXTENDS() { return getToken(BasicParser.EXTENDS, 0); }
		public Class_bodyContext class_body(int i) {
			return getRuleContext(Class_bodyContext.class,i);
		}
		public Wacc_classContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wacc_class; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterWacc_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitWacc_class(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitWacc_class(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Wacc_classContext wacc_class() throws RecognitionException {
		Wacc_classContext _localctx = new Wacc_classContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_wacc_class);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(405); match(CLASS);
			setState(406); match(IDENT);
			setState(409);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(407); match(EXTENDS);
				setState(408); match(IDENT);
				}
			}

			setState(411); match(IS);
			setState(415);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(412); class_field();
					}
					} 
				}
				setState(417);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			setState(421);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 63)) & ~0x3f) == 0 && ((1L << (_la - 63)) & ((1L << (PUBLIC - 63)) | (1L << (PRIVATE - 63)) | (1L << (PROTECTED - 63)))) != 0)) {
				{
				{
				setState(418); class_body();
				}
				}
				setState(423);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(424); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgContext extends ParserRuleContext {
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public Wacc_classContext wacc_class(int i) {
			return getRuleContext(Wacc_classContext.class,i);
		}
		public List<Wacc_classContext> wacc_class() {
			return getRuleContexts(Wacc_classContext.class);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_prog);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(426); match(BEGIN);
			setState(430);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(427); func();
					}
					} 
				}
				setState(432);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			}
			setState(436);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CLASS) {
				{
				{
				setState(433); wacc_class();
				}
				}
				setState(438);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(439); stat(0);
			setState(440); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(BasicParser.EOF, 0); }
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public MainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterMain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitMain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitMain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainContext main() throws RecognitionException {
		MainContext _localctx = new MainContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_main);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442); prog();
			setState(443); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 15: return expr_sempred((ExprContext)_localctx, predIndex);
		case 17: return stat_sempred((StatContext)_localctx, predIndex);
		case 20: return wacc_type_sempred((Wacc_typeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean stat_sempred(StatContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4: return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean wacc_type_sempred(Wacc_typeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5: return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 8);
		case 1: return precpred(_ctx, 7);
		case 2: return precpred(_ctx, 6);
		case 3: return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3J\u01c0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\5\2O\n\2\3\2\3\2\3\2\7\2T"+
		"\n\2\f\2\16\2W\13\2\3\3\3\3\3\4\3\4\3\4\3\4\7\4_\n\4\f\4\16\4b\13\4\5"+
		"\4d\n\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\5\7n\n\7\3\b\3\b\5\br\n\b\3\t"+
		"\5\tu\n\t\3\t\3\t\3\n\3\n\3\n\3\n\5\n}\n\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\6\13\u0086\n\13\r\13\16\13\u0087\3\f\3\f\3\f\3\f\3\f\5\f\u008f\n"+
		"\f\3\r\3\r\5\r\u0093\n\r\3\16\3\16\3\16\5\16\u0098\n\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\5\17\u00a0\n\17\3\20\3\20\5\20\u00a4\n\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\5\21\u00b8\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u00ca\n\21\f\21\16\21\u00cd\13"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00d5\n\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\5\23\u0105\n\23\3\23\5\23\u0108\n\23\3\23\3\23\3\23\7\23\u010d\n\23\f"+
		"\23\16\23\u0110\13\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25"+
		"\3\25\5\25\u011d\n\25\3\26\3\26\3\26\3\26\5\26\u0123\n\26\3\26\3\26\3"+
		"\26\7\26\u0128\n\26\f\26\16\26\u012b\13\26\3\27\3\27\3\27\3\27\5\27\u0131"+
		"\n\27\3\30\3\30\3\30\7\30\u0136\n\30\f\30\16\30\u0139\13\30\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u0149"+
		"\n\31\3\31\3\31\3\31\3\31\3\31\5\31\u0150\n\31\3\31\3\31\5\31\u0154\n"+
		"\31\3\32\3\32\3\32\3\32\5\32\u015a\n\32\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\7\34\u0162\n\34\f\34\16\34\u0165\13\34\3\35\3\35\3\35\3\35\5\35\u016b"+
		"\n\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\5\36\u0175\n\36\3\37\3\37"+
		"\3\37\3\37\3\37\3 \3 \3 \3 \5 \u0180\n \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!"+
		"\3!\3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\5#\u0196\n#\3$\3$\3$\3$\5$\u019c\n$"+
		"\3$\3$\7$\u01a0\n$\f$\16$\u01a3\13$\3$\7$\u01a6\n$\f$\16$\u01a9\13$\3"+
		"$\3$\3%\3%\7%\u01af\n%\f%\16%\u01b2\13%\3%\7%\u01b5\n%\f%\16%\u01b8\13"+
		"%\3%\3%\3%\3&\3&\3&\3&\2\5 $*\'\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@BDFHJ\2\2\u01ee\2N\3\2\2\2\4X\3\2\2\2\6Z\3\2"+
		"\2\2\bg\3\2\2\2\ni\3\2\2\2\fm\3\2\2\2\16q\3\2\2\2\20t\3\2\2\2\22x\3\2"+
		"\2\2\24\u0080\3\2\2\2\26\u008e\3\2\2\2\30\u0092\3\2\2\2\32\u0097\3\2\2"+
		"\2\34\u009f\3\2\2\2\36\u00a3\3\2\2\2 \u00b7\3\2\2\2\"\u00d4\3\2\2\2$\u0107"+
		"\3\2\2\2&\u0111\3\2\2\2(\u011c\3\2\2\2*\u0122\3\2\2\2,\u0130\3\2\2\2."+
		"\u0132\3\2\2\2\60\u0153\3\2\2\2\62\u0159\3\2\2\2\64\u015b\3\2\2\2\66\u015e"+
		"\3\2\2\28\u0166\3\2\2\2:\u0174\3\2\2\2<\u0176\3\2\2\2>\u017b\3\2\2\2@"+
		"\u0186\3\2\2\2B\u018f\3\2\2\2D\u0195\3\2\2\2F\u0197\3\2\2\2H\u01ac\3\2"+
		"\2\2J\u01bc\3\2\2\2LM\7I\2\2MO\7\34\2\2NL\3\2\2\2NO\3\2\2\2OP\3\2\2\2"+
		"PU\7J\2\2QR\7\34\2\2RT\7J\2\2SQ\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2"+
		"V\3\3\2\2\2WU\3\2\2\2XY\7<\2\2Y\5\3\2\2\2Zc\7\27\2\2[`\5 \21\2\\]\7\31"+
		"\2\2]_\5 \21\2^\\\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2ad\3\2\2\2b`\3"+
		"\2\2\2c[\3\2\2\2cd\3\2\2\2de\3\2\2\2ef\7\30\2\2f\7\3\2\2\2gh\7?\2\2h\t"+
		"\3\2\2\2ij\7>\2\2j\13\3\2\2\2kn\7\37\2\2ln\7 \2\2mk\3\2\2\2ml\3\2\2\2"+
		"n\r\3\2\2\2or\7\3\2\2pr\7\4\2\2qo\3\2\2\2qp\3\2\2\2r\17\3\2\2\2su\5\16"+
		"\b\2ts\3\2\2\2tu\3\2\2\2uv\3\2\2\2vw\7\36\2\2w\21\3\2\2\2xy\7D\2\2yz\7"+
		"J\2\2z|\7\25\2\2{}\5.\30\2|{\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177\7\26\2\2"+
		"\177\23\3\2\2\2\u0080\u0085\7J\2\2\u0081\u0082\7\27\2\2\u0082\u0083\5"+
		" \21\2\u0083\u0084\7\30\2\2\u0084\u0086\3\2\2\2\u0085\u0081\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\25\3\2\2"+
		"\2\u0089\u008f\7\20\2\2\u008a\u008f\7\21\2\2\u008b\u008f\7\22\2\2\u008c"+
		"\u008f\7\23\2\2\u008d\u008f\5\16\b\2\u008e\u0089\3\2\2\2\u008e\u008a\3"+
		"\2\2\2\u008e\u008b\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008d\3\2\2\2\u008f"+
		"\27\3\2\2\2\u0090\u0093\7\3\2\2\u0091\u0093\7\4\2\2\u0092\u0090\3\2\2"+
		"\2\u0092\u0091\3\2\2\2\u0093\31\3\2\2\2\u0094\u0098\7\5\2\2\u0095\u0098"+
		"\7\6\2\2\u0096\u0098\7\7\2\2\u0097\u0094\3\2\2\2\u0097\u0095\3\2\2\2\u0097"+
		"\u0096\3\2\2\2\u0098\33\3\2\2\2\u0099\u00a0\7\b\2\2\u009a\u00a0\7\t\2"+
		"\2\u009b\u00a0\7\n\2\2\u009c\u00a0\7\13\2\2\u009d\u00a0\7\f\2\2\u009e"+
		"\u00a0\7\r\2\2\u009f\u0099\3\2\2\2\u009f\u009a\3\2\2\2\u009f\u009b\3\2"+
		"\2\2\u009f\u009c\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u009e\3\2\2\2\u00a0"+
		"\35\3\2\2\2\u00a1\u00a4\7\16\2\2\u00a2\u00a4\7\17\2\2\u00a3\u00a1\3\2"+
		"\2\2\u00a3\u00a2\3\2\2\2\u00a4\37\3\2\2\2\u00a5\u00a6\b\21\1\2\u00a6\u00a7"+
		"\5\26\f\2\u00a7\u00a8\5 \21\13\u00a8\u00b8\3\2\2\2\u00a9\u00b8\5\20\t"+
		"\2\u00aa\u00b8\5\f\7\2\u00ab\u00b8\5\n\6\2\u00ac\u00b8\5\b\5\2\u00ad\u00b8"+
		"\5\4\3\2\u00ae\u00b8\7J\2\2\u00af\u00b8\5\24\13\2\u00b0\u00b1\7\25\2\2"+
		"\u00b1\u00b2\5 \21\2\u00b2\u00b3\7\26\2\2\u00b3\u00b8\3\2\2\2\u00b4\u00b8"+
		"\5\2\2\2\u00b5\u00b8\5\22\n\2\u00b6\u00b8\7I\2\2\u00b7\u00a5\3\2\2\2\u00b7"+
		"\u00a9\3\2\2\2\u00b7\u00aa\3\2\2\2\u00b7\u00ab\3\2\2\2\u00b7\u00ac\3\2"+
		"\2\2\u00b7\u00ad\3\2\2\2\u00b7\u00ae\3\2\2\2\u00b7\u00af\3\2\2\2\u00b7"+
		"\u00b0\3\2\2\2\u00b7\u00b4\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b6\3\2"+
		"\2\2\u00b8\u00cb\3\2\2\2\u00b9\u00ba\f\n\2\2\u00ba\u00bb\5\32\16\2\u00bb"+
		"\u00bc\5 \21\13\u00bc\u00ca\3\2\2\2\u00bd\u00be\f\t\2\2\u00be\u00bf\5"+
		"\30\r\2\u00bf\u00c0\5 \21\n\u00c0\u00ca\3\2\2\2\u00c1\u00c2\f\b\2\2\u00c2"+
		"\u00c3\5\34\17\2\u00c3\u00c4\5 \21\t\u00c4\u00ca\3\2\2\2\u00c5\u00c6\f"+
		"\7\2\2\u00c6\u00c7\5\36\20\2\u00c7\u00c8\5 \21\b\u00c8\u00ca\3\2\2\2\u00c9"+
		"\u00b9\3\2\2\2\u00c9\u00bd\3\2\2\2\u00c9\u00c1\3\2\2\2\u00c9\u00c5\3\2"+
		"\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc"+
		"!\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00d5\5(\25\2\u00cf\u00d0\5*\26\2"+
		"\u00d0\u00d1\7\27\2\2\u00d1\u00d2\7\30\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d5"+
		"\7;\2\2\u00d4\u00ce\3\2\2\2\u00d4\u00cf\3\2\2\2\u00d4\u00d3\3\2\2\2\u00d5"+
		"#\3\2\2\2\u00d6\u00d7\b\23\1\2\u00d7\u0108\7$\2\2\u00d8\u00d9\5*\26\2"+
		"\u00d9\u00da\7J\2\2\u00da\u00db\7-\2\2\u00db\u00dc\5\60\31\2\u00dc\u0108"+
		"\3\2\2\2\u00dd\u00de\5\62\32\2\u00de\u00df\7-\2\2\u00df\u00e0\5\60\31"+
		"\2\u00e0\u0108\3\2\2\2\u00e1\u00e2\7%\2\2\u00e2\u0108\5\62\32\2\u00e3"+
		"\u00e4\7&\2\2\u00e4\u0108\5 \21\2\u00e5\u00e6\7\'\2\2\u00e6\u0108\5 \21"+
		"\2\u00e7\u00e8\7(\2\2\u00e8\u0108\5 \21\2\u00e9\u00ea\7)\2\2\u00ea\u0108"+
		"\5 \21\2\u00eb\u00ec\7*\2\2\u00ec\u0108\5 \21\2\u00ed\u00ee\7.\2\2\u00ee"+
		"\u00ef\5 \21\2\u00ef\u00f0\7/\2\2\u00f0\u00f1\5$\23\2\u00f1\u00f2\7\60"+
		"\2\2\u00f2\u00f3\5$\23\2\u00f3\u00f4\7\61\2\2\u00f4\u0108\3\2\2\2\u00f5"+
		"\u00f6\7\62\2\2\u00f6\u00f7\5 \21\2\u00f7\u00f8\7\63\2\2\u00f8\u00f9\5"+
		"$\23\2\u00f9\u00fa\7\64\2\2\u00fa\u0108\3\2\2\2\u00fb\u00fc\7!\2\2\u00fc"+
		"\u00fd\5$\23\2\u00fd\u00fe\7\"\2\2\u00fe\u0108\3\2\2\2\u00ff\u0100\7E"+
		"\2\2\u0100\u0108\7J\2\2\u0101\u0102\7H\2\2\u0102\u0104\7\25\2\2\u0103"+
		"\u0105\5.\30\2\u0104\u0103\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0106\3\2"+
		"\2\2\u0106\u0108\7\26\2\2\u0107\u00d6\3\2\2\2\u0107\u00d8\3\2\2\2\u0107"+
		"\u00dd\3\2\2\2\u0107\u00e1\3\2\2\2\u0107\u00e3\3\2\2\2\u0107\u00e5\3\2"+
		"\2\2\u0107\u00e7\3\2\2\2\u0107\u00e9\3\2\2\2\u0107\u00eb\3\2\2\2\u0107"+
		"\u00ed\3\2\2\2\u0107\u00f5\3\2\2\2\u0107\u00fb\3\2\2\2\u0107\u00ff\3\2"+
		"\2\2\u0107\u0101\3\2\2\2\u0108\u010e\3\2\2\2\u0109\u010a\f\5\2\2\u010a"+
		"\u010b\7\24\2\2\u010b\u010d\5$\23\6\u010c\u0109\3\2\2\2\u010d\u0110\3"+
		"\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f%\3\2\2\2\u0110\u010e"+
		"\3\2\2\2\u0111\u0112\7;\2\2\u0112\u0113\7\25\2\2\u0113\u0114\5\"\22\2"+
		"\u0114\u0115\7\31\2\2\u0115\u0116\5\"\22\2\u0116\u0117\7\26\2\2\u0117"+
		"\'\3\2\2\2\u0118\u011d\7\67\2\2\u0119\u011d\78\2\2\u011a\u011d\79\2\2"+
		"\u011b\u011d\7:\2\2\u011c\u0118\3\2\2\2\u011c\u0119\3\2\2\2\u011c\u011a"+
		"\3\2\2\2\u011c\u011b\3\2\2\2\u011d)\3\2\2\2\u011e\u011f\b\26\1\2\u011f"+
		"\u0123\5(\25\2\u0120\u0123\5&\24\2\u0121\u0123\7J\2\2\u0122\u011e\3\2"+
		"\2\2\u0122\u0120\3\2\2\2\u0122\u0121\3\2\2\2\u0123\u0129\3\2\2\2\u0124"+
		"\u0125\f\4\2\2\u0125\u0126\7\27\2\2\u0126\u0128\7\30\2\2\u0127\u0124\3"+
		"\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a"+
		"+\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u012d\7\65\2\2\u012d\u0131\5 \21\2"+
		"\u012e\u012f\7\66\2\2\u012f\u0131\5 \21\2\u0130\u012c\3\2\2\2\u0130\u012e"+
		"\3\2\2\2\u0131-\3\2\2\2\u0132\u0137\5 \21\2\u0133\u0134\7\31\2\2\u0134"+
		"\u0136\5 \21\2\u0135\u0133\3\2\2\2\u0136\u0139\3\2\2\2\u0137\u0135\3\2"+
		"\2\2\u0137\u0138\3\2\2\2\u0138/\3\2\2\2\u0139\u0137\3\2\2\2\u013a\u0154"+
		"\5 \21\2\u013b\u0154\5\6\4\2\u013c\u013d\7+\2\2\u013d\u013e\7\25\2\2\u013e"+
		"\u013f\5 \21\2\u013f\u0140\7\31\2\2\u0140\u0141\5 \21\2\u0141\u0142\7"+
		"\26\2\2\u0142\u0154\3\2\2\2\u0143\u0154\5,\27\2\u0144\u0145\7,\2\2\u0145"+
		"\u0146\7J\2\2\u0146\u0148\7\25\2\2\u0147\u0149\5.\30\2\u0148\u0147\3\2"+
		"\2\2\u0148\u0149\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u0154\7\26\2\2\u014b"+
		"\u014c\7,\2\2\u014c\u014d\5\2\2\2\u014d\u014f\7\25\2\2\u014e\u0150\5."+
		"\30\2\u014f\u014e\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151\3\2\2\2\u0151"+
		"\u0152\7\26\2\2\u0152\u0154\3\2\2\2\u0153\u013a\3\2\2\2\u0153\u013b\3"+
		"\2\2\2\u0153\u013c\3\2\2\2\u0153\u0143\3\2\2\2\u0153\u0144\3\2\2\2\u0153"+
		"\u014b\3\2\2\2\u0154\61\3\2\2\2\u0155\u015a\7J\2\2\u0156\u015a\5\24\13"+
		"\2\u0157\u015a\5,\27\2\u0158\u015a\5\2\2\2\u0159\u0155\3\2\2\2\u0159\u0156"+
		"\3\2\2\2\u0159\u0157\3\2\2\2\u0159\u0158\3\2\2\2\u015a\63\3\2\2\2\u015b"+
		"\u015c\5*\26\2\u015c\u015d\7J\2\2\u015d\65\3\2\2\2\u015e\u0163\5\64\33"+
		"\2\u015f\u0160\7\31\2\2\u0160\u0162\5\64\33\2\u0161\u015f\3\2\2\2\u0162"+
		"\u0165\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0164\3\2\2\2\u0164\67\3\2\2"+
		"\2\u0165\u0163\3\2\2\2\u0166\u0167\5*\26\2\u0167\u0168\7J\2\2\u0168\u016a"+
		"\7\25\2\2\u0169\u016b\5\66\34\2\u016a\u0169\3\2\2\2\u016a\u016b\3\2\2"+
		"\2\u016b\u016c\3\2\2\2\u016c\u016d\7\26\2\2\u016d\u016e\7#\2\2\u016e\u016f"+
		"\5$\23\2\u016f\u0170\7\"\2\2\u01709\3\2\2\2\u0171\u0175\7B\2\2\u0172\u0175"+
		"\7A\2\2\u0173\u0175\7C\2\2\u0174\u0171\3\2\2\2\u0174\u0172\3\2\2\2\u0174"+
		"\u0173\3\2\2\2\u0175;\3\2\2\2\u0176\u0177\5:\36\2\u0177\u0178\5*\26\2"+
		"\u0178\u0179\7J\2\2\u0179\u017a\7\24\2\2\u017a=\3\2\2\2\u017b\u017c\5"+
		":\36\2\u017c\u017d\7J\2\2\u017d\u017f\7\25\2\2\u017e\u0180\5\66\34\2\u017f"+
		"\u017e\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0182\7\26"+
		"\2\2\u0182\u0183\7#\2\2\u0183\u0184\5$\23\2\u0184\u0185\7\"\2\2\u0185"+
		"?\3\2\2\2\u0186\u0187\5:\36\2\u0187\u0188\7F\2\2\u0188\u0189\7J\2\2\u0189"+
		"\u018a\7\25\2\2\u018a\u018b\7\26\2\2\u018b\u018c\7#\2\2\u018c\u018d\5"+
		"$\23\2\u018d\u018e\7\"\2\2\u018eA\3\2\2\2\u018f\u0190\5:\36\2\u0190\u0191"+
		"\58\35\2\u0191C\3\2\2\2\u0192\u0196\5> \2\u0193\u0196\5B\"\2\u0194\u0196"+
		"\5@!\2\u0195\u0192\3\2\2\2\u0195\u0193\3\2\2\2\u0195\u0194\3\2\2\2\u0196"+
		"E\3\2\2\2\u0197\u0198\7@\2\2\u0198\u019b\7J\2\2\u0199\u019a\7G\2\2\u019a"+
		"\u019c\7J\2\2\u019b\u0199\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u019d\3\2"+
		"\2\2\u019d\u01a1\7#\2\2\u019e\u01a0\5<\37\2\u019f\u019e\3\2\2\2\u01a0"+
		"\u01a3\3\2\2\2\u01a1\u019f\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a7\3\2"+
		"\2\2\u01a3\u01a1\3\2\2\2\u01a4\u01a6\5D#\2\u01a5\u01a4\3\2\2\2\u01a6\u01a9"+
		"\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8\u01aa\3\2\2\2\u01a9"+
		"\u01a7\3\2\2\2\u01aa\u01ab\7\"\2\2\u01abG\3\2\2\2\u01ac\u01b0\7!\2\2\u01ad"+
		"\u01af\58\35\2\u01ae\u01ad\3\2\2\2\u01af\u01b2\3\2\2\2\u01b0\u01ae\3\2"+
		"\2\2\u01b0\u01b1\3\2\2\2\u01b1\u01b6\3\2\2\2\u01b2\u01b0\3\2\2\2\u01b3"+
		"\u01b5\5F$\2\u01b4\u01b3\3\2\2\2\u01b5\u01b8\3\2\2\2\u01b6\u01b4\3\2\2"+
		"\2\u01b6\u01b7\3\2\2\2\u01b7\u01b9\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b9\u01ba"+
		"\5$\23\2\u01ba\u01bb\7\"\2\2\u01bbI\3\2\2\2\u01bc\u01bd\5H%\2\u01bd\u01be"+
		"\7\2\2\3\u01beK\3\2\2\2*NU`cmqt|\u0087\u008e\u0092\u0097\u009f\u00a3\u00b7"+
		"\u00c9\u00cb\u00d4\u0104\u0107\u010e\u011c\u0122\u0129\u0130\u0137\u0148"+
		"\u014f\u0153\u0159\u0163\u016a\u0174\u017f\u0195\u019b\u01a1\u01a7\u01b0"+
		"\u01b6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}