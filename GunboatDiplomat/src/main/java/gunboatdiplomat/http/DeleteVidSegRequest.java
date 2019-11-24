package gunboatdiplomat.http;

public class DeleteVidSegRequest {
	public String id;
	
	public DeleteVidSegRequest(String id) {
		this.id = id;
	}
	
	public DeleteVidSegRequest() {
		
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getID() {
		return id;
	}
	
	public String toString() {
		return "Delete(" + id + ")";
	}
	
}
