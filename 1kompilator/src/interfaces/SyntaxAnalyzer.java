package interfaces;

import java.util.List;

public interface SyntaxAnalyzer {
    AST getAST(List<Lexem> lexems);
}
