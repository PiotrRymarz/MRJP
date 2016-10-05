package Lexems;

import interfaces.Lexem;

public class LIdent implements Lexem {
	private String name;
	
	public LIdent(String name) {
		this.name = name;
	}

	public String getValue() {
		return name;
	}

	@Override
	public String toString() {
		return "LIdent [name=" + name + "]";
	}
}
