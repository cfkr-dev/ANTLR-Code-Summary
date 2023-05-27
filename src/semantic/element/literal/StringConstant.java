package semantic.element.literal;

import semantic.element.literal.literal_master.Literal;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

public class StringConstant extends Literal {

    public StringConstant(String value, ProgrammableElement context, int line, int column) {
        this.value = value;
        this.type = Type.STRING;
        this.name = "STRING_CONST_" + value;
        this.elementType = Element.LITERAL;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.malformed = false;
        this.line = line;
        this.column = column;
        this.hasParenthesis = false;
    }

    @Override
     public String toHTML(int HTMLIndentationLevel) {

        return "<SPAN CLASS=\"cte\">\"" + value + "\"</SPAN>";

    }

}
