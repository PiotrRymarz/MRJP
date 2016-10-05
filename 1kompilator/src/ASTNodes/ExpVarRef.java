package ASTNodes;

import interfaces.ASTNode;

import java.util.List;

class ExpVarRef implements ASTNode {
    private String variable;

    public ExpVarRef(String variable) {
        this.variable = variable;
    }

    public String getVariableName() {
        return variable;
    }

    @Override
    public List<ASTNode> getChildren() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpVarRef expVarRef = (ExpVarRef) o;

        return variable != null ? variable.equals(expVarRef.variable) : expVarRef.variable == null;

    }

    @Override
    public int hashCode() {
        return variable != null ? variable.hashCode() : 0;
    }
}
