package semantic.element.element_master;

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
}
