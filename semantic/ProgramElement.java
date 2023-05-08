import enums.Element;

import java.util.Map;

public interface ProgramElement {

    public String toHTML();

    public String getName();

    public Element getElementType();
}
