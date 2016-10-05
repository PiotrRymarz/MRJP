package Lexems;
import interfaces.Lexem;

public class LConst implements Lexem {
	
	private Integer value;
	
	public LConst(String value) {
		try {
			this.value = Integer.parseInt(value);
		} catch (Error e) {
			System.out.println(value + "is not a number");
		}	
	}

	public String getValue() {
		return value.toString();
	}

	@Override
	public String toString() {
		return "LConst [value=" + value + "]";
	}

}
