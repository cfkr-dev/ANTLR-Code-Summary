package semantic.element_interfaces;

import semantic.element.Variable;
import semantic.enums.Element;

import java.util.Map;

public interface ProgrammableElement extends ProgramElement {
    void addToSymbolTable(ProgramElement element);

    void updateSymbolTable(ProgramElement element);

    Map<Element, Map<String, ProgramElement>> getSymbolTable();

    boolean hasThisSymbol(String name);

    Variable createNewVariable(String type, String name);

    ProgramElement getSymbolByNameAndElement(String name, Element element);
}
