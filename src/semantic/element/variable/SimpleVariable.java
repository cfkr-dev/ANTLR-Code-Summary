package semantic.element.variable;

import semantic.element.Constant;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.variable.variable_interface.Variable;
import semantic.element.variable.variable_master.MasterVariable;
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
        this.malformed = false;
    }

    @Override
    public String getValue() {
        return this.toString();
    }

    @Override
    public boolean setValue(AssignableElement assignableElement, ProgrammableElement context) {

        if (this.malformed)
            return false;

        if (assignableElement.isMalformed()) {
            System.err.println("No se puede asignar una expresión malformada");
            this.malformed = true;
            return false;
        }

        if (!context.hasThisSymbol(this.name)) {
            if (!(assignableElement instanceof Variable<?> || assignableElement instanceof Constant)) {
                System.err.println("No se puede asignar " + assignableElement.getValue() + " a " + this.name + " por que " + this.name + " no ha sido declarado previamente");
                return false;
            }

            System.err.println("No se puede asignar " + assignableElement.getName() + " a " + this.name + " por que " + this.name + " no ha sido declarado previamente");
            return false;
        }

        if (assignableElement instanceof Variable<?> || assignableElement instanceof Constant) {

            if (!context.hasThisSymbol(assignableElement.getName())) {
                System.err.println("No se puede asignar " + assignableElement.getValue() + " a " + this.name + " por que " + assignableElement.getName() + " no ha sido declarado previamente");
                return false;
            }
        }

        if (!Type.checkTypeConsistency(assignableElement.getType(), this.type)) {
            System.err.println("No se puede asignar un elemento de tipo \"" + assignableElement.getType() + "\" a una variable de tipo " + this.type.name().toLowerCase());
            return false;
        }

        this.value = assignableElement;
        context.updateSymbolTable(this);
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
