package gunboatdiplomat.db;

import java.sql.*;

import gunboatdiplomat.model.VidSeg;

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
	}// end constructor


	public boolean getVideoSegment(int s) {

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM VideoSegment WHERE season=?;");
			ps.setInt(1, s);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				System.out.println("season" + rs.getInt(1));
			}
			rs.close();
			ps.close();

			return true;
		}
		catch(Exception e) {
			System.out.print("string not found!");
		}

		return false;

	}
	
	public boolean addVidSeg(VidSeg vs) throws Exception {
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM VideoSegment where video_id = ?;");

			ps = connection.prepareStatement("INSERT INTO VideoSegment (video_id, character_speaking, quote, season, episode, is_local, is_marked) values(?,?,?,?,?,?,?)");
			ps.setString(1, vs.id);
			ps.setString(2, vs.character);
			ps.setString(3, vs.quote);
			ps.setInt(4, vs.seasonNum);
			ps.setInt(5, vs.episodeNum);
			ps.setBoolean(6, vs.isLocal);
			ps.setBoolean(7, vs.isMarked);
			ps.execute();
			return true;
			
		}
		catch(Exception e) {
			throw new Exception("Failed to insert video segment: " + e.getMessage());
		}
		
	}


}//end class VideoSegmentDAO
