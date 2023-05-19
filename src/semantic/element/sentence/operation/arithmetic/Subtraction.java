package semantic.element.sentence.operation.arithmetic;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.arithmetic.ArithmeticOperation;
import semantic.utils.enums.Operation;

public class Subtraction extends ArithmeticOperation {
    public Subtraction(ProgrammableElement context) {
        super(context);
        this.name = "SUBTRACTION";
        this.symbol = "-";
        this.operationType = Operation.SUBTRACTION;
    }
}