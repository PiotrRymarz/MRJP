package Lexems;

import interfaces.Lexem;

public class LEOF implements Lexem {

    @Override
    public String getValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "LEOF";
    }
}
