package AnnotatedASTNodes;

import ASTNodes.StmtProgram;
import SyntaxTree.SymbolicTable;
import interfaces.ASTNode;
import interfaces.AnnotatedASTNode;

import java.util.ArrayList;
import java.util.List;

public class StmtProgramAnnotated extends StmtProgram implements AnnotatedASTNode {


    public StmtProgramAnnotated(List<AnnotatedASTNode> statements) {
        super();
        this.statements = new ArrayList<>(statements);
    }

    @Override
    public Integer getHeight() {
        Integer maxChildHeight = 0;
        Integer height;

        for (ASTNode child : getChildren()) {
            AnnotatedASTNode childCasted = (AnnotatedASTNode) child;

            height = childCasted.getHeight();
            if ( height > maxChildHeight) {
                maxChildHeight = height;
            }
        }

        return maxChildHeight;
    }

    @Override
    public Integer getValue(SymbolicTable symbolicTable, Integer[] currentValues) {
        Integer value = Integer.MIN_VALUE;

        for (ASTNode stmt: getChildren()) {
            AnnotatedASTNode annotatedStmt = (AnnotatedASTNode) stmt;

            value = annotatedStmt.getValue(symbolicTable, currentValues);
        }

        return value;
    }

}
