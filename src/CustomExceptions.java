/**
 * Custom-defined exceptions to be used in the code when the input 
 *  is not of valid format, or if the stack is empty or full
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
  * 
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
 
 /**
  * 
  * @author jingyuyang
  *
  */
 class MemoryOrTimeException extends Exception {

		private String message;

		/**
		 * constructor for no messages
		 */
		public MemoryOrTimeException() {
		}

		/**
		 * constructor if there is a message
		 * 
		 * @param String of the message
		 */
		public MemoryOrTimeException(String string) {
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
