package semantic.element.sentence.operation.arithmetic;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.arithmetic.ArithmeticOperation;
import semantic.utils.enums.Operation;

public class Division extends ArithmeticOperation {
    public Division(ProgrammableElement context) {
        super(context);
        this.name = "DIVISION";
        this.symbol = "DIV";
        this.operationType = Operation.DIVISION;
    }
}