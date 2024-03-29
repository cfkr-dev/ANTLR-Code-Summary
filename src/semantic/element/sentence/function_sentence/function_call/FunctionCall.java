package semantic.element.sentence.function_sentence.function_call;

import semantic.element.element_interfaces.AssignableElement;

import java.util.List;

public interface FunctionCall extends AssignableElement {

    List<AssignableElement> getCallingParams();

    String getFunctionName();

    boolean isPartOfExpression();

    void notPartOfExpression();

    FunctionCall addParam(AssignableElement param);

    FunctionCall call();
}
