package semantic.element.sentence.literal;

import semantic.element.sentence.literal.literal_master.Literal;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

public class StringConstant extends Literal {

    public StringConstant(String value, ProgrammableElement context) {
        this.value = value;
        this.type = Type.STRING;
        this.name = "STRING_CONST_" + value;
        this.elementType = Element.LITERAL;
        this.context = context;
        this.superContext = context.getSuperContext();
    }

    @Override
     public String toHTML() {

        return "<SPAN CLASS=\"cte\">\"" + value + "\"</SPAN>";

    }

}
