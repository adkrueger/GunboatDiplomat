package gunboatdiplomat.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

	public final String name;
	public List<VidSeg> contents = new ArrayList<>();
	
	public Playlist(String name) {
		this.name = name;
	}
	
	
	/*
	 * @Override
	 
	public boolean equals(Object o) {
		return ((VidSeg)o).id.equals(id) 
				&& ((VidSeg)o).character.equals(character) 
				&& ((VidSeg)o).quote.equals(quote) 
				&& ((VidSeg)o).seasonNum == seasonNum 
				&& ((VidSeg)o).episodeNum == episodeNum
				&& ((VidSeg)o).isLocal == isLocal
				&& ((VidSeg)o).isMarked == isMarked;
	}
	*/
}
