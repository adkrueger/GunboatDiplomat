package gunboatdiplomat;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.ListPlaylistsResponse;
import gunboatdiplomat.model.VidSeg;

public class ListPlaylistsHandler implements RequestHandler<Object, ListPlaylistsResponse> {

	LambdaLogger logger;

	HashMap<String, List<VidSeg>> getPlaylistsFromRDS() throws Exception {
		logger.log("in Playlists");
		PlaylistDAO dao = new PlaylistDAO();

		return dao.getAllPlaylists();
	}

	@Override
	public ListPlaylistsResponse handleRequest(Object req, Context context) {

		logger = context.getLogger();

		ListPlaylistsResponse response;	
		
		try {
			HashMap<String, List<VidSeg>> list = getPlaylistsFromRDS();

			response = new ListPlaylistsResponse(list, 200);
		}
		catch (Exception e) {
			response = new ListPlaylistsResponse(403, e.getMessage());
		}

		return response;

	}

}
