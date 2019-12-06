package gunboatdiplomat.http;

public class GetVidSegInPlaylistRequest {
	
	public String playlist_id;
	
	public GetVidSegInPlaylistRequest(String playlist_id) {
		this.playlist_id = playlist_id;
	}
	
	public GetVidSegInPlaylistRequest() {}

	public String getPlaylist_id() {
		return playlist_id;
	}

	public void setPlaylist_id(String playlist_id) {
		this.playlist_id = playlist_id;
	}

	public String toString() {
		return "Get(" + playlist_id + ")";
	}
	
}
