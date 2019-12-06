package gunboatdiplomat.http;

public class RegisterRemoteResponse {
	public String id;
	public int statusCode;
	public String error;
	
	public RegisterRemoteResponse(String id, int statusCode) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public RegisterRemoteResponse(String id, int statusCode, String err) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = err;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "RegisterRemoteResponse(" + id + ")";
		}
		else {
			return "ErrorResult(statusCode = " + statusCode
					+ ", error = " + error + ")";
		}
	}
}
