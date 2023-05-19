package semantic.element;

import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_master.MasterProgrammableElement;
import semantic.element.literal.literal_master.Literal;
import semantic.element.variable.SimpleVariable;
import semantic.element.variable.StructVariable;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.Constants;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Program extends MasterProgrammableElement {

    private List<ProgramElement> programElements;

    public Program() {
        this.name = Constants.FILE_NAME;
        this.context = null;
        this.superContext = this;
        this.symbolTable = this.initializeSymbolTable();
        this.programElements = new LinkedList<>();
        this.malformed = false;
    }

    private Map<Element, Map<String, ProgramElement>> initializeSymbolTable () {
        Map<Element, Map<String, ProgramElement>> symbolTable = new HashMap<>();
        for (Element e: Constants.PROGRAM_SYMBOLS)
            symbolTable.put(e, new HashMap<>());
        return symbolTable;
    }

    public Variable createNewVariable(String type, String name) {
        Variable variable = super.createNewVariable(type, name);
        if (variable != null)
            this.programElements.add(variable);
        else {
            if (!Type.valueOf(type.toUpperCase()).equals(Type.STRUCT)) {
                variable = new SimpleVariable(type, name, this);
            } else {
                variable = new StructVariable(type, this);
            }
            variable.setMalformed();
        }
        return variable;
    }

    public Function createNewFunction(String type, String name) {
        if (!this.hasThisSymbol(name)) {
            Function function = new Function(type, name, this);
            this.addToSymbolTable(function);
            this.programElements.add(function);
            return function;
        } else {
            System.err.println("This element has been previously declared");
            Function function = new Function(type, name, this);
            function.setMalformed();
            return function;
        }
    }

    public Function createNewMainFunction() {
        return this.createNewFunction("void", "Main");
    }

    public Constant createNewConstant(String name, Literal value) {
        if (!this.hasThisSymbol(name)) {
            Constant constant = new Constant(name, value, this);
            this.addToSymbolTable(constant);
            this.programElements.add(constant);
            return constant;
        } else {
            System.err.println("This element has been previously declared");
            Constant constant = new Constant(name, value, this);
            constant.setMalformed();
            return constant;
        }
    }

    @Override
    public String toHTML() {
        return null;
    }
}
