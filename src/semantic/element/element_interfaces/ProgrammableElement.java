package semantic.element.element_interfaces;

import semantic.element.variable.variable_interface.Variable;
import semantic.utils.enums.Element;

import java.util.Map;

public interface ProgrammableElement<E extends ProgramElement> extends ProgramElement {
    void addToSymbolTable(E element);

    void updateSymbolTable(E element);

    Map<Element, Map<String, E>> getSymbolTable();

    boolean hasThisSymbol(String name);

    Variable<? extends AssignableElement> createNewVariable(String type, String name);

    ProgramElement getSymbolByNameAndElement(String name, Element element);
}
