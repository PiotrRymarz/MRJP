package AnnotatedASTNodes;


import ASTNodes.ExpConst;
import SyntaxTree.SymbolicTable;
import interfaces.ASTNode;
import interfaces.AnnotatedASTNode;

public class ExpConstAnnotated extends ExpConst implements AnnotatedASTNode {
    private Integer value;

    public ExpConstAnnotated(Integer value) {
        super(value);
        this.value = value;
    }

    @Override
    public Integer getHeight() {
        return 1;
    }

    @Override
    public Integer getValue(SymbolicTable symbolicTable, Integer[] currentValues) {
        return value;
    }
}
