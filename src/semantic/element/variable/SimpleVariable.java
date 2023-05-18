package semantic.element.variable;

import semantic.element.variable.variable_master.MasterVariable;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

public class SimpleVariable extends MasterVariable {

    private AssignableElement value;

    public SimpleVariable(String type, String name, ProgrammableElement context) {
        this.type = Type.valueOf(type.toUpperCase());
        this.elementType = Element.VARIABLE;
        this.name = name;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.value = null;
    }

    @Override
    public String getValue() {
        return this.toString();
    }

    @Override
    public boolean setValue(AssignableElement assignableElement) {
        if (!this.context.hasThisSymbol(this.name)) {
            System.err.println("Can't assign " + assignableElement.getValue() + " to " + this.name + " because " + this.name + " hasn't been previously declared");
            return false;
        }

        if (!this.context.hasThisSymbol(assignableElement.getName())) {
            System.err.println("Can't assign " + assignableElement.getValue() + " to " + this.name + " because " + assignableElement.getName() + " hasn't been previously declared");
            return false;
        }

        if (!this.type.equals(assignableElement.getType()) && !assignableElement.getType().equals(Type.ANY)) {
            System.err.println("Can't assign \"" + assignableElement.getValue() + "\" to " + this.type.name().toLowerCase() + " variable");
            return false;
        }

        this.value = assignableElement;
        this.context.updateSymbolTable(this);
        return true;
    }

    @Override
    public String toString() {
        return this.name + " = " + this.value.getValue();
    }

    @Override
    public String toHTML() {
        return null;
    }

}
