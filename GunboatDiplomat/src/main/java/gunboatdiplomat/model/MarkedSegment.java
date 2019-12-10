package gunboatdiplomat.model;

public class MarkedSegment {

	public String url;
	public String character;
	public String text;
	
	public MarkedSegment(String url, String character, String text) {
		this.url = "https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/" + url;
		this.character = character;
		this.text = text;
	}
	
	@Override
	public boolean equals(Object ms) {
		return ((MarkedSegment) ms).url.equals(this.url)
				&& ((MarkedSegment) ms).character.equals(this.character)
				&& ((MarkedSegment) ms).text.equals(this.text);
	}
	
	public String toString() {
		return character + " says " + text + ", located at " + url;
	}
}
