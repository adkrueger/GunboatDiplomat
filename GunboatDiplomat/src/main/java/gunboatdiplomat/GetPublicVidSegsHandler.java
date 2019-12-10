package gunboatdiplomat;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.GetPublicVidSegsResponse;
import gunboatdiplomat.model.MarkedSegment;
import gunboatdiplomat.model.VidSeg;

public class GetPublicVidSegsHandler implements RequestHandler<Object, GetPublicVidSegsResponse> {

	LambdaLogger logger;

	//create a new method that takes in a list of video segments from the DAO and then checks to see if each
	//video segment is marked or not and if it is not marked then we remove it and return the list with all
	//the marked video segments

	public List<MarkedSegment> returnMarkedPublic(List<VidSeg> vs) {

		List<MarkedSegment> marked = new ArrayList<>();
		for (int i = 0; i < vs.size(); i++) {
			if (vs.get(i).isMarked == 0) {	//if it is not marked then it is = 0 and it is not public
				vs.remove(i);
				i--;
			}
			else {
				marked.add(new MarkedSegment(vs.get(i).id, vs.get(i).character, vs.get(i).text));
			}
		}
		
		System.out.println(marked.toString());
		
		return marked;
		
	}


	@Override
	public GetPublicVidSegsResponse handleRequest(Object request, Context context) {

		logger = context.getLogger();
		logger.log("loading Java Lambda handler to list all public video segments");

		GetPublicVidSegsResponse response;	
		VideoSegmentDAO dao = new VideoSegmentDAO();

		try {
			List<VidSeg> list = new ArrayList<>();
			list = dao.getAllVidSegs();
			List<MarkedSegment> markedList = returnMarkedPublic(list);
			response = new GetPublicVidSegsResponse(markedList, 200);
		}
		catch (Exception e) {
			response = new GetPublicVidSegsResponse(403, e.getMessage());
		}

		return response;
	}
}