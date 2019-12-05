package gunboatdiplomat.DatabaseTest;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import gunboatdiplomat.db.RemoteSiteDAO;

import org.junit.Test;

import gunboatdiplomat.model.RemoteSite;

public class RemoteSiteTestingDB {
	
	RemoteSiteDAO rsDAO = new RemoteSiteDAO();
	

	@Test
	public void testAddRemoteURL() throws Exception{
		RemoteSite rs = new RemoteSite("testingVidURL");
		rsDAO.addRemoteSite(rs); 
		
		System.out.println("Video URL has been added!");
		
		//Checking to see if the video is in the table
		
		assertTrue(rsDAO.getRemoteSite(rs.url).url.equals("testingVidURL"));
		
	}
	
	@Test
	public void testDeleteRemoteURL() throws Exception {
		RemoteSite rs = new RemoteSite("testingVidURL");
		
		rsDAO.deleteRemoteSite(rs);
		
		//Check to see if it exists
		assertEquals(rsDAO.getRemoteSite("testingVidURL"), null); // was not found. 
	}
	
	
	@Test
	public void testGetAllRemoteSites() throws Exception {
		List<RemoteSite> rsListINS = new ArrayList<>();
		RemoteSite rs1 = new RemoteSite("testingVidURL1");
		RemoteSite rs2 = new RemoteSite("testingVidURL2");
		RemoteSite rs3 = new RemoteSite("testingVidURL3");
		
		rsDAO.addRemoteSite(rs1);
		rsDAO.addRemoteSite(rs2);
		rsDAO.addRemoteSite(rs3);
		
		rsListINS.add(rs1);
		rsListINS.add(rs2);
		rsListINS.add(rs3);
		
		List<RemoteSite> rsListRET= rsDAO.getAllRemoteSites();
		assertTrue(rsListRET.size() >= rsListINS.size());
		for(RemoteSite rs : rsListINS) {
			assertTrue(rsListRET.contains(rs));
		}
		
		
		rsDAO.deleteRemoteSite(rs1);
		rsDAO.deleteRemoteSite(rs2);
		rsDAO.deleteRemoteSite(rs3);
	}

}
