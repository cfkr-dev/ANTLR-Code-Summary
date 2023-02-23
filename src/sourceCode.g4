grammar sourceCode;

/*
|-------------------------------------|
|        GRAMMAR SPECIFICATION        |
|-------------------------------------|
*/

source
    :   COMMENT
    |   RESERVED_WORD
    |   NUMERIC_INTEGER_CONST
    |   NUMERIC_REAL_CONST
    |   IDENTIFIER
    |   CONST_DEF_IDENTIFIER
    |   STRING_CONST
    ;


/*
|-----------------------------------|
|        TOKEN SPECIFICATION        |
|-----------------------------------|
*/

RESERVED_WORD
    :   RESERVED_SYMBOL
    |   '#define'
    |   'integer'
    |   'float'
    |   'string'
    |   'void'
    |   'Main'
    |   'DIV'
    |   'MOD'
    ;

IDENTIFIER
    :   MINUS+(MINUS|NUMBER|'_')*
    |   '_'+(MINUS|NUMBER)+(MINUS|NUMBER|'_')*
    ;

CONST_DEF_IDENTIFIER
    :   MAYUS+(MAYUS|NUMBER|'_')*
    |   '_'+(MAYUS|NUMBER)+(MAYUS|NUMBER|'_')*
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

// TODO ver si hay que quitarlo
REST
    :   . ->skip
    ;


/*
|-----------------------------------------|
|                FRAGMENTS                |
|-----------------------------------------|
*/

fragment MAYUS
    :   [A-Z]
    ;

fragment MINUS
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