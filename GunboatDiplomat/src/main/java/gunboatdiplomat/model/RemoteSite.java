package gunboatdiplomat.model;

public class RemoteSite {
	
	public String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public RemoteSite(String url) {
		this.url = url;
	}
	
	public RemoteSite() {
		
	}
	
	@Override
	public boolean equals(Object o) {
		return ((RemoteSite)o).url.equals(url);
	}

	
}
