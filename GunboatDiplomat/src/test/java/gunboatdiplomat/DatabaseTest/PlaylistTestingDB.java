package gunboatdiplomat.DatabaseTest;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import gunboatdiplomat.model.VidSeg;
import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.db.VideoSegmentDAO;
public class PlaylistTestingDB {

	public PlaylistDAO playlistDAO = new PlaylistDAO();
	public VideoSegmentDAO vsDAO = new VideoSegmentDAO();

	@Test
	public void testGetAllVidSeg() throws Exception {

		// All the video segments that should be returned.
		List<VidSeg> lsSolution = new ArrayList<>();
		VidSeg vs1 = new VidSeg("testingGetALLVidSeg1", "Spock", "A most facinating thing happened.", 1, 0);
		VidSeg vs2 = new VidSeg("testingGetALLVidSeg2", "James T. Kirk", "More like love.", 1, 0);
		VidSeg vs3 = new VidSeg("testingGetALLVidSeg3", "Leonard McCoy", "Now you must want the child!", 1, 0);
		VidSeg vs4 = new VidSeg("testingGetALLVidSeg4", "Spock", "That should prove very interesting.", 1, 0);
		VidSeg vs5 = new VidSeg("testingGetALLVidSeg5", "Leonard McCoy","You touch it, her nearest male relative will have to try to kill you.", 1, 0);
		VidSeg vs6 = new VidSeg("testingGetALLVidSeg6", "Amanda Grayson","Well it's sort of a fat teddy bear.", 1, 0);

		vsDAO.addVidSeg(vs1);
		vsDAO.addVidSeg(vs2);
		vsDAO.addVidSeg(vs3);
		vsDAO.addVidSeg(vs4);
		vsDAO.addVidSeg(vs5);
		vsDAO.addVidSeg(vs6);

		playlistDAO.addVidSegToPlaylist("TestingGetAllVidSeg", "testingGetALLVidSeg1");
		playlistDAO.addVidSegToPlaylist("TestingGetAllVidSeg", "testingGetALLVidSeg2");
		playlistDAO.addVidSegToPlaylist("TestingGetAllVidSeg", "testingGetALLVidSeg3");
		playlistDAO.addVidSegToPlaylist("TestingGetAllVidSeg", "testingGetALLVidSeg4");
		playlistDAO.addVidSegToPlaylist("TestingGetAllVidSeg", "testingGetALLVidSeg5");
		playlistDAO.addVidSegToPlaylist("TestingGetAllVidSeg", "testingGetALLVidSeg6");

		lsSolution.add(vs1);
		lsSolution.add(vs2);
		lsSolution.add(vs3);
		lsSolution.add(vs4);
		lsSolution.add(vs5);
		lsSolution.add(vs6);

		// Creating the answerKey
		List<VidSeg> lsReturned = new ArrayList<VidSeg>();

		// Returns a list of video clips.
		lsReturned = playlistDAO.getPlaylistVidSeg("TestingGetALLVidSeg");

		vsDAO.deleteVidSeg("testingGetALLVidSeg1");
		vsDAO.deleteVidSeg("testingGetALLVidSeg2");
		vsDAO.deleteVidSeg("testingGetALLVidSeg3");
		vsDAO.deleteVidSeg("testingGetALLVidSeg4");
		vsDAO.deleteVidSeg("testingGetALLVidSeg5");
		vsDAO.deleteVidSeg("testingGetALLVidSeg6");

		playlistDAO.deletePlaylist("TestingGetAllVidSeg");

		for (int i = 0; i < lsSolution.size(); i++) {
			assertTrue(lsSolution.get(i).equals(lsReturned.get(i)));

		}



	}

	@Test
	public void testGetAllPlaylists() throws Exception {

		HashMap<String, List<VidSeg>> solution = new HashMap<>();
		List<VidSeg> sol1 = new ArrayList<>();

		VidSeg vs1 = new VidSeg("testingGetALLPlaylist1", "Spock", "A most fascinating thing happened.", 1, 0);
		VidSeg vs2 = new VidSeg("testingGetALLPlaylist2", "James T. Kirk", "More like love.", 1, 0);
		VidSeg vs3 = new VidSeg("testingGetALLPlaylist3", "Leonard McCoy", "Now you must want the child!", 1, 0);
		VidSeg vs4 = new VidSeg("testingGetALLPlaylist4", "Spock", "That should prove very interesting.", 1, 0);
		VidSeg vs5 = new VidSeg("testingGetALLPlaylist5", "Leonard McCoy","You touch it, her nearest male relative will have to try to kill you.", 1, 0);
		VidSeg vs6 = new VidSeg("testingGetALLPlaylist6", "Amanda Grayson","Well it's sort of a fat teddy bear.", 1, 0);

		vsDAO.addVidSeg(vs1);
		vsDAO.addVidSeg(vs2);
		vsDAO.addVidSeg(vs3);
		vsDAO.addVidSeg(vs4);
		vsDAO.addVidSeg(vs5);
		vsDAO.addVidSeg(vs6);

		playlistDAO.addVidSegToPlaylist("TestingGetAllPlaylist", "testingGetALLPlaylist1");
		playlistDAO.addVidSegToPlaylist("TestingGetAllPlaylist", "testingGetALLPlaylist2");
		playlistDAO.addVidSegToPlaylist("TestingGetAllPlaylist", "testingGetALLPlaylist3");
		playlistDAO.addVidSegToPlaylist("TestingGetAllPlaylist", "testingGetALLPlaylist4");
		playlistDAO.addVidSegToPlaylist("TestingGetAllPlaylist", "testingGetALLPlaylist5");
		playlistDAO.addVidSegToPlaylist("TestingGetAllPlaylist", "testingGetALLPlaylist6");

		sol1.add(vs1);
		sol1.add(vs2);
		sol1.add(vs3);
		sol1.add(vs4);
		sol1.add(vs5);
		sol1.add(vs6);

		solution.put("TestingGetAllPlaylist", sol1);

		HashMap<String, List<VidSeg>> allPlaylists = playlistDAO.getAllPlaylists();

		playlistDAO.deletePlaylist("TestingGetAllPlaylist");

		for(HashMap.Entry<String, List<VidSeg>> retList : allPlaylists.entrySet()) {
			solution.entrySet().contains(retList);
		}

		vsDAO.deleteVidSeg("testingGetALLPlaylist1");
		vsDAO.deleteVidSeg("testingGetALLPlaylist2");
		vsDAO.deleteVidSeg("testingGetALLPlaylist3");
		vsDAO.deleteVidSeg("testingGetALLPlaylist4");
		vsDAO.deleteVidSeg("testingGetALLPlaylist5");
		vsDAO.deleteVidSeg("testingGetALLPlaylist6");
		
	}

