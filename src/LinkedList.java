/**
 * Implementation of LinkedList using Node objects. Used to store individual
 * inputs and each step of the Hanoi tower process
 * 
 * @author jingyuyang
 */
public class LinkedList {

	public Node headNode;
	private Node tailNode;
	private int length;

	/**
	 * Constructor
	 */
	public LinkedList() {
		headNode = null;
		tailNode = null;
		length = 0;
	}
	
	
	/**
	 * Adds the provided object to the LinkedList
	 * 
	 * @param object to be added next in the list
	 */
	public void add(Object data) {
		Node temp = new Node(data);
		if (length == 0) {
			headNode = temp;
			tailNode = headNode;
		} else {
			tailNode.next = temp;
			tailNode = temp;
		}
		length++;
	}

	/**
	 * Goes to and returns the data of the next Node in the LinkedList object
	 * 
	 * @return object that is the data for the next Node
	 */
	public Object traversal() {
		Object result = headNode.getData();
		headNode = headNode.next;
		return result;
	}

	/**
	 * Check if a LinkedList is empty
	 * 
	 * @return true if the List is empty
	 */
	public boolean isEmpty() {
		return length == 0;
	}

	
	/**
	 * Prints the list's contents to console. Debugging uses
	 */
	public void print() {
		Node temp = headNode;
		while (temp != null) {
			System.out.print(temp.getData().toString() + ", ");
			temp = temp.next;
		}
		System.out.println();
	}
}
