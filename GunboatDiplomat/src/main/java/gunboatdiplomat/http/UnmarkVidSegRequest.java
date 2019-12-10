package gunboatdiplomat.http;

public class UnmarkVidSegRequest {
	
	public String videoID;
	
	public UnmarkVidSegRequest(String videoID) {
		this.videoID = videoID;
	}
	public UnmarkVidSegRequest() {;
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
