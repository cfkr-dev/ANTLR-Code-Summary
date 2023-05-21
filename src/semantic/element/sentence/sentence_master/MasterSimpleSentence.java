package semantic.element.sentence.sentence_master;

import semantic.element.sentence.sentence_interface.Sentence;
import semantic.element.element_master.MasterProgramElement;

public abstract class MasterSimpleSentence extends MasterProgramElement implements Sentence {
    protected semantic.utils.enums.Sentence sentenceType;

    @Override
    public semantic.utils.enums.Sentence getSentenceType() {
        return this.sentenceType;
    }
}
