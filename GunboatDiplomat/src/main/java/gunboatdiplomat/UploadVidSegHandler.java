package gunboatdiplomat;

import java.io.ByteArrayInputStream;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import edu.wpi.cs.heineman.calculator.http.CreateConstantRequest;
import edu.wpi.cs.heineman.calculator.http.CreateConstantResponse;
import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.UploadVidSegRequest;
import gunboatdiplomat.http.UploadVidSegResponse;
import gunboatdiplomat.model.VidSeg;

public class UploadVidSegHandler implements RequestHandler<UploadVidSegRequest, UploadVidSegResponse>{

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	// uploading the video segment info to RDS (essentially creating a new video segment)
	boolean uploadVidSegToRDS(String id, String character, String quote, int seasonNum, int episodeNum, boolean isLocal, boolean isMarked) throws Exception {
		
		if(logger != null) { logger.log("in uploadVidSegToRDS"); }
		VideoSegmentDAO dao = new VideoSegmentDAO();
		
		VidSeg exist = dao.getVidSeg(id);
		VidSeg vidSeg = new VidSeg(id, character, quote, seasonNum, episodeNum, isLocal, isMarked);
		if(exist == null) {
			return dao.addVidSeg(vidSeg);
		}
		else {
			return false;
		}
		
	}
	
	boolean uploadVidSegToS3(String id, byte[] contents) throws Exception {
		
		if(logger != null) { logger.log("in uploadVidSegToS3"); }
		
		if(s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to s3 successful");
		}
		
		ByteArrayInputStream bais = new ByteArrayInputStream(contents);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(contents.length);
		
		s3.putObject(new PutObjectRequest("gd3733", "videoSegments/" + id, bais, omd));
		
		return true;
		
	}
	
	@Override 
	public UploadVidSegResponse handleRequest(UploadVidSegRequest req, Context context)  {
		
		logger = context.getLogger();
		logger.log(req.toString());

		UploadVidSegResponse response;
		
		try {
			byte[] encoded = java.util.Base64.getDecoder().decode(req.base64EncodedValue);
			if (req.system) {
				if (uploadVidSegToS3(req.name, encoded)) {
					response = new UploadVidSegResponse(req.name);
				} else {
					response = new UploadVidSegResponse(req.name, 422);
				}
			} else {
				String contents = new String(encoded);
				double value = Double.valueOf(contents);
				
				if (uploadVidSegToRDS(req.name, value)) {
					response = new UploadVidSegResponse(req.name);
				} else {
					response = new UploadVidSegResponse(req.name, 422);
				}
			}
		} catch (Exception e) {
			response = new UploadVidSegResponse("Unable to create constant: " + req.name + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
