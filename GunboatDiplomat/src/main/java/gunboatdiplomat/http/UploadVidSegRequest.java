package gunboatdiplomat.http;

public class UploadVidSegRequest {
	public String id;
	public String character_speaking;
	public String quote;
	public int isLocal;
	public int isMarked;
	public String base64EncodedContents;
	
	public String getName( ) { return id; }
	public void setName(String id) { this.id = id; }
	
	public String getId() {	return id; }
	public void setId(String id) { this.id = id; }
	
	public String getCharacter_speaking() { return character_speaking; }
	public void setCharacter_speaking(String character_speaking) { this.character_speaking = character_speaking; }

	public String getQuote() { return quote; }
	public void setQuote(String quote) { this.quote = quote; }

	public boolean isLocal() { return isLocal == 1; }
	public void setLocal(int isLocal) { this.isLocal = isLocal; }
	
	public boolean isMarked() { return isMarked == 1; }
	public void setMarked(int isMarked) { this.isMarked = isMarked; }
	
	public UploadVidSegRequest() { }
	
	public UploadVidSegRequest(String id, String character, String quote, int isLocal, int isMarked, String base64EncodedContents) {

		this.id = id;
		this.character_speaking = character;
		this.quote = quote;
		this.isLocal = isLocal;
		this.isMarked = isMarked;
		this.base64EncodedContents = base64EncodedContents;
		
	}
	
	public String toString() {
		return "UploadVidSeg(" + id + "," + character_speaking + "," + quote + "," + isLocal + "," + isMarked + ")";
	}
	
}
