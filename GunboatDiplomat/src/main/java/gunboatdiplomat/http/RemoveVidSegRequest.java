package gunboatdiplomat.http;

public class RemoveVidSegRequest {
	
	public String playlistName;
	public int videoIndex;
	
	public RemoveVidSegRequest(String playlistName, int videoIndex) {
		this.playlistName = playlistName;
		this.videoIndex = videoIndex;
	}
	
	public RemoveVidSegRequest() {
		
	}
	
	public String getPlaylistName() {
		return playlistName;
	}
	
	public int getVideoIndex() {
		return videoIndex;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
	
	public void setVideoIndex(int videoIndex) {
		this.videoIndex = videoIndex;
	}
	
	@Override
	public String toString() {
		return playlistName + " removing index " + videoIndex;
	}
}
