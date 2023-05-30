package semantic.element.sentence.constant_sentence;

import semantic.element.Constant;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.constant_sentence.constant_sentence_interface.ConstantSentence;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Sentence;

public class ConstantDefinition extends MasterSimpleSentence implements ConstantSentence {

    private Constant constant;
    public ConstantDefinition(Constant constant, ProgrammableElement context, int line, int column) {
        this.type = constant.getType();
        this.name = "CONST_DEF_" + line + "_" + column;
        this.elementType = Element.SENTENCE;
        this.sentenceType = Sentence.CONST_DEF;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = context.getAnchorContext() + ":" + this.name;
        this.constant = constant;
        this.line = line;
        this.column = column;
    }

    @Override
    public Constant getConstant() {
        return this.constant;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);
        return this.constant.toHTML(HTMLIndentationLevel) + ";" + HTMLHelper.genBr(tabs);
    }
}
