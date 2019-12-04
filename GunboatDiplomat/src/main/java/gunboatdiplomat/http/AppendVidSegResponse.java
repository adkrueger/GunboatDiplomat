package gunboatdiplomat.http;

public class AppendVidSegResponse {
	public String playlistName;
	public int errorCode;
	public String message;
		
	
	public AppendVidSegResponse(String playlistName, int errorCode, String message) {
		this.playlistName = playlistName;
		this.errorCode = errorCode;
		this.message = message;
	}
	public AppendVidSegResponse(String playlistName, int errorCode) {
		this.playlistName = playlistName;
		this.errorCode = errorCode;
	}

	public String toString(String playlistName, int errorCode) {
		if(errorCode == 200) {
			return "Successful in appending segment to " + playlistName;
		}
		if(errorCode == 403) {
			return message + "Error Code: " + errorCode;
		}
		else {
			return "Could not be appended to " + playlistName + " Error Code: " + errorCode;
		}
	}
	
	public String getPlaylistName() {
		return playlistName;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}
