package gunboatdiplomat.HandlerTest;

import org.junit.Assert;
import org.junit.Test;

import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.ListPlaylistsHandler;
import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.ListPlaylistsResponse;

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

		Assert.assertTrue(dao.checkPlaylistExists("Almost..."));
		Assert.assertTrue(dao.checkPlaylistExists("Done..."));
		Assert.assertTrue(dao.checkPlaylistExists("With..."));
		Assert.assertTrue(dao.checkPlaylistExists("Project!"));

		dao.deletePlaylist("Almost...");
		dao.deletePlaylist("Done...");
		dao.deletePlaylist("With...");
		dao.deletePlaylist("Project!");

	}

}

