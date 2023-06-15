package semantic.element.sentence.operation.operation_interface;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.sentence.operation.operation_master.MasterUnaryOperation;
import semantic.utils.enums.Type;

public interface UnaryOperation extends AssignableElement {

    Type assertType(AssignableElement firstOperand);
    MasterUnaryOperation setParenthesis();
}
