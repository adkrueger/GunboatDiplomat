package gunboatdiplomat.http;

public class SearchVidSegsRequest {

	private String character_speaking;
	private String quote;
	
	public String getCharacter_speaking() {
		return character_speaking;
	}

	public void setCharacter_speaking(String character_speaking) {
		this.character_speaking = character_speaking;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public SearchVidSegsRequest(String character_speaking, String quote) {
		this.character_speaking = character_speaking;
		this.quote = quote;
	}
	
	public SearchVidSegsRequest() {}
	
	public String toString() {
		return "SearchVidSegs(" + character_speaking + "," + quote + ")";
	}
	
}
