package number;

public class NumberParseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NumberParseException(Throwable t) {
		super(t);
	}
	
	public NumberParseException(String str) {
		super(str);
	}
	
}
