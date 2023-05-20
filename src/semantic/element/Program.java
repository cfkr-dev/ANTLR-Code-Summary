package semantic.element;

import semantic.element.sentence.literal.literal_master.Literal;
import semantic.element.variable.SimpleVariable;
import semantic.element.variable.StructVariable;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_master.MasterProgrammableElement;
import semantic.utils.enums.Element;
import semantic.utils.Constants;
import semantic.utils.Param;

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
    }

    private Map<Element, Map<String, ProgramElement>> initializeSymbolTable () {
        Map<Element, Map<String, ProgramElement>> symbolTable = new HashMap<>();
        for (Element e: Constants.PROGRAM_SYMBOLS)
            symbolTable.put(e, new HashMap<>());
        return symbolTable;
    }

    public SimpleVariable createNewVariable(String type, String name) {
        SimpleVariable variable = (SimpleVariable) super.createNewVariable(type, name);
        if (variable != null)
            this.programElements.add(variable);
        return variable;
    }

    public Function createNewFunction(String type, String name, List<Param> params) {
        if (!this.hasThisSymbol(name)) {
            Function function = new Function(type, name, this, params);
            this.addToSymbolTable(function);
            this.programElements.add(function);
            return function;
        } else {
            System.err.println("This element has been previously declared");
            return null;
        }
    }

    public Function createNewMainFunction(List<Param> params) {
        return this.createNewFunction("void", "Main", params);
    }

    public Constant createNewConstant(String name, Literal value) {
        if (!this.hasThisSymbol(name)) {
            Constant constant = new Constant(name, value, this);
            this.addToSymbolTable(constant);
            this.programElements.add(constant);
            return constant;
        } else {
            System.err.println("This element has been previously declared");
            return null;
        }
    }

    public StructVariable createNewStruct(String name) {
        if (!this.hasThisSymbol(name)) {
            StructVariable struct = new StructVariable(name, this);
            this.addToSymbolTable(struct);
            this.programElements.add(struct);
            return struct;
        } else {
            System.err.println("This element has been previously declared");
            return null;
        }
    }

    @Override
    public String toHTML() {

        //if (this.malformed)
          //throw new RuntimeException("No es posible crear el resumen de programa");

        String HTMLCabecera = new String();
        String HTMLFuncitonsCabecera = new String();
        String HTMLFunctions = new String();
        String HTMLGlobalVariables = new String();

        String HTMLMain = new String();
        String HTMLFinal = new String();

        HTMLCabecera = "<!doctype html>\n" +
                       "<html>\n" +
                       "\t<head>\n" +
                       "\t\t<title>" + this.name + ".html" + "</title>\n" +
                       "\t</head>\n" +
                       "\t<body>\n" +
                       "\t\t<A NAME=\"INICIO\">\n" +
                       "\t\t<h1>Programa: " + this.name + "</h1>\n" +
                       "\t\t<h2>Funciones</h2>\n" +
                       "\t\t<ul>\n";

        HTMLFinal = "\t</body>\n" +
                    "</html>";

        // HTMLFunction and HTMLMain
        for (ProgramElement element : programElements) {

            if (element instanceof Function) {

                if (element.getName() == "Main") { //HTMLMain

                    HTMLCabecera += "\t\t\t<li><A HREF=\"#" + element.getName() + "\">Programa principal</A></li>\n";

                    HTMLGlobalVariables = "\t\t<A NAME=\"" + element.getName() + "\">\n" + HTMLGlobalVariables;
                    HTMLGlobalVariables = "\t\t<h2>Funciones</h2>\n" +
                                          //"\t\t</br>\n" +
                                          HTMLGlobalVariables;
                    HTMLMain = element.toHTML().replace("\n", "\n\t\t");
                    HTMLMain += "\t\t<A HREF=\"#" + element.getName() + "\">Inicio del programa principal</A>\n";
                    HTMLMain += "\t\t<A HREF=\"#INICIO\">Inicio del programa</A> </br>\n";
                    HTMLMain += "\t\t</hr>\n";

                } else { //HTMLFunction

                    HTMLFuncitonsCabecera = "\t\t\t<li><A HREF=\"#" + element.getName() + "\">" + ((Function) element).toStringCabecera() + "</A></li>\n";

                    HTMLFunctions = "\t\t<A NAME=\"" + element.getName() + "\">\n";
                    HTMLFunctions += element.toHTML().replace("\n", "\n\t\t");
                    HTMLFunctions += "\t\t<A HREF=\"#" + element.getName() + "\">Inicio de la funcion</A>\n";
                    HTMLFunctions += "\t\t<A HREF=\"#INICIO\">Inicio del programa</A> </br>\n";
                    HTMLFunctions += "\t\t</hr>\n";

                }

            } else { // Constants and global variables

                HTMLGlobalVariables += element.toHTML().replace("\n", "\n\t\t");

            }

        }

        HTMLCabecera += HTMLFuncitonsCabecera;
        HTMLCabecera += "\t\t</ul>\n";
        HTMLCabecera += "\t\t</hr>\n";

        return HTMLCabecera + HTMLFunctions + HTMLGlobalVariables + HTMLMain + HTMLFinal;

    }
}
