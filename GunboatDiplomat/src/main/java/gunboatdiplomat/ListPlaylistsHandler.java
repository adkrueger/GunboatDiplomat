package gunboatdiplomat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

import gunboatdiplomat.db.PlaylistDAO;
import gunboatdiplomat.http.ListPlaylistsResponse;
import gunboatdiplomat.model.Playlist;

public class ListPlaylistsHandler implements RequestHandler<Object, ListPlaylistsResponse> {

	private AmazonS3 s3 = null;
	LambdaLogger logger;

	List<Playlist> getPlaylistsFromRDS() throws Exception {
		logger.log("in Playlists");
		PlaylistDAO dao = new PlaylistDAO();

		return dao.getAllPlaylists();
	}

	List<Playlist> getPlaylistsFromS3() throws Exception {
		logger.log("in getPlaylistsFromS3");

		if(s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 succeed");
		}

		ArrayList<Playlist> folderPlaylists = new ArrayList<Playlist>();

		ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request()
				.withBucketName("gd3733")
				.withPrefix("playlists");

		logger.log("process request");
		ListObjectsV2Result result = s3.listObjectsV2(listObjectsRequest);
		logger.log("process request succeeded");
		List<S3ObjectSummary> objects = result.getObjectSummaries();

		for (S3ObjectSummary os: objects) {
			String name = os.getKey();
			logger.log("S3 found: " + name);

			S3Object obj = s3.getObject("gd3733", name);

			try(S3ObjectInputStream playlistStream = obj.getObjectContent()) {
				int postSlash = name.indexOf('/');
				String id = name.substring(postSlash+1);
				
				Scanner sc = new Scanner(playlistStream);
				String contents = sc.nextLine();		//TODO: this is what is in the file; need to see if we need to do stuff with this
				sc.close();

				folderPlaylists.add(new Playlist(id));
			}

			catch (Exception e) {
				logger.log("Unable to parse contents of " + name);
			}

		}
		return folderPlaylists;
	}

	@Override
	public ListPlaylistsResponse handleRequest(Object req, Context context) {

		logger = context.getLogger();
		logger.log(req.toString());

		ListPlaylistsResponse response;	
		
		try {
			List<Playlist> list = getPlaylistsFromS3();
			//TODO: change this, will need to have more intricacy with video segments and RDS
			response = new ListPlaylistsResponse(list, 200);
		}
		catch (Exception e) {
			response = new ListPlaylistsResponse(403, e.getMessage());
		}

		return response;

	}

}
