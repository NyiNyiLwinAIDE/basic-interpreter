package newlang4;


import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import newlang3.*;

public class EndNode extends Node {
    private final static Set<LexicalType> FIRST = new HashSet<>(Arrays.asList(
            LexicalType.END
    ));

    public static boolean isMatch(LexicalType type) {
        return FIRST.contains(type);
    }

    private EndNode(Environment env) {
        super.env = env;
        type = NodeType.END;
    }

    public static Node getHandler(Environment env) {
        return new EndNode(env);
    }

    public void parse() throws Exception {
        env.getInput().get();
    }

    public String toString() {
        return "end";
    }

    public Value getValue() throws Exception {
        System.exit(0);
        return null;
    }
}
