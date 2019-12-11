package gunboatdiplomat.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gunboatdiplomat.model.VidSeg;

public class PlaylistDAO {

	java.sql.Connection conn;

	public PlaylistDAO() {
		try {
			conn = DatabaseUtil.connect();
			System.out.println("Connection has passed!");

			PreparedStatement ps = conn.prepareStatement("SET SQL_SAFE_UPDATES = 0");
			ps.execute();
		}

		catch (Exception e) {
			conn = null;
			System.out.println("Connection has failed!");
		}

	}
	
	public boolean checkPlaylistExists(String playlistName) throws SQLException {
		PreparedStatement vsPlaylist = conn.prepareStatement("SELECT * FROM Playlist WHERE playlist_title = ?");
		vsPlaylist.setString(1, playlistName);
		
		ResultSet rs = vsPlaylist.executeQuery();
		
		if(rs.next()) {
			return true;
		}
		
		return false;
	}

	public boolean addVidSegToPlaylist(String playlistName, String vidID) throws Exception {

		//Check to see if it exits in VSTable
		PreparedStatement VSPlaylist = conn.prepareStatement("SELECT * FROM VideoSegment WHERE video_id = ?");

		VSPlaylist.setString(1, vidID);
		ResultSet rs = VSPlaylist.executeQuery();

		try {
			if(rs.next()) {
				PreparedStatement PSPlaylist = conn.prepareStatement("INSERT INTO Playlist (video_id,playlist_title) VALUES (?,?);");
				PSPlaylist.setString(1, vidID);
				PSPlaylist.setString(2, playlistName);
				PSPlaylist.execute();

				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		return false;




	}

	public boolean deletePlaylist(String playlistName) throws Exception {

		// Deleting all songs associated with playlist_title <playlistName>
		PreparedStatement ps = conn.prepareStatement("DELETE FROM Playlist WHERE playlist_title = ?;");
		ps.setString(1, playlistName);
		ps.executeUpdate();

		// Checking to see if the playlist still exists.
		PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Playlist WHERE playlist_title = ?;");
		ps1.setString(1, playlistName);
		ResultSet rs2 = ps1.executeQuery();

		while (rs2.next()) {
			return false;
		}
		ps1.close();
		return true;
	} 

	/**
	 * This function will delete a video segment that is in a certain playlist. 
	 * 
	 * @param playlistName
	 * @param video_id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteVidSegFromPlaylist(String playlistName, String video_id) throws Exception {

		// Deleting VidSeg from Playlist.
		PreparedStatement ps = conn.prepareStatement("DELETE FROM Playlist WHERE video_id = ? AND playlist_title = ?");
		ps.setString(1, video_id);
		ps.setString(2, playlistName);
		ps.executeUpdate();

		return true;

	}
	/**
	 * This function will delete a video segment from all playlists that contain it. 
	 * 
	 * @param video_id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteVidSegFromAllPlaylists(String video_id) throws Exception {

		// Deleting VidSeg from Playlist.
		PreparedStatement ps = conn.prepareStatement("DELETE FROM Playlist WHERE video_id = ?");
		ps.setString(1, video_id);
		ps.executeUpdate();

		// Check to see if VidSeg is still there
		PreparedStatement checkForVidSeg = conn.prepareStatement("SELECT * FROM Playlist WHERE video_id = ?;");
		ResultSet rs = checkForVidSeg.executeQuery();

		while (rs.next()) {
			return false;
		}

		rs.close();
		ps.close();

		return true;

	}

	public HashMap<String, List<VidSeg>> getAllPlaylists() throws Exception {

		HashMap<String, List<VidSeg>> playlists = new HashMap<String, List<VidSeg>>();
		PreparedStatement ps4 = conn.prepareStatement("SELECT * FROM Playlist;");
		ResultSet rs1 = ps4.executeQuery();

		// System.out.println(rs1.getString("playlist_title"));

		while (rs1.next()) {

			System.out.println(rs1.getString("video_id"));

			// if the playlist already exists.
			if (playlists.containsKey(rs1.getString("playlist_title"))) {

				System.out.println(rs1.getString("playlist_title"));

				// Adds the VidSeg
				playlists.get(rs1.getString("playlist_title")).add(generateVidSegFromPlaylist(rs1));

			}

			// Playlist doesnt exist

			else {
				if(rs1.getString("video_id") != null) {
					List<VidSeg> vsList = new ArrayList<>();

					// create new input in hashmap;
					playlists.put(rs1.getString("playlist_title"), vsList);
					//				String vidID = rs1.getString("video_id");
					//				VidSeg vs = generateVidSeg(vidID);
					playlists.get(rs1.getString("playlist_title")).add(generateVidSegFromPlaylist(rs1));

				}

			}

		}

		return playlists;

	}

	private VidSeg generateVidSeg(String id) throws Exception {

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM VideoSegment WHERE video_id = ?;");
		ps.setString(1, id);
		ResultSet rs5 = ps.executeQuery();

		String idNum = "";
		String character = "";
		String quote = "";
		int isLocal = 0;
		int isMarked = 0;

		while (rs5.next()) {
			if(!(rs5.getString(1).equals(null))) {
				idNum = rs5.getString(1);
				quote = rs5.getString(3);
				character = rs5.getString(2);
				isLocal = rs5.getInt(4);
				isMarked = rs5.getInt(5);
			}

		}

		return new VidSeg(idNum, quote, character, isLocal, isMarked);

	}

	private VidSeg generateVidSegFromPlaylist(ResultSet rs) throws Exception {

		String id = "";

		id = rs.getString("video_id");

		System.out.println(id + "has been pulled.");

		return new VidSeg(id);

	}

	public List<VidSeg> getVideoSegInPlaylist(String playlistName) throws Exception {	//one parameter should be the playlist name
		//Just did this because there were errors in the other handler...oops
		List<VidSeg> vsList = new ArrayList<>();

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Playlist WHERE playlist_title = ?");
		ps.setString(1, playlistName);
		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			if(rs.getString("video_id") != null) {
				VidSeg vs = generateVidSegFromPlaylist(rs);
				if(vs.id != null) {
					vsList.add(vs);
				}
			}
		}

		return vsList;
	}

	public boolean deleteVidSegFromPlaylistWithIndex(String playlistName, int index) throws Exception {

		String url = "https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/";
		System.out.println(index);
		// This is getting the list of VidSeg associated with a playlist. 
		List<VidSeg> listOfVidSeg = this.getVideoSegInPlaylist(playlistName);

		String vidSegID = listOfVidSeg.get(index).id;
		System.out.println(listOfVidSeg + "\t deleting " + vidSegID);

		PreparedStatement ps = conn.prepareStatement("DELETE FROM Playlist WHERE video_id=? AND playlist_title=?");
		ps.setString(1, vidSegID);
		ps.setString(2, playlistName);
		ps.execute();

		//Return true
		return true;
	}


	public boolean createPlaylist(String playlistName) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("INSERT INTO Playlist (video_id, playlist_title) VALUES (?, ?);");
		ps.setString(2, playlistName);
		ps.setString(1, null);
		ps.execute();

		return true;
	}
}