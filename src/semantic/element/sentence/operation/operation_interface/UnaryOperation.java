package semantic.element.sentence.operation.operation_interface;

import semantic.element.element_interfaces.AssignableElement;
import semantic.utils.enums.Type;

public interface UnaryOperation extends AssignableElement {

    public Type assertType(AssignableElement firstOperand);
    public boolean isMalformed();
}
