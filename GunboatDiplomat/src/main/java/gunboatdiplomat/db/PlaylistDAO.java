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
		PreparedStatement PSPlaylist = conn.prepareStatement("INSERT INTO Playlist (video_id,playlist_title) VALUES (?,?);");
		PSPlaylist.setString(1, vidID);
		PSPlaylist.setString(2, playlistName);
		PSPlaylist.execute();

		return true;
		
	}

	public boolean deletePlaylist(String playlistName) throws Exception {

		PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Playlist WHERE playlist_title = ?;");
		ps1.setString(1, playlistName);
		ResultSet rs = ps1.executeQuery();

		if(rs.next()) {
			// Deleting all video segments associated with playlist_title <playlistName>
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Playlist WHERE playlist_title = ?;");
			ps.setString(1, playlistName);
			ps.executeUpdate();
			return true;
		}
		
		// Checking to see if the playlist still exists.
		ps1.close();
		return false;
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