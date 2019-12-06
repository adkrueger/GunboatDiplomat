package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.db.RemoteSiteDAO;
import gunboatdiplomat.http.UnregisterRemoteRequest;
import gunboatdiplomat.http.UnregisterRemoteResponse;
import gunboatdiplomat.model.RemoteSite;

public class UnregisterRemoteHandler implements RequestHandler<UnregisterRemoteRequest, UnregisterRemoteResponse> {

	LambdaLogger logger;
	
	@Override
	public UnregisterRemoteResponse handleRequest(UnregisterRemoteRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		UnregisterRemoteResponse response;
		RemoteSiteDAO dao = new RemoteSiteDAO();
		
		try {
			
			if(dao.deleteRemoteSite(req.url)) {
				response = new UnregisterRemoteResponse(req.url, 200);
			}
			else {
				response = new UnregisterRemoteResponse(req.url, 422, "Unable to process register request.");
			}
			
		}
		catch(Exception e) {
			response = new UnregisterRemoteResponse(req.url, 403, "Unable to unregister remote: " + req.url + "(" + e.getMessage() + ")");
		}
		
		return response;
		
	}

}
