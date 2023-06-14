package semantic.utils.enums;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.utils.Constants;

public enum Type {
    INTEGER, FLOAT, STRING, STRUCT, VOID, ANY;

    public static Type typeConversion(Type typeA, Type typeB){
        if (typeA.equals(Type.ANY) && !typeB.equals(Type.ANY))
            return typeB;

        if (!typeA.equals(Type.ANY) && typeB.equals(Type.ANY))
            return typeA;

        if (!typeA.equals(Type.ANY)) {
            return typeA;
        }

        return Type.ANY;
    }

    public static boolean checkTypeOperationRules(Type type, Operation operation) {
        return Constants.TYPE_OPERATION_RULES.get(type).contains(operation);
    }

    public static boolean checkTypeConsistency(Type typeA, ProgramElement programElementB, boolean literalRestrictive) {
        if (literalRestrictive && programElementB.getElementType().equals(Element.LITERAL))
            return programElementB.getType().equals(typeA);
        else
            return Constants.TYPE_X_TYPE_RULES.get(typeA).contains(programElementB.getType());
    }

    public static boolean checkTypeCasting(String casterFunctionName) {
        return casterFunctionName.equals(Type.INTEGER.name())
                || casterFunctionName.equals(Type.FLOAT.name())
                || casterFunctionName.equals(Type.STRING.name())
                || casterFunctionName.equals(Type.STRUCT.name())
                || casterFunctionName.equals(Type.VOID.name());
    }

    public static boolean checkTypeConditional(AssignableElement logicOperation) {
        return logicOperation.getElementType().equals(Element.LOGICAL_OPERATION) || logicOperation.getElementType().equals(Element.COMPARISON_OPERATION);
    }
}
