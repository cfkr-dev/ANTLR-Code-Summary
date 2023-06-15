package semantic.element.literal.literal_master;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.utils.HTMLHelper;

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

    public String toHTML(int HTMLIndentationLevel) {
        if (this.hasParenthesis)
            return HTMLHelper.genSpan("cte", "(" + this.value + ")");
        else
            return HTMLHelper.genSpan("cte", this.value);
    }

    @Override
    public AssignableElement setParenthesis() {
        this.hasParenthesis = true;
        return this;
    }
}
