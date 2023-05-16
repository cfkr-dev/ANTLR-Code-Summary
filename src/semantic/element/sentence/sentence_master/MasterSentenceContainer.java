package semantic.element.sentence.sentence_master;

import semantic.element.Function;
import semantic.element.Variable;
import semantic.element.sentence.*;
import semantic.element.sentence.conditional_branch.ConditionalBranch;
import semantic.element.sentence.literal.NumericIntegerConstant;
import semantic.element.sentence.literal.NumericRealConstant;
import semantic.element.sentence.literal.StringConstant;
import semantic.element.sentence.operation.ArithmeticOperation;
import semantic.element.sentence.operation.LogicOperation;
import semantic.element_interfaces.AssignableElement;
import semantic.element.sentence.sentence_interface.Sentence;
import semantic.element_interfaces.ProgramElement;
import semantic.element_master.MasterProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Type;

import java.util.List;

public abstract class MasterSentenceContainer extends MasterProgrammableElement {

    protected List<Sentence> sentences;

    AssignableElement newIntegerConstant(String value) {
        return new NumericIntegerConstant(value, this);
    }

    AssignableElement newRealConstant(String value) {
        return new NumericRealConstant(value, this);
    }

    AssignableElement newStringConstant(String value) {
        return new StringConstant(value, this);
    }

    Sentence addNewVariableDefinition(String type, String name){
        Variable variable = this.createNewVariable(type, name);

        if (variable != null) {
            VariableDefinition variableDefinition = new VariableDefinition(variable, this);
            this.sentences.add(variableDefinition);
            return variableDefinition;
        } else return null;
    }
    Sentence addNewVariableDefinitionAndAssign(String type, String name, AssignableElement assignableElement){
        Variable variable = this.createNewVariable(type, name);

        if (variable != null) {
            if (variable.setValue(assignableElement)) {
                VariableDefinitionAndAssign variableDefinitionAndAssign = new VariableDefinitionAndAssign(variable, this);
                this.sentences.add(variableDefinitionAndAssign);
                return variableDefinitionAndAssign;
            } else return null;
        } else return null;
    }
    Sentence addNewVariableAssign(String name, AssignableElement assignableElement){
        ProgramElement variable = this.getSymbolByNameAndElement(name, Element.VARIABLE);
        if (variable instanceof Variable) {
            if (((Variable) variable).setValue(assignableElement)) {
                VariableAssignation variableAssignation = new VariableAssignation((Variable) variable, this);
                this.sentences.add(variableAssignation);
                return variableAssignation;
            } else return null;
        } else return null;
    }

    Sentence addNewIfBranch(LogicOperation logicOperation) {
        IfBranch ifBranch = new IfBranch(logicOperation, this);
        this.sentences.add(ifBranch);
        return ifBranch;
    }

    Sentence addNewElseIfBranch(LogicOperation logicOperation, ConditionalBranch previous) {
        IfElseBranch ifElseBranch = new IfElseBranch(logicOperation, previous,  this);
        this.sentences.add(ifElseBranch);
        return ifElseBranch;
    }

    Sentence addNewElse(LogicOperation logicOperation, ConditionalBranch previous) {
        ElseBranch elseBranch = new ElseBranch(logicOperation, previous,  this);
        this.sentences.add(elseBranch);
        return elseBranch;
    }
    Sentence addNewForLoop(String indexVariableName, NumericIntegerConstant startValue,
                           LogicOperation conditionStop,
                           ArithmeticOperation afterIteration){

        ProgramElement variable = this.getSymbolByNameAndElement(indexVariableName, Element.VARIABLE);
        if (variable instanceof Variable) {
            if (((Variable) variable).setValue(startValue)) {
                ForLoop forLoop = new ForLoop((Variable) variable, conditionStop, afterIteration, this);
                this.sentences.add(forLoop);
                return forLoop;
            } else return null;
        } else return null;
    }

    Sentence addNewForLoop(String indexVariableType, String indexVariableName, NumericIntegerConstant startValue,
                           LogicOperation conditionStop,
                           ArithmeticOperation afterIteration){
        if (!this.hasThisSymbol(indexVariableName) && Type.valueOf(indexVariableType.toUpperCase()).equals(startValue.getType())) {
            ForLoop forLoop = new ForLoop(indexVariableType, indexVariableName, startValue, conditionStop, afterIteration, this);
            this.sentences.add(forLoop);
            return forLoop;
        } else return null;
    }
    Sentence addNewWhileLoop(LogicOperation logicOperation){
        WhileLoop whileLoop = new WhileLoop(logicOperation, this);
        this.sentences.add(whileLoop);
        return whileLoop;
    }
    private Sentence getNewDoWhileSentenceContainer() {
        return new DoWhileLoop(this);
    }

    Sentence addNewDoWhileLoop() {
        return getNewDoWhileSentenceContainer();
    }

    Sentence addNewDoWhileLoop(DoWhileLoop doWhileLoopSentenceContainer, LogicOperation logicOperation) {
        if (doWhileLoopSentenceContainer == null || logicOperation == null)
            return addNewDoWhileLoop();
        else {
            doWhileLoopSentenceContainer.setLogicOperation(logicOperation);
            this.sentences.add(doWhileLoopSentenceContainer);
            return doWhileLoopSentenceContainer;
        }
    }
    Sentence addNewFunctionCall(String functionName, List<AssignableElement> params){
        if (this.hasThisSymbol(functionName)){
            Function function = (Function) this.getSymbolByNameAndElement(functionName, Element.FUNCTION);
            if (function.checkCallingParams(params)) {
                FunctionCall functionCall = new FunctionCall(function, params, this);
                this.sentences.add(functionCall);
                return functionCall;
            } else return null;
        } else {
            OuterFunctionCall outerFunctionCall = new OuterFunctionCall(functionName, params, this);
            this.sentences.add(outerFunctionCall);
            return outerFunctionCall;
        }
    }
    Sentence addNewReturnPoint(AssignableElement returnElement){
        if (this.getSuperContext().getType().equals(returnElement.getType())) {
            ReturnPoint returnPoint = new ReturnPoint((Function) this.getSuperContext(), returnElement, this);
            this.sentences.add(returnPoint);
            return returnPoint;
        } else return null;
    }

    List<Sentence> getSentences() {
        return sentences;
    }
}
