import java.util.List;

import SyntaxTree.SyntaxParsingResult;
import excercises1.Frontend1;
import interfaces.*;

public class Compilator {
	
	private static LexicalAnalyzer lexicalAnalyzer;
	private static SemanticAnalyzer semanticAnalyzer;
	private static SyntaxAnalyzer syntaxAnalyzer;

	private static Frontend frontend;
	private static final String endLine = System.lineSeparator();
	private static final String program =
			"a = 5 / 5" + endLine
			+ "b = 1 - 7 * 0 + 2" + endLine
			+ "a + b";

	public static void main(String[] args) {

		frontend = new Frontend1();

		lexicalAnalyzer = frontend.getLexicalAnalyzer();
		syntaxAnalyzer = frontend.getSyntaxAnalyzer();
		semanticAnalyzer = frontend.getSemanticAnalyzer();

		List<Lexem> lexems = lexicalAnalyzer.tokenize(program);
		SyntaxParsingResult syntaxParsingResult = syntaxAnalyzer.getParsingResult(lexems);
		semanticAnalyzer.getAnnotatedAST(syntaxParsingResult);
	}
}
