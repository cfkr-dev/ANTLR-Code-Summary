package semantic.element;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.sentence.sentence_master.MasterSentenceContainer;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.Constants;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Function extends MasterSentenceContainer {
    private List<Variable> params;

    public Function(String type, String name, Program context) {
        this.type = Type.valueOf(type.toUpperCase());
        this.name = name;
        this.elementType = Element.FUNCTION;
        this.context = context;
        this.superContext = this;
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
        this.params = new LinkedList<>();
        this.malformed = false;
    }

    public Function addParam(String type, String name) {
        if (this.malformed)
            return this;
        Variable param = this.createNewVariable(type, name);
        if (param != null)
            this.params.add(param);
        else
            this.setMalformed();
        return this;
    }

    private Map<Element, Map<String, ProgramElement>> generateLocalSymbolTable(Map<Element, Map<String, ProgramElement>> symbolTable) {
        Map<Element, Map<String, ProgramElement>> localSymbolTable = new HashMap<>();
        for (Element e: Constants.PROGRAM_SYMBOLS)
            localSymbolTable.put(e, new HashMap<>(symbolTable.get(e)));
        localSymbolTable.get(Element.FUNCTION).put(this.name, this);
        return localSymbolTable;
    }

    @Override
    public String toHTML() {
        return null;
    }

    public boolean checkCallingParams(List<AssignableElement> params) {
        if (this.malformed)
            return false;

        if (this.params.size() != params.size()) {
            if (this.params.size() < params.size())
                System.err.println("Parámetros insuficientes para la función" + this.name + ". Se esperaban " + this.params.size());
            else
                System.err.println("Más parámetros de lo esperado para la función" + this.name + ". Se esperaban " + this.params.size());
            return false;
        }
        else {
            int index = 0;
            for (Variable param: this.params) {
                if (!Type.checkTypeConsistency(param.getType(), params.get(index).getType())){
                    System.err.println("Los tipos no coinciden. Esperado " + param.getName() + " (" + param.getType() + ") pero pero recibido " + params.get(index).getType());
                    return false;
                }
                index++;
            }
            return true;
        }
    }
}
