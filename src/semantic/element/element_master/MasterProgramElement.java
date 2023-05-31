package semantic.element.element_master;

import semantic.element.variable.StructVariable;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;

public abstract class MasterProgramElement implements ProgramElement {

    protected Type type;
    protected String name;
    protected Element elementType;
    protected ProgrammableElement context;
    protected ProgrammableElement superContext;
    protected int line;
    protected int column;
    protected boolean malformed;

    @Override
    public Type getType() {
        return this.type;
    }


    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Element getElementType() {
        return this.elementType;
    }

    @Override
    public ProgrammableElement getContext() {
        return this.context;
    }

    @Override
    public ProgrammableElement getSuperContext() {
        return this.superContext;
    }
    @Override
    public int getLine(){
        return this.line;
    }
    @Override
    public int getColumn() {
        return this.column;
    }

    @Override
    public boolean isMalformed() {
        return this.malformed;
    }

    @Override
    public void setMalformed() {
        this.malformed = true;
        if (this.context != null)
            this.context.setMalformed();
    }

    @Override
    public abstract String toHTML();

    @Override
    public abstract String toHTMLLong();

    @Override
    public String toHTMLContex () {

        if (this.context.getContext()== null) { // El siguiente es el programa, no necesito llegar tan lejos

            if (this.getElementType() == Element.FUNCTION) { // In a function

                if (this.getName() == "Main") { // In main

                    return "PROGRAMA_PRINCIPAL:" + this.getName();

                } else { // In any other fucntion

                    return "FUNCIONES:" + this.getName();

                }

            }else { // main part

                return "PROGRAMA_PRINCIPAL:" + this.getName();

            }

        } else {

            return this.context.toHTMLContex() + ":" + this.name + "(" + this.getLine() + "," + this.getColumn() + ")";

        }

    };


}
