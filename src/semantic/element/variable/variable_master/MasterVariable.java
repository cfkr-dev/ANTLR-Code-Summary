package semantic.element.variable.variable_master;

import semantic.element.element_master.MasterProgramElement;
import semantic.element.variable.variable_interface.Variable;

public abstract class MasterVariable extends MasterProgramElement implements Variable {
    @Override
    public void forceSetMalformed() {
        this.malformed = true;
    }

}
