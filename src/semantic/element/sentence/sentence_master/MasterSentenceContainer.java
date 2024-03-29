package semantic.element.sentence.sentence_master;

import semantic.element.Constant;
import semantic.element.Function;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_master.MasterProgrammableElement;
import semantic.element.sentence.conditional_branch.ConditionalBranch;
import semantic.element.sentence.conditional_branch.ElseBranch;
import semantic.element.sentence.conditional_branch.ElseIfBranch;
import semantic.element.sentence.conditional_branch.IfBranch;
import semantic.element.sentence.function_sentence.ReturnPoint;
import semantic.element.sentence.function_sentence.function_call.FunctionCall;
import semantic.element.sentence.function_sentence.function_call.InnerFunctionCall;
import semantic.element.sentence.function_sentence.function_call.OuterFunctionCall;
import semantic.element.sentence.function_sentence.function_call.master_function_call.MasterFunctionCall;
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
import semantic.element.symbolReference.SymbolReference;
import semantic.element.variable.SimpleVariable;
import semantic.element.variable.StructVariable;
import semantic.element.variable.variable_interface.Variable;
import semantic.utils.enums.Element;
import semantic.utils.enums.Type;

import java.util.List;

public abstract class  MasterSentenceContainer extends MasterProgrammableElement {

    protected List<Sentence> sentences;
    public ArithmeticalOperationFactory newArithmeticOperation() {return new ArithmeticalOperationFactory(this);}
    public ComparisonOperationFactory newComparisonOperation() {return new ComparisonOperationFactory(this);}
    public LogicalOperationFactory newLogicalOperation() {return new LogicalOperationFactory(this);}

    /**
     * Usor para referenciar constantes o variables al crear expresiones
     * @param tokenID <br> "VARIABLE" -> referencia a variable <br>
     *                     "CONSTANT" -> referencia a constante
     */
    public SymbolReference newSymbolReference(String tokenID, String name, int line, int column) {
        if (this.hasThisSymbol(name)) {
            if (Element.valueOf(tokenID.toUpperCase()).equals(Element.VARIABLE)) {
                Variable variable = (Variable) this.getSymbolByNameAndElement(name, Element.VARIABLE);
                if (variable != null)
                    return new SymbolReference(variable, this, line, column);
            }
            else if (Element.valueOf(tokenID.toUpperCase()).equals(Element.CONSTANT)) {
                Constant constant = (Constant) this.getSymbolByNameAndElement(name, Element.CONSTANT);
                if (constant != null)
                    return new SymbolReference(constant, this, line, column);
            }
        }

        System.err.println("ERROR " + line + ":" + column + " => " + "No existe el símbolo al que se hace referencia (" + name + ")");

        SymbolReference symbolReferenceMalformed;

        if (Element.valueOf(tokenID.toUpperCase()).equals(Element.VARIABLE)) {
            Variable variable = new SimpleVariable("ANY", "malformed", this, line, column);
            symbolReferenceMalformed = new SymbolReference(variable, this, line, column);
        } else {
            Constant constant = new Constant("malformed", this.newStringConstant("malformed", line, column), this, line, column);
            symbolReferenceMalformed = new SymbolReference(constant, this, line, column);
        }
        symbolReferenceMalformed.setMalformed();
        return symbolReferenceMalformed;
    }

    /**
     * Usor para referenciar constantes o variables al crear expresiones
     * @param tokenID <br> "VARIABLE" -> referencia a variable <br>
     *                     "CONSTANT" -> referencia a constante
     */
    public AssignableElement newSymbolReference(String tokenID, String name, boolean forLoop, int line, int column) {
        if (forLoop && Element.valueOf(tokenID.toUpperCase()).equals(Element.VARIABLE)) {
            if (this.hasThisSymbol(name))
                return new SymbolReference((Variable) this.getSymbolByNameAndElement(name, Element.VARIABLE), this, line, column);
            else
                return new SymbolReference(new SimpleVariable("INTEGER", name, this, line, column), this, line, column);
        } else {
            return this.newSymbolReference(tokenID, name, line, column);
        }
    }

