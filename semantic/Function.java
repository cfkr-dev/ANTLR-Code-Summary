import enums.Element;
import enums.Type;

import java.util.*;

public class Function implements ProgrammableElement{

    private Type type;
    private String name;
    private List<Variable> params;
    private ProgramElement context;
    private Map<Element, Map<String, ProgramElement>> symbolTable;
    private List<Sentence> sentences;

    public Function(String type, String name, Program context, List<Param> params) {
        this.type = Type.valueOf(type.toUpperCase());
        this.name = name;
        this.context = context;
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
        this.params = addParamsToSymbolTable(params);
    }

    private List<Variable> addParamsToSymbolTable(List<Param> params) {
        List<Variable> variableList = new ArrayList<>();
        for (Param param: params) {
            Variable variable = this.createNewVariable(param.getType(), param.getName());
            variableList.add(variable);
        }
        return variableList;
    }

    private Map<Element, Map<String, ProgramElement>> generateLocalSymbolTable(Map<Element, Map<String, ProgramElement>> symbolTable) {
        Map<Element, Map<String, ProgramElement>> localSymbolTable = new HashMap<>();
        for (Element e: Element.values())
            localSymbolTable.put(e, new HashMap<>(symbolTable.get(e)));

        return localSymbolTable;
    }

    @Override
    public String toHTML() {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Element getElementType() {
        return Element.FUNCTION;
    }

    @Override
    public void addToSymbolTable(ProgramElement element) {
        this.symbolTable.get(element.getElementType()).put(element.getName(), element);
    }

    @Override
    public void updateSymbolTable(ProgramElement element) {
        this.symbolTable.get(element.getElementType()).replace(element.getName(), element);
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
