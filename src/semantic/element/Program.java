package semantic.element;

import org.antlr.v4.misc.OrderedHashMap;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_master.MasterProgrammableElement;
import semantic.element.literal.literal_master.Literal;
import semantic.element.sentence.constant_sentence.ConstantDefinition;
import semantic.element.sentence.variable_sentence.VariableDefinition;
import semantic.element.sentence.variable_sentence.VariableDefinitionAndAssign;
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

public class Program extends MasterProgrammableElement {

    private final List<ProgramElement> programElements;

    public Program() {
        this.name = Constants.FILE_NAME;
        this.context = null;
        this.superContext = this;
        this.anchorContext = "PROGRAMA_PRINCIPAL";
        this.symbolTable = this.initializeSymbolTable();
        this.programElements = new LinkedList<>();
        this.malformed = false;
        this.hasReturnPoint = false;
        this.line = 0;
        this.column = 0;
    }

    private Map<Element, Map<String, ProgramElement>> initializeSymbolTable () {
        Map<Element, Map<String, ProgramElement>> symbolTable = new HashMap<>();
        for (Element e: Constants.PROGRAM_SYMBOLS)
            symbolTable.put(e, new OrderedHashMap<>());
        return symbolTable;
    }

    private List<Function> getAllInnerFunctions() {
        List<Function> functions = new LinkedList<>();
        for (ProgramElement function: this.symbolTable.get(Element.FUNCTION).values()) {
            if (!function.getName().equals("Main"))
                functions.add((Function) function);
        }
        return functions;
    }

    private List<ProgramElement> getAllMainDeclarations() {
        List<ProgramElement> declarations = new LinkedList<>();
        for (ProgramElement programElement: this.programElements) {
            if (programElement.getElementType().equals(Element.SENTENCE))
                declarations.add(programElement);
        }
        return declarations;
    }

    public VariableDefinition addNewVariableDefinition(String type, String name, int line, int column){
        boolean error = false;

        Variable variable = super.createNewVariable(type, name, line, column);

        if (variable == null) {
            if (!Type.valueOf(type.toUpperCase()).equals(Type.STRUCT)) {
                variable = new SimpleVariable(type, name, this, line, column);
            } else {
                variable = new StructVariable(this, line, column);
            }
            variable.setMalformed();
            error = true;
        }

        VariableDefinition variableDefinition = new VariableDefinition(variable, this, line, column);

        if (error) {
            variableDefinition.setMalformed();
            return variableDefinition;
        }

        this.programElements.add(variableDefinition);
        return variableDefinition;
    }

    public VariableDefinition addNewVariableDefinition(String type, int line, int column){
        boolean error = false;

        Variable variable = super.createNewVariable(type, line, column);

        if (variable == null) {
            variable = new StructVariable(this, line, column);
            variable.setMalformed();
            error = true;
        }

        VariableDefinition variableDefinition = new VariableDefinition(variable, this, line, column);

        if (error) {
            variableDefinition.setMalformed();
            return variableDefinition;
        }

        this.programElements.add(variableDefinition);
        return variableDefinition;
    }

    public VariableDefinitionAndAssign addNewVariableDefinitionAndAssign(String type, String name, AssignableElement assignableElement, int line, int column){
        boolean error = false;

        Variable variable = super.createNewVariable(type, name, line, column);

        if (variable == null) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar " + assignableElement.toString() + " a " + name + " por que " + name + " no ha sido declarado previamente");
            if (!Type.valueOf(type.toUpperCase()).equals(Type.STRUCT)) {
                variable = new SimpleVariable(type, name, this, line, column);
            } else {
                variable = new StructVariable(this, line, column);
            }
            variable.setMalformed();
            error = true;
        }

        if (Type.valueOf(type.toUpperCase()).equals(Type.STRUCT))
            variable = variable.variableClone(name);
        else
            variable = variable.variableClone();

        if (error) {
            variable.forceSetValue(assignableElement);
        } else if (!variable.setValue(assignableElement, this, line, column)) {
            error = true;
        }

        VariableDefinitionAndAssign variableDefinitionAndAssign = new VariableDefinitionAndAssign(variable, this, line, column);

        if (error) {
            variableDefinitionAndAssign.setMalformed();
            return variableDefinitionAndAssign;
        }

