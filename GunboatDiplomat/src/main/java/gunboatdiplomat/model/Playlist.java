package gunboatdiplomat.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

	public final String name;
	public List<VidSeg> vidSegs = new ArrayList<>();
	
	public Playlist(String name) {
		this.name = name;
	}
	
/*	public boolean appendVidSeg(VidSeg vs) {
		vidSegs.add(vs);
		return true;
	}
*/	
}
