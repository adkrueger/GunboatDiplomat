package gunboatdiplomat.http;

import java.util.ArrayList;
import java.util.List;

import gunboatdiplomat.model.MarkedSegment;

public class GetPublicVidSegsResponse {
	
	public final List<MarkedSegment> segments;
	public final int statusCode;
	public final String error;
	
	public GetPublicVidSegsResponse(List<MarkedSegment> segments, int code) {
		this.segments = segments;
		this.statusCode = code;
		this.error = "";
	}
	
	public GetPublicVidSegsResponse(int code, String errorMessage) {
		this.segments = new ArrayList<MarkedSegment>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (segments == null) {
			return "No public video segments";
		}
		return "All public video segments (" + segments.size() + ")";
	}

}
