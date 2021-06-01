parser grammar alg;

options{tokenVocab=algLexer;}

programa :  fcall*  variable* function*  EOF;


fcall
    :   IF expressions_list THEN instructions (ELSE instructions)? {notifyErrorListeners("Missing parenteses");}
    |   IF LP expressions_list RP instructions (ELSE instructions)? {notifyErrorListeners("Missing 'then'condition");}
    |   function_invocate     {notifyErrorListeners("Missing closing ';'");}
    ;

  //functionSpecial : INT ALG LP argsSpecial RP body;
  //argsSpecial: ARG1;
  functionSpecial : INT ALG LP INT IDENT VIRGULA MENORQ STRING MAIORQ IDENT RP;


// RECURSÃO À ESQUERDA IMEDIATA
/*expr : expr SOMA_SUB expr2 | expr2;
expr2 : expr2 MULT_DIV expr3 | expr3;
expr3 : (LITERAL_INTEIRO | IDENT)  ;*/
//_-------------------
//---------

//- RESOLUÇÃO DA RECURSAO
/*expr : expr2 expr_aux;
expr_aux : SOMA_SUB expr2 expr_aux |;
expr2 : expr3 expr2_aux;
expr2_aux: MULT_DIV expr3 expr2_aux | ;
expr3 : function_invocate | op_paranteses | op_pointer |LITERAL_INTEIRO | IDENT | LITERAL_DECIMAL |  ide ;*/

// a expr foi alterada de modo a ser mais fácil de chamar no typeChecker

expr: ID '(' exprList? ')'      # Call
    | '(' expr ')'              # Paren
    | expr (MULT_DIV) expr      # MulDiv
    | expr (SOMA_SUB) expr      # AddSub
    | function_invocate         # FuncInv
    | op_paranteses             # Parenteses
    | op_pointer                # Pointer
    | IDENT                     # Var
    | LITERAL_INTEIRO           # Int
    | LITERAL_DECIMAL           # Float
    | ide                       # Identifier
    | idy                       # IdBoolPoint
    //| expr '[' expr ']'         # PointIndex    // PointIndex foi adicionado para a Indexação de ponteiro

    ;

idy : (IDEY IDENT)|(IDEY '(' IDENT '[' expr ']' ')')|(IDEY IDENT '[' expr ']') ;
exprList : expr (',' expr)*;

defineNull : IDENT EQUAL NULL PONTO_VIRGULA;
// exemplo de não fatoração À esquerda

//inteiro : (INT IDENT ('[' (IDENT|LITERAL_INTEIRO) ']')? (EQUAL (expr)*)?);

// resolução
equals : (EQUAL (function_invocate|expr+)) | ;
inteiro:(INT? IDENT ('[' (IDENT|LITERAL_INTEIRO) ']')? equals);

//------------------
inteiros : INT IDENT (',' IDENT)*;
booleano : BOOL? IDENT (EQUAL ('true'|'false'|function_invocate))?;
booleanos : BOOL IDENT (',' IDENT)*;

// exemplo de não fatoração À esquerda
//real : FLOAT IDENT (INDEX_POINT_L(IDENT|LITERAL_INTEIRO) INDEX_POINT_R)? (EQUAL expr*)?;
// resolução
real : FLOAT? IDENT (INDEX_POINT_L(IDENT|LITERAL_INTEIRO) INDEX_POINT_R)? equals;
reais : FLOAT IDENT (',' IDENT)*;


//exemplo de não fatorização à esquerda
//cadeia_caracteres : STRING? IDENT (INDEX_POINT_L(IDENT|LITERAL_INTEIRO) INDEX_POINT_R)? (EQUAL (CADEIA_CARACTERES));
//resolução
equals_string:EQUAL CADEIA_CARACTERES | EQUAL function_invocate | ;
cadeia_caracteres: STRING? IDENT (INDEX_POINT_L(IDENT|LITERAL_INTEIRO) INDEX_POINT_R)? equals_string;
//---------------

cadeias_caracteres : STRING IDENT (',' IDENT)*;
ponteiro_inteiro : MENORQ INT MAIORQ IDENT (EQUAL (function_invocate|expr* |NULL | ('[' expr* ']')))?;
ponteiro_real : MENORQ FLOAT MAIORQ IDENT (EQUAL (function_invocate|expr* |NULL | ('[' expr* ']')))?;
ponteiro_cadeia : MENORQ STRING MAIORQ IDENT (EQUAL (CADEIA_CARACTERES|function_invocate|NULL | ('[' expr* ']')) );

//expression: (function_invocate | LITERAL_INTEIRO | LITERAL_DECIMAL | LITERAL_EXPONENCIAL | CADEIA_CARACTERES | IDENT) PONTO_VIRGULA ;
//expressions: (expr|op_paranteses|op_pointer|ide)((MULT_DIV | SOMA_SUB) (expr|op_paranteses|op_pointer|ide))* PONTO_VIRGULA;
op_paranteses : LP (expr*) RP;
op_pointer : INDEX_POINT_L (expr*) INDEX_POINT_R;
//ide : (SOMA_SUB|IDEY) (IDENT|((IDENT|LITERAL_INTEIRO) op_pointer) | LITERAL_INTEIRO);
// ide foi alterado
ide : SOMA_SUB expr?;

//idy foi adicionado



comparations : expr (MENORQ | MAIORQ | EQUAL_DIF | COMPARATOR) expr;

logics : LP (expr*) (E_LOGICO|OU_LOGICO) (expr*) RP EQUAL_DIF ('true'|'false') ;


expressions_list: comparations |(expr PONTO_VIRGULA) ;
expressions_list2: comparations |  expr | CADEIA_CARACTERES ;
arg : type IDENT;
//foram adicionados o args e o type, com alterações no function declare
args : arg (',' arg)*;
function_declare : type IDENT LP args? RP;
type: (INT|FLOAT|BOOL|STRING|VOID|(MENORQ (INT|FLOAT|STRING) MAIORQ));

body : L_BRACE (variable | function_invocate PONTO_VIRGULA | instructions|defineNull)* instructions R_BRACE;
//foi adicionado o body2
//body2 : L_BRACE (variable | function_invocate PONTO_VIRGULA | instructions | defineNull)* R_BRACE;
prologo: AT_SIGN body;
epilogo: D_MAIORQ body;

function: (function_declare|functionSpecial) prologo? body epilogo?;


function_invocate: ((IDENT LP (expr (',' expr)*)? RP) | (AT_SIGN LP RP) | (SIZEOF LP expr RP)
| ((WRITE|WRITELN)LP expr (',' expr)* RP));

ctrl_instruct: (LEAVE | RESTART | (RETURN expressions_list2) | RETURN) PONTO_VIRGULA  ;

attributes : ((IDENT |op_pointer) EQUAL expressions_list2) PONTO_VIRGULA;

instructions : expressions_list | ctrl_instruct | attributes | if_cond | loop | sub_block;

if_cond : IF LP ((comparations ((E_LOGICO|OU_LOGICO) comparations)*) | logics ) RP THEN instructions (ELSE instructions)?;

loop : WHILE LP (expressions_list | logics ) RP DO instructions (FINALLY instructions)?;

sub_block : L_BRACE instructions* R_BRACE;



variable:
    (inteiro | real  | inteiros
    | booleano| booleanos
    | reais
    | cadeia_caracteres| cadeias_caracteres
    | ponteiro_inteiro | ponteiro_real | ponteiro_cadeia) PONTO_VIRGULA;




