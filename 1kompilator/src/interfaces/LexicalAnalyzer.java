package interfaces;

import java.util.List;

public interface LexicalAnalyzer {
    List<Lexem> tokenize(String lines);
}
