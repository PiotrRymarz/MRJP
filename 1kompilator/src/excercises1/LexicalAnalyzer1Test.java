package excercises1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LexicalAnalyzer1Test {
    private LexicalAnalyzer1 lexicalAnalyzer1;

    private static final String test1 = "1 7 - 0 *";
    private static final String result1 = "[LConst 1, LConst 7, LMinus, LConst 0, LStar, LEOL, LEOF]";

    private static final String result2 = "[LConst 1, LConst 7, LMinus, LConst 0, LStar, LEOL,"
                                            + " LConst 1, LConst 7, LMinus, LConst 0, LStar, LEOL, LEOF]";

    @BeforeClass
    public void prepareAnalyzer () {
        lexicalAnalyzer1 = new LexicalAnalyzer1();
    }

    @Test
    public void testOneLine() throws Exception {
        assertEquals(lexicalAnalyzer1.tokenize(test1).toString(), result1);
    }

    @Test void testMultiLines() throws Exception {
        assertEquals(lexicalAnalyzer1.tokenize(test1, test1).toString(), result2);

    }

}