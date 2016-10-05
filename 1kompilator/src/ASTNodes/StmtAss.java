package ASTNodes;

import java.util.LinkedList;
import java.util.List;

import interfaces.ASTNode;

public class StmtAss implements ASTNode {
	
	private List<ASTNode> params;
	
	public StmtAss(ASTNode var, ASTNode exp) {
		params = new LinkedList();
		params.add(var);
		params.add(exp);
	}

	public List<ASTNode> getChildren() {
		return params;
	}

}
