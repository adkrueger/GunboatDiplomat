package gunboatdiplomat.http;

import java.util.HashMap;
import java.util.List;

import gunboatdiplomat.model.VidSeg;

public class ListPlaylistsResponse {

	public HashMap<String, List<VidSeg>> playlists;
	public final int statusCode;
	public final String error;
	
	public ListPlaylistsResponse(HashMap<String, List<VidSeg>> playlists, int statusCode) {
		this.playlists = playlists;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public ListPlaylistsResponse(int code, String errorMessage) {
		this.playlists = new HashMap<String, List<VidSeg>>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (playlists == null) { return "NoPlaylists"; }
		return "AllConstants(" + playlists.size() + ")";
	}
	
}
