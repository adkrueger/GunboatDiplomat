package gunboatdiplomat.HandlerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

		vsDAO.addVidSeg(vs1);
		vsDAO.addVidSeg(vs2);
		vsDAO.addVidSeg(vs3);

		Playlist pl = new Playlist("testingRemoveVidSeg");

		//Create a Playlist
		CreatePlaylistRequest createReq = new CreatePlaylistRequest(pl.name);
		CreatePlaylistResponse createResp = new CreatePlaylistHandler().handleRequest(createReq, createContext("create playlist"));

		//Makes sure the correct playlistName is created. 
		assertEquals(pl.name, createResp.response);

		//Append vidsegs to the playlist.
		AppendVidSegRequest appendReq1 = new AppendVidSegRequest(pl.name, vs1.id);
		AppendVidSegResponse appendResp1 = new AppendVidSegToPlaylistHandler().handleRequest(appendReq1, createContext("appending"));
		AppendVidSegRequest appendReq2 = new AppendVidSegRequest(pl.name, vs2.id);
		AppendVidSegResponse appendResp2 = new AppendVidSegToPlaylistHandler().handleRequest(appendReq2, createContext("appending"));
		AppendVidSegRequest appendReq3 = new AppendVidSegRequest(pl.name, vs3.id);
		AppendVidSegResponse appendResp3 = new AppendVidSegToPlaylistHandler().handleRequest(appendReq3, createContext("appending"));

		assertEquals(appendResp1.playlistName, pl.name);		// check that everything was appended properly
		assertEquals(appendResp2.playlistName, pl.name);
		assertEquals(appendResp3.playlistName, pl.name);
		
		//Attempt to remove a random segment.
		RemoveVidSegRequest removeReq1 = new RemoveVidSegRequest(pl.name, 1);	// testingRemoveVidSegHandler2
		RemoveVidSegResponse removeResp1 = new RemoveVidSegFromPlaylistHandler().handleRequest(removeReq1, createContext("removing"));
		RemoveVidSegRequest removeReq2 = new RemoveVidSegRequest(pl.name, 0);	// testingRemoveVidSegHandler1
		RemoveVidSegResponse removeResp2 = new RemoveVidSegFromPlaylistHandler().handleRequest(removeReq2, createContext("removing"));
		RemoveVidSegRequest removeReq3 = new RemoveVidSegRequest(pl.name, 0);	// testingRemoveVidSegHandler3
		RemoveVidSegResponse removeResp3 = new RemoveVidSegFromPlaylistHandler().handleRequest(removeReq3, createContext("removing"));
		RemoveVidSegRequest removeReq4 = new RemoveVidSegRequest(pl.name, 0);	// none; playlist is empty
		RemoveVidSegResponse removeResp4 = new RemoveVidSegFromPlaylistHandler().handleRequest(removeReq4, createContext("removing"));

		assertEquals(removeResp1.errorCode, 200);
		assertEquals(removeResp2.errorCode, 200);
		assertEquals(removeResp3.errorCode, 200);
		assertEquals(removeResp4.errorCode, 403);	// should fail because there's nothing left to remove

		assertTrue(playlistDAO.getPlaylistVidSeg("testingRemoveVidSeg").size() == 0);

		playlistDAO.deletePlaylist(pl.name);

		vsDAO.deleteVidSeg("testingRemoveVidSegHandler1");
		vsDAO.deleteVidSeg("testingRemoveVidSegHandler2");
		vsDAO.deleteVidSeg("testingRemoveVidSegHandler3");
	}


}
