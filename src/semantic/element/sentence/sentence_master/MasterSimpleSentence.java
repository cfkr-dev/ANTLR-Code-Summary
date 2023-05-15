package semantic.element.sentence.sentence_master;

import semantic.element.sentence.sentence_interface.Sentence;
import semantic.element_master.MasterProgramElement;

public abstract class MasterSimpleSentence extends MasterProgramElement implements Sentence {
    protected semantic.enums.Sentence sentenceType;

    @Override
    public semantic.enums.Sentence getSentenceType() {
        return this.sentenceType;
    }
}
