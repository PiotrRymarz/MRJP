package excercises1;

import java.util.LinkedList;
import java.util.List;

import Lexems.*;
import interfaces.Lexem;
import interfaces.LexicalAnalyzer;


class LexicalAnalyzer1 implements LexicalAnalyzer {

	@Override
	public List<Lexem> tokenize(String... lines) {
		List<Lexem> lexems = new LinkedList<>();
		String[] words;
		
		for(String line : lines) {
			words = line.split(" ");
			for (String word : words) {
				if (isNumber(word)) {
					lexems.add(new LConst(word));
				} else if (word.equals("=")) {
					lexems.add(new LEq());
				} else if (word.equals("+")) {
					lexems.add(new LPlus());
				} else if (word.equals("-")) {
					lexems.add(new LMinus());
				} else if (word.equals("*")) {
					lexems.add(new LStar());
				} else if (word.equals("/")) {
					lexems.add(new LSlash());
				} else if (word.equals("%")) {
					lexems.add(new LPercent());
				} else {
					lexems.add(new LIdent(word));
				}
			}
			lexems.add(new LEOL());
		}
		lexems.add(new LEOF());
		return lexems;
	}

	private boolean isNumber(String word) {
		return word.matches("^-?\\d+$");
	}
}
