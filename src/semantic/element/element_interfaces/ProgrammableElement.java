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

    Variable createNewVariable(String type, String name, int line, int column);

    StructVariable createNewVariable(String type, int line, int column);

    ProgramElement getSymbolByNameAndElement(String name, Element element);

    public NumericIntegerConstant newIntegerConstant(String value, int line, int column);

    public NumericRealConstant newRealConstant(String value, int line, int column);

    public StringConstant newStringConstant(String value, int line, int column);

    public boolean hasReturnPoint();

    public void setReturnPoint();
}
