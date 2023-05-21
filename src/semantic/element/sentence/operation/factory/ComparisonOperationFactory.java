package semantic.element.sentence.operation.factory;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.comparison.*;
import semantic.element.sentence.operation.operation_master.comparison.ComparisonOperation;

public class ComparisonOperationFactory {

    ProgrammableElement context;

    public ComparisonOperationFactory(ProgrammableElement context) {
        this.context = context;
    }

    public ComparisonOperation equal(int line, int column) {
        return new Equal(this.context, line, column);
    }

    public ComparisonOperation notEqual(int line, int column) {
        return new NotEqual(this.context, line, column);
    }

    public ComparisonOperation greater(int line, int column) {
        return new Greater(this.context, line, column);
    }

    public ComparisonOperation greaterEqual(int line, int column) {
        return new GreaterEqual(this.context, line, column);
    }

    public ComparisonOperation lower(int line, int column) {
        return new Lower(this.context, line, column);
    }

    public ComparisonOperation lowerEqual(int line, int column) {
        return new LowerEqual(this.context, line, column);
    }
}
