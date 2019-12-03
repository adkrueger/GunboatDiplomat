package gunboatdiplomat.http;

public class SearchVidSegsRequest {

	String character_speaking;
	String quote;
	
	public SearchVidSegsRequest(String character_speaking, String quote) {
		this.character_speaking = character_speaking;
		this.quote = quote;
	}
	
	public String toString() {
		return "SearchVidSegs(" + character_speaking + "," + quote + ")";
	}
	
}
