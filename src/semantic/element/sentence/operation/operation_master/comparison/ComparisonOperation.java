package semantic.element.sentence.operation.operation_master.comparison;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.MasterBinaryOperation;
import semantic.utils.enums.Element;

public class ComparisonOperation extends MasterBinaryOperation {

    public ComparisonOperation(ProgrammableElement context) {
        this.type = null;
        this.firstOperand = null;
        this.secondOperand = null;
        this.elementType = Element.COMPARISON_OPERATION;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.hasParenthesis = false;
    }
}
