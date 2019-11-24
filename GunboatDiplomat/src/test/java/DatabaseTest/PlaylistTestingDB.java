package DatabaseTest;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import gunboatdiplomat.model.VidSeg;
import gunboatdiplomat.db.PlaylistDAO;

public class PlaylistTestingDB {
	
	public PlaylistDAO playlistDAO = new PlaylistDAO();
	
	@Test
	public void testGetAllVidSeg() throws Exception {
		
		
		// All the video segments that should be returned. 
		List<VidSeg> lsSolution = new ArrayList<>();
		VidSeg vs1 = new VidSeg("4e770ca3-2961-4a38-a412-c2c60505bbc5", "Spock", "A more facinating thing happened.", 2, 9, 1, 0);
		VidSeg vs2 = new VidSeg("668260dc-cebf-44b3-89fe-c30ec4909d76", "James T. Kirk", "More like love.",  2, 9, 1, 0);
		VidSeg vs3 = new VidSeg("32b1d20e-62cf-4917-bc19-33c3cbb09a7e",  "Leonard McCoy", "Now you must want the child!", 2, 11, 1, 0);
		VidSeg vs4 = new VidSeg("c44af4fd-1b23-4350-bd91-d797de7f1eca", "Spock", "That should prove very interesting.",  2, 11, 1, 0);
		VidSeg vs5 = new VidSeg("30f065a0-42e2-4be3-a64f-14aac9a03252", "Leonard McCoy", "You touch it, her nearest male relative will have to try to kill you.",  2, 11, 1, 0);
		VidSeg vs6 = new VidSeg("df79e576-3c06-468d-a3b2-fab974607260", "Amanda Grayson", "Well it's sort of a fat teddy bear.", 2, 10, 1, 0);
		
		lsSolution.add(vs6);
		lsSolution.add(vs5);
		lsSolution.add(vs4);
		lsSolution.add(vs3);
		lsSolution.add(vs2);
		lsSolution.add(vs1);
		
		
		//Creating the answerKey
		List<VidSeg> lsReturned = new ArrayList<VidSeg>();
		
		
		
		// Returns a list of video clips. 
		lsReturned = playlistDAO.getPlaylistVidSeg("Funny Clips");
		
		for (int i = 0; i < lsSolution.size(); i++) {
			assertTrue(lsSolution.get(i).equals(lsReturned.get(i)));
		}
		
		
	}

}
