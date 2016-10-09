package interfaces;

import SemanticTree.AnnotatedAbstractSyntaxTree;
import SyntaxTree.SyntaxParsingResult;

public interface SemanticAnalyzer {
    AnnotatedAbstractSyntaxTree getAnnotatedAST(SyntaxParsingResult syntaxParsingResult);
}
