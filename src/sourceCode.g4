grammar sourceCode;

/*
|-------------------------------------|
|        GRAMMAR SPECIFICATION        |
|-------------------------------------|
*/

    // **** MAIN SECTION ****
    // ------------------------
        program_prime
            : {Program program = new Program();$program_prime.context = program;}  program[$program_prime.context]
            ;
        program[Program context]
            : sentlist[$context]
            | funlist[$context] sentlist[$context]
            | dcllist[$context] program_aux[$context]
            ;

        program_aux[Program context]
            : funlist[$context] sentlist[$context]
            | sentlist[$context]
            ;

        dcllist[Program context]
            : dcl[$context] dcllist_aux[$context]
            ;

        dcllist_aux[Program context]
            : dcllist[$context]
            |
            ;

        funlist[ProgrammableElement context]
            : funcdef[$context] funlist_aux[$context]
            ;

        funlist_aux[ProgrammableElement context]
            : funlist[$context]
            |
            ;

        sentlist[Program context]
            : mainhead[$context] '{' sentlist_aux[$mainhead.contextMain]

            ;

        sentlist_aux[MasterSentenceContainer context]
            : code[$context] '}'
            |  '}'
            ;


    // **** DECLARATIONS SECTION ****
    // ------------------------------

        dcl[Program context]
            : cte[$context]
            | var[$context]
            ;

        /* ---- CONSTANTS DECLARATION ---- */

            cte[Program context]
                : '#define' CONST_DEF_IDENTIFIER simpvalue[$context]
                {$context.createNewConstant($CONST_DEF_IDENTIFIER.text,$simpvalue.value);}
                ;



        /* ---- SIMPLE VARIABLES DECLARATION ---- */

            var[ProgrammableElement context]
                : vardef[$context]
                ;

            vardef[ProgrammableElement context]
                : simple_vardef[$context]
                | struct_vardef[$context]
                ;

            vardef_aux[ProgrammableElement context,String type,String name]
                :{
                     if($context instanceof Program ){
                         $context.createNewVariable($type,$name);
                     }
                     else if($context instanceof MasterSentenceContainer ){
                         $context.addNewVariableDefinition($type,$name);
                     }
                     else{
                         $context.addNewSimpleProperty($type, $name);
                     }

                 }
                | '=' simpvalue[$context]
                {
                     if($context instanceof Program ){
                         $context.createNewVariable($type,$name).setValue($simpvalue.value);
                     }
                     else if($context instanceof MasterSentenceContainer ){
                         $context.addNewVariableDefinitionAndAssign($type,$name,$simpvalue.value);
                     }
                     else{
                         $context.addNewSimpleProperty($type,$name,$simpvalue.value);
                     }

                 }
                ;



            simple_vardef[ProgrammableElement context]
                : tbas IDENTIFIER vardef_aux[$context,$tbas.type,$IDENTIFIER.text] ';'
                ;



        /* ---- STRUCTS DECLARATION ---- */

            struct_vardef[ProgrammableElement context]
                : struct_def[$context] IDENTIFIER {$struct_def.struct.createStruct($IDENTIFIER.text);} ';'
                ;



            struct_def[ProgrammableElement context] returns [StructVariable struct]
                : 'struct' '{'
                 {
                    if( $context instanceof Program){
                        $struct=$context.createNewVariable("struct");
                    }else{
                        $struct=$context.addNewNestedStructProperty("struct");
                    }
                }
                dcllist_struct[$struct] '}'
                ;

            dcllist_struct[ProgrammableElement context]
                : dcl_struct[$context] dcllist_struct_aux[$context]
                |
                ;

            dcllist_struct_aux[ProgrammableElement context]
                : dcllist_struct[$context]
                |
                ;

            dcl_struct[ProgrammableElement context]
                : var[$context]
                ;


        /* ---- ASSIGNABLE VALUES ---- */

            simpvalue[ProgrammableElement context] returns[AssignableElement value]
                : NUMERIC_INTEGER_CONST {$value=$context.newIntegerConstant($NUMERIC_INTEGER_CONST.text);}
                | NUMERIC_REAL_CONST {$value=$context.newRealConstant($NUMERIC_REAL_CONST.text);}
                | STRING_CONST {$value=$context.newStringConstant($NUMERIC_STRING_CONST.text);}

                ;



        /* ---- VARIABLE TYPES ---- */

            tbas returns [String type]
                : 'integer' {$tbas.type = "integer";}
                | 'float' {$tbas.type = "float";}
                | 'string' {$tbas.type = "string";}
                | tvoid {$tbas.type = $tvoid.void;}
                | 'struct' {$tbas.type = "struct";}
                ;

            tvoid returns [String void]
                : 'void' {$tvoid.void = "void";}
                ;




    // **** FUNCTION IMPLEMENTATION SECTION ****
    // -----------------------------------------

        funcdef [Program context]
            : funchead[$context] /*curly_open*/ '{' funcdef_aux[$funchead.returnFunction]
            ;

        funcdef_aux[Function context]
            : code[$context] /*curly_close*/ '}'
            | /*curly_close*/ '}'
            ;

        /* ---- FUNCTION HEAD ---- */

            funchead [ProgrammableElement context] returns [Function returnFunction]
                : tbas IDENTIFIER '('{$funchead.returnFunction = context.addNewFunction($tbas.type, $IDENTIFIER.text);} funchead_aux [$funchead.returnFunction]
                ;

            funchead_aux[Function context]
                : typedef[$context]   ')'
                |  ')'
                ;


        /* ---- FUNCTION PARAMETERS ---- */

            typedef [Function context]
                : tbas IDENTIFIER {$context.addParam($tbas.type, $IDENTIFIER.text);} typedef_aux[$context]

                ;

            typedef_aux [Function context]
                : ',' typedef[$context]
                |
                ;



    // **** MAIN-PROGRAM-SENTENCES SECTION ****
    // ----------------------------------------

        /* ---- FUNCION PRINCIPAL ---- */

            mainhead[Program context] returns [Function contextMain]
                : tvoid 'Main' '(' {Function newContext = context.createNewMainFunction();
                                    $context=newContext;}
                 mainhead_aux[$context]
                {$contextMain = $context;}
                ;

            mainhead_aux[Function context]
                : typedef[$context]  ')'
                | ')'
                ;



        /* ---- CODE BLOCK ---- */

            code[MasterSentenceContainer context]
                : sent[$context] code_aux[$context]
                ;

            code_aux[MasterSentenceContainer context]
                : code[$context]
                |
                ;

        /* ---- SENTENCES ---- */

            sent[MasterSentenceContainer context]

                : asig[$context]  ';'{$context.addNewVariableAssign($asig.name,$asig.value);}
                | vardef_and_asig  ';'
                | vardef_code[$context]  ';'
                | funccall[$context]   ';' {$context.addNewFunctionCall($funccall.return_function);}
                | return_func ';'
                | if
                | while
                | dowhile
                | for
                ;

        /* ---- VARIABLE DEFINITIONS ---- */
        
            vardef_code[MasterSentenceContainer context]
                : simple_vardef_code
                | struct_vardef[$context]
                ;

            simple_vardef_code
                : tbas IDENTIFIER
                ;
    

        
        /* ---- ASSIGNMENTS ---- */

            asig[MasterSentenceContainer context] returns [ String name, AssignableElement value]
                : IDENTIFIER {$name=$IDENTIFIER.text;} '=' exp[$context]
                ;



            vardef_and_asig
                : simple_vardef_code  '=' exp
                ;

        /* ---- FUNCTION CALLS ---- */

            funccall[ProgrammableElement context] returns [FunctionCall return_function]
                : IDENTIFIER  funccall_aux[$context,$IDENTIFIER.text] {$return_function=$funccall_aux.return_function;}
                | CONST_DEF_IDENTIFIER
                ;



            funccall_aux[ProgrammableElement context, String name] returns [FunctionCall return_function]
                :  subpparamlist[$context,$name] {$return_function=$subpparamlist.return_function;}
                |
                ;

            subpparamlist[ProgrammableElement context, String name] returns [FunctionCall return_function]
                : '(' { $subpparamlist.reference=$context.newFunctionCall($name)} explist[$context,$function]
                    {$return_function=$explist.return_function;}  ')'
                ;

            explist[ProgrammableElement context,FunctionCall function] returns [FunctionCall return_function]
                : exp[$context] {$function.addParam($exp.value);} explist_aux[$context,$function] {$return_function=$function;}
                ;

            explist_aux[ProgrammableElement context,FunctionCall function]
                :
                |  ',' explist[$context,$function]
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
                : 'if' expcond '{' sentlist_aux[context] if_aux
                ;

            if_aux
                : else
                |
                ;

            else
                : 'else' else_aux
                ;

            else_aux
                : '{' sentlist_aux[context]
                | if
                ;

        /* ---- WHILE SENTENCE ---- */

            while
                : 'while' '(' expcond ')' '{' sentlist_aux[context]
                ;

        /* ---- DO-WHILE SENTENCE ---- */

            dowhile
                : 'do' '{' sentlist_aux[context] 'while' '(' expcond ')' ';'
                ;

        /* ---- FOR SENTENCE ---- */

            for
                : 'for' '(' for_aux
                ;

            for_aux
                : asig ';' expcond ';' asig ')' '{' sentlist_aux[context]
                | vardef_and_asig ';' expcond ';' asig ')' '{' sentlist_aux[context]
                | simple_vardef_code ';' expcond ';' asig ')' '{' sentlist_aux[context]
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
                | '&&'
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

            exp[MasterSentenceContainer context] returns[AssignableElement value]
                :
                factor[$context] exp_aux[$context,$factor.value] {$value=$exp_aux.value}
                ;

            exp_aux[MasterSentenceContainer context,AssignableElement left] returns[AssignableElement value]
                : op[$context,$left] exp[$context] {$exp_aux.value=$op.operation.secondOperand($exp.value);}
                 exp_aux[$context,$exp_aux.value]
                | {$value=$left; }

                ;

            op[MasterSentenceContainer context ,AssignableElement left ] returns[ArithmeticOperation operation]
                : '+' {$operation=$context.newArithmeticOperation().sum().firstOperand($left);}
                | '-' {$operation=$factory.newArithmeticOperation().subtraction().firstOperand($left);}
                | '*' {$operation=$factory.newArithmeticOperation().multiplication().firstOperand($left);}
                | 'DIV' {$operation=$factory.newArithmeticOperation().division().firstOperand($left);}
                | 'MOD' {$operation=$factory.newArithmeticOperation().modulus().firstOperand($left);}
                ;

            factor[MasterSentenceContainer context] returns [AssignableElement value]
                : simpvalue_code[$context]{$value=$simpvalue_code.value;}
                | '(' exp[$context] ')' {$value=($exp.value).setParenthesis();}
                | funccall[$context] {$value=$funcall.return_function;}
                ;

            simpvalue_code[MasterSentenceContainer context] returns [AssignableElement value]
                : NUMERIC_INTEGER_CONST {$value=$context.newIntegerConstant($NUMERIC_INTEGER_CONST.text);}
                | NUMERIC_REAL_CONST {$value=$context.newRealConstant($NUMERIC_REAL_CONST.text);}
                | STRING_CONST {$value=$context.newStringConstant($NUMERIC_STRING_CONST.text);}
                | IDENTIFIER {$value=$context.newSymbolReference("VARIABLE",$IDENTIFIER.text);}
                | CONST_DEF_IDENTIFIER{$value=$context.newSymbolReference("CONSTANT",$CONST_DEF_IDENTIFIER.text);}
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
            :   ('/''/'(~'\n'*)('\n'|EOF)
            |   '/'('*'+)(('*'+)(~[/]+)|(~[/*]+)|(~[*]*)('/'+)|'\n')* ('*'+)'/') -> channel(HIDDEN)
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