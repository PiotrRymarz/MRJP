package AnnotatedASTNodes;

import ASTNodes.ExpDiv;
import SyntaxTree.SymbolicTable;
import interfaces.ASTNode;
import interfaces.AnnotatedASTNode;
import sun.rmi.server.InactiveGroupException;

public class ExpDivAnnotated extends ExpDiv implements AnnotatedASTNode {

    private Integer value;

    public ExpDivAnnotated(AnnotatedASTNode var1, AnnotatedASTNode var2) {
        super(var1, var2);
    }

    public ExpDivAnnotated() {
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
            AnnotatedASTNode dividend = (AnnotatedASTNode) getChildren().get(0);
            AnnotatedASTNode divisor = (AnnotatedASTNode) getChildren().get(1);

            value = dividend.getValue(symbolicTable, currentValues) / divisor.getValue(symbolicTable, currentValues);
        }

        return value;
    }
}
