package newlang4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import newlang3.*;

public class StmtNode extends Node {

	// first集合
	private final static Set<LexicalType> FIRST = new HashSet<>(Arrays.asList(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END
		));	
	public static boolean isMatch(LexicalType type) {
		return FIRST.contains(type);
	}
	
	private StmtNode(Environment env) {
		super.env = env;
		type = NodeType.STMT;
	}
	
	// 次に出てくる字句をparseできるnodeをひとつ返す
	public static Node getHandler(Environment env) throws Exception {
		switch (env.getInput().peek(1).getType()) {
			case NAME:
				
			case FOR:
				
			case END:
				return EndNode.getHandler(env);
			default:
				throw new Exception("StmtNodeのgetHandlerのError");
		}
	}
	
	public void parse() {
	}
	
	public String toString() {
		return "stmt";
	}

}
