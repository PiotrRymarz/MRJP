package excercises1;

import interfaces.Frontend;
import interfaces.LexicalAnalyzer;
import interfaces.SemanticAnalyzer;
import interfaces.SyntaxAnalyzer;

public class Frontend1 implements Frontend {
    @Override
    public LexicalAnalyzer getLexicalAnalyzer() {
        return new LexicalAnalyzer1();
    }

    @Override
    public SemanticAnalyzer getSemanticAnalyzer() {
        return new SemanticAnalyzer1();
    }

    @Override
    public SyntaxAnalyzer getSyntaxAnalyzer() {
        return new SyntaxAnalyzer1();
    }
}
