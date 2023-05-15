package semantic.element.sentence;

import semantic.element.Variable;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Sentence;

public class VariableDefinitionAndAssign extends MasterSimpleSentence {

    private Variable variable;
    private String value;

    public VariableDefinitionAndAssign(Variable variable, ProgrammableElement context) {
        this.type = variable.getType();
        this.name = variable.getName() + "_DEF_AND_ASSIG";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.VARDEF_ASSIG;
        this.context = context;
        this.variable = variable;
        this.value = variable.getLiteralValue();
    }

    @Override
    public String toHTML() {
        return null;
    }
}
