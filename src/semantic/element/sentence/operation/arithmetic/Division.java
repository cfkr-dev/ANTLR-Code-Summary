package semantic.element.sentence.operation.arithmetic;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.arithmetic.ArithmeticOperation;
import semantic.utils.enums.Operation;

public class Division extends ArithmeticOperation {
    public Division(ProgrammableElement context, int line, int column) {
        super(context, line, column);
        this.name = "DIVISION_" + line + "_" + column ;
        this.symbol = "DIV";
        this.operationType = Operation.DIVISION;

    }
}
