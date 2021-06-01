package alg;

import Symbols.FunctionSymbol;
import Symbols.Scope;
import Symbols.Symbol;
import Symbols.Type;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.PrintStream;

public class TypeChecker extends algBaseListener {

    public Scope globalScope;
    public Scope currentScope;
    private FunctionSymbol currentFunction;
    public int semanticErrors;

    public ParseTreeProperty<Type.PType> exprType = new ParseTreeProperty<>();

    public ParseTreeProperty<Scope> scopes = new ParseTreeProperty<>();

    private boolean defineSymbol(ParserRuleContext ctx, Symbol s)
    {
        if (!this.currentScope.define(s)) {
            String var10001 = s.name;
            System.err.println("Redefinindo variavel " + var10001 + " previamente definida, na linha " + ctx.start.getLine());
            ++this.semanticErrors;
            return false;
        }
        Symbol temp = this.currentScope.resolve(s.name);
        if(temp!=null)
        {
            if(!temp.type.equals(s.type))
            {
                System.err.println("A variavel " + s.name + " na linha " + ctx.start.getLine() + " é do tipo " + temp.type);
                ++this.semanticErrors;
                return false;
            }
        }


        else {
            return true;
        }
        return true;
    }

    private boolean defineArgs(ParserRuleContext ctx, Symbol s)
    {

            Symbol temp = this.globalScope.resolve(s.name);
            if(temp!=null)
            {

                System.err.println("Redefinindo a variavel " + s.name + " previamente definida, na linha " + ctx.start.getLine());
                ++this.semanticErrors;
                return false;
            }
            if(this.globalScope.containsName(s.name))
            {
                if(temp !=null)
                {
                    if(temp instanceof FunctionSymbol)
                    {
                        System.err.println("A variavel " + s.name + " na linha " + ctx.start.getLine() + " é uma função");
                        ++this.semanticErrors;
                        return false;
                    }
                    if(!temp.type.equals(s.type))
                    {
                        System.err.println("A variavel " + s.name + " na linha " + ctx.start.getLine() + " é do tipo " + temp.type);
                        ++this.semanticErrors;
                        return false;
                    }
                }

            }



        if (!this.currentScope.define(s)) {
            String var10001 = s.name;
            System.err.println("Redefinindo a variável " + var10001 + " previamente definida, na linha " + ctx.start.getLine());
            ++this.semanticErrors;
            return false;
        }
        else {
        this.exprType.put(ctx, s.type.getPrimitiveType());
        return true;
    }

    }

    public void enterPrograma(alg.ProgramaContext ctx)
    {
        globalScope = new Scope(null);
        this.scopes.put(ctx,globalScope);
        this.currentScope = globalScope;
        this.semanticErrors = 0;
    }
    public void exitPrograma(alg.ProgramaContext ctx) {
        //System.out.println(this.currentScope.toString());
        int size = ctx.function().size();
        int count = 0;
        for(int i = 0; i< size; i++)
        {
            if(ctx.function(i).functionSpecial() != null)
            {
                count++;
            }
        }

        if(count == 0)
        {
            System.err.println("A função alg deve estar definida para correr o programa.");
            ++this.semanticErrors;
        }

        }
    public void enterFcall(alg.FcallContext ctx) { }
    public void exitFcall(alg.FcallContext ctx) { }
    public void enterFloat(alg.FloatContext ctx) { }
    public void exitFloat(alg.FloatContext ctx)
    {exprType.put(ctx, Type.PType.FLOAT); }
    public void enterParenteses(alg.ParentesesContext ctx) { }
    public void exitParenteses(alg.ParentesesContext ctx) { }
    public void enterMulDiv(alg.MulDivContext ctx) { }

    public void exitMulDiv(alg.MulDivContext ctx) {
        Type.PType e1 = (Type.PType)this.exprType.get(ctx.expr(0));
        Type.PType e2 = (Type.PType)this.exprType.get(ctx.expr(1));
        String operator = ((ParseTree)ctx.children.get(1)).getText();
        this.verifyPrimitiveArithmeticOperator(ctx, operator, e1, e2);
    }
    public void enterAddSub(alg.AddSubContext ctx) { }

    public void exitAddSub(alg.AddSubContext ctx) {
        Type.PType e1 = (Type.PType)this.exprType.get(ctx.expr(0));
        Type.PType e2 = (Type.PType)this.exprType.get(ctx.expr(1));
        String operator = ((ParseTree)ctx.children.get(1)).getText();
        this.verifyPrimitiveArithmeticOperator(ctx, operator, e1, e2);
    }

    public void enterVar(alg.VarContext ctx) { }

    public void exitVar(alg.VarContext ctx)
    {
        Symbol s = this.currentScope.resolve(ctx.IDENT().getText());
        if(s!=null)
            this.exprType.put(ctx,s.type.getPrimitiveType());
        else
        {
            this.exprType.put(ctx, Type.PType.ERROR);
            System.err.println("A variável " + ctx.IDENT().getText() + " na linha " + ctx.start.getLine() + " não está definida");
            ++this.semanticErrors;
        }
    }
    public void enterFuncInv(alg.FuncInvContext ctx) { }

    public void exitFuncInv(alg.FuncInvContext ctx)
    {
        String name = ctx.function_invocate().IDENT().getText();
        Symbol s = this.currentScope.resolve(name);
        if(s!=null)
        {
            this.exprType.put(ctx,s.type.getPrimitiveType());
        }
    }

    public void enterPointer(alg.PointerContext ctx) { }

    public void exitPointer(alg.PointerContext ctx)
    {
        Type.PType e1 = (Type.PType)this.exprType.get(ctx.op_pointer().expr(0));
        if(e1 == Type.PType.INT || e1 == Type.PType.PINT)
        {
            this.exprType.put(ctx, Type.PType.PINT);
        }
        else if(e1 == Type.PType.FLOAT || e1 == Type.PType.PFLOAT)
        {
            this.exprType.put(ctx, Type.PType.PFLOAT);
        }
        else if(e1 == Type.PType.STRING || e1 == Type.PType.PSTRING)
        {
            this.exprType.put(ctx, Type.PType.PSTRING);
        }

        String name = ctx.getParent().getParent().getChild(0).getText();
        if(name.equals("int") || name.equals("float") || name.equals("string") || name.equals("bool"))
        {
            System.err.println("Para reservar memória na linha " + ctx.start.getLine() + " a variavel deve ser um ponteiro e não do tipo: INT/FLOAT/STRING/BOOL " );
            ++this.semanticErrors;
        }

        else if (!name.equals("int") || !name.equals("float") || !name.equals("string") || !name.equals("bool") || !name.equals("int") || !name.equals("float") || !name.equals("string") )
        {
            Symbol s = this.currentScope.resolve(name);
            if(s!=null)
            {
                if(s.type.getPrimitiveType()== Type.PType.INT || s.type.getPrimitiveType()== Type.PType.FLOAT || s.type.getPrimitiveType()== Type.PType.STRING || s.type.getPrimitiveType()== Type.PType.BOOL)
                {
                    System.err.println("Para reservar memória na linha " + ctx.start.getLine() + " a variavel deve ser um ponteiro e não do tipo " + s.type);
                    ++this.semanticErrors;
                }
            }
        }

    }

    public void enterInt(alg.IntContext ctx) { }

    public void exitInt(alg.IntContext ctx) {
        exprType.put(ctx, Type.PType.INT);
    }
    public void enterParen(alg.ParenContext ctx) { }


    public void exitParen(alg.ParenContext ctx) {
        Type.PType e1 = (Type.PType)this.exprType.get(ctx.expr().getChild(0));
        Type.PType e2 = (Type.PType)this.exprType.get(ctx.expr().getChild(2));
        if(e2!=null)
        {
            String operator = ((ParseTree)ctx.expr().children.get(1)).getText();
            this.verifyPrimitiveArithmeticOperator(ctx, operator, e1, e2);
        }

        else
            exprType.put(ctx,e1);


    }

    public void enterIdentifier(alg.IdentifierContext ctx) { }

    public void exitIdentifier(alg.IdentifierContext ctx)
    {

        Type.PType e1 = (Type.PType)this.exprType.get(ctx.ide().getChild(1));
        exprType.put(ctx, e1);
    }

    /*public void enterPointIndex(alg.PointIndexContext ctx) { }

    public void exitPointIndex(alg.PointIndexContext ctx)
    {
        Type.PType e1 = (Type.PType)this.exprType.get(ctx.expr(0));
        Type.PType e2 = (Type.PType)this.exprType.get(ctx.expr(1));
        String operator1 = ((ParseTree)ctx.INDEX_POINT_L()).getText();
        String operator2 = ((ParseTree)ctx.INDEX_POINT_R()).getText();
        this.verifyIndexPointer(ctx, operator1, operator2, e1, e2);

    }*/

    public void enterIdBoolPoint(alg.IdBoolPointContext ctx) { }

