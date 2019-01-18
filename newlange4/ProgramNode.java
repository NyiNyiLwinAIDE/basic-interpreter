package newlange4;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import newlang3.*;

public class ProgramNode extends Node {
	private final static Set<LexicalType> FIRST = new HashSet<>(Arrays.asList(
			LexicalType.IF,
			LexicalType.WHILE,
			LexicalType.DO,
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.NL
		));	
	
	public static boolean isMatch(LexicalType type) {
		return FIRST.contains(type);
	}
	
	private ProgramNode(Environment env) {
		super.env = env;
		type = NodeType.PROGRAM;
	}
	
	public static Node getHandler(Environment env) {
		return StmtListNode.getHandler(env);
	}
	
	public void parse() {
		env.getInput().get();
	}
	
	public String toString() {
		return "Program";
	}
}
