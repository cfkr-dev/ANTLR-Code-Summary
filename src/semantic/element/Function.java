package semantic.element;

import semantic.element.sentence.sentence_master.MasterSentenceContainer;
import semantic.element_interfaces.ProgramElement;
import semantic.enums.Element;
import semantic.enums.Type;
import semantic.utils.Param;

import java.util.*;

public class Function extends MasterSentenceContainer {
    private List<Variable> params;

    public Function(String type, String name, Program context, List<Param> params) {
        this.type = Type.valueOf(type.toUpperCase());
        this.name = name;
        this.elementType = Element.FUNCTION;
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
}
