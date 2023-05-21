import java.io.*;

import static semantic.utils.Constants.FILE_NAME;

public class HTMLFileGen {

    private static File fichero;
    public static void starter () {

        // Comprobar que el fichero no existe
            // Si existe excepcion
        fichero = new File(FILE_NAME + ".html");
        if (fichero.exists())
            throw new RuntimeException("El fichero de resumen de programa ya existe");

        // Abrir fichero
        try {
            // A partir del objeto File creamos el fichero físicamente
            if (!fichero.createNewFile())
                System.out.println("No ha podido ser creado el fichero");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void generate (String HTML) {

        // Introducir string en el fichero
        try {
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.write(HTML);

            pw.close();
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Ejecutar fichero en navegador
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("Start msedge " + FILE_NAME + ".html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}