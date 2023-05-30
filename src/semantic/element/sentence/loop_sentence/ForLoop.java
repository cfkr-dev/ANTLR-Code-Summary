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
    boolean createdIndexVariable;

    public ForLoop(ProgrammableElement context, int line, int column) {
        this.type = null;
        this.name = "FOR_LOOP_" + line + "_" + column;
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.FOR;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = context.getAnchorContext() + ":" + this.name;
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
        this.indexVariable = null;
        this.conditionStop = null;
        this.assignationAfterIteration = null;
        this.createdIndexVariable = false;
        this.malformed = false;
        this.line = line;
        this.column = column;
    }

    public Variable getIndexVariable() {
        return this.indexVariable.getVariable();
    }

    public ForLoop setIndexVariable(VariableSentence indexVariable, boolean createdIndexVariable) {
        if (indexVariable.isMalformed())
            this.setMalformed();

        this.indexVariable = indexVariable;

        this.createdIndexVariable = createdIndexVariable;

        return this;
    }

    public ForLoop setConditionStop(AssignableElement conditionStop) {
        if (conditionStop.isMalformed())
            this.setMalformed();

        this.conditionStop = conditionStop;

        return this;
    }

    public ForLoop setAssignationAfterIteration(VariableAssignation assignationAfterIteration) {
        if (assignationAfterIteration.isMalformed())
            this.setMalformed();

        this.assignationAfterIteration = assignationAfterIteration;

        return this;
    }

    private Map<Element, Map<String, ProgramElement>> generateLocalSymbolTable(Map<Element, Map<String, ProgramElement>> symbolTable) {
        Map<Element, Map<String, ProgramElement>> localSymbolTable = new HashMap<>();
        for (Element e: Constants.PROGRAM_SYMBOLS)
            localSymbolTable.put(e, new HashMap<>(symbolTable.get(e)));
        return localSymbolTable;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);

        StringBuilder HTMLFor = new StringBuilder()
                .append(tabs)
                .append(HTMLHelper.genSpan("palres", "for"))
                .append("(");

        if (createdIndexVariable)
            HTMLFor
                .append(HTMLHelper.genA(this.indexVariable.getVariable().getAnchorContext()));

        HTMLFor
            .append(this.indexVariable.toHTMLNoWhiteSpaces())
            .append(" ")
            .append(this.conditionStop.toHTML(HTMLIndentationLevel))
            .append("; ")
            .append(this.assignationAfterIteration.toHTMLNoWhiteSpaces())
            .append(")")
            .append(HTMLHelper.genBr(tabs))
            .append(tabs).append("{")
            .append(HTMLHelper.genBr(tabs))
            .append(tabs).append("<div>\n");

        for (semantic.element.sentence.sentence_interface.Sentence sentence: this.sentences)
            HTMLFor.append(sentence.toHTML(HTMLIndentationLevel + 1));

        HTMLFor
            .append(tabs).append("</div>\n\n")
            .append(tabs).append("}")
            .append(HTMLHelper.genBr(tabs));

        return HTMLFor.toString();
    }

}
