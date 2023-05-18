package semantic.element.sentence.operation.comparison;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.comparison.ComparisonOperation;

public class Lower extends ComparisonOperation {
    public Lower(ProgrammableElement context) {
        super(context);
        this.name = "LOWER";
        this.symbol = "<";
    }
}
