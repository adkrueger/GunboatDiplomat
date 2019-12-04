package gunboatdiplomat.HandlerTest;

import org.junit.Test;

import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.RegisterRemoteHandler;
import gunboatdiplomat.UnregisterRemoteHandler;
import gunboatdiplomat.db.RemoteSiteDAO;
import gunboatdiplomat.http.RegisterRemoteRequest;
import gunboatdiplomat.http.RegisterRemoteResponse;
import gunboatdiplomat.http.UnregisterRemoteRequest;
import gunboatdiplomat.http.UnregisterRemoteResponse;
import gunboatdiplomat.model.RemoteSite;

import java.io.IOException;

import org.junit.Assert;

public class RegisterRemoteHandlerTest extends LambdaTest {

	public RemoteSiteDAO rsDAO = new RemoteSiteDAO();
	
	@Test
	public void testRegisterRemote() throws Exception {
		
		RemoteSite rs = new RemoteSite("https://www.google.com");
		
		// register the remote
		RegisterRemoteRequest req = new RegisterRemoteRequest(rs.getUrl());
		RegisterRemoteResponse resp = new RegisterRemoteHandler().handleRequest(req, createContext("register"));
		Assert.assertEquals("https://www.google.com", resp.id);
		RemoteSite testRS = rsDAO.getRemoteSite("https://www.google.com");
		Assert.assertTrue(testRS != null);
		
		// now get rid of it
		UnregisterRemoteRequest urr = new UnregisterRemoteRequest("https://www.google.com");
		UnregisterRemoteResponse unregResponse = new UnregisterRemoteHandler().handleRequest(urr, createContext("unregister"));
		Assert.assertEquals("https://www.google.com", unregResponse.id);
		
		// make sure it's gone
		testRS = rsDAO.getRemoteSite("https://www.google.com");
		Assert.assertTrue(testRS == null);
	}
	
}
