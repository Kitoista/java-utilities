package ui.component.bind.exception;

public class UnableToHandleTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UnableToHandleTypeException(Class<?> c) {
		super("Unable to handle " + c);
	}

}
