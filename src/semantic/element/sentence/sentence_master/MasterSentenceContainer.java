package semantic.element.sentence.sentence_master;

import semantic.element.Function;
import semantic.element.Variable;
import semantic.element.sentence.*;
import semantic.element.sentence.conditional_branch.ConditionalBranch;
import semantic.element.sentence.function_call.FunctionCall;
import semantic.element.sentence.function_call.master_function_call.MasterFunctionCall;
import semantic.element.sentence.literal.NumericIntegerConstant;
import semantic.element.sentence.literal.NumericRealConstant;
import semantic.element.sentence.literal.StringConstant;
import semantic.element.sentence.operation.ArithmeticOperation;
import semantic.element.sentence.operation.LogicOperation;
import semantic.element.sentence.sentence_interface.Sentence;
import semantic.element_interfaces.AssignableElement;
import semantic.element_interfaces.ProgramElement;
import semantic.element_master.MasterProgrammableElement;
import semantic.enums.Element;
import semantic.enums.Type;

import java.util.List;

public abstract class MasterSentenceContainer extends MasterProgrammableElement {

    protected List<Sentence> sentences;

    public NumericIntegerConstant newIntegerConstant(String value) {
        return new NumericIntegerConstant(value, this);
    }

    public NumericRealConstant newRealConstant(String value) {
        return new NumericRealConstant(value, this);
    }

    public StringConstant newStringConstant(String value) {
        return new StringConstant(value, this);
    }

    public ArithmeticOperation newArithmeticOperation(String value) {return new ArithmeticOperation(value, this);}

    public LogicOperation newLogicalOperation(String value) {return new LogicOperation(value, this);}

    public FunctionCall newFunctionCall(String functionName) {
        if (this.hasThisSymbol(functionName)) {
            Function function = (Function) this.getSymbolByNameAndElement(functionName, Element.FUNCTION);
            return new InnerFunctionCall(function, this);
        } else
            return new OuterFunctionCall(functionName, this);
    }

    public Sentence addNewVariableDefinition(String type, String name){
        Variable variable = this.createNewVariable(type, name);

        if (variable != null) {
            VariableDefinition variableDefinition = new VariableDefinition(variable, this);
            this.sentences.add(variableDefinition);
            return variableDefinition;
        } else return null;
    }
    public Sentence addNewVariableDefinitionAndAssign(String type, String name, AssignableElement assignableElement){
        Variable variable = this.createNewVariable(type, name);

        if (variable != null) {
            if (variable.setValue(assignableElement)) {
                VariableDefinitionAndAssign variableDefinitionAndAssign = new VariableDefinitionAndAssign(variable, this);
                this.sentences.add(variableDefinitionAndAssign);
                return variableDefinitionAndAssign;
            } else return null;
        } else return null;
    }
    public Sentence addNewVariableAssign(String name, AssignableElement assignableElement){
        ProgramElement variable = this.getSymbolByNameAndElement(name, Element.VARIABLE);
        if (variable instanceof Variable) {
            if (((Variable) variable).setValue(assignableElement)) {
                VariableAssignation variableAssignation = new VariableAssignation((Variable) variable, this);
                this.sentences.add(variableAssignation);
                return variableAssignation;
            } else return null;
        } else return null;
    }

    public Sentence addNewIfBranch(LogicOperation logicOperation) {
        IfBranch ifBranch = new IfBranch(logicOperation, this);
        this.sentences.add(ifBranch);
        return ifBranch;
    }

    public Sentence addNewElseIfBranch(LogicOperation logicOperation, ConditionalBranch previous) {
        IfElseBranch ifElseBranch = new IfElseBranch(logicOperation, previous,  this);
        this.sentences.add(ifElseBranch);
        return ifElseBranch;
    }

    public Sentence addNewElse(ConditionalBranch previous) {
        ElseBranch elseBranch = new ElseBranch(previous,  this);
        this.sentences.add(elseBranch);
        return elseBranch;
    }
    public Sentence addNewForLoop(String indexVariableName, NumericIntegerConstant startValue,
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

    public Sentence addNewForLoop(String indexVariableType, String indexVariableName, NumericIntegerConstant startValue,
                           LogicOperation conditionStop,
                           ArithmeticOperation afterIteration){
        if (!this.hasThisSymbol(indexVariableName) && Type.valueOf(indexVariableType.toUpperCase()).equals(startValue.getType())) {
            ForLoop forLoop = new ForLoop(indexVariableType, indexVariableName, startValue, conditionStop, afterIteration, this);
            this.sentences.add(forLoop);
            return forLoop;
        } else return null;
    }
    public Sentence addNewWhileLoop(LogicOperation logicOperation){
        WhileLoop whileLoop = new WhileLoop(logicOperation, this);
        this.sentences.add(whileLoop);
        return whileLoop;
    }
    private Sentence getNewDoWhileSentenceContainer() {
        return new DoWhileLoop(this);
    }

    public Sentence addNewDoWhileLoop() {
        return getNewDoWhileSentenceContainer();
    }

    public Sentence addNewDoWhileLoop(DoWhileLoop doWhileLoopSentenceContainer, LogicOperation logicOperation) {
        if (doWhileLoopSentenceContainer == null || logicOperation == null)
            return addNewDoWhileLoop();
        else {
            doWhileLoopSentenceContainer.setLogicOperation(logicOperation);
            this.sentences.add(doWhileLoopSentenceContainer);
            return doWhileLoopSentenceContainer;
        }
    }

    public Sentence addNewFunctionCall(FunctionCall functionCall){
        this.sentences.add((MasterFunctionCall) functionCall);
        return (MasterFunctionCall) functionCall;
    }

    public Sentence addNewReturnPoint(AssignableElement returnElement){
        if (this.getSuperContext().getType().equals(returnElement.getType())) {
            ReturnPoint returnPoint = new ReturnPoint((Function) this.getSuperContext(), returnElement, this);
            this.sentences.add(returnPoint);
            return returnPoint;
        } else return null;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }
}
