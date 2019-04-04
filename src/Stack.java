/**
 * 
 * @author jingyuyang
 *
 */
public class Stack {
	int size;
	int top;
	int array[];

	/**
	 * constructor that creates the stack based on the size given 
	 * @param size
	 */
	public Stack(int size) {
		this.size = size;
		this.top = -1;
		this.array = new int[size];
	}

	
	/**
	 *  Adds an integer to the stack
	 * @param toBeAdded is the integer to add to the top
	 * @throws StackSizeException if trying to add to a full stack
	 */
	public void push(int toBeAdded) throws StackSizeException {
		if (isFull()) {
			throw new StackSizeException("Cannot push to a full stack " + size +  " TOP: " + top);
		}
		top++;
      array[top] = toBeAdded; 
	}
	
	/**
	 * 
	 * @return the integer at the top of the stack 
	 * @throws StackSizeException if trying to pop from an empty stack
	 */
	public int pop() throws StackSizeException{
		if (isEmpty()) {
			throw new StackSizeException("Cannot pop from an empty stack");
		}
		int result = array[top];
		top--;
		return result;
	}
	
	
	/**
	 * 
	 * @return if the stack is at capacity
	 */
	public boolean isFull() {
		return (top == (size - 1));
	}
	
	/**
	 * 
	 * @return whether the stack has no integers
	 */
	public boolean isEmpty() {
		return top == -1;
	}
}