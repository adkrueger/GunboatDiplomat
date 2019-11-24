package gunboatdiplomat.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import gunboatdiplomat.model.VidSeg;

/**TODO
 * 11/21/19
 * @author Shannon Truong 
 * for deleteVidSeg method, make sure to edit and check for the isLocal
 * attribute for a request to delete a video segment
 * 		ie. it can only be deleted if the video segment being requested
 * 			is local to the library and not from a remote library
 */

public class VideoSegmentDAO {
	
	java.sql.Connection connection;

	public VideoSegmentDAO() {
		try {
			connection = DatabaseUtil.connect();
			System.out.println("Connection has passed!");
		}
		catch (Exception e) {
			connection = null;
			System.out.println("Connection has failed!");
		}
	}


	public VidSeg getVidSeg(String id) throws Exception {

		try {
			VidSeg vs = null;
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM VideoSegment WHERE video_id=?;");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				vs = generateVidSeg(rs);
			}
			rs.close();
			ps.close();

			return vs;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting video segment: " + e.getMessage());
		}

	}
	
	public boolean addVidSeg(VidSeg vs) throws Exception {
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO VideoSegment (video_id,character_speaking,quote,season,episode,is_local,is_marked) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1, vs.id);
			ps.setString(2, vs.character);
			ps.setString(3, vs.quote);
			ps.setInt(4, vs.seasonNum);
			ps.setInt(5, vs.episodeNum);
			ps.setInt(6, vs.isLocal);
			ps.setInt(7, vs.isMarked);
			ps.execute();
			return true;
			
		}
		catch(Exception e) {
			throw new Exception("Failed to insert video segment: " + e.getMessage());
		}
		
	}
	
	public boolean deleteVidSeg(VidSeg vs) throws Exception {
		try { 
			
			//Check to see if VidSeg Exists
			if(vs.equals(getVidSeg(vs.id))) {
				
				//If yes, then delete. 
				PreparedStatement ps = connection.prepareStatement("DELETE FROM VideoSegment WHERE video_id=?;");
				ps.setString(1, vs.id);
				ps.executeUpdate();
				return true;
			}
			
			//VidSeg does not exist.
			else {return false;}
			
		}
		catch(Exception e) {
			System.out.println("Something went wrong!");
			return false;
		}
	}

	public List<VidSeg> getAllVidSegs() throws Exception {
		
		List<VidSeg> allVidSegs = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM VideoSegment";
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				VidSeg c = generateVidSeg(rs);
				allVidSegs.add(c);
			}
			rs.close();
			statement.close();
			
			return allVidSegs;
		}
		catch(Exception e) {
			throw new Exception("Failed to get video segments: " + e.getMessage());
		}
		
	}
	
	private VidSeg generateVidSeg(ResultSet rs) throws Exception {
		
		String id = rs.getString("video_id");
		String character = rs.getString("character_speaking");
		String quote = rs.getString("quote");
		int season = rs.getInt("season");
		int episode = rs.getInt("episode");
		int isLocal = rs.getInt("is_local");
		int isMarked = rs.getInt("is_marked");
		
		return new VidSeg(id, character, quote, season, episode, isLocal, isMarked);
	}

}
