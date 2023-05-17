package semantic.element.sentence;

import semantic.element.Function;
import semantic.element.sentence.function_call.master_function_call.MasterFunctionCall;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Sentence;

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
    }

    @Override
    public String toHTML() {
        return null;
    }
}
