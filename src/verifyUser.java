
import java.sql.*;

public class verifyUser {
	
	Connection con;
	
	public boolean check(String user, String password) {
		con = login.getConnection();
		PreparedStatement ps = null;
		String query = "select * from StudentInfo where uname="+user+" and pass = "+password;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("uname")!=null)
					return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
