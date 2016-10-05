package ASTNodes;

import java.util.LinkedList;
import java.util.List;

import interfaces.ASTNode;

public class StmtExp implements ASTNode {
	
	private List<ASTNode> exp;
	
	public StmtExp(ASTNode exp) {
		this.exp = new LinkedList();
		this.exp.add(exp);
	}

	public List<ASTNode> getChildren() {
		return exp;
	}

}
