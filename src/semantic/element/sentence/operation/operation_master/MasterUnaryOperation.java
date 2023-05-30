package semantic.element.sentence.operation.operation_master;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_master.MasterProgramElement;
import semantic.element.sentence.operation.operation_interface.UnaryOperation;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Operation;
import semantic.utils.enums.Type;

public abstract class MasterUnaryOperation extends MasterProgramElement implements UnaryOperation {
    protected AssignableElement firstOperand;
    protected String symbol;
    protected Boolean hasParenthesis;
    protected Operation operationType;

    @Override
    public AssignableElement getValue() {
        return this;
    }

    @Override
    public String toString() {
        if (this.hasParenthesis)
            return "(" + symbol + firstOperand.toString() + ")";
        else
            return symbol + firstOperand.toString();

    }

    @Override
    public MasterUnaryOperation setParenthesis() {
        this.hasParenthesis = true;
        return this;

    }

    public MasterUnaryOperation setParenthesis(Boolean hasParenthesis) {
        this.hasParenthesis = hasParenthesis;
        return this;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        StringBuilder HTMLOperation = new StringBuilder()
            .append(HTMLHelper.genSpan("palres", this.symbol));

        if (this.hasParenthesis)
            HTMLOperation
                .append("(");

        HTMLOperation
            .append(this.firstOperand.toHTML(HTMLIndentationLevel));

        if (this.hasParenthesis)
            HTMLOperation
                .append(")");

        return HTMLOperation
            .toString();
    }

    public MasterUnaryOperation firstOperand(AssignableElement firstOperand) {
        if (firstOperand.isMalformed())
            this.setMalformed();
        this.firstOperand = firstOperand;
        this.type = this.assertType(firstOperand);
        return this;
    }

    public Type assertType(AssignableElement firstOperand) {
        if (this.malformed){
            this.setMalformed();
            return null;
        }

        if (firstOperand.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No es posible operar con una expresión malformada (" + firstOperand.toString() + ")");
            this.setMalformed();
            return null;
        }

        if (!Type.checkTypeOperationRules(firstOperand.getType(), this.operationType)){
            errorHelper(firstOperand);
            return null;
        }

        return firstOperand.getType();
    }

    private void errorHelper(AssignableElement operand) {
        this.setMalformed();
        System.err.println("ERROR " + line + ":" + column + " => " + operand.getName() + " (" + operand.getType() + ") " + "no puede formar parte de esta operación (" + Operation.getOperationName(this.operationType) + ")");
    }
}
