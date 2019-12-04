package gunboatdiplomat.http;

public class RegisterRemoteRequest {

	public String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RegisterRemoteRequest(String url) {
		this.url = url;
	}
	
	public RegisterRemoteRequest() {}
	
}
