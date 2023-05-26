package semantic.element.sentence.loop_sentence;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.sentence_master.MasterProgrammableSentence;
import semantic.utils.Constants;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;
import semantic.utils.enums.Type;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DoWhileLoop extends MasterProgrammableSentence {
    AssignableElement logicOperation;
    protected boolean errorOnCreation;

    public DoWhileLoop(ProgrammableElement context, int line, int column) {
        this.type = null;
        this.name = "DO_WHILE_LOOP";
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
    public String toHTML(int HTMLIndentationLevel, int codeIndentationLevel) {

        String HTMLDoWhile = "<p><SPAN CLASS=\"ident\">do</SPAN>  {</p>\n";

        HTMLDoWhile += this.toHTMLBrackets();
        HTMLDoWhile = HTMLDoWhile.substring(0, HTMLDoWhile.length() - 5) + " <SPAN CLASS=\"ident\">while</SPAN> (" + this.logicOperation.toHTML() + ");</p>\n";

        return HTMLDoWhile;

    }
}
