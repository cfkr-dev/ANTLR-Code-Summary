package semantic.element.sentence.operation.arithmetic;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.arithmetic.ArithmeticOperation;
import semantic.utils.enums.Operation;

public class Multiplication extends ArithmeticOperation {
    public Multiplication(ProgrammableElement context) {
        super(context);
        this.name = "MULTIPLICATION";
        this.symbol = "*";
        this.operationType = Operation.MULTIPLICATION;
    }
}