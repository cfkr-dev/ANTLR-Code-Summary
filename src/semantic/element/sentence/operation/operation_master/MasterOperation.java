package semantic.element.sentence.operation.operation_master;

import semantic.element_interfaces.AssignableElement;
import semantic.element_master.MasterProgramElement;

public abstract class MasterOperation extends MasterProgramElement implements AssignableElement {
    protected String expression;

    @Override
    public String getLiteralValue() {
        return this.expression;
    }
}
