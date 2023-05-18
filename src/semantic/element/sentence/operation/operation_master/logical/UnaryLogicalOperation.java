package semantic.element.sentence.operation.operation_master.logical;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.MasterUnaryOperation;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

public class UnaryLogicalOperation extends MasterUnaryOperation {

    public UnaryLogicalOperation(ProgrammableElement context) {
        this.type = null;
        this.firstOperand = null;
        this.elementType = Element.LOGICAL_OPERATION;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.malformedFlag = false;
    }

    public Type assertType(AssignableElement firstOperand) {
        if (this.malformedFlag)
            return null;

        if (firstOperand.getType().equals(Type.ANY))
            return Type.INTEGER;

        if (firstOperand.getType().equals(Type.INTEGER))
            return Type.INTEGER;

        this.malformedFlag = true;
        System.err.println("En las expresiones lógicas es necesario operar con tipos INTEGER");
        return null;
    }
}
