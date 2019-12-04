package gunboatdiplomat;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import gunboatdiplomat.db.RemoteSiteDAO;
import gunboatdiplomat.http.RegisterRemoteRequest;
import gunboatdiplomat.http.RegisterRemoteResponse;
import gunboatdiplomat.model.RemoteSite;

public class RegisterRemoteHandler implements RequestHandler<RegisterRemoteRequest, RegisterRemoteResponse> {

	LambdaLogger logger;
	
	public boolean addRemoteToRDS(String addURL) throws Exception {
		
		if(logger != null) { logger.log("in addRemoteToRDS"); }
		RemoteSiteDAO dao = new RemoteSiteDAO();

		RemoteSite exist = dao.getRemoteSite(addURL);
		RemoteSite rs = new RemoteSite(addURL);
		if(exist == null) {
			return dao.addRemoteSite(rs);
		}
		else {
			return false;
		}
		
	}
	
	@Override
	public RegisterRemoteResponse handleRequest(RegisterRemoteRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		RegisterRemoteResponse response;
		
		try {
			if(addRemoteToRDS(req.getUrl())) {
				response = new RegisterRemoteResponse(req.url, 200);
			}
			else {
				response = new RegisterRemoteResponse(req.url, 422);
			}
		}
		catch(Exception e) {
			response = new RegisterRemoteResponse("Unable to register remote site: " + req.url + "(" + e.getMessage() + ")", 400);
		}
		
		return response;
	}

}
