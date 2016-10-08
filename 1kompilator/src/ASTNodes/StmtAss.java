package ASTNodes;

import java.util.ArrayList;
import java.util.List;

import interfaces.ASTNode;

public class StmtAss implements ASTNode {
	
	private List<ASTNode> params;
	
	public StmtAss(ASTNode var, ASTNode exp) {
		params = new ArrayList<>();
		params.add(var);
		params.add(exp);
	}

    public StmtAss() {

    }

	public List<ASTNode> getChildren() {
		return params;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StmtAss stmtAss = (StmtAss) o;

		return params != null ? params.equals(stmtAss.params) : stmtAss.params == null;

	}

	@Override
	public int hashCode() {
		return params != null ? params.hashCode() : 0;
	}
}
