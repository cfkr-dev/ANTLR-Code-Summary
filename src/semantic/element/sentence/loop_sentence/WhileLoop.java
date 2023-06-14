package semantic.element.sentence.loop_sentence;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.sentence_master.MasterProgrammableSentence;
import semantic.utils.Constants;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WhileLoop extends MasterProgrammableSentence {
    AssignableElement logicOperation;

    public WhileLoop(AssignableElement logicOperation, ProgrammableElement context, int line, int column) {
        this.type = null;
        this.name = "WHILE_LOOP_" + line + "_" + column;
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.WHILE;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = context.getAnchorContext() + ":" + this.name;
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
        this.logicOperation = logicOperation;
        this.malformed = false;
        this.hasReturnPoint = false;
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
    public String toHTML(int HTMLIndentationLevel) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);

        StringBuilder HTMLWhile = new StringBuilder()
            .append(tabs)
            .append(HTMLHelper.genSpan("palres", "while"))
            .append("(")
            .append(this.logicOperation.toHTML(HTMLIndentationLevel))
            .append(")")
            .append(HTMLHelper.genBr(tabs))
            .append(tabs).append("{")
            .append(HTMLHelper.genBr(tabs))
            .append(tabs).append("<div>\n");

        for (semantic.element.sentence.sentence_interface.Sentence sentence: this.sentences)
            HTMLWhile.append(sentence.toHTML(HTMLIndentationLevel + 1));

        HTMLWhile
            .append(tabs).append("</div>\n\n")
            .append(tabs).append("}")
            .append(HTMLHelper.genBr(tabs));

        return HTMLWhile.toString();
    }
}
