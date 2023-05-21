package semantic.element.sentence.operation.comparison;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.comparison.ComparisonOperation;
import semantic.utils.enums.Operation;

public class Equal extends ComparisonOperation {
    public Equal(ProgrammableElement context, int line, int column) {
        super(context, line, column);
        this.name = "EQUAL";
        this.symbol = "==";
        this.operationType = Operation.EQUAL;
    }
}
