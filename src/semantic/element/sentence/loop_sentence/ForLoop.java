package semantic.element.sentence.loop_sentence;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.sentence_master.MasterProgrammableSentence;
import semantic.element.sentence.variable_sentence.VariableAssignation;
import semantic.element.sentence.variable_sentence.variable_sentence_interface.VariableSentence;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.Constants;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ForLoop extends MasterProgrammableSentence {

    VariableSentence indexVariable;
    AssignableElement conditionStop;
    VariableAssignation assignationAfterIteration;

    public ForLoop(VariableSentence variable,
                   AssignableElement conditionStop,
                   VariableAssignation assignationAfterIteration,
                   ProgrammableElement context, int line, int column) {
        this.type = null;
        this.name = "FOR_LOOP_" + line + "_" + column;
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.FOR;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
        this.indexVariable = variable;
        this.conditionStop = conditionStop;
        this.assignationAfterIteration = assignationAfterIteration;
        this.malformed = false;
        this.line = line;
        this.column = column;
    }

    public Variable getIndexVariable() {
        return this.indexVariable.getVariable();
    }

    private Map<Element, Map<String, ProgramElement>> generateLocalSymbolTable(Map<Element, Map<String, ProgramElement>> symbolTable) {
        Map<Element, Map<String, ProgramElement>> localSymbolTable = new HashMap<>();
        for (Element e: Constants.PROGRAM_SYMBOLS)
            localSymbolTable.put(e, new HashMap<>(symbolTable.get(e)));
        return localSymbolTable;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel, String anchorContext) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);

        StringBuilder HTMLFor = new StringBuilder()
                .append(tabs)
                .append(HTMLHelper.genSpan("palres", "for"))
                .append("(");

        if (this.indexVariable.getSentenceType().equals(Sentence.VAR_DEF)) {
            HTMLFor
                .append(HTMLHelper.genA(anchorContext  + ":" + this.name + ":" + this.indexVariable.getVariable().getName()))
                .append(HTMLHelper.genSpan("palres", this.indexVariable.getVariable().getType().name()))
                .append(" ")
                .append(HTMLHelper.genSpan("ident", this.indexVariable.getVariable().getName()));
        } else if (this.indexVariable.getSentenceType().equals(Sentence.VARDEF_ASSIG)) {
            HTMLFor
                .append(HTMLHelper.genA(anchorContext  + ":" + this.name + ":" + this.indexVariable.getVariable().getName()))
                .append(HTMLHelper.genSpan("palres", this.indexVariable.getVariable().getType().name()))
                .append(" ")
                .append(HTMLHelper.genSpan("ident", this.indexVariable.getVariable().getName()))
                .append(HTMLHelper.genSpan("palres", " = "))
                .append(this.indexVariable.getVariable().getValue().toHTML(HTMLIndentationLevel, anchorContext));
        } else {
            HTMLFor
                .append(HTMLHelper.genAHref(anchorContext  + ":" + this.name + ":" + this.indexVariable.getVariable().getName(), HTMLHelper.genSpan("ident", this.indexVariable.getVariable().getName())))
                .append(HTMLHelper.genSpan("palres", " = "))
                .append(this.indexVariable.getVariable().getValue().toHTML(HTMLIndentationLevel, anchorContext));
        }

        HTMLFor
            .append("; ")
            .append(this.conditionStop.toHTML(HTMLIndentationLevel, anchorContext))
            .append("; ")
            .append(HTMLHelper.genAHref(anchorContext  + ":" + this.name + ":" + this.assignationAfterIteration.getVariable().getName(), HTMLHelper.genSpan("ident", this.assignationAfterIteration.getVariable().getName())))
            .append(HTMLHelper.genSpan("palres", " = "))
            .append(this.assignationAfterIteration.getVariable().getValue().toHTML(HTMLIndentationLevel, anchorContext))
            .append(";")
            .append(")")
            .append(HTMLHelper.genBr(tabs))
            .append(tabs).append("{")
            .append(HTMLHelper.genBr(tabs))
            .append(tabs).append("<div>\n");

        for (semantic.element.sentence.sentence_interface.Sentence sentence: this.sentences)
            HTMLFor.append(sentence.toHTML(HTMLIndentationLevel + 1, anchorContext + ":" + this.name));

        HTMLFor
            .append(tabs).append("</div>\n\n")
            .append(tabs).append("}")
            .append(HTMLHelper.genBr(tabs));

        return HTMLFor.toString();
    }

}