        this.programElements.add(variableDefinitionAndAssign);
        return variableDefinitionAndAssign;
    }

    public Function addNewFunction(String type, String name, int line, int column) {
        if (!this.hasThisSymbol(name)) {
            Function function = new Function(type, name, this, line, column);
            this.addToSymbolTable(function);
            this.programElements.add(function);
            return function;
        } else {
            System.err.println("ERROR " + line + ":" + column + " => " + "Este elemento ya ha sido declarado anteriormente con el mismo nombre (" + name + ")");
            Function function = new Function(type, name, this, line, column);
            function.setMalformed();
            return function;
        }
    }

    public Function addMainFunction(int line, int column) {
        return this.addNewFunction("void", "Main", line, column);
    }

    public ConstantDefinition addNewConstant(String name, Literal value, int line, int column) {
        if (!this.hasThisSymbol(name)) {
            ConstantDefinition constant = new ConstantDefinition(new Constant(name, value, this, line, column), this, line, column);
            this.addToSymbolTable(constant.getConstant());
            this.programElements.add(constant);
            return constant;
        } else {
            System.err.println("ERROR " + line + ":" + column + " => " + "Este elemento ya ha sido declarado anteriormente con el mismo nombre (" + name + ")");
            ConstantDefinition constant = new ConstantDefinition(new Constant(name, value, this, line, column), this, line, column);
            constant.setMalformed();
            return constant;
        }
    }

    @Override
    public String toHTML(int HTMLIndentationLevel) {

        // CHECK MALFORMED
        if (this.malformed) {
            System.err.println("\nERROR => No es posible crear el resumen del programa si hay errores por corregir");
            System.exit(1);
        }
        // GET PROGRAM ELEMENTS TO BUILD HTML
        List<Function> functions = this.getAllInnerFunctions();
        List<ProgramElement> declarations = this.getAllMainDeclarations();
        Function mainProgram = (Function) this.getSymbolByNameAndElement("Main", Element.FUNCTION);

        // FUNCTION HEADERS
        StringBuilder HTMLFunctionHeaders = generateFunctionHeadersList(functions);

        // FUNCTION BODIES
        StringBuilder HTMLFunctionBodies = generateFunctionBodiesList(functions);

        // MAIN PROGRAM
        StringBuilder HTMLMainProgram = generateMainProgram(declarations, mainProgram);

        // MAIN HTML CODE
        String HTMLProgram = "<!doctype html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<title>" + this.name + "</title>\n" +
                "\t\t<style>\n" +
                "\t\t\t.palres {font-weight: bold;}\n" +
                "\t\t\t.cte {color:green;}\n" +
                "\t\t\t.ident {color:blue;}\n" +
                "\t\t\tdiv {margin-left: 1.5rem;}\n" +
                "\t\t</style>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t" + HTMLHelper.genH(1, "Programa: " + this.name) + "\n\n" +
                HTMLFunctionHeaders +
                HTMLFunctionBodies +
                HTMLMainProgram +
                "\t</body>\n" +
                "</html>";

        return HTMLProgram;
    }

    private StringBuilder generateFunctionHeadersList(List<Function> functions) {
        StringBuilder HTMLFunctionHeaders = new StringBuilder()
            .append("\t\t").append(HTMLHelper.genH(2, "Funciones:")).append("\n\n")
            .append("\t\t<ul>\n");

        HTMLFunctionHeaders
            .append("\t\t\t<li>\n")
            .append("\t\t\t\t").append(HTMLHelper.genAHref(this.anchorContext, "Programa principal")).append("\n")
            .append("\t\t\t</li>\n");

        for (Function function: functions) {
            HTMLFunctionHeaders.append("\t\t\t<li>\n")
                .append("\t\t\t\t").append(HTMLHelper.genAHref(function.getAnchorContext(), function.getHeader())).append("\n")
                .append("\t\t\t</li>\n");
        }

        HTMLFunctionHeaders
            .append("\t\t</ul>");

        return HTMLFunctionHeaders;
    }

    private StringBuilder generateFunctionBodiesList(List<Function> functions) {
        StringBuilder HTMLFunctionBodies = new StringBuilder();

        for (Function function: functions) {
            HTMLFunctionBodies
                .append(HTMLHelper.genHr("\t\t"))
                .append("\t\t").append(HTMLHelper.genA(function.getAnchorContext())).append("\n")
                .append(function.toHTML(2))
                .append("\t\t").append(HTMLHelper.genAHref(function.getAnchorContext(), "Inicio de la función")).append("\n")
                .append("\t\t").append(HTMLHelper.genAHref("", "Inicio del programa")).append("\n");
        }

        return HTMLFunctionBodies;
    }

    private StringBuilder generateMainProgram(List<ProgramElement> declarations, Function mainProgram) {
        StringBuilder HTMLMainProgram = new StringBuilder()
            .append(HTMLHelper.genHr("\n", "\t\t", "\n\n"))
            .append("\t\t").append(HTMLHelper.genA(this.anchorContext)).append("\n")
            .append("\t\t").append(HTMLHelper.genH(2, "Programa principal:")).append("\n\n");

        for (ProgramElement declaration: declarations) {
            HTMLMainProgram
                .append(declaration.toHTML(2));
        }

        HTMLMainProgram
            .append(HTMLHelper.genBr("", "\t\t", "\n\n"))
            .append(mainProgram.toHTML(2))
            .append("\t\t").append(HTMLHelper.genAHref(this.anchorContext, "Inicio del programa principal")).append("\n")
            .append("\t\t").append(HTMLHelper.genAHref("", "Inicio del programa")).append("\n");

        return HTMLMainProgram;
    }
}
