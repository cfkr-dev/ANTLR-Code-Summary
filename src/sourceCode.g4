grammar sourceCode;

/*
|-------------------------------------|
|        GRAMMAR SPECIFICATION        |
|-------------------------------------|
*/

program
    : dcllist funlist sentlist
    | dcllist sentlist
    | funlist sentlist
    | sentlist
    ;

dcllist
    : dcl dcllist
    | dcl
    ;

funlist
    : funcdef funlist
    | funcdef
    ;

sentlist
    : mainhead '{'code'}'
    | mainhead '{''}'
    ;


/* ---- ZONA DE DECLARACIONES ---- */


dcl
    : ctelist
    | varlist
    ;

ctelist
    : '#define' CONST_DEF_IDENTIFIER simpvalue '\n' ctelist
    | '#define' CONST_DEF_IDENTIFIER simpvalue '\n'
    ;

simpvalue
    : NUMERIC_INTEGER_CONST
    | NUMERIC_REAL_CONST
    | STRING_CONST
    ;

varlist
    : vardef ';' varlist
    | vardef ';'
    ;

vardef
    : tbas IDENTIFIER ';'
    | tbas IDENTIFIER '=' simpvalue ';'
    ;

tbas
    : 'integer'
    | 'float'
    | 'string'
    | tvoid
    ;

tvoid
    : 'void'
    ;


/* ---- ZONA DE IMPLEMENTACIÓN DE FUNCIONES ---- */


funcdef
    : funchead '{' code '}'
    | funchead '{' '}'
    ;

funchead
    : tbas IDENTIFIER '(' typedef ')'
    | tbas IDENTIFIER '(' ')'
    ;

typedef
    : tbas IDENTIFIER typedef
    | tbas IDENTIFIER
    ;


/* ---- ZONA DE SENTENCIAS DEL PROGRAMA PRINCIPAL ---- */


mainhead
    : tvoid 'Main' '(' typedef ')'
    | tvoid 'Main' '(' ')'
    ;

code
    : sent code
    | sent
    ;

sent
    : asig ';'
    | funccall ';'
    ;

asig
    : IDENTIFIER '=' exp
    ;

exp
    : factor exps
    | factor
    ;

exps
    : exp exps
    | exp op
    | exp
    | op
    ;

op
    : '+'
    | '-'
    | '*'
    | 'DIV'
    | 'MOD'
    ;

factor
    : simpvalue
    | '(' exp ')'
    | funccall
    ;

funccall
    : IDENTIFIER subpparamlist
    | IDENTIFIER
    ;

subpparamlist
    : '(' explist ')'
    ;

explist
    : exp
    | exp ',' explist
    ;


//source
//    :   COMMENT
//    |   NUMERIC_INTEGER_CONST
//    |   NUMERIC_REAL_CONST
//    |   IDENTIFIER
//    |   CONST_DEF_IDENTIFIER
//    |   STRING_CONST
//    ;


/*
|-----------------------------------|
|        TOKEN SPECIFICATION        |
|-----------------------------------|
*/


IDENTIFIER
    :   LOWER+(LOWER|NUMBER|'_')*
    |   '_'+(LOWER|NUMBER)+(LOWER|NUMBER|'_')*
    ;

CONST_DEF_IDENTIFIER
    :   UPPER+(UPPER|NUMBER|'_')*
    |   '_'+(UPPER|NUMBER)+(UPPER|NUMBER|'_')*
    ;

NUMERIC_INTEGER_CONST
    :   ('+'|'-')?NUMBER+
    ;

NUMERIC_REAL_CONST
    :   (REAL|NUMERIC_INTEGER_CONST)[Ee]NUMERIC_INTEGER_CONST
    |   REAL
    ;

STRING_CONST
    :   '\''(~'\''|('\\''\''))*'\''
    |   '"'(~'"'|('\\''"'))*'"'
    ;

COMMENT
    :   '/''/'(~'\n'*)'\n'
    |   '/'('*'+)(('*'+)(~[/]+)|(~[/*]+)|(~[*]+)('/'+)|'\n')*('*'+)'/'
    ;

WHITE_SPACE : [ \t\n\r]+ -> channel(HIDDEN) ;

// Descomentar si se quiere evitar que se reconozcan tokens erroneos
//REST
//    :   . -> skip
//    ;


/*
|-----------------------------------------|
|                FRAGMENTS                |
|-----------------------------------------|
*/

fragment UPPER
    :   [A-Z]
    ;

fragment LOWER
    :   [a-z]
    ;

fragment NUMBER
    :   [0-9]
    ;

fragment REAL
    :   ('+'|'-')?(NUMBER*'.'NUMBER+)
    ;

fragment RESERVED_SYMBOL
    :   [\u007b\u007d\u003b\u0028\u0029\u002c\u003d\u002b\u002d\u002a\u002f\u000a]
    ;

/*
   TABLA TRADUCCIÓN UNICODE - CARACTER
   -----------------------------------
   |        \u007b  --->  '{'        |
   |        \u007d  --->  '}'        |
   |        \u003b  --->  ';'        |
   |        \u0028  --->  '('        |
   |        \u0029  --->  ')'        |
   |        \u002c  --->  ','        |
   |        \u003d  --->  '='        |
   |        \u002b  --->  '+'        |
   |        \u002d  --->  '-'        |
   |        \u002a  --->  '*'        |
   |        \u002f  --->  '/'        |
   |        \u000a  --->  '\n'       |
   -----------------------------------
*/