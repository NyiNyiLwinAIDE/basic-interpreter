package newlang4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import newlang3.*;

public class SubstNode extends Node {
	String leftVar;
	Node expr;
	
	private final static Set<LexicalType> FIRST=new HashSet<>(Arrays.asList(
			LexicalType.NAME
		));

	public static boolean isMatch(LexicalType type) {
		return FIRST.contains(type);
	}
	
	private SubstNode(Environment env) {
		super.env = env;
		type = NodeType.ASSIGN_STMT;
	}
	
	// 次に出てくる字句をparseできるnodeをひとつ返す
	public static Node getHandler(Environment env) throws Exception {
		return new SubstNode(env);
	}
	
	public void parse() throws Exception {
		if(env.getInput().peek(1).getType() == LexicalType.NAME){
			 leftVar= env.getInput().get().getValue().getSValue();
		} else {
			throw new Exception("SubstNodeのNAMElのparseエラー");
		}
		if(env.getInput().get().getType() != LexicalType.EQ) throw new Exception("SubstNodeのEQのparseエラー");
		if(ExprNode.isMatch(env.getInput().peek(1).getType())) {
			expr = ExprNode.getHandler(env);
			expr.parse();
			
		} else {
			throw new Exception("SubstNodeのexprのparseエラー");
		}
	} 
	
	public String toString() {
		return "SUBST:" + expr.toString() + "=" + leftVar;
	}

	public Value getValue() throws Exception{
		env.getVariable(leftVar).setValue(expr.getValue());
		return null;
	}

}
