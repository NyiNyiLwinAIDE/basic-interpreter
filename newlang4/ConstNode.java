package newlang4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import newlang3.*;

import newlang3.LexicalType;
import newlang3.Value;

public class ConstNode extends Node {
	Value value = null;

	private final static Set<LexicalType> FIRST = new HashSet<>(Arrays.asList(
			LexicalType.NAME,
			LexicalType.INTVAL,
			LexicalType.DOUBLEVAL,
			LexicalType.LITERAL
			));	
	public static boolean isMatch(LexicalType type) {
		return FIRST.contains(type);
	}

	private ConstNode(Environment env) throws Exception {
		super.env = env;
		switch (env.getInput().peek(1).getType()) {
		case NAME:

		case INTVAL:
			type = NodeType.INT_CONSTANT;
		case DOUBLEVAL:
			type = NodeType.DOUBLE_CONSTANT;
		case LITERAL:
			type = NodeType.STRING_CONSTANT;
		default:
			throw new Exception("ConstNodeのgetHandlerのError");
		}
	}

	public static Node getHandler(Environment env) throws Exception {
		return new ConstNode(env);
	}

	public void parse() throws Exception {
		env.getInput().get().getValue();
	}

	public String toString() {
		return value.toString();
	}

}
