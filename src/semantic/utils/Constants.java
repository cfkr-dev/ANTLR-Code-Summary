package semantic.utils;

import semantic.enums.Element;
import semantic.enums.Type;

public class Constants {

    public static final Element[] STRUCT_SYMBOLS = {Element.VARIABLE, Element.STRUCT};
  public static final Element[] PROGRAM_SYMBOLS = {Element.VARIABLE, Element.CONSTANT, Element.STRUCT, Element.FUNCTION};
    public static String FILE_NAME;

  // RUTA RAIZ PARA TEST
  private static final String TEST_PATH = "./test/";
  
  // RUTA PARA FICHEROS DE ENTRADA
  private static final String TEST_IN_PATH = "./test/in/";
  
  // RUTA PARA FICHEROS DE SALIDA
  private static final String TEST_OUT_PATH = "./test/out/";

  public static boolean checkTypeTokenType(Type type, String tokenType) {
    switch (tokenType) {
      case "NUMERIC_INTEGER_CONST" :
        return type.equals(Type.INTEGER);
      case "NUMERIC_REAL_CONST" :
        return type.equals(Type.FLOAT);
      case "STRING_CONST" :
        return type.equals(Type.STRING);
      default :
        return false;
    }
  }

  
}
