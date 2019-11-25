package gunboatdiplomat.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

	public final String name;
	public List<VidSeg> contents = new ArrayList<>();

	public Playlist(String name) {
		this.name = name;
	}


	@Override 
	public boolean equals(Object o) {
		boolean eq = true;
		if (((Playlist)o).name.equals(name)) {

			for(int i = 0; i < contents.size(); i++) {
				if (((Playlist)o).contents.get(i).equals(contents.get(i))) {
					eq = true;
				}
				else {
					return false;
				}
			}
		}
		else {
			return false;
		}
		
		return eq;
	}
}
