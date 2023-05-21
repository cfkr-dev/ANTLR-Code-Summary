package semantic.element.sentence.operation.logical;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.logical.UnaryLogicalOperation;
import semantic.utils.enums.Operation;

public class Not extends UnaryLogicalOperation {
    public Not(ProgrammableElement context, int line, int column) {
        super(context, line, column);
        this.name = "NOT";
        this.symbol = "!";
        this.operationType = Operation.NOT;
    }
}
