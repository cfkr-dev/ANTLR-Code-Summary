package semantic.element.sentence.function_sentence.function_call.master_function_call;

import semantic.element.sentence.function_sentence.function_call.FunctionCall;
import semantic.element.sentence.sentence_master.MasterSimpleSentence;
import semantic.element.element_interfaces.AssignableElement;

import java.util.List;

public abstract class MasterFunctionCall extends MasterSimpleSentence implements FunctionCall {

    protected String functionName;
    protected List<AssignableElement> callingParams;

    @Override
    public List<AssignableElement> getCallingParams() {
        return this.callingParams;
    }

    @Override
    public FunctionCall addNewParam(AssignableElement param) {
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
        return s.toString();
    }


}
