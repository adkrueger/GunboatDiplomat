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
import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.AppendVidSegRequest;
import gunboatdiplomat.http.AppendVidSegResponse;
import gunboatdiplomat.http.CreatePlaylistRequest;
import gunboatdiplomat.http.CreatePlaylistResponse;
import gunboatdiplomat.model.Playlist;
import gunboatdiplomat.model.VidSeg;



public class AppendVidSegToPlaylistHandlerTest extends LambdaTest{
	
	public PlaylistDAO playlistDAO = new PlaylistDAO();
	public VideoSegmentDAO vsDAO = new VideoSegmentDAO();
	
	@Test
	public void testAppendVidSegToPlaylistHandlerTest() throws Exception {
		VidSeg vs1 = new VidSeg("", "", "", 0, 0);
		VidSeg vs2 = new VidSeg("testingAppendVidSegHandler1", "This is a test 2", "President Barack Obama", 1, 0);
		VidSeg vs3 = new VidSeg("testingAppendVidSegHandler2", "This is a test 1", "President Donald Trump", 1, 0);
		
		List<VidSeg> listSol = new ArrayList<>();
		//listSol.add(vs1);
		listSol.add(vs2);
		listSol.add(vs3);
		
		vsDAO.addVidSeg(vs2);
		vsDAO.addVidSeg(vs3);
	
		
		
		
		Playlist pl = new Playlist("testingAppendVidSegPlaylistLambda");
		
		
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
		
		
		List<VidSeg> listRet = playlistDAO.getVideoSegInPlaylist(pl.name);
		
		assertEquals(appendResp1.errorCode, 200); // means the test has passed. 
		assertEquals(appendResp2.errorCode, 200); // means the test has passed. 
		
		for (int i = 0; i < listSol.size(); i++) {
			if(listRet.get(i).id != null && !listRet.get(i).id.equals("")) {
				assertTrue(listSol.get(i).id.equals(listRet.get(i).id));
			}
		}
		
		
		playlistDAO.deletePlaylist(pl.name);
		
		vsDAO.deleteVidSeg(vs2.id);
		vsDAO.deleteVidSeg(vs3.id);
		
	}
	
	
}
