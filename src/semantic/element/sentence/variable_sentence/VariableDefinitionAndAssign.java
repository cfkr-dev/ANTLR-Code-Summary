package semantic.element.sentence.variable_sentence;

import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element.variable.SimpleVariable;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

public class VariableDefinitionAndAssign extends MasterSimpleSentence {

    private SimpleVariable variable;
    private String value;

    public VariableDefinitionAndAssign(SimpleVariable variable, ProgrammableElement context) {
        this.type = variable.getType();
        this.name = variable.getName() + "_DEF_AND_ASSIG";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.VARDEF_ASSIG;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.variable = variable;
        this.value = variable.getValue();
    }

    @Override
    public String toHTML() {
        return "<p>" + this.type.name() + " " + this.name + " = " + this.value + ";</p>\n";
    }
}
