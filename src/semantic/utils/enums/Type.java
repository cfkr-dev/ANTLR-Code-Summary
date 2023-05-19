package semantic.utils.enums;

import semantic.utils.Constants;

public enum Type {
    INTEGER, FLOAT, STRING, STRUCT, VOID, ANY;

    public static Type typeConversion(Type typeA, Type typeB){
        if (typeA.equals(Type.ANY) && !typeB.equals(Type.ANY))
            return typeB;

        if (!typeA.equals(Type.ANY) && typeB.equals(Type.ANY))
            return typeA;

        return Type.ANY;
    }

    public static boolean checkTypeOperationRules(Type type, Operation operation) {
        return Constants.TYPE_OPERATION_RULES.get(type).contains(operation);
    }

    public static boolean checkTypeConsistency(Type typeA, Type typeB) {
        return typeA.equals(typeB) || typeA.equals(Type.ANY) || typeB.equals(Type.ANY);
    }
}