    public void exitIdBoolPoint(alg.IdBoolPointContext ctx)
    {
        String name = ctx.idy().IDENT().getText();
            Symbol s = this.currentScope.resolve(name);
            if (s == null) {
                System.err.println("A variavel " + name + " na linha " + ctx.start.getLine() + " não está definida");
                ++this.semanticErrors;
                exprType.put(ctx, Type.PType.ERROR);
                return;
            } else {
                if(ctx.idy().IDEY().getText().equals("~")) {
                if (!s.type.equals(Type.PType.BOOL)) {
                    System.err.println("A variavel " + name + " na linha " + ctx.start.getLine() + " deve ser do tipo Bool");
                    ++this.semanticErrors;
                    exprType.put(ctx, Type.PType.ERROR);
                }
                else
                    exprType.put(ctx, s.type.getPrimitiveType());
                }

                if(ctx.idy().getChild(0).getText().equals("?") && ctx.idy().IDENT() != null)
                {

                    Type.PType e1 = this.exprType.get(ctx.idy().expr());


                    if(s.type.getPrimitiveType().equals(Type.PType.PINT) || s.type.getPrimitiveType().equals(Type.PType.PSTRING) || s.type.getPrimitiveType().equals(Type.PType.PFLOAT) )
                    {
                        if(e1 == null || !e1.equals(Type.PType.INT) )
                        {
                            System.err.println("Para fazer extração de ponteiro deve ser feita a indexação de ponteiro. Linha " + ctx.start.getLine());
                            exprType.put(ctx, Type.PType.ERROR);
                            ++this.semanticErrors;
                        }

                        else
                            exprType.put(ctx, s.type.getPrimitiveType());

                    }

                   else if(s.type.getPrimitiveType().equals(Type.PType.INT) || s.type.getPrimitiveType().equals(Type.PType.STRING) || s.type.getPrimitiveType().equals(Type.PType.FLOAT) ||s.type.getPrimitiveType().equals(Type.PType.BOOL)  )
                    {
                        if(e1 != null)
                        {
                            System.err.println("A variavel " + name + " na linha " + ctx.start.getLine() + " é do tipo " + s.type);
                            exprType.put(ctx, Type.PType.ERROR);
                            ++this.semanticErrors;
                        }
                        else
                            exprType.put(ctx, s.type.getPrimitiveType());
                    }
                    else if(s.type.getPrimitiveType().equals(Type.PType.VOID) )
                    {
                            System.err.println("A variavel " + name + " na linha " + ctx.start.getLine() + " é do tipo " + s.type + " e não pode ser do tipo void");
                            exprType.put(ctx, Type.PType.ERROR);
                            ++this.semanticErrors;

                    }


                    else
                        exprType.put(ctx, s.type.getPrimitiveType());
                }
            }

    }

    public void enterEquals(alg.EqualsContext ctx) { }
    public void exitEquals(alg.EqualsContext ctx) { }

    public void enterInteiro(alg.InteiroContext ctx)
    {

    }

    public void exitInteiro(alg.InteiroContext ctx)
    {
        if(ctx.equals()!=null && ctx.equals().getChildCount() == 2 && ctx.equals().function_invocate()!=null) {
            String variableName = ctx.IDENT().get(0).getText();
            Symbol oi = this.currentScope.resolve(variableName);
            if (oi != null) {
                String name = ctx.equals().expr().get(0).getChild(0).getChild(0).getText();
                Symbol s = this.currentScope.resolve(name);
                if (s != null) {
                    if (s.type.equals(Type.PType.VOID)) {
                        System.err.println("A função " + name + " na linha " + ctx.start.getLine() + " é do tipo VOID e não retorna nenhum valor");
                        ++this.semanticErrors;
                        return;
                    }

                    if (s.type.equals(Type.PType.FLOAT) || s.type.equals(Type.PType.BOOL) || s.type.equals(Type.PType.STRING) || s.type.equals(Type.PType.PFLOAT) || s.type.equals(Type.PType.PINT) || s.type.equals(Type.PType.PSTRING)) {
                        System.err.println("A variavel " + variableName + " na linha " + ctx.start.getLine() + " é do tipo INT" + " e a função é do tipo " + s.type);
                        ++this.semanticErrors;
                        return;
                    }
                }
            }


            exprType.put(ctx, Type.PType.INT);
        }

        if(ctx.equals()!=null) {
            String variableName = ctx.IDENT().get(0).getText();
            Symbol oi = this.currentScope.resolve(variableName);
            if (oi != null || (ctx.INT()!=null && oi==null)) {
                if (ctx.equals().function_invocate() == null && ctx.equals().expr() == null && ctx.equals().getChildCount()!=0 && (ctx.equals().function_invocate().WRITE() != null || ctx.equals().function_invocate().WRITELN() != null)) {

                    System.err.println("A variavel " + ctx.IDENT(0).getText() + " na linha " + ctx.start.getLine() + " é do tipo INT e não se pode atribuir WRITE/WRITELN.");
                    this.semanticErrors++;
                    return;
                }

                if (ctx.equals().function_invocate() != null) {
                    if (ctx.equals().function_invocate().WRITELN() != null || ctx.equals().function_invocate().WRITE() != null) {
                        System.err.println("A variavel " + ctx.IDENT(0).getText() + " na linha " + ctx.start.getLine() + " é do tipo INT e a não se pode atribuir WRITE/WRITELN.");
                        this.semanticErrors++;
                        return;
                    }
                }
            }
        }
    }
    public void enterInteiros(alg.InteirosContext ctx) { }
    public void exitInteiros(alg.InteirosContext ctx)
    {
       int i = 0;
        while(ctx.IDENT().get(i) != null) {
            exprType.put(ctx.IDENT().get(i), Type.PType.INT);
            i++;
        }
    }
    public void enterBooleano(alg.BooleanoContext ctx) { }

    public void exitBooleano(alg.BooleanoContext ctx)
    {
        if(ctx.function_invocate() != null) {
            if (ctx.function_invocate().WRITELN() == null && ctx.function_invocate().WRITE() == null) {

                String variableName = ctx.IDENT().getText();
                String name = ctx.function_invocate().IDENT().getText();
                Symbol s = this.currentScope.resolve(name);
                if (s != null) {
                    if (s.type.equals(Type.PType.VOID)) {
                        System.err.println("A função " + name + " na linha " + ctx.start.getLine() + " é do tipo VOID e não retorna nenhum valor");
                        ++this.semanticErrors;
                        return;
                    }

                    if (s.type.equals(Type.PType.STRING) || s.type.equals(Type.PType.FLOAT) || s.type.equals(Type.PType.INT) || s.type.equals(Type.PType.PFLOAT) || s.type.equals(Type.PType.PINT) || s.type.equals(Type.PType.PSTRING)) {
                        System.err.println("A variavel " + variableName + " na linha " + ctx.start.getLine() + " é do tipo BOOL " + " e a função é do tipo " + s.type);
                        ++this.semanticErrors;
                        return;
                    }


                }
            }
            //'true'|'false'|function_invocate
            if (ctx.TRUE() == null && ctx.FALSE() == null && ctx.function_invocate() == null) {
                String variableName = ctx.IDENT().getText();
                Symbol s = this.currentScope.resolve(variableName);
                if (s != null) {
                    if (s.type.equals(Type.PType.BOOL)) {
                        System.err.println("A variavel " + variableName + " na linha " + ctx.start.getLine() + " é do tipo BOOL " + " e a atribuição está errada");
                        ++this.semanticErrors;
                        return;
                    }
                }

                if (ctx.EQUAL() != null) {
                    System.err.println("A variavel " + variableName + " na linha " + ctx.start.getLine() + " é do tipo BOOL " + " e a atribuição está errada");
                    ++this.semanticErrors;
                    return;
                }
            }
        }
        if(ctx.function_invocate() !=null)
        {
            if(ctx.function_invocate().WRITE() != null || ctx.function_invocate().WRITELN() != null)
                System.err.println("Não se pode atribuir WRITE/WRITELN a uma variavel. Linha " + ctx.start.getLine());
            ++this.semanticErrors;
            return;
        }
    }

