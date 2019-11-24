package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.http.AppendVidSegRequest;
import gunboatdiplomat.http.AppendVidSegResponse;

public class AppendVidSegToPlaylistHandler implements RequestHandler<AppendVidSegRequest, AppendVidSegResponse> {

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	@Override
	public AppendVidSegResponse handleRequest(AppendVidSegRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		AppendVidSegResponse response = null;		// TODO: remove " = null;"
		
		return response;
		
	}

}
