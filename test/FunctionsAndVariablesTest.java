import semantic.element.Function;
import semantic.element.Program;
import semantic.element.sentence.conditional_branch.ElseBranch;
import semantic.element.sentence.conditional_branch.ElseIfBranch;
import semantic.element.sentence.conditional_branch.IfBranch;
import semantic.element.sentence.loop_sentence.ForLoop;
import semantic.element.sentence.loop_sentence.WhileLoop;
import semantic.element.variable.StructVariable;
import semantic.utils.Constants;

public class FunctionsAndVariablesTest {
//    public static void runTest_1() {
//        /*
//
//                integer calc_fact(integer number) {
//                    if (number == 0)  {
//                        return 0;
//                    } else {
//                        return number * calc_fact(number - 1);
//                    }
//                }
//
//                void Main () {
//                    integer resultado;
//
//                    resultado = assert_equals(6, calc_fact(3));
//
//                    if (resultado == 1) {
//                        print("TEST PASADO CON EXITO");
//                        exit(0);
//                    } else {
//                        print("TEST PASADO CON EXITO");
//                        exit(1);
//                    }
//                }
//
//         */
//
//        Constants.FILE_NAME = "new_program";
//
//        Program program = new Program();
//
//
//            // integer calc_fact(integer number) {
//                Function calc_fact_function = program.createNewFunction("integer", "calc_fact")
//                        .addParam("integer", "number");
//
//                // if (number == 0)  {
//                    IfBranch calc_fact_function_if = (IfBranch) calc_fact_function.addNewIfBranch(
//                            calc_fact_function.newComparisonOperation().equal()
//                                    .firstOperand(
//                                        calc_fact_function.newSymbolReference("VARIABLE", "number")
//                                    )
//                                    .secondOperand(calc_fact_function.newIntegerConstant("0"))
//                    );
//
//                    // return 0;
//                        calc_fact_function_if.addNewReturnPoint(calc_fact_function_if.newIntegerConstant("0"));
//
//                // } else {
//                    ElseBranch calc_fact_function_else = (ElseBranch) calc_fact_function.addNewElseBranch(calc_fact_function_if);
//
//                    // return number * calc_fact(number - 1);
//                        calc_fact_function_else.addNewReturnPoint(calc_fact_function_else.newArithmeticOperation().multiplication()
//                                .firstOperand(
//                                        calc_fact_function_else.newSymbolReference("VARIABLE", "number")
//                                )
//                                .secondOperand(calc_fact_function_else.newFunctionCall("calc_fact").addParam(calc_fact_function_else.newArithmeticOperation().subtraction()
//                                        .firstOperand(calc_fact_function_else.newSymbolReference("VARIABLE", "number"))
//                                        .secondOperand(calc_fact_function_if.newIntegerConstant("0"))
//                                ).call())
//                        );
//
//                // }
//            // }
//
//
//            // void Main () {
//
//                Function mainFunction = program.createNewMainFunction();
//
//                // integer resultado;
//
//                mainFunction.addNewVariableDefinition("integer", "resultado");
//
//                // resultado = assert_equals(6, calc_fact(3));
//
//                mainFunction.addNewVariableAssign("resultado", mainFunction.newFunctionCall("asset_equals")
//                        .addParam(mainFunction.newIntegerConstant("6"))
//                        .addParam(mainFunction.newFunctionCall("calc_fact")
//                                .addParam(mainFunction.newIntegerConstant("3"))
//                                .call()
//                        )
//                        .call()
//                );
//
//                // if (resultado == 1) {
//
//                IfBranch main_if_branch = mainFunction.addNewIfBranch(
//                        mainFunction.newComparisonOperation().equal()
//                                .firstOperand(
//                                        mainFunction.newSymbolReference("VARIABLE","resultado")
//                                )
//                                .secondOperand(
//                                        mainFunction.newIntegerConstant("1")
//                                )
//                );
//
//                    // print("TEST PASADO CON EXITO");
//
//                    main_if_branch.addNewFunctionCall(
//                            main_if_branch.newFunctionCall("print")
//                                    .addParam(main_if_branch.newStringConstant("TEST PASADO CON EXITO"))
//                                    .call()
//                    );
//
//                    // exit(0);
//
//                    main_if_branch.addNewFunctionCall(
//                            main_if_branch.newFunctionCall("exit")
//                                    .addParam(main_if_branch.newIntegerConstant("0"))
//                                    .call()
//                    );
//
//                // } else {
//
//                ElseBranch main_else_branch = mainFunction.addNewElseBranch(main_if_branch);
//
//                    // print("TEST PASADO SIN EXITO");
//
//                    main_else_branch.addNewFunctionCall(
//                            main_else_branch.newFunctionCall("print")
//                                    .addParam(main_else_branch.newStringConstant("TEST PASADO SIN EXITO"))
//                                    .call()
//                    );
//
//                    // exit(1);
//
//                    main_else_branch.addNewFunctionCall(
//                            main_else_branch.newFunctionCall("exit")
//                                    .addParam(main_else_branch.newIntegerConstant("1"))
//                                    .call()
//                    );
//
//                // }
//
//    }


