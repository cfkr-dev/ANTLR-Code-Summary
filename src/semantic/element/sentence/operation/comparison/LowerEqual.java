package semantic.element.sentence.operation.comparison;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.comparison.ComparisonOperation;
import semantic.utils.enums.Operation;

public class LowerEqual extends ComparisonOperation {
    public LowerEqual(ProgrammableElement context, int line, int column) {
        super(context, line, column);
        this.name = "LowerEqual";
        this.symbol = "<=";
        this.operationType = Operation.LOWER_EQUAL;

    }
}
