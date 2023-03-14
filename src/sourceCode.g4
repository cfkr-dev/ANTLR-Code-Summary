grammar sourceCode;

/*
|-------------------------------------|
|        GRAMMAR SPECIFICATION        |
|-------------------------------------|
*/


/* ---- ZONA PRINCIPAL ---- */

program
    : dcllist program_aux
    | funlist sentlist
    | sentlist
    ;

program_aux
    : funlist sentlist
    | sentlist
    ;

dcllist
    : dcl dcllist_aux
    ;

dcllist_aux
    : dcllist
    |
    ;

funlist
    : funcdef funlist_aux
    ;

funlist_aux
    : funlist
    |
    ;

sentlist
    : mainhead '{' sentlist_aux
    ;

sentlist_aux
    : code '}'
    | '}'
    ;



/* ---- ZONA DE DECLARACIONES ---- */


dcl
    : ctelist
    | varlist
    ;

ctelist
    : '#define' CONST_DEF_IDENTIFIER simpvalue
    ;

simpvalue
    : NUMERIC_INTEGER_CONST
    | NUMERIC_REAL_CONST
    | STRING_CONST
    ;

varlist
    : vardef ';'
    ;

vardef
    : tbas IDENTIFIER vardef_aux
    ;

vardef_aux
    : '=' simpvalue
    |
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
    : funchead '{' funcdef_aux
    ;

funcdef_aux
    : code '}'
    | '}'
    ;

funchead
    : tbas IDENTIFIER '(' funchead_aux
    ;

funchead_aux
    : typedef ')'
    | ')'
    ;

typedef
    : tbas IDENTIFIER typedef_aux
    | ',' tbas IDENTIFIER typedef_aux
    ;

typedef_aux
    : typedef
    |
    ;

/* ---- ZONA DE SENTENCIAS DEL PROGRAMA PRINCIPAL ---- */


mainhead
    : tvoid 'Main' '(' mainhead_aux
    ;

mainhead_aux
    : typedef ')'
    | ')'
    ;

code
    : sent code_aux
    ;

code_aux
    : code
    |
    ;

sent
    : asig ';'
    | funccall ';'
    | vardef ';'
    | return_func ';'
    ;

return_func
    : 'return' return_func_aux
    |
    ;

return_func_aux
    : IDENTIFIER
    | simpvalue
    ;

asig
    : IDENTIFIER '=' exp
    ;

exp
    : factor exp_aux
    ;

exp_aux
    : op exp exp_aux
    |
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
    | CONST_DEF_IDENTIFIER
    ;

funccall_aux
    : subpparamlist
    |
    ;

subpparamlist
    : '(' explist ')'
    ;

explist
    : exp explist_aux
    ;

explist_aux
    : ',' explist
    |
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