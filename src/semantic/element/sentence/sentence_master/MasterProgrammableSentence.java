package semantic.element.sentence.sentence_master;

import semantic.element.sentence.sentence_interface.Sentence;

import java.util.List;

public abstract class MasterProgrammableSentence extends MasterSentenceContainer implements Sentence {
    protected List<Sentence> sentences;
    protected semantic.enums.Sentence sentenceType;

    @Override
    public semantic.enums.Sentence getSentenceType() {
        return this.sentenceType;
    }

}
