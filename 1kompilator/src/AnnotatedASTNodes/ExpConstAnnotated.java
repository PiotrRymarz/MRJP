package AnnotatedASTNodes;


import ASTNodes.ExpConst;
import interfaces.AnnotatedASTNode;

public class ExpConstAnnotated extends ExpConst implements AnnotatedASTNode {

    public ExpConstAnnotated(Integer value) {
        super(value);
    }

    @Override
    public Integer getHeight() {
        return 1;
    }
}
