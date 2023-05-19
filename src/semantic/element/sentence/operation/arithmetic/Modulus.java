package semantic.element.sentence.operation.arithmetic;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.operation_master.arithmetic.ArithmeticOperation;
import semantic.utils.enums.Operation;

public class Modulus extends ArithmeticOperation {
    public Modulus(ProgrammableElement context) {
        super(context);
        this.name = "MODULUS";
        this.symbol = "MOD";
        this.operationType = Operation.MODULUS;
    }
}