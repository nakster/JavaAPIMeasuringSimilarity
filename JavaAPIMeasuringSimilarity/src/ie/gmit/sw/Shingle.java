package ie.gmit.sw;

public class Shingle {
	//variables 
	private int docId;
	private int hashCode;
	
	//Constructors 
	public Shingle() {
		super();
	}

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
