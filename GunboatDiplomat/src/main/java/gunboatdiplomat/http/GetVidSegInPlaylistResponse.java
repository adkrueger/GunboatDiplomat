package gunboatdiplomat.http;

import java.util.ArrayList;
import java.util.List;

import gunboatdiplomat.model.VidSeg;

public class GetVidSegInPlaylistResponse {
	public List<VidSeg> segments;
	public int statusCode;	//if the video segment is not found in the playlist
	public String error;		//output message if video segment is not found
	
	public GetVidSegInPlaylistResponse(List<VidSeg> segments, int statusCode) {
		this.segments = segments;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public GetVidSegInPlaylistResponse(List<VidSeg> segments, int statusCode, String err) {
		this.segments = segments;
		this.statusCode = statusCode;
		this.error = err;
	}
	
	public GetVidSegInPlaylistResponse(int statusCode, String error) {
		this.segments = new ArrayList<>();
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		if(statusCode/100 == 2) {
			return "GetVidSegInPlaylist(" + segments + ")";
		}
		else {
			return "ErrorResult(" + segments + ", statusCode = " + statusCode + ", error = " + error + ")";
		}
	}
	
}