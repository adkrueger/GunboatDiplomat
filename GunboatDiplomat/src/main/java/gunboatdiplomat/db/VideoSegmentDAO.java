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
	
	public boolean markVidSeg(String id) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE VideoSegment SET is_marked = 1 WHERE video_id = ?");
			ps.setString(1, id);
			
			ps.executeUpdate();
			
			return true;
			
		}
		catch(Exception e){
			System.out.println("Video Segment was not successfully marked");
			return false;
		}
	}
	
	public boolean unmarkVidSeg(String id) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE VideoSegment SET is_marked = 0 WHERE video_id = ?");
			ps.setString(1, id);
			
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			System.out.println("Video Segment was not successfully unmarked");
			return false;
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
	
	public List<VidSeg> getVidSegsByCharacter(String character) throws Exception {

		List<VidSeg> listOfVS = new ArrayList<>();
		
		try {
			VidSeg vs = null;
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM VideoSegment WHERE character_speaking=?;");
			ps.setString(1, character);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				vs = generateVidSeg(rs);
				listOfVS.add(vs);
			}
			rs.close();
			ps.close();

			return listOfVS;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting video segment: " + e.getMessage());
		}

	}
	
	public List<VidSeg> getVidSegsByQuote(String quote) throws Exception {

		List<VidSeg> listOfVS = new ArrayList<>();
		
		try {
			VidSeg vs = null;
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM VideoSegment WHERE quote=?;");
			ps.setString(1, quote);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				vs = generateVidSeg(rs);
				listOfVS.add(vs);
			}
			rs.close();
			ps.close();

			return listOfVS;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting video segment: " + e.getMessage());
		}

	}
	
	public boolean addVidSeg(VidSeg vs) throws Exception {
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO VideoSegment (video_id,character_speaking,quote,is_local,is_marked) VALUES(?,?,?,?,?);");
			ps.setString(1, vs.id);
			ps.setString(2, vs.character);
			ps.setString(3, vs.quote);
			ps.setInt(4, vs.isLocal);
			ps.setInt(5, vs.isMarked);
			ps.execute();
			return true;
			
		}
		catch(Exception e) {
			throw new Exception("Failed to insert video segment: " + e.getMessage());
		}
		
	}
	
	public boolean deleteVidSeg(String video_id) throws Exception {
		try {
			//Check to see if VidSeg Exists
			if(video_id.equals(getVidSeg(video_id).id)) {
				System.out.println(getVidSeg(video_id));
				//If yes, then delete. 
				PreparedStatement ps = connection.prepareStatement("DELETE FROM VideoSegment WHERE video_id=?;");
				ps.setString(1, video_id);
				ps.executeUpdate();
				return true;
			}
			//VidSeg does not exist.
			else { return false; }
			
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
			String query = "SELECT * FROM VideoSegment;";
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				VidSeg vs = generateVidSeg(rs);
				allVidSegs.add(vs);
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
		int isLocal = rs.getInt("is_local");
		int isMarked = rs.getInt("is_marked");
		
		return new VidSeg(id, character, quote, isLocal, isMarked);
	}

}
