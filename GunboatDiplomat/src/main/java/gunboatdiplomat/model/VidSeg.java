package gunboatdiplomat.model;

public class VidSeg {

	public final String id;
	public final String character;
	public final String quote;
	public final int episodeNum;
	public final int seasonNum;
	public boolean isLocal;
	public boolean isMarked;
	
	public VidSeg(String id, String character, String quote, int episodeNum, int seasonNum, boolean isLocal, boolean isMarked) {
		
		this.id = id;
		this.character = character;
		this.quote = quote;
		this.episodeNum = episodeNum;
		this.seasonNum = seasonNum;
		this.isLocal = isLocal;
		this.isMarked = isMarked;
		
	}
	
}
