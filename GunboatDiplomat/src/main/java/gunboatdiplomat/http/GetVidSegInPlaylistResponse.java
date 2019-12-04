package gunboatdiplomat.http;

import java.util.ArrayList;

import gunboatdiplomat.model.Playlist;
import gunboatdiplomat.model.VidSeg;

public class GetVidSegInPlaylistResponse {
	public final String id;
	public final int statusCode;	//if the video segment is not found in the playlist
	public final String error;		//output message if video segment is not found
	
	//Not sure if this will be necessary but we will include a created playlist
	//and list of video segments for now
	public final Playlist playlist;
	public final ArrayList<VidSeg> videoSegs;
	
	public GetVidSegInPlaylistResponse(String id, int statusCode) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = "";
		this.playlist = null;
		this.videoSegs = null;
	}
	
	public GetVidSegInPlaylistResponse(String id, int statusCode, String err) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = err;
		this.playlist = null;
		this.videoSegs = null;
	}
	
	public GetVidSegInPlaylistResponse(Playlist p, ArrayList<VidSeg> vs, String id, int statusCode, String error) {
		this.playlist = p;
		this.videoSegs = vs; //TODO: not sure if this is allowed setting and array this way...
							 //      should look into later on
		this.id = id;
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		if(statusCode/100 == 2) {
			return "GetVidSeg(" + id + ")";
		}
		else {
			return "ErrorResult(" + id + ", statusCode = " + statusCode
					+ ", error = " + error + ")";
		}
	}
	
}