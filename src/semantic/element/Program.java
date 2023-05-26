package semantic.element;

import org.antlr.v4.misc.OrderedHashMap;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_master.MasterProgrammableElement;
import semantic.element.literal.literal_master.Literal;
import semantic.element.sentence.sentence_interface.Sentence;
import semantic.element.variable.SimpleVariable;
import semantic.element.variable.StructVariable;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.Constants;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Program extends MasterProgrammableElement {

    private List<ProgramElement> programElements;

    public Program() {
        this.name = Constants.FILE_NAME;
        this.context = null;
        this.superContext = this;
        this.symbolTable = this.initializeSymbolTable();
        this.programElements = new LinkedList<>();
        this.malformed = false;
        this.line = 0;
        this.column = 0;
        Constants.PROGRAM = this;
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
            if (programElement.getElementType().equals(Element.VARIABLE) && programElement.getElementType().equals(Element.CONSTANT))
                declarations.add(programElement);
        }
        return declarations;
    }

    @Override
    public Variable createNewVariable(String type, String name, int line, int column) {
        Variable variable = super.createNewVariable(type, name, line, column);
        if (variable != null)
            this.programElements.add(variable);
        else {
            if (!Type.valueOf(type.toUpperCase()).equals(Type.STRUCT)) {
                variable = new SimpleVariable(type, name, this, line, column);
            } else {
                variable = new StructVariable(this, line, column);
            }
            variable.setMalformed();
        }
        return variable;
    }

    public Function createNewFunction(String type, String name, int line, int column) {
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

    public Function createNewMainFunction(int line, int column) {
        return this.createNewFunction("void", "Main", line, column);
    }

    public Constant createNewConstant(String name, Literal value, int line, int column) {
        if (!this.hasThisSymbol(name)) {
            Constant constant = new Constant(name, value, this, line, column);
            this.addToSymbolTable(constant);
            this.programElements.add(constant);
            return constant;
        } else {
            System.err.println("ERROR " + line + ":" + column + " => " + "Este elemento ya ha sido declarado anteriormente con el mismo nombre (" + name + ")");
            Constant constant = new Constant(name, value, this, line, column);
            constant.setMalformed();
            return constant;
        }
    }

    @Override
    public String toHTML(int HTMLIndentationLevel, int codeIndentationLevel) {

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
        StringBuilder HTMLMain = new StringBuilder()
                .append("<!doctype html>\n")
                .append("<html>\n")
                    .append("\t<head>\n")
                        .append("\t\t<title>").append(this.name).append("</title>\n")
                        .append("\t\t<style>\n")
                            .append(".palres {font-weight: bold;}\n")
                            .append(".cte {color:green;}")
                            .append(".ident {color:blue;}")
                        .append("\t\t</style>\n")
                    .append("\t</head>\n")
                    .append("\t<body>\n")
                        .append("\t\t<h1>Programa:").append(this.name).append("</h1>\n")
                        .append("\t\t<h2>Funciones:").append("</h2>\n")
                        .append("\t\t<ul>\n")
                            .append(HTMLFunctionHeaders)
                        .append("\t\t</ul>\n")
                        .append(HTMLFunctionBodies)
                        .append(HTMLMainProgram)
                        .append("\t\t<hr>\n")
                        .append("\t\t<a name=\"programa-principal\">")
                    .append("\t</body>\n")
                .append("</html>\n");


    return null;
//        String HTMLCabecera = new String();
//        String HTMLFuncitonsCabecera = new String();
//        String HTMLFunctions = new String();
//        String HTMLGlobalVariables = new String();
//
//        String HTMLMain = new String();
//        String HTMLFinal = new String();
//
//        HTMLCabecera = "<!doctype html>\n" +
//                       "<html>\n" +
//                       "\t<head>\n" +
//                       "\t\t<title>" + this.name + ".html" + "</title>\n" +
//                       "\t</head>\n" +
//                       "\t<body>\n" +
//                       "\t\t<A NAME=\"INICIO\">\n" +
//                       "\t\t<h1>Programa: " + this.name + "</h1>\n" +
//                       "\t\t<h2>Funciones</h2>\n" +
//                       "\t\t<ul>\n";
//
//        HTMLFinal = "\t</body>\n" +
//                    "</html>";
//
//        // HTMLFunction and HTMLMain
//        for (ProgramElement element : programElements) {
//
//            if (element instanceof Function) {
//
//                if (element.getName() == "Main") { //HTMLMain
//
//                    HTMLCabecera += "\t\t\t<li><A HREF=\"#" + element.getName() + "\">Programa principal</A></li>\n";
//
//                    HTMLGlobalVariables = "\t\t<A NAME=\"" + element.getName() + "\">\n" + HTMLGlobalVariables;
//                    HTMLGlobalVariables = "\t\t<h2>Funciones</h2>\n" +
//                                          //"\t\t</br>\n" +
//                                          HTMLGlobalVariables;
//                    HTMLMain = element.toHTML().replace("\n", "\n\t\t");
//                    HTMLMain += "\t\t<A HREF=\"#" + element.getName() + "\">Inicio del programa principal</A>\n";
//                    HTMLMain += "\t\t<A HREF=\"#INICIO\">Inicio del programa</A> </br>\n";
//                    HTMLMain += "\t\t</hr>\n";
//
//                } else { //HTMLFunction
//
//                    HTMLFuncitonsCabecera = "\t\t\t<li><A HREF=\"#" + element.getName() + "\">" + ((Function) element).toStringCabecera() + "</A></li>\n";
//
//                    HTMLFunctions = "\t\t<A NAME=\"" + element.getName() + "\">\n";
//                    HTMLFunctions += element.toHTML().replace("\n", "\n\t\t");
//                    HTMLFunctions += "\t\t<A HREF=\"#" + element.getName() + "\">Inicio de la funcion</A>\n";
//                    HTMLFunctions += "\t\t<A HREF=\"#INICIO\">Inicio del programa</A> </br>\n";
//                    HTMLFunctions += "\t\t</hr>\n";
//
//                }
//
//            } else { // Constants and global variables
//
//                HTMLGlobalVariables += element.toHTML().replace("\n", "\n\t\t");
//
//            }
//
//        }
//
//        HTMLCabecera += HTMLFuncitonsCabecera;
//        HTMLCabecera += "\t\t</ul>\n";
//        HTMLCabecera += "\t\t</hr>\n";
//
//        return HTMLCabecera + HTMLFunctions + HTMLGlobalVariables + HTMLMain + HTMLFinal;

    }

    private StringBuilder generateFunctionHeadersList(List<Function> functions) {
        StringBuilder HTMLFunctionHeaders = new StringBuilder();

        boolean first = true;

        for (Function function: functions) {
            HTMLFunctionHeaders.append("\t\t\t<li>\n")
                .append("\t\t\t\t<a href=\"").append("#").append(function.getName()).append("\">\n");
                    if (first) {
                        HTMLFunctionHeaders.append("\t\t\t\t\t").append("Programa principal\n");
                        first = false;
                    } else
                        HTMLFunctionHeaders.append("\t\t\t\t\t").append(function.getHeader()).append("\n");
                HTMLFunctionHeaders.append("\t\t\t\t</a>\n")
            .append("\t\t\t</li>\n");
        }

        return HTMLFunctionHeaders;
    }

    private StringBuilder generateFunctionBodiesList(List<Function> functions) {
        StringBuilder HTMLFunctionBodies = new StringBuilder();

        for (Function function: functions) {
            HTMLFunctionBodies
                    .append("\t\t<hr/>\n")
                    .append("\t\t<a name=\"").append(function.getName()).append("\">\n")
                    .append(function.toHTML(2, 0))
                    .append("\t\t<a href=\"").append("#").append(function.getName()).append("\">").append("Inicio de la función").append("</a>\n")
                    .append("\t\t<a href=\"").append("#").append("\">").append("Inicio del programa").append("</a>\n");
        }

        return HTMLFunctionBodies;
    }
}
