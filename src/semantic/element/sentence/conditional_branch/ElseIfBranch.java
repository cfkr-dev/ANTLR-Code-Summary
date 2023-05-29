package semantic.element.sentence.conditional_branch;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.Constants;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ElseIfBranch extends MasterConditionalBranch {

    AssignableElement logicOperation;
    ConditionalBranch previous;

    public ElseIfBranch(AssignableElement logicOperation, ConditionalBranch previous, ProgrammableElement context, int line, int column) {
        this.type = null;
        this.name = "IF_ELSE_BRANCH_" + line + "_" + column;
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.IF_ELSE;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
        this.logicOperation = logicOperation;
        this.previous = previous;
        this.malformed = false;
        this.line = line;
        this.column = column;
    }

    private Map<Element, Map<String, ProgramElement>> generateLocalSymbolTable(Map<Element, Map<String, ProgramElement>> symbolTable) {
        Map<Element, Map<String, ProgramElement>> localSymbolTable = new HashMap<>();
        for (Element e: Constants.PROGRAM_SYMBOLS)
            localSymbolTable.put(e, new HashMap<>(symbolTable.get(e)));

        return localSymbolTable;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel, String anchorContext) {
        String tabs = HTMLHelper.generateTabulations(HTMLIndentationLevel);

        StringBuilder HTMLElseIf = new StringBuilder()
                .append(tabs)
                .append("<span class=\"palres\">else if</span> (")
                .append(this.logicOperation.toHTML(HTMLIndentationLevel, anchorContext)).append(")").append("\n\n")
                .append(tabs)
                .append("<br/>")
                .append("\n\n")
                .append(tabs).append("{").append("\n\n")
                .append(tabs)
                .append("<br/>")
                .append("\n\n")
                .append(tabs).append("<div>\n");

        for (semantic.element.sentence.sentence_interface.Sentence sentence: this.sentences)
            HTMLElseIf.append(sentence.toHTML(HTMLIndentationLevel + 1, anchorContext + ":" + this.name));

        HTMLElseIf
                .append(tabs).append("</div>\n\n")
                .append(tabs).append("}").append("\n\n")
                .append(tabs)
                .append("<br/>")
                .append("\n\n");

        return HTMLElseIf.toString();
    }
}
