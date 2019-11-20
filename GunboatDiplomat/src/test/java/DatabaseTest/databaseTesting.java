package DatabaseTest;

import static org.junit.Assert.*;

import org.junit.Test;

import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.model.VidSeg;

public class databaseTesting {

	@Test
	public void testFindVideoSeg() throws Exception{
		VideoSegmentDAO vidsegDAO = new VideoSegmentDAO();

		VidSeg vs1 = new VidSeg("5ecb7cb5-115a-4114-9865-bec03f4b2f5a", "Leonard McCoy", "Death by natural causes.", 2, 12, true, false);
		VidSeg vs2;
		
		vs2 = vidsegDAO.getVidSeg("5ecb7cb5-115a-4114-9865-bec03f4b2f5a");

		assertTrue(vs1.equals(vs2));

	}

}
