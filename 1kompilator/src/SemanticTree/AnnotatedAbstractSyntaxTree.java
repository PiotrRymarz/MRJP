package SemanticTree;

import AnnotatedASTNodes.StmtProgramAnnotated;
import interfaces.AnnotatedAST;
import interfaces.AnnotatedASTNode;

import java.util.List;

public class AnnotatedAbstractSyntaxTree implements AnnotatedAST {
    private final StmtProgramAnnotated root;

    public AnnotatedAbstractSyntaxTree(List<AnnotatedASTNode> stmts) {
        root = new StmtProgramAnnotated(stmts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnnotatedAbstractSyntaxTree that = (AnnotatedAbstractSyntaxTree) o;

        return root != null ? root.equals(that.root) : that.root == null;

    }

    @Override
    public int hashCode() {
        return root != null ? root.hashCode() : 0;
    }

    @Override
    public Integer getHeight() {
        return root.getHeight();
    }
}
