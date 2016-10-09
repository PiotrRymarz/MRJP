package excercises1;

import ASTNodes.*;
import AnnotatedASTNodes.*;
import Lexems.*;
import SemanticTree.AnnotatedAbstractSyntaxTree;
import SyntaxTree.AbstractSyntaxTree;
import SyntaxTree.SymbolicTable;
import SyntaxTree.SyntaxParsingResult;
import interfaces.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertTrue;

public class SemanticAnalyzer1Test {
    private SemanticAnalyzer semanticAnalyzer;
    private AbstractSyntaxTree abstractTree;
    private SymbolicTable symbolicTable;
    private List<ASTNode> stmts;

    private AnnotatedAST expectedResult;
    private List<AnnotatedASTNode> annotatedStmts;
    private SyntaxParsingResult syntaxParsingResult;

    @BeforeClass
    public void prepare() {
        semanticAnalyzer = new SemanticAnalyzer1();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        stmts = new ArrayList<>();
        symbolicTable = new SymbolicTable();
        annotatedStmts = new ArrayList<>();
    }

    // SyntaxTree for 1 + 2 * 7
    @Test
    public void checkAnnotatedAST() {
        annotatedStmts.add(
            new StmtExpAnnotated(
                    new ExpAddAnnotated(
                            new ExpConstAnnotated(1),
                            new ExpMulAnnotated(
                                    new ExpConstAnnotated(2),
                                    new ExpConstAnnotated(7)
                            )
                    )
            )
        );
        expectedResult = new AnnotatedAbstractSyntaxTree(annotatedStmts);

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
        abstractTree = new AbstractSyntaxTree(stmts);

        syntaxParsingResult = new SyntaxParsingResult(abstractTree, symbolicTable);

        AnnotatedAbstractSyntaxTree result = semanticAnalyzer.getAnnotatedAST(syntaxParsingResult);
        assertTrue(result.equals(expectedResult));
        assertTrue(result.getHeight() == 4);

    }

    /**
     * SyntaxTree for
     *  a = 1
     *  a + 2 * 7
     */
    @Test
    public void checkAnnotatedAST2() {
        annotatedStmts.add(
            new StmtAssAnnotated(
                new ExpVarRefAnnotated("a"),
                new ExpConstAnnotated(1)
            )
        );
        annotatedStmts.add(
            new StmtExpAnnotated(
                new ExpAddAnnotated(
                    new ExpVarRefAnnotated("a"),
                    new ExpMulAnnotated(
                        new ExpConstAnnotated(2),
                        new ExpConstAnnotated(7)
                    )
                )
            )
        );
        expectedResult = new AnnotatedAbstractSyntaxTree(annotatedStmts);

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
        abstractTree = new AbstractSyntaxTree(stmts);
        symbolicTable.insertKey("a", 0);


        syntaxParsingResult = new SyntaxParsingResult(abstractTree, symbolicTable);

        AnnotatedAbstractSyntaxTree result = semanticAnalyzer.getAnnotatedAST(syntaxParsingResult);
        assertTrue(result.equals(expectedResult));
        assertTrue(result.getHeight() == 4);
    }

    //a+2
    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenArgumentNotInitialized() {
        stmts.add(
            new StmtExp(
                new ExpAdd(
                    new ExpVarRef("a"),
                    new ExpConst(2)
                )
            )
        );
        abstractTree = new AbstractSyntaxTree(stmts);
        symbolicTable.insertKey("a", 0);

        syntaxParsingResult = new SyntaxParsingResult(abstractTree, symbolicTable);

        AnnotatedAbstractSyntaxTree result = semanticAnalyzer.getAnnotatedAST(syntaxParsingResult);
    }

    //a+2
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowInvalidInputExceptionWhenVariableUpsentInTable() {
        stmts.add(
                new StmtExp(
                        new ExpAdd(
                                new ExpVarRef("a"),
                                new ExpConst(2)
                        )
                )
        );
        abstractTree = new AbstractSyntaxTree(stmts);

        syntaxParsingResult = new SyntaxParsingResult(abstractTree, symbolicTable);

        AnnotatedAbstractSyntaxTree result = semanticAnalyzer.getAnnotatedAST(syntaxParsingResult);
    }
}