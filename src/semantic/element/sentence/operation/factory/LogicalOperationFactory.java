package semantic.element.sentence.operation.factory;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.logical.And;
import semantic.element.sentence.operation.logical.Not;
import semantic.element.sentence.operation.logical.Or;
import semantic.element.sentence.operation.operation_master.logical.BinaryLogicalOperation;
import semantic.element.sentence.operation.operation_master.logical.UnaryLogicalOperation;

public class LogicalOperationFactory {

    ProgrammableElement context;

    public LogicalOperationFactory(ProgrammableElement context) {
        this.context = context;
    }

    public UnaryLogicalOperation not(int line, int column) {
        return new Not(this.context, line, column);
    }
    public BinaryLogicalOperation and(int line, int column) {
        return new And(this.context, line, column);
    }
    public BinaryLogicalOperation or(int line, int column) {
        return new Or(this.context, line, column);
    }
}
