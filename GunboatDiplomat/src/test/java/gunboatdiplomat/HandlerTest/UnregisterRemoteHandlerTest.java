package gunboatdiplomat.HandlerTest;

import org.junit.Assert;
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

public class UnregisterRemoteHandlerTest extends LambdaTest {

	public RemoteSiteDAO rsDAO = new RemoteSiteDAO();
	
	@Test
	public void testUnregisterRemote() throws Exception {
		
		RemoteSite rs = new RemoteSite("https://www.letTestUnregister.org");
		
		// register the remote
		RegisterRemoteRequest req = new RegisterRemoteRequest(rs.getUrl());
		RegisterRemoteResponse resp = new RegisterRemoteHandler().handleRequest(req, createContext("register"));
		Assert.assertEquals("https://www.letTestUnregister.org", resp.id);
		RemoteSite testRS = rsDAO.getRemoteSite("https://www.letTestUnregister.org");
		Assert.assertTrue(testRS != null);
		
		// now get rid of it
		UnregisterRemoteRequest urr = new UnregisterRemoteRequest("https://www.letTestUnregister.org");
		UnregisterRemoteResponse unregResponse = new UnregisterRemoteHandler().handleRequest(urr, createContext("unregister"));
		Assert.assertEquals("https://www.letTestUnregister.org", unregResponse.id);
		unregResponse.toString();
		
		// make sure it's gone
		unregResponse = new UnregisterRemoteHandler().handleRequest(urr, createContext("unregister"));
		System.out.println(unregResponse);
		Assert.assertEquals(422, unregResponse.statusCode);
	}
	
	@Test
	public void testUnregisterRemoteHTTPS() {
		
		UnregisterRemoteResponse resp1 = new UnregisterRemoteResponse("testing", 200, "");
		Assert.assertEquals(resp1.toString(), "UnregisterRemoteResponse(testing)");
		
		UnregisterRemoteResponse resp2 = new UnregisterRemoteResponse("testing", 400);
		Assert.assertEquals(resp2.toString(), "ErrorResult(testing,statusCode=400,error=)");
		
		UnregisterRemoteRequest req = new UnregisterRemoteRequest();
		req.setUrl("url");
		Assert.assertEquals(req.getUrl(), "url");
		
	}
	
	@Test
	public void testUnregisterFail() {
		UnregisterRemoteResponse failTest = new UnregisterRemoteResponse("THis should fail", 403, "Testing failsae operation");
		failTest.toString();
	}
	
}
