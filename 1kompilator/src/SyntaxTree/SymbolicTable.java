package SyntaxTree;

import java.util.HashMap;
import java.util.Map;

public class SymbolicTable {
    private Map<String, Integer> symbols;

    public SymbolicTable() {
        symbols = new HashMap<>();
    }

    public boolean contains(String name) {
        return symbols.containsKey(name);
    }

    public void insertKey(String name, Integer value) {
        symbols.put(name, value);
    }

    public Integer getValue(String key) {
        return symbols.get(key);
    }

    public Integer size() {
        return symbols.size();
    }

    public void delete(String name) {
        symbols.remove(name);
    }
}
