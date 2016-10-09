package AnnotatedASTNodes;

import ASTNodes.StmtAss;
import interfaces.ASTNode;
import interfaces.AnnotatedASTNode;

public class StmtAssAnnotated extends StmtAss implements AnnotatedASTNode {

    public StmtAssAnnotated(AnnotatedASTNode var, AnnotatedASTNode exp) {
        super(var, exp);
    }

    public StmtAssAnnotated() {
    }

    @Override
    public Integer getHeight() {
        Integer maxChildHeight = 0;
        Integer height;

        for (ASTNode child : getChildren()) {
            AnnotatedASTNode childCasted = (AnnotatedASTNode) child;

            height = childCasted.getHeight();
            if (childCasted.getHeight() > maxChildHeight) {
                maxChildHeight = height;
            }
        }

        return maxChildHeight + 1;
    }
}
