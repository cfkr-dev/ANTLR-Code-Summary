import enums.Element;
import enums.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Function implements ProgramElement{

    private String name;
    private ProgramElement context;
    private Map<Element, Map<String, ProgramElement>> symbolTable;
    private Type type;
    private List<Sentence> sentences;

    public Function(String type, String name, Program program, List<Variable> params) {
        this.type = Type.valueOf(type.toUpperCase());
        this.name = name;
        this.context = program;
        this.sentences = new LinkedList<>();
        this.symbolTable = generateLocalSymbolTable(program.getSymbolTable(), params);
    }

    private Map<Element, Map<String, ProgramElement>> generateLocalSymbolTable(Map<Element, Map<String, ProgramElement>> symbolTable, List<Variable> params) {
        for(Variable var: params)
            symbolTable.get(Element.VARIABLE).put(var.getName(), var);
        return symbolTable;
    }

    @Override
    public String toHTML() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Element getElementType() {
        return null;
    }
}
