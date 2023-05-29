package semantic.element.sentence.variable_sentence;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element.sentence.variable_sentence.variable_sentence_interface.VariableSentence;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;
import semantic.utils.enums.Type;

public class VariableAssignation extends MasterSimpleSentence implements VariableSentence {
    private Variable variable;

    public VariableAssignation(Variable variable, ProgrammableElement context, int line, int column) {
        this.type = variable.getType();
        this.name = variable.getType().equals(Type.STRUCT) ? "STRUCT_ASSIG_" + line + "_" + column : variable.getName() + "ASSIG_" + line + "_" + column;
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.ASSIG;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.variable = variable;
        this.line = line;
        this.column = column;
    }

    @Override
    public Variable getVariable() {
        return variable;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel, String anchorContext) {
        String tabs = HTMLHelper.generateTabulations(HTMLIndentationLevel);
        return tabs + "<span class=\"ident\">" + this.variable.getName() + "</span>" + "<span class=\"palres\"> = </span>" + this.variable.getValue().toHTML(HTMLIndentationLevel, anchorContext) + ";\n\n" + tabs + "<br/>\n\n";
    }
}
