package gunboatdiplomat.HandlerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

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
	public void testAppendVidSegToPlaylistHandlerTest() throws Exception {
		VidSeg vs1 = new VidSeg("", "", "", 0, 0);
		VidSeg vs2 = new VidSeg("testingRemoveVidSegHandler1", "This is a test 1", "Spongebob Squarepants", 1, 0);
		VidSeg vs3 = new VidSeg("testingRemoveVidSegHandler2", "This is a test 2", "Patrick Star", 1, 0);
		VidSeg vs4 = new VidSeg("testingRemoveVidSegHandler3", "This is a test 3", "Sandy Squirrel", 1, 0);

		vsDAO.addVidSeg(vs2);
		vsDAO.addVidSeg(vs3);
		vsDAO.addVidSeg(vs4);
	
		Playlist pl = new Playlist("testingRemoveVidSegPlaylistLambda");
		
		//Create a Playlist
		CreatePlaylistRequest createReq = new CreatePlaylistRequest(pl.name);
		CreatePlaylistResponse createResp = new CreatePlaylistHandler().handleRequest(createReq, createContext("Created Playlist"));
		
		//Makes sure the correct playlistName is pulled. 
		assertEquals(pl.name, createResp.response);
			
		//Append to this segment.
		AppendVidSegRequest appendReq1 = new AppendVidSegRequest(pl.name, vs2.id);
		AppendVidSegResponse appendResp1 = new AppendVidSegToPlaylistHandler().handleRequest(appendReq1, createContext("Appended"));
		AppendVidSegRequest appendReq2 = new AppendVidSegRequest(pl.name, vs3.id);
		AppendVidSegResponse appendResp2 = new AppendVidSegToPlaylistHandler().handleRequest(appendReq2, createContext("Appended"));
		AppendVidSegRequest appendReq3 = new AppendVidSegRequest(pl.name, vs4.id);
		AppendVidSegResponse appendResp3 = new AppendVidSegToPlaylistHandler().handleRequest(appendReq3, createContext("Appended"));

		assertEquals(appendResp1.errorCode, 200); // means the test has passed. 
		assertEquals(appendResp2.errorCode, 200); // means the test has passed. 
		assertEquals(appendResp3.errorCode, 200);
	
		
/*		
		//Attempt to remove a random segment.
		RemoveVidSegRequest removeReq1 = new RemoveVidSegRequest(pl.name, 0);
		RemoveVidSegResponse removeResp1 = new RemoveVidSegFromPlaylistHandler().handleRequest(removeReq1, createContext("Removed"));
		RemoveVidSegRequest removeReq2 = new RemoveVidSegRequest(pl.name, 0);
		RemoveVidSegResponse removeResp2 = new RemoveVidSegFromPlaylistHandler().handleRequest(removeReq2, createContext("Removed"));
		RemoveVidSegRequest removeReq3 = new RemoveVidSegRequest(pl.name, 0);
		RemoveVidSegResponse removeResp3 = new RemoveVidSegFromPlaylistHandler().handleRequest(removeReq3, createContext("Removed"));
		RemoveVidSegRequest removeReq4 = new RemoveVidSegRequest(pl.name, 0);
		RemoveVidSegResponse removeResp4 = new RemoveVidSegFromPlaylistHandler().handleRequest(removeReq4, createContext("Removed"));
		
		assertEquals(removeResp1.errorCode, 200);
		assertEquals(removeResp2.errorCode, 200);
		assertEquals(removeResp3.errorCode, 200);
		assertEquals(removeResp4.errorCode, 200);
		
		playlistDAO.deletePlaylist(pl.name);
		
*/		
		
	}
	
	
}
