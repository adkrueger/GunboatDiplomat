package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.AppendVidSegRequest;
import gunboatdiplomat.http.AppendVidSegResponse;

public class AppendVidSegToPlaylistHandler implements RequestHandler<AppendVidSegRequest, AppendVidSegResponse> {

	LambdaLogger logger;
	
	@Override
	public AppendVidSegResponse handleRequest(AppendVidSegRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());
		
		AppendVidSegResponse response = null;
		PlaylistDAO playlistDAO = new PlaylistDAO();
		
		
		String videoID = req.getVideoID();
		String playlistName = req.getPlaylistName();
		
		try {
			if(playlistDAO.addVidSegToPlaylist(playlistName, videoID)) {
				response =  new AppendVidSegResponse(playlistName, 200);
			}
			else {
				System.out.println("Could not find vid seg in list of vid segs.");
				response = new AppendVidSegResponse(playlistName, 404);
			}
		} catch (Exception e) {
			response = new AppendVidSegResponse(playlistName, 403, "Something has gone wrong");
		}
		
		return response;
		
	}

}
