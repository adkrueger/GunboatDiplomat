package gunboatdiplomat.http;

public class GetVidSegInPlaylistRequest {
	public String id;
	
	public GetVidSegInPlaylistRequest(String id) {
		this.id = id;
	}
	
	public GetVidSegInPlaylistRequest() {
		
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getID() {
		return id;
	}
	
	public String toString() {
		return "Get(" + id + ")";
	}
	
}
