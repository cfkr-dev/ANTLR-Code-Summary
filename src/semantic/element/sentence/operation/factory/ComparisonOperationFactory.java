package semantic.element.sentence.operation.factory;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.comparison.*;
import semantic.element.sentence.operation.operation_master.comparison.ComparisonOperation;

public class ComparisonOperationFactory {

    ProgrammableElement context;

    public ComparisonOperationFactory(ProgrammableElement context) {
        this.context = context;
    }

    public ComparisonOperation equal() {
        return new Equal(this.context);
    }

    public ComparisonOperation notEqual() {
        return new NotEqual(this.context);
    }

    public ComparisonOperation greater() {
        return new Greater(this.context);
    }

    public ComparisonOperation greaterEqual() {
        return new GreaterEqual(this.context);
    }

    public ComparisonOperation lower() {
        return new Lower(this.context);
    }

    public ComparisonOperation lowerThan() {
        return new LowerEqual(this.context);
    }
}
