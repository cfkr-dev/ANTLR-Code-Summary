import enums.Element;
import enums.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program implements ProgrammableElement{

    private String name;
    private ProgramElement context;
    private Map<Element, Map<String, ProgramElement>> symbolTable;

    public Program() {
        this.name = Constants.FILE_NAME;
        this.context = null;
        this.symbolTable = this.initializeSymbolTable();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public Element getElementType() {
        return null;
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

    public Map<Element, Map<String, ProgramElement>> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(Map<Element, Map<String, ProgramElement>> symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public String toHTML() {
        return null;
    }

    private Map<Element, Map<String, ProgramElement>> initializeSymbolTable () {
        Map<Element, Map<String, ProgramElement>> symbolTable = new HashMap<>();
        for (Element e: Element.values())
            symbolTable.put(e, new HashMap<>());
        return symbolTable;
    }

    @Override
    public void addToSymbolTable(ProgramElement element) {
        this.symbolTable.get(element.getElementType()).put(element.getName(), element);
    }

    @Override
    public void updateSymbolTable(ProgramElement element) {
        this.symbolTable.get(element.getElementType()).replace(element.getName(), element);
    }

    @Override
    public boolean hasThisSymbol(String name) {
        for (Element e: Element.values()){
            if (this.symbolTable.get(e).containsKey(name))
                return true;
        }
        return false;
    }

    @Override
    public Variable createNewVariable(String type, String name) {
        if (!this.hasThisSymbol(name)) {
            Variable variable = new Variable(type, name, this);
            this.addToSymbolTable(variable);
            return variable;
        } else {
            System.err.println("This element has been previously declared");
            return null;
        }
    }

    public Function createNewFunction(String type, String name, List<Param> params) {
        if (!this.hasThisSymbol(name)) {
            Function function = new Function(type, name, this, params);
            this.addToSymbolTable(function);
            return function;
        } else {
            System.err.println("This element has been previously declared");
            return null;
        }
    }

    @Override
    public ProgramElement getSymbolByNameAndElement(String name, Element element) {
        return this.symbolTable.get(element).get(name);
    }
}
