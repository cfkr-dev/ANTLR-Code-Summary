package semantic.element.sentence.operation.factory;

import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.sentence.operation.arithmetic.*;
import semantic.element.sentence.operation.operation_master.arithmetic.ArithmeticOperation;

public class ArithmeticalOperationFactory {

    ProgrammableElement context;

    public ArithmeticalOperationFactory(ProgrammableElement context) {
        this.context = context;
    }

    public ArithmeticOperation sum(int line, int column) {
        return new Sum(this.context, line, column);
    }

    public ArithmeticOperation subtraction(int line, int column) {
        return new Subtraction(this.context, line, column);
    }

    public ArithmeticOperation multiplication(int line, int column) {
        return new Multiplication(this.context, line, column);
    }

    public ArithmeticOperation division(int line, int column) {
        return new Division(this.context, line, column);
    }

    public ArithmeticOperation modulus(int line, int column) {
        return new Modulus(this.context, line, column);
    }
}
