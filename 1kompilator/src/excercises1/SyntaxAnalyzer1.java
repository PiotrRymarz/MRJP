package excercises1;

import ASTNodes.*;
import Lexems.*;
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

    @Override
    public SyntaxParsingResult getParsingResult(List<Lexem> lexems) {
        stmts = new ArrayList<>();
        stack = new Stack<>();
        lastOperation = Operations.EMPTY;
        symbolicTable = new SymbolicTable();

        for (Lexem lexem : lexems) {
            if (isConst(lexem)) {
                stack.add(new ExpConst(Integer.parseInt(lexem.getValue())));
            } else if (isPlus(lexem)) {
                if (lastOperation.equals(Operations.STAR) || lastOperation.equals(Operations.SLASH)) {
                    lastOperation = reduceMultiplicative(stack);
                }
                stack.add(new ExpAdd());
                lastOperation = Operations.PLUS;
            } else if (isMinux(lexem)) {
                if (lastOperation.equals(Operations.STAR) || lastOperation.equals(Operations.SLASH)) {
                    lastOperation = reduceMultiplicative(stack);
                }
                stack.add(new ExpSub());
                lastOperation = Operations.MINUS;
            } else if (isStar(lexem)) {
                stack.add((new ExpMul()));
                lastOperation = Operations.STAR;
            } else if (isSlash(lexem)) {
                stack.add((new ExpDiv()));
                lastOperation = Operations.STAR;
            } else if (isIdent(lexem)) {
                if(!symbolicTable.contains(lexem.getValue())) {
                    symbolicTable.insertKey(lexem.getValue(), symbolicTable.size());
                }
                stack.add(new ExpVarRef(lexem.getValue()));
            } else if (isEq(lexem)) {
                stack.add(new StmtAss());
                lastOperation = Operations.ASSIGNMENT;
            } else if (isEOL(lexem)) {
                reduceStmt(stack, stmts);
            } else if (isEOF(lexem)) {
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

            if (isAssign(second)) {
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


            if(isAdd(second)) {
                third = stack.pop();
                stack.push(new ExpAdd(third, first));
            } else if (isSub(second)) {
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

            if(isMul(second)) {
                third = stack.pop();
                stack.push(new ExpMul(third, first));
            } else if (isDiv(second)) {
                third = stack.pop();
                stack.push(new ExpDiv(third, first));
            } else if (isAdd(second)) {
                stack.push(second);
                stack.push(first);

                return Operations.PLUS;
            } else if (isSub(second)) {
                stack.push(second);
                stack.push(first);

                return Operations.MINUS;
            } else if (isAssign(second)) {
                stack.push(second);
                stack.push(first);

                return Operations.ASSIGNMENT;
            }
        }

        return Operations.EMPTY;
    }

    private boolean isSub(ASTNode node) {
            return isClass(node, ExpSub.class);
    }

    private boolean isAssign(ASTNode node) {
        return isClass(node, StmtAss.class);
    }

    private boolean isDiv(ASTNode node) {
        return isClass(node, ExpDiv.class);
    }

    private boolean isClass(Object test, Class c) {
        return test.getClass().equals(c);
    }

    private boolean isAdd(ASTNode node) {
        return isClass(node, ExpAdd.class);
    }

    private boolean isMul(ASTNode node) {
        return isClass(node, ExpMul.class);
    }

    private boolean isEOF(Lexem lexem) {
        return isClass(lexem, LEOF.class);
    }

    private boolean isEOL(Lexem lexem) {
        return isClass(lexem, LEOL.class);
    }

    private boolean isStar(Lexem lexem) {
        return isClass(lexem, LStar.class);
    }

    private boolean isSlash(Lexem lexem) {
        return isClass(lexem, LSlash.class);
    }

    private boolean isPlus(Lexem lexem) {
        return isClass(lexem, LPlus.class);
    }

    private boolean isConst(Lexem lexem) {
        return isClass(lexem, LConst.class);
    }

    private boolean isMinux(Lexem lexem) {
        return isClass(lexem, LMinus.class);
    }

    private boolean isEq(Lexem lexem) {
        return isClass(lexem, LEq.class);
    }

    private boolean isIdent(Lexem lexem) {
        return isClass(lexem, LIdent.class);

    }
}
