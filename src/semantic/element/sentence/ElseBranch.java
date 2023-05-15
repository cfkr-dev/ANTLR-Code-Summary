package semantic.element.sentence;

import semantic.element.sentence.conditional_branch.ConditionalBranch;
import semantic.element.sentence.conditional_branch.MasterConditionalBranch;
import semantic.element.sentence.operation.LogicOperation;
import semantic.element_interfaces.ProgramElement;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Sentence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ElseBranch extends MasterConditionalBranch {

    LogicOperation logicOperation;
    ConditionalBranch previous;

    public ElseBranch(LogicOperation logicOperation, ConditionalBranch previous, ProgrammableElement context) {
        this.type = null;
        this.name = logicOperation.getExpression() + "_ELSE_BRANCH";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.ELSE;
        this.context = context;
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
        this.logicOperation = logicOperation;
        this.previous = previous;
    }

    private Map<Element, Map<String, ProgramElement>> generateLocalSymbolTable(Map<Element, Map<String, ProgramElement>> symbolTable) {
        Map<Element, Map<String, ProgramElement>> localSymbolTable = new HashMap<>();
        for (Element e: Element.values())
            localSymbolTable.put(e, new HashMap<>(symbolTable.get(e)));

        return localSymbolTable;
    }

    @Override
    public String toHTML() {
        return null;
    }
}
