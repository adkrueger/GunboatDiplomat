package gunboatdiplomat.http;

public class AppendVidSegRequest {
	
	public String playlistName;
	public String videoID;
	
	public AppendVidSegRequest(String playlistName, String videoID) {
		this.playlistName = playlistName;
		this.videoID = videoID;
		
	}
	public AppendVidSegRequest() {
		
	}
	
	public String toString() {
		return playlistName + " added a new video segment of id " + videoID; 
	}
	
	
	public String getPlaylistName() {
		return playlistName;
	}
	public String getVideoID() {
		return videoID;
	}
	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
}
