package semantic.element.sentence.operation;

import semantic.element.sentence.operation.operation_master.MasterOperation;

public class ArithmeticOperation extends MasterOperation {
    public ArithmeticOperation(String exp) {
        this.expression = exp;
    }
}
