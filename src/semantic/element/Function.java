package semantic.element;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.sentence.sentence_interface.Sentence;
import semantic.element.sentence.sentence_master.MasterSentenceContainer;
import semantic.element.variable.SimpleVariable;
import semantic.utils.Constants;
import semantic.utils.Param;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

import java.util.*;

public class Function extends MasterSentenceContainer {
    private List<SimpleVariable> params;

    public Function(String type, String name, Program context, List<Param> params) {
        this.type = Type.valueOf(type.toUpperCase());
        this.name = name;
        this.elementType = Element.FUNCTION;
        this.context = context;
        this.superContext = this;
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
        this.params = addParamsToSymbolTable(params);
    }

    private List<SimpleVariable> addParamsToSymbolTable(List<Param> params) {
        List<SimpleVariable> variableList = new ArrayList<>();
        for (Param param: params) {
            SimpleVariable variable = (SimpleVariable) this.createNewVariable(param.getType(), param.getName());
            variableList.add(variable);
        }
        return variableList;
    }

    private Map<Element, Map<String, ProgramElement>> generateLocalSymbolTable(Map<Element, Map<String, ProgramElement>> symbolTable) {
        Map<Element, Map<String, ProgramElement>> localSymbolTable = new HashMap<>();
        for (Element e: Constants.PROGRAM_SYMBOLS)
            localSymbolTable.put(e, new HashMap<>(symbolTable.get(e)));
        return localSymbolTable;
    }

    @Override
    public String toHTML() {

        String HTMLFunction = new String();

        HTMLFunction = "<p>" + this.toStringCabecera() + " {</p>\n";

        HTMLFunction += this.toHTMLBrackets();

        return HTMLFunction;

    }

    public String toStringCabecera() {

        String cabecera = new String();

        cabecera = this.type + " " + this.toHTMLIdentifier() + "(";

        for (SimpleVariable param : params) {

            cabecera += param.getType() + " " + param.toHTMLIdentifier() + ",";

        }

        return cabecera.substring(0, cabecera.length()-1) + ")";

    }

    public boolean checkCallingParams(List<AssignableElement> params) {
        if (this.params.size() != params.size()) return false;
        else {
            int index = 0;
            for (SimpleVariable param: this.params) {
                if (!param.getType().equals(params.get(index).getType()) && !(params.get(index).getType().equals(Type.ANY)))
                    return false;
                index++;
            }
            return true;
        }
    }
}
