package interfaces;

import SemanticTree.AnnotatedAbstractSyntaxTree;
import SemanticTree.SemanticParsingResult;
import SyntaxTree.SyntaxParsingResult;

public interface SemanticAnalyzer {
    SemanticParsingResult getSemanticResult(SyntaxParsingResult syntaxParsingResult);
}
