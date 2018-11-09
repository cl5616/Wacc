lexer grammar BasicLexer;

//binary operators
PLUS: '+';
MINUS: '-';
MUL: '*';
DIV: '/';
MOD: '%';
GT: '>';
GTE: '>=';
LT: '<';
LTE: '<=';
EQ: '==';
NEQ: '!=';
AND: '&&';
OR: '||';

//unary operators
NOT: '!';
LEN: 'len';
ORD: 'ord';
CHR: 'chr';

//punctuations
SEMICOLON : ';';
OPEN_PARENTHESES : '(';
CLOSE_PARENTHESES : ')';
OPEN_SQUARE_BRACKET : '[';
CLOSE_SQUARE_BRACKET : ']';
COMMA : ',';
QUOTE : '\"';
SINGLEQUOTE : '\'';
DOT : '.';

//separations
WHITE : [ \t\r\n]+ -> skip;

//numbers
INTEGER : [0-9]+;

//bool
TRUE : 'true';
FALSE : 'false';

//commands
BEGIN : 'begin';
END : 'end';
IS : 'is';
SKIP : 'skip';
READ : 'read';
FREE : 'free';
RETURN : 'return';
EXIT : 'exit';
PRINT : 'print';
PRINTLN : 'println';
NEWPAIR : 'newpair';
CALL : 'call';
ASSIGN : '=';

//if statement
IF : 'if';
THEN : 'then';
ELSE : 'else';
FI : 'fi';

//while statement
WHILE : 'while';
DO : 'do';
DONE : 'done';

//element types
FST : 'fst';
SND : 'snd';
INTTYPE : 'int';
BOOLTYPE : 'bool';
CHARTYPE : 'char';
STRINGTYPE : 'string';
PAIRTYPE : 'pair';
NULL : 'null';
COMMENT : '#' ~[\r\n]*[\r\n] -> skip;

//char
CHAR : [\'](~[\\\"\'] | [\\][\"\'0btnfr\\])[\'];

//string\
STR : [\"](~[\\\"\'] | [\\][\"\'0btnfr\\])*[\"];

//extensions
CLASS : 'class';
PUBLIC : 'public';
PRIVATE : 'private';
PROTECTED : 'protected';
NEW : 'new';
DELETE : 'delete';
DESTR : '~';
EXTENDS : 'extends';
SUPER : 'super';
THIS : 'this';

//idents
IDENT : [_a-zA-Z][_a-zA-Z0-9]*;