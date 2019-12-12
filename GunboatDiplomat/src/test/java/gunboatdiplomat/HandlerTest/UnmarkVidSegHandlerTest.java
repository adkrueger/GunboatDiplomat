package gunboatdiplomat.HandlerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.MarkVidSegHandler;
import gunboatdiplomat.UnmarkVidSegHandler;
import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.UnmarkVidSegRequest;
import gunboatdiplomat.http.UnmarkVidSegResponse;
import gunboatdiplomat.model.VidSeg;
import junit.framework.Assert;

public class UnmarkVidSegHandlerTest extends LambdaTest{
	
	public VideoSegmentDAO vsDAO = new VideoSegmentDAO();
	
	@Test
	public void testMarkVidSegHandler() throws Exception{
		
		VidSeg vsCreated = new VidSeg("TestingMarkVidSegHandler", "AshwinSmellsBad", "Ashwin", 1, 1);
		vsDAO.addVidSeg(vsCreated);
		
		VidSeg vsSol = new VidSeg("TestingMarkVidSegHandler", "AshwinSmellsBad", "Ashwin", 1, 0);
		
		UnmarkVidSegRequest createReq = new UnmarkVidSegRequest(vsCreated.id);
		UnmarkVidSegResponse responseReq = new UnmarkVidSegHandler().handleRequest(createReq, createContext("Unmarked"));
		
		assertEquals(responseReq.errorCode, 200);
		assertTrue(responseReq.toString().equals(vsCreated.id + "has been marked successfully"));
		
		
		VidSeg vs = vsDAO.getVidSeg(vsCreated.id);
		
		assertTrue(vs.equals(vsSol));
		vsDAO.deleteVidSeg(vsCreated.id);
	}
	
	@Test 
	public void testFailUnmarkVidSegHandler() {
		
		//Going to try to find a vid seg that doesnt exist. 
		UnmarkVidSegRequest createReq = new UnmarkVidSegRequest("This doesnt exist");
		UnmarkVidSegResponse responseReq = new UnmarkVidSegHandler().handleRequest(createReq, createContext("Not Unmarked"));

		
		assertTrue((!(responseReq.errorCode == 400)));
	}
	
	@Test
	public void testFailErrorCodeUnmarkVidSegHandler() {
		UnmarkVidSegResponse responseReq = new UnmarkVidSegResponse("This is a test", 403, "The unmark has failed");
		
		assertEquals(responseReq.errorCode, 403);
		
		assertTrue(responseReq.toString().equals("This is a testhas not been marked successfully --> Error Code: 403"));
	}

}
