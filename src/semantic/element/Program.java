package semantic.element;

import semantic.enums.Element;
import semantic.element_interfaces.ProgramElement;
import semantic.element_master.MasterProgrammableElement;
import semantic.utils.Constants;
import semantic.utils.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program extends MasterProgrammableElement {

    public Program() {
        this.name = Constants.FILE_NAME;
        this.context = null;
        this.symbolTable = this.initializeSymbolTable();
    }

    private Map<Element, Map<String, ProgramElement>> initializeSymbolTable () {
        Map<Element, Map<String, ProgramElement>> symbolTable = new HashMap<>();
        for (Element e: Element.values())
            symbolTable.put(e, new HashMap<>());
        return symbolTable;
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
    public String toHTML() {
        return null;
    }
}
