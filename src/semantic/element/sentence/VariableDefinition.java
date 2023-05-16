package semantic.element.sentence;

import semantic.element.Variable;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Sentence;

public class VariableDefinition extends MasterSimpleSentence {

    private Variable variable;

    public VariableDefinition(Variable variable, ProgrammableElement context) {
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
