package alumni;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Company.Company;
import models.user;
import models.ConnectionManager;
import models.techStud;

public class Adao {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	static int p;
	
	public static int Count(){
		int c=0;
		try{
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery("select count(*) from alumni");
			if(rs.next()){
				c=rs.getInt(1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return c;
	}
	
	public static void register(alumni a){
		try{
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery("select * from alumni where name=" + "'" + a.getName() + "'" + " and password ='" + a.getPass() + "'");
			if(rs.next()){
				alumni.rflag=false;
			}
			else{
				int p = stmt.executeUpdate("insert into alumni values(" + a.getid() + ",'" + a.getName() + "','" + a.getBatch() +"','" + a.getField() + "','" + a.getWork() + "','" + a.getPass() + "')");
				alumni.rflag=true;
			}
		}
		catch(Exception e){
			System.out.println("error in alumni registration \n");
			//e.printStackTrace();
		}
	}
	
	public static void register(research s){
		try{
			currentCon = ConnectionManager.getConnection();
			Statement stmt=currentCon.createStatement();
			//System.out.println(s.getid());
			p = stmt.executeUpdate("insert into research values(" + s.getid() + ",'" + s.getTopic() +"','" + s.getcoreField() + "'," + s.getStipend() + ")");
		}
		catch(Exception e){
			System.out.println("error in  research field alumni registration \n");
			//e.printStackTrace();
		}
	}
	public static void register(Job s){
		try{
			currentCon = ConnectionManager.getConnection();
			Statement stmt=currentCon.createStatement();
			p = stmt.executeUpdate("insert into job values(" + s.getid() + ",'" + s.getPost() +"','" + s.getCompany() + "'," + s.getPackage() + ")");
		}
		catch(Exception e){
			System.out.println("error in alumni(job) registration\n");
			//e.printStackTrace();
		}
	}
	public static void register(startup s){
		try{
			currentCon = ConnectionManager.getConnection();
			Statement stmt=currentCon.createStatement();
			p = stmt.executeUpdate("insert into startup values(" + s.getid() + ",'" + s.getTopic() +"','" + s.getStName() + "'," + s.getStipend() + ","+ s.getvacancy() + ")");
		}
		catch(Exception e){
			System.out.println("error in registration of alumni (startup)\n");
			//e.printStackTrace();
		}
	}
	public static alumni login(alumni a){
	    
		String username = a.getName();    
		String password = a.getPass();
		//Date regDate= u.getregDate();
		//String email = u.getEmail();

		String searchQuery = "select * from alumni where name=" + "'" + username + "'" + " and password ='" + password + "'";
		 
		 try{
			 currentCon = ConnectionManager.getConnection();
	         stmt=currentCon.createStatement();
	         rs = stmt.executeQuery(searchQuery);
	         if (rs.next()) {
	        	 a.setValid(true);
	        	 alumni.lflag=true;
	        	a.setName(rs.getString("name"));
	        	a.setField(rs.getString("field"));
	        	a.setid(rs.getInt("id"));
	        	a.setWork(rs.getString("work"));
	        	a.setBatch(rs.getString("batch"));
	         }
	         else{
	        	 alumni.lflag=false;
	        	 a.setValid(false);
	         }
		 }
		 catch(Exception e){
			 System.out.println("error in alumni login \n");
		 }
		
		return a;	
	}
	
	public static ArrayList<user> showUser(){
		//Company c[] =  null;
		ArrayList<user> u = new ArrayList<user>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			rs = st.executeQuery("select * from Student");
			//c = new Company[4];
			while(rs.next()){
				user a = new user();
				//c[i] = new Company();
				a.setName(rs.getString("username"));
				a.setid(rs.getInt("Sid"));
				a.setEmail(rs.getString("Email"));
				a.setField(rs.getString("field"));
				a.setPointer(rs.getFloat("Spointer"));
				a.setPass(rs.getString("password"));
				a.setCid(rs.getInt("appliedComp"));
				if(rs.getString("appliedComp")==null)
				u.add(a);
			}
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe username/password is invalid");
            System.out.println("error in showUser method in alumni\n");
            //ex.printStackTrace();
        } 
		finally {
            if (currentCon != null) {
                try {
                	currentCon.close();
                	}
                catch (SQLException ex) {
                	System.out.println("error in Connection closing in showUser method in alumni\n");
                    //ex.printStackTrace();
            	}
            }
        }
		return u;
	}
	public static ArrayList<user> checkField(alumni b){
		ArrayList<user> u=new ArrayList<user>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			rs = st.executeQuery("select * from alumni a ,Student s where (a.field = s.field or a.field = 'both' or s.field='both') and a.id = " + b.getid());
			//c = new Company[4];
			while(rs.next()){
				user a = new user();
				a.setName(rs.getString("username"));
				a.setid(rs.getInt("Sid"));
				a.setEmail(rs.getString("Email"));
				a.setField(rs.getString("field"));
				a.setPointer(rs.getFloat("Spointer"));
				a.setPass(rs.getString("password"));
				a.setCid(rs.getInt("appliedComp"));
				if(rs.getString("appliedComp")==null)
				u.add(a);
			}
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe username/password is invalid");
            System.out.println("error in checkField method in alumni\n");
           // ex.printStackTrace();
        } 
		finally {
            if (currentCon != null) {
                try {
                	currentCon.close();
                	}
                catch (SQLException ex) {
                	System.out.println("error in Connection closing in showUser method in alumni\n");
                    //ex.printStackTrace();
            	}
            }
        }
		return u;
	}
	public static ArrayList<techStud> moreInfo(user u){
		ArrayList<techStud> tu = new ArrayList<techStud>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			rs = st.executeQuery("select * from techStud where id = " + u.getid());
			while(rs.next()){
				techStud a = new techStud();
				a.setSkill(rs.getString("skill"));
				a.setIntern(rs.getString("intern"));
				tu.add(a);
			}
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe username/password is invalid");
            System.out.println("error in moreInfo method in alumni\n");
            //ex.printStackTrace();
        } 
		finally {
            if (currentCon != null) {
                try {
                	currentCon.close();
                	}
                catch (SQLException ex) {
                	System.out.println("error in Connection closing in moreInfo method in alumni\n");
                    //ex.printStackTrace();
            	}
            }
        }
		return tu;
	}
	public static ArrayList<String> showSkills(user b){
		ArrayList<String> s = new ArrayList<String>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			if(b.getField().equals("tech")){
				rs=st.executeQuery("select * from techStud where id = "+ b.getid());
				while(rs.next()){
					s.add(rs.getString("skill"));
				}
			}
			if(b.getField().equals("both")){
				rs=st.executeQuery("select * from bstud where id = "+ b.getid());
				while(rs.next()){
					s.add(rs.getString("skill"));
				}
			}
			if(b.getField().equals("mng")){
				rs=st.executeQuery("select * from mngStud where id = "+ b.getid());
				while(rs.next()){
					s.add(rs.getString("skill"));
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		return s;
	}
}
