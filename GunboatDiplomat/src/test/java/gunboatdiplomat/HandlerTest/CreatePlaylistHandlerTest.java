package gunboatdiplomat.HandlerTest;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import gunboatdiplomat.CreatePlaylistHandler;
import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.CreatePlaylistRequest;
import gunboatdiplomat.http.CreatePlaylistResponse;
import gunboatdiplomat.model.Playlist;
import gunboatdiplomat.model.VidSeg;

public class CreatePlaylistHandlerTest extends LambdaTest {
	
	public PlaylistDAO plDAO = new PlaylistDAO();
	
    @Test
    public void testCreatePlaylistHandler() throws Exception {
    	    	
    	Playlist pl = new Playlist("testin' playlist creatin'");
    	
    	CreatePlaylistRequest req = new CreatePlaylistRequest("testin' playlist creatin'");
    	CreatePlaylistResponse resp = new CreatePlaylistHandler().handleRequest(req, createContext("create"));
    	Assert.assertEquals(resp.response, "testin' playlist creatin'");
    	
    	Map<String, List<VidSeg>> retMap = plDAO.getAllPlaylists();
    	Assert.assertTrue(plDAO.checkPlaylistExists(pl.name));
    	
    	plDAO.deletePlaylist("testin' playlist creatin'");
    	retMap = plDAO.getAllPlaylists();
    	Assert.assertFalse((retMap.keySet().contains(pl.name)));
    	
    }
    

}
