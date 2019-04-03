import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 
 * @author jingyuyang This program takes in an input file of integers and
 *         processes them as the size of towers of Hanoi. The program then
 *         recurisvely and iteratively process each tower, print the output of
 *         the processing, along with their respective run times.
 */

/*
 * java YangJLab2 /Users/jennyyang/eclipse-workspace/YangJLab2/input/in.txt
 * /Users/jennyyang/eclipse-workspace/YangJLab2/output/out.txt
 */

public class YangJLab2 {

	/**
	 * Main method that runs steps to read the input file, process each tower of
	 * Hanoi statement, and write to an output file
	 * 
	 * @param an array of args passed in from the command line. Contains the path of
	 *           the input and the output files
	 */
	public static void main(String[] args) {
		BufferedReader in;
		BufferedWriter out;

		// warn user to pass in the correct arguments prior to exiting
		if (args.length != 2) {
			System.err.println("Missing one or more arguments. Please include both input and output files");
			System.exit(1);
		}

		// create reader and writers based on user input file paths
		try {
			in = new BufferedReader(new FileReader(args[0]));
			out = new BufferedWriter(new FileWriter(args[1]));
		} catch (Exception e) {
			return;
		}

		// process input integers in the file, then write to output file
		LinkedList allEntries = readFile(in);
		if (allEntries.headNode != null) { // Not an empty file
			processOneByOne(out, allEntries);
		}

		// closing the input and output so it can write to file
		try {
			in.close();
			out.close();
		} catch (Exception x) {
			System.err.println(x.toString());
		}
	}

	/**
	 * Goes through the list of entries and processes them for recursive and
	 * iterative processes, and prints to appropriate format
	 * 
	 * @param BufferedWriter to write file, a list of entries read from the file
	 */
	private static void processOneByOne(BufferedWriter output, LinkedList entries) {
		Node curr = entries.headNode;
		LinkedList recursiveOutput = new LinkedList();
		LinkedList iterativeOutput = new LinkedList();

		while (curr != null) {
			try {
				String inputString = curr.getData().toString();
				int input = Integer.parseInt(inputString);
				if (input < 0) {
					throw new InvalidInputException("Input size cannot be negative, but " + input + " is a negative number");
				}
				if (input == 0) {
					printStringtoOutput("INPUT:" + input, output);
					processResulttoPrint(recursiveOutput, output, true);
					processResulttoPrint(iterativeOutput, output, false);
				} else {
					printStringtoOutput("", output);
					printStringtoOutput("INPUT:" + input, output);

					// recursive method
					long recursiveStartTime = System.nanoTime();
					recursiveOutput = recurisveHanoiSteps(input);
					long recursiveEndTime = System.nanoTime();
					long recursiveTime = recursiveEndTime - recursiveStartTime;

					// iterative method
					IterativeHanoi iteration = new IterativeHanoi(input);
					long iterativeStartTime = System.nanoTime();
					iterativeOutput = iteration.iterativeHanoi();
					long iterativeEndTime = System.nanoTime();
					long iterativeTime = iterativeEndTime - iterativeStartTime;

					if (input < 7) {
						processResulttoPrint(recursiveOutput, output, true); // print out each set
						processResulttoPrint(iterativeOutput, output, false); // print out each set
					}
					printStringtoOutput("Time for recursive: " + recursiveTime, output);
					printStringtoOutput("Time for iterative: " + iterativeTime, output);
					printStringtoOutput("", output);

				}
			} catch (MemoryOrTimeException e) {
				printStringtoOutput("Error: input is too large. " + e.getMessage(), output);
			} catch (NumberFormatException e) {
				printStringtoOutput("Error: input from file is invalid. " + e.getMessage(), output);
			} catch (InvalidInputException e) {
				printStringtoOutput("Error: Input is invalid. " + e.getMessage(), output);
			} catch (StackSizeException e) {
				printStringtoOutput("Error: Stack sizes has the error:  " + e.getMessage(), output);
				e.printStackTrace();
			}
			curr = curr.next;
		}
	}

	/**
	 * reads the file and adds each expression to be evaluated to a list
	 * 
	 * @param the bufferedReader's file stream input
	 * @return LinkedList of each expression to be evaluated
	 */
	private static LinkedList readFile(BufferedReader input) {
		String temp;
		LinkedList allEntries = new LinkedList();
		try {
			while ((temp = input.readLine()) != null) {
				allEntries.add(temp);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return allEntries;
	}

	/**
	 * method to run the recursive Hanoi helper method
	 * 
	 * @param size of the tower
	 * @return LinkedList of result. list of steps
	 * @throws InvalidInputException if the input is a negative number
	 * @throws MemoryOrTimeException
	 */
	private static LinkedList recurisveHanoiSteps(int size) throws InvalidInputException, MemoryOrTimeException {
		if (size < 0) {
			throw new InvalidInputException("Input size cannot be negative, but " + size + " is a negative number");
		}
		LinkedList steps = new LinkedList();
		try {
			return doRecursiveHanoiSteps(size, 'A', 'B', 'C', steps);
		} catch (OutOfMemoryError e) {
			throw new MemoryOrTimeException("Ran out of memory");
		}
	}

	/**
	 * Helper method for recursive Hanoi processing
	 * 
	 * @param current disk number, the source tower, destination tower, and the
	 *                other tower
	 * @return the LinkedList for output
	 * @throws MemoryOrTimeException
	 */
	private static LinkedList doRecursiveHanoiSteps(int disk, char source, char other, char dest, LinkedList steps)
			throws OutOfMemoryError {
			if (disk == 1) {
					steps.add(new OutputItem(disk, source, dest));
			} else {
				doRecursiveHanoiSteps(disk - 1, source, dest, other, steps);
					steps.add(new OutputItem(disk, source, dest));
				doRecursiveHanoiSteps(disk - 1, other, source, dest, steps);
			}
			return steps;

	}

	/**
	 * Using the results, matches the operands to the operators and calls another
	 * method to print in the appropriate format
	 * 
	 * @param steps for evaluating an expression, stored in an object as two
	 *              LinkedLists, and the BufferedWriter instance
	 */
	private static void processResulttoPrint(LinkedList outputList, BufferedWriter out, boolean isRecursive) {
		if (outputList != null) {
			printStringtoOutput("OUTPUT (" + (isRecursive ? "recursive" : "iterative") + ") : ", out);
			while (outputList.headNode != null) {
				// will print something like "Move disk 1 from tower A to tower C"
				printStringtoOutput(outputList.traversal().toString(), out);
			}
			printStringtoOutput("", out);
		}
	}

	/**
	 * writes to file as directed will exit with error message if write fails
	 * 
	 * @param a String of the line that it will write, and the BufferedWriter
	 *          instance
	 */
	private static void printStringtoOutput(String lineToPrint, BufferedWriter out) {
		try {
			out.write(lineToPrint);
			out.newLine();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(3);
		}
	}
}
