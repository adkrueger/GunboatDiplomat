package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.http.ListPlaylistsResponse;

public class ListPlaylistsHandler implements RequestHandler<Object, ListPlaylistsResponse> {

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	@Override
	public ListPlaylistsResponse handleRequest(Object req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		ListPlaylistsResponse response = null;		// TODO: remove " = null;"
		
		return response;
		
	}
	
}
