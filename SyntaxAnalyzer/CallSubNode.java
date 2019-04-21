package SyntaxAnalyzer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import LexicalAnalyzer.*;

public class CallSubNode extends Node {
    String name;
    ExprListNode arguments;

    private final static Set<LexicalType> FIRST = new HashSet<>(Arrays.asList(
            LexicalType.NAME
    ));

    public static boolean isMatch(LexicalType type) {
        return FIRST.contains(type);
    }

    private CallSubNode(Environment env) {
        super.env = env;
        type = NodeType.FUNCTION_CALL;
    }

    public static Node getHandler(Environment env) throws Exception {
        return new CallSubNode(env);
    }

    public void parse() throws Exception {
        if (env.getInput().peek(1).getType() == LexicalType.NAME) {
            name = env.getInput().get().getValue().getSValue();
        } else {
            throw new Exception("CallSubNodeのNAMEのparse部分のエラー");
        }
        if (ExprListNode.isMatch(env.getInput().peek(1).getType())) {
            arguments = ExprListNode.getHandler(env);
            arguments.parse();
        } else {
            throw new Exception("CallSubNodeのExprListNodeのparse部分のエラー");
        }
    }

    public String toString() {
        return "FUNC" + name + "ARGUMENTS_LIST" + arguments;
    }

    public Value getValue() throws Exception {
        return env.getFunction(name).invoke(arguments);
    }
}