    public static void runTest_2() {
        /*

                #define FECHA 2023
                #define CODIGO 10
                integer n_alumnos;
                float __valor_real;
                string nombre = "alumno1";
                struct {
                    string nombre;
                    string apellido;
                    integer n_expediente;
                    integer promocion = 2023;
                }alumno;

                string codificar_cadena(string cadena, integer desplazamiento){
                    string cadena2;
                    cadena2 = (cadena + desplazamiento) * random(desplazamiento);
                    return(cadena2);
                }

                void save(string nombre){
                    fopen("file.txt", "w");
                    printf(nombre);
                }

                void Main (){
                    while(n_alumnos < CODIGO){
                        string codificado;
                        codificado = codificar_cadena(nombre, FECHA);
                        save(codificado);
                        n_alumnos = n_alumnos + 1;
                        codificado = codificar_cadena(nombre, (CODIGO DIV codificado));

                        if (codificado==__valor_real){
                            __valor_real = FLOAT(n_alumos);
                            }

                        if (codificado==__valor_real & n_alumnos > 1){
                            for (i=codificado; i>CODIGO; i = i + 1){
                                codificado = codificado - 1;
                            }
                            printf(codificado);
                        }
                        else if (!(codificado*CODIGO > FECHA)) {
                            printf(n_alumno);
                        }
                        else {
                            printf('fin del \'programa\' con "codificacion" %s', codificado);
                        }
                    }
                }

         */

        Constants.FILE_NAME = "new_program";

        Program program = new Program();

        //  #define FECHA 2023
        program.createNewConstant("FECHA", program.newIntegerConstant("2023", 1,15), 1, 1);

        //  #define CODIGO 10
        program.createNewConstant("CODIGO", program.newIntegerConstant("10", 2, 16), 2, 1);

        //  integer n_alumnos;
        program.createNewVariableDefinition("integer", "n_alumnos",3,1);

        //  float __valor_real;
        program.createNewVariableDefinition("float", "__valor_real",4,1);

        //  string nombre = "alumno1";
        program.createNewVariableDefinitionAndAssign("string", "nombre", program.newStringConstant("\"alumno1\"", 5, 17),5,1);

        //  struct {
        StructVariable program_struct = (StructVariable) program.createNewVariableDefinition("STRUCT", 99, 99).getVariable();

            //  string nombre;
            program_struct.addNewSimpleProperty("string","nombre", 99, 99);

            //  string apellido;
            program_struct.addNewSimpleProperty("string","apellido", 99, 99);

            //  integer n_expediente;
            program_struct.addNewSimpleProperty("integer","n_expediente", 99, 99);

            //  integer promocion = 2023;
            program_struct.addNewSimpleProperty("integer","promocion", program_struct.newIntegerConstant("2023", 99, 99), 99, 99);

        //  } alumno;
        program_struct.createStruct("alumno");





        //  string codificar_cadena(string cadena, integer desplazamiento){
        Function program_function_codificar_cadena = program.createNewFunction("string", "codificar_cadena", 99, 99)
                .addParam("string", "cadena", 99, 99)
                .addParam("integer", "desplazamiento", 99, 99);

            //  string cadena2;
            program_function_codificar_cadena.addNewVariableDefinition("string", "cadena2", 99, 99);

            //  cadena2 = (cadena + desplazamiento) * random(desplazamiento);
            program_function_codificar_cadena.addNewVariableAssign("cadena2", program_function_codificar_cadena.newArithmeticOperation().multiplication(99, 99)
                    .firstOperand(program_function_codificar_cadena.newArithmeticOperation().sum(99, 99)
                            .firstOperand(program_function_codificar_cadena.newSymbolReference("VARIABLE", "cadena", 99, 99))
                            .secondOperand(program_function_codificar_cadena.newSymbolReference("VARIABLE", "desplazamiento", 99, 99))
                            .setParenthesis()
                    )
                    .secondOperand(program_function_codificar_cadena.newFunctionCall("random", 99, 99)
                            .addParam(program_function_codificar_cadena.newSymbolReference("VARIABLE", "desplazamiento", 99, 99))
                            .call()
                    )
                    , 99, 99);

            //  return(cadena2);
            program_function_codificar_cadena.addNewReturnPoint(program_function_codificar_cadena.newSymbolReference("VARIABLE", "cadena2", 99, 99), 99, 99);

        //  }



        //  void save(string nombre){
        Function program_function_save = program.createNewFunction("void", "save", 99, 99)
                .addParam("string", "nombre", 99, 99);

            //  fopen("file.txt", "w");
            program_function_save.addNewFunctionCall(program_function_save.newFunctionCall("fopen", 99, 99)
                    .addParam(program_function_save.newStringConstant("\"file.text\"", 99, 99))
                    .addParam(program_function_save.newStringConstant("\"w\"", 99, 99))
                    .call()
            );

            //  printf(nombre);
            program_function_save.addNewFunctionCall(program_function_save.newFunctionCall("printf", 99, 99)
                    .addParam(program_function_save.newSymbolReference("VARIABLE", "nombre", 99, 99))
                    .call()
            );

        //  }

        //  void Main (){
        Function program_function_main = program.createNewMainFunction(99, 99);

            //  while(n_alumnos < CODIGO){
            WhileLoop program_function_main_while = program_function_main.addNewWhileLoop(program_function_main.newComparisonOperation().lower(99, 99)
                    .firstOperand(program_function_main.newSymbolReference("VARIABLE", "n_alumnos", 99, 99))
                    .secondOperand(program_function_main.newSymbolReference("CONSTANT", "CODIGO", 99, 99))
                    , 99, 99);

                //  string codificado;
                program_function_main_while.addNewVariableDefinition("string", "codificado", 99, 99);

                //  codificado = codificar_cadena(nombre, FECHA);
                program_function_main_while.addNewVariableAssign("codificado", program_function_main_while.newFunctionCall("codificar_cadena", 99, 99)
                        .addParam(program_function_main_while.newSymbolReference("VARIABLE", "nombre", 99, 99))
                        .addParam(program_function_main_while.newSymbolReference("CONSTANT", "FECHA", 99, 99))
                        .call()
                        , 99, 99);

                //  save(codificado);
                program_function_main_while.addNewFunctionCall(program_function_main_while.newFunctionCall("save", 99, 99)
                        .addParam(program_function_main_while.newSymbolReference("VARIABLE", "codificado", 99, 99))
                        .call()
                );

                //  n_alumnos = n_alumnos + 1;
                program_function_main_while.addNewVariableAssign("n_alumnos", program_function_main_while.newArithmeticOperation().sum(99, 99)
                        .firstOperand(program_function_main_while.newSymbolReference("VARIABLE", "n_alumnos", 99, 99))
                        .secondOperand(program_function_main_while.newIntegerConstant("1", 99, 99))
                        , 99, 99);

                //  codificado = codificar_cadena(nombre, (CODIGO DIV codificado));
                program_function_main_while.addNewVariableAssign("codificado", program_function_main_while.newFunctionCall("codificar_cadena", 99, 99)
                        .addParam(program_function_main_while.newSymbolReference("VARIABLE", "nombre", 99, 99))
                        .addParam(program_function_main_while.newArithmeticOperation().division(99, 99)
                                .firstOperand(program_function_main_while.newSymbolReference("CONSTANT", "CODIGO", 99, 99))
                                .secondOperand(program_function_main_while.newSymbolReference("VARIABLE", "codificado", 99, 99))
                                .setParenthesis()
                        )
                        .call()
                        , 99, 99);

                //  if (codificado==__valor_real){
                IfBranch program_function_main_while_if = program_function_main_while.addNewIfBranch(program_function_main_while.newComparisonOperation().equal(99, 99)
                        .firstOperand(program_function_main_while.newSymbolReference("VARIABLE", "codificado", 99, 99))
                        .secondOperand(program_function_main_while.newSymbolReference("VARIABLE", "__valor_real", 99, 99))
                        , 99, 99);

                    //  __valor_real = FLOAT(n_alumos);
                    program_function_main_while_if.addNewVariableAssign("__valor_real", program_function_main_while_if.newFunctionCall("FLOAT", 99, 99)
                            .addParam(program_function_main_while_if.newSymbolReference("VARIABLE", "n_alumnos", 99, 99))
                            .call()
                            , 99, 99);


                //  }

                //  if (codificado==__valor_real & n_alumnos > 1){
                IfBranch program_function_main_while_if_1 = program_function_main_while.addNewIfBranch(program_function_main_while.newLogicalOperation().and(99, 99)
                        .firstOperand(program_function_main_while.newComparisonOperation().equal(99, 99)
                                .firstOperand(program_function_main_while.newSymbolReference("VARIABLE", "codificado", 99, 99))
                                .secondOperand(program_function_main_while.newSymbolReference("VARIABLE", "__valor_real", 99, 99))
                        )
                        .secondOperand(program_function_main_while.newComparisonOperation().greater(99, 99)
                                .firstOperand(program_function_main_while.newSymbolReference("VARIABLE", "n_alumnos", 99, 99))
                                .secondOperand(program_function_main_while.newIntegerConstant("1", 99, 99))
                        )
                        , 99, 99);

                    //  for (i=codificado; i>CODIGO; i = i + 1){
                    ForLoop program_function_main_while_if_1_for = program_function_main_while_if_1.addNewForLoop(
                            "i",
                            program_function_main_while_if_1.newSymbolReference("VARIABLE", "codificado", 99, 99),
                            program_function_main_while_if_1.newComparisonOperation().greater(99,99)
                                    .firstOperand(program_function_main_while_if_1.newSymbolReference("VARIABLE", "i", true, 99, 99))
                                    .secondOperand(program_function_main_while_if_1.newSymbolReference("CONSTANT", "CODIGO", 99, 99)),
                            "i",
                            program_function_main_while_if_1.newArithmeticOperation().sum(99, 99)
                                    .firstOperand(program_function_main_while_if_1.newSymbolReference("VARIABLE", "i",true, 99, 99))
                                    .secondOperand(program_function_main_while_if_1.newIntegerConstant("1", 99, 99)),
                            99,
                            99
                    );

                        //  codificado = codificado - 1;
                        program_function_main_while_if_1_for.addNewVariableAssign("codificado", program_function_main_while_if_1_for.newArithmeticOperation().subtraction(99,99)
                                .firstOperand(program_function_main_while_if_1_for.newSymbolReference("VARIABLE", "codificado", 99, 99))
                                .secondOperand(program_function_main_while_if_1_for.newIntegerConstant("1", 99, 99))
                        , 99 , 99);
                //  }

                //  printf(codificado);
                program_function_main_while_if_1.addNewFunctionCall(program_function_main_while_if_1.newFunctionCall("printf", 99, 99)
                        .addParam(program_function_main_while_if_1.newSymbolReference("VARIABLE", "codificado", 99, 99))
                        .call()
                );

                //  }

                //  else if (!(codificado*CODIGO > FECHA)) {
                 ElseIfBranch program_function_main_while_else_if = program_function_main_while.addNewElseIfBranch(program_function_main_while.newLogicalOperation().not(99,99)
                        .firstOperand(program_function_main_while.newComparisonOperation().greater(99,99)
                                .firstOperand(program_function_main_while.newArithmeticOperation().multiplication(99,99)
                                        .firstOperand(program_function_main_while.newSymbolReference("VARIABLE", "codificado", 99, 99))
                                        .secondOperand(program_function_main_while.newSymbolReference("CONSTANT", "CODIGO", 99, 99))
                                )
                                .secondOperand(program_function_main_while.newSymbolReference("CONSTANT", "FECHA", 99, 99))
                        )
                        .setParenthesis()
                ,program_function_main_while_if_1,99, 99);

                    //  printf(n_alumno);
                    program_function_main_while_else_if.addNewFunctionCall(program_function_main_while_else_if.newFunctionCall("printf",99,99)
                            .addParam(program_function_main_while_else_if.newSymbolReference("VARIABLE", "n_alumnos", 99, 99))
                            .call()
                    );

                //  }

                //  else {
                ElseBranch program_function_main_while_else = program_function_main_while.addNewElseBranch(program_function_main_while_else_if, 99,99);

                    //  printf('fin del \'programa\' con "codificacion" %s', codificado);
                    program_function_main_while_else.addNewFunctionCall(program_function_main_while_else.newFunctionCall("printf",99,99)
                            .addParam(program_function_main_while_else.newStringConstant("'fin del \\'programa\\' con \"codificacion\" %s'",99,99))
                            .addParam(program_function_main_while_else.newSymbolReference("VARIABLE", "codificado", 99, 99))
                            .call()
                    );

                //  }
        //  }
    //  }


       String html = program.toHTML(0, "");
    }


