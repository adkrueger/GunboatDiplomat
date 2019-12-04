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

		VidSeg vs1 = new VidSeg("testingFindVidSeg", "Leonard McCoy", "Death by natural causes.", 1, 0);
		vidsegDAO.addVidSeg(vs1);
		VidSeg vs2 = vidsegDAO.getVidSeg("testingFindVidSeg");;

		assertTrue(vs1.equals(vs2));
		vidsegDAO.deleteVidSeg(vs1.id);

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
		if(vidsegDAO.deleteVidSeg(vs2.id) == true) {
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
		
		VidSeg vs1 = new VidSeg("testSeg1", "Leonard McCoy", "Death by natural causes.", 1, 0);
		VidSeg vs2 = new VidSeg("testSeg2", "James T. Kirk", "I am the captain of the ship and totally capable of commanding her", 1, 0 );
		VidSeg vs3 = new VidSeg("testSeg3", "Zefram Cochrane", "Captain, why did you build that translator with a feminine voice?", 1, 0);
		VidSeg vs4 = new VidSeg("testSeg4", "Leonard McCoy", "That's a pretty far out story.", 1, 0);
		VidSeg vs5 = new VidSeg("testSeg5", "Amanda Grayson", "Well it's sort of a fat teddy bear.", 1, 0);
		VidSeg vs6 = new VidSeg("testSeg6", "Leonard McCoy", "You touch it, her nearest male relative will have to try to kill you.", 1, 0);
		VidSeg vs7 = new VidSeg("testSeg7", "Spock", "That should prove very interesting.", 1, 0);
		VidSeg vs8 = new VidSeg("testSeg8", "Leonard McCoy", "Now you must want the child!", 1, 0);
		VidSeg vs9 = new VidSeg("testSeg9", "James T. Kirk", "More like love.", 1, 0);
		VidSeg vs10 = new VidSeg("testSeg10", "Spock", "A most fascinating thing happened.", 1, 0);
		
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
		
		vidsegDAO.addVidSeg(vs1);
		vidsegDAO.addVidSeg(vs2);
		vidsegDAO.addVidSeg(vs3);
		vidsegDAO.addVidSeg(vs4);
		vidsegDAO.addVidSeg(vs5);
		vidsegDAO.addVidSeg(vs6);
		vidsegDAO.addVidSeg(vs7);
		vidsegDAO.addVidSeg(vs8);
		vidsegDAO.addVidSeg(vs9);
		vidsegDAO.addVidSeg(vs10);
		
		List<VidSeg> listOfSegTest = new ArrayList<>();
		listOfSegTest = vidsegDAO.getAllVidSegs();
		
		for (VidSeg vs : listOfSegSolution) {
			assertTrue(listOfSegTest.contains(vs));
			vidsegDAO.deleteVidSeg(vs.id);
		}
	}
	
	@Test
	public void testDeleteVidSeg() throws Exception {
		vidsegDAO.addVidSeg(new VidSeg("testingDeleteVidSeg", "Aaron Krueger", "i am a robot beep boop", 1, 0));
		VidSeg vsTest = vidsegDAO.getVidSeg("testingDeleteVidSeg");
		assertTrue(vsTest != null);		// make sure it's there
		
		vidsegDAO.deleteVidSeg("testingDeleteVidSeg");
		vsTest = vidsegDAO.getVidSeg("testingDeleteVidSeg");
		assertTrue(vsTest == null);		// video segment should no longer be there
	}
	
	/**
	 * testMark and Unmark VideoSegment
	 * @throws Exception 
	 * 	
	 */
	
	@Test
	public void testMarkVidSeg() throws Exception {
		vidsegDAO.addVidSeg(new VidSeg("testingMarkVidSeg", "Aaron Krueger", "Hello, world!", 1, 0));
		vidsegDAO.markVidSeg("testingMarkVidSeg");
		
		//check to see if Video has been marked. 
		assertEquals(vidsegDAO.getVidSeg("testingMarkVidSeg").isMarked, 1);
		vidsegDAO.deleteVidSeg("testingMarkVidSeg");
	}
	
	@Test
	public void testUnmarkVidSeg() throws Exception{
		vidsegDAO.addVidSeg(new VidSeg("testingUnmarkVidSeg", "Aaron Krueger", "good morning america", 1, 1));
		vidsegDAO.unmarkVidSeg("testingUnmarkVidSeg");
		
		//Check to see if Video has been unmarked. 
		assertEquals(vidsegDAO.getVidSeg("testingUnmarkVidSeg").isMarked, 0);
		vidsegDAO.deleteVidSeg("testingUnmarkVidSeg");
	}


}
