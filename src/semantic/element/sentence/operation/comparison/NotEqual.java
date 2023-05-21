package semantic.element.sentence.operation.comparison;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.comparison.ComparisonOperation;
import semantic.utils.enums.Operation;

public class NotEqual extends ComparisonOperation {
    public NotEqual(ProgrammableElement context, int line, int column) {
        super(context, line, column);
        this.name = "NotEqual";
        this.symbol = "!=";
        this.operationType = Operation.NOT_EQUAL;
    }
}
