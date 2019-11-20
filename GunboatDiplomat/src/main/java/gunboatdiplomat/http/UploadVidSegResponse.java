package gunboatdiplomat.http;

public class UploadVidSegResponse {
	
	public final String response;
	public final int httpCode;
	
	public UploadVidSegResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public UploadVidSegResponse(String s) {
		this.response = s;
		this.httpCode = 200;	// success
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
	
}
