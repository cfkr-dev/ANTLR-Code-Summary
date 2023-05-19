package semantic.utils.enums;

import semantic.utils.Constants;

public enum Operation {
    SUM,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION,
    MODULUS,

    EQUAL,
    NOT_EQUAL,
    GREATER,
    GREATER_EQUAL,
    LOWER,
    LOWER_EQUAL,

    AND,
    OR,
    NOT;

    public static String getOperationName(Operation operationType) {
        return Constants.OPERATION_NAMES.get(operationType);
    }
}
