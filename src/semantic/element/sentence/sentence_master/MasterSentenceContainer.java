package semantic.element.sentence.sentence_master;

import semantic.element.Function;
import semantic.element.Variable;
import semantic.element.sentence.*;
import semantic.element.sentence.literal.NumericIntegerConstant;
import semantic.element.sentence.literal.NumericRealConstant;
import semantic.element.sentence.literal.StringConstant;
import semantic.element.sentence.operation.ArithmeticOperation;
import semantic.element.sentence.operation.LogicOperation;
import semantic.element.sentence.sentence_interface.AssignableElement;
import semantic.element.sentence.conditional_branch.ConditionalBranch;
import semantic.element.sentence.sentence_interface.Sentence;
import semantic.element_master.MasterProgrammableElement;

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
    Sentence addNewVariableAssign(Variable variable, AssignableElement assignableElement){
        if (variable != null) {
            if (variable.setValue(assignableElement)) {
                VariableAssignation variableAssignation = new VariableAssignation(variable, this);
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
    Sentence addNewForLoop(Variable variable, LogicOperation logicOperation, ArithmeticOperation arithmeticOperation){

    }
    Sentence addNewWhileLoop(LogicOperation logicOperation){

    }
    Sentence addNewDoWhileLoop(LogicOperation logicOperation){

    }
    Sentence addNewFunctionCall(Function function, List<FunctionCallParams> params){

    }
    Sentence addNewReturnPoint(Returnable returnElement){

    }
}
