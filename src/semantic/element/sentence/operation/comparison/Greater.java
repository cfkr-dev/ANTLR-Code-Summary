package semantic.element.sentence.operation.comparison;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.comparison.ComparisonOperation;

public class Greater extends ComparisonOperation {
    public Greater(ProgrammableElement context) {
        super(context);
        this.name = "GREATER";
        this.symbol = ">";
    }
}
