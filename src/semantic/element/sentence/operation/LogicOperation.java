package semantic.element.sentence.operation;

import semantic.element.sentence.operation.operation_master.MasterOperation;

public class LogicOperation extends MasterOperation {
    public LogicOperation(String exp) {
        this.expression = exp;
    }
}
