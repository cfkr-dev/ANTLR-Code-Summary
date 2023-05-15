package semantic.element.sentence.literal;

import semantic.element.sentence.literal.literal_master.MasterLiteral;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Type;

public class NumericIntegerConstant extends MasterLiteral {

    public NumericIntegerConstant(String value, ProgrammableElement context) {
        this.value = value;
        this.type = Type.INTEGER;
        this.name = "INT_CONST_" + value;
        this.elementType = Element.LITERAL;
        this.context = context;
    }

    @Override
    public String toHTML() {
        return null;
    }
}
