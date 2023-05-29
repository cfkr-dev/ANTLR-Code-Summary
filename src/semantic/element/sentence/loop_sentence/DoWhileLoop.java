package semantic.element.sentence.loop_sentence;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.sentence_master.MasterProgrammableSentence;
import semantic.utils.Constants;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;
import semantic.utils.enums.Type;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DoWhileLoop extends MasterProgrammableSentence {
    AssignableElement logicOperation;

    public DoWhileLoop(ProgrammableElement context, int line, int column) {
        this.type = null;
        this.name = "DO_WHILE_LOOP_" + line + "_" + column;
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.DO_WHILE;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
        this.logicOperation = null;
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

    public DoWhileLoop createDoWhileLoop(AssignableElement logicOperation) {
        if (logicOperation.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada");
            this.setMalformed();
        } else if (Type.checkTypeConditional(logicOperation)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "Se debe introducir una expresión lógica");
            this.setMalformed();
        }

        this.logicOperation = logicOperation;
        return this;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel, String anchorContext) {
        String tabs = HTMLHelper.generateTabulations(HTMLIndentationLevel);

        StringBuilder HTMLDoWhile = new StringBuilder()
                .append(tabs)
                .append("<span class=\"palres\">do</span>")
                .append("\n\n")
                .append(tabs)
                .append("<br/>")
                .append("\n\n")
                .append("{")
                .append("\n\n")
                .append(tabs)
                .append("<br/>")
                .append("\n\n");

        HTMLDoWhile.append(tabs).append("<div>\n");

        for (semantic.element.sentence.sentence_interface.Sentence sentence: this.sentences)
            HTMLDoWhile.append(sentence.toHTML(HTMLIndentationLevel + 1, anchorContext + ":" + this.name));

        HTMLDoWhile.append(tabs).append("</div>\n\n");

        HTMLDoWhile
            .append(tabs)
            .append("} <span class=\"palres\">while</span> (")
            .append(this.logicOperation).append(");")
            .append("\n\n")
            .append(tabs)
            .append("<br/>")
            .append("\n\n");

        return HTMLDoWhile.toString();
    }
}
