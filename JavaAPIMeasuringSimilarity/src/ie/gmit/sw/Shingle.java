package ie.gmit.sw;
/**
 * 
 * @author naqia
 *
 */
public class Shingle {
	//variables 
	private int docId;
	private int hashCode;
	
	//Constructors 
	/**
	 * empty constructor 
	 */
	public Shingle() {
		super();
	}
	/**
	 * constructor that takes in two paramameters 
	 * @param docId
	 * @param hashCode
	 */
	public Shingle(int docId, int hashCode) {
		super();
		this.docId = docId;
		this.hashCode = hashCode;
	}
	//getters and setters 
	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

}
