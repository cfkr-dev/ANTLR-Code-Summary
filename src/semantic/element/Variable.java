package semantic.element;

import semantic.element_interfaces.AssignableElement;
import semantic.element_interfaces.ProgrammableElement;
import semantic.element_interfaces.StructableElement;
import semantic.element_master.MasterProgramElement;
import semantic.enums.Element;
import semantic.enums.Type;

public class Variable extends MasterProgramElement implements AssignableElement, StructableElement {

    private AssignableElement value;

    public Variable(String type, String name, ProgrammableElement context) {
        this.type = Type.valueOf(type.toUpperCase());
        this.elementType = Element.VARIABLE;
        this.name = name;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.value = null;
    }

    public AssignableElement getValueTarget() {
        return this.value;
    }

    @Override
    public String getLiteralValue() {
        return this.value.getLiteralValue();
    }

    public boolean setValue(AssignableElement assignableElement) {
        if (!this.context.hasThisSymbol(this.name)) {
            System.err.println("Can't assign " + assignableElement.getLiteralValue() + " to " + this.name + " because " + this.name + " hasn't been previously declared");
            return false;
        }

        if (!this.context.hasThisSymbol(assignableElement.getName())) {
            System.err.println("Can't assign " + assignableElement.getLiteralValue() + " to " + this.name + " because " + assignableElement.getName() + " hasn't been previously declared");
            return false;
        }

        if (!this.type.equals(assignableElement.getType()) && !assignableElement.getType().equals(Type.ANY)) {
            System.err.println("Can't assign \"" + assignableElement.getLiteralValue() + "\" to " + this.type.name().toLowerCase() + " variable");
            return false;
        }

        this.value = assignableElement;
        this.context.updateSymbolTable(this);
        return true;
    }

    @Override
    public String toHTML() {
        return null;
    }


}
