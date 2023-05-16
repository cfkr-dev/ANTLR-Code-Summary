package semantic.element_master;

import semantic.enums.Element;
import semantic.enums.Type;
import semantic.element_interfaces.ProgramElement;
import semantic.element_interfaces.ProgrammableElement;

public abstract class MasterProgramElement implements ProgramElement {

    protected Type type;
    protected String name;
    protected Element elementType;
    protected ProgrammableElement context;
    protected ProgrammableElement superContext;

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
    public abstract String toHTML();
}
