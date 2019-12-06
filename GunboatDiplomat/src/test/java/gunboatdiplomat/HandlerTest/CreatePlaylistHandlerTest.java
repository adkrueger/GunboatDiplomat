package gunboatdiplomat.HandlerTest;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.util.IOUtils;
import com.google.gson.Gson;

import gunboatdiplomat.CreatePlaylistHandler;
import gunboatdiplomat.DeleteVidSegHandler;
import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.UploadVidSegHandler;
import gunboatdiplomat.http.CreatePlaylistRequest;
import gunboatdiplomat.http.CreatePlaylistResponse;
import gunboatdiplomat.http.DeleteVidSegRequest;
import gunboatdiplomat.http.DeleteVidSegResponse;
import gunboatdiplomat.http.UploadVidSegRequest;
import gunboatdiplomat.http.UploadVidSegResponse;

public class CreatePlaylistHandlerTest extends LambdaTest {
	
	
/*	@Test
	public void testInputSuccess(String input) throws IOException {
		CreatePlaylistHandler handler = new CreatePlaylistHandler();
		CreatePlaylistRequest request = new Gson().fromJson(input, CreatePlaylistRequest.class);
		
		CreatePlaylistResponse response = handler.handleRequest(request, createContext("create"));
		Assert.assertEquals(200, response.httpCode);
	}
	
	@Test
	public void testInputFailure(String input, int failureCode) throws IOException {
		CreatePlaylistHandler handler = new CreatePlaylistHandler();
		CreatePlaylistRequest request = new Gson().fromJson(input, CreatePlaylistRequest.class);
		
		CreatePlaylistResponse response = handler.handleRequest(request, createContext("create"));
		Assert.assertEquals(failureCode, response.httpCode);
	}

    @Test
    public void testCreateVidSegHandler() throws IOException {
    	//upload two video segments
		InputStream inputStream1 = UploadVidSegHandlerTest.class.getResourceAsStream("Segment1.ogg");
        String testFile1 = new String(java.util.Base64.getEncoder().encode(IOUtils.toByteArray(inputStream1)));
        UploadVidSegRequest req1 = new UploadVidSegRequest("testingCreatePlaylist", "Leonard McCoy", "Death by natural causes.", 1, 0, testFile1);
        UploadVidSegResponse resp1 = new UploadVidSegHandler().handleRequest(req1, createContext("upload"));
        Assert.assertEquals("testingCreatePlaylist", resp1.response);
        
        InputStream inputStream2 = UploadVidSegHandlerTest.class.getResourceAsStream("Segment2.ogg");
        String testFile2 = new String(java.util.Base64.getEncoder().encode(IOUtils.toByteArray(inputStream2)));
        UploadVidSegRequest req2 = new UploadVidSegRequest("testingCreatePlaylist", "James T. Kirk", "I am the captain of this ship and am totally capable of commanding her.", 1, 0, testFile2);
        UploadVidSegResponse resp2 = new UploadVidSegHandler().handleRequest(req2, createContext("upload"));
        Assert.assertEquals("testingCreatePlaylist", resp2.response);
        
        // now create playlist
        CreatePlaylistRequest cpr = new CreatePlaylistRequest("testingCreatePlaylist");
        CreatePlaylistResponse createResponse = new CreatePlaylistHandler().handleRequest(cpr, createContext("create playlist"));
        System.out.println(createResponse.response);
        Assert.assertEquals("testingCreatePlaylist", createResponse.response);
        
    }
    
*/

}
