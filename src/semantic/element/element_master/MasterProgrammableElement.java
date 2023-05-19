package semantic.element.element_master;

import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.literal.NumericIntegerConstant;
import semantic.element.literal.NumericRealConstant;
import semantic.element.literal.StringConstant;
import semantic.element.variable.SimpleVariable;
import semantic.element.variable.StructVariable;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.Constants;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

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
            Variable variable;
            if (Type.valueOf(type.toUpperCase()).equals(Type.STRUCT)) {
                variable = new StructVariable(name, this);
            } else {
                variable = new SimpleVariable(type, name, this);
            }
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

    public NumericIntegerConstant newIntegerConstant(String value) {
        return new NumericIntegerConstant(value, this);
    }

    public NumericRealConstant newRealConstant(String value) {
        return new NumericRealConstant(value, this);
    }

    public StringConstant newStringConstant(String value) {
        return new StringConstant(value, this);
    }
}
