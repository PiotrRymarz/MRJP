package AnnotatedASTNodes;

import ASTNodes.ExpMul;
import SyntaxTree.SymbolicTable;
import interfaces.ASTNode;
import interfaces.AnnotatedAST;
import interfaces.AnnotatedASTNode;

public class ExpMulAnnotated extends ExpMul implements AnnotatedASTNode {

    private Integer value;

    public ExpMulAnnotated() {
    }

    public ExpMulAnnotated(AnnotatedASTNode var1, AnnotatedASTNode var2) {
        super(var1, var2);
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

    @Override
    public Integer getValue(SymbolicTable symbolicTable, Integer[] currentValues) {
        if (value == null) {
            AnnotatedASTNode first = (AnnotatedASTNode) getChildren().get(0);
            AnnotatedASTNode second = (AnnotatedASTNode) getChildren().get(1);

            value = first.getValue(symbolicTable, currentValues) * second.getValue(symbolicTable, currentValues);
        }

        return value;
    }
}
