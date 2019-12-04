package gunboatdiplomat;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import gunboatdiplomat.db.RemoteSiteDAO;
import gunboatdiplomat.http.ListRemotesResponse;
import gunboatdiplomat.model.RemoteSite;

public class ListRemotesHandler implements RequestHandler<Object, ListRemotesResponse> {

	LambdaLogger logger;

	ArrayList<RemoteSite> getRemoteSitesFromRDS() throws Exception {
		logger.log("getting remote URLs");
		RemoteSiteDAO dao = new RemoteSiteDAO();
		
		return dao.getAllRemoteSites();
	}
	
	@Override
	public ListRemotesResponse handleRequest(Object req, Context context) {
		
		logger = context.getLogger();

		ListRemotesResponse response;

		try {
			ArrayList<RemoteSite> rsList = getRemoteSitesFromRDS();
			
			response = new ListRemotesResponse(rsList, 200);
		}
		catch(Exception e) {
			response = new ListRemotesResponse(403, e.getMessage());
		}
		
		return response;
		
	}

}
