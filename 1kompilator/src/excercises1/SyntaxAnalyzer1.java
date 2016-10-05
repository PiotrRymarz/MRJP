package excercises1;

import Lexems.LConst;
import interfaces.AST;
import interfaces.ASTNode;
import interfaces.Lexem;
import interfaces.SyntaxAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class SyntaxAnalyzer1 implements SyntaxAnalyzer {
    private AST tree;
    private List<ASTNode> stmts;
    private Stack<ASTNode> stack;

    @Override
    public AST getAST(List<Lexem> lexems) {
        stmts = new ArrayList<>();
        stack = new Stack<>();

//        for (Lexem lexem : lexems) {
//          if (lexem.getClass().equals(LConst.class)) {
//                //ToDo
//            }
//        }

        throw new UnsupportedOperationException();
    }
}