    /**
     * IMPORTANTE: UNA VEZ TERMINADO DE COLOCAR TODOS LOS PARAMETROS DE LLAMADA, EJECUTAR EL MÉTODO ".call()"
     */
    public FunctionCall newFunctionCall(String functionName, int line, int column) {
        if (this.hasThisSymbol(functionName)) {
            Function function = (Function) this.getSymbolByNameAndElement(functionName, Element.FUNCTION);

            if (function != null)
                return new InnerFunctionCall(function, this, line, column);
            else
                return new OuterFunctionCall(functionName, this, line, column);

        } else
            return new OuterFunctionCall(functionName, this, line, column);
    }

    public VariableDefinition addNewVariableDefinition(String type, String name, int line, int column){
        boolean error = false;

        Variable variable = this.createNewVariable(type, name, line, column);

        if (variable == null) {
            if (!Type.valueOf(type.toUpperCase()).equals(Type.STRUCT)) {
                variable = new SimpleVariable(type, name, this, line, column);
            } else {
                variable = new StructVariable(this, line, column);
            }
            variable.setMalformed();
            error = true;
        }

        VariableDefinition variableDefinition = new VariableDefinition(variable, this, line, column);

        if (error) {
            variableDefinition.setMalformed();
            return variableDefinition;
        }

        this.sentences.add(variableDefinition);
        return variableDefinition;
    }

    public VariableDefinitionAndAssign addNewVariableDefinitionAndAssign(String type, String name, AssignableElement assignableElement, int line, int column){
        boolean error = false;

        Variable variable = this.createNewVariable(type, name, line, column);

        if (variable == null) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar " + assignableElement.toString() + " a " + name + " por que " + name + " no ha sido declarado previamente");
            if (!Type.valueOf(type.toUpperCase()).equals(Type.STRUCT)) {
                variable = new SimpleVariable(type, name, this, line, column);
            } else {
                variable = new StructVariable(this, line, column);
            }
            variable.setMalformed();
            error = true;
        }

        if (Type.valueOf(type.toUpperCase()).equals(Type.STRUCT))
            variable = variable.variableClone(name);
        else
            variable = variable.variableClone();

        if (error) {
            variable.forceSetValue(assignableElement);
        } else if (!variable.setValue(assignableElement, this, line, column)) {
            error = true;
        }

        VariableDefinitionAndAssign variableDefinitionAndAssign = new VariableDefinitionAndAssign(variable, this, line, column);

        if (error) {
            variableDefinitionAndAssign.setMalformed();
            return variableDefinitionAndAssign;
        }

