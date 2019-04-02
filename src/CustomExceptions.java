/**
 * Custom-defined exceptions to be used in the code when the input postfix
 * expression is not of valid format
 * 
 * @author jingyuyang
 *
 */
 class InvalidInputException extends Exception {

	private String message;

	/**
	 * constructor for no messages
	 */
	public InvalidInputException() {
	}

	/**
	 * constructor if there is a message
	 * 
	 * @param String of the message
	 */
	public InvalidInputException(String string) {
		message = string;
	}

	/**
	 * Getter of the custom error message
	 * @return the error message
	 */
	public String getMessage() {
		return message;
	}
}

/**
 * Custom-defined exceptions to be used in the code when the Stack is popped or
 * peeked at while it is empty
 * 
 * @author jingyuyang
 *
 */
class EmptyStackException extends Exception {
}