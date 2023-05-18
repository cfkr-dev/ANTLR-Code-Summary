package semantic.element.sentence.loop_sentence;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.sentence_master.MasterProgrammableSentence;
import semantic.element.sentence.variable_sentence.VariableAssignation;
import semantic.element.variable.SimpleVariable;
import semantic.utils.Constants;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ForLoop extends MasterProgrammableSentence {

    SimpleVariable indexVariable;
    AssignableElement conditionStop;
    VariableAssignation assignationAfterIteration;

    public ForLoop(SimpleVariable variable,
                   AssignableElement conditionStop,
                   VariableAssignation assignationAfterIteration,
                   ProgrammableElement context) {
        this.type = null;
        this.name = "FOR_LOOP";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.FOR;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
        this.indexVariable = variable;
        this.conditionStop = conditionStop;
        this.assignationAfterIteration = assignationAfterIteration;

        insertIndexVariable();
    }

    public SimpleVariable getIndexVariable() {
        return this.indexVariable;
    }

    private void insertIndexVariable() {
        this.createNewVariable(indexVariable.getType().name(), getIndexVariable().getName());
    }

    private Map<Element, Map<String, ProgramElement>> generateLocalSymbolTable(Map<Element, Map<String, ProgramElement>> symbolTable) {
        Map<Element, Map<String, ProgramElement>> localSymbolTable = new HashMap<>();
        for (Element e: Constants.PROGRAM_SYMBOLS)
            localSymbolTable.put(e, new HashMap<>(symbolTable.get(e)));
        return localSymbolTable;
    }

    @Override
    public String toHTML() {
        return null;
    }

}
