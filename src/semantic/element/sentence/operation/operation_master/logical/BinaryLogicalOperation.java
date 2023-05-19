package semantic.element.sentence.operation.operation_master.logical;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.MasterBinaryOperation;
import semantic.utils.enums.Element;

public class BinaryLogicalOperation extends MasterBinaryOperation {

    public BinaryLogicalOperation(ProgrammableElement context) {
        this.type = null;
        this.firstOperand = null;
        this.secondOperand = null;
        this.elementType = Element.LOGICAL_OPERATION;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.hasParenthesis = false;
    }
}
