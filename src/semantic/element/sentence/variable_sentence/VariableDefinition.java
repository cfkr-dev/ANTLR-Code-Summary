package semantic.element.sentence.variable_sentence;

import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element.variable.SimpleVariable;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

public class VariableDefinition extends MasterSimpleSentence {

    private SimpleVariable variable;

    public VariableDefinition(SimpleVariable variable, ProgrammableElement context) {
        this.type = variable.getType();
        this.name = variable.getName() + "_DEF";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.VAR_DEF;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.variable = variable;
    }

    @Override
    public String toHTML() {
        return null;
    }
}