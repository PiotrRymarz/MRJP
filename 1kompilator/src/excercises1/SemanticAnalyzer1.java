package excercises1;

import ASTNodes.ExpConst;
import ASTNodes.ExpVarRef;
import AnnotatedASTNodes.*;
import SemanticTree.AnnotatedAbstractSyntaxTree;
import SyntaxTree.AbstractSyntaxTree;
import SyntaxTree.SymbolicTable;
import SyntaxTree.SyntaxParsingResult;
import interfaces.ASTNode;
import interfaces.AnnotatedASTNode;
import interfaces.SemanticAnalyzer;

import java.util.ArrayList;
import java.util.List;

class SemanticAnalyzer1 implements SemanticAnalyzer {
    private AbstractSyntaxTree abstractSyntaxTree;
    private SymbolicTable symbolicTable;
    private boolean[] variablesInitialized;
    private List<AnnotatedASTNode> annotatedStmts;
    private HelperClassChecker helper;

    public SemanticAnalyzer1() {
        this.helper = new HelperClassChecker();
    }

    @Override
    public AnnotatedAbstractSyntaxTree getAnnotatedAST(SyntaxParsingResult syntaxParsingResult) {
        AnnotatedAbstractSyntaxTree annotatedAbstractSyntaxTree;
        abstractSyntaxTree = syntaxParsingResult.getAST();
        symbolicTable = syntaxParsingResult.getSymbolicTable();
        variablesInitialized = new boolean[symbolicTable.size()];

        annotatedStmts = new ArrayList<>();

        for (ASTNode stmt : abstractSyntaxTree.getProgram().getChildren()) {
            annotatedStmts.add(getAnnotatedStmt(stmt, symbolicTable, variablesInitialized));
        }

        return new AnnotatedAbstractSyntaxTree(annotatedStmts);
    }

    private AnnotatedASTNode getAnnotatedStmt(ASTNode stmt, SymbolicTable symbolicTable, boolean[] variablesInitialized) {
        AnnotatedASTNode annotatedRootNode;

        if (helper.isAssign(stmt)) {

            annotatedRootNode = getAnnotatedAssignmentStatement(stmt, symbolicTable, variablesInitialized);

        } else if (helper.isExp(stmt)) {

            annotatedRootNode = getAnnotatedExpressionStatement(stmt, symbolicTable, variablesInitialized);
        } else {
            throw new IllegalArgumentException("Can't resolve statements other to assign and expression");
        }

        return annotatedRootNode;
    }

    private AnnotatedASTNode getAnnotatedExpressionStatement(ASTNode stmt, SymbolicTable symbolicTable, boolean[] variablesInitialized) {
        assertOneChild(stmt);

        List<ASTNode> children = stmt.getChildren();

        ASTNode exp = children.get(0);
        AnnotatedASTNode annotatedExpression = getAnnotatedExpression(exp, symbolicTable, variablesInitialized);

        return new StmtExpAnnotated(annotatedExpression);
    }

    private  AnnotatedASTNode getAnnotatedAssignmentStatement(ASTNode stmt, SymbolicTable symbolicTable, boolean[] variablesInitialized) {
        List<ASTNode> children = stmt.getChildren();

        assertAssignmentCriteria(children);

        ASTNode variable = children.get(0);
        ASTNode exp = children.get(1);
        AnnotatedASTNode annotatedExpression = getAnnotatedExpression(exp, symbolicTable, variablesInitialized);

        //This has to be after expression evaluation to check if variable is not used before initialization
        AnnotatedASTNode annotatedVariable = getAnnotatedVariableInAssignment(variable, symbolicTable, variablesInitialized);

        return new StmtAssAnnotated(annotatedVariable, annotatedExpression);

    }

    private AnnotatedASTNode getAnnotatedExpression(ASTNode exp, SymbolicTable symbolicTable, boolean[] variablesInitialized) {
        if (helper.isAdd(exp)) {
            return getAnnotatedAdd(exp, symbolicTable, variablesInitialized);
        } else if (helper.isConst(exp)) {
            return getAnnotatedConst(exp, symbolicTable, variablesInitialized);
        } else if (helper.isDiv(exp)) {
            return getAnnotatedDiv(exp, symbolicTable, variablesInitialized);
        } else if (helper.isSub(exp)) {
            return getAnnotatedSub(exp, symbolicTable, variablesInitialized);
        } else if (helper.isMul(exp)) {
            return getAnnotatedMul(exp, symbolicTable, variablesInitialized);
        } else if (helper.isSub(exp)) {
            return getAnnotatedSub(exp, symbolicTable, variablesInitialized);
        } else if (helper.isExpVarRef(exp)) {
            return getAnnotatedVariableInExpression(exp, symbolicTable, variablesInitialized);
        } else {
            throw new IllegalArgumentException("Can't assign : " + exp.toString() + " to a variable.");
        }
    }

