package semantic.element.sentence.literal;

import semantic.element.sentence.literal.literal_master.MasterLiteral;
import semantic.element_interfaces.ProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Type;

public class StringConstant extends MasterLiteral {

    public StringConstant(String value, ProgrammableElement context) {
        this.value = value;
        this.type = Type.STRING;
        this.name = "STRING_CONST_" + value;
        this.elementType = Element.LITERAL;
        this.context = context;
    }

    @Override
    public String toHTML() {
        return null;
    }
}
