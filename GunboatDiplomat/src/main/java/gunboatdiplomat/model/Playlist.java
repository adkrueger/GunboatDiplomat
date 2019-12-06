package gunboatdiplomat.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

	public final String name;
	public List<VidSeg> contents;

	public Playlist(String name) {
		this.name = name;
		this.contents = new ArrayList<>();
	}

	public boolean addVideoSegment(VidSeg vs) {
		boolean ans = false;
		contents.add(vs);
		ans = true; 
		return ans;
	}
	
	@Override 
	public boolean equals(Object o) {
		boolean eq = true;
		if (((Playlist)o).name.equals(name)) {

			if (((Playlist)o).contents.size() == contents.size()) {

				for(int i = 0; i < contents.size(); i++) {
					if (((Playlist)o).contents.get(i).equals(contents.get(i))) {
						eq = true;
					}
					else {
						return false;
					}
				}
			}
		}
		else {
			return false;
		}

		return eq;
	}
}
