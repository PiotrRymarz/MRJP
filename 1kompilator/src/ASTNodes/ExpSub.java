package ASTNodes;

import java.util.ArrayList;
import java.util.List;

import interfaces.ASTNode;

public class ExpSub implements ASTNode {
	private List<ASTNode> args;
	
	public ExpSub(ASTNode var1, ASTNode var2) {
		args = new ArrayList<>();
		args.add(var1);
		args.add(var2);
	}

	public ExpSub() {

	}

	public List<ASTNode> getChildren() {
		return args;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ExpSub expSub = (ExpSub) o;

		return args != null ? args.equals(expSub.args) : expSub.args == null;

	}

	@Override
	public int hashCode() {
		return args != null ? args.hashCode() : 0;
	}
}
