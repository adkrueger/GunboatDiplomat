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

	@Test
	public void testListRemotesHandler() throws Exception {
		
		ListRemotesHandler handler = new ListRemotesHandler();
		ListRemotesResponse response = handler.handleRequest(null, createContext("list remotes"));
		
		ArrayList<RemoteSite> rsList = response.remotes;
		RemoteSiteDAO dao = new RemoteSiteDAO();
		
		for(RemoteSite rs : rsList) {
			RemoteSite rsTest = dao.getRemoteSite(rs.url);
			
			Assert.assertEquals(rs, rsTest);
		}
		
		Assert.assertEquals(200, response.statusCode);
		
	}
	
}
