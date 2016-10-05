package ASTNodes;

import java.util.List;

import interfaces.ASTNode;

public class Program implements ASTNode {
	private List<ASTNode> statements;
	
	public Program(List<ASTNode> statements) {
		this.statements = statements;
	}

	public List<ASTNode> getChildren() {
		return statements;
	}
}
