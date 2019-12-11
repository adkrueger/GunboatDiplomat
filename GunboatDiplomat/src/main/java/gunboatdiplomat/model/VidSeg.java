package gunboatdiplomat.model;

public class VidSeg {

	public String id;
	public String character;
	public String text;
	public int isLocal;		// 0 or 1
	public int isMarked;	// 0 or 1
	public String base64EncodedContents;
	public String url;

	
	public VidSeg(String id, String character, String text, int isLocal, int isMarked, String contents) {
		
		this.id = id;
		this.character = character;
		this.text = text;
		this.isLocal = isLocal;
		this.isMarked = isMarked;
		this.base64EncodedContents = contents;
	
	}
	
	public VidSeg(String id, String character, String text, int isLocal, int isMarked) {

		this.id = id;
		this.character = character;
		this.text = text;
		this.isLocal = isLocal;
		this.isMarked = isMarked;

	}
	
	// used in DAO when getting video segments from playlist
	public VidSeg(String url) {
		
		this.id = url.substring(url.lastIndexOf("/")+1);
		this.url = url;
		
	}
	
	// used in getPublicVidSegsHandler
	public VidSeg(String id, String character, String text) {
		this.url = "https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/" + id;
		this.character = character;
		this.text = text;
		isMarked = 1;
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
				&& ((VidSeg)o).text.equals(text)
				&& ((VidSeg)o).isLocal == isLocal
				&& ((VidSeg)o).isMarked == isMarked;				
	}
	
	@Override
	public String toString() {
		return id + ", with " + character + " saying \"" + text + "\". Marked: " + isMarked + ", Local: " + isLocal;
	}

}
