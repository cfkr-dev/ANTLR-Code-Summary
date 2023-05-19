package semantic.element.sentence.operation.comparison;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.comparison.ComparisonOperation;
import semantic.utils.enums.Operation;

public class GreaterEqual extends ComparisonOperation {
    public GreaterEqual(ProgrammableElement context) {
        super(context);
        this.name = "GreaterEqual";
        this.symbol = ">=";
        this.operationType = Operation.GREATER_EQUAL;
    }
}
