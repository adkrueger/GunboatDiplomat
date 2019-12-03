package gunboatdiplomat.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	
	private RemoteSite generateRemoteSite(ResultSet rs) throws Exception {
		
		String url = rs.getString("url");
		
		return new RemoteSite(url);
		
	}
}
