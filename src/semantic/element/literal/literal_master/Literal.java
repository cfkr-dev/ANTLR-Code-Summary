package semantic.element.literal.literal_master;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_master.MasterProgramElement;

public abstract class Literal extends MasterProgramElement implements AssignableElement {
    protected String value;
    protected boolean hasParenthesis;

    @Override
    public String getValue() {
        return this.toString();
    }

    @Override
    public String toString() {
        return value;
    }

    public String toHTML(int indentationLevel) {

        if (this.hasParenthesis)
            return "<SPAN CLASS=\"cte\">" + "(" + value + ")" + "</SPAN>";
        else
            return "<SPAN CLASS=\"cte\">" + value + "</SPAN>";
    }

    @Override
    public AssignableElement setParenthesis() {
        this.hasParenthesis = true;
        return this;
    }
}
