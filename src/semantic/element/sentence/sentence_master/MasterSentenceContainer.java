package semantic.element.sentence.sentence_master;

import semantic.element.Function;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgramElement;
import semantic.element.element_master.MasterProgrammableElement;
import semantic.element.sentence.conditional_branch.ConditionalBranch;
import semantic.element.sentence.conditional_branch.ElseBranch;
import semantic.element.sentence.conditional_branch.IfBranch;
import semantic.element.sentence.conditional_branch.IfElseBranch;
import semantic.element.sentence.function_sentence.ReturnPoint;
import semantic.element.sentence.function_sentence.function_call.FunctionCall;
import semantic.element.sentence.function_sentence.function_call.InnerFunctionCall;
import semantic.element.sentence.function_sentence.function_call.OuterFunctionCall;
import semantic.element.sentence.function_sentence.function_call.master_function_call.MasterFunctionCall;
import semantic.element.sentence.literal.NumericIntegerConstant;
import semantic.element.sentence.literal.NumericRealConstant;
import semantic.element.sentence.literal.StringConstant;
import semantic.element.sentence.loop_sentence.DoWhileLoop;
import semantic.element.sentence.loop_sentence.ForLoop;
import semantic.element.sentence.loop_sentence.WhileLoop;
import semantic.element.sentence.operation.factory.ArithmeticalOperationFactory;
import semantic.element.sentence.operation.factory.ComparisonOperationFactory;
import semantic.element.sentence.operation.factory.LogicalOperationFactory;
import semantic.element.sentence.sentence_interface.Sentence;
import semantic.element.sentence.variable_sentence.VariableAssignation;
import semantic.element.sentence.variable_sentence.VariableDefinition;
import semantic.element.sentence.variable_sentence.VariableDefinitionAndAssign;
import semantic.element.variable.SimpleVariable;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

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

    public ArithmeticalOperationFactory newArithmeticOperation() {return new ArithmeticalOperationFactory(this);}
    public ComparisonOperationFactory newComparisonOperation() {return new ComparisonOperationFactory(this);}

    public LogicalOperationFactory newLogicalOperation() {return new LogicalOperationFactory(this);}

    /**
     * Usor para referenciar constantes o variables al crear expresiones
     * @param tokenID <br> "VARIABLE" -> referencia a variable <br>
     *                     "CONSTANT" -> referencia a constante
     */
    public AssignableElement newSymbolReference(String tokenID, String name) {
        if (!this.hasThisSymbol(name)) {
            System.err.println("No existe el símbolo al que se hace referencia");
            return null;
        }

        if (Element.valueOf(tokenID.toUpperCase()).equals(Element.VARIABLE))
            return (AssignableElement) this.getSymbolByNameAndElement(name, Element.VARIABLE);
        else if (Element.valueOf(tokenID.toUpperCase()).equals(Element.CONSTANT))
            return (AssignableElement) this.getSymbolByNameAndElement(name, Element.CONSTANT);

        return null;
    }

    public FunctionCall newFunctionCall(String functionName) {
        if (this.hasThisSymbol(functionName)) {
            Function function = (Function) this.getSymbolByNameAndElement(functionName, Element.FUNCTION);
            return new InnerFunctionCall(function, this);
        } else
            return new OuterFunctionCall(functionName, this);
    }

    public Sentence addNewVariableDefinition(String type, String name){
        SimpleVariable variable = (SimpleVariable) this.createNewVariable(type, name);

        if (variable == null)
            return null;

        VariableDefinition variableDefinition = new VariableDefinition(variable, this);
        this.sentences.add(variableDefinition);
        return variableDefinition;
    }

    public Sentence addNewVariableDefinitionAndAssign(String type, String name, AssignableElement assignableElement){
        SimpleVariable variable = (SimpleVariable) this.createNewVariable(type, name);

        if (variable == null)
            return null;

        if (!variable.setValue(assignableElement))
            return null;

        VariableDefinitionAndAssign variableDefinitionAndAssign = new VariableDefinitionAndAssign(variable, this);
        this.sentences.add(variableDefinitionAndAssign);
        return variableDefinitionAndAssign;
    }

    public Sentence addNewVariableAssign(String name, AssignableElement assignableElement){
        ProgramElement variable = this.getSymbolByNameAndElement(name, Element.VARIABLE);

        if (!((SimpleVariable) variable).setValue(assignableElement))
            return null;

        VariableAssignation variableAssignation = new VariableAssignation((SimpleVariable) variable, this);
        this.sentences.add(variableAssignation);
        return variableAssignation;
    }

    /**
     * Usar para definir el tercer parametro del bucle for
     */
    public VariableAssignation newFictionalVariableAssign(String name, AssignableElement assignableElement){
        SimpleVariable variable = new SimpleVariable("ANY", name, this);
        variable.setValue(assignableElement);
        return new VariableAssignation(variable, this);
    }

    public Sentence addNewIfBranch(AssignableElement logicOperation) {
        if (!(logicOperation.getElementType().equals(Element.LOGICAL_OPERATION) || logicOperation.getElementType().equals(Element.COMPARISON_OPERATION))) {
            System.err.println("Se debe introducir una expresión lógica");
            return null;
        }

        IfBranch ifBranch = new IfBranch(logicOperation, this);
        this.sentences.add(ifBranch);
        return ifBranch;
    }

    public Sentence addNewElseIfBranch(AssignableElement logicOperation, ConditionalBranch previous) {
        if (!(logicOperation.getElementType().equals(Element.LOGICAL_OPERATION) || logicOperation.getElementType().equals(Element.COMPARISON_OPERATION))) {
            System.err.println("Se debe introducir una expresión lógica");
            return null;
        }

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
                           AssignableElement conditionStop,
                           VariableAssignation assignationAfterIteration) {

        if (!this.hasThisSymbol(indexVariableName)) {
            System.err.println("La variable indice del bucle tiene que estar declarada anteriormente una variable de tipo integer");
            return null;
        }

        ProgramElement variable = this.getSymbolByNameAndElement(indexVariableName, Element.VARIABLE);

        if (!variable.getType().equals(Type.INTEGER)) {
            System.err.println("La variable indice debe ser de tipo integer");
            return null;
        }

        if (!(conditionStop.getElementType().equals(Element.LOGICAL_OPERATION) || conditionStop.getElementType().equals(Element.COMPARISON_OPERATION))) {
            System.err.println("Debe incluir una expresión lógica de parada como segundo argumento");
            return null;
        }

        if (!assignationAfterIteration.getVariable().getName().equals(indexVariableName)) {
            System.err.println("La variable indice nunca es actualizada");
            return null;
        }

        if (!((SimpleVariable) variable).setValue(startValue))
            return null;

        ForLoop forLoop = new ForLoop((SimpleVariable) variable, conditionStop, assignationAfterIteration, this);
        this.sentences.add(forLoop);
        return forLoop;

    }

    public Sentence addNewForLoop(String indexVariableType, String indexVariableName, NumericIntegerConstant startValue,
                                  AssignableElement conditionStop,
                                  VariableAssignation assignationAfterIteration){

        if (!Type.valueOf(indexVariableType.toUpperCase()).equals(Type.INTEGER)) {
            System.err.println("La variable indice debe ser de tipo integer");
            return null;
        }

        SimpleVariable variable = new SimpleVariable(indexVariableType, indexVariableName, this);

        if (!(conditionStop.getElementType().equals(Element.LOGICAL_OPERATION) || conditionStop.getElementType().equals(Element.COMPARISON_OPERATION))) {
            System.err.println("Debe incluir una expresión lógica de parada como segundo argumento");
            return null;
        }

        if (!assignationAfterIteration.getVariable().getName().equals(indexVariableName)) {
            System.err.println("La variable indice nunca es actualizada");
            return null;
        }

        if (!(variable).setValue(startValue)) {
            System.err.println("Imposible asignar valor de inicio a la variable indice");
            return null;
        }

        ForLoop forLoop = new ForLoop(variable, conditionStop, assignationAfterIteration, this);
        this.sentences.add(forLoop);
        return forLoop;
    }
    public Sentence addNewWhileLoop(AssignableElement logicOperation){
        if (!(logicOperation.getElementType().equals(Element.LOGICAL_OPERATION) || logicOperation.getElementType().equals(Element.COMPARISON_OPERATION))) {
            System.err.println("Se debe introducir una expresión lógica");
            return null;
        }

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

    public Sentence addNewDoWhileLoop(DoWhileLoop doWhileLoopSentenceContainer, AssignableElement logicOperation) {
        if (doWhileLoopSentenceContainer == null || logicOperation == null)
            return addNewDoWhileLoop();
        else {
            if (!(logicOperation.getElementType().equals(Element.LOGICAL_OPERATION) || logicOperation.getElementType().equals(Element.COMPARISON_OPERATION))) {
                System.err.println("Se debe introducir una expresión lógica");
                return null;
            }

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
        if (!this.getSuperContext().getType().equals(returnElement.getType())) {
            System.err.println("El tipo del elemento devuelto debe concordar con el tipo de la función");
            return null;
        }

        ReturnPoint returnPoint = new ReturnPoint((Function) this.getSuperContext(), returnElement, this);
        this.sentences.add(returnPoint);
        return returnPoint;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }
}
