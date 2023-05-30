package semantic.element.sentence.operation.operation_master.comparison;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.MasterBinaryOperation;
import semantic.utils.enums.Element;

public class ComparisonOperation extends MasterBinaryOperation {

    public ComparisonOperation(ProgrammableElement context, int line, int column) {
        this.type = null;
        this.firstOperand = null;
        this.secondOperand = null;
        this.elementType = Element.COMPARISON_OPERATION;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = context.getAnchorContext() + ":" + this.name;
        this.malformed = false;
        this.hasParenthesis = false;
        this.line = line;
        this.column = column;
    }
}
