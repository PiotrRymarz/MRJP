package SemanticTree;

import SyntaxTree.SymbolicTable;

public class SemanticParsingResult {
        private AnnotatedAbstractSyntaxTree tree;
        private SymbolicTable symbolicTable;

        public SemanticParsingResult(AnnotatedAbstractSyntaxTree tree, SymbolicTable symbolicTable) {
            this.tree = tree;
            this.symbolicTable = symbolicTable;
        }

        public AnnotatedAbstractSyntaxTree getAnnotatedAST() {
            return tree;
        }

        public SymbolicTable getSymbolicTable() {
            return symbolicTable;
        }

        public Integer getValue() {
            return tree.getValue(symbolicTable);
        }

}
