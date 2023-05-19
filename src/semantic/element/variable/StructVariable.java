package semantic.element.variable;

import semantic.element.variable.variable_interface.Variable;
import semantic.element.variable.variable_master.MasterVariable;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructVariable extends MasterVariable<Variable> implements ProgrammableElement<Variable>{

    protected Map<Element, Map<String, Variable>> symbolTable;
    protected List<Variable<? extends AssignableElement>> properties;

    public StructVariable(String name, ProgrammableElement context) {
        this.type = Type.STRUCT;
        this.elementType = Element.VARIABLE;
        this.name = name;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.symbolTable = initializeSymbolTable();
        this.properties = new ArrayList<>();
        this.malformed = false;
    }

    protected Map<Element, Map<String, Variable>> initializeSymbolTable() {
        Map<Element, Map<String, Variable>> symbolTable = new HashMap<>();
        symbolTable.put(Element.VARIABLE, new HashMap<>());
        return symbolTable;
    }

    @Override
    public void addToSymbolTable(Variable element) {
        this.symbolTable.get(element.getElementType()).put(element.getName(), element);
    }

    @Override
    public void updateSymbolTable(Variable element) {
        if (element instanceof StructVariable)
            this.symbolTable = ((StructVariable) element).getSymbolTable();
    }

    @Override
    public Map<Element, Map<String, Variable>> getSymbolTable() {
        return this.symbolTable;
    }

    @Override
    public boolean hasThisSymbol(String name) {
        return this.symbolTable.get(Element.VARIABLE).containsKey(name);
    }

    @Override
    public ProgramElement getSymbolByNameAndElement(String name, Element element) {
        return this.symbolTable.get(element).get(name);
    }

    public StructVariable addNewSimpleProperty(String type, String name) {
        if (!this.hasThisSymbol(name)) {
            if (!Type.valueOf(type.toUpperCase()).equals(Type.STRUCT)){
                SimpleVariable simpleVariable = new SimpleVariable(type, name, this);
                this.addToSymbolTable(simpleVariable);
                this.properties.add(simpleVariable);
                return this;
            }
        }
        System.err.println("Este elemento ya ha sido declarado anteriormente con el mismo nombre (" + name + ")");
        this.setMalformed();
        return this;
    }

    public StructVariable addNewSimpleProperty(String type, String name, AssignableElement value) {
        if (!this.hasThisSymbol(name)) {
            if (!Type.valueOf(type.toUpperCase()).equals(Type.STRUCT)) {
                SimpleVariable simpleVariable = new SimpleVariable(type, name, this);
                simpleVariable.setValue(value, this);
                this.addToSymbolTable(simpleVariable);
                this.properties.add(simpleVariable);
            }
        }
        System.err.println("Este elemento ya ha sido declarado anteriormente con el mismo nombre (" + name + ")");
        this.setMalformed();
        return this;
    }

    public StructVariable addNewNestedStructProperty(String name) {
        if (!this.hasThisSymbol(name)) {
            StructVariable structVariable = new StructVariable(name, this);
            this.addToSymbolTable(structVariable);
            this.properties.add(structVariable);
            return structVariable;
        }
        StructVariable structVariable = new StructVariable(name, this);
        structVariable.setMalformed();
        System.err.println("Este elemento ya ha sido declarado anteriormente con el mismo nombre (" + name + ")");
        return structVariable;

    }

    @Override
    public boolean setValue(Variable assignableElement, ProgrammableElement context) {
        if (this.malformed)
            return false;

        if (assignableElement.isMalformed()) {
            System.err.println("No se puede asignar una expresión malformada");
            this.setMalformed();
            return false;
        }

        if (assignableElement instanceof StructVariable) {
            this.name = assignableElement.getName();
            this.context = assignableElement.getContext();
            this.superContext = assignableElement.getSuperContext();
            this.symbolTable = ((StructVariable) assignableElement).getSymbolTable();
            this.properties = ((StructVariable) assignableElement).getProperties();
            return true;
        } else {
            System.err.println("Una variable de tipo struct solo puede ser asignada con otra variable de tipo struct");
            this.setMalformed();
            return false;
        }
    }

    public String getValue() {
        return this.name;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("struct {");
        for (Variable property: this.properties) {
            s.append(property.getValue());
        }
        return s.append("} ").append(this.name).toString();
    }

    public List<Variable<? extends AssignableElement>> getProperties() {
        return properties;
    }

    /**
     * Se delega el comportamiento a el método ".addNewSimpleProperty()"
     */
    @Override
    public Variable createNewVariable(String type, String name) {
        return this.addNewSimpleProperty(type, name);
    }

    @Override
    public String toHTML() {
        return null;
    }
}
