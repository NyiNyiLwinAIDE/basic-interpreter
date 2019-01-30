package newlang4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import newlang3.*;

public class CondNode extends Node {
	Node left;
	Node right;
	LexicalType symbols;
	
	private final static Set<LexicalType> FIRST=new HashSet<>(Arrays.asList(
			LexicalType.NAME,
			LexicalType.INTVAL,
			LexicalType.DOUBLEVAL,
			LexicalType.LITERAL
		));
	
	private final static Set<LexicalType> SYMBOLS = new HashSet<>(Arrays.asList(
			LexicalType.EQ,			// =
			LexicalType.LT,			// <
			LexicalType.GT,			// >
			LexicalType.LE,			// <=, =<
			LexicalType.GE,			// >=, =>
			LexicalType.NE			// <>
		));	
	public static boolean isMatch(LexicalType type) {
		return FIRST.contains(type);
	}
	
	private CondNode(Environment env) {
		super.env = env;
		type = NodeType.COND;
	}
	
	// 次に出てくる字句をparseできるnodeをひとつ返す
	public static Node getHandler(Environment env) throws Exception {
		return new CondNode(env);
	}
	
	public void parse() throws Exception {
		//	Exprをチェックする
		if(ExprNode.isMatch(env.getInput().peek(1).getType())) {
			left = ExprNode.getHandler(env);
			left.parse();
		} else {
			throw new Exception("CondNodeのExprのparseエラー");
		}
		if(SYMBOLS.contains(env.getInput().peek(1).getType())) {
			symbols = env.getInput().get().getType();
		} else {
			throw new Exception("CondNodeのsymbolのparseエラー");
		}
		if(ExprNode.isMatch(env.getInput().peek(1).getType())) {
			right = ExprNode.getHandler(env);
			right.parse();
		} else {
			throw new Exception("CondNodeのExprのparseエラー");
		}
	} 
	
	public String toString() {
		return "COND" + left + symbols + right;
	}

}
