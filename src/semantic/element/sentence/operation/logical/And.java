package semantic.element.sentence.operation.logical;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.logical.BinaryLogicalOperation;
import semantic.utils.enums.Operation;

public class And extends BinaryLogicalOperation {
    public And(ProgrammableElement context) {
        super(context);
        this.name = "AND";
        this.symbol = "&&";
        this.operationType = Operation.AND;
    }
}