package semantic.element.variable;

import semantic.element.Constant;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.variable.variable_interface.Variable;
import semantic.element.variable.variable_master.MasterVariable;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

public class SimpleVariable extends MasterVariable {

    private AssignableElement value;

    public SimpleVariable(String type, String name, ProgrammableElement context, int line, int column) {
        this.type = Type.valueOf(type.toUpperCase());
        this.elementType = Element.VARIABLE;
        this.name = name;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = context.getAnchorContext() + ":" + this.name;
        this.value = null;
        this.malformed = false;
        this.line = line;
        this.column = column;
    }

    @Override
    public Variable variableClone() {
        Variable variable = new SimpleVariable(this.type.name(), this.name, this.context, this.line, this.column);
        if (this.malformed)
            variable.forceSetMalformed();
        return variable;
    }

    @Override
    public Variable variableClone(String name) {
        Variable variable = new SimpleVariable(this.type.name(), name, this.context, this.line, this.column);
        if (this.malformed)
            variable.forceSetMalformed();
        return variable;
    }

    @Override
    public AssignableElement getValue() {
        return this.value;
    }

    @Override
    public void forceSetValue(AssignableElement assignableElement) {
        this.value = assignableElement;
    }

    @Override
    public boolean setValue(AssignableElement assignableElement, ProgrammableElement context, int line, int column) {

        if (this.malformed) {
            this.setMalformed();
            return false;
        }

        if (assignableElement.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada");
            this.setMalformed();
            return false;
        }

        if (!context.hasThisSymbol(this.name)) {
            if (!(assignableElement.getValue() instanceof Variable || assignableElement.getValue() instanceof Constant)) {
                System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar " + assignableElement.toString() + " a " + this.name + " por que " + this.name + " no ha sido declarado previamente");
                this.setMalformed();
                return false;
            }

            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar " + assignableElement.toString() + " a " + this.name + " por que " + this.name + " no ha sido declarado previamente");
            this.setMalformed();
            return false;
        }

        if (assignableElement.getValue() instanceof Variable || assignableElement.getValue() instanceof Constant) {

            if (!context.hasThisSymbol(assignableElement.getValue().getName())) {
                System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar " + assignableElement.toString() + " a " + this.name + " por que " + assignableElement.toString() + " no ha sido declarado previamente");
                this.setMalformed();
                return false;
            }
        }

        if (!Type.checkTypeConsistency(assignableElement.getType(), this.type)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar un elemento de tipo \"" + assignableElement.getType() + "\" a una variable de tipo " + this.type.name().toLowerCase());
            this.setMalformed();
            return false;
        }

        this.value = assignableElement;
        context.updateSymbolTable(this);
        return true;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);

        StringBuilder HTMLVariable = new StringBuilder();

        if (this.value == null)
            return HTMLVariable
                .append(tabs).append(HTMLHelper.genA(this.anchorContext)).append("\n")
                .append(tabs)
                .append(HTMLHelper.genSpan("palres", this.getType().name().toLowerCase()))
                .append(" ")
                .append(HTMLHelper.genSpan("ident", this.getName()))
                .toString();
        else
            return HTMLVariable
                .append(tabs).append(HTMLHelper.genA(this.anchorContext)).append("\n")
                .append(tabs)
                .append(HTMLHelper.genSpan("palres", this.getType().name().toLowerCase()))
                .append(" ")
                .append(HTMLHelper.genSpan("ident", this.getName()))
                .append(HTMLHelper.genSpan("palres", " = "))
                .append(this.value.toHTML(HTMLIndentationLevel))
                .toString();
    }
}
