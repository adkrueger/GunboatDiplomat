package gunboatdiplomat.HandlerTest;

import java.util.List;

import org.junit.Test;
import org.junit.Assert;

import gunboatdiplomat.GetPublicVidSegsHandler;
import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.GetPublicVidSegsResponse;
import gunboatdiplomat.model.VidSeg;

public class GetPublicVidSegsHandlerTest extends LambdaTest {

	VideoSegmentDAO dao = new VideoSegmentDAO();
	
	@Test
	public void testGetPublicVidSegsHandler() throws Exception {
		
		VidSeg vs1 = new VidSeg("markedTestSeg1", "aaron", "hi im aaron", 1, 1);
		VidSeg vs2 = new VidSeg("markedTestSeg2", "obama", "welcome to the white house aaron", 0, 1);
		VidSeg vs3 = new VidSeg("markedTestSeg3", "aaron", "thanks obama", 1, 1);
		VidSeg vs4 = new VidSeg("markedTestSeg4", "obama", "no problem buddy", 1, 0);
		
		dao.addVidSeg(vs1);
		dao.addVidSeg(vs2);
		dao.addVidSeg(vs3);
		dao.addVidSeg(vs4);
		
		GetPublicVidSegsHandler handler = new GetPublicVidSegsHandler();
		GetPublicVidSegsResponse resp = handler.handleRequest(null, createContext("list public segs"));
		List<VidSeg> retList = resp.segments;
		
		for(VidSeg vs : retList) {
			Assert.assertTrue(vs.isMarked == 1);
		}
		
		Assert.assertTrue(retList.contains(vs1));
		Assert.assertTrue(retList.contains(vs2));
		Assert.assertTrue(retList.contains(vs3));
		Assert.assertFalse(retList.contains(vs4));
		
		dao.deleteVidSeg("markedTestSeg1");
		dao.deleteVidSeg("markedTestSeg2");
		dao.deleteVidSeg("markedTestSeg3");
		dao.deleteVidSeg("markedTestSeg4");
	}
	
}
