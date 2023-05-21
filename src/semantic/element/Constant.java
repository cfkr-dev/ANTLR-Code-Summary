package semantic.element;

import semantic.element.literal.literal_master.Literal;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.utils.enums.Element;

public class Constant extends MasterProgramElement implements AssignableElement {

    private Literal value;
    private boolean malformed;
    private boolean hasParenthesis;

    public Constant(String name, Literal value, ProgrammableElement context, int line, int column) {
        this.type = value.getType();
        this.elementType = Element.CONSTANT;
        this.name = name;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.value = value;
        this.malformed = false;
        this.line = line;
        this.column = column;
        this.hasParenthesis = false;
    }

    public AssignableElement getValueTarget() {
        return this.value;
    }

    @Override
    public AssignableElement setParenthesis() {
        this.hasParenthesis = true;
        return this;
    }

    @Override
    public String getValue() {
        return this.name;
    }

    @Override
    public String toHTML() {
        return "<p>#define " + this.name + " \"" + this.value.toHTML() + "\"</p>\n";
    }
}
