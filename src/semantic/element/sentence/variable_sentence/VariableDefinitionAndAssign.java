package semantic.element.sentence.variable_sentence;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

public class VariableDefinitionAndAssign extends MasterSimpleSentence {

    private Variable variable;

    public VariableDefinitionAndAssign(Variable variable, ProgrammableElement context, int line, int column) {
        this.type = variable.getType();
        this.name = variable.getName() + "_DEF_AND_ASSIG";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.VARDEF_ASSIG;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.variable = variable;
        this.line = line;
        this.column = column;
    }

    public Variable getVariable () {
        return this.variable;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        return "<p>" + this.type.name() + " " + this.name + " = " + this.variable.getValue() + ";</p>\n";
    }
}
