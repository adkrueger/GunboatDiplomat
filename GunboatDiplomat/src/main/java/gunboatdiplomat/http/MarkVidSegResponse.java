package gunboatdiplomat.http;

public class MarkVidSegResponse {
	
	public String videoID;
	public int errorCode;
	public String message;
	
	public MarkVidSegResponse(String videoID, int errorCode, String message) {
		this.videoID = videoID;
		this.errorCode = errorCode;
		this.message = message;
	}
	
	
	public MarkVidSegResponse(String videoID, int errorCode) {
		this.errorCode = errorCode;
		this.videoID  = videoID;
	}
	
	public String toString() {
		if(errorCode == 200) {
			return videoID + "has been marked successfully";
		}
		
		if(errorCode == 403){
			return videoID + "has not been marked successfully --> Error Code: " + errorCode;
		}
		
		else {
			return "Something has gone wrong.";
		}
	}
	
	

}
