import java.io.BufferedWriter;
import java.io.IOException;

public class IterativeHanoi {
	private Stack sourceStack;
	private Stack destStack;
	private Stack otherStack;
	private int size;
	
	public IterativeHanoi(int size) {
		sourceStack = new Stack(size);
		destStack = new Stack(size);
		otherStack = new Stack(size);
		this.size = size;
	}
	
	
	/**
	 * 
	 * @param size
	 * @return
	 * @throws StackSizeException
	 * @throws InvalidInputException 
	 */
	public LinkedList iterativeHanoi() throws StackSizeException, InvalidInputException {
		LinkedList result = new LinkedList();
		
		char source = 'A';
		char dest = 'C';
		char other = 'B';
		
		if (size < 0) {
			throw new InvalidInputException("Input size cannot be negative, but " + size + " is a negative number");
		}
		double totalMoves = Math.pow(2, size) - 1;

		if (size % 2 == 0) {
			dest = 'B';
			other = 'C';
		}

		for (int i = size; i >= 1; i--) {
			sourceStack.push(i);
		}

		for (int i = 1; i <= totalMoves; i++) {
			if (i % 3 == 1) {
				result.add(moveDisk(sourceStack, destStack, source, dest));
			} else if (i % 3 == 2) {
				result.add(moveDisk(sourceStack, otherStack, source, other));
			} else {
				result.add(moveDisk(otherStack, destStack, other, dest));
			}
		}
		return result;
	}

	private static OutputItem moveDisk(Stack sStack, Stack dStack, char source, char dest)
			throws StackSizeException {
		if (sStack.isFull() && dStack.isFull()) {
			throw new StackSizeException("Stacks cannot be both full!");
		}
		if (sStack.isEmpty() && dStack.isEmpty()) {
			throw new StackSizeException("Both stacks cannot be empty!");
		}
		if (sStack.isEmpty()) {
			int toBeAdded = dStack.pop();
			sStack.push(toBeAdded);
			return new OutputItem(toBeAdded, dest, source);
		} else if (dStack.isEmpty()) {
			int toBeAdded = sStack.pop();
			dStack.push(toBeAdded);
			return new OutputItem(toBeAdded, source, dest);
		}

		int sourceTop = sStack.pop();
		int destTop = dStack.pop();

		if (sourceTop > destTop) {
			sStack.push(sourceTop);
			sStack.push(destTop);

			return new OutputItem(destTop, dest, source);

		} else {
			dStack.push(destTop);
			dStack.push(sourceTop);
			return new OutputItem(sourceTop, source, dest);
		}
	}

}
