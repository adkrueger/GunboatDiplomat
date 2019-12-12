package gunboatdiplomat.http;

public class RemoveVidSegResponse {
	
	public String playlistName;
	public int errorCode;
	public String message;
	
	public RemoveVidSegResponse(String playlistName, int errorCode, String message) {
		this.playlistName = playlistName;
		this.errorCode = errorCode;
		this.message = message;
	}

	public RemoveVidSegResponse(String playlistName, int errorCode) {
		this.playlistName = playlistName;
		this.errorCode = errorCode;
	}
		
	public String toString() {
		if(errorCode == 200) {
			return "Successful in removing segment from " + playlistName;
		} else if (errorCode == 403) {
			return message + "Error Code: " + errorCode;
		} else {
			return "Could not be deleted from " + playlistName + ". Error Code: " + errorCode;
		}
	}
}
