package semantic.element.sentence.operation.operation_master;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.element.sentence.operation.operation_interface.BinaryOperation;
import semantic.utils.enums.Operation;
import semantic.utils.enums.Type;

public abstract class MasterBinaryOperation extends MasterProgramElement implements BinaryOperation {
    protected AssignableElement firstOperand;
    protected AssignableElement secondOperand;
    protected String symbol;
    protected Operation operationType;
    protected Boolean hasParenthesis;

    @Override
    public String getValue() {
        return this.toString();
    }

    @Override
    public String toString() {
        return firstOperand.getValue() + " " + symbol + " " + secondOperand.getValue();
    }

    @Override
    public MasterBinaryOperation setParenthesis() {
        this.hasParenthesis = true;
        return this;
    }

    @Override
    public String toHTML() {
        return null;
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
            System.err.println("No es posible operar con una expresión malformada (" + firstOperand.getValue() + ")");
            this.setMalformed();
            return null;
        }

        if (secondOperand.isMalformed()) {
            System.err.println("No es posible operar con una expresión malformada (" + secondOperand.getValue() + ")");
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

        return Type.typeConversion(firstOperand.getType(), secondOperand.getType());
    }

    private void errorHelper(AssignableElement operand) {
        this.setMalformed();
        System.err.println(operand.getName() + " (" + operand.getType() + ") " + "no puede formar parte de esta operación (" + Operation.getOperationName(this.operationType) + ")");
    }

    private void errorHelper(AssignableElement operandA, AssignableElement operandB) {
        this.setMalformed();
        System.err.println(operandA.getName() + " (" + operandA.getType() + ") no es operable con " + operandB.getName() + " (" + operandB.getType() + ")");
    }

}
