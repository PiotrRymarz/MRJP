package ASTNodes;

import interfaces.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ExpMul implements ASTNode {
    private List<ASTNode> args;

    public ExpMul(ASTNode var1, ASTNode var2) {
        args = new ArrayList<>();
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

        ExpMul expMul = (ExpMul) o;

        return args != null ? args.equals(expMul.args) : expMul.args == null;

    }

    @Override
    public int hashCode() {
        return args != null ? args.hashCode() : 0;
    }
}
