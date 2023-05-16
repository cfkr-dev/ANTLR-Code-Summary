package semantic.element.struct;

import semantic.element.struct.master_struct.Master_struct;
import semantic.element_interfaces.ProgrammableElement;

import java.util.ArrayList;

public class Struct extends Master_struct {

    private Struct structContext;

    public Struct(String name, ProgrammableElement context, Struct structContext) {
        this.name = name;
        this.properties = new ArrayList<>();
        this.context = context;
        this.superContext = context.getSuperContext();
        this.structContext = structContext;
        this.symbolTable = this.initializeSymbolTable();
    }

    public Struct getStructContext() {
        return structContext;
    }

    @Override
    public String toHTML() {
        return null;
    }

}
