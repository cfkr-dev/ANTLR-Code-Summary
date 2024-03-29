package semantic.element.variable.variable_interface;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;

public interface Variable extends ProgramElement {
    boolean setValue(AssignableElement assignableElement, ProgrammableElement context, int line, int column);
    AssignableElement getValue();
    void forceSetValue(AssignableElement assignableElement);
    Variable variableClone();
    Variable variableClone(String name);
    void forceSetMalformed();
}
