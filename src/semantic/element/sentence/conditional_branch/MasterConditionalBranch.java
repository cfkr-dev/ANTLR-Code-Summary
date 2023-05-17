package semantic.element.sentence.conditional_branch;

import semantic.element.sentence.sentence_master.MasterProgrammableSentence;

public abstract class MasterConditionalBranch extends MasterProgrammableSentence implements ConditionalBranch {

    protected ConditionalBranch previous;

    public ConditionalBranch getPreviousBranch() {
        return this.previous;
    }
}
