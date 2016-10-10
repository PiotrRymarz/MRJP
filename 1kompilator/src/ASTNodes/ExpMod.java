package ASTNodes;

import interfaces.ASTNode;

import java.util.LinkedList;
import java.util.List;

public class ExpMod implements ASTNode {
    private List<ASTNode> args;

    public ExpMod(ASTNode var1, ASTNode var2) {
        args = new LinkedList<>();
        args.add(var1);
        args.add(var2);
    }

    public ExpMod() {

    }

    public List<ASTNode> getChildren() {
        return args;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpMod expMod = (ExpMod) o;

        return args != null ? args.equals(expMod.args) : expMod.args == null;

    }

    @Override
    public int hashCode() {
        return args != null ? args.hashCode() : 0;
    }
}
