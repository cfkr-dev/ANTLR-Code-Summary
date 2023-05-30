package semantic.element.sentence.variable_sentence.variable_sentence_interface;

import semantic.element.sentence.sentence_interface.Sentence;
import semantic.element.variable.variable_interface.Variable;

public interface VariableSentence extends Sentence {
    public Variable getVariable();

    public String toHTMLNoWhiteSpaces();
}
