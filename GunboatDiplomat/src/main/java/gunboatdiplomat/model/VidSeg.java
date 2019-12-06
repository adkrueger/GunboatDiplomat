package gunboatdiplomat.model;

public class VidSeg {

	public String id;
	public String character;
	public String quote;
	public int isLocal;		// 0 or 1
	public int isMarked;	// 0 or 1
	public String base64EncodedContents;
	public String url;

	
	public VidSeg(String id, String character, String quote, int isLocal, int isMarked, String contents) {
		
		this.id = id;
		this.character = character;
		this.quote = quote;
		this.isLocal = isLocal;
		this.isMarked = isMarked;
		this.base64EncodedContents = contents;
	
	}
	
	public VidSeg(String id, String character, String quote, int isLocal, int isMarked) {

		this.id = id;
		this.character = character;
		this.quote = quote;
		this.isLocal = isLocal;
		this.isMarked = isMarked;

	}
	
	// used in getVidSegs to add video segments to the list from the S3 bucket
	public VidSeg(String id, String url) {
		
		this.id = id;
		this.url = url;
		
	}
	
	public boolean setContents(String contents) {
		base64EncodedContents = contents;
		return true;
	}
	
	public boolean setURL(String url) {
		this.url = url;
		return true;
	}

	@Override
	public boolean equals(Object o) {
		return     ((VidSeg)o).id.equals(id) 
				&& ((VidSeg)o).character.equals(character) 
				&& ((VidSeg)o).quote.equals(quote)
				&& ((VidSeg)o).isLocal == isLocal
				&& ((VidSeg)o).isMarked == isMarked;				
	}
	
	@Override
	public String toString() {
		return id + ", with " + character + " saying \"" + quote + "\".";
	}

}
