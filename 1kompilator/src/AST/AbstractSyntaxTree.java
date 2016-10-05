package AST;

import ASTNodes.Program;
import interfaces.AST;
import interfaces.ASTNode;

import java.util.Iterator;
import java.util.List;

public class AbstractSyntaxTree implements AST, Iterable<ASTNode> {
    private ASTNode root;

    public AbstractSyntaxTree(List<ASTNode> stmts) {
        root = new Program(stmts);
    }

    @Override
    public Iterator<ASTNode> iterator() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractSyntaxTree astNodes = (AbstractSyntaxTree) o;

        return root != null ? root.equals(astNodes.root) : astNodes.root == null;

    }

    @Override
    public int hashCode() {
        return root != null ? root.hashCode() : 0;
    }
}
