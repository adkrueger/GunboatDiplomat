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
		lsReturned = playlistDAO.getVideoSegInPlaylist("TestingGetAllVidSeg");

		vsDAO.deleteVidSeg("testingGetALLVidSeg1");
		vsDAO.deleteVidSeg("testingGetALLVidSeg2");
		vsDAO.deleteVidSeg("testingGetALLVidSeg3");
		vsDAO.deleteVidSeg("testingGetALLVidSeg4");
		vsDAO.deleteVidSeg("testingGetALLVidSeg5");
		vsDAO.deleteVidSeg("testingGetALLVidSeg6");

		playlistDAO.deletePlaylist("TestingGetAllVidSeg");

		for (int i = 0; i < lsSolution.size(); i++) {
			assertTrue(lsSolution.get(i).id.equals(lsReturned.get(i).id));

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

		playlistDAO.createPlaylist("TestingGetAllPlaylist");

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

		vsDAO.deleteVidSeg("testingGetALLPlaylist1");
		vsDAO.deleteVidSeg("testingGetALLPlaylist2");
		vsDAO.deleteVidSeg("testingGetALLPlaylist3");
		vsDAO.deleteVidSeg("testingGetALLPlaylist4");
		vsDAO.deleteVidSeg("testingGetALLPlaylist5");
		vsDAO.deleteVidSeg("testingGetALLPlaylist6");

		for(int i = 0; i < solution.get("TestingGetAllPlaylist").size(); i++) {
			assertTrue(allPlaylists.get("TestingGetAllPlaylist").get(i).id.equals(solution.get("TestingGetAllPlaylist").get(i).id));
		}
	}

	@Test
	public void testAppendVidSeg() throws Exception {
		String playlistName = "Funny Clips";

		VideoSegmentDAO vsDAO = new VideoSegmentDAO();		// need to add to video segment table for playlist to find it
		vsDAO.addVidSeg(new VidSeg("testAppendVidSeg1", "", "", 1, 0));
		vsDAO.addVidSeg(new VidSeg("testAppendVidSeg2", "", "", 1, 0));
		vsDAO.addVidSeg(new VidSeg("testAppendVidSeg3", "", "", 1, 0));

		playlistDAO.createPlaylist(playlistName);

		playlistDAO.addVidSegToPlaylist(playlistName, "testAppendVidSeg1");
		playlistDAO.addVidSegToPlaylist(playlistName, "testAppendVidSeg2");
		playlistDAO.addVidSegToPlaylist(playlistName, "testAppendVidSeg3");

		List<VidSeg> allVSInPlaylist = playlistDAO.getVideoSegInPlaylist(playlistName);

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

		playlistDAO.createPlaylist("testingDeletePlaylistDAO");
		
		//Checking to see if its there.
		assertEquals(playlistDAO.deletePlaylist("testingDeletePlaylistDAO"), true);
		
		playlistDAO.createPlaylist("testingDeletePlaylistDAO2");
		playlistDAO.addVidSegToPlaylist("testingDeletePlaylistDAO2", "https://testingDeletePlaylist2.com");
		
		//Checking to see if its there.
		assertEquals(playlistDAO.deletePlaylist("testingDeletePlaylistDAO2"), true);

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
			assertTrue(vsSol.get(i).id.equals(vsRet.get(i).id));
		}

	}

	@Test
	public void testCreatePlaylist() throws Exception {
		List<VidSeg> vsList = new ArrayList<VidSeg>();
		VidSeg vs1 = new VidSeg("testCreatePlaylistCreateVIDSEG", "Spock", "A most fascinating thing happened.", 1, 0);
		vsList.add(vs1);
		vsDAO.addVidSeg(vs1);


		playlistDAO.createPlaylist("testPlaylist");
		playlistDAO.addVidSegToPlaylist("testPlaylist", "testCreatePlaylistCreateVIDSEG");

		List<VidSeg> list = playlistDAO.getVideoSegInPlaylist("testPlaylist");


		for(int i = 0; i < list.size(); i++) {
			assertTrue(vsList.get(i).id.equals(list.get(i).id));
		}

		playlistDAO.deletePlaylist("testPlaylist");
		vsDAO.deleteVidSeg("testCreatePlaylistCreateVIDSEG");

	}

	@Test
	public void testDeleteVidSegFromPlaylist() throws Exception{
		VidSeg vs1 = new VidSeg("https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/deleteID1");
		VidSeg vs2 = new VidSeg("https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/deleteID2");
		List<VidSeg> sol = new ArrayList<>();
		sol.add(vs2);
		vsDAO.addVidSeg(vs1);
		vsDAO.addVidSeg(vs2);

		playlistDAO.createPlaylist("testingDeletePlaylist");
		playlistDAO.addVidSegToPlaylist("testingDeletePlaylist", "deleteID1");
		playlistDAO.addVidSegToPlaylist("testingDeletePlaylist", "deleteID2");

		playlistDAO.deleteVidSegFromPlaylist("testingDeletePlaylist", "deleteID1");

		List<VidSeg> list = playlistDAO.getVideoSegInPlaylist("testingDeletePlaylist"); //should only return 1 Video

		playlistDAO.deletePlaylist("testingDeletePlaylist");

		for(int i = 0; i < sol.size(); i++) {
			assertTrue(sol.get(i).id.equals(list.get(i).id));
		}

		vsDAO.deleteVidSeg("deleteID1");
		vsDAO.deleteVidSeg("deleteID2");

	}

	@Test
	public void testDeleteVidSegFromPlaylistWithIndex() throws Exception{

		VidSeg vs1 = new VidSeg("testDeleteVidSegFromPlaylistWithIndex1", "Spock", "A most fascinating thing happened.", 1, 0);
		VidSeg vs2 = new VidSeg("testDeleteVidSegFromPlaylistWithIndex2", "James T. Kirk", "More like love.", 1, 0);
		VidSeg vs3 = new VidSeg("testDeleteVidSegFromPlaylistWithIndex3", "Leonard McCoy", "Now you must want the child!", 1, 0);
		VidSeg vs4 = new VidSeg("testDeleteVidSegFromPlaylistWithIndex4", "Spock", "That should prove very interesting.", 1, 0);
		VidSeg vs5 = new VidSeg("testDeleteVidSegFromPlaylistWithIndex5", "Leonard McCoy","You touch it, her nearest male relative will have to try to kill you.", 1, 0);
		VidSeg vs6 = new VidSeg("testDeleteVidSegFromPlaylistWithIndex6", "Amanda Grayson","Well it's sort of a fat teddy bear.", 1, 0);

		vsDAO.addVidSeg(vs1);
		vsDAO.addVidSeg(vs2);
		vsDAO.addVidSeg(vs3);
		vsDAO.addVidSeg(vs4);
		vsDAO.addVidSeg(vs5);
		vsDAO.addVidSeg(vs6);

		playlistDAO.addVidSegToPlaylist("testDeleteVidSegFromPlaylistWithIndexPLAYLIST", vs1.id);
		playlistDAO.addVidSegToPlaylist("testDeleteVidSegFromPlaylistWithIndexPLAYLIST", vs2.id);
		playlistDAO.addVidSegToPlaylist("testDeleteVidSegFromPlaylistWithIndexPLAYLIST", vs3.id);
		playlistDAO.addVidSegToPlaylist("testDeleteVidSegFromPlaylistWithIndexPLAYLIST", vs4.id);
		playlistDAO.addVidSegToPlaylist("testDeleteVidSegFromPlaylistWithIndexPLAYLIST", vs5.id);
		playlistDAO.addVidSegToPlaylist("testDeleteVidSegFromPlaylistWithIndexPLAYLIST", vs6.id);

		playlistDAO.deleteVidSegFromPlaylistWithIndex("testDeleteVidSegFromPlaylistWithIndexPLAYLIST", 1);

		List<VidSeg> sol = new ArrayList<VidSeg>();
		sol.add(vs1);
		sol.add(vs3);
		sol.add(vs4);
		sol.add(vs5);
		sol.add(vs6);																					

		List<VidSeg> ret = playlistDAO.getVideoSegInPlaylist("testDeleteVidSegFromPlaylistWithIndexPLAYLIST");

		playlistDAO.deletePlaylist("testDeleteVidSegFromPlaylistWithIndexPLAYLIST");

		vsDAO.deleteVidSeg("testDeleteVidSegFromPlaylistWithIndex1");
		vsDAO.deleteVidSeg("testDeleteVidSegFromPlaylistWithIndex2");
		vsDAO.deleteVidSeg("testDeleteVidSegFromPlaylistWithIndex3");
		vsDAO.deleteVidSeg("testDeleteVidSegFromPlaylistWithIndex4");
		vsDAO.deleteVidSeg("testDeleteVidSegFromPlaylistWithIndex5");
		vsDAO.deleteVidSeg("testDeleteVidSegFromPlaylistWithIndex6");


		for(int i = 0; i < sol.size(); i ++) {
			assertTrue(sol.get(i).id.equals(ret.get(i).id));
		}


	}



}
