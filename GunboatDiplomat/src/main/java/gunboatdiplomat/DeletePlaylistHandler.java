package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.http.DeletePlaylistRequest;
import gunboatdiplomat.http.DeletePlaylistResponse;

public class DeletePlaylistHandler implements RequestHandler<DeletePlaylistRequest, DeletePlaylistResponse> {

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	@Override
	public DeletePlaylistResponse handleRequest(DeletePlaylistRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		DeletePlaylistResponse response = null;		// TODO: remove " = null;"
		
		return response;
		
	}

}
