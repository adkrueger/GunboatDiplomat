package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.db.VideoSegmentDAO;

public class UploadVidSegHandler {

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	// uploading the video segment (essentially creating a new video segment(
/*	boolean uploadVidSeg(String id) {
		if(logger != null) { logger.log("in uploadVidSeg"); }
		VideoSegmentDAO dao = new VideoSegmentDAO();
		
		VidSeg exist = dao.getVidSeg(id);
		VidSeg vidSeg = new VidSeg(id);
		if(exist == null) {
			return dao.addVidSeg(vidSeg);
		}
		else {
			return false;
		}
	}
*/
}
