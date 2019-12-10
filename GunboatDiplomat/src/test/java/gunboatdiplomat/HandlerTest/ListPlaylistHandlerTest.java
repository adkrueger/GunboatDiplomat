package gunboatdiplomat.HandlerTest;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.ListPlaylistsHandler;
import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.ListPlaylistsResponse;
import gunboatdiplomat.model.VidSeg;

public class ListPlaylistHandlerTest extends LambdaTest {

	PlaylistDAO dao = new PlaylistDAO();

	@Test
	public void testListPlaylistHandler() throws Exception {

		dao.createPlaylist("Almost...");
		dao.createPlaylist("Done...");
		dao.createPlaylist("With...");
		dao.createPlaylist("Project!");

		ListPlaylistsHandler handler = new ListPlaylistsHandler();
		ListPlaylistsResponse resp = handler.handleRequest(null, createContext("list playlist"));

		Assert.assertEquals(200, resp.statusCode);

		HashMap<String, List<VidSeg>> plist = resp.playlists;

		Assert.assertTrue(plist.containsKey("Almost..."));
		Assert.assertTrue(plist.containsKey("Done..."));
		Assert.assertTrue(plist.containsKey("With..."));
		Assert.assertTrue(plist.containsKey("Project!"));

		dao.deletePlaylist("Almost...");
		dao.deletePlaylist("Done...");
		dao.deletePlaylist("With...");
		dao.deletePlaylist("Project!");

	}

}

