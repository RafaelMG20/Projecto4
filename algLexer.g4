lexer grammar algLexer;



INT : 'int';
BOOL : 'bool';
FLOAT : 'float';
STRING : 'string';
VOID : 'void';
SIZEOF : 'sizeof';
NULL : 'null';
ALG : 'alg';
TRUE : 'true';
FALSE : 'false';
WHILE : 'while';
DO : 'do';
FINALLY : 'finally';
LEAVE : 'leave';
RESTART : 'restart';
RETURN : 'return';
IF : 'if';
THEN : 'then';
ELSE : 'else';
WRITE : 'write';
WRITELN : 'writeln';

CHANGE_LINE : '\\n' -> skip;
CARRIAGE_RETURN : '\\r' -> skip;
SPACE : ' ' -> skip;
TABULACAO_HORIZONTAL : '\\t' -> skip;

COMMENT_E : '##' .*? (CHANGE_LINE | CARRIAGE_RETURN) -> skip ;
COMMENT_O : '(*' .+? '*)' -> skip;

VIRGULA : ',';
E : 'e';
PONTO_VIRGULA : ';';

IDENT : LETTER ( LETTER | [0-9] | '_')*;
ID : LETTER;
LETTER : [a-zA-Z]|LATIN1;
LATIN1 : '\u00A0'..'\u00FF';

LITERAL_INTEIRO : ('0' | ([1-9][0-9]*));
LITERAL_DECIMAL : LITERAL_INTEIRO'.'[0-9]+;
LITERAL_EXPONENCIAL :  [0-9] ('.'[0-9]+)? (('e'|'E')('+'|'-'|) [0-9]+)?;

CADEIA_CARACTERES : '\'' ('\u0001'..'\u{FFFF}')+? '\'' ;

/*OPERATOR :  '(' |
            ')' |
            '[' |
            ']' |
            '?' |
            '%' |
            '+' |
            '-' |
            '>'('=')? |
            '<'('=')? |
            ('='|'!')? '=' |
            '~' |
            '&&' |
            '||' |
            '>>' |
            '@' ;*/

EQUAL: '=';


MENORQ : '<';
MAIORQ: '>';

AT_SIGN : '@';
L_BRACE: '{';
R_BRACE: '}';
LP : '(';
RP : ')';
INDEX_POINT_L: '[';
INDEX_POINT_R: ']';
IDEY: '~'|'?';
MULT_DIV : '*' | '/' | '%';
SOMA_SUB : '+' | '-';
EQUAL_DIF : '==' | '!=' ;
COMPARATOR :  '>=' | '<=';
E_LOGICO : '&&';
OU_LOGICO : '||';
D_MAIORQ: '>>';



WS : [ \t\r\n] -> skip;
COMMENTS : '//'(~'\n')*?'\n' -> skip;










