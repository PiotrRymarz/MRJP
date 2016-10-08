package SyntaxTree;

public class SyntaxParsingResult {
    private AbstractSyntaxTree tree;
    private SymbolicTable symbolicTable;

    public SyntaxParsingResult(AbstractSyntaxTree tree, SymbolicTable symbolicTable) {
        this.tree = tree;
        this.symbolicTable = symbolicTable;
    }

    public AbstractSyntaxTree getAST() {
        return tree;
    }

    public SymbolicTable getSymbolicTable() {
        return symbolicTable;
    }
}
