package semantic.element.variable.variable_interface;

import semantic.element.element_interfaces.AssignableElement;

public interface Variable<E extends AssignableElement> extends AssignableElement {
    boolean setValue(E assignableElement);
    String getValue();
}
