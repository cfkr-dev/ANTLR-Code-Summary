package semantic.element.sentence.variable_sentence;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

public class VariableDefinition extends MasterSimpleSentence {

    private Variable variable;

    public VariableDefinition(Variable variable, ProgrammableElement context, int line, int column) {
        this.type = variable.getType();
        this.name = variable.getName() + "_DEF";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.VAR_DEF;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.variable = variable;
        this.malformed = false;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {

        return "<p>" + this.type.name() + " " + this.name + ";</p>\n";
    }
}
