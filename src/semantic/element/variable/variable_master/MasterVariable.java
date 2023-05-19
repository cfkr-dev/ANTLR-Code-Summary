package semantic.element.variable.variable_master;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.variable.variable_interface.Variable;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_master.MasterProgramElement;

public abstract class MasterVariable<E extends AssignableElement> extends MasterProgramElement implements Variable<E> {

    protected boolean malformed;

    @Override
    public abstract boolean setValue(E assignableElement, ProgrammableElement context);

    @Override
    public abstract String getValue();

    @Override
    public abstract String toString();

    @Override
    public boolean isMalformed() {
        return this.malformed;
    }
}