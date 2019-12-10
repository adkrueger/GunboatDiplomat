package gunboatdiplomat.HandlerTest;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import gunboatdiplomat.CreatePlaylistHandler;
import gunboatdiplomat.DeletePlaylistHandler;
import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.CreatePlaylistRequest;
import gunboatdiplomat.http.CreatePlaylistResponse;
import gunboatdiplomat.http.DeletePlaylistRequest;
import gunboatdiplomat.http.DeletePlaylistResponse;
import gunboatdiplomat.model.Playlist;
import gunboatdiplomat.model.VidSeg;

public class DeletePlaylistHandlerTest extends LambdaTest {

	PlaylistDAO dao = new PlaylistDAO();
	
	@Test
	public void testDeletePlaylistHandler() throws Exception {
		
		Playlist playlist = new Playlist("testing delete playlist");
		
		//create Playlist
		CreatePlaylistRequest req = new CreatePlaylistRequest("testing delete playlist");
    	CreatePlaylistResponse resp = new CreatePlaylistHandler().handleRequest(req, createContext("create"));
    	Assert.assertEquals(resp.response, "testing delete playlist");
    	
    	Map<String, List<VidSeg>> retMap = dao.getAllPlaylists();
    	Assert.assertTrue((retMap.keySet().contains(playlist.name)));
    	
    	//now delete
    	DeletePlaylistRequest dpr = new DeletePlaylistRequest("testing delete playlist");
    	DeletePlaylistResponse deleteResponse = new DeletePlaylistHandler().handleRequest(dpr, createContext("delete"));
    	System.out.println(deleteResponse.id);
		Assert.assertEquals(deleteResponse.id, "testing delete playlist");
		
		dao.deletePlaylist("testing delete playlist");
    	retMap = dao.getAllPlaylists();
    	Assert.assertFalse((retMap.keySet().contains(playlist.name)));
		
		//try again and this should fail
		deleteResponse = new DeletePlaylistHandler().handleRequest(dpr, createContext("delete"));
		Assert.assertEquals(200, deleteResponse.statusCode);
	}
	
}
