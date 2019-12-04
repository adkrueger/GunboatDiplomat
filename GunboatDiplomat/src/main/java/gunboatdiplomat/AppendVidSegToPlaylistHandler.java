package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.AppendVidSegRequest;
import gunboatdiplomat.http.AppendVidSegResponse;

public class AppendVidSegToPlaylistHandler implements RequestHandler<AppendVidSegRequest, AppendVidSegResponse> {

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	@Override
	public AppendVidSegResponse handleRequest(AppendVidSegRequest req, Context context) {
		AppendVidSegResponse response = null;
		PlaylistDAO playlistDAO = new PlaylistDAO();
		
		
		String videoID = req.getVideoID();
		String playlistName = req.getPlaylistName();
		
		try {
			if(playlistDAO.addVidSegToPlaylist(playlistName, videoID)) {
					response =  new AppendVidSegResponse(playlistName, 200);
			}
			else {
				response = new AppendVidSegResponse(playlistName, 422);
			}
		} catch (Exception e) {
			response = new AppendVidSegResponse(playlistName, 403, "Something has gone wrong");
		}
		
		
		
		
		logger = context.getLogger();
		logger.log(req.toString());

				// TODO: remove " = null;"
		
		return response;
		
	}

}
