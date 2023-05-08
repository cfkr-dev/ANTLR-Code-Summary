import enums.Element;
import enums.Type;

public class Variable implements ProgramElement{

    private String name;
    private ProgrammableElement context;

    private Type type;

    private Element elementType;

    private String value;

    public Variable(String type, String name, ProgrammableElement context) {
        this.type = Type.valueOf(type.toUpperCase());
        this.elementType = Element.VARIABLE;
        this.name = name;
        this.context = context;
        this.value = null;
    }

    public String getName() {
        return name;
    }

    @Override
    public Element getElementType() {
        return this.elementType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProgramElement getContext() {
        return context;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value, String tokenType) {
        if (!this.context.hasThisSymbol(this.name)) {
            System.err.println("Can't assign " + value + " to " + this.name + " because it hasn't been previously declared");
            return;
        }

        if (!(Constants.checkTypeTokenType(this.type, tokenType))) {
            System.err.println("Can't assign \"" + value + "\" to " + this.type.name().toLowerCase() + " variable");
            return;
        }

        this.value = value;
        this.context.updateSymbolTable(this);
    }

    public void setValue(Variable variable) {
        if (!this.context.hasThisSymbol(this.name)) {
            System.err.println("Can't assign " + variable.name + " value to " + this.name + " because it hasn't been previously declared");
            return;
        }

        if (!this.type.equals(variable.type)) {
            System.err.println("Can't assign " + variable.name + " value (" + variable.type.name().toLowerCase() + ") to " + this.name + " (" + this.type.name().toLowerCase() + ") because types doesn't match");
            return;
        }

        this.value = variable.value;
        this.context.updateSymbolTable(this);
    }

    @Override
    public String toHTML() {
        return null;
    }
}
