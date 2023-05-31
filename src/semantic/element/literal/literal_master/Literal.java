package semantic.element.literal.literal_master;

import semantic.HTMLToolKit;
import semantic.element.Constant;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.element.literal.NumericIntegerConstant;

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

        return HTMLToolKit.cteMaker(value);

    }
    @Override
    public String toHTMLLong() {
        return this.toHTML();
    }
}
