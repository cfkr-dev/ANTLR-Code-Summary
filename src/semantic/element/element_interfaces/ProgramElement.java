package semantic.element.element_interfaces;

import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

public interface ProgramElement {

    String getAnchorContext();
    Type getType();

    String getName();

    Element getElementType();

    ProgrammableElement getContext();

    ProgramElement forceChangeContext(ProgrammableElement context);

    ProgramElement forceChangeContext(ProgrammableElement context, String customAnchorContext);

    ProgrammableElement getSuperContext();

    int getLine();

    int getColumn();

    boolean isMalformed();

    void setMalformed();

    String toHTML(int HTMLIndentationLevel);

}
