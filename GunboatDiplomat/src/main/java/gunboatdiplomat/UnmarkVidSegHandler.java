package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.db.VideoSegmentDAO;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import gunboatdiplomat.http.UnmarkVidSegRequest;
import gunboatdiplomat.http.UnmarkVidSegResponse;


public class UnmarkVidSegHandler implements RequestHandler<UnmarkVidSegRequest, UnmarkVidSegResponse>{
	
	private AmazonS3 s3 = null;
	LambdaLogger logger;
	
	
	@Override
	public UnmarkVidSegResponse handleRequest(UnmarkVidSegRequest req, Context context) {
		
		UnmarkVidSegResponse response = null;
		VideoSegmentDAO vsDAO = new VideoSegmentDAO();
		
		String videoID = req.getVideoID();
		
		
		try {
			if(vsDAO.unmarkVidSeg(videoID)) {
				response = new UnmarkVidSegResponse(videoID, 200);
			}
			else {
				response = new UnmarkVidSegResponse(videoID, 422);
			}
			
		}
		
		catch(Exception e) {
			response = new UnmarkVidSegResponse(videoID, 403);
		}
		
		
		logger = context.getLogger();
		logger.log(req.toString());
		return response;
	}
	

}
