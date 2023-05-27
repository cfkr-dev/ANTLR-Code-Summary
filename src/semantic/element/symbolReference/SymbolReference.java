package semantic.element.symbolReference;

import semantic.element.Constant;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.enums.Element;

public class SymbolReference extends MasterProgramElement implements AssignableElement {

    protected boolean hasParenthesis;
    protected AssignableElement value;

    public SymbolReference(Variable variable, ProgrammableElement context, int line, int column) {
        this.value = variable;
        this.type = variable.getType();
        this.name = "SYMBOL_REF_" + variable.getName();
        this.elementType = Element.SYMBOL_REFERENCE;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.malformed = false;
        this.line = line;
        this.column = column;
        this.hasParenthesis = false;
    }

    public SymbolReference(Constant constant, ProgrammableElement context, int line, int column) {
        this.value = constant;
        this.type = constant.getType();
        this.name = "SYMBOL_REF" + constant.getName();
        this.elementType = Element.SYMBOL_REFERENCE;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.malformed = false;
        this.line = line;
        this.column = column;
        this.hasParenthesis = false;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        return null;
    }

    @Override
    public String getValue() {
        return this.value.getName();
    }

    @Override
    public AssignableElement setParenthesis() {
        this.hasParenthesis = true;
        return this;
    }
}
