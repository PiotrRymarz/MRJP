package ASTNodes;

import java.util.LinkedList;
import java.util.List;

import interfaces.ASTNode;

public class ExpAdd implements ASTNode {
	private List<ASTNode> args;
	
	public ExpAdd(ASTNode var1, ASTNode var2) {
		args = new LinkedList();
		args.add(var1);
		args.add(var2);
	}

	public List<ASTNode> getChildren() {
		return args;
	}

}
