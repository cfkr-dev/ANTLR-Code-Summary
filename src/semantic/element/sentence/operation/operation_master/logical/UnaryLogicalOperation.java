package semantic.element.sentence.operation.operation_master.logical;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.MasterUnaryOperation;
import semantic.utils.enums.Element;

public class UnaryLogicalOperation extends MasterUnaryOperation {

    public UnaryLogicalOperation(ProgrammableElement context, int line, int column) {
        this.type = null;
        this.firstOperand = null;
        this.elementType = Element.LOGICAL_OPERATION;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.malformed = false;
        this.hasParenthesis = false;
        this.line = line;
        this.column = column;
    }
}
