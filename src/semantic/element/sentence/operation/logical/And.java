package semantic.element.sentence.operation.logical;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.logical.BinaryLogicalOperation;

public class And extends BinaryLogicalOperation {
    public And(ProgrammableElement context) {
        super(context);
        this.name = "AND";
        this.symbol = "&&";
    }
}