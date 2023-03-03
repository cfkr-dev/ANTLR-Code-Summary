grammar sourceCode;

/*
|-------------------------------------|
|        GRAMMAR SPECIFICATION        |
|-------------------------------------|
*/

program
    : '#define' program_aux
    | 'integer' program_aux
    | 'float' program_aux
    | 'string' program_aux
    | 'void' program_aux
    ;

program_aux
    : dcllist funlist sentlist
    | dcllist sentlist
    ;

dcllist
    : '#define' dcllist_aux
    | 'integer' dcllist_aux
    | 'float' dcllist_aux
    | 'string' dcllist_aux
    | 'void' dcllist_aux
    ;

dcllist_aux
    : dcl
    | dcl dcllist
    ;

funlist
    : 'integer' funlist_aux
    | 'float' funlist_aux
    | 'string' funlist_aux
    | 'void' funlist_aux
    ;

funlist_aux
    : funcdef
    | funcdef funlist
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
    : '#define' ctelist_aux
    ;

ctelist_aux
    : CONST_DEF_IDENTIFIER simpvalue '\n'
    | CONST_DEF_IDENTIFIER simpvalue '\n' ctelist
    ;

simpvalue
    : NUMERIC_INTEGER_CONST
    | NUMERIC_REAL_CONST
    | STRING_CONST
    ;

varlist
    : 'integer' varlist_aux
    | 'float' varlist_aux
    | 'string' varlist_aux
    | 'void' varlist_aux
    ;

varlist_aux
    : vardef ';'
    | vardef ';' varlist
    ;

vardef
    : 'integer' vardef_aux
    | 'float' vardef_aux
    | 'string' vardef_aux
    | 'void' vardef_aux
    ;

vardef_aux
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
    : 'integer' funcdef_aux
    | 'float' funcdef_aux
    | 'string' funcdef_aux
    | 'void' funcdef_aux
    ;

funcdef_aux
    : funchead '{'code'}'
    | funchead '{''}'
    ;

funchead
    : 'integer' funchead_aux
    | 'float' funchead_aux
    | 'string' funchead_aux
    | 'void' funchead_aux
    ;

funchead_aux
    : tbas IDENTIFIER '('typedef')'
    | tbas IDENTIFIER '('')'
    ;

typedef
    : 'integer' typedef_aux
    | 'float' typedef_aux
    | 'string' typedef_aux
    | 'void' typedef_aux
    ;

typedef_aux
    : tbas IDENTIFIER typedef
    | tbas IDENTIFIER
    ;


/* ---- ZONA DE SENTENCIAS DEL PROGRAMA PRINCIPAL ---- */


mainhead
    : 'void' mainhead_aux
    ;

mainhead_aux
    : tvoid 'Main' '('typedef')'
    | tvoid 'Main' '('')'
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
    : IDENTIFIER funccall_aux
    ;

funccall_aux
    : IDENTIFIER subpparamlist
    | IDENTIFIER
    ;

subpparamlist
    : '('explist')'
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