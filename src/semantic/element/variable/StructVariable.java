package semantic.element.variable;

import semantic.element.literal.NumericIntegerConstant;
import semantic.element.literal.NumericRealConstant;
import semantic.element.literal.StringConstant;
import semantic.element.variable.variable_interface.Variable;
import semantic.element.variable.variable_master.MasterVariable;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructVariable extends MasterVariable<Variable> implements ProgrammableElement<Variable>{

    protected Map<Element, Map<String, Variable>> symbolTable;
    protected List<Variable<? extends AssignableElement>> properties;
    protected boolean errorOnCreation;
    private boolean hasParenthesis;

    public StructVariable(ProgrammableElement context, int line, int column) {
        this.type = Type.STRUCT;
        this.elementType = Element.VARIABLE;
        this.name = null;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.symbolTable = initializeSymbolTable();
        this.properties = new ArrayList<>();
        this.errorOnCreation = false;
        this.malformed = true;
        this.line = line;
        this.column = column;
        this.hasParenthesis = false;
    }

    @Override
    public AssignableElement setParenthesis() {
        return this;
    }

    public StructVariable createStruct(String name) {
        if (this.errorOnCreation) {
            this.setMalformed();
        } else {
            this.malformed = false;
        }

        this.name = name;
        this.context.addToSymbolTable(this);
        return this;
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

    public StructVariable addNewSimpleProperty(String type, String name, int line, int column) {
        if (!this.hasThisSymbol(name)) {
            if (!Type.valueOf(type.toUpperCase()).equals(Type.STRUCT)){
                SimpleVariable simpleVariable = new SimpleVariable(type, name, this, line, column);
                this.addToSymbolTable(simpleVariable);
                this.properties.add(simpleVariable);
                return this;
            }
        }
        System.err.println("ERROR " + line + ":" + column + " => " + "Este elemento ya ha sido declarado anteriormente con el mismo nombre (" + name + ")");
        this.errorOnCreation = true;
        return this;
    }

    public StructVariable addNewSimpleProperty(String type, String name, AssignableElement value, int line, int column) {
        if (!this.hasThisSymbol(name)) {
            if (!Type.valueOf(type.toUpperCase()).equals(Type.STRUCT)) {
                SimpleVariable simpleVariable = new SimpleVariable(type, name, this, line, column);
                this.addToSymbolTable(simpleVariable);
                simpleVariable.setValue(value, this, line, column);
                this.properties.add(simpleVariable);
                return this;
            }
        }
        System.err.println("ERROR " + line + ":" + column + " => " + "Este elemento ya ha sido declarado anteriormente con el mismo nombre (" + name + ")");
        this.errorOnCreation = true;
        return this;
    }

    public StructVariable addNewNestedStructProperty(String name, int line, int column) {
        if (!this.hasThisSymbol(name)) {
            StructVariable structVariable = new StructVariable(this, line, column);
            this.properties.add(structVariable);
            return structVariable;
        }
        StructVariable structVariable = new StructVariable(this, line, column);
        this.errorOnCreation = true;
        System.err.println("ERROR " + line + ":" + column + " => " + "Este elemento ya ha sido declarado anteriormente con el mismo nombre (" + name + ")");
        return structVariable;
    }

    @Override
    public boolean setValue(Variable assignableElement, ProgrammableElement context, int line, int column) {
        if (this.malformed) {
            this.setMalformed();
            return false;
        }

        if (assignableElement.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada");
            this.setMalformed();
            return false;
        }

        if (assignableElement instanceof StructVariable) {
            this.name = assignableElement.getName();
            this.context = assignableElement.getContext();
            this.superContext = assignableElement.getSuperContext();
            this.symbolTable = ((StructVariable) assignableElement).getSymbolTable();
            this.properties = ((StructVariable) assignableElement).getProperties();
            this.line = assignableElement.getLine();
            this.column = assignableElement.getColumn();
            return true;
        } else {
            System.err.println("ERROR " + line + ":" + column + " => " + "Una variable de tipo struct solo puede ser asignada con otra variable de tipo struct");
            this.setMalformed();
            return false;
        }
    }

    public void forceSetValue(AssignableElement assignableElement) {
        if (assignableElement instanceof StructVariable) {
            this.name = assignableElement.getName();
            this.context = assignableElement.getContext();
            this.superContext = assignableElement.getSuperContext();
            this.symbolTable = ((StructVariable) assignableElement).getSymbolTable();
            this.properties = ((StructVariable) assignableElement).getProperties();
            this.line = assignableElement.getLine();
            this.column = assignableElement.getColumn();
        }
    }

    public AssignableElement getValue() {
        return this;
    }

    @Override
    public Variable variableClone() {
        StructVariable structVariable = new StructVariable(this.context, this.line, this.column).createStruct(this.name);
        if (this.malformed)
            structVariable.forceSetMalformed();
        return structVariable;
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

    @Override
    public NumericIntegerConstant newIntegerConstant(String value, int line, int column) {
        return new NumericIntegerConstant(value, this, line, column);
    }
    @Override
    public NumericRealConstant newRealConstant(String value, int line, int column) {
        return new NumericRealConstant(value, this, line, column);
    }
    @Override
    public StringConstant newStringConstant(String value, int line, int column) {
        return new StringConstant(value, this, line, column);
    }

    /**
     * Se delega el comportamiento a el método ".addNewSimpleProperty()"
     */
    @Override
    public Variable createNewVariable(String type, String name, int line, int column) {
        return this.addNewSimpleProperty(type, name, line, column);
    }

    @Override
    public StructVariable createNewVariable(String type, int line, int column) {
        if (Type.valueOf(type.toUpperCase()).equals(Type.STRUCT))
            return this.addNewNestedStructProperty(type, line, column);
        else return null;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        String tabs = HTMLHelper.generateTabulations(HTMLIndentationLevel);

        StringBuilder HTMLStruct = new StringBuilder();
        StringBuilder HTMLProperties = generatePropertiesList(this.properties, HTMLIndentationLevel + 1);

        return HTMLStruct
            .append(tabs).append("struct <br/>\n")
            .append(tabs).append("{ <br/>\n")
            .append(tabs).append("<div>\n")
                .append(HTMLProperties)
            .append(tabs).append("</div>\n")
            .append(tabs).append("} ").append(this.name).append("; <br/>\n")
            .toString();
    }

    private StringBuilder generatePropertiesList(List<Variable<? extends AssignableElement>> properties, int HTMLIndentationLevel) {
        StringBuilder HTMLProperties = new StringBuilder();

        for (Variable property: properties) {
            HTMLProperties
                .append(property.toHTML(HTMLIndentationLevel));
        }

        return HTMLProperties;
    }
}
