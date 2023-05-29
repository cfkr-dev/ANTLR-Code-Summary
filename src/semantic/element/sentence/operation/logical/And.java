package semantic.element.sentence.operation.logical;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.logical.BinaryLogicalOperation;
import semantic.utils.enums.Operation;

public class And extends BinaryLogicalOperation {
    public And(ProgrammableElement context, int line, int column) {
        super(context, line, column);
        this.name = "AND_" + line + "_" + column;
        this.symbol = "&";
        this.operationType = Operation.AND;

    }
}