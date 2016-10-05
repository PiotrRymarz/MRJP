package interfaces;

public interface Frontend {
    LexicalAnalyzer getLexicalAnalyzer();

    SemanticAnalyzer getSemanticAnalyzer();

    SyntaxAnalyzer getSyntaxAnalyzer();
}