	@Test
	public void testAppendVidSeg() throws Exception {
		String playlistName = "Funny Clips";

		playlistDAO.addVidSegToPlaylist(playlistName, "testAppendVidSeg1");
		playlistDAO.addVidSegToPlaylist(playlistName, "testAppendVidSeg2");
		playlistDAO.addVidSegToPlaylist(playlistName, "testAppendVidSeg3");

		VideoSegmentDAO vsDAO = new VideoSegmentDAO();		// need to add to video segment table for playlist to find it
		vsDAO.addVidSeg(new VidSeg("testAppendVidSeg1", "", "", 1, 0));
		vsDAO.addVidSeg(new VidSeg("testAppendVidSeg2", "", "", 1, 0));
		vsDAO.addVidSeg(new VidSeg("testAppendVidSeg3", "", "", 1, 0));
		
		List<VidSeg> allVSInPlaylist = playlistDAO.getPlaylistVidSeg(playlistName);
		
		assertEquals("testAppendVidSeg1", allVSInPlaylist.get(0).id);
		assertEquals("testAppendVidSeg2", allVSInPlaylist.get(1).id);
		assertEquals("testAppendVidSeg3", allVSInPlaylist.get(2).id);
		
		vsDAO.deleteVidSeg("testAppendVidSeg1");
		vsDAO.deleteVidSeg("testAppendVidSeg2");
		vsDAO.deleteVidSeg("testAppendVidSeg3");
		playlistDAO.deletePlaylist("Funny Clips");
		
	}


	@Test
	public void testDeletePlaylist() throws Exception {

		//Checking to see if its there.

		assertEquals(playlistDAO.deletePlaylist("Funny Clips"), true);

	}

	@Test
	public void testGetVideoSegInPlaylist() throws Exception{

		List<VidSeg> vsSol = new ArrayList<>();

		VidSeg vs1 = new VidSeg("testingGetVSPlaylist1", "Spock", "A most fascinating thing happened.", 1, 0);
		VidSeg vs2 = new VidSeg("testingGetVSPlaylist2", "James T. Kirk", "More like love.", 1, 0);
		VidSeg vs3 = new VidSeg("testingGetVSPlaylist3", "Leonard McCoy", "Now you must want the child!", 1, 0);
		VidSeg vs4 = new VidSeg("testingGetVSPlaylist4", "Spock", "That should prove very interesting.", 1, 0);
		VidSeg vs5 = new VidSeg("testingGetVSPlaylist5", "Leonard McCoy","You touch it, her nearest male relative will have to try to kill you.", 1, 0);
		VidSeg vs6 = new VidSeg("testingGetVSPlaylist6", "Amanda Grayson","Well it's sort of a fat teddy bear.", 1, 0);

		vsSol.add(vs1);
		vsSol.add(vs2);
		vsSol.add(vs3);
		vsSol.add(vs4);
		vsSol.add(vs5);
		vsSol.add(vs6);

		vsDAO.addVidSeg(vs1);
		vsDAO.addVidSeg(vs2);
		vsDAO.addVidSeg(vs3);
		vsDAO.addVidSeg(vs4);
		vsDAO.addVidSeg(vs5);
		vsDAO.addVidSeg(vs6);

		playlistDAO.addVidSegToPlaylist("testingVSPlaylist", "testingGetVSPlaylist1");
		playlistDAO.addVidSegToPlaylist("testingVSPlaylist", "testingGetVSPlaylist2");
		playlistDAO.addVidSegToPlaylist("testingVSPlaylist", "testingGetVSPlaylist3");
		playlistDAO.addVidSegToPlaylist("testingVSPlaylist", "testingGetVSPlaylist4");
		playlistDAO.addVidSegToPlaylist("testingVSPlaylist", "testingGetVSPlaylist5");
		playlistDAO.addVidSegToPlaylist("testingVSPlaylist", "testingGetVSPlaylist6");

		List<VidSeg> vsRet = playlistDAO.getVideoSegInPlaylist("testingVSPlaylist");

		playlistDAO.deletePlaylist("testingVSPlaylist");
		vsDAO.deleteVidSeg("testingGetVSPlaylist1");
		vsDAO.deleteVidSeg("testingGetVSPlaylist2");
		vsDAO.deleteVidSeg("testingGetVSPlaylist3");
		vsDAO.deleteVidSeg("testingGetVSPlaylist4");
		vsDAO.deleteVidSeg("testingGetVSPlaylist5");
		vsDAO.deleteVidSeg("testingGetVSPlaylist6");


		for(int i = 0; i < vsSol.size(); i++) {
			assertTrue(vsSol.get(i).equals(vsRet.get(i)));
		}

	}



}