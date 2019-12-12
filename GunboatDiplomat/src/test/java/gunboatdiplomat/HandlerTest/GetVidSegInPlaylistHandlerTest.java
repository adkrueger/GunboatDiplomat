package gunboatdiplomat.HandlerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import gunboatdiplomat.GetVidSegInPlaylistHandler;
import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.GetVidSegInPlaylistRequest;
import gunboatdiplomat.http.GetVidSegInPlaylistResponse;
import gunboatdiplomat.model.VidSeg;

public class GetVidSegInPlaylistHandlerTest extends LambdaTest{
	public PlaylistDAO plDAO = new PlaylistDAO();
	public VideoSegmentDAO vsDAO = new VideoSegmentDAO();

	@Test
	public void testGetVidSegInPlaylistHandlerStatusCode() throws Exception {

		plDAO.createPlaylist("ChickenNuggetsRock");


		VidSeg vs = new VidSeg("testHandlerPlaylist", "CharacterHere", "QuoteHere", 1, 0);

		vsDAO.addVidSeg(vs);
		plDAO.addVidSegToPlaylist("ChickenNuggestsRock", vs.id);
		GetVidSegInPlaylistRequest createReq = new GetVidSegInPlaylistRequest("ChickenNuggetsRock");
		GetVidSegInPlaylistResponse response = new GetVidSegInPlaylistHandler().handleRequest(createReq, createContext(vs.id + " Has Been Pulled"));

		assertEquals(response.statusCode, 200);

		plDAO.deletePlaylist("ChickenNuggestsRock");
		vsDAO.deleteVidSeg(vs.id);

	}

	@Test
	public void testGetVidSegInPlaylistHandler() throws Exception {
		plDAO.createPlaylist("ChickenNuggetsRock");


		VidSeg vs = new VidSeg("testHandlerPlaylist", "CharacterHere", "QuoteHere", 1, 0);

		vsDAO.addVidSeg(vs);
		plDAO.addVidSegToPlaylist("ChickenNuggetsRock", vs.id);
		GetVidSegInPlaylistRequest createReq = new GetVidSegInPlaylistRequest("ChickenNuggetsRock");
		GetVidSegInPlaylistResponse response = new GetVidSegInPlaylistHandler().handleRequest(createReq, createContext(vs.id + " Has Been Pulled"));

		List<VidSeg> ls = plDAO.getVideoSegInPlaylist("ChickenNuggetsRock");
		
		assertTrue(ls.get(0).id.equals(vs.id));
		

		plDAO.deletePlaylist("ChickenNuggetsRock");
		vsDAO.deleteVidSeg(vs.id);
	}

}
