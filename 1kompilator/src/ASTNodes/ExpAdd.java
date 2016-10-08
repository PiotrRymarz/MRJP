package ASTNodes;

import java.util.ArrayList;
import java.util.List;

import interfaces.ASTNode;

public class ExpAdd implements ASTNode {
	private List<ASTNode> args;

	public ExpAdd() {
	}

	public ExpAdd(ASTNode var1, ASTNode var2) {
		args = new ArrayList<>();
		setChildren(var1, var2);
	}

	public void setChildren(ASTNode var1, ASTNode var2) {
		args.clear();
		args.add(var1);
		args.add(var2);
	}

	public List<ASTNode> getChildren() {
		return args;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ExpAdd expAdd = (ExpAdd) o;

		return args != null ? args.equals(expAdd.args) : expAdd.args == null;

	}

	@Override
	public int hashCode() {
		return args != null ? args.hashCode() : 0;
	}
}
