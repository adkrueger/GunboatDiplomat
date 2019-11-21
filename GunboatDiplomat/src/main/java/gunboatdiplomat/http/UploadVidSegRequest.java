package gunboatdiplomat.http;

public class UploadVidSegRequest {
	public String id;
	public String character_speaking;
	public String quote;
	public String base64EncodedSeason;
	public String base64EncodedEpisode;
	public int isLocal;
	public int isMarked;
	
	public String getName( ) { return id; }
	public void setName(String id) { this.id = id; }
	
	public String getId() {	return id; }
	public void setId(String id) { this.id = id; }
	
	public String getCharacter_speaking() { return character_speaking; }
	public void setCharacter_speaking(String character_speaking) { this.character_speaking = character_speaking; }

	public String getQuote() { return quote; }
	public void setQuote(String quote) { this.quote = quote; }
	
	public String getBase64EncodedSeason() { return base64EncodedSeason; }
	public void setBase64EncodedSeason(String base64EncodedSeason) { this.base64EncodedSeason = base64EncodedSeason; }

	public String getBase64EncodedEpisode() { return base64EncodedEpisode; }
	public void setBase64EncodedEpisode(String base64EncodedEpisode) { this.base64EncodedEpisode = base64EncodedEpisode; }

	public boolean isLocal() { return isLocal == 1; }
	public void setLocal(int isLocal) { this.isLocal = isLocal; }
	
	public boolean isMarked() { return isMarked == 1; }
	public void setMarked(int isMarked) { this.isMarked = isMarked; }
	
	public UploadVidSegRequest() { }
	
	public UploadVidSegRequest(String id, String character, String quote, String seasonNum, String episodeNum, int isLocal, int isMarked) {

		this.id = id;
		this.character_speaking = character;
		this.quote = quote;
		this.base64EncodedSeason = seasonNum;
		this.base64EncodedEpisode = episodeNum;
		this.isLocal = isLocal;
		this.isMarked = isMarked;
		
	}
	
	public String toString() {
		return "CreateConstant(" + id + "," + character_speaking + "," + quote + "," + base64EncodedSeason + "," + base64EncodedEpisode + "," + isLocal + "," + isMarked + ")";
	}
	
}
