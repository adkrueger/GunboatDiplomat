package gunboatdiplomat;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;

import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.DeleteVidSegRequest;
import gunboatdiplomat.http.DeleteVidSegResponse;
import gunboatdiplomat.model.VidSeg;

public class DeleteVidSegHandler implements RequestHandler<DeleteVidSegRequest, DeleteVidSegResponse> {

	public LambdaLogger logger = null;
	private AmazonS3 s3 = null;
	
	public boolean deleteVidSegFromS3(String id) throws Exception {
		
		if(logger != null) { logger.log("in deleteVidSegFromS3"); }
		
		if(s3 == null) {
			logger.log("requesting attach to S3");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 successful");
		}
		
		try {
			s3.deleteObject(new DeleteObjectRequest("gd3733", "videoSegments/" + id));
		
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public DeleteVidSegResponse handleRequest(DeleteVidSegRequest request, Context context) {
		logger = context.getLogger();
		logger.log("In handler to delete video segment...");
		
		DeleteVidSegResponse response = null;
		logger.log("delete request: " + request.toString());
		
		VideoSegmentDAO dao = new VideoSegmentDAO();
		VidSeg vidSeg = new VidSeg(request.id, "", "", 0, 0);
		
		try {
			// delete the video segment from RDS
			if(dao.deleteVidSeg(vidSeg)) {
				response = new DeleteVidSegResponse(request.id, 200);
			} 
			else {
				response = new DeleteVidSegResponse(request.id, 422, "Unable to delete video segment from RDS.");
			}
			
			// delete the video segment from S3
			if(deleteVidSegFromS3(request.id)) {
				response = new DeleteVidSegResponse(request.id, 200);
			}
			else {
				response = new DeleteVidSegResponse(request.id, 422, "Unable to delete video segment from S3.");
			}
		} 
		catch(Exception e) {
			response = new DeleteVidSegResponse(request.id, 403, "Unable to delete video segment: " + request.id + "(" + e.getMessage() + ")");
		}

		return response;
	}
	
}
