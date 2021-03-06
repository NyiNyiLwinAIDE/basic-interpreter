package SyntaxAnalyzer;

import java.util.HashMap;

import LexicalAnalyzer.*;

public class Environment {
    LexicalAnalyzer input;
    HashMap <String, Function> library;
    HashMap <String, VariableNode> var_table;

    public Environment(LexicalAnalyzer my_input) {
        input = my_input;
        library = new HashMap<>();
        library.put("PRINT", new PrintFunction());
        var_table = new HashMap<>();
    }

    public LexicalAnalyzer getInput() {
        return input;
    }

    public Function getFunction(String fname) {
        return (Function) library.get(fname);
    }

    public VariableNode getVariable(String vname) {
        VariableNode v;
        v = (VariableNode) var_table.get(vname);
        if (v == null) {
            v = new VariableNode(vname);
            var_table.put(vname, v);
        }
        return v;
    }
}
