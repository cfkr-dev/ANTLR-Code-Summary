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

    public UnaryLogicalOperation not() {
        return new Not(this.context);
    }
    public BinaryLogicalOperation and() {
        return new And(this.context);
    }
    public BinaryLogicalOperation or() {
        return new Or(this.context);
    }
}
