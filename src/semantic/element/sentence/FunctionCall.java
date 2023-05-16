package semantic.element.sentence;

import semantic.element.Function;
import semantic.element_interfaces.AssignableElement;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Sentence;

import java.util.List;

public class FunctionCall extends MasterSimpleSentence {

    private Function function;
    private List<AssignableElement> callingParams;

    public FunctionCall(Function function, List<AssignableElement> callingParams, ProgrammableElement context) {
        this.type = function.getType();
        this.name = function.getName() + "_CALL";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.FUNCT_CALL;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.function = function;
        this.callingParams = callingParams;
    }

    @Override
    public String toHTML() {
        return null;
    }
}
