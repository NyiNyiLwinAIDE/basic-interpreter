package newlang4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import newlang3.LexicalType;

public class BlockNode extends Node {
	
	private final static Set<LexicalType> FIRST = new HashSet<>(Arrays.asList(
			LexicalType.IF
		));	
	public static boolean isMatch(LexicalType type) {
		return FIRST.contains(type);
	}
	
	private BlockNode(Environment env) {
		super.env = env;
		type = NodeType.BLOCK;
	}
	
	// 次に出てくる字句をparseできるnodeをひとつ返す
	public static Node getHandler(Environment env) throws Exception {
		switch (env.getInput().peek(1).getType()) {
			case IF:
				return IfBlockNode.getHandler(env);
			default:
				throw new Exception("StmtNodeのgetHandlerのError");
		}
	}
	
	public void parse() {
	}
	
	public String toString() {
		return "block";
	}
}