    public static void runTest_3() {
        /*

                #define FECHA 2023
                #define CODIGO 10
                integer n_alumnos;
                float __valor_real;
                string nombre = "alumno1";
                struct {
                    string nombre;
                    string apellido;
                    integer n_expediente;
                    integer promocion = 2023;
                }alumno;

                string codificar_cadena(string cadena, integer desplazamiento){
                    string cadena2;
                    cadena2 = (cadena + desplazamiento) * random(desplazamiento);
                    return(cadena2);
                }

                void save(string nombre){
                    fopen("file.txt", "w");
                    printf(nombre);
                }

                void Main (){
                    while(n_alumnos < CODIGO){
                        string codificado;
                        codificado = codificar_cadena(nombre, FECHA);
                        save(codificado);
                        n_alumnos = n_alumnos + 1;
                        codificado = codificar_cadena(nombre, (CODIGO DIV codificado));

                        if (codificado==__valor_real){
                            __valor_real = FLOAT(n_alumos);
                            }

                        if (codificado==__valor_real & n_alumnos > 1){
                            for (i=codificado; i>CODIGO; i = i + 1){
                                codificado = codificado - 1;
                            }
                            printf(codificado);
                        }
                        else if (!(codificado*CODIGO > FECHA)) {
                            printf(n_alumno);
                        }
                        else {
                            printf('fin del \'programa\' con "codificacion" %s', codificado);
                        }
                    }
                }

         */

        Constants.FILE_NAME = "new_program";

        Program program = new Program();

        //  #define FECHA 2023
        program.createNewConstant("FECHA", program.newIntegerConstant("2023", 1,15), 1, 1);

        //  #define CODIGO 10
        program.createNewConstant("CODIGO", program.newIntegerConstant("10", 2, 16), 2, 1);

        //  integer n_alumnos;
        program.createNewVariableDefinition("integer", "n_alumnos",3,1);

        //  float __valor_real;
        program.createNewVariableDefinition("float", "__valor_real",4,1);

        //  string nombre = "alumno1";
        program.createNewVariableDefinitionAndAssign("string", "nombre", program.newStringConstant("\"alumno1\"", 5, 17),5,1);

        //  struct {
        StructVariable program_struct = (StructVariable) program.createNewVariableDefinition("STRUCT", 99, 99).getVariable();

        //  string nombre;
        program_struct.addNewSimpleProperty("string","nombre", 99, 99);

        //  string apellido;
        program_struct.addNewSimpleProperty("string","apellido", 99, 99);

        //  integer n_expediente;
        program_struct.addNewSimpleProperty("integer","n_expediente", 99, 99);

        //  integer promocion = 2023;
        program_struct.addNewSimpleProperty("integer","promocion", program_struct.newIntegerConstant("2023", 99, 99), 99, 99);

            StructVariable program_struct_nested_struct = program_struct.addNewNestedStructProperty(99,99);

            program_struct_nested_struct.addNewSimpleProperty("integer", "numeracion", 99, 99);

            program_struct_nested_struct.addNewSimpleProperty("integer", "n_expediente", 99, 99);

            program_struct_nested_struct.createStruct("__valor_real");

        //  } alumno;
        program_struct.createStruct("alumno");





        //  string codificar_cadena(string cadena, integer desplazamiento){
        Function program_function_codificar_cadena = program.createNewFunction("string", "codificar_cadena", 99, 99)
                .addParam("string", "cadena", 99, 99)
                .addParam("integer", "desplazamiento", 99, 99);

        //  string cadena2;
        program_function_codificar_cadena.addNewVariableDefinition("string", "cadena2", 99, 99);

        //  cadena2 = (cadena + desplazamiento) * random(desplazamiento);
        program_function_codificar_cadena.addNewVariableAssign("cadena2", program_function_codificar_cadena.newArithmeticOperation().multiplication(99, 99)
                        .firstOperand(program_function_codificar_cadena.newArithmeticOperation().sum(99, 99)
                                .firstOperand(program_function_codificar_cadena.newSymbolReference("VARIABLE", "cadena", 99, 99))
                                .secondOperand(program_function_codificar_cadena.newSymbolReference("VARIABLE", "desplazamiento", 99, 99))
                                .setParenthesis()
                        )
                        .secondOperand(program_function_codificar_cadena.newFunctionCall("random", 99, 99)
                                .addParam(program_function_codificar_cadena.newSymbolReference("VARIABLE", "desplazamiento", 99, 99))
                                .call()
                        )
                , 99, 99);

        //  return(cadena2);
        program_function_codificar_cadena.addNewReturnPoint(program_function_codificar_cadena.newSymbolReference("VARIABLE", "cadena2", 99, 99), 99, 99);

        //  }



        //  void save(string nombre){
        Function program_function_save = program.createNewFunction("void", "save", 99, 99)
                .addParam("string", "nombre", 99, 99);

        //  fopen("file.txt", "w");
        program_function_save.addNewFunctionCall(program_function_save.newFunctionCall("fopen", 99, 99)
                .addParam(program_function_save.newStringConstant("\"file.text\"", 99, 99))
                .addParam(program_function_save.newStringConstant("\"w\"", 99, 99))
                .call()
        );

        //  printf(nombre);
        program_function_save.addNewFunctionCall(program_function_save.newFunctionCall("printf", 99, 99)
                .addParam(program_function_save.newSymbolReference("VARIABLE", "nombre", 99, 99))
                .call()
        );

        //  }

        //  void Main (){
        Function program_function_main = program.createNewMainFunction(99, 99);

        //  while(n_alumnos < CODIGO){
        WhileLoop program_function_main_while = program_function_main.addNewWhileLoop(program_function_main.newComparisonOperation().lower(99, 99)
                        .firstOperand(program_function_main.newSymbolReference("VARIABLE", "n_alumnos", 99, 99))
                        .secondOperand(program_function_main.newSymbolReference("CONSTANT", "CODIGO", 99, 99))
                , 99, 99);

        //  string codificado;
        program_function_main_while.addNewVariableDefinition("string", "__valor_real", 99, 99);

        //  codificado = codificar_cadena(nombre, FECHA);
        program_function_main_while.addNewVariableAssign("codificado", program_function_main_while.newFunctionCall("codificar_cadena", 99, 99)
                        .addParam(program_function_main_while.newSymbolReference("VARIABLE", "nombre", 99, 99))
                        .addParam(program_function_main_while.newSymbolReference("CONSTANT", "FECHA", 99, 99))
                        .call()
                , 99, 99);

        //  save(codificado);
        program_function_main_while.addNewFunctionCall(program_function_main_while.newFunctionCall("save", 99, 99)
                .addParam(program_function_main_while.newSymbolReference("VARIABLE", "codificado", 99, 99))
                .call()
        );

        //  n_alumnos = n_alumnos + 1;
        program_function_main_while.addNewVariableAssign("n_alumnos", program_function_main_while.newArithmeticOperation().sum(99, 99)
                        .firstOperand(program_function_main_while.newSymbolReference("VARIABLE", "n_alumnos", 99, 99))
                        .secondOperand(program_function_main_while.newIntegerConstant("1", 99, 99))
                , 99, 99);

        //  codificado = codificar_cadena(nombre, (CODIGO DIV codificado));
        program_function_main_while.addNewVariableAssign("codificado", program_function_main_while.newFunctionCall("codificar_cadena", 99, 99)
                        .addParam(program_function_main_while.newSymbolReference("VARIABLE", "nombre", 99, 99))
                        .addParam(program_function_main_while.newArithmeticOperation().division(99, 99)
                                .firstOperand(program_function_main_while.newSymbolReference("CONSTANT", "CODIGO", 99, 99))
                                .secondOperand(program_function_main_while.newSymbolReference("VARIABLE", "codificado", 99, 99))
                                .setParenthesis()
                        )
                        .call()
                , 99, 99);

        //  if (codificado==__valor_real){
        IfBranch program_function_main_while_if = program_function_main_while.addNewIfBranch(program_function_main_while.newComparisonOperation().equal(99, 99)
                        .firstOperand(program_function_main_while.newSymbolReference("VARIABLE", "codificado", 99, 99))
                        .secondOperand(program_function_main_while.newSymbolReference("VARIABLE", "__valor_real", 99, 99))
                , 99, 99);

        //  __valor_real = FLOAT(n_alumos);
        program_function_main_while_if.addNewVariableAssign("__valor_real", program_function_main_while_if.newFunctionCall("FLOAT", 99, 99)
                        .addParam(program_function_main_while_if.newSymbolReference("VARIABLE", "n_alumnos", 99, 99))
                        .call()
                , 99, 99);


        //  }

        //  if (codificado==__valor_real & n_alumnos > 1){
        IfBranch program_function_main_while_if_1 = program_function_main_while.addNewIfBranch(program_function_main_while.newLogicalOperation().and(99, 99)
                        .firstOperand(program_function_main_while.newComparisonOperation().equal(99, 99)
                                .firstOperand(program_function_main_while.newSymbolReference("VARIABLE", "codificado", 99, 99))
                                .secondOperand(program_function_main_while.newSymbolReference("VARIABLE", "__valor_real", 99, 99))
                        )
                        .secondOperand(program_function_main_while.newComparisonOperation().greater(99, 99)
                                .firstOperand(program_function_main_while.newSymbolReference("VARIABLE", "n_alumnos", 99, 99))
                                .secondOperand(program_function_main_while.newIntegerConstant("1", 99, 99))
                        )
                , 99, 99);

        //  for (i=codificado; i>CODIGO; i = i + 1){
        ForLoop program_function_main_while_if_1_for = program_function_main_while_if_1.addNewForLoop(
                "i",
                program_function_main_while_if_1.newSymbolReference("VARIABLE", "codificado", 99, 99),
                program_function_main_while_if_1.newComparisonOperation().greater(99,99)
                        .firstOperand(program_function_main_while_if_1.newSymbolReference("VARIABLE", "i", true, 99, 99))
                        .secondOperand(program_function_main_while_if_1.newSymbolReference("CONSTANT", "CODIGO", 99, 99)),
                "i",
                program_function_main_while_if_1.newArithmeticOperation().sum(99, 99)
                        .firstOperand(program_function_main_while_if_1.newSymbolReference("VARIABLE", "i",true, 99, 99))
                        .secondOperand(program_function_main_while_if_1.newIntegerConstant("1", 99, 99)),
                99,
                99
        );

        //  codificado = codificado - 1;
        program_function_main_while_if_1_for.addNewVariableAssign("codificado", program_function_main_while_if_1_for.newArithmeticOperation().subtraction(99,99)
                        .firstOperand(program_function_main_while_if_1_for.newSymbolReference("VARIABLE", "codificado", 99, 99))
                        .secondOperand(program_function_main_while_if_1_for.newIntegerConstant("1", 99, 99))
                , 99 , 99);
        //  }

        //  printf(codificado);
        program_function_main_while_if_1.addNewFunctionCall(program_function_main_while_if_1.newFunctionCall("printf", 99, 99)
                .addParam(program_function_main_while_if_1.newSymbolReference("VARIABLE", "codificado", 99, 99))
                .call()
        );

        //  }

        //  else if (!(codificado*CODIGO > FECHA)) {
        ElseIfBranch program_function_main_while_else_if = program_function_main_while.addNewElseIfBranch(program_function_main_while.newLogicalOperation().not(99,99)
                        .firstOperand(program_function_main_while.newComparisonOperation().greater(99,99)
                                .firstOperand(program_function_main_while.newArithmeticOperation().multiplication(99,99)
                                        .firstOperand(program_function_main_while.newSymbolReference("VARIABLE", "codificado", 99, 99))
                                        .secondOperand(program_function_main_while.newSymbolReference("CONSTANT", "CODIGO", 99, 99))
                                )
                                .secondOperand(program_function_main_while.newSymbolReference("CONSTANT", "FECHA", 99, 99))
                        )
                        .setParenthesis()
                ,program_function_main_while_if_1,99, 99);

        //  printf(n_alumno);
        program_function_main_while_else_if.addNewFunctionCall(program_function_main_while_else_if.newFunctionCall("printf",99,99)
                .addParam(program_function_main_while_else_if.newSymbolReference("VARIABLE", "n_alumnos", 99, 99))
                .call()
        );

        //  }

        //  else {
        ElseBranch program_function_main_while_else = program_function_main_while.addNewElseBranch(program_function_main_while_else_if, 99,99);

        //  printf('fin del \'programa\' con "codificacion" %s', codificado);
        program_function_main_while_else.addNewFunctionCall(program_function_main_while_else.newFunctionCall("printf",99,99)
                .addParam(program_function_main_while_else.newStringConstant("'fin del \\'programa\\' con \"codificacion\" %s'",99,99))
                .addParam(program_function_main_while_else.newSymbolReference("VARIABLE", "codificado", 99, 99))
                .call()
        );

        //  }
        //  }
        //  }


        String html = program.toHTML(0, "");
    }
}
