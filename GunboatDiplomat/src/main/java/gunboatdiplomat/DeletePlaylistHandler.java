package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.DeletePlaylistRequest;
import gunboatdiplomat.http.DeletePlaylistResponse;

public class DeletePlaylistHandler implements RequestHandler<DeletePlaylistRequest, DeletePlaylistResponse> {

	public LambdaLogger logger = null;
	
	@Override
	public DeletePlaylistResponse handleRequest(DeletePlaylistRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		DeletePlaylistResponse response;
		
		PlaylistDAO dao = new PlaylistDAO();
		
		try {
			// delete the playlist from RDS
			if(dao.deletePlaylist(req.id)) {
				response = new DeletePlaylistResponse(req.id, 200);
			}
			else {
				response = new DeletePlaylistResponse(req.id, 422, "Unable to delete playlist");
			}
		}
		catch(Exception e) {
			response = new DeletePlaylistResponse(req.id, 403, "Unable to delete playlist: " + req.id + "(" + e.getMessage() + ")");
		}
		
		return response;
		
	}

}
