package SyntaxTree;

import ASTNodes.StmtProgram;
import interfaces.AST;
import interfaces.ASTNode;

import java.util.Iterator;
import java.util.List;

public class AbstractSyntaxTree implements AST {
    private ASTNode root;

    public AbstractSyntaxTree(List<ASTNode> stmts) {
        root = new StmtProgram(stmts);
    }

    public ASTNode getProgram() {
        return root;
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
