package gunboatdiplomat;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import gunboatdiplomat.db.VideoSegmentDAO;
import gunboatdiplomat.http.AllVidSegsResponse;
import gunboatdiplomat.model.VidSeg;

public class ListVidSegsHandler implements RequestHandler<Object,AllVidSegsResponse>{

	private AmazonS3 s3 = null;
	public LambdaLogger logger;

	List<VidSeg> getVidSegs() throws Exception {
		logger.log("in getVideoSegments");
		VideoSegmentDAO dao = new VideoSegmentDAO();

		return dao.getAllVidSegs();
	}

	//TODO: change name and implement
	List<VidSeg> systemConstants() throws Exception {
		logger.log("in systemConstants");
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 succeed");
		}
		ArrayList<VidSeg> folderVidSegs = new ArrayList<>();

		// retrieve listing of all objects in the designated bucket
		ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request()
				.withBucketName("gd3733")    // top-level bucket
				.withPrefix("vidsegs");       // sub-folder declarations here (i.e., a/b/c)


		// request the s3 objects in the s3 bucket 'cs3733wpi/constants' -- change based on your bucket name
		logger.log("process request");
		ListObjectsV2Result result = s3.listObjectsV2(listObjectsRequest);
		logger.log("process request succeeded");
		List<S3ObjectSummary> objects = result.getObjectSummaries();

		for (S3ObjectSummary os: objects) {
			String name = os.getKey();
			logger.log("S3 found:" + name);

			// If name ends with slash it is the 'constants/' bucket itself so you skip
			if (name.endsWith("/")) { continue; }

			S3Object obj = s3.getObject("gd3733", name);

			try (S3ObjectInputStream vidSegStream = obj.getObjectContent()) {
				Scanner sc = new Scanner(vidSegStream);
				String id = sc.nextLine();
				String character = sc.nextLine();
				String quote = sc.nextLine();
				String seasonNum = sc.nextLine();
				String episodeNum = sc.nextLine();
				String isLocal = sc.nextLine();
				String isMarked = sc.nextLine();

				sc.close();

				// just grab name *after* the slash. Note this is a SYSTEM constant
				int postSlash = name.indexOf('/');
				//Check on this later on!!!
				folderVidSegs.add(new VidSeg(id, character, quote, Integer.valueOf(seasonNum), Integer.valueOf(episodeNum), Integer.valueOf(isLocal), Integer.valueOf(isMarked)));
			} 
			catch (Exception e) {
				logger.log("Unable to parse contents of " + name);
			}
		}
		return folderVidSegs;
	}
	
	@Override
	public AllVidSegsResponse handleRequest(Object input, Context context)  {
		logger = context.getLogger();
		logger.log("loading Java Lambda handler to list all video segments");

		AllVidSegsResponse response;
		
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			List<VidSeg> list = getVidSegs();
			for (VidSeg c : systemConstants()) {
				if (!list.contains(c)) {
					list.add(c);
				}
			}
			response = new AllVidSegsResponse(list, 200);
		} 
		catch (Exception e) {
			response = new AllVidSegsResponse(403, e.getMessage());
		}
		
		return response;
	}	
	
}