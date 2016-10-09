package excercises1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.remote.SuiteDispatcher;

import static org.testng.Assert.*;

public class LexicalAnalyzer1Test {
    private LexicalAnalyzer1 lexicalAnalyzer1;

    private static final String test1 = "1 - 7 * 0";
    private static final String result1 = "[LConst 1, LMinus, LConst 7, LStar, LConst 0, LEOL, LEOF]";

    private static final String result2 = "[LConst 1, LMinus, LConst 7, LStar, LConst 0, LEOL,"
                                            + " LConst 1, LMinus, LConst 7, LStar, LConst 0, LEOL, LEOF]";

    @BeforeClass
    public void prepareAnalyzer () {
        lexicalAnalyzer1 = new LexicalAnalyzer1();
    }

    @Test
    public void testOneLine() throws Exception {
        assertEquals(lexicalAnalyzer1.tokenize(test1).toString(), result1);
    }

    @Test void testMultiLines() throws Exception {
        assertEquals(lexicalAnalyzer1.tokenize(test1 + System.lineSeparator() + test1).toString(), result2);

    }

}