package semantic.element.sentence;

import semantic.element_interfaces.AssignableElement;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Sentence;
import semantic.enums.Type;

import java.util.List;

public class OuterFunctionCall extends MasterSimpleSentence implements AssignableElement{

    private String functionName;
    private List<AssignableElement> callingParams;

    public OuterFunctionCall(String functionName, List<AssignableElement> callingParams, ProgrammableElement context) {
        this.type = Type.ANY;
        this.name = functionName + "_CALL";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.FUNCT_CALL;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.functionName = functionName;
        this.callingParams = callingParams;
    }

    @Override
    public String toHTML() {
        return null;
    }

    @Override
    public String getLiteralValue() {
        return this.toString();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(this.functionName + '(');
        boolean first = true;
        for (AssignableElement param: this.callingParams) {
            if (first) {
                s.append(param.getName());
                first = false;
            } else
                s.append(", ").append(param.getName());
        }
        return s.toString();
    }
}
