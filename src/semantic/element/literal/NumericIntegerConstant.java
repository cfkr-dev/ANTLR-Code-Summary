package semantic.element.literal;

import semantic.element.literal.literal_master.Literal;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

public class NumericIntegerConstant extends Literal {

    public NumericIntegerConstant(String value, ProgrammableElement context, int line, int column) {
        this.value = value;
        this.type = Type.INTEGER;
        this.name = "INT_CONST_" + line + "_" + column;
        this.elementType = Element.LITERAL;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = context.getAnchorContext() + ":" + this.name;
        this.malformed = false;
        this.line = line;
        this.column = column;
        this.hasParenthesis = false;
    }

}
