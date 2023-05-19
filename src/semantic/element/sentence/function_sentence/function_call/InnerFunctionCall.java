package semantic.element.sentence.function_sentence.function_call;

import semantic.element.Function;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.sentence.function_sentence.function_call.master_function_call.MasterFunctionCall;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.Param;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

import java.util.LinkedList;

public class InnerFunctionCall extends MasterFunctionCall {

    private Function function;

    public InnerFunctionCall(Function function, ProgrammableElement context, Boolean partOfExpression) {
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
    }

}
