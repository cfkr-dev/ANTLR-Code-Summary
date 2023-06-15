package semantic.element.sentence.variable_sentence;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element.sentence.variable_sentence.variable_sentence_interface.VariableSentence;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

public class VariableDefinitionAndAssign extends MasterSimpleSentence implements VariableSentence {

    private final Variable variable;

    public VariableDefinitionAndAssign(Variable variable, ProgrammableElement context, int line, int column) {
        this.type = variable.getType();
        this.name = "DEF_AND_ASSIG_" + line + "_" + column;
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.VARDEF_ASSIG;
        this.variable = variable;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = variable.getAnchorContext();
        this.malformed = false;
        this.line = line;
        this.column = column;
    }

    @Override
    public Variable getVariable () {
        return this.variable;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);
        return this.variable.toHTML(HTMLIndentationLevel) + ";" + HTMLHelper.genBr(tabs);
    }

    @Override
    public String toHTMLNoWhiteSpaces() {
        return this.variable.toHTML(0) + ";";
    }
}
