package gunboatdiplomat.db;

import java.sql.*;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import gunboatdiplomat.model.VidSeg;

public class PlaylistDAO {

	java.sql.Connection conn;

	public PlaylistDAO() {
		try {
			conn = DatabaseUtil.connect();
			System.out.println("Connection has passed!");
		}
		catch (Exception e) {
			conn = null;
			System.out.println("Connection has failed!");
		}
	}


	/**This method uses a playlistname and returns all the VidSeg that are a part of that playlist. 
	 * 
	 * @param playlistName
	 * @return List<VidSeg>
	 * @throws Exception
	 */

	public List<VidSeg> getPlaylistVidSeg(String playlistName) throws Exception{
		List<VidSeg> ls = new ArrayList<>();;

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM VideoSegment v JOIN Playlist p WHERE p.video_id = v.video_id AND p.playlist_title = ?;");
		ps.setString(1, playlistName);
		ResultSet rs_playlist = ps.executeQuery(); // return all the video_id that are in that playlist. 



		while(rs_playlist.next()) {

			VidSeg vs = generateVidSeg(rs_playlist);
			ls.add(vs);
		}
		ps.close();

		return ls;


	}

	public boolean deletePlaylist(String playlistName) throws Exception{

		//Deleting all songs associated with playlist_title <playlistName>
		PreparedStatement ps = conn.prepareStatement("DELETE FROM Playlist WHERE playlist_title = ?;");
		ps.setString(1, playlistName);
		ResultSet rs = ps.executeQuery();

		ps.close();

		//Checking to see if the playlist still exists. 
		PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Playlist WHERE playlist_title = ?;");
		ps1.setString(1, playlistName);
		ResultSet rs2 = ps1.executeQuery();

		while(rs2.next()) {
			return false;
		}
		ps1.close();
		return true;

	}



	/** This method takes in a ResultSet (aka a row from the DB)
	 *  and generates a VidSeg obj. from the VideoSegment table.  
	 * 
	 * @param rs
	 * @return VIdSeg
	 * @throws Exception
	 */

	private VidSeg generateVidSeg(ResultSet rs) throws Exception {


		String id = rs.getString(1);
		String character = rs.getString("character_speaking");
		System.out.println(id);
		String quote = rs.getString("quote");
		int season = rs.getInt("season");
		int episode = rs.getInt("episode");
		int isLocal = rs.getInt("is_local");
		int isMarked = rs.getInt("is_marked");

		System.out.println(id + "has been pulled.");



		return new VidSeg(id, character, quote, season, episode, isLocal, isMarked);
	}

}

