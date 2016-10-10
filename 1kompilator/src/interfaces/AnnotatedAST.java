package interfaces;

import SyntaxTree.SymbolicTable;

public interface AnnotatedAST {
    Integer getHeight();
    Integer getValue(SymbolicTable symbolicTable);
}
