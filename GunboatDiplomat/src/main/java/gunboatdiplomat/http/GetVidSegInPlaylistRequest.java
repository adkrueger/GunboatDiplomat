package gunboatdiplomat.http;

public class GetVidSegInPlaylistRequest {
	public String video_id;
	public String playlist_id;
	
	public GetVidSegInPlaylistRequest(String video_id, String playlist_id) {
		this.video_id = video_id;
		this.playlist_id = playlist_id;
	}
	
	public GetVidSegInPlaylistRequest() {}

	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public String getPlaylist_id() {
		return playlist_id;
	}

	public void setPlaylist_id(String playlist_id) {
		this.playlist_id = playlist_id;
	}

	public String toString() {
		return "Get(" + video_id + "," + playlist_id + ")";
	}
	
}
