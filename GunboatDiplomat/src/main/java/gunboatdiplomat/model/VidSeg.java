package gunboatdiplomat.model;

public class VidSeg {

	public String id;
	public String character;
	public String quote;	
	public int seasonNum;
	public int episodeNum;
	public int isLocal;		// 0 or 1
	public int isMarked;	// 0 or 1
	public String base64EncodedContents;

	
	public VidSeg(String id, String character, String quote, int seasonNum, int episodeNum, int isLocal, int isMarked, String contents) {
		
		this.id = id;
		this.character = character;
		this.quote = quote;
		this.seasonNum = seasonNum;
		this.episodeNum = episodeNum;
		this.isLocal = isLocal;
		this.isMarked = isMarked;
		this.base64EncodedContents = contents;
	
	}
	
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
	
	public VidSeg(String id, String contents) {
		
		this.id = id;
		this.base64EncodedContents = contents;
		
	}
	
	public boolean setContents(String contents) {
		base64EncodedContents = contents;
		return true;
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
	
	@Override
	public String toString() {
		return id + ", with " + character + " saying \"" + quote + "\".";
	}

}
