package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.MarkVidSegRequest;
import gunboatdiplomat.http.MarkVidSegResponse;

public class MarkVidSegHandler implements RequestHandler<MarkVidSegRequest, MarkVidSegResponse> {
	
	private AmazonS3 s3 = null;
	LambdaLogger logger;  

	@Override
	public MarkVidSegResponse handleRequest(MarkVidSegRequest req, Context context) {
		
		MarkVidSegResponse response = null;
		VideoSegmentDAO vsDAO = new VideoSegmentDAO();
		
		String videoID = req.getVideoID();
		
		
		try {
			if(vsDAO.markVidSeg(videoID)) {
				response = new MarkVidSegResponse(videoID, 200);
			}
			else {
				response = new MarkVidSegResponse(videoID, 422);
			}
			
		}
		
		catch(Exception e) {
			response = new MarkVidSegResponse(videoID, 403);
		}
		
		
		logger = context.getLogger();
		logger.log(req.toString());
		return response;
	}
	
	

}
