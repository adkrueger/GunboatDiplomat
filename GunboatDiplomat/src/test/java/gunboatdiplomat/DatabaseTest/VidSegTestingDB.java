package gunboatdiplomat.DatabaseTest;

import static org.junit.Assert.*;

import org.junit.Test;
import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.model.VidSeg;
import java.util.ArrayList;
import java.util.List;

public class VidSegTestingDB {


	// Creating VidSegDAO Object
	public VideoSegmentDAO vidsegDAO = new VideoSegmentDAO();


	/**
	 * testFindVideoSeg
	 *
	 * This method tests to see if you can access a video with video_id.
	 * It runs the getVidSeg(String s) and returns the video obj. with that ID. 
	 * @throws Exception
	 * 		In case of failing to connect to DB. 
	 */
	@Test
	public void testFindVideoSeg() throws Exception{

		VidSeg vs1 = new VidSeg("5ecb7cb5-115a-4114-9865-bec03f4b2f5a", "Leonard McCoy", "Death by natural causes.", 1, 0);
		VidSeg vs2;

		vs2 = vidsegDAO.getVidSeg("5ecb7cb5-115a-4114-9865-bec03f4b2f5a");

		assertTrue(vs1.equals(vs2));

	}

	/**
	 * testAddVidSeg
	 * 
	 * This method tests to see if you can add a video segment to the DB. 
	 * At the end it also tests the delete function which removes a video 
	 * from the DB. 
	 * @throws Exception
	 */
	@Test
	public void testAddVidSeg() throws Exception{

		//Creating sample entry for DB.  
		VidSeg vs1 = new VidSeg("TestingString", "Ashwin Pai", "This is a test", 1, 0);

		//Adding sample entry to DB. 
		vidsegDAO.addVidSeg(vs1);

		//Checking to see if the the entry was processed. 
		VidSeg vs2 = vidsegDAO.getVidSeg("TestingString");

		//Checking to see that they are equal
		assertTrue(vs1.equals(vs2));

		//Deleting the VidSeg that was just added. 
		if(vidsegDAO.deleteVidSeg(vs2) == true) {
			System.out.println("Video has been deleted from the the table");
		}

	}
	
	/**
	 * testGetAllVidSeg
	 * 
	 * This method tests to see if you can return a list of all video segments in the DB.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllVidSeg() throws Exception {
		
		VidSeg vs1 = new VidSeg("5ecb7cb5-115a-4114-9865-bec03f4b2f5a", "Leonard McCoy", "Death by natural causes.", 1, 0);
		VidSeg vs2 = new VidSeg("dea234c9-f27b-42e0-9f61-e8c462294f2b", "James T. Kirk", "I am the captain of the ship and totally capable of commanding her", 1, 0 );
		VidSeg vs3 = new VidSeg("6600f679-27e6-43b4-b743-50c66483b2d5", "Zefram Cochrane", "Captain, why did you build that translator with a feminine voice?", 1, 0);
		VidSeg vs4 = new VidSeg("65ec514c-64f4-473d-902e-b93c4bcd9439", "Leonard McCoy", "That's a pretty far out story.", 1, 0);
		VidSeg vs5 = new VidSeg("df79e576-3c06-468d-a3b2-fab974607260", "Amanda Grayson", "Well it's sort of a fat teddy bear.", 1, 0);
		VidSeg vs6 = new VidSeg("30f065a0-42e2-4be3-a64f-14aac9a03252", "Leonard McCoy", "You touch it, her nearest male relative will have to try to kill you.", 1, 0);
		VidSeg vs7 = new VidSeg("c44af4fd-1b23-4350-bd91-d797de7f1eca", "Spock", "That should prove very interesting.", 1, 0);
		VidSeg vs8 = new VidSeg("32b1d20e-62cf-4917-bc19-33c3cbb09a7e", "Leonard McCoy", "Now you must want the child!", 1, 0);
		VidSeg vs9 = new VidSeg("668260dc-cebf-44b3-89fe-c30ec4909d76", "James T. Kirk", "More like love.", 1, 0);
		VidSeg vs10 = new VidSeg("4e770ca3-2961-4a38-a412-c2c60505bbc5", "Spock", "A most facinating thing happened.", 1, 0);
		
		List<VidSeg> listOfSegSolution  = new ArrayList<VidSeg>();
		listOfSegSolution.add(vs1);
		listOfSegSolution.add(vs2);
		listOfSegSolution.add(vs3);
		listOfSegSolution.add(vs4);
		listOfSegSolution.add(vs5);
		listOfSegSolution.add(vs6);
		listOfSegSolution.add(vs7);
		listOfSegSolution.add(vs8);
		listOfSegSolution.add(vs9);
		listOfSegSolution.add(vs10);
		
		List<VidSeg> listOfSegTest = new ArrayList<>();
		listOfSegTest = vidsegDAO.getAllVidSegs();
		
		for (int i = 0; i < listOfSegSolution.size(); i++) {
//			System.out.println(listOfSegSolution.get(i));
//			System.out.println(listOfSegTest.get(i));
			assertTrue(listOfSegSolution.get(i).equals(listOfSegTest.get(i)));
		}

	}
	


}
