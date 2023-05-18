package semantic.element;

import semantic.element.sentence.literal.literal_master.Literal;
import semantic.element.variable.SimpleVariable;
import semantic.element.variable.StructVariable;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_master.MasterProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.Constants;
import semantic.utils.Param;

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
    }

    private Map<Element, Map<String, ProgramElement>> initializeSymbolTable () {
        Map<Element, Map<String, ProgramElement>> symbolTable = new HashMap<>();
        for (Element e: Constants.PROGRAM_SYMBOLS)
            symbolTable.put(e, new HashMap<>());
        return symbolTable;
    }

    public SimpleVariable createNewVariable(String type, String name) {
        SimpleVariable variable = (SimpleVariable) super.createNewVariable(type, name);
        if (variable != null)
            this.programElements.add(variable);
        return variable;
    }

    public Function createNewFunction(String type, String name, List<Param> params) {
        if (!this.hasThisSymbol(name)) {
            Function function = new Function(type, name, this, params);
            this.addToSymbolTable(function);
            this.programElements.add(function);
            return function;
        } else {
            System.err.println("This element has been previously declared");
            return null;
        }
    }

    public Function createNewMainFunction(List<Param> params) {
        return this.createNewFunction("void", "Main", params);
    }

    public Constant createNewConstant(String name, Literal value) {
        if (!this.hasThisSymbol(name)) {
            Constant constant = new Constant(name, value, this);
            this.addToSymbolTable(constant);
            this.programElements.add(constant);
            return constant;
        } else {
            System.err.println("This element has been previously declared");
            return null;
        }
    }

    public StructVariable createNewStruct(String name) {
        if (!this.hasThisSymbol(name)) {
            StructVariable struct = new StructVariable(name, this);
            this.addToSymbolTable(struct);
            this.programElements.add(struct);
            return struct;
        } else {
            System.err.println("This element has been previously declared");
            return null;
        }
    }

    @Override
    public String toHTML() {
        return null;
    }
}
