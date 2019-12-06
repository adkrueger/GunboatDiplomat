package gunboatdiplomat.http;

public class CreatePlaylistRequest {
	
	public String name;
	public boolean system;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public CreatePlaylistRequest() {
		
	}
	
	public CreatePlaylistRequest(String n) {
		this.name = n;
	}
	
	public CreatePlaylistRequest(String n, boolean system) {
		this.name = n;
		this.system = system;
	}
	
	public String toString() {
		return "CreatePlaylist(" + name + ")";
	}

}
