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
            }
        }
        System.err.println("This element has been previously declared");
        return this;
    }

    public StructVariable addNewSimpleProperty(String type, String name, AssignableElement value) {
        if (!this.hasThisSymbol(name)) {
            if (!Type.valueOf(type.toUpperCase()).equals(Type.STRUCT)) {
                SimpleVariable simpleVariable = new SimpleVariable(type, name, this);
                simpleVariable.setValue(value);
                this.addToSymbolTable(simpleVariable);
                this.properties.add(simpleVariable);
            }
        }
        System.err.println("This element has been previously declared");
        return this;
    }

    public StructVariable addNewNestedStructProperty(String name) {
        if (!this.hasThisSymbol(name)) {
            StructVariable structVariable = new StructVariable(name, this);
            this.addToSymbolTable(structVariable);
            this.properties.add(structVariable);
            return structVariable;
        }
        System.err.println("This element has been previously declared");
        return null;

    }

    /**
     * Unsupported Method
     */
    @Override
    public boolean setValue(Variable assignableElement) {
        System.err.println("Unsupported");
        return false;
    }

    public String getValue() {
        return this.toString();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("struct {");
        for (Variable property: this.properties) {
            s.append(property.getValue());
        }
        return s.append("} ").append(this.name).toString();
    }

    /**
     * Deprecated
     */
    @Override
    public Variable createNewVariable(String type, String name) {
        return this.addNewSimpleProperty(type, name);
    }

    @Override
    public String toHTML() {

        String HTMLStruct = new String();

        HTMLStruct = "<p>struct{</p>\n";

        HTMLStruct = "\t<DIV style=\"text-indent: 2cm\"><p>\n";

        for (Variable<? extends AssignableElement> element : this.properties) {

            HTMLStruct += element.toHTML().replace("\n", "\n\t\t");

        }

        HTMLStruct += "\t</DIV>\n";
        HTMLStruct += "<p>} " + this.name + ";</p>\n";

        return HTMLStruct;
    }
}
