package gunboatdiplomat.model;

import java.util.ArrayList;
import java.util.List;

public class Library {

	public final String name;
	public List<Playlist> playlists = new ArrayList<>();
	public List<VidSeg> vidSegs = new ArrayList<>();
	
	public Library(String name) {
		this.name = name;
	}
	
}
