package gunboatdiplomat.http;

public class MarkVidSegRequest {
	
	public String videoID;
	
	public MarkVidSegRequest(String videoID) {
		this.videoID = videoID;
	}
	public MarkVidSegRequest() {;
	}
	
	public String toString() {
		return videoID + " has been marked";
	}
	public String getVideoID() {
		return videoID;
	}
	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
	
	

}
