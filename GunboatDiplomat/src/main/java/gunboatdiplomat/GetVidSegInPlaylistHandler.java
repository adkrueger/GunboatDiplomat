package gunboatdiplomat;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.GetVidSegInPlaylistRequest;
import gunboatdiplomat.http.GetVidSegInPlaylistResponse;
import gunboatdiplomat.model.VidSeg;

public class GetVidSegInPlaylistHandler implements RequestHandler<GetVidSegInPlaylistRequest, GetVidSegInPlaylistResponse> {

	LambdaLogger logger;

	public List<VidSeg> getVidSegInPlaylistFromRDS(String playlistID) throws Exception {
		logger.log("in getVideoSegmentsFromPlaylist");
		PlaylistDAO dao = new PlaylistDAO();
		
		return dao.getVideoSegInPlaylist(playlistID);
	}
	
	public GetVidSegInPlaylistResponse handleRequest(GetVidSegInPlaylistRequest request, Context context) {
		
		logger = context.getLogger();
		logger.log(request.toString());
		
		GetVidSegInPlaylistResponse response;
		
		try {
			List<VidSeg> list = getVidSegInPlaylistFromRDS(request.playlist_id);
			response = new GetVidSegInPlaylistResponse(list, 200);
		}
		catch (Exception e) {
			response = new GetVidSegInPlaylistResponse(403, e.getMessage());
		}
		
		return response;
		
	}
	
}
