package semantic.element.variable;

import semantic.element.Constant;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.literal.NumericIntegerConstant;
import semantic.element.literal.NumericRealConstant;
import semantic.element.literal.StringConstant;
import semantic.element.variable.variable_interface.Variable;
import semantic.element.variable.variable_master.MasterVariable;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructVariable extends MasterVariable implements ProgrammableElement<Variable>{

    protected Map<Element, Map<String, Variable>> symbolTable;
    protected List<Variable> properties;
    protected boolean errorOnCreation;

    protected AssignableElement value;

    public StructVariable(ProgrammableElement context, int line, int column) {
        this.type = Type.STRUCT;
        this.elementType = Element.VARIABLE;
        this.name = null;
        this.context = context;
        this.superContext = context.getSuperContext();
        this.anchorContext = context.getAnchorContext() + ":";
        this.symbolTable = initializeSymbolTable();
        this.properties = new ArrayList<>();
        this.errorOnCreation = false;
        this.value = null;
        this.malformed = true;
        this.line = line;
        this.column = column;
    }

    public StructVariable createStruct(String name) {
        if (this.errorOnCreation) {
            this.setMalformed();
        } else {
            this.malformed = false;
        }

        this.name = name;
        this.anchorContext += name;

        if (!this.context.hasThisSymbol(name)) {
            this.context.addToSymbolTable(this);
        } else if (this.context.getSymbolByNameAndElement(name, Element.VARIABLE).getContext() != this.context) {
            this.context.updateSymbolTable(this);
        } else {
            this.setMalformed();
            System.err.println("ERROR " + this.line + ":" + this.column + " => " + "Este elemento ya ha sido declarado anteriormente con el mismo nombre (" + name + ")");
        }

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

    public StructVariable addNewNestedStructProperty(int line, int column) {
        StructVariable structVariable = new StructVariable(this, line, column);
        this.properties.add(structVariable);
        return structVariable;
    }

    @Override
    public AssignableElement getValue() {
        return this.value;
    }

    @Override
    public boolean setValue(AssignableElement assignableElement, ProgrammableElement context, int line, int column) {
        if (this.properties.size() == 0)
            this.malformed = false;

        if (this.malformed) {
            this.setMalformed();
            return false;
        }

        if (assignableElement.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada");
            this.setMalformed();
            return false;
        }

        if (!context.hasThisSymbol(this.name)) {
            if (!(assignableElement.getValue() instanceof Variable || assignableElement.getValue() instanceof Constant)) {
                System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar " + assignableElement.toString() + " a " + this.name + " por que " + this.name + " no ha sido declarado previamente");
                this.setMalformed();
                return false;
            }

            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar " + assignableElement.toString() + " a " + this.name + " por que " + this.name + " no ha sido declarado previamente");
            this.setMalformed();
            return false;
        }

        if (assignableElement.getValue() instanceof StructVariable) {

            if (!context.hasThisSymbol(assignableElement.getValue().getName())) {
                System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar " + assignableElement.toString() + " a " + this.name + " por que " + assignableElement.toString() + " no ha sido declarado previamente");
                this.setMalformed();
                return false;
            }
        }

        if (!Type.checkTypeConsistency(this.type, assignableElement, true)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar un elemento de tipo \"" + assignableElement.getType() + "\" a una variable de tipo " + this.type.name().toLowerCase());
            this.setMalformed();
            return false;
        }

        this.value = assignableElement;
        context.updateSymbolTable(this);
        return true;
    }

    public void forceSetValue(AssignableElement assignableElement) {
        if (assignableElement instanceof StructVariable) {
            this.name = assignableElement.getName();
            this.context = assignableElement.getContext();
            this.superContext = assignableElement.getSuperContext();
            this.anchorContext = assignableElement.getAnchorContext();
            this.symbolTable = ((StructVariable) assignableElement).getSymbolTable();
            this.properties = ((StructVariable) assignableElement).getProperties();
            this.line = assignableElement.getLine();
            this.column = assignableElement.getColumn();
        }
    }

    @Override
    public Variable variableClone() {
        StructVariable structVariable = new StructVariable(this.context, this.line, this.column).createStruct(this.name);
        if (this.malformed)
            structVariable.forceSetMalformed();
        return structVariable;
    }

    @Override
    public Variable variableClone(String name) {
        StructVariable structVariable = new StructVariable(this.context, this.line, this.column).createStruct(name);
        if (this.malformed)
            structVariable.forceSetMalformed();
        return structVariable;
    }

    public List<Variable> getProperties() {
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
            return this.addNewNestedStructProperty(line, column);
        else return null;
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);

        StringBuilder HTMLStruct = new StringBuilder();

        if (this.value != null)
            return HTMLStruct
                .append(tabs).append(HTMLHelper.genA(this.anchorContext)).append("\n")
                .append(tabs)
                .append(HTMLHelper.genSpan("palres", this.getType().name().toLowerCase()))
                .append(" ")
                .append(HTMLHelper.genSpan("ident", this.getName()))
                .append(HTMLHelper.genSpan("palres", " = "))
                .append(this.value.toHTML(HTMLIndentationLevel))
                .toString();

        StringBuilder HTMLProperties = generatePropertiesList(this.properties, HTMLIndentationLevel + 1, anchorContext + ":" + this.name);

        return HTMLStruct
            .append(tabs).append(HTMLHelper.genA(this.anchorContext)).append("\n")
            .append(tabs).append(HTMLHelper.genSpan("palres", "struct"))
            .append(HTMLHelper.genBr(tabs))
            .append(tabs).append("{")
            .append(HTMLHelper.genBr(tabs))
            .append(tabs).append("<div>\n")
            .append(HTMLProperties)
            .append(tabs).append("</div>\n\n")
            .append(tabs).append("} ")
            .append(HTMLHelper.genSpan("ident", this.name))
            .toString();
    }

    private StringBuilder generatePropertiesList(List<Variable> properties, int HTMLIndentationLevel, String anchorContext) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);
        StringBuilder HTMLProperties = new StringBuilder();

        for (Variable property: properties) {
            property.forceChangeContext(this, this.anchorContext + ":" + property.getName());
            HTMLProperties
                .append(property.toHTML(HTMLIndentationLevel)).append(";")
                .append(HTMLHelper.genBr(tabs));
        }

        return HTMLProperties;
    }
}
