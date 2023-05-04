import enums.Element;
import enums.Type;

import java.util.Map;

public class Variable<E> implements ProgramElement{

    private String name;
    private ProgramElement context;

    private Type type;

    private E value;

    public Variable(String type, String name, ProgramElement context) {
        this.type = Type.valueOf(type.toUpperCase());
        this.name = name;
        this.context = context;
        this.value = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProgramElement getContext() {
        return context;
    }

    public void setContext(ProgramElement context) {
        this.context = context;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        if (value.getClass().getName().equals(this.type.name()))
            this.value = value;
    }

    @Override
    public String toHTML() {
        return null;
    }
}
