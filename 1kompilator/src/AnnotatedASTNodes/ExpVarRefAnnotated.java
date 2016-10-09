package AnnotatedASTNodes;

import ASTNodes.ExpVarRef;
import interfaces.ASTNode;
import interfaces.AnnotatedASTNode;

public class ExpVarRefAnnotated extends ExpVarRef implements AnnotatedASTNode {

    public ExpVarRefAnnotated(String variable) {
        super(variable);
    }

    @Override
    public Integer getHeight() {
        return 1;
    }
}
