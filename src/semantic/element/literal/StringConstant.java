package semantic.element.literal;

import semantic.HTMLToolKit;
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
    }

    @Override
     public String toHTML() {

        return HTMLToolKit.cteMaker("\"" + value + "\"");

    }

    // TODO: hay que poner el tipo de comillas que se usa: simples o dobles
    @Override
    public String toHTMLLong() {
        return this.toHTML();
    }

}
