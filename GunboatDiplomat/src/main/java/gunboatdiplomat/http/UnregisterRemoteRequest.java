package gunboatdiplomat.http;

public class UnregisterRemoteRequest {

	public String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UnregisterRemoteRequest(String url) {
		this.url = url;
	}

	public UnregisterRemoteRequest() {}
	
}
