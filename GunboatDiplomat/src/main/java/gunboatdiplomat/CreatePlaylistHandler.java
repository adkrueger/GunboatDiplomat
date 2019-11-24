package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.http.CreatePlaylistRequest;
import gunboatdiplomat.http.CreatePlaylistResponse;

public class CreatePlaylistHandler implements RequestHandler<CreatePlaylistRequest, CreatePlaylistResponse> {

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	@Override
	public CreatePlaylistResponse handleRequest(CreatePlaylistRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		CreatePlaylistResponse response = null;		// TODO: remove " = null;"
		
		return response;
		
	}

}
