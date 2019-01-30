package newlang4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import newlang3.*;

public class StmtListNode extends Node {
	List<Node> list = new ArrayList<>();
	
	// first集合
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
	
	private StmtListNode(Environment env) {
		super.env = env;
		type = NodeType.STMT_LIST;
	}
	// 次に出てくる字句をparseできるnodeをひとつ返す
	public static Node getHandler(Environment env) {
		return new StmtListNode(env);
	}
	
	public void parse() throws Exception{
		LexicalUnit lu = env.getInput().peek(1);
		Node handler = null;

		while(true) {
			while (lu.getType() == LexicalType.NL && StmtListNode.isMatch(env.getInput().peek(2).getType())) {
				env.getInput().get();
			}{
				// stmtがfirst集合に含まれるとき
				if (StmtNode.isMatch(lu.getType())) {
					handler = StmtNode.getHandler(env);
				} else if (BlockNode.isMatch(lu.getType())) {
					handler = BlockNode.getHandler(env);
				} else {
					return;
				}
				handler.parse();
				list.add(handler);
			}
		}

	}
	
	public String toString() {
		return list.toString();
	}
}
