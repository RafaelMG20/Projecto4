// Generated from C:/Users/sopos/Desktop/Projecto4\alg.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link alg}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface algVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link alg#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(alg.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#fcall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFcall(alg.FcallContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#functionSpecial}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionSpecial(alg.FunctionSpecialContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Call}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(alg.CallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parenteses}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenteses(alg.ParentesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdBoolPoint}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdBoolPoint(alg.IdBoolPointContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(alg.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(alg.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Var}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(alg.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Pointer}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPointer(alg.PointerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(alg.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PointIndex}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPointIndex(alg.PointIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Float}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat(alg.FloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(alg.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FuncInv}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncInv(alg.FuncInvContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Paren}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen(alg.ParenContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(alg.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#defineNull}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineNull(alg.DefineNullContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#equals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquals(alg.EqualsContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#inteiro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteiro(alg.InteiroContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#inteiros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteiros(alg.InteirosContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#booleano}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleano(alg.BooleanoContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#booleanos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanos(alg.BooleanosContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#real}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal(alg.RealContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#reais}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReais(alg.ReaisContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#equals_string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquals_string(alg.Equals_stringContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#cadeia_caracteres}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCadeia_caracteres(alg.Cadeia_caracteresContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#cadeias_caracteres}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCadeias_caracteres(alg.Cadeias_caracteresContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#ponteiro_inteiro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPonteiro_inteiro(alg.Ponteiro_inteiroContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#ponteiro_real}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPonteiro_real(alg.Ponteiro_realContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#ponteiro_cadeia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPonteiro_cadeia(alg.Ponteiro_cadeiaContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#op_paranteses}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_paranteses(alg.Op_parantesesContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#op_pointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_pointer(alg.Op_pointerContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#ide}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIde(alg.IdeContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#idy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdy(alg.IdyContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#comparations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparations(alg.ComparationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#logics}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogics(alg.LogicsContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#expressions_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressions_list(alg.Expressions_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#expressions_list2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressions_list2(alg.Expressions_list2Context ctx);
	/**
	 * Visit a parse tree produced by {@link alg#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(alg.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(alg.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#function_declare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_declare(alg.Function_declareContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(alg.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(alg.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#prologo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrologo(alg.PrologoContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#epilogo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEpilogo(alg.EpilogoContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(alg.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#function_invocate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_invocate(alg.Function_invocateContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#ctrl_instruct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCtrl_instruct(alg.Ctrl_instructContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#attributes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributes(alg.AttributesContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#instructions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructions(alg.InstructionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#if_cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_cond(alg.If_condContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(alg.LoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#sub_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub_block(alg.Sub_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link alg#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(alg.VariableContext ctx);
}