package gunboatdiplomat.http;

import java.util.ArrayList;

import gunboatdiplomat.model.RemoteSite;

public class ListRemotesResponse {
	
	public ArrayList<RemoteSite> remotes;
	public final int statusCode;
	public final String error;
	
	public ListRemotesResponse(ArrayList<RemoteSite> remotes, int statusCode) {
		this.remotes = remotes;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public ListRemotesResponse(int code, String errorMessage) {
		this.remotes = new ArrayList<RemoteSite>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (remotes == null) { return "No Remotes Found"; }
		return "AllConstants(" + remotes.size() + ")";
	}
}
