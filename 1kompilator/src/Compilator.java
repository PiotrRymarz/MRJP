import java.util.List;

import SemanticTree.SemanticParsingResult;
import SyntaxTree.SyntaxParsingResult;
import excercises1.Frontend1;
import interfaces.*;

public class Compilator {
	
	private static LexicalAnalyzer lexicalAnalyzer;
	private static SemanticAnalyzer semanticAnalyzer;
	private static SyntaxAnalyzer syntaxAnalyzer;

	private static Frontend frontend;
	private static final String endLine = System.lineSeparator();
	private static final String program1 = "1 + 2 + 3";
	private static final String program2 =
			"a = 1 + 2 * 3 + 4" + endLine
			+ "b = 10 - 7 * 1 + 2" + endLine
			+ "a + b";

	private static final String program3 =
			"a = 5 % 3" + endLine
			+ "b = 7 % 4" + endLine
			+ "a + b";

	public static void main(String[] args) {

		frontend = new Frontend1();

		lexicalAnalyzer = frontend.getLexicalAnalyzer();
		syntaxAnalyzer = frontend.getSyntaxAnalyzer();
		semanticAnalyzer = frontend.getSemanticAnalyzer();

		List<Lexem> lexems = lexicalAnalyzer.tokenize(program3);
		SyntaxParsingResult syntaxParsingResult = syntaxAnalyzer.getParsingResult(lexems);
		SemanticParsingResult semanticParsingResult = semanticAnalyzer.getSemanticResult(syntaxParsingResult);

		compile(semanticParsingResult);
	}

	private static void compile(SemanticParsingResult semanticParsingResult) {
		System.out.println(semanticParsingResult.getValue());
	}
}
