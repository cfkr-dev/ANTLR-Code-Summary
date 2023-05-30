package semantic.element.variable.variable_interface;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;

public interface Variable extends ProgramElement {
    boolean setValue(AssignableElement assignableElement, ProgrammableElement context, int line, int column);
    AssignableElement getValue();
    public void forceSetValue(AssignableElement assignableElement);
    public Variable variableClone();
    public Variable variableClone(String name);
    public void forceSetMalformed();
}
