package semantic.element.sentence.sentence_master;

import semantic.element.sentence.sentence_interface.Sentence;

public abstract class MasterProgrammableSentence extends MasterSentenceContainer implements Sentence {
    protected semantic.enums.Sentence sentenceType;

    @Override
    public semantic.enums.Sentence getSentenceType() {
        return this.sentenceType;
    }

}
