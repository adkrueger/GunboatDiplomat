package gunboatdiplomat.HandlerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.MarkVidSegHandler;
import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.MarkVidSegRequest;
import gunboatdiplomat.http.MarkVidSegResponse;
import gunboatdiplomat.model.VidSeg;

public class MarkVidSegHandlerTest extends LambdaTest{
	
	public VideoSegmentDAO vsDAO = new VideoSegmentDAO();
	
	@Test
	public void testMarkVidSegHandler() throws Exception{
		
		VidSeg vsCreated = new VidSeg("TestingMarkVidSegHandler", "AshwinSmellsBad", "Ashwin", 1, 0);
		vsDAO.addVidSeg(vsCreated);
		
		VidSeg vsSol = new VidSeg("TestingMarkVidSegHandler", "AshwinSmellsBad", "Ashwin", 1, 1);		// ashwin you smell fine bb
		
		MarkVidSegRequest createReq = new MarkVidSegRequest(vsCreated.id);
		MarkVidSegResponse responseReq = new MarkVidSegHandler().handleRequest(createReq, createContext("Marked"));
		responseReq.toString();
		
		assertEquals(responseReq.errorCode, 200);
		
		
		VidSeg vs = vsDAO.getVidSeg(vsCreated.id);
		
		assertTrue(vs.equals(vsSol));
		vsDAO.deleteVidSeg(vsCreated.id);
	}
	
	@Test
	public void testMarkVidSegHandlerFalse(){
		MarkVidSegResponse resp = new MarkVidSegResponse("This is a test", 403, "This should fail");
		resp.toString();
		
	}
}
