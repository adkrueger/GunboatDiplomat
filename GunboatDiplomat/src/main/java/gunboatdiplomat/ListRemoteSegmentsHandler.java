package gunboatdiplomat;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import gunboatdiplomat.http.ListRemoteSegmentsResponse;
import gunboatdiplomat.model.VidSeg;

public class ListRemoteSegmentsHandler implements RequestHandler<Object, ListRemoteSegmentsResponse> {
	
	public LambdaLogger logger;
	
	@Override
	public ListRemoteSegmentsResponse handleRequest(Object input, Context context) {
		logger = context.getLogger();
		logger.log("In handler to list remote segments");
		
		List<VidSeg> listSegs = new ArrayList<>();
		listSegs.add(new VidSeg("https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/326e583f-d091-4b25-a5a6-e17293a8daec", "Spock", "That should prove very interesting."));
		listSegs.add(new VidSeg("https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/43fc70ff-88eb-4070-859f-452fd82b7a62", "Leonard McCoy", "Death by natural causes."));
		listSegs.add(new VidSeg("https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/8113b05c-a7af-4ae8-9859-aa5b9ff88f77", "James T. Kirk", "I am the captain of the ship and totally capable of commanding her"));
		listSegs.add(new VidSeg("https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/9a0b79b7-14e5-4282-9534-dce29f1b7cb3", "James T. Kirk", "More like love."));
		listSegs.add(new VidSeg("https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/a4769f74-7df0-4db7-8d01-2ad9c6c195a6", "Leonard McCoy", "That's a pretty far out story."));
		listSegs.add(new VidSeg("https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/ae8e1ede-3ffd-4bd8-af3e-6f14e509c8be", "Amanda Grayson", "Well it's sort of a fat teddy bear."));
		listSegs.add(new VidSeg("https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/b0304fba-9f76-4283-89d4-b5bec0c6edd5", "Leonard McCoy", "You touch it, her nearest male relative will have to try to kill you."));
		listSegs.add(new VidSeg("https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/f336fff4-50ba-4529-b98b-c70d1172c05a", "Zefram Cochrane", "Captain, why did you build that translator with a feminine voice?"));
		listSegs.add(new VidSeg("https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/fa4d887c-0123-4a2b-b01d-3d354090fbeb", "Spock", "A most fascinating thing happened."));
		listSegs.add(new VidSeg("https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/4da75de0-50ad-4b90-b442-023f4af43b36", "Leonard McCoy", "Now you must want the child!"));
		
		ListRemoteSegmentsResponse response = new ListRemoteSegmentsResponse(listSegs, 200);
		
		return response;
		
	}
	
}
