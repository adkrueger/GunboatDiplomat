package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

public class GetPublicVidSegsHandler {
	private AmazonS3 s3 = null;
	public LambdaLogger logger;
}