package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.DeleteVidSegRequest;
import gunboatdiplomat.http.DeleteVidSegResponse;
import gunboatdiplomat.model.VidSeg;

public class DeleteVidSegHandler implements RequestHandler<DeleteVidSegRequest, DeleteVidSegResponse> {

	public LambdaLogger logger = null;
	
	@Override
	public DeleteVidSegResponse handleRequest(DeleteVidSegRequest request, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java LAmbda handler to delete video segment...");
		
		DeleteVidSegResponse response = null;
		logger.log(request.toString());
		
		VideoSegmentDAO dao = new VideoSegmentDAO();
		
		VidSeg vidseg = new VidSeg(request.id, "", "", 0, 0);
		try {
			if (dao.deleteVidSeg(vidseg)) {
				response = new DeleteVidSegResponse(request.id, 200);
			} 
			else {
				response = new DeleteVidSegResponse(request.id, 422, "Unable to delete constant.");
			}
		} 
		catch (Exception e) {
			response = new DeleteVidSegResponse(request.id, 403, "Unable to delete constant: " + request.id + "(" + e.getMessage() + ")");
		}

		return response;
	}
	
}
