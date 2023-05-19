package semantic.element.sentence.function_sentence.function_call;

import semantic.element.Function;
import semantic.element.sentence.function_sentence.function_call.master_function_call.MasterFunctionCall;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

import java.util.LinkedList;

public class InnerFunctionCall extends MasterFunctionCall {

    private Function function;

    public InnerFunctionCall(Function function, ProgrammableElement context) {
        this.type = function.getType();
        this.name = function.getName() + "_CALL";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.FUNCT_CALL;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.function = function;
        this.functionName = function.getName();
        this.callingParams = new LinkedList<>();
        this.malformed = true;
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
        if (this.function.checkCallingParams(this.callingParams))
            this.malformed = false;
        return this;
    }
}
