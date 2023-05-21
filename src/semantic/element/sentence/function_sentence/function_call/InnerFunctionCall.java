package semantic.element.sentence.function_sentence.function_call;

import semantic.element.Function;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.function_sentence.function_call.master_function_call.MasterFunctionCall;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;
import semantic.utils.enums.Type;

import java.util.LinkedList;

public class InnerFunctionCall extends MasterFunctionCall {

    private Function function;

    public InnerFunctionCall(Function function, ProgrammableElement context, Boolean partOfExpression, int line, int column) {
        if (Type.checkTypeCasting(function.getName()))
            this.type = Type.valueOf(function.getName());
        else
            this.type = function.getType();
        this.name = function.getName() + "_CALL";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.FUNCT_CALL;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.function = function;
        this.functionName = function.getName();
        this.callingParams = new LinkedList<>();
        this.partOfExpression = partOfExpression;
        this.malformed = true;
        this.errorOnCreation = false;
        this.line = line;
        this.column = column;
        this.hasParenthesis = false;
    }

    public Function getFunction() {
        return function;
    }

    public void setMalformed() {
        this.malformed = true;
    }

    @Override
    public String toHTML() {
        return null;
    }
    @Override
    public FunctionCall call() {
        if (this.errorOnCreation) {
            this.setMalformed();
        } else {
            if (!this.function.checkCallingParams(this.callingParams))
                this.setMalformed();
            else
                this.malformed = false;
        }
        return this;
    }
}
