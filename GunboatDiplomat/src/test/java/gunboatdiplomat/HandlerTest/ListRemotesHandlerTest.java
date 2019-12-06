package gunboatdiplomat.HandlerTest;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.ListRemotesHandler;
import gunboatdiplomat.db.RemoteSiteDAO;
import gunboatdiplomat.http.ListRemotesResponse;
import gunboatdiplomat.model.RemoteSite;

public class ListRemotesHandlerTest extends LambdaTest {
	
	RemoteSiteDAO dao = new RemoteSiteDAO();

	@Test
	public void testListRemotesHandler() throws Exception {
		
		RemoteSite rs1 = new RemoteSite("https://testList1.com");
		RemoteSite rs2 = new RemoteSite("https://testList2.com");
		RemoteSite rs3 = new RemoteSite("https://testList3.com");
		
		dao.addRemoteSite(rs1);
		dao.addRemoteSite(rs2);
		dao.addRemoteSite(rs3);
		
		ListRemotesHandler handler = new ListRemotesHandler();
		ListRemotesResponse response = handler.handleRequest(null, createContext("list remotes"));
		
		Assert.assertEquals(200, response.statusCode);
		
		ArrayList<RemoteSite> rsList = response.remotes;

		Assert.assertTrue(rsList.contains(rs1));
		Assert.assertTrue(rsList.contains(rs2));
		Assert.assertTrue(rsList.contains(rs3));
		
		for(RemoteSite rs : rsList) {
			RemoteSite rsTest = dao.getRemoteSite(rs.url);
			
			Assert.assertEquals(rs, rsTest);	// check no results are null
		}
		
		dao.deleteRemoteSite("https://testList1.com");
		dao.deleteRemoteSite("https://testList2.com");
		dao.deleteRemoteSite("https://testList3.com");
				
	}
	
}
