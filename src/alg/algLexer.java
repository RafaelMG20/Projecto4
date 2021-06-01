// Generated from C:\Users\sopos\Desktop\Projecto4\algLexer.g4 by ANTLR 4.9.1
package alg;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class algLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INT=1, BOOL=2, FLOAT=3, STRING=4, VOID=5, SIZEOF=6, NULL=7, ALG=8, TRUE=9, 
		FALSE=10, WHILE=11, DO=12, FINALLY=13, LEAVE=14, RESTART=15, RETURN=16, 
		IF=17, THEN=18, ELSE=19, WRITE=20, WRITELN=21, CHANGE_LINE=22, CARRIAGE_RETURN=23, 
		SPACE=24, TABULACAO_HORIZONTAL=25, COMMENT_E=26, COMMENT_O=27, VIRGULA=28, 
		E=29, PONTO_VIRGULA=30, IDENT=31, ID=32, LETTER=33, LATIN1=34, LITERAL_INTEIRO=35, 
		LITERAL_DECIMAL=36, LITERAL_EXPONENCIAL=37, CADEIA_CARACTERES=38, EQUAL=39, 
		MENORQ=40, MAIORQ=41, AT_SIGN=42, L_BRACE=43, R_BRACE=44, LP=45, RP=46, 
		INDEX_POINT_L=47, INDEX_POINT_R=48, IDEY=49, MULT_DIV=50, SOMA_SUB=51, 
		EQUAL_DIF=52, COMPARATOR=53, E_LOGICO=54, OU_LOGICO=55, D_MAIORQ=56, WS=57, 
		COMMENTS=58;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"INT", "BOOL", "FLOAT", "STRING", "VOID", "SIZEOF", "NULL", "ALG", "TRUE", 
			"FALSE", "WHILE", "DO", "FINALLY", "LEAVE", "RESTART", "RETURN", "IF", 
			"THEN", "ELSE", "WRITE", "WRITELN", "CHANGE_LINE", "CARRIAGE_RETURN", 
			"SPACE", "TABULACAO_HORIZONTAL", "COMMENT_E", "COMMENT_O", "VIRGULA", 
			"E", "PONTO_VIRGULA", "IDENT", "ID", "LETTER", "LATIN1", "LITERAL_INTEIRO", 
			"LITERAL_DECIMAL", "LITERAL_EXPONENCIAL", "CADEIA_CARACTERES", "EQUAL", 
			"MENORQ", "MAIORQ", "AT_SIGN", "L_BRACE", "R_BRACE", "LP", "RP", "INDEX_POINT_L", 
			"INDEX_POINT_R", "IDEY", "MULT_DIV", "SOMA_SUB", "EQUAL_DIF", "COMPARATOR", 
			"E_LOGICO", "OU_LOGICO", "D_MAIORQ", "WS", "COMMENTS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "'bool'", "'float'", "'string'", "'void'", "'sizeof'", 
			"'null'", "'alg'", "'true'", "'false'", "'while'", "'do'", "'finally'", 
			"'leave'", "'restart'", "'return'", "'if'", "'then'", "'else'", "'write'", 
			"'writeln'", "'\\n'", "'\\r'", "' '", "'\\t'", null, null, "','", "'e'", 
			"';'", null, null, null, null, null, null, null, null, "'='", "'<'", 
			"'>'", "'@'", "'{'", "'}'", "'('", "')'", "'['", "']'", null, null, null, 
			null, null, "'&&'", "'||'", "'>>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INT", "BOOL", "FLOAT", "STRING", "VOID", "SIZEOF", "NULL", "ALG", 
			"TRUE", "FALSE", "WHILE", "DO", "FINALLY", "LEAVE", "RESTART", "RETURN", 
			"IF", "THEN", "ELSE", "WRITE", "WRITELN", "CHANGE_LINE", "CARRIAGE_RETURN", 
			"SPACE", "TABULACAO_HORIZONTAL", "COMMENT_E", "COMMENT_O", "VIRGULA", 
			"E", "PONTO_VIRGULA", "IDENT", "ID", "LETTER", "LATIN1", "LITERAL_INTEIRO", 
			"LITERAL_DECIMAL", "LITERAL_EXPONENCIAL", "CADEIA_CARACTERES", "EQUAL", 
			"MENORQ", "MAIORQ", "AT_SIGN", "L_BRACE", "R_BRACE", "LP", "RP", "INDEX_POINT_L", 
			"INDEX_POINT_R", "IDEY", "MULT_DIV", "SOMA_SUB", "EQUAL_DIF", "COMPARATOR", 
			"E_LOGICO", "OU_LOGICO", "D_MAIORQ", "WS", "COMMENTS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public algLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "algLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2<\u01a1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\33\7\33\u0106\n\33\f\33\16\33\u0109\13\33\3\33\3\33\5\33\u010d"+
		"\n\33\3\33\3\33\3\34\3\34\3\34\3\34\6\34\u0115\n\34\r\34\16\34\u0116\3"+
		"\34\3\34\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \7 \u0127"+
		"\n \f \16 \u012a\13 \3!\3!\3\"\3\"\5\"\u0130\n\"\3#\3#\3$\3$\3$\7$\u0137"+
		"\n$\f$\16$\u013a\13$\5$\u013c\n$\3%\3%\3%\6%\u0141\n%\r%\16%\u0142\3&"+
		"\3&\3&\6&\u0148\n&\r&\16&\u0149\5&\u014c\n&\3&\3&\3&\5&\u0151\n&\3&\6"+
		"&\u0154\n&\r&\16&\u0155\5&\u0158\n&\3\'\3\'\6\'\u015c\n\'\r\'\16\'\u015d"+
		"\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61"+
		"\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\65\3\65\5\65\u0180\n\65"+
		"\3\66\3\66\3\66\3\66\5\66\u0186\n\66\3\67\3\67\3\67\38\38\38\39\39\39"+
		"\3:\3:\3:\3:\3;\3;\3;\3;\7;\u0199\n;\f;\16;\u019c\13;\3;\3;\3;\3;\6\u0107"+
		"\u0116\u015d\u019a\2<\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63"+
		"e\64g\65i\66k\67m8o9q:s;u<\3\2\f\4\2\62;aa\4\2C\\c|\3\2\63;\3\2\62;\4"+
		"\2GGgg\4\2--//\4\2AA\u0080\u0080\5\2\'\',,\61\61\5\2\13\f\17\17\"\"\3"+
		"\2\f\f\2\u01b2\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2"+
		"_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3"+
		"\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\3w\3\2\2"+
		"\2\5{\3\2\2\2\7\u0080\3\2\2\2\t\u0086\3\2\2\2\13\u008d\3\2\2\2\r\u0092"+
		"\3\2\2\2\17\u0099\3\2\2\2\21\u009e\3\2\2\2\23\u00a2\3\2\2\2\25\u00a7\3"+
		"\2\2\2\27\u00ad\3\2\2\2\31\u00b3\3\2\2\2\33\u00b6\3\2\2\2\35\u00be\3\2"+
		"\2\2\37\u00c4\3\2\2\2!\u00cc\3\2\2\2#\u00d3\3\2\2\2%\u00d6\3\2\2\2\'\u00db"+
		"\3\2\2\2)\u00e0\3\2\2\2+\u00e6\3\2\2\2-\u00ee\3\2\2\2/\u00f3\3\2\2\2\61"+
		"\u00f8\3\2\2\2\63\u00fc\3\2\2\2\65\u0101\3\2\2\2\67\u0110\3\2\2\29\u011d"+
		"\3\2\2\2;\u011f\3\2\2\2=\u0121\3\2\2\2?\u0123\3\2\2\2A\u012b\3\2\2\2C"+
		"\u012f\3\2\2\2E\u0131\3\2\2\2G\u013b\3\2\2\2I\u013d\3\2\2\2K\u0144\3\2"+
		"\2\2M\u0159\3\2\2\2O\u0161\3\2\2\2Q\u0163\3\2\2\2S\u0165\3\2\2\2U\u0167"+
		"\3\2\2\2W\u0169\3\2\2\2Y\u016b\3\2\2\2[\u016d\3\2\2\2]\u016f\3\2\2\2_"+
		"\u0171\3\2\2\2a\u0173\3\2\2\2c\u0175\3\2\2\2e\u0177\3\2\2\2g\u0179\3\2"+
		"\2\2i\u017f\3\2\2\2k\u0185\3\2\2\2m\u0187\3\2\2\2o\u018a\3\2\2\2q\u018d"+
		"\3\2\2\2s\u0190\3\2\2\2u\u0194\3\2\2\2wx\7k\2\2xy\7p\2\2yz\7v\2\2z\4\3"+
		"\2\2\2{|\7d\2\2|}\7q\2\2}~\7q\2\2~\177\7n\2\2\177\6\3\2\2\2\u0080\u0081"+
		"\7h\2\2\u0081\u0082\7n\2\2\u0082\u0083\7q\2\2\u0083\u0084\7c\2\2\u0084"+
		"\u0085\7v\2\2\u0085\b\3\2\2\2\u0086\u0087\7u\2\2\u0087\u0088\7v\2\2\u0088"+
		"\u0089\7t\2\2\u0089\u008a\7k\2\2\u008a\u008b\7p\2\2\u008b\u008c\7i\2\2"+
		"\u008c\n\3\2\2\2\u008d\u008e\7x\2\2\u008e\u008f\7q\2\2\u008f\u0090\7k"+
		"\2\2\u0090\u0091\7f\2\2\u0091\f\3\2\2\2\u0092\u0093\7u\2\2\u0093\u0094"+
		"\7k\2\2\u0094\u0095\7|\2\2\u0095\u0096\7g\2\2\u0096\u0097\7q\2\2\u0097"+
		"\u0098\7h\2\2\u0098\16\3\2\2\2\u0099\u009a\7p\2\2\u009a\u009b\7w\2\2\u009b"+
		"\u009c\7n\2\2\u009c\u009d\7n\2\2\u009d\20\3\2\2\2\u009e\u009f\7c\2\2\u009f"+
		"\u00a0\7n\2\2\u00a0\u00a1\7i\2\2\u00a1\22\3\2\2\2\u00a2\u00a3\7v\2\2\u00a3"+
		"\u00a4\7t\2\2\u00a4\u00a5\7w\2\2\u00a5\u00a6\7g\2\2\u00a6\24\3\2\2\2\u00a7"+
		"\u00a8\7h\2\2\u00a8\u00a9\7c\2\2\u00a9\u00aa\7n\2\2\u00aa\u00ab\7u\2\2"+
		"\u00ab\u00ac\7g\2\2\u00ac\26\3\2\2\2\u00ad\u00ae\7y\2\2\u00ae\u00af\7"+
		"j\2\2\u00af\u00b0\7k\2\2\u00b0\u00b1\7n\2\2\u00b1\u00b2\7g\2\2\u00b2\30"+
		"\3\2\2\2\u00b3\u00b4\7f\2\2\u00b4\u00b5\7q\2\2\u00b5\32\3\2\2\2\u00b6"+
		"\u00b7\7h\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7p\2\2\u00b9\u00ba\7c\2\2"+
		"\u00ba\u00bb\7n\2\2\u00bb\u00bc\7n\2\2\u00bc\u00bd\7{\2\2\u00bd\34\3\2"+
		"\2\2\u00be\u00bf\7n\2\2\u00bf\u00c0\7g\2\2\u00c0\u00c1\7c\2\2\u00c1\u00c2"+
		"\7x\2\2\u00c2\u00c3\7g\2\2\u00c3\36\3\2\2\2\u00c4\u00c5\7t\2\2\u00c5\u00c6"+
		"\7g\2\2\u00c6\u00c7\7u\2\2\u00c7\u00c8\7v\2\2\u00c8\u00c9\7c\2\2\u00c9"+
		"\u00ca\7t\2\2\u00ca\u00cb\7v\2\2\u00cb \3\2\2\2\u00cc\u00cd\7t\2\2\u00cd"+
		"\u00ce\7g\2\2\u00ce\u00cf\7v\2\2\u00cf\u00d0\7w\2\2\u00d0\u00d1\7t\2\2"+
		"\u00d1\u00d2\7p\2\2\u00d2\"\3\2\2\2\u00d3\u00d4\7k\2\2\u00d4\u00d5\7h"+
		"\2\2\u00d5$\3\2\2\2\u00d6\u00d7\7v\2\2\u00d7\u00d8\7j\2\2\u00d8\u00d9"+
		"\7g\2\2\u00d9\u00da\7p\2\2\u00da&\3\2\2\2\u00db\u00dc\7g\2\2\u00dc\u00dd"+
		"\7n\2\2\u00dd\u00de\7u\2\2\u00de\u00df\7g\2\2\u00df(\3\2\2\2\u00e0\u00e1"+
		"\7y\2\2\u00e1\u00e2\7t\2\2\u00e2\u00e3\7k\2\2\u00e3\u00e4\7v\2\2\u00e4"+
		"\u00e5\7g\2\2\u00e5*\3\2\2\2\u00e6\u00e7\7y\2\2\u00e7\u00e8\7t\2\2\u00e8"+
		"\u00e9\7k\2\2\u00e9\u00ea\7v\2\2\u00ea\u00eb\7g\2\2\u00eb\u00ec\7n\2\2"+
		"\u00ec\u00ed\7p\2\2\u00ed,\3\2\2\2\u00ee\u00ef\7^\2\2\u00ef\u00f0\7p\2"+
		"\2\u00f0\u00f1\3\2\2\2\u00f1\u00f2\b\27\2\2\u00f2.\3\2\2\2\u00f3\u00f4"+
		"\7^\2\2\u00f4\u00f5\7t\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\b\30\2\2\u00f7"+
		"\60\3\2\2\2\u00f8\u00f9\7\"\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\b\31\2"+
		"\2\u00fb\62\3\2\2\2\u00fc\u00fd\7^\2\2\u00fd\u00fe\7v\2\2\u00fe\u00ff"+
		"\3\2\2\2\u00ff\u0100\b\32\2\2\u0100\64\3\2\2\2\u0101\u0102\7%\2\2\u0102"+
		"\u0103\7%\2\2\u0103\u0107\3\2\2\2\u0104\u0106\13\2\2\2\u0105\u0104\3\2"+
		"\2\2\u0106\u0109\3\2\2\2\u0107\u0108\3\2\2\2\u0107\u0105\3\2\2\2\u0108"+
		"\u010c\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u010d\5-\27\2\u010b\u010d\5/"+
		"\30\2\u010c\u010a\3\2\2\2\u010c\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e"+
		"\u010f\b\33\2\2\u010f\66\3\2\2\2\u0110\u0111\7*\2\2\u0111\u0112\7,\2\2"+
		"\u0112\u0114\3\2\2\2\u0113\u0115\13\2\2\2\u0114\u0113\3\2\2\2\u0115\u0116"+
		"\3\2\2\2\u0116\u0117\3\2\2\2\u0116\u0114\3\2\2\2\u0117\u0118\3\2\2\2\u0118"+
		"\u0119\7,\2\2\u0119\u011a\7+\2\2\u011a\u011b\3\2\2\2\u011b\u011c\b\34"+
		"\2\2\u011c8\3\2\2\2\u011d\u011e\7.\2\2\u011e:\3\2\2\2\u011f\u0120\7g\2"+
		"\2\u0120<\3\2\2\2\u0121\u0122\7=\2\2\u0122>\3\2\2\2\u0123\u0128\5C\"\2"+
		"\u0124\u0127\5C\"\2\u0125\u0127\t\2\2\2\u0126\u0124\3\2\2\2\u0126\u0125"+
		"\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129"+
		"@\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u012c\5C\"\2\u012cB\3\2\2\2\u012d"+
		"\u0130\t\3\2\2\u012e\u0130\5E#\2\u012f\u012d\3\2\2\2\u012f\u012e\3\2\2"+
		"\2\u0130D\3\2\2\2\u0131\u0132\4\u00a2\u0101\2\u0132F\3\2\2\2\u0133\u013c"+
		"\7\62\2\2\u0134\u0138\t\4\2\2\u0135\u0137\t\5\2\2\u0136\u0135\3\2\2\2"+
		"\u0137\u013a\3\2\2\2\u0138\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013c"+
		"\3\2\2\2\u013a\u0138\3\2\2\2\u013b\u0133\3\2\2\2\u013b\u0134\3\2\2\2\u013c"+
		"H\3\2\2\2\u013d\u013e\5G$\2\u013e\u0140\7\60\2\2\u013f\u0141\t\5\2\2\u0140"+
		"\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2"+
		"\2\2\u0143J\3\2\2\2\u0144\u014b\t\5\2\2\u0145\u0147\7\60\2\2\u0146\u0148"+
		"\t\5\2\2\u0147\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u0147\3\2\2\2\u0149"+
		"\u014a\3\2\2\2\u014a\u014c\3\2\2\2\u014b\u0145\3\2\2\2\u014b\u014c\3\2"+
		"\2\2\u014c\u0157\3\2\2\2\u014d\u0150\t\6\2\2\u014e\u0151\t\7\2\2\u014f"+
		"\u0151\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u014f\3\2\2\2\u0151\u0153\3\2"+
		"\2\2\u0152\u0154\t\5\2\2\u0153\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155"+
		"\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0158\3\2\2\2\u0157\u014d\3\2"+
		"\2\2\u0157\u0158\3\2\2\2\u0158L\3\2\2\2\u0159\u015b\7)\2\2\u015a\u015c"+
		"\4\3\1\2\u015b\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015e\3\2\2\2\u015d"+
		"\u015b\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0160\7)\2\2\u0160N\3\2\2\2\u0161"+
		"\u0162\7?\2\2\u0162P\3\2\2\2\u0163\u0164\7>\2\2\u0164R\3\2\2\2\u0165\u0166"+
		"\7@\2\2\u0166T\3\2\2\2\u0167\u0168\7B\2\2\u0168V\3\2\2\2\u0169\u016a\7"+
		"}\2\2\u016aX\3\2\2\2\u016b\u016c\7\177\2\2\u016cZ\3\2\2\2\u016d\u016e"+
		"\7*\2\2\u016e\\\3\2\2\2\u016f\u0170\7+\2\2\u0170^\3\2\2\2\u0171\u0172"+
		"\7]\2\2\u0172`\3\2\2\2\u0173\u0174\7_\2\2\u0174b\3\2\2\2\u0175\u0176\t"+
		"\b\2\2\u0176d\3\2\2\2\u0177\u0178\t\t\2\2\u0178f\3\2\2\2\u0179\u017a\t"+
		"\7\2\2\u017ah\3\2\2\2\u017b\u017c\7?\2\2\u017c\u0180\7?\2\2\u017d\u017e"+
		"\7#\2\2\u017e\u0180\7?\2\2\u017f\u017b\3\2\2\2\u017f\u017d\3\2\2\2\u0180"+
		"j\3\2\2\2\u0181\u0182\7@\2\2\u0182\u0186\7?\2\2\u0183\u0184\7>\2\2\u0184"+
		"\u0186\7?\2\2\u0185\u0181\3\2\2\2\u0185\u0183\3\2\2\2\u0186l\3\2\2\2\u0187"+
		"\u0188\7(\2\2\u0188\u0189\7(\2\2\u0189n\3\2\2\2\u018a\u018b\7~\2\2\u018b"+
		"\u018c\7~\2\2\u018cp\3\2\2\2\u018d\u018e\7@\2\2\u018e\u018f\7@\2\2\u018f"+
		"r\3\2\2\2\u0190\u0191\t\n\2\2\u0191\u0192\3\2\2\2\u0192\u0193\b:\2\2\u0193"+
		"t\3\2\2\2\u0194\u0195\7\61\2\2\u0195\u0196\7\61\2\2\u0196\u019a\3\2\2"+
		"\2\u0197\u0199\n\13\2\2\u0198\u0197\3\2\2\2\u0199\u019c\3\2\2\2\u019a"+
		"\u019b\3\2\2\2\u019a\u0198\3\2\2\2\u019b\u019d\3\2\2\2\u019c\u019a\3\2"+
		"\2\2\u019d\u019e\7\f\2\2\u019e\u019f\3\2\2\2\u019f\u01a0\b;\2\2\u01a0"+
		"v\3\2\2\2\25\2\u0107\u010c\u0116\u0126\u0128\u012f\u0138\u013b\u0142\u0149"+
		"\u014b\u0150\u0155\u0157\u015d\u017f\u0185\u019a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}