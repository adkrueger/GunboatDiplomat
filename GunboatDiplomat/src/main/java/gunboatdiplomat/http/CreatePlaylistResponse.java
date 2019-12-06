package gunboatdiplomat.http;

public class CreatePlaylistResponse {
	
	public final String response;
	public final int httpCode;
	public final String error;
	
	public CreatePlaylistResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
		this.error = "";
	}
	
	public CreatePlaylistResponse (String s, int code, String error) {
		this.response = s;
		this.httpCode = code;
		this.error = error;
	}
	
	public String toString() {
		
		if (httpCode/100 == 2) {
			return "DeleteResponde(" + response + ")";
		} else {
			return "ErrorResult(httpCode = " + httpCode + ", error = " + error + ")";
		}
		
	}

}
