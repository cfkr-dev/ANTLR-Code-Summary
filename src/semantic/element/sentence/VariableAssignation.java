package semantic.element.sentence;

import semantic.element.Variable;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Sentence;

public class VariableAssignation extends MasterSimpleSentence {
    private Variable variable;
    private String value;

    public VariableAssignation(Variable variable, ProgrammableElement context) {
        this.type = variable.getType();
        this.name = variable.getName() + "_ASSIG";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.ASSIG;
        this.context = context;
        this.variable = variable;
        this.value = variable.getLiteralValue();
    }

    @Override
    public String toHTML() {
        return null;
    }
}
