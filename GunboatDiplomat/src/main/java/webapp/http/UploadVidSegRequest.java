package webapp.http;

public class UploadVidSegRequest {
	
	public String name;
	public String base64EncodedValue;
	public boolean system;
	
	// getters and setters are for AWS compliancy
	public String getName( ) { return name; }
	public void setName(String name) { this.name = name; }
	
	public boolean getSystem( ) { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public String getBase64EncodedValue() { return base64EncodedValue; }
	public void setBase64EncodedValue(String base64EncodedValue) { this.base64EncodedValue = base64EncodedValue; }
	
	// constructor with no arguments is also for AWS compliancy
	public UploadVidSegRequest() {}
	
	public UploadVidSegRequest(String n, String encoding) {
		this.name = n;
		this.base64EncodedValue = encoding;
	}
	
	public UploadVidSegRequest(String n, String encoding, boolean system) {
		this.name = n;
		this.base64EncodedValue = encoding;
		this.system = system;
	}
	
	public String toString() {
		return "CreateConstant(" + name + "," + base64EncodedValue + ")";
	}
	
}
