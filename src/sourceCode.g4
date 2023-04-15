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
    | var
    | varlist_error_semicolon
    ;

ctelist
    : '#define' CONST_DEF_IDENTIFIER simpvalue
    | DEFINE_ERROR CONST_DEF_IDENTIFIER simpvalue
    ;

simpvalue
    : NUMERIC_INTEGER_CONST
    | NUMERIC_REAL_CONST
    | STRING_CONST
    ;

var
    : vardef ';'
    ;

varlist_error_semicolon
    : vardef
    ;

vardef
    : simple_vardef
    | struct_vardef
    ;

simple_vardef
    : tbas IDENTIFIER vardef_aux
    | error_simple_vardef
    ;

error_simple_vardef
    : tbas
    | tbas vardef_aux
    ;

struct_vardef
    : struct_def IDENTIFIER
    | error_struct_vardef
    ;

error_struct_vardef
    : struct_def
    ;

vardef_aux
    : '=' simpvalue
    |
    ;

struct_def
    : 'struct' '{' dcllist_struct '}'
    | STRUCT_ERROR '{' dcllist_struct '}'
    ;

dcllist_struct
    : dcl_struct dcllist_struct_aux
    |
    ;

dcllist_struct_aux
    : dcllist_struct
    |
    ;

dcl_struct
    : var
    | varlist_error_semicolon
    ;

tbas
    : 'integer'
    | integer_error
    | 'float'
    | FLOAT_ERROR
    | 'string'
    | STRING_ERROR
    | tvoid
//    | 'struct'
//    | STRUCT_ERROR
    ;

integer_error
    : INTEGER_ERROR IDENTIFIER
    ;

tvoid
    : 'void'
    | VOID_ERROR
    ;

struct
    : 'struct' '{' dcllist_struct '}'
    | STRUCT_ERROR '{' dcllist_struct '}'
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
    | tvoid MAIN_ERROR '(' mainhead_aux
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
    | vardef_and_asig ';'
    | return_func ';'
    | if
    | while
    | dowhile
    | for
    ;

if
    : 'if' expcond '{' sentlist_aux if_aux
    | IF_ERROR expcond '{' sentlist_aux if_aux
    ;

if_aux
    : else
    |
    ;

else
    : 'else' else_aux
    | ELSE_ERROR else_aux
    ;

else_aux
    : '{' sentlist_aux
    | if
    ;

while
    : 'while' '(' expcond ')' '{' sentlist_aux
    | WHILE_ERROR '(' expcond ')' '{' sentlist_aux
    ;

dowhile
    : 'do' '{' sentlist_aux 'while' '(' expcond ')' ';'
    | DO_ERROR '{' sentlist_aux WHILE_ERROR '(' expcond ')' ';'
    ;

for
    : 'for' '(' for_aux
    | FOR_ERROR '(' for_aux
    ;

// NUEVO
for_aux
    : vardef ';' expcond ';' asig ')' '{' sentlist_aux
    | asig ';' expcond ';' asig ')' '{' sentlist_aux
    | vardef_and_asig ';' expcond ';' asig ')' '{' sentlist_aux
    ;

expcond
    : factorcond expcond_aux
    ;

expcond_aux
    : oplog expcond expcond_aux
    |
    ;

oplog
    : '||'
    | OR_ERROR
    | '&'
    | AND_ERROR
    ;

factorcond
    : '(' expcond ')'
    | exp factorcond_aux
    | '!' factorcond
    ;

factorcond_aux
    : opcomp exp
    |
    ;

opcomp
    : '<'
    | LT_ERROR
    | '>'
    | GT_ERROR
    | '<='
    | LTE_ERROR
    | '>='
    | GTE_ERROR
    | '=='
    | EQ_ERROR
    | '!='
    | NEQ_ERROR
    ;

// NUEVO
return_func
    : 'return' return_func_aux
    ;

return_func_aux
    : '(' explist ')'
    | explist
    ;

asig
    : IDENTIFIER '=' exp
    ;

