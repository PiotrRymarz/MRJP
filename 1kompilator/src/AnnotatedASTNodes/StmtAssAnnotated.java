package AnnotatedASTNodes;

import ASTNodes.StmtAss;
import SyntaxTree.SymbolicTable;
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

    @Override
    public Integer getValue(SymbolicTable symbolicTable, Integer[] currentValues) {
        ExpVarRefAnnotated variable = (ExpVarRefAnnotated) getChildren().get(0);
        AnnotatedASTNode expression = (AnnotatedASTNode) getChildren().get(1);

        Integer value = expression.getValue(symbolicTable, currentValues);
        variable.setValue(value, symbolicTable, currentValues);

        return value;    
    }
}
