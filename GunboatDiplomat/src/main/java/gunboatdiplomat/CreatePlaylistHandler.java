package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.CreatePlaylistRequest;
import gunboatdiplomat.http.CreatePlaylistResponse;

public class CreatePlaylistHandler implements RequestHandler<CreatePlaylistRequest, CreatePlaylistResponse> {

	public LambdaLogger logger = null;
//	private AmazonS3 s3 = null;

	
	
/*	public boolean createPlaylist(String name) throws Exception {
		if (logger != null) { logger.log(" in createPlaylist"); }
		
		PlaylistDAO dao = new PlaylistDAO();
		
		return dao.createPlaylist();		
	}
	
*/
	
	
	@Override
	public CreatePlaylistResponse handleRequest(CreatePlaylistRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to create playlist...");
		
		CreatePlaylistResponse response = null;
		logger.log("Create playlist: " + req.toString());
		
		PlaylistDAO dao = new PlaylistDAO();
		
		try {
			if(dao.createPlaylist(req.name)) {
				response = new CreatePlaylistResponse(req.name, 200);
			} else {
				response = new CreatePlaylistResponse(req.name, 422, "Unable to create playlist.");
			}
			
		} catch (Exception e) {
			response = new CreatePlaylistResponse("Unable to create playlist: " + req.name + "(" + e.getMessage() + ")", 400);	
		}

		return response;
		
	}

}
