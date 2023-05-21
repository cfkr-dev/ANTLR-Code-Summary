package semantic.element.sentence.operation.operation_interface;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.sentence.operation.operation_master.MasterBinaryOperation;
import semantic.utils.enums.Type;

public interface BinaryOperation extends AssignableElement {

    public Type assertType(AssignableElement firstOperand, AssignableElement secondOperand);
    MasterBinaryOperation setParenthesis();
}
