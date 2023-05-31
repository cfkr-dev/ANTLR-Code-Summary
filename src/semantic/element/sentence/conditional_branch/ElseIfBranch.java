package semantic.element.sentence.conditional_branch;

import semantic.HTMLToolKit;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.Constants;
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
        this.name = logicOperation.getValue() + "_IF_ELSE_BRANCH";
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
    public String toHTML() {

        String HTMLElseIf = new String();

        HTMLElseIf =  HTMLToolKit.palresMaker("else if") + " (" + this.logicOperation.toHTML() + ") ";
        HTMLElseIf += HTMLToolKit.betweenBrakets(this.sentences);

        return HTMLElseIf + "<br>\n";

    }

    @Override
    public String toHTMLLong() {
        return this.toHTML();
    }
}
