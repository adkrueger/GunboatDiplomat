package gunboatdiplomat.db;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import gunboatdiplomat.model.RemoteSite;

public class RemoteSiteDAO {

	java.sql.Connection connection;
	
	public RemoteSiteDAO() {
		try {
			connection = DatabaseUtil.connect();
			System.out.println("Connection has passed!");
		}
		catch (Exception e) {
			connection = null;
			System.out.println("Connection has failed!");
		}
	}
	
	public RemoteSite getRemoteSite(String url) throws Exception {
		
		try {
			RemoteSite rs = null;
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM RemoteSite WHERE url=?;");
			ps.setString(1, url);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				rs = generateRemoteSite(result);
			}
			
			result.close();
			ps.close();
			
			return rs;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting remote site: " + e.getMessage());
		}
		
	}
	
	public boolean addRemoteSite(RemoteSite rs) throws Exception {
		
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO RemoteSite(url) VALUES(?);");
			
			ps.setString(1, rs.url);
			ps.execute();
			
			return true;
		}
		catch(Exception e) {
			throw new Exception("Failed to insert remote site: " + e.getMessage());
		}
		
	}
	
	public boolean deleteRemoteSite(RemoteSite rs) throws Exception {
		
		try {
			// attempt to find remote site
			if(rs.url.equals(getRemoteSite(rs.url).url)) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM RemoteSite WHERE url=?;");
				ps.setString(1, rs.url);
				ps.executeUpdate();
				return true;
			}
			else { return false; }	// remote site doesn't exist
		}
		catch(Exception e) {
			System.out.println("Something went wrong!");
			return false;
		}
		
	}
	
	public ArrayList<RemoteSite> getAllRemoteSites() throws Exception {
		
		ArrayList<RemoteSite> allRemoteSites = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM RemoteSite;";
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				RemoteSite site = generateRemoteSite(rs);
				allRemoteSites.add(site);
			}
			
			rs.close();
			statement.close();
			
			return allRemoteSites;
		}
		catch(Exception e) {
			throw new Exception("Failed to get video segments: " + e.getMessage());
		}
		
	}
	
	private RemoteSite generateRemoteSite(ResultSet rs) throws Exception {
		
		String url = rs.getString("url");
		
		return new RemoteSite(url);
		
	}
}
