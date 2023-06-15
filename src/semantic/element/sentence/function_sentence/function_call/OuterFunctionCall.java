package semantic.element.sentence.function_sentence.function_call;

import semantic.element.sentence.function_sentence.function_call.master_function_call.MasterFunctionCall;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;
import semantic.utils.enums.Type;

import java.util.LinkedList;

public class OuterFunctionCall extends MasterFunctionCall {

    public OuterFunctionCall(String functionName, ProgrammableElement context, int line, int column) {
        if (Type.checkTypeCasting(functionName))
            this.type = Type.valueOf(functionName);
        else
            this.type = Type.ANY;
        this.name = "OUTER_CALL_" + line + "_" + column;
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.OUTER_FUNCT_CALL;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = context.getAnchorContext() + ":" + this.name;
        this.functionName = functionName;
        this.callingParams = new LinkedList<>();
        this.malformed = true;
        this.partOfExpression = true;
        this.errorOnCreation = false;
        this.line = line;
        this.column = column;
        this.hasParenthesis = false;
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
