package semantic.element.sentence.function_sentence;

import semantic.element.Function;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

public class ReturnPoint extends MasterSimpleSentence {

    private Function targetFunction;
    private AssignableElement returnElement;

    public ReturnPoint(Function targetFunction, AssignableElement returnElement, ProgrammableElement context, int line, int column) {
        this.type = returnElement.getType();
        this.name = "RETURN_" + line + "_" + column;
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.RETURN;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.targetFunction = targetFunction;
        this.returnElement = returnElement;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel, String anchorContext) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);

        return new StringBuilder()
            .append(tabs)
            .append(HTMLHelper.genSpan("palres", "return"))
            .append(" ")
            .append(this.returnElement.toHTML(HTMLIndentationLevel, anchorContext))
            .append(";")
            .append(HTMLHelper.genBr(tabs))
            .toString();
    }
}
