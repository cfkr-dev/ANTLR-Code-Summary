grammar sourceCode;

/*
|-------------------------------------|
|        GRAMMAR SPECIFICATION        |
|-------------------------------------|
*/

    // **** MAIN SECTION ****
    // ------------------------

        program
            : sentlist
            | funlist sentlist
            | dcllist program_aux
            | program_empty
            ;

        program_aux
            : funlist sentlist
            | sentlist
            ;

        program_empty
            :
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
            | sentlist_error
            ;

        sentlist_aux
            : code '}'
            | '}'
            ;

        sentlist_error
            :
            ;

    // **** DECLARATIONS SECTION ****
    // ------------------------------

        dcl
            : cte
            | var
            | cte_error_semicolon
            ;

        /* ---- CONSTANTS DECLARATION ---- */

            cte
                : '#define' CONST_DEF_IDENTIFIER simpvalue
                | cte_error
                ;

            cte_error
                : HASHTAG_TEXT cte_error_aux
                | '#define' cte_error_aux
                ;

            cte_error_aux
                : simpvalue
                | cte_error_aux_1
                |
                ;

            cte_error_aux_1
                : (TEXT | IDENTIFIER | CONST_DEF_IDENTIFIER) cte_error_aux_1_aux
                ;

            cte_error_aux_1_aux
                : simpvalue
                |
                ;

            cte_error_semicolon
                : cte ';'
                ;

        /* ---- SIMPLE VARIABLES DECLARATION ---- */

            var
                : vardef
                ;

            vardef
                : simple_vardef
                | struct_vardef
                ;

            vardef_aux
                :
                | equal_asig simpvalue
                | vardef_aux_error
                ;

            vardef_aux_error
                : equal_asig exp
                ;

            simple_vardef
                : tbas IDENTIFIER vardef_aux semicolon
                | error_simple_vardef vardef_aux semicolon
                ;

            error_simple_vardef
                : tbas error_simple_vardef_aux
                | (TEXT | IDENTIFIER | CONST_DEF_IDENTIFIER) (TEXT | IDENTIFIER | CONST_DEF_IDENTIFIER)
                ;

            error_simple_vardef_aux
                : (TEXT | IDENTIFIER | CONST_DEF_IDENTIFIER)
                |
                ;

        /* ---- STRUCTS DECLARATION ---- */

            struct_vardef
                : struct_def IDENTIFIER semicolon
                | error_struct_vardef semicolon
                ;

            error_struct_vardef
                : struct_def
                ;

            struct_def
                : 'struct' curly_open dcllist_struct curly_close
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
                ;


        /* ---- ASSIGNABLE VALUES ---- */

            simpvalue
                : NUMERIC_INTEGER_CONST
                | NUMERIC_REAL_CONST
                | STRING_CONST
                | simpvalue_error
                ;

            simpvalue_error
                : (TEXT | IDENTIFIER | CONST_DEF_IDENTIFIER)
                |
                ;

        /* ---- VARIABLE TYPES ---- */

            tbas
                : 'integer'
                | 'float'
                | 'string'
                | tvoid
                | 'struct'
                ;

            tvoid
                : 'void'
                ;




    // **** FUNCTION IMPLEMENTATION SECTION ****
    // -----------------------------------------

        funcdef
            : funchead curly_open funcdef_aux
            ;

        funcdef_aux
            : code curly_close
            | curly_close
            ;

        /* ---- FUNCTION HEAD ---- */

            funchead
                : tbas IDENTIFIER '(' funchead_aux
                | funchead_error '(' funchead_aux
                ;

            funchead_aux
                : typedef paren_close
                | paren_close
                ;

            funchead_error
                : tbas funchead_error_aux
                | (TEXT | IDENTIFIER | CONST_DEF_IDENTIFIER) (TEXT | IDENTIFIER | CONST_DEF_IDENTIFIER)
                ;

            funchead_error_aux
                : (TEXT | IDENTIFIER | CONST_DEF_IDENTIFIER)
                |
                ;

        /* ---- FUNCTION PARAMETERS ---- */

            typedef
                : tbas IDENTIFIER typedef_aux
                | typedef_error typedef_aux
                ;

            typedef_aux
                : comma typedef
                | comma_no_var_error
                |
                ;

            typedef_error
                : (TEXT | IDENTIFIER | CONST_DEF_IDENTIFIER) typedef_error_aux
                | tbas typedef_error_aux
                ;

            typedef_error_aux
                : TEXT
                |
                ;

    // **** MAIN-PROGRAM-SENTENCES SECTION ****
    // ----------------------------------------

        /* ---- FUNCION PRINCIPAL ---- */

            mainhead
                : tvoid 'Main' '(' mainhead_aux
                | mainhead_error 'Main' '(' mainhead_aux
                ;

            mainhead_aux
                : typedef paren_close
                | paren_close
                ;

            mainhead_error
                : tbas
                | (TEXT | IDENTIFIER | CONST_DEF_IDENTIFIER)
                ;

        /* ---- CODE BLOCK ---- */

            code
                : sent code_aux
                ;

            code_aux
                : code
                |
                ;

        /* ---- SENTENCES ---- */

            sent
                : asig semicolon
                | vardef_and_asig semicolon
                | vardef_code semicolon
                | funccall semicolon
                | return_func semicolon
                | if
                | while
                | dowhile
                | for
                ;

        /* ---- VARIABLE DEFINITIONS ---- */
        
            vardef_code
                : simple_vardef_code
                | struct_vardef
                ;

            simple_vardef_code
                : tbas IDENTIFIER
                | error_simple_vardef_code
                ;
    
            error_simple_vardef_code
                : tbas error_simple_vardef_code_aux
                | (TEXT | IDENTIFIER | CONST_DEF_IDENTIFIER) (TEXT | IDENTIFIER | CONST_DEF_IDENTIFIER)
                ;
    
            error_simple_vardef_code_aux
                : (TEXT | IDENTIFIER | CONST_DEF_IDENTIFIER)
                |
                ;
        
        /* ---- ASSIGNMENTS ---- */

            asig
                : IDENTIFIER equal_asig_no_empty exp
                | asig_error equal_asig_no_empty exp
                ;

            asig_error
                : (TEXT | CONST_DEF_IDENTIFIER | IDENTIFIER)
                |
                ;

            vardef_and_asig
                : simple_vardef_code equal_asig_no_empty exp
                ;

        /* ---- FUNCTION CALLS ---- */

            funccall
                : IDENTIFIER funccall_aux
                | CONST_DEF_IDENTIFIER
                | funccall_error funccall_aux
                ;

            funccall_error
                : TEXT
                ;

            funccall_aux
                : subpparamlist
                |
                ;

            subpparamlist
                : paren_open explist paren_close
                ;

            explist
                : exp explist_aux
                |
                ;

            explist_aux
                : comma explist
                | comma_no_var_error
                |
                ;

        //todo completar recuperación desde este punto
        /* ---- FUNCTION-RETURN SECTION ---- */

            return_func
                : 'return' return_func_aux
                ;

            return_func_aux
                : '(' explist ')'
                | explist
                ;

        /* ---- IF-ELSE SENTENCE ---- */

            if
                : 'if' expcond '{' sentlist_aux if_aux
                ;

            if_aux
                : else
                |
                ;

            else
                : 'else' else_aux
                ;

            else_aux
                : '{' sentlist_aux
                | if
                ;

        /* ---- WHILE SENTENCE ---- */

            while
                : 'while' '(' expcond ')' '{' sentlist_aux
                ;

        /* ---- DO-WHILE SENTENCE ---- */

            dowhile
                : 'do' '{' sentlist_aux 'while' '(' expcond ')' ';'
                ;

        /* ---- FOR SENTENCE ---- */

            for
                : 'for' '(' for_aux
                ;

            for_aux
                : vardef ';' expcond ';' asig ')' '{' sentlist_aux
                | asig ';' expcond ';' asig ')' '{' sentlist_aux
                | vardef_and_asig ';' expcond ';' asig ')' '{' sentlist_aux
                ;

        /* ---- CONDITIONAL OPERATIONS ---- */

            expcond
                : factorcond expcond_aux
                ;

            expcond_aux
                : oplog expcond expcond_aux
                |
                ;

            oplog
                : '||'
                | '&'
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
                | '>'
                | '<='
                | '>='
                | '=='
                | '!='
                ;

        /* ---- ARITHMETIC OPERATIONS ---- */

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
                : simpvalue_code
                | '(' exp ')'
                | funccall
                ;

            simpvalue_code
                : NUMERIC_INTEGER_CONST
                | NUMERIC_REAL_CONST
                | STRING_CONST
                | IDENTIFIER
                | CONST_DEF_IDENTIFIER
                | simpvalue_code_error
                ;

            simpvalue_code_error
                : TEXT
                ;


    // **** ERROR-RECOBERY-CONTROL PRODUCTIONS ****
    // ------------------------------------

        /* ---- OPEN AND CLOSE PARENTHESIS ---- */

            paren_open
                : '('
                | paren_open_error
                ;

            paren_open_error
                :
                ;

            paren_close
                : ')'
                | paren_close_error
                ;

            paren_close_error
                :
                ;

        /* ---- OPEN AND CLOSE CURLY-BRACKETS ---- */

            curly_open
                : '{'
                | curly_open_error
                ;

            curly_open_error
                :
                ;

            curly_close
                : '}'
                | curly_close_error
                ;

            curly_close_error
                :
                ;

        /* ---- COMMA-SYMBOL ---- */

            comma
                : ','
                | comma_error
                ;

            comma_error
                :
                ;

            comma_no_var_error
                : ','
                ;

        /* ---- SEMICOLON-SYMBOL ---- */

            semicolon
                : ';'
                | semicolon_error
                ;

            semicolon_error
                :
                ;

        /* ---- EQUAL-SYMBOL FOR ASSIGNMENTS ---- */

            equal_asig
                : '='
                | equal_asig_error
                ;

            equal_asig_error
                : '=='
                | EQ_MORE_ONE_ERROR
                |
                ;

            equal_asig_no_empty
                : '='
                | equal_asig_no_empty_error
                ;

            equal_asig_no_empty_error
                : '=='
                | EQ_MORE_ONE_ERROR
                ;

/*
|-----------------------------------|
|        TOKEN SPECIFICATION        |
|-----------------------------------|
*/

    // **** MAIN TOKENS ****
    // ---------------------

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

    // **** AUXILIAR ERROR-RECOVERY-CONTROL TOKENS ****
    // ------------------------------------------------

        TEXT
            :   (NUMBER | LOWER | UPPER | '_')+
            ;

        HASHTAG_TEXT
            :   '#' (NUMBER | LOWER | UPPER | '_')*
            ;

        EQ_MORE_ONE_ERROR
            : '=' '='+
            ;

    // **** TOKENS TO IGNORE ****
    // --------------------------

        WHITE_SPACE
            :   [ \t\n\r]+ -> channel(HIDDEN)
            ;

        COMMENT
            :   ('/''/'(~'\n'*)'\n'
            |   '/'('*'+)(('*'+)(~[/]+)|(~[/*]+)|(~[*]+)('/'+)|'\n')*('*'+)'/') -> channel(HIDDEN)
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