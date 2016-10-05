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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Program program = (Program) o;

		return statements != null ? statements.equals(program.statements) : program.statements == null;

	}

	@Override
	public int hashCode() {
		return statements != null ? statements.hashCode() : 0;
	}
}