    public void enterBooleanos(alg.BooleanosContext ctx) { }
    public void exitBooleanos(alg.BooleanosContext ctx) { }
    public void enterReal(alg.RealContext ctx) { }
    public void exitReal(alg.RealContext ctx)
    {
        if(ctx.equals().getChildCount() == 2 && ctx.equals().function_invocate()!=null) {
            String variableName = ctx.IDENT().get(0).getText();
            Symbol oi = this.currentScope.resolve(variableName);
            if (oi != null) {
                String name = ctx.equals().expr().get(0).getChild(0).getChild(0).getText();
                Symbol s = this.currentScope.resolve(name);
                if (s != null) {
                    if (s.type.equals(Type.PType.VOID)) {
                        System.err.println("A função " + name + " na linha " + ctx.start.getLine() + " é do tipo VOID e não retorna nenhum valor");
                        ++this.semanticErrors;
                        return;
                    }

                    if (s.type.equals(Type.PType.STRING) || s.type.equals(Type.PType.BOOL) || s.type.equals(Type.PType.PFLOAT) || s.type.equals(Type.PType.PINT) || s.type.equals(Type.PType.PSTRING)) {
                        System.err.println("A variavel " + variableName + " na linha " + ctx.start.getLine() + " é do tipo FLOAT " + " e a função é do tipo " + s.type);
                        ++this.semanticErrors;
                        return;
                    }
                }
            }




            exprType.put(ctx, Type.PType.FLOAT);
        }
        if(ctx.equals()!=null) {
            String variableName = ctx.IDENT().get(0).getText();
            Symbol oi = this.currentScope.resolve(variableName);
            if (oi != null || (ctx.FLOAT()!=null && oi==null)) {
                if (ctx.equals().function_invocate() == null && ctx.equals().expr() == null && (ctx.equals().function_invocate().WRITE() != null || ctx.equals().function_invocate().WRITELN() != null)) {

                    System.err.println("A variavel " + ctx.IDENT(0).getText() + " na linha " + ctx.start.getLine() + " é do tipo FLOAT e a atribuição está errada.");
                    this.semanticErrors++;
                    return;
                }

                if (ctx.equals().function_invocate() != null) {
                    if (ctx.equals().function_invocate().WRITELN() != null || ctx.equals().function_invocate().WRITE() != null) {
                        System.err.println("A variavel " + ctx.IDENT(0).getText() + " na linha " + ctx.start.getLine() + " é do tipo FLOAT e a não se pode atribuir WRITE/WRITELN.");
                        this.semanticErrors++;
                        return;
                    }
                }
            }
        }

    }
    public void enterReais(alg.ReaisContext ctx) { }
    public void exitReais(alg.ReaisContext ctx) {
        int i = 0;
        while(ctx.IDENT().get(i).getSymbol() != null) {
            exprType.put(ctx.IDENT().get(i), Type.PType.FLOAT);
            i++;
        }
    }
    public void enterEquals_string(alg.Equals_stringContext ctx) { }
    public void exitEquals_string(alg.Equals_stringContext ctx) { }
    public void enterCadeia_caracteres(alg.Cadeia_caracteresContext ctx) {

    }

    public void exitCadeia_caracteres(alg.Cadeia_caracteresContext ctx)
    {
        if(ctx.equals_string().CADEIA_CARACTERES() == null)
        {
            Symbol s = this.currentScope.resolve(ctx.IDENT(0).getText());
            if(s != null) // entra se houver um simbolo s com esse nome
            {
                if(s.type.equals(Type.PType.STRING)) // entra se o type for string
                {
                    System.err.println("A variável " + ctx.IDENT(0).getText() + " na linha " + ctx.IDENT(0).getSymbol().getLine() + " é do tipo " + s.type);
                    ++this.semanticErrors;
                    return;
                }
            }

           /*else{ System.err.println("A variável " + ctx.IDENT(0).getText() + " na linha " + ctx.IDENT(0).getSymbol().getLine() + " é do tipo STRING");
            ++this.semanticErrors;}*/
        }

        if(ctx.equals_string().function_invocate() != null)
        {
            if(ctx.equals_string().function_invocate().WRITELN() == null && ctx.equals_string().function_invocate().WRITE() == null) {
                String variableName = ctx.IDENT().get(0).getText();
                String name = ctx.equals_string().function_invocate().IDENT().getText();
                Symbol s = this.currentScope.resolve(name);
                if (s != null) {
                    if (s.type.equals(Type.PType.VOID)) {
                        System.err.println("A função " + name + " na linha " + ctx.start.getLine() + " é do tipo VOID e não retorna nenhum valor");
                        ++this.semanticErrors;
                        return;
                    }

                    if (s.type.equals(Type.PType.FLOAT) || s.type.equals(Type.PType.BOOL) || s.type.equals(Type.PType.INT) || s.type.equals(Type.PType.PFLOAT) || s.type.equals(Type.PType.PINT) || s.type.equals(Type.PType.PSTRING)) {
                        System.err.println("A variavel " + variableName + " na linha " + ctx.start.getLine() + " é do tipo STRING " + " e a função é do tipo " + s.type);
                        ++this.semanticErrors;
                        return;
                    }
                }
            }
        }

        if(ctx.equals_string().CADEIA_CARACTERES() == null && ctx.equals_string().function_invocate() == null)
        {
            String variableName = ctx.IDENT().get(0).getText();
            Symbol s = this.currentScope.resolve(variableName);
            if(s!=null)
            {
                if(s.type.equals(Type.PType.STRING)) {
                    System.err.println("A variavel " + variableName + " na linha " + ctx.start.getLine() + " é do tipo STRING" + " e a atribuição está errada.");
                    ++this.semanticErrors;
                    return;
                }
            }
            else if(ctx.STRING()!=null && ctx.equals_string().getChildCount()>0)
            {
                System.err.println("A variavel " + variableName + " na linha " + ctx.start.getLine() + " é do tipo STRING" + " e a atribuição está errada.");
                ++this.semanticErrors;
                return;
            }

        }

        if(ctx.equals_string() !=null)
        {
            if(ctx.equals_string().function_invocate() != null) {
                if (ctx.equals_string().function_invocate().WRITE() != null || ctx.equals_string().function_invocate().WRITELN() != null)
                    System.err.println("Não se pode atribuir WRITE/WRITELN a uma variavel. Linha " + ctx.start.getLine());
                ++this.semanticErrors;
                return;
            }
        }
    }


    public void enterCadeias_caracteres(alg.Cadeias_caracteresContext ctx) { }
    public void exitCadeias_caracteres(alg.Cadeias_caracteresContext ctx) {

    }
    public void enterPonteiro_inteiro(alg.Ponteiro_inteiroContext ctx) { }

    public void exitPonteiro_inteiro(alg.Ponteiro_inteiroContext ctx) {
       if(ctx.getChildCount() == 6 && ctx.function_invocate()!=null)
        {

            if(ctx.expr(0) != null) {
                if (ctx.expr(0).getChild(0).getChild(ctx.expr(0).getChild(0).getChildCount() - 1).getText().equals(")")) {
                    String variableName = ctx.IDENT().getText();
                    String name = ctx.expr().get(0).getChild(0).getChild(0).getText();
                    Symbol s = this.currentScope.resolve(name);
                    if (s != null) {
                        if (s.type.equals(Type.PType.VOID)) {
                            System.err.println("A função " + name + " na linha " + ctx.start.getLine() + " é do tipo VOID e não retorna nenhum valor");
                            ++this.semanticErrors;
                            return;
                        }

                        if (s.type.equals(Type.PType.FLOAT) || s.type.equals(Type.PType.BOOL) || s.type.equals(Type.PType.STRING) || s.type.equals(Type.PType.PFLOAT) || s.type.equals(Type.PType.INT) || s.type.equals(Type.PType.PSTRING)) {
                            System.err.println("A variavel " + variableName + " na linha " + ctx.start.getLine() + " é do tipo PINT" + " e a função é do tipo " + s.type);
                            ++this.semanticErrors;
                            return;
                        }
                    }
                }
            }
            Symbol s = this.currentScope.resolve(ctx.IDENT().getText());
            if(s== null) {
                if (ctx.function_invocate().WRITELN() != null || ctx.function_invocate().WRITE() != null) {
                    System.err.println("A variável " + ctx.IDENT().getText() + " na linha " + ctx.IDENT().getSymbol().getLine() + " é do tipo PINT e não se pode atribuir WRITE/WRITELN");
                    ++this.semanticErrors;
                }
            }

        }
        exprType.put(ctx, Type.PType.PINT);
     }

    public void enterPonteiro_real(alg.Ponteiro_realContext ctx) { }

    public void exitPonteiro_real(alg.Ponteiro_realContext ctx) {
        if(ctx.getChildCount() == 6 && ctx.function_invocate()!=null)
        {
            if(ctx.expr(0) != null) {
                if (ctx.expr(0).getChild(0).getChild(ctx.expr(0).getChild(0).getChildCount() - 1).getText().equals(")")) {
                    String variableName = ctx.IDENT().getText();
                    String name = ctx.expr().get(0).getChild(0).getChild(0).getText();
                    Symbol s = this.currentScope.resolve(name);
                    if (s != null) {
                        if (s.type.equals(Type.PType.VOID)) {
                            System.err.println("A função " + name + " na linha " + ctx.start.getLine() + " é do tipo VOID e não retorna nenhum valor");
                            ++this.semanticErrors;
                            return;
                        }

                        if (s.type.equals(Type.PType.FLOAT) || s.type.equals(Type.PType.BOOL) || s.type.equals(Type.PType.STRING) || s.type.equals(Type.PType.INT) || s.type.equals(Type.PType.PINT) || s.type.equals(Type.PType.PSTRING)) {
                            System.err.println("A variavel " + variableName + " na linha " + ctx.start.getLine() + " é do tipo PFLOAT" + " e a função é do tipo " + s.type);
                            ++this.semanticErrors;
                            return;
                        }
                    }
                }
            }

            Symbol s = this.currentScope.resolve(ctx.IDENT().getText());
            if(s== null) {
                if (ctx.function_invocate().WRITELN() != null || ctx.function_invocate().WRITE() != null) {
                    System.err.println("A variável " + ctx.IDENT().getText() + " na linha " + ctx.IDENT().getSymbol().getLine() + " é do tipo PFLOAT e não se pode atribuir WRITE/WRITELN");
                    ++this.semanticErrors;
                }
            }
        }
        exprType.put(ctx, Type.PType.PFLOAT);
    }
    public void enterPonteiro_cadeia(alg.Ponteiro_cadeiaContext ctx) { }

