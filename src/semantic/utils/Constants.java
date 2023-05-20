package semantic.utils;

import semantic.element.Program;
import semantic.utils.enums.Element;

public class Constants {

  public static final Element[] PROGRAM_SYMBOLS = {Element.VARIABLE, Element.CONSTANT, Element.FUNCTION};
  public static String FILE_NAME;

  public static Program p;

  // RUTA RAIZ PARA TEST
  private static final String TEST_PATH = "./test/";
  
  // RUTA PARA FICHEROS DE ENTRADA
  private static final String TEST_IN_PATH = "./test/in/";
  
  // RUTA PARA FICHEROS DE SALIDA
  private static final String TEST_OUT_PATH = "./test/out/";

  
}
