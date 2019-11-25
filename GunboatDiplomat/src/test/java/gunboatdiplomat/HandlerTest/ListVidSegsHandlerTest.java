package gunboatdiplomat.HandlerTest;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.ListVidSegsHandler;
import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.AllVidSegsResponse;
import gunboatdiplomat.model.VidSeg;

public class ListVidSegsHandlerTest extends LambdaTest{
	
	@Test
	public void testgetList() throws Exception {
		ListVidSegsHandler handler = new ListVidSegsHandler();
		
		AllVidSegsResponse response = handler.handleRequest(null, createContext("list"));
		
		List<VidSeg> rdsList = handler.getVidSegsFromRDS();
		List<VidSeg> s3List = handler.getVidSegsFromS3();
		VideoSegmentDAO dao = new VideoSegmentDAO();

		for(VidSeg vs : s3List) {
			System.out.println(vs.id);
			System.out.println(dao.getVidSeg(vs.id));
			Assert.assertTrue(rdsList.contains(dao.getVidSeg(vs.id)));
		}
		
		Assert.assertEquals(200, response.statusCode);
		
	}
	
}
