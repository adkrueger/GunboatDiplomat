package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.http.RegisterRemoteRequest;
import gunboatdiplomat.http.RegisterRemoteResponse;

public class RegisterRemoteHandler implements RequestHandler<RegisterRemoteRequest, RegisterRemoteResponse> {

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	@Override
	public RegisterRemoteResponse handleRequest(RegisterRemoteRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		RegisterRemoteResponse response = null;		// TODO: remove " = null;"
		
		return response;
		
	}

}
