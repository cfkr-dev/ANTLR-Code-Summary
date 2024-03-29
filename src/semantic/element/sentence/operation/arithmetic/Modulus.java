package semantic.element.sentence.operation.arithmetic;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.arithmetic.ArithmeticOperation;
import semantic.utils.enums.Operation;

public class Modulus extends ArithmeticOperation {
    public Modulus(ProgrammableElement context, int line, int column) {
        super(context, line, column);
        this.name = "MODULUS_" + line + "_" + column;
        this.symbol = "MOD";
        this.operationType = Operation.MODULUS;

    }
}
