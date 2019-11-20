package DatabaseTest;

import static org.junit.Assert.*;

import org.junit.Test;

import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.model.VidSeg;

public class databaseTesting {
	
	
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

		VidSeg vs1 = new VidSeg("5ecb7cb5-115a-4114-9865-bec03f4b2f5a", "Leonard McCoy", "Death by natural causes.", 2, 12, true, false);
		VidSeg vs2;
		
		vs2 = vidsegDAO.getVidSeg("5ecb7cb5-115a-4114-9865-bec03f4b2f5a");

		assertTrue(vs1.equals(vs2));

	}
	
	@Test
	public void testAddVidSeg() throws Exception{
		
		//Creating sample entry for DB.  
		VidSeg vs1 = new VidSeg("TestingString", "Ashwin Pai", "This is a test", 2, 12, true, false);
		
		//Adding sample entry to DB. 
		vidsegDAO.addVidSeg(vs1);
		
		//Checking to see if the the entry was processed. 
		VidSeg vs2 = vidsegDAO.getVidSeg("TestingString");
		
		//Checking to see that they are equal
		assertTrue(vs1.equals(vs2));
		
		//Deleting the VidSeg that was just added. 
		
		
		
	}

}
