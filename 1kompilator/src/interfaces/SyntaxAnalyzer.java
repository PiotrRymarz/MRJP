package interfaces;

import SyntaxTree.SyntaxParsingResult;

import java.util.List;

public interface SyntaxAnalyzer {
    SyntaxParsingResult getParsingResult(List<Lexem> lexems);
}
