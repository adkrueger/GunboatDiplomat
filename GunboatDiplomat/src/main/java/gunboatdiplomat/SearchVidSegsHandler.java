package gunboatdiplomat;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.SearchVidSegsRequest;
import gunboatdiplomat.http.SearchVidSegsResponse;
import gunboatdiplomat.model.VidSeg;

public class SearchVidSegsHandler implements RequestHandler<SearchVidSegsRequest, SearchVidSegsResponse> {

	private AmazonS3 s3 = null;

	LambdaLogger logger;
	
	public List<VidSeg> searchRDS(String character, String quote) throws Exception {
		
		List<VidSeg> finalList = new ArrayList<>();
		List<VidSeg> charList = new ArrayList<>();
		List<VidSeg> quoteList = new ArrayList<>();

		VideoSegmentDAO dao = new VideoSegmentDAO();
		if(character != null) {
			charList = dao.getVidSegsByCharacter(character);
		}
		if(quote != null) {
			quoteList = dao.getVidSegsByQuote(quote);
		}
		
		for(VidSeg vs : charList) {
			finalList.add(vs);
		}
		for(VidSeg vs : quoteList) {
			finalList.add(vs);
		}
		
		return finalList;
		
	}
	
	@Override
	public SearchVidSegsResponse handleRequest(SearchVidSegsRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());

		SearchVidSegsResponse response = null;		// TODO: remove " = null;"
		
		return response;
		
	}

}
