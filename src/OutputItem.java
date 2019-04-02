/**
 * Class that defines the object of a step in the hanoi tower.  
 * Has getters and its data is defined upon creation
 * @author jingyuyang
 */
public class OutputItem {
	private int disk;
	private char source;
	private char dest;

	/**
	 * Constructor that sets the data and stores three attributes, 
	 * @param string for the disk number, character for the name of the source tower,
	 * and character for the name of the destination tower
	 */
	public OutputItem(int disk, char tower1, char tower2) {
		this.disk = disk;
		this.source = tower1;
		this.dest = tower2;
	}
	/**
	 * Getter for the disks int
	 * @return the disks int
	 */
	public int getDisk() {
		return disk;
	}
	
	/**
	 * Getter for the source character
	 * @return the source character
	 */
	public char getTower1() {
		return source;
	}
	
	/**
	 * Getter for the dest character
	 * @return the dest character
	 */
	public char getTower2() {
		return dest;
	}
	
	
	/**
	 * debugging printer
	 * @return the String to be printed 
	 */
	public String toString() {
		return "Move disk " + disk + " from "+ source + 
				" to tower " + dest;
	}
}
