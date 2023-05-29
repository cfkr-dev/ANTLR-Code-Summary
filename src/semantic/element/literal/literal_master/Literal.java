package semantic.element.literal.literal_master;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_master.MasterProgramElement;

public abstract class Literal extends MasterProgramElement implements AssignableElement {
    protected String value;
    protected boolean hasParenthesis;

    public AssignableElement getValue() {
        return this;
    }

    @Override
    public String toString() {
        return value;
    }

    public String toHTML(int HTMLIndentationLevel, String anchorContext) {
        StringBuilder HTMLLiteral = new StringBuilder();

        HTMLLiteral.append("<span class=\"cte\">");

        if (this.hasParenthesis)
            HTMLLiteral.append("(").append(this.value).append(")");
        else
            HTMLLiteral.append(this.value);

        HTMLLiteral.append("</span>");

        return HTMLLiteral.toString();
    }

    @Override
    public AssignableElement setParenthesis() {
        this.hasParenthesis = true;
        return this;
    }
}
