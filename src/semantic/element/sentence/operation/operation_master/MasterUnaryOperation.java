package semantic.element.sentence.operation.operation_master;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.element.sentence.operation.operation_interface.UnaryOperation;
import semantic.utils.enums.Element;
import semantic.utils.enums.Operation;
import semantic.utils.enums.Type;

public abstract class MasterUnaryOperation extends MasterProgramElement implements UnaryOperation {
    protected AssignableElement firstOperand;
    protected String symbol;
    protected Operation operationType;
    protected boolean malformedFlag;

    public boolean isMalformed(){
        return this.malformedFlag;
    }

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
        if (this.malformedFlag)
            return null;

        if (firstOperand.isMalformed()) {
            System.err.println("No es posible operar con una expresión malformada (" + firstOperand.getValue() + ")");
            this.malformedFlag = true;
            return null;
        }

        if (!Type.checkTypeOperationRules(firstOperand.getType(), this.operationType)){
            errorHelper(firstOperand);
            return null;
        }

        return firstOperand.getType();
    }

    private void errorHelper(AssignableElement operand) {
        this.malformedFlag = true;
        System.err.println(operand.getName() + " (" + operand.getType() + ") " + "no puede formar parte de esta operación (" + Operation.getOperationName(this.operationType) + ")");
    }
}
