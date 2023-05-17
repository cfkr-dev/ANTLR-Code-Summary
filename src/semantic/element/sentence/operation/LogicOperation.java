package semantic.element.sentence.operation;

import semantic.element.sentence.operation.operation_master.MasterOperation;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Type;

public class LogicOperation extends MasterOperation {
    public LogicOperation(String exp, ProgrammableElement context) {
        this.type = Type.ANY;
        this.name = "LOGICAL_OP_" + exp;
        this.elementType = Element.LOGICAL_OPERATION;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.expression = exp;
    }

    @Override
    public String toHTML() {
        return null;
    }
}
