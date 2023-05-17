import semantic.element.Function;
import semantic.element.Program;
import semantic.element.sentence.ElseBranch;
import semantic.element.sentence.IfBranch;
import semantic.element.sentence.literal.NumericIntegerConstant;
import semantic.element.sentence.operation.ArithmeticOperation;
import semantic.element.sentence.operation.LogicOperation;
import semantic.utils.Constants;
import semantic.utils.Param;

import java.util.ArrayList;
import java.util.List;

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
                List<Param> paramList = new ArrayList<>();
                paramList.add(new Param("integer", "number"));
                Function calc_fact_function = program.createNewFunction("integer", "calc_fact", paramList);

                // if (number == 0)  {
                    IfBranch calc_fact_function_if = (IfBranch) calc_fact_function.addNewIfBranch(calc_fact_function.newLogicalOperation()
                            .equal(calc_fact_function.newSymbolReference("number"), calc_fact_function.newIntegerConstant("0")));

                    // return 0;
                        calc_fact_function_if.addNewReturnPoint(calc_fact_function_if.newIntegerConstant("0"));

                // } else {
                    ElseBranch calc_fact_function_else = (ElseBranch) calc_fact_function.addNewElse(calc_fact_function_if);

                    // return number * calc_fact(number - 1);
                        calc_fact_function_else.addNewReturnPoint(
                                calc_fact_function_else.newArithmeticOperation()
                                        .mult(
                                                calc_fact_function_else.newSymbolReference("number"),
                                                calc_fact_function_else.newFunctionCall("calc_fact")
                                                        .addNewParam(
                                                                calc_fact_function_else.newArithmeticOperation()
                                                                        .sub(
                                                                            calc_fact_function_else.newSymbolReference("number"),
                                                                            calc_fact_function_else.newIntegerConstant("1")
                                                                        )
                                                        )
                                        )
                        );

                // }
            // }


            // void Main () {
                List<Param> paramList_main = new ArrayList<>();
                Function mainFunction = program.createNewMainFunction(paramList);

                // integer resultado;
                    mainFunction.createNewVariable("integer", "resultado");

                // resultado = assert_equals(6, calc_fact(3));
                    mainFunction.addNewVariableAssign("resultado",
                            mainFunction.newFunctionCall("assert_equals")
                                    .addNewParam(mainFunction.newIntegerConstant("6"))
                                    .addNewParam(mainFunction.newFunctionCall("calc_fact")
                                            .addNewParam(mainFunction.newIntegerConstant("3"))));

                // if (resultado == 1) {
                    mainFunction.addNewIfBranch()
    }
}
