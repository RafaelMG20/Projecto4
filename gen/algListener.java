// Generated from C:/Users/sopos/Desktop/Projecto4\alg.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link alg}.
 */
public interface algListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link alg#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(alg.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(alg.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#fcall}.
	 * @param ctx the parse tree
	 */
	void enterFcall(alg.FcallContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#fcall}.
	 * @param ctx the parse tree
	 */
	void exitFcall(alg.FcallContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#functionSpecial}.
	 * @param ctx the parse tree
	 */
	void enterFunctionSpecial(alg.FunctionSpecialContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#functionSpecial}.
	 * @param ctx the parse tree
	 */
	void exitFunctionSpecial(alg.FunctionSpecialContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Call}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void enterCall(alg.CallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Call}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void exitCall(alg.CallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parenteses}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenteses(alg.ParentesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parenteses}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenteses(alg.ParentesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdBoolPoint}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdBoolPoint(alg.IdBoolPointContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdBoolPoint}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdBoolPoint(alg.IdBoolPointContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(alg.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(alg.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(alg.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(alg.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Var}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void enterVar(alg.VarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Var}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void exitVar(alg.VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Pointer}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void enterPointer(alg.PointerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Pointer}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void exitPointer(alg.PointerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(alg.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(alg.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PointIndex}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void enterPointIndex(alg.PointIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PointIndex}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void exitPointIndex(alg.PointIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Float}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloat(alg.FloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Float}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloat(alg.FloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(alg.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(alg.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FuncInv}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void enterFuncInv(alg.FuncInvContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FuncInv}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void exitFuncInv(alg.FuncInvContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Paren}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void enterParen(alg.ParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Paren}
	 * labeled alternative in {@link alg#expr}.
	 * @param ctx the parse tree
	 */
	void exitParen(alg.ParenContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(alg.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(alg.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#defineNull}.
	 * @param ctx the parse tree
	 */
	void enterDefineNull(alg.DefineNullContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#defineNull}.
	 * @param ctx the parse tree
	 */
	void exitDefineNull(alg.DefineNullContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#equals}.
	 * @param ctx the parse tree
	 */
	void enterEquals(alg.EqualsContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#equals}.
	 * @param ctx the parse tree
	 */
	void exitEquals(alg.EqualsContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#inteiro}.
	 * @param ctx the parse tree
	 */
	void enterInteiro(alg.InteiroContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#inteiro}.
	 * @param ctx the parse tree
	 */
	void exitInteiro(alg.InteiroContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#inteiros}.
	 * @param ctx the parse tree
	 */
	void enterInteiros(alg.InteirosContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#inteiros}.
	 * @param ctx the parse tree
	 */
	void exitInteiros(alg.InteirosContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#booleano}.
	 * @param ctx the parse tree
	 */
	void enterBooleano(alg.BooleanoContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#booleano}.
	 * @param ctx the parse tree
	 */
	void exitBooleano(alg.BooleanoContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#booleanos}.
	 * @param ctx the parse tree
	 */
	void enterBooleanos(alg.BooleanosContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#booleanos}.
	 * @param ctx the parse tree
	 */
	void exitBooleanos(alg.BooleanosContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#real}.
	 * @param ctx the parse tree
	 */
	void enterReal(alg.RealContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#real}.
	 * @param ctx the parse tree
	 */
	void exitReal(alg.RealContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#reais}.
	 * @param ctx the parse tree
	 */
	void enterReais(alg.ReaisContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#reais}.
	 * @param ctx the parse tree
	 */
	void exitReais(alg.ReaisContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#equals_string}.
	 * @param ctx the parse tree
	 */
	void enterEquals_string(alg.Equals_stringContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#equals_string}.
	 * @param ctx the parse tree
	 */
	void exitEquals_string(alg.Equals_stringContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#cadeia_caracteres}.
	 * @param ctx the parse tree
	 */
	void enterCadeia_caracteres(alg.Cadeia_caracteresContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#cadeia_caracteres}.
	 * @param ctx the parse tree
	 */
	void exitCadeia_caracteres(alg.Cadeia_caracteresContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#cadeias_caracteres}.
	 * @param ctx the parse tree
	 */
	void enterCadeias_caracteres(alg.Cadeias_caracteresContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#cadeias_caracteres}.
	 * @param ctx the parse tree
	 */
	void exitCadeias_caracteres(alg.Cadeias_caracteresContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#ponteiro_inteiro}.
	 * @param ctx the parse tree
	 */
	void enterPonteiro_inteiro(alg.Ponteiro_inteiroContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#ponteiro_inteiro}.
	 * @param ctx the parse tree
	 */
	void exitPonteiro_inteiro(alg.Ponteiro_inteiroContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#ponteiro_real}.
	 * @param ctx the parse tree
	 */
	void enterPonteiro_real(alg.Ponteiro_realContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#ponteiro_real}.
	 * @param ctx the parse tree
	 */
	void exitPonteiro_real(alg.Ponteiro_realContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#ponteiro_cadeia}.
	 * @param ctx the parse tree
	 */
	void enterPonteiro_cadeia(alg.Ponteiro_cadeiaContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#ponteiro_cadeia}.
	 * @param ctx the parse tree
	 */
	void exitPonteiro_cadeia(alg.Ponteiro_cadeiaContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#op_paranteses}.
	 * @param ctx the parse tree
	 */
	void enterOp_paranteses(alg.Op_parantesesContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#op_paranteses}.
	 * @param ctx the parse tree
	 */
	void exitOp_paranteses(alg.Op_parantesesContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#op_pointer}.
	 * @param ctx the parse tree
	 */
	void enterOp_pointer(alg.Op_pointerContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#op_pointer}.
	 * @param ctx the parse tree
	 */
	void exitOp_pointer(alg.Op_pointerContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#ide}.
	 * @param ctx the parse tree
	 */
	void enterIde(alg.IdeContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#ide}.
	 * @param ctx the parse tree
	 */
	void exitIde(alg.IdeContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#idy}.
	 * @param ctx the parse tree
	 */
	void enterIdy(alg.IdyContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#idy}.
	 * @param ctx the parse tree
	 */
	void exitIdy(alg.IdyContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#comparations}.
	 * @param ctx the parse tree
	 */
	void enterComparations(alg.ComparationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#comparations}.
	 * @param ctx the parse tree
	 */
	void exitComparations(alg.ComparationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#logics}.
	 * @param ctx the parse tree
	 */
	void enterLogics(alg.LogicsContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#logics}.
	 * @param ctx the parse tree
	 */
	void exitLogics(alg.LogicsContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#expressions_list}.
	 * @param ctx the parse tree
	 */
	void enterExpressions_list(alg.Expressions_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#expressions_list}.
	 * @param ctx the parse tree
	 */
	void exitExpressions_list(alg.Expressions_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#expressions_list2}.
	 * @param ctx the parse tree
	 */
	void enterExpressions_list2(alg.Expressions_list2Context ctx);
	/**
	 * Exit a parse tree produced by {@link alg#expressions_list2}.
	 * @param ctx the parse tree
	 */
	void exitExpressions_list2(alg.Expressions_list2Context ctx);
	/**
	 * Enter a parse tree produced by {@link alg#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(alg.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(alg.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(alg.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(alg.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#function_declare}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declare(alg.Function_declareContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#function_declare}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declare(alg.Function_declareContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#type}.
	 * @param ctx the parse tree
	 */
	void enterType(alg.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#type}.
	 * @param ctx the parse tree
	 */
	void exitType(alg.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(alg.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(alg.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#prologo}.
	 * @param ctx the parse tree
	 */
	void enterPrologo(alg.PrologoContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#prologo}.
	 * @param ctx the parse tree
	 */
	void exitPrologo(alg.PrologoContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#epilogo}.
	 * @param ctx the parse tree
	 */
	void enterEpilogo(alg.EpilogoContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#epilogo}.
	 * @param ctx the parse tree
	 */
	void exitEpilogo(alg.EpilogoContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(alg.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(alg.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#function_invocate}.
	 * @param ctx the parse tree
	 */
	void enterFunction_invocate(alg.Function_invocateContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#function_invocate}.
	 * @param ctx the parse tree
	 */
	void exitFunction_invocate(alg.Function_invocateContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#ctrl_instruct}.
	 * @param ctx the parse tree
	 */
	void enterCtrl_instruct(alg.Ctrl_instructContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#ctrl_instruct}.
	 * @param ctx the parse tree
	 */
	void exitCtrl_instruct(alg.Ctrl_instructContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#attributes}.
	 * @param ctx the parse tree
	 */
	void enterAttributes(alg.AttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#attributes}.
	 * @param ctx the parse tree
	 */
	void exitAttributes(alg.AttributesContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#instructions}.
	 * @param ctx the parse tree
	 */
	void enterInstructions(alg.InstructionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#instructions}.
	 * @param ctx the parse tree
	 */
	void exitInstructions(alg.InstructionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#if_cond}.
	 * @param ctx the parse tree
	 */
	void enterIf_cond(alg.If_condContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#if_cond}.
	 * @param ctx the parse tree
	 */
	void exitIf_cond(alg.If_condContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(alg.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(alg.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#sub_block}.
	 * @param ctx the parse tree
	 */
	void enterSub_block(alg.Sub_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#sub_block}.
	 * @param ctx the parse tree
	 */
	void exitSub_block(alg.Sub_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link alg#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(alg.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link alg#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(alg.VariableContext ctx);
}