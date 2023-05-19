import semantic.element.Function;
import semantic.element.Program;
import semantic.element.sentence.conditional_branch.ElseBranch;
import semantic.element.sentence.conditional_branch.IfBranch;
import semantic.utils.Constants;

public class FunctionsAndVariablesTest {
    public static void runTest() throws InstantiationException {
        /*

                integer calc_fact(integer number) {
                    if (number == 0)  {
                        return 0;
                    } else {
                        return number * calc_fact(number - 1);
                    }
                }

                void Main () {
                    integer resultado;

                    resultado = assert_equals(6, calc_fact(3));

                    if (resultado == 1) {
                        print("TEST PASADO CON EXITO");
                        exit(0);
                    } else {
                        print("TEST PASADO CON EXITO");
                        exit(1);
                    }
                }

         */

        Constants.FILE_NAME = "new_program";

        Program program = new Program();


            // integer calc_fact(integer number) {
//                List<Param> paramList = new ArrayList<>();
//                paramList.add(new Param("integer", "number"));
                Function calc_fact_function = program.createNewFunction("integer", "calc_fact")
                        .addParam("integer", "number");

                // if (number == 0)  {
                    IfBranch calc_fact_function_if = (IfBranch) calc_fact_function.addNewIfBranch(
                            calc_fact_function.newComparisonOperation().equal()
                                    .firstOperand(
                                        calc_fact_function.newSymbolReference("VARIABLE", "number")
                                    )
                                    .secondOperand(calc_fact_function.newIntegerConstant("0"))
                    );

                    // return 0;
                        calc_fact_function_if.addNewReturnPoint(calc_fact_function_if.newIntegerConstant("0"));

                // } else {
                    ElseBranch calc_fact_function_else = (ElseBranch) calc_fact_function.addNewElse(calc_fact_function_if);

                    // return number * calc_fact(number - 1);
                        calc_fact_function_else.addNewReturnPoint(calc_fact_function_else.newArithmeticOperation().multiplication()
                                .firstOperand(
                                        calc_fact_function_else.newSymbolReference("VARIABLE", "number")
                                )
                                .secondOperand(calc_fact_function_else.newFunctionCall("calc_fact").addNewParam(calc_fact_function_else.newArithmeticOperation().subtraction()
                                        .firstOperand(calc_fact_function_else.newSymbolReference("VARIABLE", "number"))
                                        .secondOperand(calc_fact_function_if.newIntegerConstant("0"))
                                ).call())
                        );

                // }
            // }


            // void Main () {
                //List<Param> paramList_main = new ArrayList<>();
                //Function mainFunction = program.createNewMainFunction(paramList);

                // integer resultado;
                    //mainFunction.createNewVariable("integer", "resultado");

                // resultado = assert_equals(6, calc_fact(3));
                    //mainFunction.addNewVariableAssign("resultado",
                           // mainFunction.newFunctionCall("assert_equals")
                                  //  .addNewParam(mainFunction.newIntegerConstant("6"))
                                 //   .addNewParam(mainFunction.newFunctionCall("calc_fact")
                                 //           .addNewParam(mainFunction.newIntegerConstant("3"))));

                // if (resultado == 1) {
                  //  mainFunction.addNewIfBranch()
    }
}
