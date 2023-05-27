package semantic.element.sentence.variable_sentence;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

public class VariableAssignation extends MasterSimpleSentence {
    private Variable variable;

    public VariableAssignation(Variable variable, ProgrammableElement context, int line, int column) {
        this.type = variable.getType();
        this.name = variable.getName() + "_ASSIG";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.ASSIG;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.variable = variable;
        this.line = line;
        this.column = column;
    }

    public Variable getVariable() {
        return variable;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        String tabs = HTMLHelper.generateTabulations(HTMLIndentationLevel);

        StringBuilder HTMLVariable = new StringBuilder();

        return HTMLVariable
                .append(tabs)
                .append("<span class=\"ident\">").append(this.getName()).append("</span>")
                .append(" = ")
                .append(this.variable.getValue().toHTML(HTMLIndentationLevel)).append(";")
                .append(" <br/>\n")
                .toString();
    }
}
