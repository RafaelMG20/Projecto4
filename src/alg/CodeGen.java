package alg;

import Symbols.FunctionSymbol;
import Symbols.Scope;
import Symbols.Symbol;
import Symbols.Type;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.io.BufferedWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CodeGen extends algBaseVisitor<Symbol>{

    private int tmpCounter = 0;
    private int labelCounter = 0;
    private BufferedWriter out;
    private Scope globalScope;
    public List<String> code;
    private Stack<String> whileAfterLabels;
    private Stack<String> whileBeforeLabels;

    public ParseTreeProperty<Scope> scopes;

    public CodeGen(ParseTreeProperty<Scope> scopes)
    {
        this.scopes = scopes;
        code = new ArrayList<String>();
        this.whileAfterLabels = new Stack<>();
        this.whileBeforeLabels = new Stack<>();
    }

    private Symbol widen(ParserRuleContext ctx, Symbol addr, Type down, Type up)
    {
        //se são do mesmo tipo não há cast a fazer
        if(down.equals(up)) return addr;
        else if(Type.isConvertibleTo(down,up))
        {
            Symbol t = temp(ctx,up);
            emit(t.name + " = (" + up + ") " + addr.name );
            return t;
        }
        return null;
    }

    private int emit(String s)
    {
        this.code.add(s);
        return this.code.size()-1;
    }

    private void replace(int line, String s)
    {
        this.code.set(line,s);
    }

    private Symbol temp(ParserRuleContext ctx, Type t)
    {
        return this.scopes.get(ctx).defineTemp(t);
    }

    private String label()
    {
        return "_L" + this.labelCounter++;
    }

    private String label(String name)
    {
        return "_L" + name + this.labelCounter++;
    }

    //start : decl+ EOF;
    public Symbol visitPrograma(alg.ProgramaContext ctx)
    {
        //initialize the Global scope
        this.globalScope = new Scope(null);
        visitChildren(ctx);
        //only expressions need to return a proper symbol
        return null;
    }




    //simple expressions
    //simple_exp --> LINT;
    public Symbol visitInt(alg.IntContext ctx)
    {
        Symbol s = new Symbol(new Type(Type.PType.INT),ctx.getText(), ctx.getText());
        //literal constants can be stored as constants in the global scope, indexed by their value
        this.globalScope.define(s);
        //we need to resolve, because it might be the case that the same constant was already declared, and in this case
        //we return the previously created symbol instead of a new one
        return globalScope.resolve(s.name);
    }

    public Symbol visitVar(alg.VarContext ctx)
    {
        Symbol s = this.globalScope.resolve(ctx.IDENT().getText());
        //literal constants can be stored as constants in the global scope, indexed by their value
        this.globalScope.define(s);
        //we need to resolve, because it might be the case that the same constant was already declared, and in this case
        //we return the previously created symbol instead of a new one
        return globalScope.resolve(s.name);
    }


    public Symbol visitInteiro(alg.InteiroContext ctx)
    {
        Symbol s = new Symbol(new Type(Type.PType.INT),ctx.IDENT(0).getText(),ctx.IDENT(0).getText());
        //literal constants can be stored as constants in the global scope, indexed by their value
        this.globalScope.define(s);
        //we need to resolve, because it might be the case that the same constant was already declared, and in this case
        //we return the previously created symbol instead of a new one
        return globalScope.resolve(s.name);
    }

    public Symbol visitPonteiro_inteiro(alg.Ponteiro_inteiroContext ctx)
    {
        Symbol s = new Symbol(new Type(Type.PType.PINT),ctx.IDENT().getText(),ctx.IDENT().getText());
        //literal constants can be stored as constants in the global scope, indexed by their value
        this.globalScope.define(s);
        //we need to resolve, because it might be the case that the same constant was already declared, and in this case
        //we return the previously created symbol instead of a new one
        return globalScope.resolve(s.name);
    }

    public Symbol visitPonteiro_real(alg.Ponteiro_realContext ctx)
    {
        Symbol s = new Symbol(new Type(Type.PType.PFLOAT),ctx.IDENT().getText(),ctx.IDENT().getText());
        //literal constants can be stored as constants in the global scope, indexed by their value
        this.globalScope.define(s);
        //we need to resolve, because it might be the case that the same constant was already declared, and in this case
        //we return the previously created symbol instead of a new one
        return globalScope.resolve(s.name);
    }

    public Symbol visitPonteiro_cadeia(alg.Ponteiro_cadeiaContext ctx)
    {
        Symbol s = new Symbol(new Type(Type.PType.PSTRING),ctx.IDENT().getText(),ctx.IDENT().getText());
        //literal constants can be stored as constants in the global scope, indexed by their value
        this.globalScope.define(s);
        //we need to resolve, because it might be the case that the same constant was already declared, and in this case
        //we return the previously created symbol instead of a new one
        return globalScope.resolve(s.name);
    }

    public Symbol visitReal(alg.RealContext ctx)
    {
        Symbol s = new Symbol(new Type(Type.PType.FLOAT),ctx.IDENT(0).getText(),ctx.IDENT(0).getText());
        //literal constants can be stored as constants in the global scope, indexed by their value
        this.globalScope.define(s);
        //we need to resolve, because it might be the case that the same constant was already declared, and in this case
        //we return the previously created symbol instead of a new one
        return globalScope.resolve(s.name);
    }

    public Symbol visitCadeia_caracteres(alg.Cadeia_caracteresContext ctx)
    {
        String strText = ctx.equals_string().CADEIA_CARACTERES().getText();
        //we need to remove the trailing "" and add the null character
        strText = strText.substring(1,strText.length()-1) + "\u0000";
        //Strings are special because they are a dynamic multi-byte type
        //however, the size of a string literal is well-known in compile-time, so in this particular case
        // we can properly calculate its size
        Symbol s = new Symbol(new Type(Type.PType.STRING),ctx.IDENT(0).getText(),ctx.IDENT(0).getText());
        s.width = s.type.getWidth() * strText.length(); //we need one extra space for the end of string character \u0000
        //literal constants can be stored as constants in the global scope, indexed by their value
        s.isWidthKnown = true;
        this.globalScope.define(s);
        //we need to resolve, because it might be the case that the same constant was already declared, and in this case
        //we return the previously created symbol instead of a new one
        return globalScope.resolve(s.name);
    }

    public Symbol visitBooleano(alg.BooleanoContext ctx)
    {
        Symbol s = new Symbol(new Type(Type.PType.BOOL),ctx.IDENT().getText(),ctx.IDENT().getText());
        //literal constants can be stored as constants in the global scope, indexed by their value
        this.globalScope.define(s);
        //we need to resolve, because it might be the case that the same constant was already declared, and in this case
        //we return the previously created symbol instead of a new one
        return globalScope.resolve(s.name);
    }

    public Symbol visitFunction_declare (alg.Function_declareContext ctx)
    {
        emit(ctx.IDENT().getText() + ":\n");
        return null;
    }

    public Symbol visitFunctionSpecial(alg.FunctionSpecialContext ctx)
    {
        emit("alg:\n");
        return null;
    }



    //TEMOS DE FAZER ISTO
    /*public Symbol visitPointer(alg.PointerContext ctx)
    {
        String s = ctx.op_pointer().
        Symbol s = new Symbol(new Type(Type.PType.INT),ctx.getText(), ctx.getText());
        //literal constants can be stored as constants in the global scope, indexed by their value
        globalScope = new Scope(null);
        this.globalScope.define(s);
        //we need to resolve, because it might be the case that the same constant was already declared, and in this case
        //we return the previously created symbol instead of a new one
        return globalScope.resolve(s.name);
    }*/

    //simple_exp --> LITERAL_R
   /*public Symbol visitFloat(alg.FloatContext ctx)
    {
        Symbol s = new Symbol(new Type(Type.PType.FLOAT),ctx.getText(),ctx.getText());
        //literal constants can be stored as constants in the global scope, indexed by their value
        this.globalScope.define(s);
        //we need to resolve, because it might be the case that the same constant was already declared, and in this case
        //we return the previously created symbol instead of a new one
        return globalScope.resolve(s.name);
    }*/
    //simple_exp --> LITERAL_STRING
    /*public Symbol visitLString(alg.LStringContext ctx)
    {
        String strText = ctx.getText();
        //we need to remove the trailing "" and add the null character
        strText = strText.substring(1,strText.length()-1) + "\u0000";
        //Strings are special because they are a dynamic multi-byte type
        //however, the size of a string literal is well-known in compile-time, so in this particular case
        // we can properly calculate its size
        Symbol s = new Symbol(new Type(Type.PType.STRING),strText,strText);
        s.width = s.type.getWidth() * strText.length(); //we need one extra space for the end of string character \u0000
        //literal constants can be stored as constants in the global scope, indexed by their value
        s.isWidthKnown = true;
        this.globalScope.define(s);
        //we need to resolve, because it might be the case that the same constant was already declared, and in this case
        //we return the previously created symbol instead of a new one
        return globalScope.resolve(s.name);
    }*/

 /*   //simple_exp --> Null
    public Symbol visitLNull(alg.LNullContext ctx)

    {
        //returns the special symbol null. This one doesn't even need to be stored in the symbol table
        return new Symbol(new Type(true, Type.PType.VOID),"null","null");
    }*/

    //simple_exp --> (TRUE|FALSE)
    /*public Symbol visitLBoolean(alg.LBooleanContext ctx)
    {
        Symbol s = new Symbol(new Type(Type.PType.BOOLEAN),ctx.getText(),ctx.getText());
        //literal constants can be stored as constants in the global scope, indexed by their value
        this.globalScope.define(s);
        //we need to resolve, because it might be the case that the same constant was already declared, and in this case
        //we return the previously created symbol instead of a new one
        return globalScope.resolve(s.name);
    }*/

    //simple_exp --> IDENT
  /*  public Symbol visitVar(alg.VarContext ctx)
    {
        return this.scopes.get(ctx).resolve(ctx.IDENT().getText());
    }*/

    /*public Symbol visitSimpleExp(alg.SimpleExpContext ctx)
    {
        return visit(ctx.simple_exp());
    }

    public Symbol visitParen(alg.ParenContext ctx)
    {
        return visit(ctx.exp());
    }*/

    //Task T2
    //exp --> QMark left_side #PointerExtract
    public Symbol visitIdy(alg.IdyContext ctx)
    {
        String s = ctx.IDENT().getText();
        Symbol temp = this.globalScope.resolve(s);
        if(ctx.expr()!=null)
        {
            Symbol s1 = visit(ctx.expr());

            if(s1 != null)
            {
                Symbol t = this.globalScope.defineTemp(temp.type);
                emit(t.name + " = " + s1.name + " " + " * " + " " + temp.width);
                Symbol t1 = this.globalScope.defineTemp(temp.type);
                emit(t1.name + " = " + temp.name + " " + " + " + " " + t.name);

            }
        }

        else
        {
            Symbol t = this.globalScope.defineTemp(temp.type);
            emit(t.name + " = " + "&" + temp.name );
        }

        return null;
    }



   /* public Symbol visitUnary(alg.UnaryContext ctx)
    {
        String op = "";
        int opType = ctx.unary_op().start.getType();
        Symbol s1 = visit(ctx.exp());
        //if it is a + we don't need to do anything
        if(opType==algLexer.PLUS) return s1;
        if(opType==algLexer.MINUS)
        {
            op = "-";
        }
        else if(opType == algLexer.NOT)
        {
            op = "not";
        }

        Symbol t = this.temp(ctx,s1.type);
        emit(t.name + " = " + op + " " +  s1.name);
        return t;
    }*/

    public Symbol visitMulDiv(alg.MulDivContext ctx)
    {
        Symbol t1 = visit(ctx.expr(0));
        Symbol t2 = visit(ctx.expr(1));
        Type maxType = Type.getMaxType(t1.type,t2.type);
        t1 = widen(ctx,t1,t1.type,maxType);
        t2 = widen(ctx,t2,t2.type,maxType);
        String op = ctx.MULT_DIV().getText();

        Symbol t = this.globalScope.defineTemp(maxType);
        this.globalScope.define(t);
        emit(t.name + " = " + t1.name + " " + op + " " + t2.name);
        return t;
    }

    public Symbol visitAddSub(alg.AddSubContext ctx) {
        Symbol t1 = visit(ctx.expr(0));
        Symbol t2 = visit(ctx.expr(1));
        Type maxType = Type.getMaxType(t1.type,t2.type);
        t1 = widen(ctx,t1,t1.type,maxType);
        t2 = widen(ctx,t2,t2.type,maxType);
        String op = ctx.SOMA_SUB().getText();

        Symbol t = this.globalScope.defineTemp(maxType);
        this.globalScope.define(t);
        emit(t.name + " = " + t1.name + " " + op + " " + t2.name);
        return t;
    }

   /* public Symbol visitCompare(alg.CompareContext ctx) {

        Symbol t1 = visit(ctx.exp(0));
        Symbol t2 = visit(ctx.exp(1));
        Type maxType = Type.getMaxType(t1.type,t2.type);
        t1 = widen(ctx,t1,t1.type,maxType);
        t2 = widen(ctx,t2,t2.type,maxType);
        String op = ctx.comp_op().getText();
        Symbol t = this.temp(ctx,new Type(Type.PType.BOOLEAN));
        emit(t.name + " = " + t1.name + " " + op + " " + t2.name);
        return t;
    }*/

   /* public Symbol visitAnd(alg.AndContext ctx)
    {
        Symbol t1 = visit(ctx.exp(0));
        Symbol t2 = visit(ctx.exp(1));
        Symbol t = this.temp(ctx,new Type(Type.PType.BOOLEAN));

        emit(t.name + "=" + t1.name + " and " + t2.name);
        return t;
    }*/

    /*public Symbol visitOr(alg.OrContext ctx)
    {
        Symbol t1 = visit(ctx.exp(0));
        Symbol t2 = visit(ctx.exp(1));
        Symbol t = this.temp(ctx,new Type(Type.PType.BOOLEAN));

        emit(t.name + "=" + t1.name + " or " + t2.name);
        return t;
    }
*/




  /*

    //instructions
    //inst --> exp PVIR #ExpInst
    public Symbol visitExpInst(alg.ExpInstContext ctx)
    {
        //this is very easy, just visit the expression
        visit(ctx.exp());
        //It is not mandatory to return any symbol in an instruction
        return null;
    }
*/


   /* //inst --> IF exp THEN inst (ELSE inst)?   #If
    public Symbol visitIf(alg.IfContext ctx)
    {
        Symbol condition = visit(ctx.exp());
        String falseLabel = label("false");
        emit("ifFalse " + condition.name + " goto " + falseLabel);
        visit(ctx.inst(0));

        if(ctx.ELSE() == null)
        {
            //Single if
            emit(falseLabel + ":");
        }
        else
        {
            String nextLabel = label("next");
            emit("goto " + nextLabel);
            emit(falseLabel + ":");
            visit(ctx.inst(1));
            emit(nextLabel + ":");
        }

        return null;
    }*/




    public Symbol visitReturn(alg.Ctrl_instructContext ctx)
    {
        emit("return");
        return null;
    }

    public Symbol visitCtrl_instruct(alg.Ctrl_instructContext ctx)
    {
        Symbol returnValue = visit(ctx.expressions_list2().expr());
        if(returnValue != null)
        {
            emit("return " + returnValue.name + "\n");
        }
        else
            emit("return" + " " +  ctx.expressions_list2().expr().getChild(0).getText() + "\n");

        return null;
    }



}