package gunboatdiplomat.model;

public class VidSeg {

	public final String id;
	public final String character;
	public final String quote;	
	public final int seasonNum;
	public final int episodeNum;
	public int isLocal;		// 0 or 1
	public int isMarked;	// 0 or 1

	public VidSeg(String id, String character, String quote, int seasonNum, int episodeNum, int isLocal, int isMarked) {

		this.id = id;
		this.character = character;
		this.quote = quote;
		this.seasonNum = seasonNum;
		this.episodeNum = episodeNum;
		this.isLocal = isLocal;
		this.isMarked = isMarked;

	}

	public VidSeg(String id, String character, String quote, int seasonNum, int episodeNum) {

		this.id = id;
		this.character = character;
		this.quote = quote;
		this.seasonNum = seasonNum;
		this.episodeNum = episodeNum;

	}

	@Override
	public boolean equals(Object o) {
		return     ((VidSeg)o).id.equals(id) 
				&& ((VidSeg)o).character.equals(character) 
				&& ((VidSeg)o).quote.equals(quote) 
				&& ((VidSeg)o).seasonNum == seasonNum 
				&& ((VidSeg)o).episodeNum == episodeNum
				&& ((VidSeg)o).isLocal == isLocal
				&& ((VidSeg)o).isMarked == isMarked;				
	}

}
