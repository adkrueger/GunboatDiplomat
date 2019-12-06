package gunboatdiplomat.HandlerTest;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.util.IOUtils;
import com.google.gson.Gson;

import gunboatdiplomat.DeleteVidSegHandler;
import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.UploadVidSegHandler;
import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.DeleteVidSegRequest;
import gunboatdiplomat.http.DeleteVidSegResponse;
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
		VidSeg exist = dao.getVidSeg("testingUploadVidSeg");
		if(exist != null) {
			System.out.println("vid seg already in table");
			dao.deleteVidSeg(exist.id);
		}
		
		InputStream inputStream = UploadVidSegHandlerTest.class.getResourceAsStream("Segment1.ogg");
		String testFile = null;
		
		try {
			testFile = new String(java.util.Base64.getEncoder().encode(IOUtils.toByteArray(inputStream)));
		} catch (IOException ioe) {
			Assert.fail("Invalid: " + ioe.getMessage());
		}
		
		UploadVidSegRequest request = new UploadVidSegRequest("testingUploadVidSeg", "Leonard McCoy", "Death by natural causes.", 1, 0, testFile);
		String input = new Gson().toJson(request);

		try {
			testInputSuccess(input);
		}
		catch (IOException ioe) {
			Assert.fail("Invalid: " + ioe.getMessage());
		}
		
        DeleteVidSegRequest dvsr = new DeleteVidSegRequest("testingUploadVidSeg");
        DeleteVidSegResponse deleteResponse = new DeleteVidSegHandler().handleRequest(dvsr, createContext("delete"));
        System.out.println(deleteResponse.id);
        Assert.assertEquals("testingUploadVidSeg", deleteResponse.id);
        
	}
	
	@Test
	public void testUploadVidSegHTTPS() {
		
		UploadVidSegResponse resp1 = new UploadVidSegResponse("testing");
		Assert.assertEquals(resp1.toString(), "Response(testing)");
		resp1 = new UploadVidSegResponse("testing", 200);
		Assert.assertEquals(resp1.toString(), "Response(testing)");

		UploadVidSegRequest req = new UploadVidSegRequest();
		req.setId("id");
		Assert.assertEquals(req.getId(), "id");
		req.setCharacter_speaking("character_speaking");
		Assert.assertEquals(req.getCharacter_speaking(), "character_speaking");
		req.setQuote("quote");
		Assert.assertEquals(req.getQuote(), "quote");
		req.setLocal(1);
		Assert.assertTrue(req.isLocal());
		req.setMarked(0);
		Assert.assertFalse(req.isMarked());
		Assert.assertEquals(req.toString(), "UploadVidSeg(id,character_speaking,quote,1,0)");
		
	}
	
}