    public void exitPonteiro_cadeia(alg.Ponteiro_cadeiaContext ctx) {
        if(ctx.getChildCount() == 6 && ctx.function_invocate()!=null)
        {
            if(ctx.expr(0) != null) {
                if (ctx.function_invocate().getChild((ctx.function_invocate().getChildCount() - 1)).getText().equals((")"))) {
                    String variableName = ctx.IDENT().getText();
                    String name = ctx.function_invocate().IDENT().getText();
                    Symbol s = this.currentScope.resolve(name);
                    if (s != null) {
                        if (s.type.equals(Type.PType.VOID)) {
                            System.err.println("A função " + name + " na linha " + ctx.start.getLine() + " é do tipo VOID e não retorna nenhum valor");
                            ++this.semanticErrors;
                            return;
                        }

                        if (s.type.equals(Type.PType.FLOAT) || s.type.equals(Type.PType.BOOL) || s.type.equals(Type.PType.STRING) || s.type.equals(Type.PType.PFLOAT) || s.type.equals(Type.PType.INT) || s.type.equals(Type.PType.PINT)) {
                            System.err.println("A variavel " + variableName + " na linha " + ctx.start.getLine() + " é do tipo PSTRING" + " e a função é do tipo " + s.type);
                            ++this.semanticErrors;
                            return;
                        }
                    }
                }
            }
            Symbol s = this.currentScope.resolve(ctx.IDENT().getText());
            if(s== null) {
                if (ctx.function_invocate().WRITELN() != null || ctx.function_invocate().WRITE() != null) {
                    System.err.println("A variável " + ctx.IDENT().getText() + " na linha " + ctx.IDENT().getSymbol().getLine() + " é do tipo PSTRING e não se pode atribuir WRITE/WRITELN");
                    ++this.semanticErrors;
                }
            }

        }
        exprType.put(ctx, Type.PType.PSTRING);
    }
    public void enterOp_paranteses(alg.Op_parantesesContext ctx) { }
    public void exitOp_paranteses(alg.Op_parantesesContext ctx) { }
    public void enterOp_pointer(alg.Op_pointerContext ctx) { }
    public void exitOp_pointer(alg.Op_pointerContext ctx) { }
    public void enterIde(alg.IdeContext ctx) { }
    public void exitIde(alg.IdeContext ctx) { }
    public void enterComparations(alg.ComparationsContext ctx) { }

    public void exitComparations(alg.ComparationsContext ctx)
    {
        String s = ctx.getChild(1).getText();
        if(s.equals("<") || s.equals(">") || s.equals(">=") || s.equals("<="))
        {

            Type.PType e1 = (Type.PType)this.exprType.get(ctx.expr(0));
            Type.PType e2 = (Type.PType)this.exprType.get(ctx.expr(1));
            String operator = ((ParseTree)ctx.children.get(1)).getText();

            this.verifyComparator(ctx, operator, e1, e2);
        }

        else if(s.equals("==") || s.equals("!="))
        {
            Type.PType e1 = (Type.PType)this.exprType.get(ctx.expr(0));
            Type.PType e2 = (Type.PType)this.exprType.get(ctx.expr(1));
            String operator = ((ParseTree)ctx.children.get(1)).getText();

            this.verifyEqualDif(ctx, operator, e1, e2);
        }
    }

    public void enterLogics(alg.LogicsContext ctx) { }
    public void exitLogics(alg.LogicsContext ctx) { }
    public void enterExpressions_list(alg.Expressions_listContext ctx) { }
    public void exitExpressions_list(alg.Expressions_listContext ctx) { }
    public void enterExpressions_list2(alg.Expressions_list2Context ctx) { }
    public void exitExpressions_list2(alg.Expressions_list2Context ctx) { }
    public void enterArgs(alg.ArgsContext ctx) { }

    public void exitArgs(alg.ArgsContext ctx)
    {

    }

    public void enterArg(alg.ArgContext ctx) { }

    public void exitArg(alg.ArgContext ctx) {
        if(ctx.type().MENORQ() != null)
        {
            String a = "";
            if(ctx.type().INT() != null)
            {
                    a = "pint";
            }

            else if(ctx.type().FLOAT() != null) {
                    a = "pfloat";
            }
            else if(ctx.type().STRING()!=null) {
                    a = "pstring";
            }

            String name = ctx.IDENT().getText();

            Symbol parameter = new Symbol(new Type(a), name);
            if (defineSymbol(ctx, parameter) && this.currentFunction != null) {
                this.currentFunction.arguments.add(parameter);
            }
        }
        else {
            String type = ctx.type().getChild(0).getText();
            String name = ctx.IDENT().getText();

            Symbol parameter = new Symbol(new Type(type), name);
            if (defineArgs(ctx, parameter) && this.currentFunction != null) {
                this.currentFunction.arguments.add(parameter);
            }
        }
    }

    public void enterFunction_declare(alg.Function_declareContext ctx)
    {

        if(ctx.type().getChild(0).getText().equals("<"))
        {
            String functionName = ctx.IDENT().getText();
            String type0 = ctx.type().getChild(1).getText();
            String typeFinal = "";
            if(type0.equals("int"))
            {
                typeFinal = "pint";
            }
            else if(type0.equals("string"))
            {
                typeFinal = "pstring";
            }
            else if(type0.equals("float"))
            {
                typeFinal = "pfloat";
            }
            FunctionSymbol f = new FunctionSymbol(new Type(typeFinal), functionName,this.currentScope);
            this.defineSymbol(ctx, f);
            this.currentFunction = f;
            this.currentScope = new Scope(this.currentScope);
            this.exprType.put(ctx,f.type.getPrimitiveType());

        }
        else {
            String functionName = ctx.IDENT().getText();
            String type = ctx.type().start.getText();
            FunctionSymbol f = new FunctionSymbol(new Type(type), functionName,this.currentScope);
            this.defineSymbol(ctx, f);
            this.currentFunction = f;
            this.currentScope = new Scope(this.currentScope);
            this.exprType.put(ctx, f.type.getPrimitiveType());
        }
    }

    public void exitFunction_declare(alg.Function_declareContext ctx) { }
    public void enterBody(alg.BodyContext ctx) { }

    public void exitBody(alg.BodyContext ctx) {
        int count = ctx.instructions().size();
        for(int i = 0; i<count; i++){
            if(ctx.instructions(i).sub_block() != null) {
                int count2 = ctx.instructions(i).sub_block().instructions().size();
                for (int j = 0; j < count2; j++) {
                    if (ctx.instructions(i).sub_block().instructions(j).ctrl_instruct() != null && ctx.instructions(i).sub_block().instructions(count2-1).ctrl_instruct() != null) {
                        if ((ctx.instructions(i).sub_block().instructions(count2-1).ctrl_instruct().RETURN() != null) && (ctx.instructions(i).sub_block().instructions(j).ctrl_instruct().RETURN() != null && j != count2 - 1)) {
                            System.err.println("Só pode ser invocado um return no subbloco, existe outro na linha " + ctx.instructions(i).sub_block().instructions(j).start.getLine());
                            ++this.semanticErrors;
                        }
                    }if(ctx.instructions(i).sub_block().instructions(j).ctrl_instruct() != null && ctx.instructions(i).sub_block().instructions(j).ctrl_instruct().LEAVE() != null) {
                        System.err.println("Instrução LEAVE da linha " + ctx.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " apenas pode ser chamado num ciclo");
                        ++this.semanticErrors;
                    }if(ctx.instructions(i).sub_block().instructions(j).ctrl_instruct() != null && ctx.instructions(i).sub_block().instructions(j).ctrl_instruct().RESTART() != null ){
                        System.err.println("Instrução RESTART da linha " + ctx.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " apenas pode ser chamado num ciclo");
                        ++this.semanticErrors;
                    }
                }
            }else{
                if(ctx.instructions(i).ctrl_instruct() != null && ctx.instructions(count-1).ctrl_instruct() != null) {
                    if ((ctx.instructions(count-1).ctrl_instruct().RETURN() != null) && (ctx.instructions(i).ctrl_instruct().RETURN() != null && i != count - 1)) {
                        System.err.println("Só pode ser invocado um return na função, existe outro na linha " + ctx.instructions(i).start.getLine());
                        ++this.semanticErrors;
                    }
                }
            }if(ctx.instructions(i).ctrl_instruct() != null && ctx.instructions(i).ctrl_instruct().LEAVE() != null) {
                System.err.println("Instrução LEAVE da linha " + ctx.instructions(i).ctrl_instruct().start.getLine() + " apenas pode ser chamado num ciclo");
                ++this.semanticErrors;
            }if(ctx.instructions(i).ctrl_instruct() != null && ctx.instructions(i).ctrl_instruct().RESTART() != null ){
                System.err.println("Instrução RESTART da linha " + ctx.instructions(i).ctrl_instruct().start.getLine() + " apenas pode ser chamado num ciclo");
                ++this.semanticErrors;
            }
        }
    }

