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
        this.name = variable.getType().equals(Type.STRUCT) ? "STRUCT_ASSIG_" + line + "_" + column : "ASSIG_" + line + "_" + column;
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.ASSIG;
        this.variable = variable;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = variable.getAnchorContext();
        this.malformed = false;
        this.line = line;
        this.column = column;
    }

    @Override
    public Variable getVariable() {
        return variable;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);

        return new StringBuilder()
            .append(tabs).append(HTMLHelper.genAHref(this.anchorContext, HTMLHelper.genSpan("ident", this.variable.getName())))
            .append(HTMLHelper.genSpan("palres", " = "))
            .append(this.variable.getValue().toHTML(HTMLIndentationLevel))
            .append(";")
            .append(HTMLHelper.genBr(tabs))
            .toString();
    }

    @Override
    public String toHTMLNoWhiteSpaces() {
        return new StringBuilder()
            .append(HTMLHelper.genAHref(this.anchorContext, HTMLHelper.genSpan("ident", this.variable.getName())))
            .append(HTMLHelper.genSpan("palres", " = "))
            .append(this.variable.getValue().toHTML(0))
            .append(";")
            .toString();
    }
}
