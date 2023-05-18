package semantic.element.sentence.operation.operation_master.logical;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.MasterBinaryOperation;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

public class BinaryLogicalOperation extends MasterBinaryOperation {

    public BinaryLogicalOperation(ProgrammableElement context) {
        this.type = null;
        this.firstOperand = null;
        this.secondOperand = null;
        this.elementType = Element.LOGICAL_OPERATION;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.malformedFlag = false;
    }

    public Type assertType(AssignableElement firstOperand, AssignableElement secondOperand) {
        if (this.malformedFlag)
            return null;

        if (firstOperand.getType().equals(Type.ANY) && secondOperand.getType().equals(Type.ANY))
            return Type.INTEGER;

        if (firstOperand.getType().equals(Type.INTEGER) && secondOperand.getType().equals(Type.ANY))
            return Type.INTEGER;

        if (secondOperand.getType().equals(Type.INTEGER) && firstOperand.getType().equals(Type.ANY))
            return Type.INTEGER;

        this.malformedFlag = true;
        System.err.println("En las expresiones lógicas es necesario operar con tipos INTEGER");
        return null;
    }
}
