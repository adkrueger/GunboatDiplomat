package gunboatdiplomat.http;

import java.util.ArrayList;
import java.util.List;

import gunboatdiplomat.model.VidSeg;

public class ListRemoteSegmentsResponse {

	public List<VidSeg> segments;
	public int statusCode;
	public String error;
	
	public ListRemoteSegmentsResponse(List<VidSeg> list, int statusCode) {
		this.segments = list;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public ListRemoteSegmentsResponse(int statusCode, String error) {
		this.segments = new ArrayList<VidSeg>();
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		if (segments == null) { return "EmptySegments"; }
		return "Segments(" + segments.size() + ")";
	}
	
}
