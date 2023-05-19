package semantic.element.sentence.literal;

import semantic.element.sentence.literal.literal_master.Literal;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

public class NumericIntegerConstant extends Literal {

    public NumericIntegerConstant(String value, ProgrammableElement context) {
        this.value = value;
        this.type = Type.INTEGER;
        this.name = "INT_CONST_" + value;
        this.elementType = Element.LITERAL;
        this.context = context;
        this.superContext = context.getSuperContext();

    }

    @Override
    public String toHTML() {
        return null;
    }
}
