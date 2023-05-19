package semantic.element.variable.variable_interface;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgrammableElement;

public interface Variable<E extends AssignableElement> extends AssignableElement {
    boolean setValue(E assignableElement, ProgrammableElement context);
    String getValue();
}