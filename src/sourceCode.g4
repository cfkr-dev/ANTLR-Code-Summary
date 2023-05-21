grammar sourceCode;

/*
|-------------------------------------|
|        GRAMMAR SPECIFICATION        |
|-------------------------------------|
*/

    // **** MAIN SECTION ****
    // ------------------------
        program_prime
            : {Program program = new Program();}  program[program]
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
                {$context.createNewConstant($CONST_DEF_IDENTIFIER.text,$simpvalue.value,$start.line,$start.pos);}
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
                         $context.createNewVariable($type,$name,$start.line,$start.pos);
                     }
                     else if($context instanceof MasterSentenceContainer ){
                         $context.addNewVariableDefinition($type,$name,$start.line,$start.pos);
                     }
                     else{
                         $context.addNewSimpleProperty($type, $name,$start.line,$start.pos);
                     }

                 }
                | '=' simpvalue[$context]
                {
                     if($context instanceof Program ){
                         $context.createNewVariable($type,$name,$start.line,$start.pos).setValue($simpvalue.value);
                     }
                     else if($context instanceof MasterSentenceContainer ){
                         $context.addNewVariableDefinitionAndAssign($type,$name,$simpvalue.value,$start.line,$start.pos);
                     }
                     else{
                         $context.addNewSimpleProperty($type,$name,$simpvalue.value,$start.line,$start.pos);
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
                        $struct=$context.createNewVariable("struct",$start.line,$start.pos);
                    }else{
                        $struct=$context.addNewNestedStructProperty("struct",$start.line,$start.pos);
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
                : NUMERIC_INTEGER_CONST {$value=$context.newIntegerConstant($NUMERIC_INTEGER_CONST.text,$start.line,$start.pos);}
                | NUMERIC_REAL_CONST {$value=$context.newRealConstant($NUMERIC_REAL_CONST.text,$start.line,$start.pos);}
                | STRING_CONST {$value=$context.newStringConstant($STRING_CONST.text,$start.line,$start.pos);}

                ;



        /* ---- VARIABLE TYPES ---- */

            tbas returns [String type]
                : 'integer' {$type = "integer";}
                | 'float' {$type = "float";}
                | 'string' {$type = "string";}
                | tvoid {$type = $tvoid.void;}
                | 'struct' {$type = "struct";}
                ;

            tvoid returns [String void]
                : 'void' {$void = "void";}
                ;


    // **** FUNCTION IMPLEMENTATION SECTION ****
    // -----------------------------------------

        funcdef [Program context]
            : funchead[$context] '{' funcdef_aux[$funchead.returnFunction]
            ;

        funcdef_aux[Function context]
            : code[$context]  '}'
            | '}'
            ;

        /* ---- FUNCTION HEAD ---- */

            funchead [ProgrammableElement context] returns [Function returnFunction]
                : tbas IDENTIFIER '('{$returnFunction = context.addNewFunction($tbas.type, $IDENTIFIER.text,$start.line,$start.pos);} funchead_aux [$returnFunction]
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
                : tvoid 'Main' '(' {$contextMain= context.createNewMainFunction($start.line,$start.pos);}
                 mainhead_aux[$contextMain]

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

                : asig[$context]  ';'{$context.addNewVariableAssign($asig.name,$asig.value,$start.line,$start.pos);}
                | vardef_and_asig[$context]  ';'
                | vardef_code[$context]  ';'
                | funccall[$context]   ';' {$context.addNewFunctionCall($funccall.return_function,$start.line,$start.pos);}
                | return_func[$context]';'
                | if[$context]
                | while[$context]
                | dowhile[$context]
                | for[$context]
                ;

        /* ---- VARIABLE DEFINITIONS ---- */
        
            vardef_code[MasterSentenceContainer context]
                : simple_vardef_code[$context] {$context.addNewVariableDefinition($simple_vardef_code.type,$simple_vardef_code.name,$start.line,$start.pos);}
                | struct_vardef[$context]
                ;

            simple_vardef_code [MasterSentenceContainer context] returns [String type,String name]
                : tbas IDENTIFIER {$type=$tbas.type; $name= $IDENTIFIER.text ;}
                ;
    

        
        /* ---- ASSIGNMENTS ---- */

            asig[MasterSentenceContainer context] returns [ String name, AssignableElement value]
                : IDENTIFIER {$name=$IDENTIFIER.text;} '=' exp[$context] {$value=$exp.value;}
                ;



            vardef_and_asig[MasterSentenceContainer context] returns[String type,String name,AssignableElement value]
                : simple_vardef_code[$context]  '=' exp[$context]
                {$context.addNewVariableDefinitionAndAssign($simple_vardef_code.type,$simple_vardef_code.name, $exp.value,$start.line,$start.pos);
                  $type=$simple_vardef_code.type;
                  $name=$simple_vardef_code.name;
                  $value=$exp.value;
                }
                ;

        /* ---- FUNCTION CALLS ---- */

            funccall[ProgrammableElement context] returns [FunctionCall return_function]
                : IDENTIFIER  funccall_aux[$context,$IDENTIFIER.text] {$return_function=$funccall_aux.return_function;}
                | CONST_DEF_IDENTIFIER funccall_aux[$context,$CONST_DEF_IDENTIFIER.text] {$return_function=$funccall_aux.return_function;}
                ;

            funccall_aux[ProgrammableElement context, String name] returns [FunctionCall return_function]
                :  subpparamlist[$context,$name] {$return_function=$subpparamlist.return_function;}
                | {$return_function= $context.newFunctionCall($name,$start.line,$start.pos);}
                ;

            subpparamlist[ProgrammableElement context, String name] returns [FunctionCall return_function]
                : '(' {FunctionCall  reference=$context.newFunctionCall($name,$start.line,$start.pos);} explist[$context,reference]
                    {$return_function=$explist.return_function;}  ')'
                ;

            explist[ProgrammableElement context,FunctionCall function] returns [FunctionCall return_function]
                : exp[$context] {$function.addParam($exp.value);} explist_aux[$context,$function] {$return_function=$function;}
                ;

            explist_aux[ProgrammableElement context,FunctionCall function]
                :
                |  ',' explist[$context,$function]
                ;



        /* ---- FUNCTION-RETURN SECTION ---- */

            return_func[MasterSentenceContainer context]
                : 'return' exp[$context] {$context.addNewReturnPoint($exp.value,$start.line,$start.pos);}
                ;

        /* ---- IF-ELSE SENTENCE ---- */

            if [MasterSentenceContainer context]
                : 'if' expcond[$context] {MasterSentenceContainer ifContext=$context.addNewIfBranch($expcond.value,$start.line,$start.pos);}'{' sentlist_aux[ifContext] if_aux[$context,ifContext]
                ;

            if_aux[MasterSentenceContainer context,MasterSentenceContainer context_if]
                : else[$context,$context_if]
                |
                ;

            else[MasterSentenceContainer context,MasterSentenceContainer context_if]
                : 'else' else_aux[$context,$context_if]
                ;

            else_aux[MasterSentenceContainer context,MasterSentenceContainer context_if]
                : '{'{MasterSentenceContainer elseC=$context.addNewElse($context_if,$start.line,$start.pos);} sentlist_aux[elseC]
                | if_else[$context,$context_if]
                ;
            if_else[MasterSentenceContainer context,MasterSentenceContainer context_if]
                : 'if' expcond[$context] {MasterSentenceContainer newContext=$context.addNewElseIfBranch($expcond.value,$context_if,$start.line,$start.pos);}'{' sentlist_aux[newContext] if_aux[newContext]
                ;

        /* ---- WHILE SENTENCE ---- */

            while[MasterSentenceContainer context]
                : 'while' '(' expcond[$context] {MasterSentenceContainer newContext=$context.addNewWhileLoop($expcond.value,$start.line,$start.pos);}')' '{' sentlist_aux[newContext]
                ;

        /* ---- DO-WHILE SENTENCE ----*/

            dowhile[MasterSentenceContainer context]
                : 'do' '{' {MasterSentenceContainer newContext=$context.addNewDoWhileLoop($start.line,$start.pos);} sentlist_aux[newContext]
                'while' '(' expcond[newContext] {newContext.createDoWhileLoop($expcond.value,$start.line,$start.pos);} ')' ';'
                ;

        /* ---- FOR SENTENCE ---- */

            for[MasterSentenceContainer context]
                : 'for' '(' for_aux[$context]
                ;

            for_aux[MasterSentenceContainer context]
                : asig1=asig[$context] ';' expcond[$context] ';' asig2=asig[$context] ')' '{'
                  {MasterSentenceContainer forContext=$context.addNewForLoop($asig1.name,$asig1.value,$expcond.value,$asig2.name,
                  $asig2.value,$start.line,$start.pos);}
                  sentlist_aux[forContext]

                | vardef_and_asig[$context] ';' expcond[$context] ';' asig[$context] ')' '{'
                  {MasterSentenceContainer forContext=$context.addNewForLoop($vardef_and_asig.type,$vardef_and_asig.name,
                  $vardef_and_asig.value,$expcond.value,$asig.name,$asig.value,$start.line,$start.pos);}
                  sentlist_aux[forContext]

                | simple_vardef_code[$context] ';' expcond[$context] ';' asig[$context] ')' '{'
                  {MasterSentenceContainer forContext=$context.addNewForLoop($simple_vardef_code.type,$simple_vardef_code.name,
                  $expcond.value,$asig.name,$asig.value,$start.line,$start.pos);}
                  sentlist_aux[forContext]
                ;

        /* ---- CONDITIONAL OPERATIONS ---- */

            expcond[MasterSentenceContainer context] returns[AssignableElement value]
                : factorcond[$context] expcond_aux[$context,$factorcond.value] {$value=$expcond_aux.value;}
                ;

            expcond_aux[MasterSentenceContainer context,AssignableElement left] returns[AssignableElement value]
                : oplog[$context,$left] expcond[$context]{AssignableElement valueH=$oplog.operation.secondOperand($expcond.value);}
                expcond_aux1=expcond_aux[$context,valueH] {$value=$expcond_aux1.value ;}
                | {$value=$left;}
                ;

            oplog[MasterSentenceContainer context ,AssignableElement left ] returns[BinaryLogicalOperation operation]
                : '||'{$operation=$context.newLogicalOperation().or($start.line,$start.pos).firstOperand($left);}
                | '&'{$operation=$context.newLogicalOperation().and($start.line,$start.pos).firstOperand($left);}
                ;

            factorcond[MasterSentenceContainer context] returns [AssignableElement value]
                : '(' expcond[$context] ')'  {$value=($expcond.value).setParenthesis();}
                | exp[$context] factorcond_aux[$context,$exp.value] {$value=$factorcond_aux.value ;}
                | '!' factorcond[$context] {$value=$context.newLogicalOperation().not($start.line,$start.pos).firstOperand($factorcond.value);}
                ;

            factorcond_aux[MasterSentenceContainer context ,AssignableElement left ] returns [AssignableElement value]
                : opcomp[$context,$left] exp[$context] {$value=$opcomp.operation.secondOperand($exp.value);}
                | {$value=$left;}
                ;

            opcomp[MasterSentenceContainer context ,AssignableElement left ] returns[ComparisonOperation operation]
                : '<' {$operation=$context.newComparisonOperation().lower($start.line,$start.pos).firstOperand($left);}
                | '>' {$operation=$context.newComparisonOperation().greater($start.line,$start.pos).firstOperand($left);}
                | '<='{$operation=$context.newComparisonOperation().lowerEqual($start.line,$start.pos).firstOperand($left);}
                | '>='{$operation=$context.newComparisonOperation().greaterEqual($start.line,$start.pos).firstOperand($left);}
                | '=='{$operation=$context.newComparisonOperation().equal($start.line,$start.pos).firstOperand($left);}
                | '!='{$operation=$context.newComparisonOperation().notEqual($start.line,$start.pos).firstOperand($left);}
                ;

        /* ---- ARITHMETIC OPERATIONS ---- */

            exp[MasterSentenceContainer context] returns[AssignableElement value]
                :factor[$context] exp_aux[$context,$factor.value] {$value=$exp_aux.value;}
                ;

            exp_aux[MasterSentenceContainer context,AssignableElement left] returns[AssignableElement value]
                : op[$context,$left] exp[$context] {AssignableElement valueH=$op.operation.secondOperand($exp.value);}
                  exp_aux1=exp_aux[$context,valueH] {$value=$exp_aux1.value ;}
                | {$value=$left;}
                ;

            op[MasterSentenceContainer context ,AssignableElement left ] returns[ArithmeticOperation operation]
                : '+' {$operation=$context.newArithmeticOperation().sum($start.line,$start.pos).firstOperand($left);}
                | '-' {$operation=$context.newArithmeticOperation().subtraction($start.line,$start.pos).firstOperand($left);}
                | '*' {$operation=$context.newArithmeticOperation().multiplication($start.line,$start.pos).firstOperand($left);}
                | 'DIV' {$operation=$context.newArithmeticOperation().division($start.line,$start.pos).firstOperand($left);}
                | 'MOD' {$operation=$context.newArithmeticOperation().modulus($start.line,$start.pos).firstOperand($left);}
                ;

            factor[MasterSentenceContainer context] returns [AssignableElement value]
                : simpvalue_code[$context]{$value=$simpvalue_code.value;}
                | '(' exp[$context] ')' {$value=($exp.value).setParenthesis();}
                | funccall[$context] {$value=($funccall.return_function).call();}
                ;

            simpvalue_code[MasterSentenceContainer context] returns [AssignableElement value]
                : NUMERIC_INTEGER_CONST {$value=$context.newIntegerConstant($NUMERIC_INTEGER_CONST.text,$start.line,$start.pos);}
                | NUMERIC_REAL_CONST {$value=$context.newRealConstant($NUMERIC_REAL_CONST.text,$start.line,$start.pos);}
                | STRING_CONST {$value=$context.newStringConstant($STRING_CONST.text,$start.line,$start.pos);}
                | IDENTIFIER
                {
                    if($context instanceof ForLoop){
                        $value=$context.newSymbolReference("VARIABLE",$IDENTIFIER.text,true,$start.line,$start.pos);
                    }else{
                        $value=$context.newSymbolReference("VARIABLE",$IDENTIFIER.text,$start.line,$start.pos);
                    }

                }
                | CONST_DEF_IDENTIFIER{$value=$context.newSymbolReference("CONSTANT",$CONST_DEF_IDENTIFIER.text,$start.line,$start.pos);}
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