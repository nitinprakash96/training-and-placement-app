package Company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import alumni.Adao;
import alumni.alumni;
import models.ConnectionManager;
import models.bstud;
import models.mngStud;
import models.techStud;
import models.user;

public class Cdao {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	static int p;
	
	public static int Count(){
		int c=0;
		try{
			currentCon = ConnectionManager.getConnection();
	         stmt=currentCon.createStatement();
	         rs = stmt.executeQuery("select count(*) from Company");
	         if(rs.next()){
	        	 c = rs.getInt(1);
	         }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return c;
	}
	
public static Company login(Company c){
	    
		String username = c.getName();    
		String password = c.getPass();
		//Date regDate= u.getregDate();
		//String email = u.getEmail();

		String searchQuery = "select * from Company where Cname=" + "'" + username + "'" + " and pass ='" + password + "'";
		 
		 try{
			 currentCon = ConnectionManager.getConnection();
	         stmt=currentCon.createStatement();
	         rs = stmt.executeQuery(searchQuery);
	         if (rs.next()) {
	        	c.setValid(true);
	        	Company.lflag=true;
				c.setName(rs.getString("Cname"));
				c.setid(rs.getInt("Cid"));
				c.setPackage(rs.getInt("package"));
				c.setCount(rs.getInt("count"));
				c.setField(rs.getString("Cfield"));
				c.setPass(rs.getString("pass"));
				c.setSid(rs.getInt("appliedStud"));
	         }
	         else{
	        	 Company.lflag=false;
	        	 c.setValid(false);
	        	 System.out.println("Invalid password ");
	         }
		 }
		 catch(Exception e){
			 System.out.println("error in  company login\n");
		 }
		
		return c;	
	}

public static ArrayList<user> skillUser(Company c){
	//Company c[] =  null;
	ArrayList<user> u=new ArrayList<user>();
	try{
		currentCon = ConnectionManager.getConnection();
		Statement st = currentCon.createStatement();
		if(c.getField().equals("tech"))
			rs = st.executeQuery(" select * from Student where Sid in (select s.id from techComp c,techStud s where s.skill=c.skill and c.id=" + c.getid() + ")");
		//c = new Company[4];
		else if(c.getField().equals("mng"))
			rs = st.executeQuery(" select * from Student where Sid in (select s.id from mngComp c,mngStud s where s.skill=c.skill and c.id=" + c.getid() + ")");
		else if(c.getField().equals("both"))
			rs = st.executeQuery(" select * from Student where Sid in (select s.id from bComp c,bstud s where s.skill=c.skill and c.id=" + c.getid() + ")");
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
        System.out.println("An error occurred. Maybe user/password is invalid");
        ex.printStackTrace();
    } 
	finally {
        if (currentCon != null) {
            try {
            	currentCon.close();
            	}
            catch (SQLException ex) {
                ex.printStackTrace();
        	}
        }
    }
	return u;
}
public static void register(Company a){
	try{
		currentCon = ConnectionManager.getConnection();
		stmt=currentCon.createStatement();
		rs = stmt.executeQuery("select * from Company where Cname=" + "'" + a.getName() + "'" + " and pass ='" + a.getPass() + "'");
		if(rs.next()){
			Company.rflag=false;
		}
		else{
			int p = stmt.executeUpdate("insert into Company values('" + a.getName() + "'," + a.getid() + "," + a.getPackage() +"," + a.getCount() + "," + a.getPointer() + ",'" + a.getField() +  "','" + a.getPass()  + "',null)");
			Company.rflag=true;
		}
	}
	catch(Exception e){
		System.out.println("error in company registration\n");
		e.printStackTrace();
	}
}
public static void register(techComp c){
	try{
		currentCon = ConnectionManager.getConnection();
		Statement stmt=currentCon.createStatement();
		p = stmt.executeUpdate("insert into techComp values(" + c.getid() + ",'" + c.getSkill() + "')");
	}
	catch(Exception e){
		System.out.println("error in  technical company registration\n");
		//e.printStackTrace();
	}
}
public static void register(mngComp c){
	try{
		currentCon = ConnectionManager.getConnection();
		Statement stmt=currentCon.createStatement();
		p = stmt.executeUpdate("insert into mngComp values(" + c.getid() + ",'" + c.getSkill()  + "')");
	}
	catch(Exception e){
		System.out.println("error in management company registration\n");
		//e.printStackTrace();
	}
}
public static void register(bComp c){
	try{
		currentCon = ConnectionManager.getConnection();
		Statement stmt=currentCon.createStatement();
		p = stmt.executeUpdate("insert into bComp values(" + c.getid() + ",'" + c.getSkill() + "')");
	}
	catch(Exception e){
		System.out.println("error in  business company registration\n");
		//e.printStackTrace();
	}
}

public static ArrayList<user> checkField(Company b){
	ArrayList<user> u=new ArrayList<user>();
	try{
		currentCon = ConnectionManager.getConnection();
		Statement st = currentCon.createStatement();
		rs = st.executeQuery("select * from Company a ,Student s where (a.Cfield = s.field or a.Cfield = 'both' or s.field='both') and a.Cid = " + b.getid());
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
			if(rs.getString("appliedStud")==null && rs.getString("appliedComp")==null)
			u.add(a);
		}
	}
	catch (SQLException ex) {
        System.out.println("An error occurred. Maybe username/password is invalid");
        System.out.println("error in checkField function of Company\n");
       // ex.printStackTrace();
    } 
	finally {
        if (currentCon != null) {
            try {
            	currentCon.close();
            	}
            catch (SQLException ex) {
            	System.out.println("Connection closing problem in checkField of Company\n");
               // ex.printStackTrace();
        	}
        }
    }
	return u;
}
public static ArrayList<user> checkPointer(Company b){
	ArrayList<user> u=new ArrayList<user>();
	try{
		currentCon = ConnectionManager.getConnection();
		Statement st = currentCon.createStatement();
		rs = st.executeQuery("select * from Company,Student where Spointer > Cpointer and Cid=" + b.getid() + "");
		//c = new Company[4];
		while(rs.next()){
			//c[i] = new Company();
			user a = new user();
			a.setName(rs.getString("username"));
			a.setid(rs.getInt("Sid"));
			a.setEmail(rs.getString("Email"));
			a.setField(rs.getString("field"));
			a.setPointer(rs.getFloat("Spointer"));
			a.setPass(rs.getString("password"));
			a.setCid(rs.getInt("appliedComp"));
			if(rs.getString("appliedStud")==null && rs.getString("appliedComp")==null)
			u.add(a);
		}
	}
	catch (SQLException ex) {
        System.out.println("An error occurred. Maybe user/password is invalid");
        System.out.println("error in checkPointer function in company\n");
       // ex.printStackTrace();
    } 
	finally {
        if (currentCon != null) {
            try {
            	currentCon.close();
            	}
            catch (SQLException ex) {
            	System.out.println("Connection closing problem in CheckPointer of Company\n");
                //ex.printStackTrace();
        	}
        }
    }
	return u;
}
public static ArrayList<user> checkApllied(Company b){
	ArrayList<user> u=new ArrayList<user>();
	try{
		currentCon = ConnectionManager.getConnection();
		Statement st = currentCon.createStatement();
		rs = st.executeQuery("select * from Student where Sid in ( select appliedStud from Company where Cname = " + b.getName() + ")");
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
			if(rs.getString("appliedComp")!=null)
			u.add(a);
		}
	}
	catch (SQLException ex) {
        System.out.println("An error occurred. Maybe user/password is invalid");
        System.out.println("error in checkApllied method in Company  \n");
       // ex.printStackTrace();
    } 
	finally {
        if (currentCon != null) {
            try {
            	currentCon.close();
            	}
            catch (SQLException ex) {
            	System.out.println("Connection closing problem in checkApplied method in Company\n");
               // ex.printStackTrace();
        	}
        }
    }
	return u;
}
public static ArrayList<user> showCstud(String s,Company c){
	ArrayList<user> currentuser=new ArrayList<user>();
	if(s.equals("Field")){
			//out.print("red");
			currentuser = Cdao.checkField(c);
		}
		else if(s.equals("Pointer")){
			currentuser = Cdao.checkPointer(c);
		}
		else if(s.equals("Applied")){
			currentuser = Cdao.checkApllied(c);
		}
		else if(s.equals("skill")){
			currentuser = Cdao.skillUser(c);
		}
		else{
			currentuser = Adao.showUser();
		}
		return currentuser;
}
}