    public void enterPrologo(alg.PrologoContext ctx) { }
    public void exitPrologo(alg.PrologoContext ctx) {
        alg.BodyContext a = ctx.body();
        Type.PType type_function = this.exprType.get(ctx.getParent().getChild(0));
        int count = a.instructions().size();
        for (int i = 0; i < count; i++) {
            if (a.instructions(i).ctrl_instruct() != null) {
                if (a.instructions(i).ctrl_instruct().RETURN() != null && a.instructions(i).ctrl_instruct().expressions_list2() != null) {
                    Type.PType value_type = (Type.PType) this.exprType.get(a.instructions(i).ctrl_instruct().expressions_list2().expr());
                    if (type_function != Type.PType.VOID && value_type != null && value_type != type_function) {
                        System.err.println("Return na linha " + a.instructions(i).ctrl_instruct().start.getLine() + " retorna valores do tipo " + value_type.toString() + " e deve retornar do tipo " + type_function.toString());
                        ++this.semanticErrors;
                    }
                    if (value_type == null) {
                        System.err.println("Return na linha " + a.instructions(i).ctrl_instruct().start.getLine() + " retorna valores indefinidos e deve retornar do tipo " + type_function.toString());
                        ++this.semanticErrors;
                    }
                    if (type_function == Type.PType.VOID) {
                        System.err.println("Return na linha " + a.instructions(i).ctrl_instruct().start.getLine() + " não deve retornar valores, função tipo " + type_function.toString());
                        ++this.semanticErrors;
                    }
                } else {
                    if (type_function != Type.PType.VOID) {
                        System.err.println("Return na linha " + a.instructions(i).ctrl_instruct().start.getLine() + " deve retornar valor");
                        ++this.semanticErrors;
                    }
                }
            }
            if (a.instructions(i).sub_block() != null) {
                int count_block = a.instructions(i).sub_block().instructions().size();
                for (int j = 0; j < count_block; j++) {
                    if (a.instructions(i).sub_block().instructions(j).ctrl_instruct() != null) {
                        if (a.instructions(i).sub_block().instructions(j).ctrl_instruct().RETURN() != null && a.instructions(i).sub_block().instructions(j).ctrl_instruct().expressions_list2() != null) {
                            Type.PType value_type = (Type.PType) this.exprType.get(a.instructions(i).sub_block().instructions(j).ctrl_instruct().expressions_list2().expr());
                            if (type_function != Type.PType.VOID && value_type != null && value_type != type_function) {
                                System.err.println("Return na linha " + a.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " retorna valores do tipo " + value_type.toString() + " e deve retornar do tipo " + type_function.toString());
                                ++this.semanticErrors;
                            }
                            if (value_type == null) {
                                System.err.println("Return na linha " + a.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " retorna valores indefinidos e deve retornar do tipo " + type_function.toString());
                                ++this.semanticErrors;
                            }
                            if (type_function == Type.PType.VOID) {
                                System.err.println("Return na linha " + a.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " não deve retornar valores, função tipo " + type_function.toString());
                                ++this.semanticErrors;
                            }
                        } else {
                            if (type_function != Type.PType.VOID) {
                                System.err.println("Return na linha " + a.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " deve retornar valor");
                                ++this.semanticErrors;
                            }
                        }
                    }
                }
            }
        }
    }
    public void enterEpilogo(alg.EpilogoContext ctx) { }
    public void exitEpilogo(alg.EpilogoContext ctx) {
        alg.BodyContext a = ctx.body();
        Type.PType type_function = this.exprType.get(ctx.getParent().getChild(0));
        int count = a.instructions().size();
        for (int i = 0; i < count; i++) {
            if (a.instructions(i).ctrl_instruct() != null) {
                if (a.instructions(i).ctrl_instruct().RETURN() != null && a.instructions(i).ctrl_instruct().expressions_list2() != null) {
                    Type.PType value_type = (Type.PType) this.exprType.get(a.instructions(i).ctrl_instruct().expressions_list2().expr());
                    if (type_function != Type.PType.VOID && value_type != null && value_type != type_function) {
                        System.err.println("Return na linha " + a.instructions(i).ctrl_instruct().start.getLine() + " retorna valores do tipo " + value_type.toString() + " e deve retornar do tipo " + type_function.toString());
                        ++this.semanticErrors;
                    }
                    if (value_type == null) {
                        System.err.println("Return na linha " + a.instructions(i).ctrl_instruct().start.getLine() + " retorna valores indefinidos e deve retornar do tipo " + type_function.toString());
                        ++this.semanticErrors;
                    }
                    if (type_function == Type.PType.VOID) {
                        System.err.println("Return na linha " + a.instructions(i).ctrl_instruct().start.getLine() + " não deve retornar valores, função tipo " + type_function.toString());
                        ++this.semanticErrors;
                    }
                } else {
                    if (type_function != Type.PType.VOID) {
                        System.err.println("Return na linha " + a.instructions(i).ctrl_instruct().start.getLine() + " deve retornar valor");
                        ++this.semanticErrors;
                    }
                }
            }
            if (a.instructions(i).sub_block() != null) {
                int count_block = a.instructions(i).sub_block().instructions().size();
                for (int j = 0; j < count_block; j++) {
                    if (a.instructions(i).sub_block().instructions(j).ctrl_instruct() != null) {
                        if (a.instructions(i).sub_block().instructions(j).ctrl_instruct().RETURN() != null && a.instructions(i).sub_block().instructions(j).ctrl_instruct().expressions_list2() != null) {
                            Type.PType value_type = (Type.PType) this.exprType.get(a.instructions(i).sub_block().instructions(j).ctrl_instruct().expressions_list2().expr());
                            if (type_function != Type.PType.VOID && value_type != null && value_type != type_function) {
                                System.err.println("Return na linha " + a.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " retorna valores do tipo " + value_type.toString() + " e deve retornar do tipo " + type_function.toString());
                                ++this.semanticErrors;
                            }
                            if (value_type == null) {
                                System.err.println("Return na linha " + a.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " retorna valores indefinidos e deve retornar do tipo " + type_function.toString());
                                ++this.semanticErrors;
                            }
                            if (type_function == Type.PType.VOID) {
                                System.err.println("Return na linha " + a.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " não deve retornar valores, função tipo " + type_function.toString());
                                ++this.semanticErrors;
                            }
                        } else {
                            if (type_function != Type.PType.VOID) {
                                System.err.println("Return na linha " + a.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " deve retornar valor");
                                ++this.semanticErrors;
                            }
                        }
                    }
                }
            }
        }
    }
    public void enterFunction(alg.FunctionContext ctx) {
        this.scopes.put(ctx,globalScope);
    }

