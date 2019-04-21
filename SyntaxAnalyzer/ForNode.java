package SyntaxAnalyzer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import LexicalAnalyzer.*;

public class ForNode extends Node {
    Node init;
    Node max;
    Node symbol;
    String step;

    private final static Set<LexicalType> FIRST = new HashSet<>(Arrays.asList(
            LexicalType.FOR
    ));

    public static boolean isMatch(LexicalType type) {
        return FIRST.contains(type);
    }

    private ForNode(Environment env) {
        super.env = env;
        type = NodeType.FOR_STMT;
    }

    // 次に出てくる字句をparseできるnodeをひとつ返す
    public static Node getHandler(Environment env) throws Exception {
        return new ForNode(env);
    }

    public void parse() throws Exception {
        if (env.getInput().get().getType() != LexicalType.FOR) throw new Exception("ForNodeのforのparse部分がおかしいエラー");

        if (SubstNode.isMatch(env.getInput().peek(1).getType())) {
            init = SubstNode.getHandler(env);
            init.parse();
        } else {
            throw new Exception("ForNodeのsubstの部分がおかしいエラー");
        }
        if (env.getInput().peek(1).getType() == LexicalType.TO) {
            env.getInput().get();
        } else {
            throw new Exception("ForNodeのTOの部分がおかしいエラー");
        }
        if (env.getInput().peek(1).getType() == LexicalType.INTVAL) {
            max = ConstNode.getHandler(env);
            max.parse();
        } else {
            throw new Exception("ForNodeのintvalの部分がおかしいエラー");
        }
        if (env.getInput().peek(1).getType() == LexicalType.NL) {
            env.getInput().get();
        } else {
            throw new Exception("ForNodeのNLの部分がおかしいエラー");
        }
        if (StmtListNode.isMatch(env.getInput().peek(1).getType())) {
            symbol = StmtListNode.getHandler(env);
            symbol.parse();
        } else {
            throw new Exception("ForNodeのstmt_listの部分がおかしいエラー");
        }
        if (env.getInput().get().getType() != LexicalType.NL) throw new Exception("NLじゃないよ");
        if (env.getInput().peek(1).getType() == LexicalType.NEXT) {
            env.getInput().get();
        } else {
            throw new Exception("ForNodeのNEXTの部分がおかしいエラー");
        }
        if (env.getInput().peek(1).getType() == LexicalType.NAME) {
            step = env.getInput().get().getValue().getSValue();
        } else {
            throw new Exception("ForNodeのNAMEの部分がおかしいエラー");
        }
    }

    public String toString() {
        String str = "";
        str += "FOR" + init + "MAX" + max + "STMTLIST" + symbol + "STEP" + step;
        return str;
    }

    public Value getValue() throws Exception {
        init.getValue();


        while (true) {
            if (env.getVariable(step).getValue().getIValue() > max.getValue().getIValue()) {
                break;
            }
            symbol.getValue();
            int i = env.getVariable(step).getValue().getIValue() + 1;
            env.getVariable(step).setValue(new ValueImple("" + i, ValueType.INTEGER));
        }
        return null;
    }

}
