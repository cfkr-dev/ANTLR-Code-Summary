package semantic.element.sentence.literal.literal_master;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_master.MasterProgramElement;

public abstract class Literal extends MasterProgramElement implements AssignableElement {
    protected String value;

    @Override
    public String getValue() {
        return this.toString();
    }

    @Override
    public String toString() {
        return value;
    }

    public String toHTML() {

        return value;

    }
}