    public void exitFunction(alg.FunctionContext ctx) {
        Type.PType type_function = null;
        alg.BodyContext a = ctx.body();
        if (ctx.function_declare() != null) {
            type_function = (Type.PType) this.exprType.get(ctx.function_declare());
        } if(ctx.functionSpecial()!=null) {
            type_function = Type.PType.INT;
        }
        int count = a.instructions().size();
        for (int i = 0; i < count; i++) {
            if (a.instructions(i).ctrl_instruct() != null) {
                if (a.instructions(i).ctrl_instruct().RETURN() != null && a.instructions(i).ctrl_instruct().expressions_list2() != null) {
                    Type.PType value_type = (Type.PType) this.exprType.get(a.instructions(i).ctrl_instruct().expressions_list2().expr());
                    if (type_function != Type.PType.VOID && value_type != null && value_type != type_function) {
                        System.err.println("Return na linha " + a.instructions(i).ctrl_instruct().start.getLine() + " retorna valores do tipo " + value_type.toString() + " e deve retornar do tipo " + type_function.toString());
                        ++this.semanticErrors;
                    }
                    if (value_type == null) {
                        System.err.println("Return na linha " + a.instructions(i).ctrl_instruct().start.getLine() + " retorna valores indefinidos e deve retornar do tipo " + type_function.toString());
                        ++this.semanticErrors;
                    }
                    if (type_function == Type.PType.VOID) {
                        System.err.println("Return na linha " + a.instructions(i).ctrl_instruct().start.getLine() + " não deve retornar valores, função tipo " + type_function.toString());
                        ++this.semanticErrors;
                    }
                } else {
                    if (type_function != Type.PType.VOID) {
                        System.err.println("Return na linha " + a.instructions(i).ctrl_instruct().start.getLine() + " deve retornar valor");
                        ++this.semanticErrors;
                    }
                }
            }
            if (a.instructions(i).sub_block() != null) {
                int count_block = a.instructions(i).sub_block().instructions().size();
                for (int j = 0; j < count_block; j++) {
                    if (a.instructions(i).sub_block().instructions(j).ctrl_instruct() != null) {
                        if (a.instructions(i).sub_block().instructions(j).ctrl_instruct().RETURN() != null && a.instructions(i).sub_block().instructions(j).ctrl_instruct().expressions_list2() != null) {
                            Type.PType value_type = (Type.PType) this.exprType.get(a.instructions(i).sub_block().instructions(j).ctrl_instruct().expressions_list2().expr());
                            if (type_function != Type.PType.VOID && value_type != null && value_type != type_function) {
                                System.err.println("Return na linha " + a.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " retorna valores do tipo " + value_type.toString() + " e deve retornar do tipo " + type_function.toString());
                                ++this.semanticErrors;
                            }
                            if (value_type == null) {
                                System.err.println("Return na linha " + a.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " retorna valores indefinidos e deve retornar do tipo " + type_function.toString());
                                ++this.semanticErrors;
                            }
                            if (type_function == Type.PType.VOID) {
                                System.err.println("Return na linha " + a.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " não deve retornar valores, função tipo " + type_function.toString());
                                ++this.semanticErrors;
                            }
                        } else {
                            if (type_function != Type.PType.VOID) {
                                System.err.println("Return na linha " + a.instructions(i).sub_block().instructions(j).ctrl_instruct().start.getLine() + " deve retornar valor");
                                ++this.semanticErrors;
                            }
                        }
                    }
                }
            }
        }
        this.currentFunction = null;
        this.currentScope = this.currentScope.getParentScope();
    }

        public void exitDefineNull(alg.DefineNullContext ctx)
        {
            String name = ctx.IDENT().getText();
            Symbol s = this.currentScope.resolve(name);
            if (s==null)
            {
                System.err.println("A variavel " + name + " na linha " + ctx.start.getLine() + " não está definida");
                ++this.semanticErrors;
                return;
            }

            if(s.type.getPrimitiveType()!= Type.PType.PINT && s.type.getPrimitiveType()!= Type.PType.PSTRING && s.type.getPrimitiveType()!= Type.PType.PFLOAT && s.type.getPrimitiveType()!= Type.PType.PNULL)
            {
                System.err.println("A variavel " + name + " na linha " + ctx.start.getLine() + " é do tipo " + s.type.getPrimitiveType()+ " e não pode ser NULL");
                ++this.semanticErrors;
                return;
            }

            else
            {
                s.type = new Type("pnull");
                this.exprType.put(ctx, Type.PType.PNULL);
            }
        }

        public void enterFunction_invocate(alg.Function_invocateContext ctx) { }

    public void exitFunction_invocate(alg.Function_invocateContext ctx)
    {
        //FALTA ADICIONAR QUANDO SE PODE METER REAL COMO INTEIRO, OU NULL COMO PONTEIRO)
        if(ctx.WRITE() != null || ctx.WRITELN() != null)
        {
            int childCount = ctx.getChildCount();
            int exprIDX = 0;
            for (int i=0;i<childCount;i++)
            {
                if(ctx.getChild(i)==ctx.expr(exprIDX))
                {
                    Type.PType e1 = (Type.PType)this.exprType.get(ctx.expr(exprIDX));
                    if(e1 != Type.PType.INT && e1 != Type.PType.FLOAT && e1 != Type.PType.STRING && e1 != Type.PType.BOOL)
                    {
                        System.err.println("Ao utilizar write/writeln na linha " + ctx.start.getLine() + " os argumentos utilizados apenas devem ser do tipo primitivo: INT,BOOL,STRING,FLOAT.");
                        ++this.semanticErrors;
                    }
                    exprIDX++;
                }
            }
        }
        else {
        String name = ctx.IDENT().getText();
        Symbol s = this.currentScope.resolve(name);
        int countArgs = 0;
        if (ctx.getChildCount() > 4) {
            for (int i = 0; i < ctx.getChildCount(); i++) {
                if (ctx.getChild(i).getText().equals(","))
                    countArgs++;
            }

            if (countArgs > 0)
                countArgs += 1;
        } else if (ctx.getChildCount() == 4)
            countArgs = 1;


        if (s instanceof FunctionSymbol) {
            int args = ((FunctionSymbol) s).arguments.size();
            if (args < countArgs) {
                System.err.println("A chamada à função " + name + " na linha " + ctx.start.getLine() + " tem demasiados argumentos. Retire " + (countArgs - args));
                ++this.semanticErrors;
                return;
            } else if (args > countArgs) {
                System.err.println("A chamada à função " + name + " na linha " + ctx.start.getLine() + " tem poucos argumentos. Adicione " + (-(countArgs - args)));
                ++this.semanticErrors;
                return;
            } else {
                int temporary = this.semanticErrors;
                int z = 0;
                for (int i = 2; i < ctx.getChildCount() - 1; i += 2) {
                    String temp = ctx.getChild(i).getChild(0).getText();
                    Symbol name2 = this.currentScope.resolve(temp);
                    if (name2 != null) {
                        if (name2.type.equals(Type.PType.INT) || name2.type.equals(Type.PType.FLOAT)) {
                            if (!((FunctionSymbol) s).arguments.get(z).type.equals(Type.PType.INT) && !((FunctionSymbol) s).arguments.get(z).type.equals(Type.PType.FLOAT)) {

                                System.err.println("A variavel " + name2.name + " na linha " + ctx.start.getLine() + " deve ser do tipo " + ((FunctionSymbol) s).arguments.get(z).type);
                                ++this.semanticErrors;
                            }
                        } else if (name2.type.equals(Type.PType.PNULL)) {
                            System.out.println("pnull " + ((FunctionSymbol) s).arguments.get(z).type);
                            if (!((FunctionSymbol) s).arguments.get(z).type.equals(Type.PType.PSTRING) && !((FunctionSymbol) s).arguments.get(z).type.equals(Type.PType.PFLOAT) && !((FunctionSymbol) s).arguments.get(z).type.equals(Type.PType.PINT)) {

                                System.err.println("A variavel " + name2.name + " na linha " + ctx.start.getLine() + " deve ser do tipo " + ((FunctionSymbol) s).arguments.get(z).type);
                                ++this.semanticErrors;
                            }
                        } else if (!name2.type.equals(((FunctionSymbol) s).arguments.get(z).type)) {

                            System.err.println("A variavel " + name2.name + " na linha " + ctx.start.getLine() + " deve ser do tipo " + ((FunctionSymbol) s).arguments.get(z).type);
                            ++this.semanticErrors;
                        }
                    }
                    z++;
                }
                if (temporary == this.semanticErrors)
                    this.exprType.put(ctx, s.type.getPrimitiveType());
            }

        } else if (s == null) {
            System.err.println("Função " + name + " indefinida na linha " + ctx.IDENT().getSymbol().getLine() + " posição " + ctx.IDENT().getSymbol().getCharPositionInLine());
            ++this.semanticErrors;
            //exprType.put(ctx,Type.PType.ERROR);
            this.exprType.put(ctx, Type.PType.ERROR);
        } else if (!(s instanceof FunctionSymbol)) {
            System.err.println("A usar a variável " + name + " como função, na linha " + ctx.IDENT().getSymbol().getLine());
            ++this.semanticErrors;
            this.exprType.put(ctx, Type.PType.ERROR);
        }
    }
    }

    public void enterCtrl_instruct(alg.Ctrl_instructContext ctx) { }
    public void exitCtrl_instruct(alg.Ctrl_instructContext ctx) { }
    public void enterAttributes(alg.AttributesContext ctx) { }
    public void exitAttributes(alg.AttributesContext ctx) { }
    public void enterInstructions(alg.InstructionsContext ctx) { }

    public void exitInstructions(alg.InstructionsContext ctx) {
        if(ctx.attributes() != null)
        {
            String variableName = ctx.attributes().IDENT().getText();

            Symbol s = this.currentScope.resolve(variableName);

            if(s==null)
            {
                System.err.println("Variavel " + variableName + " indefinida, na linha " + ctx.attributes().IDENT().getSymbol().getLine() + " posição " + ctx.attributes().IDENT().getSymbol().getCharPositionInLine());
                ++this.semanticErrors;
                this.exprType.put(ctx, Type.PType.ERROR);
            }

            else if(s instanceof FunctionSymbol)
            {
                System.err.println("A usar a função " + variableName + " como variável, na linha " + ctx.attributes().IDENT().getSymbol().getLine());
                ++this.semanticErrors;
                this.exprType.put(ctx, Type.PType.ERROR);
            }

            else
                exprType.put(ctx,s.type.getPrimitiveType());
        }
    }
    public void enterIf_cond(alg.If_condContext ctx) { }

