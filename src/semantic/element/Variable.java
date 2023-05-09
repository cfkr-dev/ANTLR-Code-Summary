package semantic.element;

import semantic.enums.Element;
import semantic.enums.Type;
import semantic.element_interfaces.ProgrammableElement;
import semantic.element_master.MasterProgramElement;
import semantic.utils.Constants;

public class Variable extends MasterProgramElement {

    private String value;

    public Variable(String type, String name, ProgrammableElement context) {
        this.type = Type.valueOf(type.toUpperCase());
        this.elementType = Element.VARIABLE;
        this.name = name;
        this.context = context;
        this.value = null;
    }

    public void setValue(String value, String tokenType) {
        if (!this.context.hasThisSymbol(this.name)) {
            System.err.println("Can't assign " + value + " to " + this.name + " because it hasn't been previously declared");
            return;
        }

        if (!(Constants.checkTypeTokenType(this.type, tokenType))) {
            System.err.println("Can't assign \"" + value + "\" to " + this.type.name().toLowerCase() + " variable");
            return;
        }

        this.value = value;
        this.context.updateSymbolTable(this);
    }

    public void setValue(Variable variable) {
        if (!this.context.hasThisSymbol(this.name)) {
            System.err.println("Can't assign " + variable.name + " value to " + this.name + " because it hasn't been previously declared");
            return;
        }

        if (!this.type.equals(variable.type)) {
            System.err.println("Can't assign " + variable.name + " value (" + variable.type.name().toLowerCase() + ") to " + this.name + " (" + this.type.name().toLowerCase() + ") because types doesn't match");
            return;
        }

        this.value = variable.value;
        this.context.updateSymbolTable(this);
    }

    @Override
    public String toHTML() {
        return null;
    }
}
