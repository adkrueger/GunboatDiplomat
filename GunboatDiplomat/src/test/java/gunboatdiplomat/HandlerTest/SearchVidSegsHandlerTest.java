package gunboatdiplomat.HandlerTest;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.util.IOUtils;

import gunboatdiplomat.DeleteVidSegHandler;
import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.SearchVidSegsHandler;
import gunboatdiplomat.UploadVidSegHandler;
import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.DeleteVidSegRequest;
import gunboatdiplomat.http.DeleteVidSegResponse;
import gunboatdiplomat.http.SearchVidSegsRequest;
import gunboatdiplomat.http.SearchVidSegsResponse;
import gunboatdiplomat.http.UploadVidSegRequest;
import gunboatdiplomat.http.UploadVidSegResponse;
import gunboatdiplomat.model.VidSeg;

public class SearchVidSegsHandlerTest extends LambdaTest {


	@Test
	public void testSearchBothHandler() throws Exception {
		// upload a testing video segment
		InputStream inputStream = UploadVidSegHandlerTest.class.getResourceAsStream("Segment1.ogg");
		String testFile = new String(java.util.Base64.getEncoder().encode(IOUtils.toByteArray(inputStream)));;
		UploadVidSegRequest req = new UploadVidSegRequest("testingSearchBoth", "Leonard McCoy", "Death by natural causes.", 1, 0, testFile);
		UploadVidSegResponse resp = new UploadVidSegHandler().handleRequest(req, createContext("upload"));
		Assert.assertEquals("testingSearchBoth", resp.response);

		SearchVidSegsRequest searchReq = new SearchVidSegsRequest("Leonard McCoy", "Death by natural causes.");
		SearchVidSegsHandler svsh = new SearchVidSegsHandler();
		SearchVidSegsResponse searchResp = svsh.handleRequest(searchReq, createContext("search"));
		VideoSegmentDAO dao = new VideoSegmentDAO();
		for(VidSeg vs: searchResp.vidSegs) {
			System.out.println(vs);
			Assert.assertTrue(dao.getVidSeg(vs.id) != null);		// proof of concept - show the vid seg is really in the table
			Assert.assertEquals("Leonard McCoy", vs.character);		// check character is the only thing we've found
			Assert.assertEquals("Death by natural causes.", vs.text);		// check quote is the only thing we've found
		}

		// now delete
		DeleteVidSegRequest dvsr = new DeleteVidSegRequest("testingSearchBoth");
		DeleteVidSegResponse deleteResponse = new DeleteVidSegHandler().handleRequest(dvsr, createContext("delete"));
		System.out.println(deleteResponse.id);
		Assert.assertEquals("testingSearchBoth", deleteResponse.id);
	}

	@Test
	public void testSearchCharacterHandler() throws Exception {
		// upload a testing video segment
		InputStream inputStream = UploadVidSegHandlerTest.class.getResourceAsStream("Segment1.ogg");
		String testFile = new String(java.util.Base64.getEncoder().encode(IOUtils.toByteArray(inputStream)));;
		UploadVidSegRequest req = new UploadVidSegRequest("testingSearchCharacter", "Leonard McCoy", "Death by natural causes.", 1, 0, testFile);
		UploadVidSegResponse resp = new UploadVidSegHandler().handleRequest(req, createContext("upload"));
		Assert.assertEquals("testingSearchCharacter", resp.response);

		SearchVidSegsRequest searchReq = new SearchVidSegsRequest("Leonard McCoy", "");
		SearchVidSegsHandler svsh = new SearchVidSegsHandler();
		SearchVidSegsResponse searchResp = svsh.handleRequest(searchReq, createContext("search"));
		VideoSegmentDAO dao = new VideoSegmentDAO();
		for(VidSeg vs: searchResp.vidSegs) {
			System.out.println(vs);
			Assert.assertTrue(dao.getVidSeg(vs.id) != null);		// proof of concept - show the vid seg is really in the table
			Assert.assertEquals("Leonard McCoy", vs.character);		// check character is the only thing we've found
		}
		
		searchReq = new SearchVidSegsRequest("Leonard", "");
		searchResp = svsh.handleRequest(searchReq, createContext("search"));
		Assert.assertTrue(searchResp.vidSegs.contains(new VidSeg("testingSearchCharacter", "Leonard McCoy", "Death by natural causes.", 1, 0)));
		for(VidSeg vs: searchResp.vidSegs) {
			System.out.println(vs);
			Assert.assertTrue(dao.getVidSeg(vs.id) != null);		// proof of concept - show the vid seg is really in the table
			Assert.assertTrue(vs.character.contains("Leonard"));		// check character is the only thing we've found
		}

		// now delete
		DeleteVidSegRequest dvsr = new DeleteVidSegRequest("testingSearchCharacter");
		DeleteVidSegResponse deleteResponse = new DeleteVidSegHandler().handleRequest(dvsr, createContext("delete"));
		System.out.println(deleteResponse.id);
		Assert.assertEquals("testingSearchCharacter", deleteResponse.id);
	}

