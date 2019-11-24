package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.http.UnregisterRemoteRequest;
import gunboatdiplomat.http.UnregisterRemoteResponse;

public class UnregisterRemoteHandler implements RequestHandler<UnregisterRemoteRequest, UnregisterRemoteResponse> {

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	@Override
	public UnregisterRemoteResponse handleRequest(UnregisterRemoteRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		UnregisterRemoteResponse response = null;		// TODO: remove " = null;"
		
		return response;
		
	}

}
