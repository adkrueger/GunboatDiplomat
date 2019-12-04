package gunboatdiplomat;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;

import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.DeletePlaylistRequest;
import gunboatdiplomat.http.DeletePlaylistResponse;
import gunboatdiplomat.model.Playlist;

public class DeletePlaylistHandler implements RequestHandler<DeletePlaylistRequest, DeletePlaylistResponse> {

	public LambdaLogger logger = null;
	private AmazonS3 s3 = null;	
	
	@Override
	public DeletePlaylistResponse handleRequest(DeletePlaylistRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		DeletePlaylistResponse response = null;		// TODO: remove " = null;"
		logger.log(req.toString());
		
		PlaylistDAO dao = new PlaylistDAO();
		Playlist playlist = new Playlist(req.id);
		
		try {
			// delete the playlist from RDS
			if(dao.deletePlaylist(playlist.name)) {
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
