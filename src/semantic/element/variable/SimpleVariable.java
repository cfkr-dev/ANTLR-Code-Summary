package semantic.element.variable;

import semantic.HTMLToolKit;
import semantic.element.Constant;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.variable.variable_interface.Variable;
import semantic.element.variable.variable_master.MasterVariable;
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
        this.value = null;
        this.malformed = false;
        this.line = line;
        this.column = column;
    }



    @Override
    public String getValue() {
        return this.name;
    }

//    @Override
//    public AssignableElement getVariableValue() {
//        return this.value;
//    }

    @Override
    public void forceSetValue(AssignableElement assignableElement) {
        this.value = assignableElement;
    }

    @Override
    public Variable variableClone() {
        Variable variable = new SimpleVariable(this.type.name(), this.name, this.context, this.line, this.column);
        if (this.malformed)
            variable.forceSetMalformed();
        return variable;
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
            if (!(assignableElement instanceof Variable<?> || assignableElement instanceof Constant)) {
                System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar " + assignableElement.getValue() + " a " + this.name + " por que " + this.name + " no ha sido declarado previamente");
                this.setMalformed();
                return false;
            }

            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar " + assignableElement.getName() + " a " + this.name + " por que " + this.name + " no ha sido declarado previamente");
            this.setMalformed();
            return false;
        }

        if (assignableElement instanceof Variable<?> || assignableElement instanceof Constant) {

            if (!context.hasThisSymbol(assignableElement.getName())) {
                System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar " + assignableElement.getValue() + " a " + this.name + " por que " + assignableElement.getName() + " no ha sido declarado previamente");
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
    public String toString() {
        if (this.value == null)
            return this.name;
        else
            return this.name + " = " + this.value.getValue();
    }

    public String toHTML () {

        return HTMLToolKit.identRefMaker(this.toHTMLContex(), this.name);

    }

    public String toHTMLLong() {

        if (this.value == null)
            return HTMLToolKit.palresMaker(this.type.toString()) + " " + HTMLToolKit.identRefGenerator(this.toHTMLContex(), this.name) + ";<br>\n";
        else
            return HTMLToolKit.palresMaker(this.type.toString()) + " " + HTMLToolKit.identRefGenerator(this.toHTMLContex(), this.name) + " = " + this.value.toHTML() + ";<br>\n";

    }

    public String toHTMLValue() {

        return this.value.toHTML();

    }

}
