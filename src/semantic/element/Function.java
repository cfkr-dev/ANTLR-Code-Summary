package semantic.element;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.sentence.sentence_interface.Sentence;
import semantic.element.sentence.sentence_master.MasterSentenceContainer;
import semantic.element.variable.SimpleVariable;
import semantic.element.variable.StructVariable;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.Constants;
import semantic.utils.HTMLHelper;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Function extends MasterSentenceContainer {
    private List<Variable> params;

    public Function(String type, String name, Program context, int line, int column) {
        this.type = Type.valueOf(type.toUpperCase());
        this.name = name;
        this.elementType = Element.FUNCTION;
        this.context = context;
        this.superContext = this;
        if (this.name.equals("Main"))
            this.anchorContext = "PROGRAMA_PRINCIPAL:" + this.name;
        else
            this.anchorContext = "FUNCIONES:" + this.name;
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(context.getSymbolTable());
        this.params = new LinkedList<>();
        this.malformed = false;
        this.hasReturnPoint = false;
        this.line = line;
        this.column = column;
    }

    public Function addParam(String type, String name, int line, int column) {
        Variable param;

        if (Type.valueOf(type.toUpperCase()).equals(Type.STRUCT)) {
            param = new StructVariable(this, line, column);
        } else {
            param = new SimpleVariable(type, name, this, line, column);
        }

        if (this.hasThisSymbol(name)) {
            this.symbolTable.get(Element.VARIABLE).replace(name, param);
        } else {
            this.symbolTable.get(Element.VARIABLE).put(name, param);
        }

        this.params.add(param);
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
    public String toHTML(int HTMLIndentationLevel) {
        String tabs = HTMLHelper.genTabs(HTMLIndentationLevel);

        StringBuilder function = new StringBuilder()
            .append(tabs).append(HTMLHelper.genSpan("palres", this.type.name().toLowerCase()))
            .append(" ")
            .append(HTMLHelper.genSpan("ident", this.name))
            .append("(");

        boolean first = true;

        for (Variable variable: this.params) {
            if (first)
                first = false;
            else
                function
                    .append(", ");
            function
                .append(HTMLHelper.genSpan("palres", variable.getType().name().toLowerCase()))
                .append(" ")
                .append(HTMLHelper.genA(variable.getAnchorContext(), HTMLHelper.genSpan("ident", variable.getName())));
        }

        function
            .append(")")
            .append(HTMLHelper.genBr(tabs));

        function
            .append(tabs)
            .append("{")
            .append(HTMLHelper.genBr(tabs));

        function
            .append(tabs)
            .append("<div>\n");

        for (Sentence sentence: this.sentences)
            function
                .append(sentence.toHTML(HTMLIndentationLevel + 1));

        function
            .append(tabs)
            .append("</div>\n\n");

        function
            .append(tabs)
            .append("}")
            .append(HTMLHelper.genBr(tabs));

        return function.toString();
    }

    public boolean checkCallingParams(List<AssignableElement> params, int line, int column) {
        if (this.malformed) {
            this.setMalformed();
            return false;
        }

        if (this.params.size() != params.size()) {
            if (this.params.size() > params.size())
                System.err.println("ERROR " + line + ":" + column + " => " + "Parámetros insuficientes para la función " + this.name + ". Esperados: " + this.params.size() + ". Introducidos: " + params.size());
            else
                System.err.println("ERROR " + line + ":" + column + " => " + "Más parámetros de lo esperado para la función " + this.name + ". Esperados:  " + this.params.size() + ". Introducidos: " + params.size());
            return false;
        }
        else {
            int index = 0;
            for (Variable param: this.params) {
                if (!Type.checkTypeConsistency(param.getType(), params.get(index), true)){
                    System.err.println("ERROR " + line + ":" + column + " => " + "Los tipos de los parámetros no coinciden. Esperado " + param.getName() + " (" + param.getType() + ") pero pero recibido " + params.get(index).getType());
                    return false;
                }
                index++;
            }
            return true;
        }
    }

    public String getHeader() {
        StringBuilder header = new StringBuilder().append(this.type.name().toLowerCase()).append(" ").append(this.name).append("(");

        boolean first = true;

        for (Variable variable: this.params) {
            if (first)
                first = false;
            else
                header.append(", ");
            header.append(variable.getType().name().toLowerCase()).append(" ").append(variable.getName());
        }

        header.append(")");

        return header.toString();
    }
}
