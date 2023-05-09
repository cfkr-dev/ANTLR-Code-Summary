package semantic.element_master;

import semantic.element.Variable;
import semantic.enums.Element;
import semantic.element_interfaces.ProgramElement;
import semantic.element_interfaces.ProgrammableElement;

import java.util.Map;

public abstract class MasterProgrammableElement extends MasterProgramElement implements ProgrammableElement {
    protected Map<Element, Map<String, ProgramElement>> symbolTable;

    @Override
    public void addToSymbolTable(ProgramElement element) {
        this.symbolTable.get(element.getElementType()).put(element.getName(), element);
    }

    @Override
    public void updateSymbolTable(ProgramElement element) {
        this.symbolTable.get(element.getElementType()).replace(element.getName(), element);
    }

    @Override
    public Map<Element, Map<String, ProgramElement>> getSymbolTable() {
        return this.symbolTable;
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

    @Override
    public ProgramElement getSymbolByNameAndElement(String name, Element element) {
        return this.symbolTable.get(element).get(name);
    }
}
