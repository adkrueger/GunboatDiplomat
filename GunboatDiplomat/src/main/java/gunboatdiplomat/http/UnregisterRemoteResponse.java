package gunboatdiplomat.http;

public class UnregisterRemoteResponse {

	public String id;
	public int statusCode;
	public String error;
	
	public UnregisterRemoteResponse(String id, int statusCode, String error) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = error;
	}

	public UnregisterRemoteResponse(String id, int statusCode) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "UnregisterRemoteResponse(" + id + ")";
		}
		else {
			return "ErrorResult(" + id + ",statusCode=" + statusCode + ",error=" + error + ")";
		}
	}
	
}
