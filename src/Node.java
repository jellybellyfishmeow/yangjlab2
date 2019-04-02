/**
 * 
 * @author jingyuyang
 *
 */
public class Node {
	private Object data;
	public Node next;
	
	
	/**
	 * 
	 * @param data as the object being passed into the list
	 */
	public Node(Object data) {
		this.data = data;
		this.next = null;
	}

	
	/**
	 * 
	 * @return
	 */
	public Object getData() {
		return this.data;
	}
}