package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.http.SearchVidSegsRequest;
import gunboatdiplomat.http.SearchVidSegsResponse;

public class SearchVidSegsHandler implements RequestHandler<SearchVidSegsRequest, SearchVidSegsResponse> {

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	@Override
	public SearchVidSegsResponse handleRequest(SearchVidSegsRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		SearchVidSegsResponse response = null;		// TODO: remove " = null;"
		
		return response;
		
	}

}
