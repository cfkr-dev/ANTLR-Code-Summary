package semantic.element.sentence;

import semantic.element.Variable;
import semantic.element.sentence.literal.NumericIntegerConstant;
import semantic.element.sentence.operation.ArithmeticOperation;
import semantic.element.sentence.operation.LogicOperation;
import semantic.element.sentence.sentence_master.MasterProgrammableSentence;
import semantic.element_interfaces.ProgramElement;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Sentence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ForLoop extends MasterProgrammableSentence {

    Variable indexVariable;
    LogicOperation conditionStop;
    ArithmeticOperation afterIteration;

    public ForLoop(Variable variable,
                   LogicOperation conditionStop,
                   ArithmeticOperation afterIteration,
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
        this.afterIteration = afterIteration;
    }

    public ForLoop(String indexVariableType, String indexVariableName, NumericIntegerConstant startValue,
                   LogicOperation conditionStop,
                   ArithmeticOperation afterIteration,
                   ProgrammableElement context) {
        this.type = null;
        this.name = "FOR_LOOP";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.FOR;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
        this.indexVariable = createIndexVariable(indexVariableType, indexVariableName, startValue);
        this.conditionStop = conditionStop;
        this.afterIteration = afterIteration;
    }

    public Variable getIndexVariable() {
        return this.indexVariable;
    }

    private Variable createIndexVariable(String indexVariableType, String indexVariableName, NumericIntegerConstant startValue) {
        Variable variable = this.createNewVariable(indexVariableType,indexVariableName);
        variable.setValue(startValue);
        return variable;
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
