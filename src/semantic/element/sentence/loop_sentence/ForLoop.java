package semantic.element.sentence.loop_sentence;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.sentence_master.MasterProgrammableSentence;
import semantic.element.sentence.variable_sentence.VariableAssignation;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.Constants;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ForLoop extends MasterProgrammableSentence {

    Variable indexVariable;
    AssignableElement conditionStop;
    VariableAssignation assignationAfterIteration;

    public ForLoop(Variable variable,
                   AssignableElement conditionStop,
                   VariableAssignation assignationAfterIteration,
                   ProgrammableElement context, int line, int column) {
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
        this.malformed = false;
        this.line = line;
        this.column = column;
    }

    public Variable getIndexVariable() {
        return this.indexVariable;
    }

    private Map<Element, Map<String, ProgramElement>> generateLocalSymbolTable(Map<Element, Map<String, ProgramElement>> symbolTable) {
        Map<Element, Map<String, ProgramElement>> localSymbolTable = new HashMap<>();
        for (Element e: Constants.PROGRAM_SYMBOLS)
            localSymbolTable.put(e, new HashMap<>(symbolTable.get(e)));
        return localSymbolTable;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {

        String HTMLFor = "<p><SPAN CLASS=\"ident\">for</SPAN> (" + this.indexVariable.toHTML() + "; " + this.conditionStop.toHTML() + "; " + this.assignationAfterIteration.toHTML() + ") {</p>\n";
        HTMLFor += this.toHTMLBrackets();

        return HTMLFor;

    }

}
