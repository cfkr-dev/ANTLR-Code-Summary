package semantic.element;

import semantic.element.sentence.sentence_interface.AssignableElement;
import semantic.element_interfaces.ProgrammableElement;
import semantic.element_master.MasterProgramElement;
import semantic.enums.Element;
import semantic.enums.Type;

public class Variable extends MasterProgramElement implements AssignableElement {

    private AssignableElement value;

    public Variable(String type, String name, ProgrammableElement context) {
        this.type = Type.valueOf(type.toUpperCase());
        this.elementType = Element.VARIABLE;
        this.name = name;
        this.context = context;
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

        if (assignableElement.getElementType().equals(Element.VARIABLE) || assignableElement.getElementType().equals(Element.FUNCTION)) {
            if (!this.context.hasThisSymbol(assignableElement.getName())) {
                System.err.println("Can't assign " + assignableElement.getLiteralValue() + " to " + this.name + " because " + assignableElement.getName() + " hasn't been previously declared");
                return false;
            }
        }

        if (!this.type.equals(assignableElement.getType())) {
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
