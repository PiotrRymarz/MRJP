package excercises1;

import AST.AbstractSyntaxTree;
import ASTNodes.ExpAdd;
import ASTNodes.ExpConst;
import ASTNodes.ExpMul;
import Lexems.LConst;
import Lexems.LPlus;
import Lexems.LStar;
import interfaces.ASTNode;
import interfaces.Lexem;
import interfaces.SyntaxAnalyzer;
import org.testng.annotations.BeforeClass;

import interfaces.AST;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class SyntaxAnalyzer1Test {
    private SyntaxAnalyzer syntaxAnalyzer;
    private AST expectedResult;
    private List<ASTNode> stmts;

    private List<Lexem> lexems;

    // AST for 1 + 2 * 7
    @BeforeClass
    public void prepare() {
        lexems = new ArrayList<>();
        syntaxAnalyzer = new SyntaxAnalyzer1();
        stmts = new ArrayList<>();
    }

    @Test
    public void checkAST() {
        stmts.add(
                new ExpAdd(
                        new ExpConst(1),
                        new ExpMul(
                                new ExpConst(2),
                                new ExpConst(7)
                        )
                )
        );
        expectedResult = new AbstractSyntaxTree(stmts);

        lexems.add(new LConst("1"));
        lexems.add(new LPlus());
        lexems.add(new LConst("2"));
        lexems.add(new LStar());
        lexems.add(new LConst("7"));


        assertTrue(syntaxAnalyzer.getAST(lexems).equals(expectedResult));

    }




}