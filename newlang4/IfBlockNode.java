package newlang4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import java.util.List;

import newlang3.LexicalType;

public class IfBlockNode extends Node {
List<Node> cond = new ArrayList<>();
List<Node> trueprocess = new ArrayList<>();
Node elseprocess = null;

private final static Set<LexicalType> FIRST = new HashSet<>(Arrays.asList(
		LexicalType.IF
		));

public static boolean isMatch(LexicalType type) {
	return FIRST.contains(type);
}

private IfBlockNode(Environment env) {
	super.env = env;
	type = NodeType.IF_BLOCK;
}

// 次に出てくる字句をparseできるnodeをひとつ返す
public static Node getHandler(Environment env) throws Exception {
	return new IfBlockNode(env);
}

public void parse() throws Exception {
	if(env.getInput().get().getType() != LexicalType.IF) {
		throw new Exception("IFじゃないよ");
	}

	if(CondNode.isMatch(env.getInput().peek(1).getType())) {
		Node handler = null;
		handler = CondNode.getHandler(env);
		handler.parse();
		cond.add(handler);
	}

	if(env.getInput().get().getType() != LexicalType.THEN) {
		throw new Exception("THENじゃないよ");
	}

	if(env.getInput().get().getType() != LexicalType.NL) {
		throw new Exception("NLじゃないよ");
	}
	if(StmtListNode.isMatch(env.getInput().peek(1).getType())) {
		Node handler = null;
		handler = StmtListNode.getHandler(env);
		handler.parse();
		trueprocess.add(handler);
	}
	if(env.getInput().get().getType() != LexicalType.NL) throw new Exception("NLじゃないよ");
	while (env.getInput().peek(1).getType() == LexicalType.ELSEIF) {
		env.getInput().get();

		if(CondNode.isMatch(env.getInput().peek(1).getType())) {
			Node handler = null;
			handler = CondNode.getHandler(env);
			handler.parse();
			cond.add(handler);
		}

		if(env.getInput().get().getType() != LexicalType.THEN) {
			throw new Exception("THENじゃないよ");
		}

		if(env.getInput().get().getType() != LexicalType.NL) {
			throw new Exception("NLじゃないよ");
		}
		if(StmtListNode.isMatch(env.getInput().peek(1).getType())) {
			Node handler = null;
			handler = StmtListNode.getHandler(env);
			handler.parse();
			trueprocess.add(handler);
		}
		if(env.getInput().get().getType() != LexicalType.NL) throw new Exception("NLじゃないよ");
	}
	if (env.getInput().peek(1).getType() == LexicalType.ELSE) {
		env.getInput().get();


		if(env.getInput().get().getType() != LexicalType.NL) throw new Exception("NLじゃないよ");

		if(StmtListNode.isMatch(env.getInput().peek(1).getType())) {
			Node handler = null;
			handler = StmtListNode.getHandler(env);
			handler.parse();
			elseprocess = handler;
		}
		if(env.getInput().get().getType() != LexicalType.NL) throw new Exception("NLじゃないよ");
	}
	if(env.getInput().get().getType() != LexicalType.ENDIF) throw new Exception("ENDIFじゃないよ");
	if(env.getInput().get().getType() != LexicalType.NL) throw new Exception("NLじゃないよ");

}

public String toString() {
	String str = "";
	str += String.format("IF THEN" + cond, trueprocess);

	if(elseprocess != null) {
		str += String.format("ELSE" + elseprocess);
	}
	return str;

}
}
