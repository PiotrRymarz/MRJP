package excercises1;

import ASTNodes.*;
import SyntaxTree.AbstractSyntaxTree;
import SyntaxTree.SymbolicTable;
import SyntaxTree.SyntaxParsingResult;
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
    private Operations lastOperation;
    private SymbolicTable symbolicTable;
    private HelperClassChecker helper;

    public SyntaxAnalyzer1() {
        this.helper = new HelperClassChecker();
    }

    @Override
    public SyntaxParsingResult getParsingResult(List<Lexem> lexems) {
        stmts = new ArrayList<>();
        stack = new Stack<>();
        lastOperation = Operations.EMPTY;
        symbolicTable = new SymbolicTable();

        for (Lexem lexem : lexems) {
            if (helper.isLConst(lexem)) {
                stack.add(new ExpConst(Integer.parseInt(lexem.getValue())));
            } else if (helper.isLPlus(lexem)) {
                if (lastOperation.equals(Operations.STAR) || lastOperation.equals(Operations.SLASH)) {
                    lastOperation = reduceMultiplicative(stack);
                }
                stack.add(new ExpAdd());
                lastOperation = Operations.PLUS;
            } else if (helper.isLMinus(lexem)) {
                if (lastOperation.equals(Operations.STAR) || lastOperation.equals(Operations.SLASH)) {
                    lastOperation = reduceMultiplicative(stack);
                }
                stack.add(new ExpSub());
                lastOperation = Operations.MINUS;
            } else if (helper.isLStar(lexem)) {
                stack.add((new ExpMul()));
                lastOperation = Operations.STAR;
            } else if (helper.isLSlash(lexem)) {
                stack.add((new ExpDiv()));
                lastOperation = Operations.STAR;
            } else if (helper.isLIdent(lexem)) {
                if(!symbolicTable.contains(lexem.getValue())) {
                    symbolicTable.insertKey(lexem.getValue(), symbolicTable.size());
                }
                stack.add(new ExpVarRef(lexem.getValue()));
            } else if (helper.isLEq(lexem)) {
                stack.add(new StmtAss());
                lastOperation = Operations.ASSIGNMENT;
            } else if (helper.isLEOL(lexem)) {
                reduceStmt(stack, stmts);
            } else if (helper.isLEOF(lexem)) {
                return new SyntaxParsingResult(
                        new AbstractSyntaxTree(stmts),
                        symbolicTable
                );
            }
        }

        return null;
    }




    private void reduceStmt(Stack<ASTNode> stack, List<ASTNode> stmts) {
                if (stack == null || stack.size() == 0) {
                        throw new UnsupportedOperationException("Can't reduce empty stack" + stack);
                    }
        lastOperation = reduceMultiplicative(stack);
        lastOperation = reduceAdditive(stack);
        lastOperation = reduceStatements(stack);

        if (stack.size() == 1) {
                stmts.add(stack.pop());
            } else {
                throw new UnsupportedOperationException("Reduced statement has size different to 1");
            }
    }

    private Operations reduceStatements(Stack<ASTNode> stack) {
        if (stack == null) {
            throw new NullPointerException("stack is null");
        }
        if (stack.size() < 1) {
            throw new UnsupportedOperationException("Can't reduce assignment in a stack smaller than 1 operation");
        }
        if (stack.size() == 3) {


            ASTNode first = stack.pop();
            ASTNode second = stack.pop();
            ASTNode third = stack.pop();

            if (helper.isAssign(second)) {
                stack.add(new StmtAss(third, first));
            }
        } else if (stack.size() == 1) {
            ASTNode exp = stack.pop();
            stack.add(new StmtExp(exp));
        }

        return Operations.EMPTY;
    }

    private Operations reduceAdditive(Stack<ASTNode> stack) {
        if (stack == null) {
                throw new NullPointerException("stack is null");
        }
        if (stack.size() < 3) {
                return lastOperation;
        }
        ASTNode first;
        ASTNode second;
        ASTNode third;
        //Reduce plus as long as possible
        while ( stack.size() > 2) {
                first = stack.pop();
                second = stack.pop();


            if(helper.isAdd(second)) {
                third = stack.pop();
                stack.push(new ExpAdd(third, first));
            } else if (helper.isSub(second)) {
                third = stack.pop();
                stack.push(new ExpSub(third, first));
            } else {
                stack.push(second);
                stack.push(first);

                return lastOperation;
            }
        }

        return Operations.EMPTY;
    }

    private Operations reduceMultiplicative(Stack<ASTNode> stack) {
        if (stack == null || stack.size() < 3) {
            throw new UnsupportedOperationException("Can't reduce empty stack as mul" + stack);
        }
        ASTNode first;
        ASTNode second;
        ASTNode third;
        //Reduce star as long as possible
        while ( stack.size() > 2) {
            first = stack.pop();
            second = stack.pop();

            if(helper.isMul(second)) {
                third = stack.pop();
                stack.push(new ExpMul(third, first));
            } else if (helper.isDiv(second)) {
                third = stack.pop();
                stack.push(new ExpDiv(third, first));
            } else if (helper.isAdd(second)) {
                stack.push(second);
                stack.push(first);

                return Operations.PLUS;
            } else if (helper.isSub(second)) {
                stack.push(second);
                stack.push(first);

                return Operations.MINUS;
            } else if (helper.isAssign(second)) {
                stack.push(second);
                stack.push(first);

                return Operations.ASSIGNMENT;
            }
        }

        return Operations.EMPTY;
    }


}
