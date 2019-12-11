package gunboatdiplomat.HandlerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import gunboatdiplomat.AppendVidSegToPlaylistHandler;
import gunboatdiplomat.CreatePlaylistHandler;
import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.RemoveVidSegFromPlaylistHandler;
import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.AppendVidSegRequest;
import gunboatdiplomat.http.AppendVidSegResponse;
import gunboatdiplomat.http.CreatePlaylistRequest;
import gunboatdiplomat.http.CreatePlaylistResponse;
import gunboatdiplomat.http.RemoveVidSegRequest;
import gunboatdiplomat.http.RemoveVidSegResponse;
import gunboatdiplomat.model.Playlist;
import gunboatdiplomat.model.VidSeg;



public class RemoveVidSegFromPlaylistHandlerTest extends LambdaTest{

	public PlaylistDAO playlistDAO = new PlaylistDAO();
	public VideoSegmentDAO vsDAO = new VideoSegmentDAO();

	@Test
	public void testRemoveVidSegFromPlaylist() throws Exception {
		VidSeg vs1 = new VidSeg("testingRemoveVidSegHandler1", "Spongebob Squarepants", "this is a test", 1, 0);
		VidSeg vs2 = new VidSeg("testingRemoveVidSegHandler2", "Patrick Star", "This is a test 2", 1, 0);
		VidSeg vs3 = new VidSeg("testingRemoveVidSegHandler3", "Sandy Squirrel", "This is a test 3", 1, 0);
		VidSeg vs4 = new VidSeg("testingRemoveVidSegHandler4", "Mr. Krabs", "gimme money", 1, 0);
		vsDAO.addVidSeg(vs1);
		vsDAO.addVidSeg(vs2);
		vsDAO.addVidSeg(vs3);
		vsDAO.addVidSeg(vs4);


		//Create a Playlist
		CreatePlaylistRequest createReq = new CreatePlaylistRequest("testingRemoveVidSeg");
		CreatePlaylistResponse createResp = new CreatePlaylistHandler().handleRequest(createReq, createContext("create playlist"));

		//Makes sure the correct playlistName is created. 
		assertEquals("testingRemoveVidSeg", createResp.response);

		//Append vidsegs to the playlist.
		AppendVidSegResponse appendResp1 = new AppendVidSegToPlaylistHandler().handleRequest(new AppendVidSegRequest("testingRemoveVidSeg", vs1.id), createContext("appending"));
		AppendVidSegResponse appendResp2 = new AppendVidSegToPlaylistHandler().handleRequest(new AppendVidSegRequest("testingRemoveVidSeg", vs2.id), createContext("appending"));
		AppendVidSegResponse appendResp3 = new AppendVidSegToPlaylistHandler().handleRequest(new AppendVidSegRequest("testingRemoveVidSeg", vs3.id), createContext("appending"));
		AppendVidSegResponse appendResp4 = new AppendVidSegToPlaylistHandler().handleRequest(new AppendVidSegRequest("testingRemoveVidSeg", vs4.id), createContext("appending"));

		
		assertEquals(appendResp1.playlistName, "testingRemoveVidSeg");		// check that everything was appended properly
		assertEquals(appendResp2.playlistName, "testingRemoveVidSeg");
		assertEquals(appendResp3.playlistName, "testingRemoveVidSeg");
		assertEquals(appendResp4.playlistName, "testingRemoveVidSeg");
		
		
		//Attempt to remove a random segment.
		RemoveVidSegResponse removeResp1 = new RemoveVidSegFromPlaylistHandler().handleRequest(new RemoveVidSegRequest("testingRemoveVidSeg", 2), createContext("removing"));	// testingRemoveVidSegHandler3
		assertEquals(removeResp1.errorCode, 200);
		List<VidSeg> currPlaylist = playlistDAO.getPlaylistVidSeg("testingRemoveVidSeg");
		System.out.println(currPlaylist);
		assertTrue(currPlaylist.get(0).id.equals("testingRemoveVidSegHandler1"));
		assertTrue(currPlaylist.get(1).id.equals("testingRemoveVidSegHandler2"));
		assertTrue(currPlaylist.get(2).id.equals("testingRemoveVidSegHandler4"));
		
		RemoveVidSegResponse removeResp2 = new RemoveVidSegFromPlaylistHandler().handleRequest(new RemoveVidSegRequest("testingRemoveVidSeg", 1), createContext("removing"));	// testingRemoveVidSegHandler2
		assertEquals(removeResp2.errorCode, 200);
		currPlaylist = playlistDAO.getPlaylistVidSeg("testingRemoveVidSeg");
		System.out.println(currPlaylist);
		assertTrue(currPlaylist.get(0).id.equals("testingRemoveVidSegHandler1"));
		assertTrue(currPlaylist.get(1).id.equals("testingRemoveVidSegHandler4"));
		
		RemoveVidSegResponse removeResp3 = new RemoveVidSegFromPlaylistHandler().handleRequest(new RemoveVidSegRequest("testingRemoveVidSeg", 1), createContext("removing"));	// testingRemoveVidSegHandler4
		assertEquals(removeResp3.errorCode, 200);
		currPlaylist = playlistDAO.getPlaylistVidSeg("testingRemoveVidSeg");
		System.out.println(currPlaylist);
		assertTrue(currPlaylist.get(0).id.equals("testingRemoveVidSegHandler1"));
		
		RemoveVidSegResponse removeResp4 = new RemoveVidSegFromPlaylistHandler().handleRequest(new RemoveVidSegRequest("testingRemoveVidSeg", 0), createContext("removing"));	// testingRemoveVidSegHandler1
		assertEquals(removeResp4.errorCode, 200);		
		currPlaylist = playlistDAO.getPlaylistVidSeg("testingRemoveVidSeg");
		assertTrue(currPlaylist.size() == 0);
		
		RemoveVidSegResponse removeResp5 = new RemoveVidSegFromPlaylistHandler().handleRequest(new RemoveVidSegRequest("testingRemoveVidSeg", 0), createContext("removing"));	// testingRemoveVidSegHandler1
		assertEquals(removeResp5.errorCode, 403);		// should "fail" because there's nothing left to remove

		playlistDAO.deletePlaylist("testingRemoveVidSeg");

		vsDAO.deleteVidSeg("testingRemoveVidSegHandler1");
		vsDAO.deleteVidSeg("testingRemoveVidSegHandler2");
		vsDAO.deleteVidSeg("testingRemoveVidSegHandler3");
		vsDAO.deleteVidSeg("testingRemoveVidSegHandler4");
	}


}
