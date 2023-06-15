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

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link sourceCodeParser}.
 */
public interface sourceCodeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(sourceCodeParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(sourceCodeParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#program_aux}.
	 * @param ctx the parse tree
	 */
	void enterProgram_aux(sourceCodeParser.Program_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#program_aux}.
	 * @param ctx the parse tree
	 */
	void exitProgram_aux(sourceCodeParser.Program_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#dcllist}.
	 * @param ctx the parse tree
	 */
	void enterDcllist(sourceCodeParser.DcllistContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#dcllist}.
	 * @param ctx the parse tree
	 */
	void exitDcllist(sourceCodeParser.DcllistContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#dcllist_aux}.
	 * @param ctx the parse tree
	 */
	void enterDcllist_aux(sourceCodeParser.Dcllist_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#dcllist_aux}.
	 * @param ctx the parse tree
	 */
	void exitDcllist_aux(sourceCodeParser.Dcllist_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#funlist}.
	 * @param ctx the parse tree
	 */
	void enterFunlist(sourceCodeParser.FunlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#funlist}.
	 * @param ctx the parse tree
	 */
	void exitFunlist(sourceCodeParser.FunlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#funlist_aux}.
	 * @param ctx the parse tree
	 */
	void enterFunlist_aux(sourceCodeParser.Funlist_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#funlist_aux}.
	 * @param ctx the parse tree
	 */
	void exitFunlist_aux(sourceCodeParser.Funlist_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#sentlist}.
	 * @param ctx the parse tree
	 */
	void enterSentlist(sourceCodeParser.SentlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#sentlist}.
	 * @param ctx the parse tree
	 */
	void exitSentlist(sourceCodeParser.SentlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#sentlist_aux}.
	 * @param ctx the parse tree
	 */
	void enterSentlist_aux(sourceCodeParser.Sentlist_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#sentlist_aux}.
	 * @param ctx the parse tree
	 */
	void exitSentlist_aux(sourceCodeParser.Sentlist_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#dcl}.
	 * @param ctx the parse tree
	 */
	void enterDcl(sourceCodeParser.DclContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#dcl}.
	 * @param ctx the parse tree
	 */
	void exitDcl(sourceCodeParser.DclContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#cte}.
	 * @param ctx the parse tree
	 */
	void enterCte(sourceCodeParser.CteContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#cte}.
	 * @param ctx the parse tree
	 */
	void exitCte(sourceCodeParser.CteContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(sourceCodeParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(sourceCodeParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#vardef}.
	 * @param ctx the parse tree
	 */
	void enterVardef(sourceCodeParser.VardefContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#vardef}.
	 * @param ctx the parse tree
	 */
	void exitVardef(sourceCodeParser.VardefContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#vardef_aux}.
	 * @param ctx the parse tree
	 */
	void enterVardef_aux(sourceCodeParser.Vardef_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#vardef_aux}.
	 * @param ctx the parse tree
	 */
	void exitVardef_aux(sourceCodeParser.Vardef_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#simple_vardef}.
	 * @param ctx the parse tree
	 */
	void enterSimple_vardef(sourceCodeParser.Simple_vardefContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#simple_vardef}.
	 * @param ctx the parse tree
	 */
	void exitSimple_vardef(sourceCodeParser.Simple_vardefContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#struct_vardef}.
	 * @param ctx the parse tree
	 */
	void enterStruct_vardef(sourceCodeParser.Struct_vardefContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#struct_vardef}.
	 * @param ctx the parse tree
	 */
	void exitStruct_vardef(sourceCodeParser.Struct_vardefContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#struct_def}.
	 * @param ctx the parse tree
	 */
	void enterStruct_def(sourceCodeParser.Struct_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#struct_def}.
	 * @param ctx the parse tree
	 */
	void exitStruct_def(sourceCodeParser.Struct_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#dcllist_struct}.
	 * @param ctx the parse tree
	 */
	void enterDcllist_struct(sourceCodeParser.Dcllist_structContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#dcllist_struct}.
	 * @param ctx the parse tree
	 */
	void exitDcllist_struct(sourceCodeParser.Dcllist_structContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#dcllist_struct_aux}.
	 * @param ctx the parse tree
	 */
	void enterDcllist_struct_aux(sourceCodeParser.Dcllist_struct_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#dcllist_struct_aux}.
	 * @param ctx the parse tree
	 */
	void exitDcllist_struct_aux(sourceCodeParser.Dcllist_struct_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#dcl_struct}.
	 * @param ctx the parse tree
	 */
	void enterDcl_struct(sourceCodeParser.Dcl_structContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#dcl_struct}.
	 * @param ctx the parse tree
	 */
	void exitDcl_struct(sourceCodeParser.Dcl_structContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#simpvalue}.
	 * @param ctx the parse tree
	 */
	void enterSimpvalue(sourceCodeParser.SimpvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#simpvalue}.
	 * @param ctx the parse tree
	 */
	void exitSimpvalue(sourceCodeParser.SimpvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#tbas}.
	 * @param ctx the parse tree
	 */
	void enterTbas(sourceCodeParser.TbasContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#tbas}.
	 * @param ctx the parse tree
	 */
	void exitTbas(sourceCodeParser.TbasContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#tvoid}.
	 * @param ctx the parse tree
	 */
	void enterTvoid(sourceCodeParser.TvoidContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#tvoid}.
	 * @param ctx the parse tree
	 */
	void exitTvoid(sourceCodeParser.TvoidContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#funcdef}.
	 * @param ctx the parse tree
	 */
	void enterFuncdef(sourceCodeParser.FuncdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#funcdef}.
	 * @param ctx the parse tree
	 */
	void exitFuncdef(sourceCodeParser.FuncdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#funcdef_aux}.
	 * @param ctx the parse tree
	 */
	void enterFuncdef_aux(sourceCodeParser.Funcdef_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#funcdef_aux}.
	 * @param ctx the parse tree
	 */
	void exitFuncdef_aux(sourceCodeParser.Funcdef_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#funchead}.
	 * @param ctx the parse tree
	 */
	void enterFunchead(sourceCodeParser.FuncheadContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#funchead}.
	 * @param ctx the parse tree
	 */
	void exitFunchead(sourceCodeParser.FuncheadContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#funchead_aux}.
	 * @param ctx the parse tree
	 */
	void enterFunchead_aux(sourceCodeParser.Funchead_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#funchead_aux}.
	 * @param ctx the parse tree
	 */
	void exitFunchead_aux(sourceCodeParser.Funchead_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#typedef}.
	 * @param ctx the parse tree
	 */
	void enterTypedef(sourceCodeParser.TypedefContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#typedef}.
	 * @param ctx the parse tree
	 */
	void exitTypedef(sourceCodeParser.TypedefContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#typedef_aux}.
	 * @param ctx the parse tree
	 */
	void enterTypedef_aux(sourceCodeParser.Typedef_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#typedef_aux}.
	 * @param ctx the parse tree
	 */
	void exitTypedef_aux(sourceCodeParser.Typedef_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#mainhead}.
	 * @param ctx the parse tree
	 */
	void enterMainhead(sourceCodeParser.MainheadContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#mainhead}.
	 * @param ctx the parse tree
	 */
	void exitMainhead(sourceCodeParser.MainheadContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#mainhead_aux}.
	 * @param ctx the parse tree
	 */
	void enterMainhead_aux(sourceCodeParser.Mainhead_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#mainhead_aux}.
	 * @param ctx the parse tree
	 */
	void exitMainhead_aux(sourceCodeParser.Mainhead_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(sourceCodeParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(sourceCodeParser.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#code_aux}.
	 * @param ctx the parse tree
	 */
	void enterCode_aux(sourceCodeParser.Code_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#code_aux}.
	 * @param ctx the parse tree
	 */
	void exitCode_aux(sourceCodeParser.Code_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#sent}.
	 * @param ctx the parse tree
	 */
	void enterSent(sourceCodeParser.SentContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#sent}.
	 * @param ctx the parse tree
	 */
	void exitSent(sourceCodeParser.SentContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#vardef_code}.
	 * @param ctx the parse tree
	 */
	void enterVardef_code(sourceCodeParser.Vardef_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#vardef_code}.
	 * @param ctx the parse tree
	 */
	void exitVardef_code(sourceCodeParser.Vardef_codeContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#simple_vardef_code}.
	 * @param ctx the parse tree
	 */
	void enterSimple_vardef_code(sourceCodeParser.Simple_vardef_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#simple_vardef_code}.
	 * @param ctx the parse tree
	 */
	void exitSimple_vardef_code(sourceCodeParser.Simple_vardef_codeContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#asig}.
	 * @param ctx the parse tree
	 */
	void enterAsig(sourceCodeParser.AsigContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#asig}.
	 * @param ctx the parse tree
	 */
	void exitAsig(sourceCodeParser.AsigContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#vardef_and_asig}.
	 * @param ctx the parse tree
	 */
	void enterVardef_and_asig(sourceCodeParser.Vardef_and_asigContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#vardef_and_asig}.
	 * @param ctx the parse tree
	 */
	void exitVardef_and_asig(sourceCodeParser.Vardef_and_asigContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#funccall}.
	 * @param ctx the parse tree
	 */
	void enterFunccall(sourceCodeParser.FunccallContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#funccall}.
	 * @param ctx the parse tree
	 */
	void exitFunccall(sourceCodeParser.FunccallContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#funccall_aux}.
	 * @param ctx the parse tree
	 */
	void enterFunccall_aux(sourceCodeParser.Funccall_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#funccall_aux}.
	 * @param ctx the parse tree
	 */
	void exitFunccall_aux(sourceCodeParser.Funccall_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#subpparamlist}.
	 * @param ctx the parse tree
	 */
	void enterSubpparamlist(sourceCodeParser.SubpparamlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#subpparamlist}.
	 * @param ctx the parse tree
	 */
	void exitSubpparamlist(sourceCodeParser.SubpparamlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#explist}.
	 * @param ctx the parse tree
	 */
	void enterExplist(sourceCodeParser.ExplistContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#explist}.
	 * @param ctx the parse tree
	 */
	void exitExplist(sourceCodeParser.ExplistContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#explist_aux}.
	 * @param ctx the parse tree
	 */
	void enterExplist_aux(sourceCodeParser.Explist_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#explist_aux}.
	 * @param ctx the parse tree
	 */
	void exitExplist_aux(sourceCodeParser.Explist_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#return_func}.
	 * @param ctx the parse tree
	 */
	void enterReturn_func(sourceCodeParser.Return_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#return_func}.
	 * @param ctx the parse tree
	 */
	void exitReturn_func(sourceCodeParser.Return_funcContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#if}.
	 * @param ctx the parse tree
	 */
	void enterIf(sourceCodeParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#if}.
	 * @param ctx the parse tree
	 */
	void exitIf(sourceCodeParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#if_aux}.
	 * @param ctx the parse tree
	 */
	void enterIf_aux(sourceCodeParser.If_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#if_aux}.
	 * @param ctx the parse tree
	 */
	void exitIf_aux(sourceCodeParser.If_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#else}.
	 * @param ctx the parse tree
	 */
	void enterElse(sourceCodeParser.ElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#else}.
	 * @param ctx the parse tree
	 */
	void exitElse(sourceCodeParser.ElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#else_aux}.
	 * @param ctx the parse tree
	 */
	void enterElse_aux(sourceCodeParser.Else_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#else_aux}.
	 * @param ctx the parse tree
	 */
	void exitElse_aux(sourceCodeParser.Else_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#if_else}.
	 * @param ctx the parse tree
	 */
	void enterIf_else(sourceCodeParser.If_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#if_else}.
	 * @param ctx the parse tree
	 */
	void exitIf_else(sourceCodeParser.If_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#while}.
	 * @param ctx the parse tree
	 */
	void enterWhile(sourceCodeParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#while}.
	 * @param ctx the parse tree
	 */
	void exitWhile(sourceCodeParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#dowhile}.
	 * @param ctx the parse tree
	 */
	void enterDowhile(sourceCodeParser.DowhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#dowhile}.
	 * @param ctx the parse tree
	 */
	void exitDowhile(sourceCodeParser.DowhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#for}.
	 * @param ctx the parse tree
	 */
	void enterFor(sourceCodeParser.ForContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#for}.
	 * @param ctx the parse tree
	 */
	void exitFor(sourceCodeParser.ForContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#for_aux}.
	 * @param ctx the parse tree
	 */
	void enterFor_aux(sourceCodeParser.For_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#for_aux}.
	 * @param ctx the parse tree
	 */
	void exitFor_aux(sourceCodeParser.For_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#expcond}.
	 * @param ctx the parse tree
	 */
	void enterExpcond(sourceCodeParser.ExpcondContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#expcond}.
	 * @param ctx the parse tree
	 */
	void exitExpcond(sourceCodeParser.ExpcondContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#expcond_aux}.
	 * @param ctx the parse tree
	 */
	void enterExpcond_aux(sourceCodeParser.Expcond_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#expcond_aux}.
	 * @param ctx the parse tree
	 */
	void exitExpcond_aux(sourceCodeParser.Expcond_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#oplog}.
	 * @param ctx the parse tree
	 */
	void enterOplog(sourceCodeParser.OplogContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#oplog}.
	 * @param ctx the parse tree
	 */
	void exitOplog(sourceCodeParser.OplogContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#factorcond}.
	 * @param ctx the parse tree
	 */
	void enterFactorcond(sourceCodeParser.FactorcondContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#factorcond}.
	 * @param ctx the parse tree
	 */
	void exitFactorcond(sourceCodeParser.FactorcondContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#factorcond_aux}.
	 * @param ctx the parse tree
	 */
	void enterFactorcond_aux(sourceCodeParser.Factorcond_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#factorcond_aux}.
	 * @param ctx the parse tree
	 */
	void exitFactorcond_aux(sourceCodeParser.Factorcond_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#opcomp}.
	 * @param ctx the parse tree
	 */
	void enterOpcomp(sourceCodeParser.OpcompContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#opcomp}.
	 * @param ctx the parse tree
	 */
	void exitOpcomp(sourceCodeParser.OpcompContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(sourceCodeParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(sourceCodeParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#exp_aux}.
	 * @param ctx the parse tree
	 */
	void enterExp_aux(sourceCodeParser.Exp_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#exp_aux}.
	 * @param ctx the parse tree
	 */
	void exitExp_aux(sourceCodeParser.Exp_auxContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(sourceCodeParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(sourceCodeParser.OpContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(sourceCodeParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(sourceCodeParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link sourceCodeParser#simpvalue_code}.
	 * @param ctx the parse tree
	 */
	void enterSimpvalue_code(sourceCodeParser.Simpvalue_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link sourceCodeParser#simpvalue_code}.
	 * @param ctx the parse tree
	 */
	void exitSimpvalue_code(sourceCodeParser.Simpvalue_codeContext ctx);
}