    public void exitIf_cond(alg.If_condContext ctx)
    {
        int count = ctx.getChildCount();
        if(ctx.ELSE() != null)
            count -=6;
        else
            count-=4;
        if(ctx.E_LOGICO()!=null||ctx.OU_LOGICO()!=null) {
            for (int i = 3; i < count; i += 2) {
                Type.PType e1 = (Type.PType) this.exprType.get(ctx.getChild(i - 1));
                Type.PType e2 = (Type.PType) this.exprType.get(ctx.getChild(i + 1));
                String operator = ((ParseTree) ctx.children.get(i)).getText();
                //System.out.println(operator);

                this.verifyLogicals(ctx, operator, e1, e2);
            }
        }
    }

    public void enterLoop(alg.LoopContext ctx) { }
    public void exitLoop(alg.LoopContext ctx) { }
    public void enterSub_block(alg.Sub_blockContext ctx) { }
    public void exitSub_block(alg.Sub_blockContext ctx) { }
    public void enterVariable(alg.VariableContext ctx) { }

    public void exitVariable(alg.VariableContext ctx)
    {
        if(ctx.inteiro() != null)
        {
            /*if(ctx.inteiro().getChild(2).getText().equals("["))
            {
                if(defineSymbol(ctx,new Symbol(ctx.getChild(0).getChild(0).getText(),ctx.getChild(0).getChild(1).getText()))) {
                    exprType.put(ctx, Type.PType.INT);
                    return;
                }

            }*/
            if(ctx.inteiro().INDEX_POINT_L() != null)
            {
                Symbol s1 = this.currentScope.resolve(ctx.inteiro().getChild(0).getText());
                Symbol s2 = this.currentScope.resolve(ctx.inteiro().getChild(2).getText());
                Type.PType e1 = s1.type.getPrimitiveType();
                Type.PType e2;
                if(s2 != null)
                {
                    e2 = s2.type.getPrimitiveType();
                }
                else
                    e2 = (Type.PType)this.exprType.get(ctx.inteiro().getChild(2));
                String operator1 = ((ParseTree)ctx.inteiro().INDEX_POINT_L()).getText();
                String operator2 = ((ParseTree)ctx.inteiro().INDEX_POINT_R()).getText();
                this.verifyIndexPointer(ctx, operator1, operator2, e1, e2);
                return;
            }
            String id = ctx.inteiro().IDENT().get(0).getText();
            if(ctx.inteiro().INT()!=null)
            {
                defineSymbol(ctx,new Symbol(new Type(ctx.getChild(0).getChild(0).getText()),ctx.getChild(0).getChild(1).getText()));
                exprType.put(ctx, Type.PType.INT);
            }

            if(ctx.inteiro().equals().getChildCount() == 2)
            {
                Type.PType e1 = this.exprType.get(ctx.inteiro().equals().getChild(1));
                String name = ctx.inteiro().IDENT(0).getText();
                Symbol s = this.currentScope.resolve(name);
                if(s==null)
                {
                    System.err.println("Variável " + name + " indefinida na linha " + ctx.start.getLine() + " posição " + ctx.inteiro().IDENT(0).getSymbol().getCharPositionInLine() );
                    ++this.semanticErrors;
                    return;
                }
                else if(e1!=null)
                {
                    if(e1 == Type.PType.FLOAT)
                    {
                        System.err.println("Na linha " + ctx.start.getLine() + " deve atribuir um valor do tipo INT e não do tipo " + e1);
                        ++this.semanticErrors;
                        return;
                    }
                }
            }

            else
            {
                Symbol s = this.currentScope.resolve(id);
                if(s!=null)
                    if(!s.type.getPrimitiveType().equals(Type.PType.INT))
                    {
                        System.err.println("A variável " + id + " na linha " + ctx.start.getLine() + " é do tipo " + s.type);
                        ++this.semanticErrors;
                        return;
                    }
                if(s==null)
                {
                    System.err.println("Variável " + id + " indefinida na linha " + ctx.inteiro().IDENT(0).getSymbol().getLine() + " posição " + ctx.inteiro().IDENT(0).getSymbol().getCharPositionInLine());
                    ++this.semanticErrors;
                    this.exprType.put(ctx, Type.PType.ERROR);
                }

                else if(s instanceof FunctionSymbol)
                {
                    System.err.println("A usar a função " + id + " como variável na linha " + ctx.inteiro().IDENT(0).getSymbol().getLine());
                    ++this.semanticErrors;
                    this.exprType.put(ctx, Type.PType.ERROR);
                }

                else
                    this.exprType.put(ctx, s.type.getPrimitiveType());

            }

        }

        else if(ctx.real() != null)
        {
            defineSymbol(ctx,new Symbol(new Type(ctx.getChild(0).getChild(0).getText()),ctx.getChild(0).getChild(1).getText()));
            exprType.put(ctx, Type.PType.FLOAT);
        }

        else if(ctx.inteiros() != null
                || ctx.booleanos() !=null
                || ctx.reais() != null
                || ctx.cadeias_caracteres() != null)
        {
            int totalIds = ctx.getChild(0).getChildCount();
            for (int i = 1; i<totalIds; i+=2)
            {
                defineSymbol(ctx,new Symbol(new Type(ctx.getChild(0).getChild(0).getText()),ctx.getChild(0).getChild(i).getText()));
            }
        }
        //--------------------
        else if(ctx.ponteiro_cadeia() !=null
            || ctx.ponteiro_real() != null
            || ctx.ponteiro_inteiro() != null)
        {
            String a = "";
            if(ctx.ponteiro_inteiro() != null)
            {
                if(ctx.ponteiro_inteiro().NULL() != null || ctx.ponteiro_inteiro().getChildCount() == 4)
                    a ="pnull";

                else
                    a = "pint";
            }

            else if(ctx.ponteiro_real() != null) {
                if(ctx.ponteiro_real().NULL() != null || ctx.ponteiro_real().getChildCount() == 4)
                    a ="pnull";
                else
                    a = "pfloat";
            }
            else if(ctx.ponteiro_cadeia()!=null) {
                if(ctx.ponteiro_cadeia().NULL() != null || ctx.ponteiro_cadeia().getChildCount() == 4)
                    a ="pnull";
                else
                    a = "pstring";
            }

            defineSymbol(ctx,new Symbol(new Type(a),ctx.getChild(0).getChild(3).getText()));
        }

        //---------------------------
        else if(ctx.cadeia_caracteres()!=null)
        {
            if(this.currentScope.containsName(ctx.cadeia_caracteres().IDENT().get(0).getText()))
            {
                Symbol s = this.currentScope.resolve(ctx.cadeia_caracteres().IDENT().get(0).getText());
                if(s.type.getPrimitiveType()!= Type.PType.STRING && s.type.getPrimitiveType()!= Type.PType.PSTRING)
                {
                    System.err.println("A variavel " + ctx.cadeia_caracteres().IDENT().get(0).getText() + " na linha " + ctx.start.getLine() + " é do tipo " + s.type.getPrimitiveType());
                    ++this.semanticErrors;
                    return;
                }
            }

            //------------------
            if(ctx.cadeia_caracteres().getChild(0).getText().equals("string"))
            {
                defineSymbol(ctx,new Symbol(new Type(ctx.cadeia_caracteres().getChild(0).getText()),ctx.cadeia_caracteres().getChild(1).getText()));
                exprType.put(ctx, Type.PType.STRING);
            }

            else
            {
                String variableName = ctx.cadeia_caracteres().getChild(0).getText();

                Symbol s = this.currentScope.resolve(variableName);

                if(s==null)
                {
                    System.err.println("A variável " + variableName + " está indefinida, na linha " + ctx.cadeia_caracteres().IDENT().get(0).getSymbol().getLine() + " posição " + ctx.cadeia_caracteres().IDENT().get(0).getSymbol().getCharPositionInLine());
                    ++this.semanticErrors;
                    this.exprType.put(ctx, Type.PType.ERROR);
                }

                else if(s instanceof FunctionSymbol)
                {
                    System.err.println("Usando a variável " + variableName + " como função na linha " + ctx.cadeia_caracteres().IDENT().get(0).getSymbol().getLine());
                    ++this.semanticErrors;
                    this.exprType.put(ctx, Type.PType.ERROR);
                }
                else {
                    this.exprType.put(ctx, s.type.getPrimitiveType());
                }

            }


        }

        else if(ctx.booleano()!=null)
        {
           if(this.currentScope.containsName(ctx.booleano().IDENT().getText()))
            {

                Symbol s = this.currentScope.resolve(ctx.booleano().IDENT().getText());
                if(s!=null) {
                    if (!s.type.equals("bool")) {
                        System.err.println("A variavel " + ctx.booleano().IDENT().getText() + " na linha " + ctx.start.getLine() + " é do tipo " + s.type);
                        ++this.semanticErrors;
                        return;
                    }


                }
            }

            if(ctx.booleano().getChild(0).getText().equals("bool"))
            {
                defineSymbol(ctx,new Symbol(new Type(ctx.booleano().getChild(0).getText()),ctx.booleano().getChild(1).getText()));
                exprType.put(ctx, Type.PType.BOOL);
            }

            else
            {
                String variableName = ctx.booleano().getChild(0).getText();

                Symbol s = this.currentScope.resolve(variableName);

                if(s==null)
                {
                    System.err.println("Bool " + variableName + " indefinido, na linha " + ctx.booleano().IDENT().getSymbol().getLine() + " posição " + ctx.booleano().IDENT().getSymbol().getCharPositionInLine());
                    ++this.semanticErrors;
                    this.exprType.put(ctx, Type.PType.ERROR);
                }

                else if(s instanceof FunctionSymbol)
                {
                    System.err.println("Bool " + variableName + " a ser usado como função na linha " + ctx.booleano().IDENT().getSymbol().getLine());
                    ++this.semanticErrors;
                    this.exprType.put(ctx, Type.PType.ERROR);
                }
                else {
                    this.exprType.put(ctx, s.type.getPrimitiveType());
                }
            }
        }
    }

