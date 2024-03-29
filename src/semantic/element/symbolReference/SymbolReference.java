package semantic.element.symbolReference;

import semantic.element.Constant;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;

public class SymbolReference extends MasterProgramElement implements AssignableElement {

    protected boolean hasParenthesis;
    protected ProgramElement value;

    public SymbolReference(Variable variable, ProgrammableElement context, int line, int column) {
        this.value = variable;
        this.type = variable.getType();
        this.name = "SYMBOL_REF_" + line + "_" + column;
        this.elementType = Element.SYMBOL_REFERENCE;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = value.getAnchorContext();
        this.malformed = false;
        this.line = line;
        this.column = column;
        this.hasParenthesis = false;
    }

    public SymbolReference(Constant constant, ProgrammableElement context, int line, int column) {
        this.value = constant;
        this.type = constant.getType();
        this.name = "SYMBOL_REF_" + line + "_" + column;
        this.elementType = Element.SYMBOL_REFERENCE;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = value.getAnchorContext();
        this.malformed = false;
        this.line = line;
        this.column = column;
        this.hasParenthesis = false;
    }

    @Override
    public String toString() {
        if (this.hasParenthesis)
            return "(" + value.getName() + ")";
        else
            return value.getName();
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        if (this.hasParenthesis)
            return HTMLHelper.genAHref(this.anchorContext, HTMLHelper.genSpan("ident", "(" + value.getName() + ")"));
        else
            return HTMLHelper.genAHref(this.anchorContext, HTMLHelper.genSpan("ident", value.getName()));
    }

    @Override
    public ProgramElement getValue() {
        return this.value;
    }

    @Override
    public AssignableElement setParenthesis() {
        this.hasParenthesis = true;
        return this;
    }
}
