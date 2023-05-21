package semantic.element.element_interfaces;

import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

public interface ProgramElement {

    public Type getType();

    public String getName();

    public Element getElementType();

    public ProgrammableElement getContext();

    public ProgrammableElement getSuperContext();

    public int getLine();
    public int getColumn();

    public String toHTML();

    public boolean isMalformed();

    public void setMalformed();
}
