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

public class UnmarkVidSegHandlerTest extends LambdaTest{
	
	public VideoSegmentDAO vsDAO = new VideoSegmentDAO();
	
	@Test
	public void testMarkVidSegHandler() throws Exception{
		
		VidSeg vsCreated = new VidSeg("TestingMarkVidSegHandler", "AshwinSmellsBad", "Ashwin", 1, 1);
		vsDAO.addVidSeg(vsCreated);
		
		VidSeg vsSol = new VidSeg("TestingMarkVidSegHandler", "AshwinSmellsBad", "Ashwin", 1, 0);
		
		UnmarkVidSegRequest createReq = new UnmarkVidSegRequest(vsCreated.id);
		UnmarkVidSegResponse responseReq = new UnmarkVidSegHandler().handleRequest(createReq, createContext("Marked"));
		
		assertEquals(responseReq.errorCode, 200);
		
		
		VidSeg vs = vsDAO.getVidSeg(vsCreated.id);
		
		assertTrue(vs.equals(vsSol));
		vsDAO.deleteVidSeg(vsCreated.id);
	}

}
