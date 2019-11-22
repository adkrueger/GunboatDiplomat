package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.http.RemoveVidSegRequest;
import gunboatdiplomat.http.RemoveVidSegResponse;

public class RemoveVidSegFromPlaylistHandler implements RequestHandler<RemoveVidSegRequest, RemoveVidSegResponse> {

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	@Override
	public RemoveVidSegResponse handleRequest(RemoveVidSegRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		RemoveVidSegResponse response = null;		// TODO: remove " = null;"
		
		return response;
		
	}

}
