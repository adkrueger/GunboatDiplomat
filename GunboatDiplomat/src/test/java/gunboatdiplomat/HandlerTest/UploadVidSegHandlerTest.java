package gunboatdiplomat.HandlerTest;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.util.IOUtils;
import com.google.gson.Gson;

import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.UploadVidSegHandler;
import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.UploadVidSegRequest;
import gunboatdiplomat.http.UploadVidSegResponse;
import gunboatdiplomat.model.VidSeg;

public class UploadVidSegHandlerTest extends LambdaTest {

	void testInputSuccess(String input) throws IOException {
		UploadVidSegHandler handler = new UploadVidSegHandler();
		UploadVidSegRequest request = new Gson().fromJson(input, UploadVidSegRequest.class);
		
		UploadVidSegResponse response = handler.handleRequest(request, createContext("upload"));
		Assert.assertEquals(200, response.httpCode);
	}
	
	void testInputFailure(String input, int failureCode) throws IOException {
		UploadVidSegHandler handler = new UploadVidSegHandler();
		UploadVidSegRequest request = new Gson().fromJson(input, UploadVidSegRequest.class);
		
		UploadVidSegResponse response = handler.handleRequest(request, createContext("upload"));
		Assert.assertEquals(failureCode, response.httpCode);
	}
	
	@Test
	public void testOkInput() throws Exception {

		VideoSegmentDAO dao = new VideoSegmentDAO();
		VidSeg exist = dao.getVidSeg("testingVidSeg");
		if(exist != null) {
			System.out.println("vid seg already in table");
			dao.deleteVidSeg(exist);
		}
		
		InputStream inputStream = UploadVidSegHandlerTest.class.getResourceAsStream("Segment1.ogg");
		String testFile = null;
		
		try {
			testFile = new String(java.util.Base64.getEncoder().encode(IOUtils.toByteArray(inputStream)));
		} catch (IOException ioe) {
			Assert.fail("Invalid: " + ioe.getMessage());
		}
		
		UploadVidSegRequest request = new UploadVidSegRequest("testingVidSeg", "Leonard McCoy", "Death by natural causes.", 1, 0, testFile);
		String input = new Gson().toJson(request);

		try {
			testInputSuccess(input);
		}
		catch (IOException ioe) {
			Assert.fail("Invalid: " + ioe.getMessage());
		}
		
	}
	
}
