package ie.gmit.sw;
/**
 * This is a object shingle class
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
	 * @param docId Document Id to identify different documents 
	 * @param hashCode hash-code 
	 */
	public Shingle(int docId, int hashCode) {
		super();
		this.docId = docId;
		this.hashCode = hashCode;
	}
	//getters and setters 
	/**
	 * 
	 * @return returns value docId
	 */
	public int getDocId() {
		return docId;
	}
	/**
	 * 
	 * @param docId takes in document ID 
	 */
	public void setDocId(int docId) {
		this.docId = docId;
	}
	/**
	 * 
	 * @return hashCode
	 */
	public int getHashCode() {
		return hashCode;
	}
	/**
	 * 
	 * @param hashCode takes in the hashcode for the docID
	 */
	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

}
