package semantic.element.sentence.operation.operation_master;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.element.sentence.operation.operation_interface.BinaryOperation;
import semantic.utils.enums.Element;
import semantic.utils.enums.Operation;
import semantic.utils.enums.Type;

public abstract class MasterBinaryOperation extends MasterProgramElement implements BinaryOperation {
    protected AssignableElement firstOperand;
    protected AssignableElement secondOperand;
    protected String symbol;
    protected Operation operationType;
    protected Boolean hasParenthesis;

    public String getValue() {
        return this.toString();
    }

    @Override
    public String toString() {
        if (this.hasParenthesis)
            return "(" + firstOperand.getValue() + " " + symbol + " " + secondOperand.getValue() + ")";
        else
            return firstOperand.getValue() + " " + symbol + " " + secondOperand.getValue();
    }

    @Override
    public MasterBinaryOperation setParenthesis() {
        this.hasParenthesis = true;
        return this;
    }

    public MasterBinaryOperation setParenthesis(Boolean hasParenthesis) {
        this.hasParenthesis = hasParenthesis;
        return this;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {

        String HTMLOp = new String();

        HTMLOp = this.firstOperand.toHTML() + " " + this.symbol + " " + this.secondOperand.toHTML();

        if (this.hasParenthesis)
            return "(" + HTMLOp + ")";
        else
            return HTMLOp;

    }

    public MasterBinaryOperation firstOperand(AssignableElement firstOperand) {
        if (firstOperand.isMalformed())
            this.setMalformed();
        this.firstOperand = firstOperand;
        if (this.secondOperand != null)
            this.type = this.assertType(firstOperand, secondOperand);
        return this;
    }

    public MasterBinaryOperation secondOperand(AssignableElement secondOperand) {
        if (secondOperand.isMalformed())
            this.setMalformed();
        this.secondOperand = secondOperand;
        if (this.firstOperand != null)
            this.type = this.assertType(firstOperand, secondOperand);
        return this;
    }

    public Type assertType(AssignableElement firstOperand, AssignableElement secondOperand) {
        if (this.malformed){
            this.setMalformed();
            return null;
        }

        if (firstOperand.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No es posible operar con una expresión malformada (" + firstOperand.getValue() + ")");
            this.setMalformed();
            return null;
        }

        if (secondOperand.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No es posible operar con una expresión malformada (" + secondOperand.getValue() + ")");
            this.setMalformed();
            return null;
        }

        if (!Type.checkTypeOperationRules(firstOperand.getType(), this.operationType)){
            errorHelper(firstOperand);
            return null;
        }

        if (!Type.checkTypeOperationRules(secondOperand.getType(), this.operationType)){
            errorHelper(secondOperand);
            return null;
        }

        if (!Type.checkTypeConsistency(firstOperand.getType(), secondOperand.getType())) {
            errorHelper(firstOperand, secondOperand);
        }

        if (this.elementType.equals(Element.LOGICAL_OPERATION) || this.elementType.equals(Element.COMPARISON_OPERATION))
            return Type.INTEGER;
        else
            return Type.typeConversion(firstOperand.getType(), secondOperand.getType());
    }

    private void errorHelper(AssignableElement operand) {
        this.setMalformed();
        System.err.println("ERROR " + line + ":" + column + " => " + operand.getName() + " (" + operand.getType() + ") " + "no puede formar parte de esta operación (" + Operation.getOperationName(this.operationType) + ")");
    }

    private void errorHelper(AssignableElement operandA, AssignableElement operandB) {
        this.setMalformed();
        System.err.println("ERROR " + line + ":" + column + " => " + operandA.getName() + " (" + operandA.getType() + ") no es operable con " + operandB.getName() + " (" + operandB.getType() + ")");
    }

}
