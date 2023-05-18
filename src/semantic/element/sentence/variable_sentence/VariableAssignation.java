package semantic.element.sentence.variable_sentence;

import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element.variable.SimpleVariable;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

public class VariableAssignation extends MasterSimpleSentence {
    private SimpleVariable variable;
    private String value;

    public VariableAssignation(SimpleVariable variable, ProgrammableElement context) {
        this.type = variable.getType();
        this.name = variable.getName() + "_ASSIG";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.ASSIG;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.variable = variable;
        this.value = variable.getValue();
    }

    public SimpleVariable getVariable() {
        return variable;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toHTML() {
        return null;
    }
}
