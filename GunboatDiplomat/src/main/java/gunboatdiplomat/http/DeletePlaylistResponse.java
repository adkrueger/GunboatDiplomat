package gunboatdiplomat.http;

public class DeletePlaylistResponse {

	public final String id;
	public final int statusCode;
	public final String error;
	
	public DeletePlaylistResponse(String id, int statusCode) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public DeletePlaylistResponse(String id, int statusCode, String error) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		if(statusCode/100 == 2) {
			return "DeleteReponse(" + id + ")";
		}
		else {
			return "ErrorResult(" + ", statusCode = " + statusCode
					+ ", error = " + error + ")";
		}
	}
}
