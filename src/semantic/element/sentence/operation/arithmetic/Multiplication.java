package semantic.element.sentence.operation.arithmetic;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.arithmetic.ArithmeticOperation;
import semantic.utils.enums.Operation;

public class Multiplication extends ArithmeticOperation {
    public Multiplication(ProgrammableElement context, int line, int column) {
        super(context, line, column);
        this.name = "MULTIPLICATION_" + line + "_" + column;
        this.symbol = "*";
        this.operationType = Operation.MULTIPLICATION;

    }
}