package excercises1;

import ASTNodes.*;
import Lexems.*;
import SyntaxTree.AbstractSyntaxTree;
import SyntaxTree.SyntaxParsingResult;
import interfaces.ASTNode;
import interfaces.Lexem;
import interfaces.SyntaxAnalyzer;
import org.testng.annotations.BeforeClass;

import interfaces.AST;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class SyntaxAnalyzer1Test {
    private SyntaxAnalyzer syntaxAnalyzer;
    private AST expectedResult;
    private List<ASTNode> stmts;

    private List<Lexem> lexems;
    private SyntaxParsingResult syntaxParsingResult;


    @BeforeClass
    public void prepare() {
        lexems = new ArrayList<>();
        syntaxAnalyzer = new SyntaxAnalyzer1();
        stmts = new ArrayList<>();
    }

    @BeforeMethod
    public void reset() {
        stmts.clear();
        lexems.clear();
    }

    // SyntaxTree for 1 + 2 * 7
    @Test
    public void checkAST() {
        stmts.add(
                new StmtExp(
                    new ExpAdd(
                            new ExpConst(1),
                            new ExpMul(
                                    new ExpConst(2),
                                    new ExpConst(7)
                            )
                    )
                )
        );
        expectedResult = new AbstractSyntaxTree(stmts);

        lexems.add(new LConst("1"));
        lexems.add(new LPlus());
        lexems.add(new LConst("2"));
        lexems.add(new LStar());
        lexems.add(new LConst("7"));
        lexems.add(new LEOL());
        lexems.add(new LEOF());


        syntaxParsingResult = syntaxAnalyzer.getParsingResult(lexems);
        AST tree = syntaxParsingResult.getAST();
        assertTrue(tree.equals(expectedResult));
        assertTrue(syntaxParsingResult.getSymbolicTable().size() == 0);

    }

    // SyntaxTree for 1 + 2 * 7 + 5
    @Test
    public void checkAST2() {
        stmts.add(
                new StmtExp(
                    new ExpAdd(
                            new ExpConst(1),
                            new ExpAdd(
                                new ExpMul(
                                        new ExpConst(2),
                                        new ExpConst(7)
                                ),
                                new ExpConst(5)
                            )
                    )
                )
        );
        expectedResult = new AbstractSyntaxTree(stmts);

        lexems.add(new LConst("1"));
        lexems.add(new LPlus());
        lexems.add(new LConst("2"));
        lexems.add(new LStar());
        lexems.add(new LConst("7"));
        lexems.add(new LPlus());
        lexems.add(new LConst("5"));
        lexems.add(new LEOL());
        lexems.add(new LEOF());


        syntaxParsingResult = syntaxAnalyzer.getParsingResult(lexems);
        AST tree = syntaxParsingResult.getAST();
        assertTrue(tree.equals(expectedResult));
        assertTrue(syntaxParsingResult.getSymbolicTable().size() == 0);
    }

    // SyntaxTree for 1 - 2 / 7
    @Test
    public void checkAST3() {
        stmts.add(
                new StmtExp(
                    new ExpSub(
                            new ExpConst(1),
                            new ExpDiv(
                                    new ExpConst(2),
                                    new ExpConst(7)
                            )
                    )
                )
        );
        expectedResult = new AbstractSyntaxTree(stmts);

        lexems.add(new LConst("1"));
        lexems.add(new LMinus());
        lexems.add(new LConst("2"));
        lexems.add(new LSlash());
        lexems.add(new LConst("7"));
        lexems.add(new LEOL());
        lexems.add(new LEOF());


        syntaxParsingResult = syntaxAnalyzer.getParsingResult(lexems);
        AST tree = syntaxParsingResult.getAST();
        assertTrue(tree.equals(expectedResult));
        assertTrue(syntaxParsingResult.getSymbolicTable().size() == 0);

    }

    // SyntaxTree for 1 + 2 * 3 - 4 / 5
    @Test
    public void checkAST4() {
        stmts.add(
                new StmtExp(
                    new ExpAdd(
                        new ExpConst(1),
                        new ExpSub(
                            new ExpMul(
                                new ExpConst(2),
                                new ExpConst(3)
                            ),
                            new ExpDiv(
                                new ExpConst(4),
                                new ExpConst(5)
                            )
                        )
                    )
                )
        );
        expectedResult = new AbstractSyntaxTree(stmts);

        lexems.add(new LConst("1"));
        lexems.add(new LPlus());
        lexems.add(new LConst("2"));
        lexems.add(new LStar());
        lexems.add(new LConst("3"));
        lexems.add(new LMinus());
        lexems.add(new LConst("4"));
        lexems.add(new LSlash());
        lexems.add(new LConst("5"));
        lexems.add(new LEOL());
        lexems.add(new LEOF());


        syntaxParsingResult = syntaxAnalyzer.getParsingResult(lexems);
        AST tree = syntaxParsingResult.getAST();
        assertTrue(tree.equals(expectedResult));
        assertTrue(syntaxParsingResult.getSymbolicTable().size() == 0);
    }

    /**
     *     SyntaxTree for:
     *     1 + 2 * 3 - 4 / 5;
     *     1 + 2 * 3 - 4 / 5
     */
    @Test
    public void checkAST5() {
        final ASTNode example_statement =
                new StmtExp(
                    new ExpAdd(
                        new ExpConst(1),
                        new ExpSub(
                                new ExpMul(
                                        new ExpConst(2),
                                        new ExpConst(3)
                                ),
                                new ExpDiv(
                                        new ExpConst(4),
                                        new ExpConst(5)
                                )
                        )
                    )
            );

        stmts.add(example_statement);
        stmts.add(example_statement);

        expectedResult = new AbstractSyntaxTree(stmts);

        lexems.add(new LConst("1"));
        lexems.add(new LPlus());
        lexems.add(new LConst("2"));
        lexems.add(new LStar());
        lexems.add(new LConst("3"));
        lexems.add(new LMinus());
        lexems.add(new LConst("4"));
        lexems.add(new LSlash());
        lexems.add(new LConst("5"));
        lexems.add(new LEOL());
        lexems.add(new LConst("1"));
        lexems.add(new LPlus());
        lexems.add(new LConst("2"));
        lexems.add(new LStar());
        lexems.add(new LConst("3"));
        lexems.add(new LMinus());
        lexems.add(new LConst("4"));
        lexems.add(new LSlash());
        lexems.add(new LConst("5"));
        lexems.add(new LEOL());
        lexems.add(new LEOF());


        syntaxParsingResult = syntaxAnalyzer.getParsingResult(lexems);
        AST tree = syntaxParsingResult.getAST();
        assertTrue(tree.equals(expectedResult));
        assertTrue(syntaxParsingResult.getSymbolicTable().size() == 0);
    }

    /**
     * SyntaxTree for
     *  a = 1
     *  a + 2 * 7
     */

    @Test
    public void checkAST6() {
        stmts.add(
                new StmtAss(
                        new ExpVarRef("a"),
                        new ExpConst(1)
                )
        );
        stmts.add(
                new StmtExp(
                        new ExpAdd(
                                new ExpVarRef("a"),
                                new ExpMul(
                                        new ExpConst(2),
                                        new ExpConst(7)
                                )
                        )
                )
        );
        expectedResult = new AbstractSyntaxTree(stmts);

        lexems.add(new LIdent("a"));
        lexems.add(new LEq());
        lexems.add(new LConst("1"));
        lexems.add(new LEOL());
        lexems.add(new LIdent("a"));
        lexems.add(new LPlus());
        lexems.add(new LConst("2"));
        lexems.add(new LStar());
        lexems.add(new LConst("7"));
        lexems.add(new LEOL());
        lexems.add(new LEOF());


        syntaxParsingResult = syntaxAnalyzer.getParsingResult(lexems);
        AST tree = syntaxParsingResult.getAST();
        assertTrue(tree.equals(expectedResult));
        assertTrue(syntaxParsingResult.getSymbolicTable().size() == 1);

    }
}