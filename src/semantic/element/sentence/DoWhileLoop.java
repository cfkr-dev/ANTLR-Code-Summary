package semantic.element.sentence;

import semantic.element.sentence.conditional_branch.MasterConditionalBranch;
import semantic.element.sentence.operation.LogicOperation;
import semantic.element_interfaces.ProgramElement;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Sentence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DoWhileLoop extends MasterConditionalBranch {
    LogicOperation logicOperation;

    public DoWhileLoop(ProgrammableElement context) {
        this.type = null;
        this.name = "DO_WHILE_LOOP";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.DO_WHILE;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
        this.logicOperation = null;
    }

    public void setLogicOperation(LogicOperation logicOperation) {
        this.logicOperation = logicOperation;
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
