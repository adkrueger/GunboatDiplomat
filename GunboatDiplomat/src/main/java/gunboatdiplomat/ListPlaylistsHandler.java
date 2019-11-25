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

	List<Playlist> getPlaylists() throws Exception {
		logger.log("in Playlists");
		PlaylistDAO dao = new PlaylistDAO();

		return dao.getAllPlaylists();
	}

	List<Playlist> systemPlaylists() throws Exception {
		logger.log("in systemPlaylists...");

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
				Scanner sc = new Scanner(playlistStream);
				String id = sc.nextLine();
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
			
			List<Playlist> list = getPlaylists();
			
			for(Playlist p : systemPlaylists()) {
				if(!list.contains(p)) {
					list.add(p);
				}
			}
			
			response = new ListPlaylistsResponse(list, 200);
		}
		catch (Exception e) {
			response = new ListPlaylistsResponse(403, e.getMessage());
		}

		return response;

	}

}
