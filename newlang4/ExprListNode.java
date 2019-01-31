package newlang4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import newlang3.LexicalType;
import newlang3.LexicalUnit;

public class ExprListNode extends Node {
List<Node> list = new ArrayList<>();

// first集合
private final static Set<LexicalType> FIRST = new HashSet<>(Arrays.asList(
		LexicalType.NAME,
		LexicalType.SUB,
		LexicalType.LP,
		LexicalType.INTVAL,
		LexicalType.DOUBLEVAL,
		LexicalType.LITERAL
	));
public static boolean isMatch(LexicalType type) {
	return FIRST.contains(type);
}

private ExprListNode(Environment env) {
	super.env = env;
	type = NodeType.EXPR_LIST;
}
// 次に出てくる字句をparseできるnodeをひとつ返す
public static ExprListNode getHandler(Environment env) {
	return new ExprListNode(env);
}

public void parse() throws Exception{
	Node handler;
	if(ExprNode.isMatch(env.getInput().peek(1).getType())) {
		handler = ExprNode.getHandler(env);
		handler.parse();
		list.add(handler);
	} else {
		throw new InternalError("ExprListNodeのparse部分のエラー");
	}

	while(true) {
		if (env.getInput().peek(1).getType() == LexicalType.COMMA) {
			env.getInput().get();
		} else {
			break;
		}
		if (ExprNode.isMatch(env.getInput().peek(1).getType())) {
			handler = ExprNode.getHandler(env);
			handler.parse();
			list.add(handler);
		} else {
			throw new Exception("ExprListNodeのExprNodeのparse部分がおかしいエラー");
		}
	}
}

public String toString() {
	return list.toString();
}
}
