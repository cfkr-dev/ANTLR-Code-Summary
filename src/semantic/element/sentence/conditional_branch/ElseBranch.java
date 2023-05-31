package semantic.element.sentence.conditional_branch;

import semantic.HTMLToolKit;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.Constants;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ElseBranch extends MasterConditionalBranch {

    ConditionalBranch previous;

    public ElseBranch(ConditionalBranch previous, ProgrammableElement context, int line, int column) {
        this.type = null;
        this.name = "_ELSE_BRANCH";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.ELSE;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
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
    public String toHTML() {

        String HTMLElse = new String();

        HTMLElse = HTMLToolKit.palresMaker("else") + " ";
        HTMLElse += HTMLToolKit.betweenBrakets(this.sentences);

        return HTMLElse + "<br>\n";

    }

    @Override
    public String toHTMLLong() {
        return this.toHTML();
    }
}
