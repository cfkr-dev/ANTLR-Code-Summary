package semantic.element;

import semantic.element.literal.literal_master.Literal;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

public class Constant extends MasterProgramElement {

    private Literal value;
    private boolean malformed;

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
    }

    public AssignableElement getValueTarget() {
        return this.value;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel, String anchorContext) {
        String tabs = HTMLHelper.generateTabulations(HTMLIndentationLevel);

        StringBuilder HTMLConstant = new StringBuilder();

        HTMLConstant
            .append(tabs)
            .append("<span class=\"palres\">").append("#define").append("</span>")
            .append(" ")
            .append("<span class=\"ident\">").append(this.getName()).append("</span>")
            .append(" ");

        //</span>

        HTMLConstant.append(this.value.toHTML(HTMLIndentationLevel, anchorContext));

        if (!this.value.getType().equals(Type.STRING))
            HTMLConstant.insert(HTMLConstant.lastIndexOf("cte\">") + 5, "\"");

        if (!this.value.getType().equals(Type.STRING))
            HTMLConstant.insert(HTMLConstant.lastIndexOf("</span>"), "\"");

        return HTMLConstant.toString();
    }
}
