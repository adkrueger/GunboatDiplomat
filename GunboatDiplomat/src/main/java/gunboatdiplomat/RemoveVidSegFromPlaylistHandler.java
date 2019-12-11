package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.RemoveVidSegRequest;
import gunboatdiplomat.http.RemoveVidSegResponse;

public class RemoveVidSegFromPlaylistHandler implements RequestHandler<RemoveVidSegRequest, RemoveVidSegResponse> {

	LambdaLogger logger;
	
	@Override
	public RemoveVidSegResponse handleRequest(RemoveVidSegRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());
		
		RemoveVidSegResponse response = null;
		PlaylistDAO playlistDAO = new PlaylistDAO();
		
		int videoIndex = req.getVideoIndex();
		String playlistName = req.getPlaylistName();
		
		try {
			if(playlistDAO.deleteVidSegFromPlaylistWithIndex(playlistName, videoIndex)) {
				response = new RemoveVidSegResponse(playlistName, 200);
			} else {
				response = new RemoveVidSegResponse(playlistName, 422);
			}
		} catch (Exception e) {
			response = new RemoveVidSegResponse(playlistName, 403, "Something has gone wrong.");
		}
		
		return response;
		
	}

}
