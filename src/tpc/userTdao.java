package tpc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Company.Company;
import alumni.alumni;
import models.ConnectionManager;
import models.user;

public class userTdao {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	
	public static int Count(){
		int c=0;
		try{
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery("select * from tpcMember");
			if(rs.next()){
				c = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return c;
	}
	
	public static void register(tpcMember t){
		try{
			
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery("select * from tpcMember where username=" + "'" + t.getName() + "'" + " and password ='" + t.getPass() + "'");
			if(rs.next()){
				tpcMember.rflag=false;
			}
			else{
				tpcMember.rflag=true;
			int p = stmt.executeUpdate("insert into tpcMember values(" + t.getId() + ",'" + t.getName() + "','" + t.getEmail() +"','" + t.getFreeSlot() + "','" + t.getPass() + "'," + t.getYear() + ")");
			}
		}
		catch(Exception e){
			System.out.println("error in tpc member registration\n");
			//e.printStackTrace();
		}
	}
	public static tpcHead login(tpcHead t){
		String username = t.getName();    
		String password = t.getPass();
		String searchQuery = "select * from tpcHead where name=" + "'" + username + "'" + " and password ='" + password + "'";
		try{
			 currentCon = ConnectionManager.getConnection();
	         stmt=currentCon.createStatement();
	         rs = stmt.executeQuery(searchQuery);
	         if (rs.next()) {
	        	 t.setValid(true);
	        	 tpcHead.lflag=true;
					t.setName(rs.getString("name"));
					t.setPass(rs.getString("password"));
	         }
	         else{
	        	 tpcHead.lflag=false;
	        	 t.setValid(false);
	         }

		 }
		 catch(Exception e){
			 System.out.println("error in tpc head login\n");
		 }
		
		return t;	
	}
	public static tpcMember login(tpcMember t){
	    
		String username = t.getName();    
		String password = t.getPass();
		//Date regDate= u.getregDate();
		//String email = u.getEmail();

		String searchQuery = "select * from tpcMember where name=" + "'" + username + "'" + " and password ='" + password + "'";
		 
		 try{
			 currentCon = ConnectionManager.getConnection();
	         stmt=currentCon.createStatement();
	         rs = stmt.executeQuery(searchQuery);
	         if (rs.next()) {
	        	 t.setValid(true);
	        	 tpcMember.lflag=true;
					t.setName(rs.getString("name"));
					t.setId(rs.getInt("id"));
					t.setEmail(rs.getString("email"));
					t.setFreeSlot(rs.getString("freSlot"));
					t.setPass(rs.getString("password"));
					t.setYear(rs.getInt("year"));
	         }
	         else{
	        	 tpcMember.lflag=false;
	        	 t.setValid(false);
	         }

		 }
		 catch(Exception e){
			 System.out.println("error in tpc member login\n");
		 }
		
		return t;	
		
	}
	public static void Sdelete(user u){
		String username = u.getName();    
		String password = u.getPass();
		try{
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery("select * from Student whereusername=" + "'" + username + "'" + " and password ='" + password + "'");
			if(rs.next()){
				int p = stmt.executeUpdate("delete from Student where username=" + "'" + username + "'" + " and password ='" + password + "'");
				tpcMember.dflag=true;
			}
			else{
				tpcMember.dflag=false;
			}
		}
		catch(Exception e){
			System.out.println("error in deleting a Student info\n ");
			}
	}
	public static void Adelete(alumni a){
		String username = a.getName();    
		String password = a.getPass();
		try{
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery("select * from alumni where name=" + "'" + username + "'" + " and password ='" + password + "'");
			if(rs.next()){
				int p = stmt.executeUpdate("delete from alumni where name=" + "'" + username + "'" + " and password ='" + password + "'");
				tpcMember.dflag=true;
			}
			else{
				tpcMember.dflag=false;
			}
		}
		catch(Exception e){
			System.out.println("error in deleting alumni info\n");
			}
	}
	public static void Tdelete(tpcMember t){
		String username = t.getName();    
		String password = t.getPass();
		try{
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery("select * from tpcMember where name=" + "'" + username + "'" + " and password ='" + password + "'");
			if(rs.next()){
				int p = stmt.executeUpdate("delete from tpcMember where name=" + "'" + username + "'" + " and password ='" + password + "'");
				tpcHead.dflag=true;
			}
			else{
				tpcHead.dflag=false;
			}
		}
		catch(Exception e){
			System.out.println("error in deleting tpc member info\n");
			}
	}
	public static void Cdelete(Company c){
		String username = c.getName();    
		String password = c.getPass();
		try{
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery("select * from Company where Cname=" + "'" + username + "'" + " and pass ='" + password + "'");
			if(rs.next()){
				int p = stmt.executeUpdate("delete from Company where Cname=" + "'" + username + "'" + " and pass ='" + password + "'");
				tpcMember.dflag=true;
			}
			else{
				tpcMember.dflag=false;
			}
		}
		catch(Exception e){
			System.out.println("error in deleting company info\n");
			}
	}
	public static ArrayList<tpcMember> showTpc(){
		//Company c[] =  null;
		ArrayList<tpcMember> u = new ArrayList<tpcMember>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			rs = st.executeQuery("select * from tpcMember");
			//c = new Company[4];
			while(rs.next()){
				tpcMember a = new tpcMember();
				//c[i] = new Company();
				a.setName(rs.getString("name"));
				a.setId(rs.getInt("id"));
				a.setEmail(rs.getString("email"));
				a.setFreeSlot(rs.getString("freSlot"));
				a.setPass(rs.getString("password"));
				a.setYear(rs.getInt("year"));
				u.add(a);
			}
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe username/password is invalid");
            System.out.println("error in showing tpc members list\n");
           // ex.printStackTrace();
        } 
		finally {
            if (currentCon != null) {
                try {
                	currentCon.close();
                	}
                catch (SQLException ex) {
                	System.out.println("Connection closing problem in ShowTpc method in tpc\n");
                  //  ex.printStackTrace();
            	}
            }
        }
		return u;
	}
}

