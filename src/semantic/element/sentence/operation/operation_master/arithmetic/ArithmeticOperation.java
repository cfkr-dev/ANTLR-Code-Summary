package semantic.element.sentence.operation.operation_master.arithmetic;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.MasterBinaryOperation;
import semantic.utils.enums.Element;

public class ArithmeticOperation extends MasterBinaryOperation {

    public ArithmeticOperation(ProgrammableElement context) {
        this.type = null;
        this.firstOperand = null;
        this.secondOperand = null;
        this.elementType = Element.ARITHMETIC_OPERATION;
        this.context = context;
        this.superContext = context.getSuperContext();
    }
}
