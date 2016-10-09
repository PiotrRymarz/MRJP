package AnnotatedASTNodes;

import ASTNodes.StmtExp;
import interfaces.ASTNode;
import interfaces.AnnotatedASTNode;

public class StmtExpAnnotated extends StmtExp implements AnnotatedASTNode {

    public StmtExpAnnotated(AnnotatedASTNode exp) {
        super(exp);
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
