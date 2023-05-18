package semantic.element.sentence.operation.factory;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.arithmetic.*;
import semantic.element.sentence.operation.operation_master.arithmetic.ArithmeticOperation;

public class ArithmeticalOperationFactory {

    ProgrammableElement context;

    public ArithmeticalOperationFactory(ProgrammableElement context) {
        this.context = context;
    }

    public ArithmeticOperation sum() {
        return new Sum(this.context);
    }

    public ArithmeticOperation subtraction() {
        return new Subtraction(this.context);
    }

    public ArithmeticOperation multiplication() {
        return new Multiplication(this.context);
    }

    public ArithmeticOperation division() {
        return new Division(this.context);
    }

    public ArithmeticOperation modulus() {
        return new Modulus(this.context);
    }
}
