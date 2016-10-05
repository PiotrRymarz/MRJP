package ASTNodes;

import interfaces.ASTNode;

import java.util.List;

public class ExpConst implements ASTNode {
    private Integer value;

    public ExpConst(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public List<ASTNode> getChildren() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpConst expConst = (ExpConst) o;

        return getValue() != null ? getValue().equals(expConst.getValue()) : expConst.getValue() == null;

    }

    @Override
    public int hashCode() {
        return getValue() != null ? getValue().hashCode() : 0;
    }
}
