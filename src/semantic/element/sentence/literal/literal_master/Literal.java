package semantic.element.sentence.literal.literal_master;

import semantic.element.sentence.sentence_interface.AssignableElement;
import semantic.element_master.MasterProgramElement;

public abstract class Literal extends MasterProgramElement implements AssignableElement {
    protected String value;

    @Override
    public String getLiteralValue() {
        return value;
    }
}
