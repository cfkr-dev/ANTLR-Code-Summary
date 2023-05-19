package semantic.element.sentence.operation.logical;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.logical.BinaryLogicalOperation;
import semantic.utils.enums.Operation;

public class Or extends BinaryLogicalOperation {
    public Or(ProgrammableElement context) {
        super(context);
        this.name = "OR";
        this.symbol = "||";
        this.operationType = Operation.OR;
    }
}