	@Test
	public void testSearchQuoteHandler() throws Exception {
		// upload a testing video segment
		InputStream inputStream = UploadVidSegHandlerTest.class.getResourceAsStream("Segment1.ogg");
		String testFile = new String(java.util.Base64.getEncoder().encode(IOUtils.toByteArray(inputStream)));;
		UploadVidSegRequest req = new UploadVidSegRequest("testingSearchQuote", "Leonard McCoy", "Death by natural causes.", 1, 0, testFile);
		UploadVidSegResponse resp = new UploadVidSegHandler().handleRequest(req, createContext("upload"));
		Assert.assertEquals("testingSearchQuote", resp.response);

		SearchVidSegsRequest searchReq = new SearchVidSegsRequest("", "Death by natural causes.");
		SearchVidSegsHandler svsh = new SearchVidSegsHandler();
		SearchVidSegsResponse searchResp = svsh.handleRequest(searchReq, createContext("search"));
		VideoSegmentDAO dao = new VideoSegmentDAO();
		for(VidSeg vs: searchResp.vidSegs) {
			System.out.println(vs);
			Assert.assertTrue(dao.getVidSeg(vs.id) != null);		// proof of concept - show the vid seg is really in the table
			Assert.assertEquals("Death by natural causes.", vs.text);		// check quote is the only thing we've found
		}

		searchReq = new SearchVidSegsRequest("", "Death");
		searchResp = svsh.handleRequest(searchReq, createContext("search"));
		Assert.assertTrue(searchResp.vidSegs.contains(new VidSeg("testingSearchQuote", "Leonard McCoy", "Death by natural causes.", 1, 0)));
		for(VidSeg vs: searchResp.vidSegs) {
			System.out.println(vs);
			Assert.assertTrue(dao.getVidSeg(vs.id) != null);		// proof of concept - show the vid seg is really in the table
			Assert.assertTrue(vs.character.contains("Leonard"));		// check character is the only thing we've found
		}
		
		// now delete
		DeleteVidSegRequest dvsr = new DeleteVidSegRequest("testingSearchQuote");
		DeleteVidSegResponse deleteResponse = new DeleteVidSegHandler().handleRequest(dvsr, createContext("delete"));
		System.out.println(deleteResponse.id);
		Assert.assertEquals("testingSearchQuote", deleteResponse.id);
	}

	@Test
	public void testSearchHTTPS() {

		SearchVidSegsRequest req1 = new SearchVidSegsRequest();
		req1.setQuote("this is a test");
		Assert.assertEquals(req1.getQuote(), "this is a test");
		req1.setCharacter_speaking("this is a test");
		Assert.assertEquals(req1.getCharacter_speaking(), "this is a test");
		
		SearchVidSegsResponse resp1 = new SearchVidSegsResponse(422, "test error");
		Assert.assertEquals(resp1.toString(), "SearchVidSegs(0)");
		
		SearchVidSegsResponse resp2 = new SearchVidSegsResponse(null, 400);
		Assert.assertEquals(resp2.toString(), "No Vid Segs Found");
	}

}
