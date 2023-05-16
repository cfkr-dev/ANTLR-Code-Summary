package semantic.element_interfaces;

import semantic.enums.Element;
import semantic.enums.Type;

public interface ProgramElement {

    public Type getType();

    public String getName();

    public Element getElementType();

    public ProgrammableElement getContext();

    public ProgrammableElement getSuperContext();

    public String toHTML();
}
