package semantic.utils;

import semantic.element.Program;
import semantic.utils.enums.Element;
import semantic.utils.enums.Operation;
import semantic.utils.enums.Type;

import java.util.Map;
import java.util.Set;

public class Constants {

    public static final Element[] PROGRAM_SYMBOLS = {Element.VARIABLE, Element.CONSTANT, Element.FUNCTION};

    public static Program PROGRAM;

    public static String FILE_NAME;

    public static final Map<Type, Set<Operation>> TYPE_OPERATION_RULES = Map.ofEntries(
            Map.entry(Type.INTEGER, Set.of(
                    Operation.SUM,
                    Operation.SUBTRACTION,
                    Operation.MULTIPLICATION,
                    Operation.DIVISION,
                    Operation.MODULUS,

                    Operation.EQUAL,
                    Operation.NOT_EQUAL,
                    Operation.GREATER,
                    Operation.GREATER_EQUAL,
                    Operation.LOWER,
                    Operation.LOWER_EQUAL,

                    Operation.AND,
                    Operation.OR,
                    Operation.NOT
            )),
            Map.entry(Type.FLOAT, Set.of(
                    Operation.SUM,
                    Operation.SUBTRACTION,
                    Operation.MULTIPLICATION,
                    Operation.DIVISION,

                    Operation.EQUAL,
                    Operation.NOT_EQUAL,
                    Operation.GREATER,
                    Operation.GREATER_EQUAL,
                    Operation.LOWER,
                    Operation.LOWER_EQUAL
            )),
            Map.entry(Type.STRING, Set.of(
                    Operation.SUM,
                    Operation.SUBTRACTION,
                    Operation.MULTIPLICATION,
                    Operation.DIVISION,

                    Operation.EQUAL,
                    Operation.NOT_EQUAL,
                    Operation.GREATER,
                    Operation.GREATER_EQUAL,
                    Operation.LOWER,
                    Operation.LOWER_EQUAL
            )),
            Map.entry(Type.STRUCT, Set.of(
                    Operation.EQUAL,
                    Operation.NOT_EQUAL
            )),
            Map.entry(Type.VOID, Set.of(
                    Operation.EQUAL,
                    Operation.NOT_EQUAL
            )),
            Map.entry(Type.ANY, Set.of(
                    Operation.SUM,
                    Operation.SUBTRACTION,
                    Operation.MULTIPLICATION,
                    Operation.DIVISION,
                    Operation.MODULUS,

                    Operation.EQUAL,
                    Operation.NOT_EQUAL,
                    Operation.GREATER,
                    Operation.GREATER_EQUAL,
                    Operation.LOWER,
                    Operation.LOWER_EQUAL,

                    Operation.AND,
                    Operation.OR,
                    Operation.NOT
            ))
    );

    public static final Map<Type, Set<Type>> TYPE_X_TYPE_RULES = Map.ofEntries(
            Map.entry(Type.INTEGER, Set.of(
                    Type.INTEGER,
                    Type.FLOAT,
                    Type.STRING,
                    Type.ANY
            )),
            Map.entry(Type.FLOAT, Set.of(
                    Type.INTEGER,
                    Type.FLOAT,
                    Type.STRING,
                    Type.ANY
            )),
            Map.entry(Type.STRING, Set.of(
                    Type.INTEGER,
                    Type.FLOAT,
                    Type.STRING,
                    Type.ANY
            )),
            Map.entry(Type.STRUCT, Set.of(
                    Type.STRUCT,
                    Type.ANY
            )),
            Map.entry(Type.VOID, Set.of(
                    Type.VOID,
                    Type.ANY
            )),
            Map.entry(Type.ANY, Set.of(
                    Type.INTEGER,
                    Type.FLOAT,
                    Type.STRING,
                    Type.STRUCT,
                    Type.VOID,
                    Type.ANY
            ))
    );

    public static final Map<Operation, String> OPERATION_NAMES = Map.ofEntries(
            Map.entry(Operation.SUM, "SUMA"),
            Map.entry(Operation.SUBTRACTION, "RESTA"),
            Map.entry(Operation.MULTIPLICATION, "MULTIPLICACIÓN"),
            Map.entry(Operation.DIVISION, "DIVISIÓN"),
            Map.entry(Operation.MODULUS, "MÓDULO"),
            Map.entry(Operation.EQUAL, "IGUALDAD"),
            Map.entry(Operation.NOT_EQUAL, "DESIGUALDAD"),
            Map.entry(Operation.GREATER, "MAYOR QUE"),
            Map.entry(Operation.GREATER_EQUAL, "MAYOR O IGUAL QUE"),
            Map.entry(Operation.LOWER, "MENOR"),
            Map.entry(Operation.LOWER_EQUAL, "MENOR O IGUAL QUE"),
            Map.entry(Operation.AND, "AND LÓGICO"),
            Map.entry(Operation.OR, "OR LÓGICO"),
            Map.entry(Operation.NOT, "NOT LÓGICO")
    );
}
