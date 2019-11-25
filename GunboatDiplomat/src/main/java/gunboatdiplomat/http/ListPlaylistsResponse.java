package gunboatdiplomat.http;

import java.util.ArrayList;
import java.util.List;

import gunboatdiplomat.model.Playlist;

public class ListPlaylistsResponse {

	public final List<Playlist> playlists;
	public final int statusCode;
	public final String error;
	
	public ListPlaylistsResponse(List<Playlist> playlists, int statusCode) {
		this.playlists = playlists;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public ListPlaylistsResponse(int code, String errorMessage) {
		this.playlists = new ArrayList<Playlist>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (playlists == null) { return "NoPlaylists"; }
		return "AllConstants(" + playlists.size() + ")";
	}
	
}
