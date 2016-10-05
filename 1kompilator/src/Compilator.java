import java.util.List;

import excercises1.Frontend1;
import interfaces.*;

public class Compilator {
	
	private static LexicalAnalyzer lexicalAnalyzer;
	private static SemanticAnalyzer semanticAnalyzer;
	private static SyntaxAnalyzer syntaxAnalyzer;

	private static Frontend frontend;
	
	public static void main(String[] args) {
		frontend = new Frontend1();

		lexicalAnalyzer = frontend.getLexicalAnalyzer();
		semanticAnalyzer = frontend.getSemanticAnalyzer();
		syntaxAnalyzer = frontend.getSyntaxAnalyzer();

//		List<Lexem> lexems = lexicalAnalyzer.tokenize(program);

	}
	
	

}
