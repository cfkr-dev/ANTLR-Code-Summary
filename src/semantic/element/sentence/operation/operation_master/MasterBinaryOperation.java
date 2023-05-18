package semantic.element.sentence.operation.operation_master;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.element.sentence.operation.operation_interface.BinaryOperation;
import semantic.utils.enums.Element;

public abstract class MasterBinaryOperation extends MasterProgramElement implements BinaryOperation {
    protected AssignableElement firstOperand;
    protected AssignableElement secondOperand;
    protected String symbol;
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
        String firstOpStr, secondOpStr;
        
        if (this.firstOperand.getElementType().equals(Element.VARIABLE) || this.firstOperand.getElementType().equals(Element.CONSTANT))
             firstOpStr = firstOperand.getName();
        else firstOpStr = firstOperand.getValue();

        if (this.secondOperand.getElementType().equals(Element.VARIABLE) || this.secondOperand.getElementType().equals(Element.CONSTANT))
            secondOpStr = secondOperand.getName();
        else secondOpStr = secondOperand.getValue();
        
        return firstOpStr + " " + symbol + " " + secondOpStr;
    }

    @Override
    public String toHTML() {
        return null;
    }

    public MasterBinaryOperation firstOperand(AssignableElement firstOperand) {
        this.firstOperand = firstOperand;
        if (this.firstOperand != null && this.secondOperand != null)
            this.type = this.assertType(firstOperand, secondOperand);
        return this;
    }

    public MasterBinaryOperation secondOperand(AssignableElement secondOperand) {
        this.secondOperand = secondOperand;
        if (this.firstOperand != null && this.secondOperand != null)
            this.type = this.assertType(firstOperand, secondOperand);
        return this;
    }

}
