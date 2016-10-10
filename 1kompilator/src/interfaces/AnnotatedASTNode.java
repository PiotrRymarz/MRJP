package interfaces;

import SyntaxTree.SymbolicTable;

public interface AnnotatedASTNode extends ASTNode {
    Integer getHeight();
    Integer getValue(SymbolicTable symbolicTable, Integer[] currentValues);
}
