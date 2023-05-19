package semantic.element;

import semantic.element.literal.literal_master.Literal;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.utils.enums.Element;

public class Constant extends MasterProgramElement implements AssignableElement {

    private Literal value;
    private boolean malformed;

    public Constant(String name, Literal value, ProgrammableElement context) {
        this.type = value.getType();
        this.elementType = Element.CONSTANT;
        this.name = name;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.value = value;
        this.malformed = false;
    }

    public AssignableElement getValueTarget() {
        return this.value;
    }

    @Override
    public String getValue() {
        return this.value.getValue();
    }

    @Override
    public String toHTML() {
        return null;
    }


}
