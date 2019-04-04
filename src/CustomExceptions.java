/**
 * Custom-defined exceptions to be used in the code when the input 
 *  is not of valid format, such as a negative number, not an integer,
 *  or a string/char
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
  * custom exception for when the stack size is not correct
  * @author jingyuyang
  *
  */
 class StackSizeException extends Exception {

		private String message;

		/**
		 * constructor for no messages
		 */
		public StackSizeException() {
		}

		/**
		 * constructor if there is a message
		 * 
		 * @param String of the message
		 */
		public StackSizeException(String string) {
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
 
 