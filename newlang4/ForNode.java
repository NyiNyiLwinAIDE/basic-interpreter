package newlang4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import newlang3.*;

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
		if(env.getInput().get().getType() != LexicalType.FOR) throw new Exception("ForNodeのforのparse部分がおかしいエラー");

		if(SubstNode.isMatch(env.getInput().peek(1).getType())) {
			init = SubstNode.getHandler(env);
			init.parse();
		}else {
			throw new Exception("ForNodeのsubstの部分がおかしいエラー");
		}
		if(env.getInput().peek(1).getType() == LexicalType.TO) {
			env.getInput().get();
		} else {
			throw new Exception("ForNodeのTOの部分がおかしいエラー");
		}
		if(env.getInput().peek(1).getType() == LexicalType.INTVAL) {
			max = ConstNode.getHandler(env);
			max.parse();
		} else {
			throw new Exception("ForNodeのintvalの部分がおかしいエラー");
		}
		if(env.getInput().peek(1).getType() == LexicalType.NL) {
			env.getInput().get();
		} else {
			throw new Exception("ForNodeのNLの部分がおかしいエラー");
		}
		if(StmtListNode.isMatch(env.getInput().peek(1).getType())) {
			symbol = StmtListNode.getHandler(env);
			symbol.parse();
		} else {
			throw new Exception("ForNodeのstmt_listの部分がおかしいエラー");
		}
		System.out.println(env.getInput().peek(1).getType());
		if(env.getInput().peek(1).getType() == LexicalType.NEXT) {
			env.getInput().get();
		} else {
			throw new Exception("ForNodeのNEXTの部分がおかしいエラー");
		}
		if(env.getInput().peek(1).getType() == LexicalType.NAME) {
			step = env.getInput().get().getValue().getSValue();
		} else {
			throw new Exception("ForNodeのNAMEの部分がおかしいエラー");
		}
	}
	
	public String toString() {
		String str = "";
		str += String.format("FOR MAX STMTLIST STEP" + init, max, symbol, step);
		return str;
	}

}
