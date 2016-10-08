package ASTNodes;

import java.util.ArrayList;
import java.util.List;

import interfaces.ASTNode;

public class StmtExp implements ASTNode {
	
	private List<ASTNode> exp;
	
	public StmtExp(ASTNode exp) {
		this.exp = new ArrayList<>();
		this.exp.add(exp);
	}

	public List<ASTNode> getChildren() {
		return exp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StmtExp stmtExp = (StmtExp) o;

		return exp != null ? exp.equals(stmtExp.exp) : stmtExp.exp == null;

	}

	@Override
	public int hashCode() {
		return exp != null ? exp.hashCode() : 0;
	}
}
