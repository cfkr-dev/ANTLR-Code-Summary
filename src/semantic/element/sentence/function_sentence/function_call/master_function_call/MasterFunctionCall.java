package semantic.element.sentence.function_sentence.function_call.master_function_call;

import semantic.element.element_interfaces.AssignableElement;
import semantic.element.sentence.function_sentence.function_call.FunctionCall;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;

import java.util.List;

public abstract class MasterFunctionCall extends MasterSimpleSentence implements FunctionCall {

    protected String functionName;
    protected List<AssignableElement> callingParams;
    protected boolean malformed;
    protected boolean errorOnCreation;

    @Override
    public List<AssignableElement> getCallingParams() {
        return this.callingParams;
    }

    @Override
    public FunctionCall addParam(AssignableElement param) {
        if (param.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No es posible llamar a una función con una expresión malformada (" + param.getValue() + ")");
            this.errorOnCreation = true;
            return this;
        }
        this.callingParams.add(param);
        return this;
    }

    @Override
    public String getFunctionName() {
        return this.functionName;
    }

    @Override
    public String getValue() {
        return this.toString();
    }

    public String toString() {
        StringBuilder s = new StringBuilder(this.functionName + '(');
        boolean first = true;
        for (AssignableElement param: this.callingParams) {
            if (first) {
                s.append(param.getName());
                first = false;
            } else
                s.append(", ").append(param.getName());
        }
        return s.append(')').toString();
    }

    @Override
    public boolean isMalformed() {
        return this.malformed;
    }

    @Override
    public abstract FunctionCall call();
}
