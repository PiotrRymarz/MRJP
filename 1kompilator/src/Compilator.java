import java.util.List;

import interfaces.Lexem;
import static org.junit.Assert.*;

public class Compilator {
	
	public static String program = "1 7 - 0 *";
	public static String result = "[LConst [value=1], LConst [value=7], LMinus, LConst [value=0], LStar]";
	
	private static LexicalAnalyzer lexicalAnalyzer;
	
	public static void main(String[] args) {
		lexicalAnalyzer = new LexicalAnalyzer();
		
		List<Lexem> lexems = lexicalAnalyzer.tokenize(program);
		
		Assert.assertEqual(lexems, result);
		
	}
	
	

}
