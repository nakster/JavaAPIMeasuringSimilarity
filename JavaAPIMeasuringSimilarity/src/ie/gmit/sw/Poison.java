package ie.gmit.sw;

/**
 * 
 * Poison places known data item on the queue and when the consumer reads this item it closes down. 
 * Poison extends Shingle 
 * @author Naqi Ahmad G00332403
 *
 */
public class Poison extends Shingle{
	
	/**
	 * 
	 * @param DocumentID Document Id identifies which document
	 * @param hashCode hash-code 
	 */
	public Poison(int DocumentID, int hashCode) {
		super(DocumentID, hashCode);
	}

}
