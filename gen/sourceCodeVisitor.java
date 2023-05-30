// Generated from /data_linux/UNIVERSIDAD/GII_GIS_5/PL/Practicas/practica_obligatoria/src/sourceCode.g4 by ANTLR 4.12.0

import semantic.utils.Constants;
import semantic.element.Function;
import semantic.element.Program;
import semantic.element.element_interfaces.AssignableElement;
import semantic.element.element_interfaces.ProgrammableElement;
import semantic.element.literal.literal_master.Literal;
import semantic.element.sentence.conditional_branch.ConditionalBranch;
import semantic.element.sentence.function_sentence.function_call.FunctionCall;
import semantic.element.sentence.loop_sentence.DoWhileLoop;
import semantic.element.sentence.loop_sentence.ForLoop;
import semantic.element.sentence.operation.operation_master.arithmetic.ArithmeticOperation;
import semantic.element.sentence.operation.operation_master.comparison.ComparisonOperation;
import semantic.element.sentence.operation.operation_master.logical.BinaryLogicalOperation;
import semantic.element.sentence.sentence_master.MasterSentenceContainer;
import semantic.element.variable.StructVariable;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link sourceCodeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface sourceCodeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#program_prime}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram_prime(sourceCodeParser.Program_primeContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(sourceCodeParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#program_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram_aux(sourceCodeParser.Program_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#dcllist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcllist(sourceCodeParser.DcllistContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#dcllist_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcllist_aux(sourceCodeParser.Dcllist_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#funlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunlist(sourceCodeParser.FunlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#funlist_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunlist_aux(sourceCodeParser.Funlist_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#sentlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentlist(sourceCodeParser.SentlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#sentlist_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentlist_aux(sourceCodeParser.Sentlist_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#dcl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcl(sourceCodeParser.DclContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#cte}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCte(sourceCodeParser.CteContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(sourceCodeParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#vardef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardef(sourceCodeParser.VardefContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#vardef_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardef_aux(sourceCodeParser.Vardef_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#simple_vardef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_vardef(sourceCodeParser.Simple_vardefContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#struct_vardef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStruct_vardef(sourceCodeParser.Struct_vardefContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#struct_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStruct_def(sourceCodeParser.Struct_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#dcllist_struct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcllist_struct(sourceCodeParser.Dcllist_structContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#dcllist_struct_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcllist_struct_aux(sourceCodeParser.Dcllist_struct_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#dcl_struct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcl_struct(sourceCodeParser.Dcl_structContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#simpvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpvalue(sourceCodeParser.SimpvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#tbas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTbas(sourceCodeParser.TbasContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#tvoid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTvoid(sourceCodeParser.TvoidContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#funcdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdef(sourceCodeParser.FuncdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#funcdef_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdef_aux(sourceCodeParser.Funcdef_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#funchead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunchead(sourceCodeParser.FuncheadContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#funchead_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunchead_aux(sourceCodeParser.Funchead_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#typedef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedef(sourceCodeParser.TypedefContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#typedef_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedef_aux(sourceCodeParser.Typedef_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#mainhead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainhead(sourceCodeParser.MainheadContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#mainhead_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainhead_aux(sourceCodeParser.Mainhead_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode(sourceCodeParser.CodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#code_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode_aux(sourceCodeParser.Code_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#sent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSent(sourceCodeParser.SentContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#vardef_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardef_code(sourceCodeParser.Vardef_codeContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#simple_vardef_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_vardef_code(sourceCodeParser.Simple_vardef_codeContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#asig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsig(sourceCodeParser.AsigContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#vardef_and_asig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardef_and_asig(sourceCodeParser.Vardef_and_asigContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#funccall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunccall(sourceCodeParser.FunccallContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#funccall_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunccall_aux(sourceCodeParser.Funccall_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#subpparamlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubpparamlist(sourceCodeParser.SubpparamlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#explist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplist(sourceCodeParser.ExplistContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#explist_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplist_aux(sourceCodeParser.Explist_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#return_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_func(sourceCodeParser.Return_funcContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(sourceCodeParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#if_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_aux(sourceCodeParser.If_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse(sourceCodeParser.ElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#else_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_aux(sourceCodeParser.Else_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#if_else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_else(sourceCodeParser.If_elseContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(sourceCodeParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#dowhile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDowhile(sourceCodeParser.DowhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor(sourceCodeParser.ForContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#for_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_aux(sourceCodeParser.For_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#expcond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpcond(sourceCodeParser.ExpcondContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#expcond_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpcond_aux(sourceCodeParser.Expcond_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#oplog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOplog(sourceCodeParser.OplogContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#factorcond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorcond(sourceCodeParser.FactorcondContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#factorcond_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorcond_aux(sourceCodeParser.Factorcond_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#opcomp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpcomp(sourceCodeParser.OpcompContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(sourceCodeParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#exp_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_aux(sourceCodeParser.Exp_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(sourceCodeParser.OpContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(sourceCodeParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link sourceCodeParser#simpvalue_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpvalue_code(sourceCodeParser.Simpvalue_codeContext ctx);
}