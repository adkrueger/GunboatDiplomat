package gunboatdiplomat.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gunboatdiplomat.model.Playlist;

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
	
	public boolean addVidSegToPlaylist(String playlistName, String vidID) throws Exception {

			PreparedStatement ps = conn.prepareStatement("INSERT INTO Playlist (video_id,playlist_title) VALUES (?,?);");
			
			ps.setString(1, vidID);
			ps.setString(2, playlistName);
			ps.execute();
			
			return true;
			
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

	public List<VidSeg> getPlaylistVidSeg(String playlistName) throws Exception {

		List<VidSeg> ls = new ArrayList<>();
		PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM VideoSegment v JOIN Playlist p WHERE p.video AND (p.video_id = v.video_id AND p.playlist_title = ?);");

		ps.setString(1, playlistName);
		ResultSet rs_playlist = ps.executeQuery(); // return all the video_id that are in that playlist.

		while (rs_playlist.next()) {
			VidSeg vs = generateVidSeg(rs_playlist);
			if(!ls.contains(vs)) {
				ls.add(vs);
			}	
		}

		ps.close();
		rs_playlist.close();

		return ls;

	}

	public boolean deleteVidSegFromPlaylist(String video_id) throws Exception {

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
				playlists.get(rs1.getString("playlist_title")).add(generateVidSeg(rs1.getString("video_id")));

			}

			// Playlist doesnt exist

			else {
				List<VidSeg> vsList = new ArrayList<>();

				// create new input in hashmap;
				playlists.put(rs1.getString("playlist_title"), vsList);
				String vidID = rs1.getString("video_id");
				VidSeg vs = generateVidSeg(vidID);
				playlists.get(rs1.getString("playlist_title")).add(vs);

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
			idNum = rs5.getString(1);
			quote = rs5.getString(3);
			character = rs5.getString(2);
			isLocal = rs5.getInt(4);
			isMarked = rs5.getInt(5);
		}

		return new VidSeg(idNum, quote, character, isLocal, isMarked);

	}

	private VidSeg generateVidSeg(ResultSet rs) throws Exception {

		String id = rs.getString(1);
		String character = rs.getString("character_speaking");
		System.out.println(id);
		String quote = rs.getString("quote");
		int isLocal = rs.getInt("is_local");
		int isMarked = rs.getInt("is_marked");

		System.out.println(id + "has been pulled.");

		return new VidSeg(id, character, quote, isLocal, isMarked);
	}
	
	//TODO: this method takes in the playlist name and returns all the video segments in
	//		that given playlist
	public List<VidSeg> getVideoSegInPlaylist(String playlistName) throws Exception {	//one parameter should be the playlist name
													//Just did this because there were errors in the other handler...oops
		List<VidSeg> vsList = new ArrayList<>();
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Playlist WHERE playlist_title = ?");
		ps.setString(1, playlistName);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			VidSeg vs = generateVidSeg(rs.getString("video_id"));
			vsList.add(vs);
			
		}
		
		
		
		return vsList;
	}
}