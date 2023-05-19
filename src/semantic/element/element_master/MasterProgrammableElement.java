package semantic.element.element_master;

import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.variable.SimpleVariable;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.Constants;
import semantic.utils.enums.Element;

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
        for (Element e: Constants.PROGRAM_SYMBOLS){
            if (this.symbolTable.get(e).containsKey(name))
                return true;
        }
        return false;
    }

    @Override
    public Variable createNewVariable(String type, String name) {
        if (!this.hasThisSymbol(name)) {
            SimpleVariable variable = new SimpleVariable(type, name, this);
            this.addToSymbolTable(variable);
            return variable;
        } else {
            System.err.println("Este elemento ya ha sido declarado anteriormente con el mismo nombre (" + name + ")");
            return null;
        }
    }

    @Override
    public ProgramElement getSymbolByNameAndElement(String name, Element element) {
        return this.symbolTable.get(element).get(name);
    }
}
