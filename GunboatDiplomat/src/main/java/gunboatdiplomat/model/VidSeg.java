package gunboatdiplomat.model;

public class VidSeg {

	public final String id;
	public final String character;
	public final String quote;	
	public final int seasonNum;
	public final int episodeNum;
	public boolean isLocal;
	public boolean isMarked;
	
	public VidSeg(String id, String character, String quote, int seasonNum, int episodeNum, boolean isLocal, boolean isMarked) {
		
		this.id = id;
		this.character = character;
		this.quote = quote;
		this.seasonNum = seasonNum;
		this.episodeNum = episodeNum;
		this.isLocal = isLocal;
		this.isMarked = isMarked;
		
	}
	
}
