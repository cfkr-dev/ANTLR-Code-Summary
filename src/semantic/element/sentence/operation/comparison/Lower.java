package semantic.element.sentence.operation.comparison;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.comparison.ComparisonOperation;
import semantic.utils.enums.Operation;

public class Lower extends ComparisonOperation {
    public Lower(ProgrammableElement context, int line, int column) {
        super(context, line, column);
        this.name = "LOWER_" + line + "_" + column;
        this.symbol = "<";
        this.operationType = Operation.LOWER;

    }
}
