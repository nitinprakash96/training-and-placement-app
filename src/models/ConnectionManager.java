package models;

import java.sql.*;

import java.util.*;

public class ConnectionManager {

	static Connection con;
    static String url;
    
    public static Connection getConnection(){
    	
    	try{
    	    Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Placement",
							 "root", "inspiron34");
    	}
    	catch(Exception e){System.out.println("sdhsd");}
    	
    	return con;
    }
	
}
