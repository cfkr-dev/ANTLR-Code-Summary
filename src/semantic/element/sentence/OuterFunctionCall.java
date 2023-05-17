package semantic.element.sentence;

import semantic.element.sentence.function_call.master_function_call.MasterFunctionCall;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Sentence;
import semantic.enums.Type;

import java.util.LinkedList;

public class OuterFunctionCall extends MasterFunctionCall {

    public OuterFunctionCall(String functionName, ProgrammableElement context) {
        this.type = Type.ANY;
        this.name = functionName + "_CALL";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.FUNCT_CALL;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.functionName = functionName;
        this.callingParams = new LinkedList<>();
    }

    @Override
    public String toHTML() {
        return null;
    }
}
