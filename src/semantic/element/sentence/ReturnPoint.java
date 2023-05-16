package semantic.element.sentence;

import semantic.element.Function;
import semantic.element_interfaces.AssignableElement;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Sentence;

public class ReturnPoint extends MasterSimpleSentence {

    private Function targetFunction;
    private AssignableElement returnElement;

    public ReturnPoint(Function targetFunction, AssignableElement returnElement, ProgrammableElement context) {
        this.type = returnElement.getType();
        this.name = targetFunction.getName() + "_RETURN";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.RETURN;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.targetFunction = targetFunction;
        this.returnElement = returnElement;
    }

    @Override
    public String toHTML() {
        return null;
    }
}
