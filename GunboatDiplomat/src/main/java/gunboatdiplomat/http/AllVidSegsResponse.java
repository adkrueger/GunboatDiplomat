package gunboatdiplomat.http;

import java.util.ArrayList;
import java.util.List;

import gunboatdiplomat.model.VidSeg;

public class AllVidSegsResponse {

	public final List<VidSeg> vidSegs;
	public final int statusCode;
	public final String error;
	
	public AllVidSegsResponse(List<VidSeg> vidSegs, int code) {
		this.vidSegs = vidSegs;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllVidSegsResponse(int code, String errorMessage) {
		this.vidSegs = new ArrayList<VidSeg>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (vidSegs == null) { return "VidSegsEmpty"; }
		return "AllConstants(" + vidSegs.size() + ")";
	}
	
}
