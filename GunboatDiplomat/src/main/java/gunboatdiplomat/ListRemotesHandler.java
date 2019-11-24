package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.http.ListRemotesResponse;

public class ListRemotesHandler implements RequestHandler<Object, ListRemotesResponse> {

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	@Override
	public ListRemotesResponse handleRequest(Object req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		ListRemotesResponse response = null;		// TODO: remove " = null;"
		
		return response;
		
	}

}
