package semantic.element.sentence.variable_sentence;

import semantic.HTMLToolKit;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element.variable.SimpleVariable;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

public class VariableAssignation extends MasterSimpleSentence {
    private Variable variable;

    public VariableAssignation(Variable variable, ProgrammableElement context, int line, int column) {
        this.type = variable.getType();
        this.name = variable.getName() + "_ASSIG";
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.ASSIG;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.variable = variable;
        this.line = line;
        this.column = column;
    }

    public Variable getVariable() {
        return variable;
    }

    //TODO: hacer algo con la parte derecha del = que tambien acepte structs
    @Override
    public String toHTML() {
        return HTMLToolKit.identRefMaker(this.variable.toHTMLContex(), this.variable.getName()) + " = " + ((SimpleVariable)this.variable).toHTMLValue();
    }

    public String toHTMLLong() {
        return this.toHTML() + ";<br>\n";
    }
}
