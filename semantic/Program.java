import enums.Element;

import java.util.HashMap;
import java.util.Map;

public class Program implements ProgramElement{

    private String name;
    private ProgramElement context;
    private Map<Element, Map<String, ProgramElement>> symbolTable;

    public Program() {
        this.name = Constants.FILE_NAME;
        this.context = null;
        this.symbolTable = this.initializeSymbolTable();
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
}
