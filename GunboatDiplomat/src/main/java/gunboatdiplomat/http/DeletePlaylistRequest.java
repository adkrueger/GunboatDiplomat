package gunboatdiplomat.http;

public class DeletePlaylistRequest {
	public String id;
	
	public DeletePlaylistRequest(String id) {
		this.id = id;
	}
	
	public DeletePlaylistRequest() {
		
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
