import java.awt.*;
import java.io.*;

import static semantic.utils.Constants.FILE_NAME;

public class HTMLFileGen {
    public static void generate (String HTML) throws IOException {
        try {
            File file = new File(FILE_NAME + ".html");

            if (file.exists()) {
                System.out.println("ATENCIÓN => Ya existe un fichero con el mismo nombre (" + file.getName() + "). Será reemplazado.");
                if (!file.delete())
                    throw new IOException();
            }

            if (!file.createNewFile())
                throw new IOException();

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.write(HTML);

            pw.close();
            bw.close();

            System.out.println(
                "El resumen del programa ha sido creado con éxito.\n" +
                "El fichero se encuentra en: " + file.getAbsolutePath()
            );

        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();

            if (file.exists())
                desktop.open(file);
            else
                throw new IOException();

        } else
            System.out.println(
                "No es posible abrir el navegador para mostrar el resumen del programa.\n" +
                "El fichero se encuentra en: " + file.getAbsolutePath()
            );
        } catch (IOException e) {
            throw new IOException("ERROR => Ha ocurrido un problema al crear el resumen del programa", e);
        }
    }

}
