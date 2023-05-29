package semantic.element.sentence.variable_sentence;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element.sentence.variable_sentence.variable_sentence_interface.VariableSentence;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

public class VariableDefinition extends MasterSimpleSentence implements VariableSentence {

    private Variable variable;

    public VariableDefinition(Variable variable, ProgrammableElement context, int line, int column) {
        this.type = variable.getType();
        this.name = "DEF_" + line + "_" + column;
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
    public Variable getVariable() {
        return variable;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel, String anchorContext) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);
        return this.variable.toHTML(HTMLIndentationLevel, anchorContext) + ";" + HTMLHelper.genBr(tabs);
    }
}
