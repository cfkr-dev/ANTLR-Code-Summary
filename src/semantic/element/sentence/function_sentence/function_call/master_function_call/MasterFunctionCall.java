package semantic.element.sentence.function_sentence.function_call.master_function_call;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.sentence.function_sentence.function_call.FunctionCall;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;

import java.util.List;

public abstract class MasterFunctionCall extends MasterSimpleSentence implements FunctionCall {

    protected String functionName;
    protected List<AssignableElement> callingParams;
    protected boolean malformed;
    protected boolean errorOnCreation;
    protected boolean partOfExpression;
    protected boolean hasParenthesis;

    @Override
    public List<AssignableElement> getCallingParams() {
        return this.callingParams;
    }

    @Override
    public AssignableElement setParenthesis() {
        this.hasParenthesis = true;
        return this;
    }

    @Override
    public FunctionCall addParam(AssignableElement param) {
        if (param.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No es posible llamar a una función con una expresión malformada (" + param.getValue() + ")");
            this.errorOnCreation = true;
            return this;
        }
        this.callingParams.add(param);
        return this;
    }

    @Override
    public String getFunctionName() {
        return this.functionName;
    }

    @Override
    public String getValue() {
        return this.toString();
    }

    public Boolean getPartOfExpression() {
        return partOfExpression;
    }

    public void notPartOfExpression() {
        this.partOfExpression = false;
    }

    public String toHTML(int HTMLIndentationLevel) {

        StringBuilder HTMLFunction;
        if (!this.hasParenthesis)
            HTMLFunction = new StringBuilder("<SPAN CLASS=\"ident\">" + this.functionName + "</SPAN>(");
        else
            HTMLFunction = new StringBuilder("<SPAN CLASS=\"ident\">" + "(" + this.functionName + "</SPAN>(");


        for (AssignableElement elem : callingParams) {

            if (elem != callingParams.get(0))
                HTMLFunction.append(", ");

            HTMLFunction.append(elem.getName());

        }
        HTMLFunction.append(")");

        if (this.hasParenthesis)
            HTMLFunction.append(")");

        if (partOfExpression)
            return HTMLFunction.toString();
        else
            return "<p>" + HTMLFunction + ";</p>\n";

    }

    @Override
    public boolean isMalformed() {
        return this.malformed;
    }

    @Override
    public abstract FunctionCall call();
}