    public void enterFunctionSpecial(alg.FunctionSpecialContext ctx) {
        this.scopes.put(ctx,globalScope);
            FunctionSymbol f = new FunctionSymbol(new Type("int"), "alg",this.currentScope);
            this.defineSymbol(ctx, f);
            this.currentFunction = f;
            this.currentScope = new Scope(this.currentScope);
            this.exprType.put(ctx, f.type.getPrimitiveType());


        Symbol parameter = new Symbol(new Type("int"), ctx.IDENT(0).getText());
        Symbol parameter2 = new Symbol(new Type("pstring"), ctx.IDENT(1).getText());
        int count = 0;
        if(!parameter.name.equals("n"))
        {
            System.err.println("A função alg deve ter como argumento 'int n'.");
            ++this.semanticErrors;
            count++;
        }
        if(!parameter2.name.equals("args"))
        {
            System.err.println("A função alg deve ter como argumento '<string> args'.");
            ++this.semanticErrors;
            count++;
        }

        if(count >0)
        {
            return;
        }
        else{
            if (defineArgs(ctx, parameter) && this.currentFunction != null) {
                this.currentFunction.arguments.add(parameter);
            }
            if (defineArgs(ctx, parameter2) && this.currentFunction != null) {
                this.currentFunction.arguments.add(parameter2);
            }
        }
    }

    public void exitFunctionSpecial(alg.FunctionSpecialContext ctx) { }

    private boolean isConvertibleTo(Type.PType from, Type.PType to) {
        if (from == to) {
            return true;
        } else {
            return from == Type.PType.INT && to == Type.PType.FLOAT;
        }
    }

    private boolean verifyPrimitiveArithmeticOperator(ParserRuleContext ctx, String operator, Type.PType e1, Type.PType e2) {
        if (e1 != Type.PType.ERROR && e2 != Type.PType.ERROR) {
            if (e1 == Type.PType.INT && e2 == Type.PType.INT) {
                this.exprType.put(ctx, Type.PType.INT);
                return true;
            } else if (e1 == Type.PType.FLOAT && this.isConvertibleTo(e2, e1)) {
                this.exprType.put(ctx, Type.PType.FLOAT);
                return true;
            } else if (e2 == Type.PType.FLOAT && this.isConvertibleTo(e1, e2)) {
                this.exprType.put(ctx, Type.PType.FLOAT);
                return true;
            } else {
                PrintStream var10000 = System.err;
                String var10001 = e1.toString();
                var10000.println("Tipos inválidos para a operação binária " + var10001 + " " + operator + " " + e2.toString() + " na linha " + ctx.start.getLine());
                ++this.semanticErrors;
                this.exprType.put(ctx, Type.PType.ERROR);
                return false;
            }
        } else {
            this.exprType.put(ctx, Type.PType.ERROR);
            return false;
        }
    }

    private boolean verifyEqualDif(ParserRuleContext ctx, String operator, Type.PType e1, Type.PType e2) {
        if (e1 != Type.PType.ERROR && e2 != Type.PType.ERROR) {
            if (e1 == e2  ) {
                this.exprType.put(ctx, Type.PType.BOOL);
                return true;
            }  else if((e1 == Type.PType.PNULL && e2== Type.PType.PFLOAT) || (e1 == Type.PType.PNULL && e2== Type.PType.PINT) || (e1 == Type.PType.PNULL && e2== Type.PType.PSTRING) || (e1 == Type.PType.PFLOAT && e2==  Type.PType.PNULL) || (e1 == Type.PType.PINT && e2==  Type.PType.PNULL) || (e1 == Type.PType.PSTRING && e2==  Type.PType.PNULL) )
            {
                this.exprType.put(ctx, Type.PType.BOOL);
                return true;
            }
            else {
                PrintStream var10000 = System.err;
                String var10001 = e1.toString();
                var10000.println("Tipos inválidos para a comparação equal/dif " + var10001 + " " + operator + " " + e2.toString() + " na linha " + ctx.start.getLine());
                ++this.semanticErrors;
                this.exprType.put(ctx, Type.PType.ERROR);
                return false;
            }
        } else {
            this.exprType.put(ctx, Type.PType.ERROR);
            return false;
        }
    }

    private boolean verifyComparator(ParserRuleContext ctx, String operator, Type.PType e1, Type.PType e2) {
        if (e1 != Type.PType.ERROR && e2 != Type.PType.ERROR) {
            if (e1 == Type.PType.INT && e2 == Type.PType.INT) {
                this.exprType.put(ctx, Type.PType.BOOL);
                return true;
            } else if (e1 == Type.PType.FLOAT && this.isConvertibleTo(e2, e1)) {
                this.exprType.put(ctx, Type.PType.BOOL);
                return true;
            } else if (e2 == Type.PType.FLOAT && this.isConvertibleTo(e1, e2)) {
                this.exprType.put(ctx, Type.PType.BOOL);
                return true;
            } else {
                PrintStream var10000 = System.err;
                String var10001 = e1.toString();
                var10000.println("Tipos inválidos para o comparador " + var10001 + " " + operator + " " + e2.toString() + " na linha " + ctx.start.getLine());
                ++this.semanticErrors;
                this.exprType.put(ctx, Type.PType.ERROR);
                return false;
            }
        } else {
            this.exprType.put(ctx, Type.PType.ERROR);
            return false;
        }
    }

    private boolean verifyLogicals(ParserRuleContext ctx, String operator, Type.PType e1, Type.PType e2) {
        if (e1 != Type.PType.ERROR && e2 != Type.PType.ERROR) {
            if (e1 == Type.PType.BOOL && e2 == Type.PType.BOOL) {
                this.exprType.put(ctx, Type.PType.BOOL);
                return true;
            }
            else {
                PrintStream var10000 = System.err;
                String var10001 = e1.toString();
                var10000.println("Tipos inválidos para operador lógico " + var10001 + " " + operator + " " + e2.toString() + " na linha " + ctx.start.getLine());
                ++this.semanticErrors;
                this.exprType.put(ctx, Type.PType.ERROR);
                return false;
            }
        } else {
            this.exprType.put(ctx, Type.PType.ERROR);
            return false;
        }
    }

    private boolean verifyIndexPointer(ParserRuleContext ctx, String operator1, String operator2, Type.PType e1, Type.PType e2) {
        if (e1 != Type.PType.ERROR && e2 != Type.PType.ERROR) {
            if (e1 == Type.PType.PINT && e2 == Type.PType.INT) {
                this.exprType.put(ctx, Type.PType.INT);
                return true;
            } else if (e1 == Type.PType.PFLOAT && e2 == Type.PType.INT) {
                this.exprType.put(ctx, Type.PType.FLOAT);
                return true;
            } else if (e1 == Type.PType.PSTRING && e2 == Type.PType.INT) {
                this.exprType.put(ctx, Type.PType.STRING);
                return true;
            }
            else {
                PrintStream var10000 = System.err;
                String var10001 = e1.toString();
                var10000.println("Tipos inválidos para POINTER " + var10001 + " " + operator1 + " " + e2.toString() + " " + operator2 + " na linha " + ctx.start.getLine());
                ++this.semanticErrors;
                this.exprType.put(ctx, Type.PType.ERROR);
                return false;
            }
        } else {
            this.exprType.put(ctx, Type.PType.ERROR);
            return false;
        }
    }

    public void enterEveryRule(ParserRuleContext ctx) { }
    public void exitEveryRule(ParserRuleContext ctx) { }
    public void visitTerminal(TerminalNode node) { }
    public void visitErrorNode(ErrorNode node) { }
}

//DUVIDAS
/*  Subbloco numa função void deve conter return sem valor?
    Subbloco numa função de qualquer tipo deve conter return do mesmo tipo?
    Epilogo e Prologo deve ter return?
    SE DÁ ERRO NA OUTRA ANALISE É SUPOSTO MANDAR MENSAGEM DE ERRO AQUI TAMBEM?
 */
