package semantic.element.sentence.operation.arithmetic;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.arithmetic.ArithmeticOperation;
import semantic.utils.enums.Operation;

public class Sum extends ArithmeticOperation {
    public Sum(ProgrammableElement context) {
        super(context);
        this.name = "SUM";
        this.symbol = "+";
        this.operationType = Operation.SUM;
    }
}
