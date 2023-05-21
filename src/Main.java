import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

import static semantic.utils.Constants.FILE_NAME;
import static semantic.utils.Constants.PROGRAM;

public class Main {
    public static void main(String[] args) throws InstantiationException {
        /*
        try {

            // Create input stream for reading file
            CharStream input = CharStreams.fromFileName(args[0]);

            // Save file name
            FILE_NAME = args[0];

            // Start HTML generator
            HTMLFileGen.starter();

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

            // Start generation
            HTMLFileGen.generate(PROGRAM.toHTML());

        } catch (org.antlr.v4.runtime.RecognitionException e) {
            // Input recognition error
            System.err.println("REC " + e.getMessage());

        } catch (IOException e) {
            // Input / Output recognition error
            System.err.println("IO " + e.getMessage());

        } catch (java.lang.RuntimeException e) {
            // Other fail
            System.err.println("RUN " + e.getMessage());
        }
        //*/

        // Save file name
        FILE_NAME = "new_program";

        // Start HTML generator
        HTMLFileGen.starter();

        FunctionsAndVariablesTest.runTest_2();

        // Start generation
        HTMLFileGen.generate(PROGRAM.toHTML());
        //System.out.println(PROGRAM.toHTML());
    }
}