// NUEVO
vardef_and_asig
    : typedef '=' exp
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
    | PLUS_ERROR
    | '-'
    | MINUS_ERROR
    | '*'
    | MULT_ERROR
    | 'DIV'
    | DIV_ERROR
    | 'MOD'
    | MOD_ERROR
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
    |
    ;

explist_aux
    : ',' explist
    |
    ;

IDENTIFIER
    :   LOWER+(LOWER|NUMBER|'_')*
    |   '_'+(LOWER|NUMBER)+(LOWER|NUMBER|'_')*
    ;

/*
|-------------------------------------------------------|
|        TOKEN TYPES COMMON ERRORS SPECIFICATION        |
|-------------------------------------------------------|
*/

INTEGER_ERROR
    : [iI] (LOWER | UPPER)*
    ;

FLOAT_ERROR
    : [fF] (LOWER | UPPER)* [ \t\n\r]* ~[;{ \t\n\r]+
    ;

STRUCT_ERROR
    : [sS] (LOWER | UPPER)* [cC] (LOWER | UPPER)* ~[;{ \t\n\r]+
    ;

STRING_ERROR
    : [sS] (LOWER | UPPER)* [ \t\n\r]* ~[;{ \t\n\r]+
    ;

VOID_ERROR
    : [vV] (LOWER | UPPER)* [ \t\n\r]* ~[;{ \t\n\r]+
    ;


/*
|-----------------------------------|
|        TOKEN SPECIFICATION        |
|-----------------------------------|
*/



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

WHITE_SPACE
    :   [ \t\n\r]+ -> channel(HIDDEN)
    ;

COMMENT
    :   ('/''/'(~'\n'*)'\n'
    |   '/'('*'+)(('*'+)(~[/]+)|(~[/*]+)|(~[*]+)('/'+)|'\n')*('*'+)'/') -> channel(HIDDEN)
    ;


/*
|-----------------------------------|
|        TOKEN COMMON ERRORS        |
|-----------------------------------|
*/

FOR_ERROR
    : [fF] (LOWER | UPPER)*
    ;

WHILE_ERROR
    : [wW] (LOWER | UPPER)*
    ;

DO_ERROR
    : [dD] (LOWER | UPPER)*
    ;

IF_ERROR
    : [iI][fF] (LOWER | UPPER)*
    ;

ELSE_ERROR
    : [eE] [lL] (LOWER | UPPER)*
    ;

MAIN_ERROR
    : 'M' (LOWER | UPPER)*
    ;

DEFINE_ERROR
    : '\u0023' '\u0023'* (LOWER | UPPER)*
    ;

MOD_ERROR
    : ([mM] | '\u0025' '\u0025'+) (LOWER | UPPER)*
    ;

PLUS_ERROR
    : ([sS][uU][mM] | '\u002b' '\u002b'+)
    ;

MINUS_ERROR
    : '\u002d' '\u002d'+
    ;

MULT_ERROR
    : ([Mm][uU][lL][tT] | '\u002a' '\u002a'+)
    ;

DIV_ERROR
    : ([dD] | '\u002f' '\u002f'*) (LOWER | UPPER)*
    ;

LT_ERROR
    : '\u003c' '\u003c'+
    ;

LTE_ERROR
    : '\u003c' '\u003c'+ '\u003d'+
    | '\u003d'+ '\u003c'+
    ;

GT_ERROR
    : '\u003e' '\u003e'+
    ;

GTE_ERROR
    : '\u003e' '\u003e'+ '\u003d'+
    | '\u003e'+ '\u003e'+
    ;

EQ_ERROR
    : '\u003d' '\u003d' '\u003d'+
    ;

NEQ_ERROR
    : ('\u007e' | '\u0021' '\u0021'+) '\u003d' '\u003d'+
    | '\u003d'+ ('\u0021'+ | '\u007e'+)
    ;

OR_ERROR
    : '\u007c' '\u007c' '\u007c'+
    | [oO][rR]
    | [rR][oO]
    ;

AND_ERROR
    : '\u0026' '\u0026'+
    | [aA][nN][dD]
    ;

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