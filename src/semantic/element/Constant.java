package semantic.element;

import semantic.element.literal.literal_master.Literal;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

public class Constant extends MasterProgramElement {

    private final Literal value;
    private final boolean malformed;

    public Constant(String name, Literal value, ProgrammableElement context, int line, int column) {
        this.type = value.getType();
        this.elementType = Element.CONSTANT;
        this.name = name;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = context.getAnchorContext() + ":" + this.name;
        this.value = value;
        this.malformed = false;
        this.line = line;
        this.column = column;
    }

    public AssignableElement getValueTarget() {
        return this.value;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);

        StringBuilder HTMLConstant = new StringBuilder();

        HTMLConstant
            .append(tabs).append(HTMLHelper.genA(this.anchorContext)).append("\n")
            .append(tabs)
            .append(HTMLHelper.genSpan("palres", "#define"))
            .append(" ")
            .append(HTMLHelper.genSpan("ident", this.getName()))
            .append(" ");

        HTMLConstant
            .append(this.value.toHTML(HTMLIndentationLevel));

        if (!this.value.getType().equals(Type.STRING))
            HTMLConstant.insert(HTMLConstant.lastIndexOf("cte\">") + 5, "\"");

        if (!this.value.getType().equals(Type.STRING))
            HTMLConstant.insert(HTMLConstant.lastIndexOf("</span>"), "\"");

        return HTMLConstant.toString();
    }
}
