package semantic.element.sentence.function_sentence.function_call;

import semantic.element.sentence.function_sentence.function_call.master_function_call.MasterFunctionCall;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;
import semantic.utils.enums.Type;

import java.util.LinkedList;

public class OuterFunctionCall extends MasterFunctionCall {

    public OuterFunctionCall(String functionName, ProgrammableElement context, Boolean partOfExpression, int line, int column) {
        this.type = Type.ANY;
        this.name = functionName + "_CALL";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.FUNCT_CALL;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.functionName = functionName;
        this.callingParams = new LinkedList<>();
        this.partOfExpression = partOfExpression;
        this.malformed = true;
        this.errorOnCreation = false;
        this.line = line;
        this.column = column;
    }

    @Override
    public FunctionCall call() {
        if (this.errorOnCreation)
            this.setMalformed();
        else
            this.malformed = false;
        return this;
    }

}
