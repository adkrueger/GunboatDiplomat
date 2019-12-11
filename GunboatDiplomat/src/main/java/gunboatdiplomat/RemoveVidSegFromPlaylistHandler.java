package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.RemoveVidSegRequest;
import gunboatdiplomat.http.RemoveVidSegResponse;

public class RemoveVidSegFromPlaylistHandler implements RequestHandler<RemoveVidSegRequest, RemoveVidSegResponse> {

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	@Override
	public RemoveVidSegResponse handleRequest(RemoveVidSegRequest req, Context context) {
		
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
		

		logger = context.getLogger();
		logger.log(req.toString());
		
		return response;
		
	}

}
