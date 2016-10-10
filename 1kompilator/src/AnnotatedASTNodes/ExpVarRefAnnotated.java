package AnnotatedASTNodes;

import ASTNodes.ExpVarRef;
import SyntaxTree.SymbolicTable;
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

    @Override
    public Integer getValue(SymbolicTable symbolicTable, Integer[] currentValues) {
        return currentValues[symbolicTable.getValue(getVariableName())];
    }

    public void setValue(Integer value, SymbolicTable symbolicTable, Integer[] currentValues) {
        currentValues[symbolicTable.getValue(getVariableName())] = value;
    }
}
