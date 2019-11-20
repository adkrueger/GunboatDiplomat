package db;

public class VidSegDAO {

	java.sql.Connection conn;

    public VidSegDAO() {
    	try  {
    		conn = DatabaseUtility.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	
}