        this.sentences.add(variableDefinitionAndAssign);
        return variableDefinitionAndAssign;
    }

    public VariableAssignation addNewVariableAssign(String name, AssignableElement assignableElement, int line, int column){
        boolean error = false;

        Variable variable = (Variable) this.getSymbolByNameAndElement(name, Element.VARIABLE);

        if (variable == null) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar " + assignableElement.toString() + " a " + name + " por que " + name + " no ha sido declarado previamente");
            variable = new SimpleVariable("ANY", name, this, line, column);
            variable.setMalformed();
            error = true;
        }

        if (variable.getType().equals(Type.STRUCT))
            variable = variable.variableClone(name);
        else
            variable = variable.variableClone();

        if (error) {
            variable.forceSetValue(assignableElement);
        } else if (!variable.setValue(assignableElement, this, line, column)) {
            error = true;
        }

        VariableAssignation variableAssignation = new VariableAssignation(variable, this, line, column);

        if (error) {
            variableAssignation.setMalformed();
            return variableAssignation;
        }

        this.sentences.add(variableAssignation);
        return variableAssignation;
    }

    public IfBranch addNewIfBranch(AssignableElement logicOperation, int line, int column) {
        boolean error = false;

        if (!Type.checkTypeConditional(logicOperation)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "Se debe introducir una expresión lógica");
            error = true;
        }

        if (logicOperation.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada");
            error = true;
        }

        IfBranch ifBranch = new IfBranch(logicOperation, this, line, column);

        if (error) {
            ifBranch.setMalformed();
            return ifBranch;
        }

        this.sentences.add(ifBranch);
        return ifBranch;
    }

    public ElseIfBranch addNewElseIfBranch(AssignableElement logicOperation, ConditionalBranch previous, int line, int column) {
        boolean error = false;

        if (!Type.checkTypeConditional(logicOperation)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "Se debe introducir una expresión lógica");
            error = true;
        }

        if (logicOperation.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada");
            error = true;
        }

        if (previous.isMalformed())
            error = true;

        ElseIfBranch elseIfBranch = new ElseIfBranch(logicOperation, previous, this, line, column);

        if (error) {
            elseIfBranch.setMalformed();
            return elseIfBranch;
        }

        this.sentences.add(elseIfBranch);
        return elseIfBranch;
    }

    public ElseBranch addNewElseBranch(ConditionalBranch previous, int line, int column) {
        boolean error = previous.isMalformed();

        ElseBranch elseBranch = new ElseBranch(previous, this, line, column);

        if (error) {
            elseBranch.setMalformed();
            return elseBranch;
        }

        this.sentences.add(elseBranch);
        return elseBranch;
    }

    public ForLoop addNewForLoop(String indexVariableName, AssignableElement startValue,
                                 AssignableElement conditionStop,
                                 String afterLoopVariableName, AssignableElement afterLoopValue, int line, int column){

        boolean error = false;
        boolean createdIndexVariable = false;

        ForLoop forLoop = new ForLoop(this, line, column);

        Variable variableIndex = (Variable) this.getSymbolByNameAndElement(indexVariableName, Element.VARIABLE);

        if (variableIndex == null) {
            variableIndex = new SimpleVariable("integer", indexVariableName, this, line, column);
            this.addToSymbolTable(variableIndex);
            forLoop.addToSymbolTable(variableIndex);
            createdIndexVariable = true;
        }

        if (!variableIndex.getType().equals(Type.INTEGER)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "La variable índice debe ser de tipo integer");
            variableIndex.setMalformed();
            error = true;
        }

        variableIndex = variableIndex.variableClone();

        if (error) {
            variableIndex.forceSetValue(startValue);
        } else if (!variableIndex.setValue(startValue, this, line, column)) {
            error = true;
        }

        if (!Type.checkTypeConditional(conditionStop)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "Debe incluir una expresión lógica de parada como segundo argumento");
            error = true;
        }

        if (conditionStop.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada como condición de parada");
            error = true;
        }

        Variable afterIterationVariable = new SimpleVariable("integer", indexVariableName, this, line, column);

        if (!afterLoopVariableName.equals(indexVariableName)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "La variable índice nunca es actualizada");
            error = true;
        }

        if (afterLoopValue.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada como expresión de actualización");
            error = true;
        }

        afterIterationVariable = afterIterationVariable.variableClone();

        if (error) {
            afterIterationVariable.forceSetValue(afterLoopValue);
        } else if (!afterIterationVariable.setValue(afterLoopValue, this, line, column)) {
            error = true;
        }

        VariableAssignation afterIterationAssign = new VariableAssignation(afterIterationVariable, this, line, column);

        forLoop
            .setIndexVariable(new VariableAssignation(variableIndex, this, line, column), createdIndexVariable)
            .setConditionStop(conditionStop)
            .setAssignationAfterIteration(afterIterationAssign);

        if (error) {
            forLoop.setMalformed();
            return forLoop;
        }

        this.sentences.add(forLoop);
        return forLoop;

    }

    public ForLoop addNewForLoop(String indexVariableType, String indexVariableName, AssignableElement startValue,
                                  AssignableElement conditionStop,
                                  String afterLoopVariableName, AssignableElement afterLoopValue, int line, int column){
        boolean error = false;
        boolean createdIndexVariable = false;

        ForLoop forLoop = new ForLoop(this, line, column);

        SimpleVariable variableIndex = new SimpleVariable(indexVariableType, indexVariableName, this, line, column);

        if (this.hasThisSymbol(indexVariableName)){
            this.updateSymbolTable(variableIndex);
            forLoop.updateSymbolTable(variableIndex);
        } else {
            this.addToSymbolTable(variableIndex);
            forLoop.addToSymbolTable(variableIndex);
            createdIndexVariable = true;
        }

        if (!variableIndex.getType().equals(Type.INTEGER)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "La variable índice debe ser de tipo integer");
            variableIndex.setMalformed();
            error = true;
        }

        variableIndex = (SimpleVariable) variableIndex.variableClone();

        if (error) {
            variableIndex.forceSetValue(startValue);
        } else if (!variableIndex.setValue(startValue, this, line, column)) {
            error = true;
        }

        if (!Type.checkTypeConditional(conditionStop)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "Debe incluir una expresión lógica de parada como segundo argumento");
            error = true;
        }

        if (conditionStop.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada como condición de parada");
            error = true;
        }

        Variable afterIterationVariable = new SimpleVariable("integer", indexVariableName, this, line, column);

        if (!afterLoopVariableName.equals(indexVariableName)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "La variable índice nunca es actualizada");
            error = true;
        }

        if (afterLoopValue.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada como expresión de actualización");
            error = true;
        }

        afterIterationVariable = afterIterationVariable.variableClone();

        if (error) {
            afterIterationVariable.forceSetValue(afterLoopValue);
        } else if (!afterIterationVariable.setValue(afterLoopValue, this, line, column)) {
            error = true;
        }

        VariableAssignation afterIterationAssign = new VariableAssignation(afterIterationVariable, this, line, column);

        forLoop
            .setIndexVariable(new VariableDefinitionAndAssign(variableIndex, this, line, column), createdIndexVariable)
            .setConditionStop(conditionStop)
            .setAssignationAfterIteration(afterIterationAssign);

        if (error) {
            forLoop.setMalformed();
            return forLoop;
        }

        this.sentences.add(forLoop);
        return forLoop;
    }

    public ForLoop addNewForLoop(String indexVariableType, String indexVariableName,
                                  AssignableElement conditionStop,
                                  String afterLoopVariableName, AssignableElement afterLoopValue, int line, int column){
        boolean error = false;
        boolean createdIndexVariable = false;

        ForLoop forLoop = new ForLoop(this, line, column);

        SimpleVariable variableIndex = new SimpleVariable(indexVariableType, indexVariableName, this, line, column);

        if (this.hasThisSymbol(indexVariableName)){
            this.updateSymbolTable(variableIndex);
            forLoop.updateSymbolTable(variableIndex);
        } else {
            this.addToSymbolTable(variableIndex);
            forLoop.addToSymbolTable(variableIndex);
            createdIndexVariable = true;
        }

        if (variableIndex == null) {
            variableIndex = new SimpleVariable("integer", indexVariableName, this, line, column);
            variableIndex.setMalformed();
            error = true;
        }

        if (!variableIndex.getType().equals(Type.INTEGER)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "La variable índice debe ser de tipo integer");
            variableIndex.setMalformed();
            error = true;
        }

        variableIndex = (SimpleVariable) variableIndex.variableClone();

        if (error) {
            variableIndex.forceSetValue(this.newIntegerConstant("0", line, column));
        } else if (!variableIndex.setValue(this.newIntegerConstant("0", line, column), this, line, column)) {
            error = true;
        }

        if (!Type.checkTypeConditional(conditionStop)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "Debe incluir una expresión lógica de parada como segundo argumento");
            error = true;
        }

        if (conditionStop.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada como condición de parada");
            error = true;
        }

        Variable afterIterationVariable = new SimpleVariable("integer", indexVariableName, this, line, column);

        if (!afterLoopVariableName.equals(indexVariableName)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "La variable índice nunca es actualizada");
            error = true;
        }

        if (afterLoopValue.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada como expresión de actualización");
            error = true;
        }

        afterIterationVariable = afterIterationVariable.variableClone();

        if (error) {
            afterIterationVariable.forceSetValue(afterLoopValue);
        } else if (!afterIterationVariable.setValue(afterLoopValue, this, line, column)) {
            error = true;
        }

        VariableAssignation afterIterationAssign = new VariableAssignation(afterIterationVariable, this, line, column);

        forLoop
            .setIndexVariable(new VariableDefinition(variableIndex, this, line, column), createdIndexVariable)
            .setConditionStop(conditionStop)
            .setAssignationAfterIteration(afterIterationAssign);

        if (error) {
            forLoop.setMalformed();
            return forLoop;
        }

        this.sentences.add(forLoop);
        return forLoop;
    }

    public WhileLoop addNewWhileLoop(AssignableElement logicOperation, int line, int column){
        boolean error = false;

        if (!Type.checkTypeConditional(logicOperation)) {
            System.err.println("ERROR " + line + ":" + column + " => " + "Se debe introducir una expresión lógica");
            error = true;
        }

        if (logicOperation.isMalformed()) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada");
            error = true;
        }

        WhileLoop whileLoop = new WhileLoop(logicOperation, this, line, column);

        if (error) {
            whileLoop.setMalformed();
            return whileLoop;
        }

        this.sentences.add(whileLoop);
        return whileLoop;
    }

    /**
     * IMPORTANTE: UNA VEZ INSERTADAS TODAS LAS SENTENCIAS LLAMAR AL MÉTODO INTERNO ".createDoWhileLoop()" CON SU OPERACIÓN LÓGICA CORRESPONDIENTE
     */
    public DoWhileLoop addNewDoWhileLoop(int line, int column) {
        DoWhileLoop doWhileLoop = new DoWhileLoop(this, line, column);
        if (!this.malformed)
            this.sentences.add(doWhileLoop);
        return doWhileLoop;
    }

    public FunctionCall addNewFunctionCall(FunctionCall functionCall){
        Boolean error = false;

        if (functionCall.isMalformed())
            error = true;

        if (error) {
            functionCall.setMalformed();
            return functionCall;
        }
        functionCall.notPartOfExpression();
        this.sentences.add((MasterFunctionCall) functionCall);
        return functionCall;
    }

    public ReturnPoint addNewReturnPoint(AssignableElement returnElement, int line, int column){
        boolean error = false;

        if (this.getSuperContext().getType().equals(Type.VOID)) {
            System.err.println("ERROR " + line + ":" + column + " => La función " + this.getSuperContext().getName() + " es de tipo " + Type.VOID + ". No se esperaba un punto de retorno");
            error = true;
        }

        if (this.hasReturnPoint && !error) {
            System.err.println("ERROR " + line + ":" + column + " => Una función no puede tener más de un punto de retorno dentro de un mismo contexto");
            error = true;
        }

        if (!Type.checkTypeConsistency(this.getSuperContext().getType(), returnElement, true) && !error) {
            System.err.println("ERROR " + line + ":" + column + " => " + "El tipo del elemento devuelto (" + returnElement.getType() + ") debe concordar con el tipo de la función (" + this.getSuperContext().getType() + ")");
            error = true;
        }

        if (returnElement.isMalformed() && !error) {
            System.err.println("ERROR " + line + ":" + column + " => " + "No se puede asignar una expresión malformada");
            error = true;
        }

        ReturnPoint returnPoint = new ReturnPoint((Function) this.getSuperContext(), returnElement, this, line, column);
        this.setReturnPoint();

        if (error) {
            returnElement.setMalformed();
            return returnPoint;
        }

        this.sentences.add(returnPoint);
        return returnPoint;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

}