    private AnnotatedASTNode getAnnotatedVariableInExpression(ASTNode exp, SymbolicTable symbolicTable, boolean[] variablesInitialized) {
        String variableName = ((ExpVarRef)exp).getVariableName();

        if (!symbolicTable.contains(variableName)) {
            throw new IllegalArgumentException("Something is wrong. " + variableName + " should be in symbolic table.");
        }

        if(!variablesInitialized[symbolicTable.getValue(variableName)]) {
            throw new IndexOutOfBoundsException("Variable '" + variableName + "' not initialized.");
        }

        return new ExpVarRefAnnotated(variableName);
    }

    private AnnotatedASTNode getAnnotatedMul(ASTNode exp, SymbolicTable symbolicTable, boolean[] variablesInitialized) {
        assertTwoChildren(exp);

        List<ASTNode> children = exp.getChildren();

        AnnotatedASTNode firstExp = getAnnotatedExpression(children.get(0), symbolicTable, variablesInitialized);
        AnnotatedASTNode secondExp = getAnnotatedExpression(children.get(1), symbolicTable, variablesInitialized);

        return new ExpMulAnnotated(firstExp, secondExp);
    }

    private AnnotatedASTNode getAnnotatedSub(ASTNode exp, SymbolicTable symbolicTable, boolean[] variablesInitialized) {
        assertTwoChildren(exp);

        List<ASTNode> children = exp.getChildren();

        AnnotatedASTNode firstExp = getAnnotatedExpression(children.get(0), symbolicTable, variablesInitialized);
        AnnotatedASTNode secondExp = getAnnotatedExpression(children.get(1), symbolicTable, variablesInitialized);

        return new ExpSubAnnotated(firstExp, secondExp);
    }

    private AnnotatedASTNode getAnnotatedDiv(ASTNode exp, SymbolicTable symbolicTable, boolean[] variablesInitialized) {
        assertTwoChildren(exp);

        List<ASTNode> children = exp.getChildren();

        AnnotatedASTNode firstExp = getAnnotatedExpression(children.get(0), symbolicTable, variablesInitialized);
        AnnotatedASTNode secondExp = getAnnotatedExpression(children.get(1), symbolicTable, variablesInitialized);

        return new ExpDivAnnotated(firstExp, secondExp);
    }

    private AnnotatedASTNode getAnnotatedAdd(ASTNode exp, SymbolicTable symbolicTable, boolean[] variablesInitialized) {
        assertTwoChildren(exp);

        List<ASTNode> children = exp.getChildren();

        AnnotatedASTNode firstExp = getAnnotatedExpression(children.get(0), symbolicTable, variablesInitialized);
        AnnotatedASTNode secondExp = getAnnotatedExpression(children.get(1), symbolicTable, variablesInitialized);

        return new ExpAddAnnotated(firstExp, secondExp);
    }

    private AnnotatedASTNode getAnnotatedConst(ASTNode exp, SymbolicTable symbolicTable, boolean[] variablesInitialized) {
        return new ExpConstAnnotated(((ExpConst)exp).getValue());
    }

    private AnnotatedASTNode getAnnotatedVariableInAssignment(ASTNode variable, SymbolicTable symbolicTable, boolean[] variablesInitialized) {
        String variableName = ((ExpVarRef)variable).getVariableName();

        if (!symbolicTable.contains(variableName)) {
            throw new IllegalArgumentException("Something is wrong. " + variableName + " should be in symbolic table.");
        }

        variablesInitialized[symbolicTable.getValue(variableName)] = true;

        return new ExpVarRefAnnotated(variableName);
    }

    private void printStmt(ASTNode stmt) {

        if (stmt.getClass() == ExpConst.class) {
            System.out.println(((ExpConst)stmt).getValue());
        } else if (stmt.getClass() == ExpVarRef.class) {
            System.out.println(((ExpVarRef)stmt).getVariableName());
        } else {
            System.out.println(stmt.getClass());
            for (ASTNode child : stmt.getChildren()) {
                printStmt(child);
            }
        }
    }

    private void assertAssignmentCriteria(List<ASTNode> children) {
        if (children.size() != 2) {
            throw new IllegalArgumentException("Assignment should have two children." + children.toString());
        }

        if (!helper.isExpVarRef(children.get(0))) {
            throw new IllegalArgumentException("Assignment not possible to : " +  children.get(0).toString());
        }
    }

    private void assertOneChild(ASTNode exp) {
        if (exp.getChildren().size() != 1) {
            throw new IllegalArgumentException("Operation should have one arguments.");
        }
    }

    private void assertTwoChildren(ASTNode exp) {
        if (exp.getChildren().size() != 2) {
            throw new IllegalArgumentException("Operation should have two arguments.");
        }
    }

}
