package semantic.element.sentence.function_sentence.function_call.master_function_call;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.sentence.function_sentence.function_call.FunctionCall;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Sentence;

import java.util.List;

public abstract class MasterFunctionCall extends MasterSimpleSentence implements FunctionCall {

    protected String functionName;
    protected List<AssignableElement> callingParams;
    protected boolean malformed;
    protected boolean partOfExpression;
    protected boolean errorOnCreation;
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
            System.err.println("ERROR " + line + ":" + column + " => " + "No es posible llamar a una función con una expresión malformada (" + param.toString() + ")");
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
    public boolean isPartOfExpression() {
        return this.partOfExpression;
    }

    @Override
    public void notPartOfExpression(){
        this.partOfExpression = false;
    }

    @Override
    public AssignableElement getValue() {
        return this;
    }

    @Override
    public String toString() {
        StringBuilder functionCall = new StringBuilder();

        functionCall
            .append(this.functionName)
            .append("(");

        boolean first = true;
        for (AssignableElement param: this.callingParams) {
            if (first)
                first = false;
            else
                functionCall.append(", ");

            functionCall
                .append(param.toString());
        }

        functionCall
            .append(")");

        return functionCall.toString();
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);

        StringBuilder HTMLFunctionCall = new StringBuilder();

        if (!this.partOfExpression)
            HTMLFunctionCall.append(tabs);

        if (this.sentenceType.equals(Sentence.INNER_FUNCT_CALL))
            HTMLFunctionCall
                .append(HTMLHelper.genAHref(this.anchorContext, HTMLHelper.genSpan("ident", this.functionName)));
        else
            HTMLFunctionCall
                .append(HTMLHelper.genSpan("ident", this.functionName));

        HTMLFunctionCall
            .append("(");

        boolean first = true;
        for (AssignableElement param: this.callingParams) {
            if (first)
                first = false;
            else
                HTMLFunctionCall.append(", ");

            HTMLFunctionCall
                .append(param.toHTML(HTMLIndentationLevel));
        }

        HTMLFunctionCall
            .append(")");

        if (!this.partOfExpression)
            HTMLFunctionCall
                .append(";")
                .append(HTMLHelper.genBr(tabs));

        return HTMLFunctionCall.toString();
    }

    @Override
    public boolean isMalformed() {
        return this.malformed;
    }

    @Override
    public abstract FunctionCall call();
}
