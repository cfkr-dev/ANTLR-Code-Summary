import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import semantic.utils.Constants;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InstantiationException {
        try {
            // Check arguments
            if (args.length == 0)
                System.err.println("Argumentos incorrectos: para iniciar el conversor indique la ruta de fichero de entrada");

            // Create input stream for reading file
            CharStream input = CharStreams.fromFileName(args[0]);

            // Create lexer
            sourceCodeLexer lexer = new sourceCodeLexer(input);

            // Create tokens stream
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Create parser
            sourceCodeParser parser = new sourceCodeParser(tokens);

            // Remove default error listener from lexer
            lexer.removeErrorListeners();

            // Add custom error listener to lexer
            lexer.addErrorListener(new UnderlineCustomErrorListener());

            // Remove default error listener from parser
            parser.removeErrorListeners();

            // Add custom error listener to parser
            parser.addErrorListener(new UnderlineCustomErrorListener());

            // Add custom error strategy to parser
            parser.setErrorHandler(new CustomErrorStrategy());

            // Run parser
            parser.program_prime();

            // Create HTML string
            String HTML = Constants.PROGRAM.toHTML(0);

            // Get source file name
            Constants.FILE_NAME = new File(args[0]).getName();

            // Generate HTML file
            HTMLFileGen.generate(HTML);

        } catch (org.antlr.v4.runtime.RecognitionException e) {
            // Input recognition error
            System.err.println("Error de reconocimiento: " + e.getMessage());

        } catch (IOException e) {
            // Input / Output recognition error
            System.err.println("Error de entrada/salida: " + e.getMessage());

        } catch (java.lang.RuntimeException e) {
            // Other fail
            System.err.println("Error de ejecución: " + e.getMessage());
        }
    }
}