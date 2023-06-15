package semantic.element.sentence.operation.arithmetic;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.arithmetic.ArithmeticOperation;
import semantic.utils.enums.Operation;

public class Subtraction extends ArithmeticOperation {
    public Subtraction(ProgrammableElement context, int line, int column) {
        super(context, line, column);
        this.name = "SUBTRACTION_" + line + "_" + column;
        this.symbol = "-";
        this.operationType = Operation.SUBTRACTION;

    }
}
