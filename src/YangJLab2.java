import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author jingyuyang
 * This program does 
 */

/*
java YangJLab2 /Users/jennyyang/eclipse-workspace/YangJLab2/input/in.txt /Users/jennyyang/eclipse-workspace/YangJLab2/output/out.txt
*/

public class YangJLab2 {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader in;
		BufferedWriter out;
		System.out.println("in main");
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
	 * Goes through the list of entries and processes them for recursive and iterative
	 * processes, and prints to appropriate format
	 * 
	 * @param BufferedWriter to write file, a list of entries read from the file
	 */
	private static void processOneByOne(BufferedWriter output, LinkedList entries) {
		Node curr = entries.headNode;
		LinkedList singleOutput = new LinkedList();
		while (curr != null) {
			try {
				int input = Integer.parseInt(curr.getData().toString());
				printStringtoOutput("", output);
				printStringtoOutput("INPUT:" + input, output);
				
				//recursive method
				long recursiveStartTime = System.nanoTime();
			   singleOutput = recurisveHanoiSteps(input);
			   long recursitveEndTime = System.nanoTime();
				printStringtoOutput("Time for Recurisve: " + (recursitveEndTime - recursiveStartTime), output);
				//processResulttoPrint(singleOutput, output); // print out each set

			   //iterative method
				
				//processResulttoPrint(singleOutput, output); // print out each set
			} catch (InvalidInputException e) {
				printStringtoOutput("\t Error: Input is invalid. " + e.getMessage(), output);
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
	 * 
	 * @param size
	 * @return
	 * @throws InvalidInputException 
	 */
	public static LinkedList recurisveHanoiSteps(int size) throws InvalidInputException {
		if (size < 0) {
			throw new InvalidInputException("Input size cannot be negative, but " + size
					+ " is a negative number");
		}
		LinkedList steps = new LinkedList();		
		if (size == 0) {
			return steps;
		}
		return doRecursiveHanoiSteps(size, 'A', 'B', 'C', steps);
	}

	/**
	 * 
	 */
	private static LinkedList doRecursiveHanoiSteps(int disk, char source, char other,
			char dest, LinkedList steps) {
		if (disk == 1) {
			steps.add(new OutputItem(disk, source, dest));
		} else {
			doRecursiveHanoiSteps(disk - 1, source, dest, other, steps);
			steps.add(new OutputItem(disk, source, dest));
			doRecursiveHanoiSteps(disk - 1, other, dest, source, steps);
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
	private static void processResulttoPrint(LinkedList outputList, BufferedWriter out) {
		System.out.println("in process");

		if (outputList != null) {
			printStringtoOutput("OUTPUT: ", out);
			while (outputList.headNode != null) {
				//will print something like "Move disk 1 from tower A to tower C"
				printStringtoOutput(outputList.traversal().toString(), out);
			}
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
