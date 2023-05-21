package semantic.element.element_interfaces;

import semantic.element.literal.NumericIntegerConstant;
import semantic.element.literal.NumericRealConstant;
import semantic.element.literal.StringConstant;
import semantic.element.variable.StructVariable;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.enums.Element;

import java.util.Map;

public interface ProgrammableElement<E extends ProgramElement> extends ProgramElement {
    void addToSymbolTable(E element);

    void updateSymbolTable(E element);

    Map<Element, Map<String, E>> getSymbolTable();

    boolean hasThisSymbol(String name);

    Variable createNewVariable(String type, String name);

    StructVariable createNewVariable(String type);

    ProgramElement getSymbolByNameAndElement(String name, Element element);

    public NumericIntegerConstant newIntegerConstant(String value);

    public NumericRealConstant newRealConstant(String value);

    public StringConstant newStringConstant(String value);
}
