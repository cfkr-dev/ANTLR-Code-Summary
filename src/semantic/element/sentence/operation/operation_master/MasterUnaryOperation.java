package semantic.element.sentence.operation.operation_master;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.element.sentence.operation.operation_interface.UnaryOperation;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

public abstract class MasterUnaryOperation extends MasterProgramElement implements UnaryOperation {
    protected AssignableElement firstOperand;
    protected String symbol;

    @Override
    public String getValue() {
        return this.toString();
    }

    @Override
    public String toString() {
        String firstOpStr;

        if (this.firstOperand.getElementType().equals(Element.VARIABLE) || this.firstOperand.getElementType().equals(Element.CONSTANT))
            firstOpStr = firstOperand.getName();
        else firstOpStr = firstOperand.getValue();

        return symbol + firstOpStr;
    }

    @Override
    public String toHTML() {
        return null;
    }

    public MasterUnaryOperation firstOperand(AssignableElement firstOperand) {
        this.firstOperand = firstOperand;
        if (this.firstOperand != null)
            this.type = this.assertType(firstOperand);
        return this;
    }

    public Type assertType(AssignableElement firstOperand) {
        if (firstOperand.getType().equals(Type.INTEGER))
            return firstOperand.getType();
        else if (firstOperand.getType().equals(Type.ANY))
            return Type.ANY;
        else {
            System.err.println("Los tipos no son iguales");
            return Type.ANY;
        }
    }
}
