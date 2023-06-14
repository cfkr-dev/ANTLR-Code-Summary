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

    public InnerFunctionCall(Function function, ProgrammableElement context, int line, int column) {
        if (Type.checkTypeCasting(function.getName()))
            this.type = Type.valueOf(function.getName());
        else
            this.type = function.getType();
        this.name = "INNER_CALL_" + line + "_" + column;
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.INNER_FUNCT_CALL;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = function.getAnchorContext();
        this.function = function;
        this.functionName = function.getName();
        this.callingParams = new LinkedList<>();
        this.malformed = true;
        this.partOfExpression = true;
        this.errorOnCreation = false;
        this.line = line;
        this.column = column;
        this.hasParenthesis = false;
    }

    public Function getFunction() {
        return function;
    }

    @Override
    public FunctionCall call() {
        if (this.errorOnCreation) {
            this.setMalformed();
        } else {
            if (!this.function.checkCallingParams(this.callingParams, this.line, this.column))
                this.setMalformed();
            else
                this.malformed = false;
        }
        return this;
    }
}
