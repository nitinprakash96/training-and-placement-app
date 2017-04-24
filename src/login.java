

import java.sql.*;

public class login {
	
	private static Connection con;
	
	
	private login() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentID", "root", "inspiron34");
		    }
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if(con != null) {
			return con;
		}
		else {
			new login();
			return login.con;
		}
	}
	/*
    Statement st = con.createStatement();
    ResultSet rs;
    rs = st.executeQuery("select * from StudentInfo where username='" + userid + "' and password='" + pwd + "'");
    if (rs.next()) {
        session.setAttribute("userid", userid);
        //out.println("welcome " + userid);
        //out.println("<a href='logout.jsp'>Log out</a>");
        response.sendRedirect("success.jsp");
    } else {
        out.println("Invalid password <a href='index.jsp'>try again</a>");
    }*/
}
