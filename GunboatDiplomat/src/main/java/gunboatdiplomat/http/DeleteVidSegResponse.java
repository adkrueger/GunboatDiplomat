package gunboatdiplomat.http;

public class DeleteVidSegResponse {

	public final String id;
	public final int statusCode;
	public final String err;
	
	public DeleteVidSegResponse(String id, int statusCode) {
		this.id = id;
		this.statusCode = statusCode;
		this.err = "";
	}
	
	public DeleteVidSegResponse(String id, int statusCode, String err) {
		this.id = id;
		this.statusCode = statusCode;
		this.err = err;
	}
	
	public String toString() {
		if(statusCode/200 == 2) {
			return "DeleteResponse(" + id + ")";
		}
		else {
			return "ErrorResult(" + id + ", statusCode = " + statusCode
					+ ", error = " + err + ")";
		}
	}
	
}
