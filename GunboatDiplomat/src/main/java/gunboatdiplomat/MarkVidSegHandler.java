package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.MarkVidSegRequest;
import gunboatdiplomat.http.MarkVidSegResponse;

public class MarkVidSegHandler implements RequestHandler<MarkVidSegRequest, MarkVidSegResponse> {
	
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
