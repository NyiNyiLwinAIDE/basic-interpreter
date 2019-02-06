package newlang4;

import newlang3.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class ExprNode extends Node {
    Node hoge;
    LexicalUnit lu;

    private final static Set<LexicalType> FIRST = new HashSet<>(Arrays.asList(
            LexicalType.NAME,
            LexicalType.INTVAL,
            LexicalType.DOUBLEVAL,
            LexicalType.LITERAL
    ));

    public static boolean isMatch(LexicalType type) {
        return FIRST.contains(type);
    }

    private ExprNode(Environment env) {
        super.env = env;
        type = NodeType.EXPR;
    }

    // 次に出てくる字句をparseできるnodeをひとつ返す
    public static Node getHandler(Environment env) {
        return new ExprNode(env);
    }

    public void parse() throws Exception {
        //	ここでは単項式のみ
        lu = env.getInput().peek(1);
        Node handler = null;

        if (ConstNode.isMatch(lu.getType())) {
            handler = ConstNode.getHandler(env);
        }
        handler.parse();
        hoge = handler;
    }

    public String toString() {
        String str = "EXPR:";
        if (hoge != null) {
            str += hoge;
        }
        return str;

    }

    public Value getValue() throws Exception {
        if(lu.getType() == LexicalType.NAME){
            return env.getVariable(lu.getValue().getSValue()).getValue();
        }else{
            return lu.getValue();
        }
    }
}
