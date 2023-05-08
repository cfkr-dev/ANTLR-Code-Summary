import enums.Element;
import enums.Type;

public interface ProgrammableElement extends ProgramElement {
    void addToSymbolTable(ProgramElement element);

    void updateSymbolTable(ProgramElement element);

    boolean hasThisSymbol(String name);

    Variable createNewVariable(String type, String name);

    ProgramElement getSymbolByNameAndElement(String name, Element element);
}
