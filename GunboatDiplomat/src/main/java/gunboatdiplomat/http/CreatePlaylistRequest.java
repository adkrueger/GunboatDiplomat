package gunboatdiplomat.http;

public class CreatePlaylistRequest {
	
	public String name;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public CreatePlaylistRequest() { }
	
	public CreatePlaylistRequest(String n) {
		this.name = n;
	}
	
	public String toString() {
		return "CreatePlaylist(" + name + ")";
	}

}
