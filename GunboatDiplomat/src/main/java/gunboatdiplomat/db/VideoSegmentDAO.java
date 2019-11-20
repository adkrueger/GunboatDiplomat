package gunboatdiplomat.db;

import java.sql.*;

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


}//end class VideoSegmentDAO
