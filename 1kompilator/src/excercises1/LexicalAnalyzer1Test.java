package excercises1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.remote.SuiteDispatcher;

import static org.testng.Assert.*;

public class LexicalAnalyzer1Test {
    private LexicalAnalyzer1 lexicalAnalyzer1;

    private static final String test1 = "1 - 7 * 0";
    private static final String result1 = "[LConst 1, LMinus, LConst 7, LStar, LConst 0, LEOL, LEOF]";

    private static final String test2 = test1 + System.lineSeparator() + test1;
    private static final String result2 = "[LConst 1, LMinus, LConst 7, LStar, LConst 0, LEOL,"
                                            + " LConst 1, LMinus, LConst 7, LStar, LConst 0, LEOL, LEOF]";

    private static final String test3 = "1 + 2 * 3 - 4 / 5 % 6";
    private static final String result3 = "[LConst 1, LPlus, LConst 2, LStar, LConst 3," +
                                            " LMinus, LConst 4, LSlash, LConst 5, LPercent, LConst 6, LEOL, LEOF]";
    @BeforeClass
    public void prepareAnalyzer () {
        lexicalAnalyzer1 = new LexicalAnalyzer1();
    }

    @Test
    public void testOneLine() throws Exception {
        assertEquals(lexicalAnalyzer1.tokenize(test1).toString(), result1);
    }

    @Test
    public void testMultiLines() throws Exception {
        assertEquals(lexicalAnalyzer1.tokenize(test2).toString(), result2);
    }

    @Test
    public void testAll() throws Exception {
        assertEquals(lexicalAnalyzer1.tokenize(test3).toString(), result3);
    }
}