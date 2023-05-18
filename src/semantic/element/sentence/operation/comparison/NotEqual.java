package semantic.element.sentence.operation.comparison;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.comparison.ComparisonOperation;

public class NotEqual extends ComparisonOperation {
    public NotEqual(ProgrammableElement context) {
        super(context);
        this.name = "NotEqual";
        this.symbol = "!=";
    }
}
