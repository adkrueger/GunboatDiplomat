package gunboatdiplomat.http;

public class GetVidSegInPlaylistResponse {
	public String id;
	public int statusCode;	//if the video segment is not found in the playlist
	public String error;		//output message if video segment is not found
	
	public GetVidSegInPlaylistResponse(String id, int statusCode) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public GetVidSegInPlaylistResponse(String id, int statusCode, String err) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = err;
	}
	
	public GetVidSegInPlaylistResponse(int statusCode, String error) {
		this.id = "";
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		if(statusCode/100 == 2) {
			return "GetVidSegInPlaylist(" + id + ")";
		}
		else {
			return "ErrorResult(" + id + ", statusCode = " + statusCode + ", error = " + error + ")";
		}
	}
	
}