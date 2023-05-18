package semantic.element.sentence.operation.comparison;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.comparison.ComparisonOperation;

public class LowerEqual extends ComparisonOperation {
    public LowerEqual(ProgrammableElement context) {
        super(context);
        this.name = "LowerEqual";
        this.symbol = "<=";
    }
}
