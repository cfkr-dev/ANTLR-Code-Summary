package semantic.element.struct.master_struct;

import semantic.element.Variable;
import semantic.element.sentence.literal.literal_master.Literal;
import semantic.element.struct.Struct;
import semantic.element_interfaces.AssignableElement;
import semantic.element_interfaces.StructableElement;
import semantic.element_master.MasterProgramElement;
import semantic.enums.Element;
import semantic.enums.Type;
import semantic.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Master_struct extends MasterProgramElement implements AssignableElement, StructableElement {

    protected Map<Element, Map<String, StructableElement>> symbolTable;
    protected List<StructableElement> properties;
    protected final Type type = Type.STRUCT;
    protected final Element elementType = Element.STRUCT;

    protected Map<Element, Map<String, StructableElement>> initializeSymbolTable() {
        Map<Element, Map<String, StructableElement>> symbolTable = new HashMap<>();
        for (Element e: Constants.STRUCT_SYMBOLS)
            symbolTable.put(e, new HashMap<>());
        return symbolTable;
    }

    void addToSymbolTable(StructableElement element) {
        this.symbolTable.get(element.getElementType()).put(element.getName(), element);
    }

    boolean hasThisSymbol(String name) {
        for (Element e: Constants.STRUCT_SYMBOLS){
            if (this.symbolTable.get(e).containsKey(name))
                return true;
        }
        return false;
    }

    StructableElement createNewProperty(String type, String name) {
        if (!this.hasThisSymbol(name)) {
                Variable variable = new Variable(type, name, this.context);
                this.addToSymbolTable(variable);
                return variable;
        } else {
            System.err.println("This element has been previously declared");
            return null;
        }
    }

    StructableElement createNewProperty(String type, String name, Literal value) {
        if (!this.hasThisSymbol(name)) {
            Variable variable = new Variable(type, name, this.context);
            variable.setValue(value);
            this.addToSymbolTable(variable);
            return variable;
        } else {
            System.err.println("This element has been previously declared");
            return null;
        }
    }

    StructableElement createNewProperty(String name) {
        if (!this.hasThisSymbol(name)) {
            Struct struct = new Struct(name, this.context, (Struct) this);
            this.addToSymbolTable(struct);
            return struct;
        } else {
            System.err.println("This element has been previously declared");
            return null;
        }
    }

    @Override
    public String getLiteralValue() {
        return "STRUCT_" + this.name;
    }

    @Override
    public abstract String toHTML();
}
