package excercises1;

import ASTNodes.*;
import Lexems.*;
import interfaces.ASTNode;
import interfaces.Lexem;

class HelperClassChecker {

    private boolean isClass(Object test, Class c) {
        return test.getClass().equals(c);
    }

    // ASTNodes

    boolean isExp(ASTNode node) {
        return isClass(node, StmtExp.class);
    }

    boolean isExpVarRef(ASTNode node) {
        return isClass(node, ExpVarRef.class);
    }

    boolean isSub(ASTNode node) {
        return isClass(node, ExpSub.class);
    }

    boolean isAssign(ASTNode node) {
        return isClass(node, StmtAss.class);
    }

    boolean isDiv(ASTNode node) {
        return isClass(node, ExpDiv.class);
    }

    boolean isAdd(ASTNode node) {
        return isClass(node, ExpAdd.class);
    }

    boolean isMul(ASTNode node) {
        return isClass(node, ExpMul.class);
    }

    boolean isConst(ASTNode node) {
        return isClass(node, ExpConst.class);
    }

    public boolean isModulo(ASTNode node) {
        return isClass(node, ExpMod.class);
    }

    //Lexems

    boolean isLEOF(Lexem lexem) {
        return isClass(lexem, LEOF.class);
    }

    boolean isLEOL(Lexem lexem) {
        return isClass(lexem, LEOL.class);
    }

    boolean isLStar(Lexem lexem) {
        return isClass(lexem, LStar.class);
    }

    boolean isLSlash(Lexem lexem) {
        return isClass(lexem, LSlash.class);
    }

    boolean isLPlus(Lexem lexem) {
        return isClass(lexem, LPlus.class);
    }

    boolean isLConst(Lexem lexem) {
        return isClass(lexem, LConst.class);
    }

    boolean isLMinus(Lexem lexem) {
        return isClass(lexem, LMinus.class);
    }

    boolean isLEq(Lexem lexem) {
        return isClass(lexem, LEq.class);
    }

    boolean isLIdent(Lexem lexem) {
        return isClass(lexem, LIdent.class);
    }

    public boolean isPercent(Lexem lexem) {
        return isClass(lexem, LPercent.class);
    }

    public boolean shouldReduceMultiplicative(Operations lastOperation) {
        return lastOperation.equals(Operations.STAR) || lastOperation.equals(Operations.SLASH);
    }

    public boolean shouldReduceModulo(Operations lastOperation) {
        return lastOperation.equals(Operations.PROCENT);
    }
}
