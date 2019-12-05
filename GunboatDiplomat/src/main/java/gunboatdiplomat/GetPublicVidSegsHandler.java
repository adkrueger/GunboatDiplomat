package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.http.GetPublicVidSegsResponse;

public class GetPublicVidSegsHandler implements RequestHandler<Object, GetPublicVidSegsResponse> {
	
	private AmazonS3 s3 = null;
	
	LambdaLogger logger;
	
	public GetPublicVidSegsResponse handleRequest(Object request, Context context) {
		
		logger = context.getLogger();
		logger.log(request.toString());

		GetPublicVidSegsResponse response;	
		
		//create a new method that takes in a list of video segments from the DAO and then checks to see if each
		//video segment is marked or not and if it is not marked then we remove it and return the list with all
		//the marked video segments
		
		response = null;		// TODO: Implement and delete this line!!! (just here to satisfy AWS req's)
		
		
		return response;
	}
}