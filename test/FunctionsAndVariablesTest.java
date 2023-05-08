import enums.Element;

public class FunctionsAndVariablesTest {
    public static void runTest() throws InstantiationException {
        Constants.FILE_NAME = "new_program";


        Program program = new Program();


        Variable variable_1 = program.createNewVariable("integer", "variable_1");
        program.createNewVariable("float", "variable_2");
        Variable variable_3 = program.createNewVariable("string", "variable_3");


        variable_1.setValue("12", "NUMERIC_INTEGER_CONST");

        Variable variable_2 = (Variable) program.getSymbolByNameAndElement("variable_2", Element.VARIABLE);
        variable_2.setValue("-5.23", "NUMERIC_REAL_CONST");

        variable_3.setValue("Hello World!", "STRING_CONST");


        // Error -> Variable 1 ya está declarada
        program.createNewVariable("integer", "variable_1");

        // Error -> Variable 1 es de tipo integer
        variable_1.setValue("Hola, soy un fallo", "STRING_CONST");


        Variable variable_assign = program.createNewVariable("float", "variable_assign");
        variable_assign.setValue("-0.56E56", "NUMERIC_REAL_CONST");
        variable_assign.setValue(variable_2);

        // Error -> tipos no iguales
        variable_assign.setValue(variable_1);
    }
}