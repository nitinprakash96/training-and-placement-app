package models;

import java.util.*;

import Company.Cdao;
import Company.Company;
import alumni.alumni;
import java.sql.*;

public class userDao {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static int p;
	
	public static int Count(){
		int c=0;
		try{
			currentCon = ConnectionManager.getConnection();
			Statement stmt=currentCon.createStatement();
			rs = stmt.executeQuery("select count(*) from Student");
		if(rs.next()){
			c=rs.getInt(1);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return c;
	}
	
	public static void register(user u){
		try{
			currentCon = ConnectionManager.getConnection();
			Statement stmt=currentCon.createStatement();
			rs = stmt.executeQuery("select * from Student where username=" + "'" + u.getName() + "'" + " and password ='" + u.getPass() + "'");
			if(rs.next()){
				user.rflag=false;
			}
			else{
				p = stmt.executeUpdate("insert into Student values('" + u.getName() + "','" + u.getEmail() +"','" + u.getPass() + "'," + "CURDATE()" + ",'" + u.getPointer() + "','" + u.getField() + "' ,null," + u.getid() + ")");
				user.rflag=true;
			}
		}
		catch(Exception e){
			System.out.println("Student registration error!!! \n");
			e.printStackTrace();
		}
	}
	
	public static void register(techStud s){
		try{
			currentCon = ConnectionManager.getConnection();
			Statement stmt=currentCon.createStatement();
			p = stmt.executeUpdate("insert into techStud values(" + s.getid() + ",'" + s.getSkill() +"','" + s.getIntern() + "')");
		}
		catch(Exception e){
			System.out.println("technical Student registration error!!!\n");
			//e.printStackTrace();
		}
	}
	public static void register(mngStud s){
		try{
			currentCon = ConnectionManager.getConnection();
			Statement stmt=currentCon.createStatement();
			p = stmt.executeUpdate("insert into mngStud values(" + s.getid() + ",'" + s.getSkill() +"','" + s.getIntern() + "')");
		}
		catch(Exception e){
			System.out.println("Management background student registration error!!!\n");
			//e.printStackTrace();
		}
	}
	public static void register(bstud s){
		try{
			currentCon = ConnectionManager.getConnection();
			Statement stmt=currentCon.createStatement();
			p = stmt.executeUpdate("insert into bstud values(" + s.getid() + ",'" + s.getSkill() +"','" + s.getIntern() + "')");
		}
		catch(Exception e){
			System.out.println("business Student Registration error !!!\n");
			e.printStackTrace();
		}
	}
	
	public static user login(user u){
			    
		String username = u.getName();    
		String password = u.getPass();
		//Date regDate= u.getregDate();
		//String email = u.getEmail();
		Statement stmt = null;

		String searchQuery = "select * from Student where username=" + "'" + username + "'" + " and password ='" + password + "'";
		 
		 try{
			 currentCon = ConnectionManager.getConnection();
	         stmt=currentCon.createStatement();
	         rs = stmt.executeQuery(searchQuery);
	         if (rs.next()) {
	        	u.setValid(true);
	        	user.lflag=true;
	        	u.setName(rs.getString("username"));
	        	//u.setRegDate(rs.getLocalDate("regDate"));
	        	u.setEmail(rs.getString("email"));
	        	u.setField(rs.getString("field"));
	        	u.setPointer(rs.getFloat("Spointer"));
	        	u.setCid(rs.getInt("appliedComp"));
	        	u.setid(rs.getInt("Sid"));
	         } else {
	        	 user.lflag=false;
	        	 u.setValid(false);
	             System.out.println("Invalid password ");
	         }

		 }
		 catch(Exception e){
			 System.out.println("error in Student login !!!\n");
		 }
		
		return u;	
	}
	public static ArrayList<Company> skillComp(user u){
		//Company c[] =  null;
		ArrayList<Company> c=new ArrayList<Company>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			if(u.getField().equals("tech"))
				rs = st.executeQuery(" select * from Company where Cid in (select c.id from techComp c,techStud s where s.skill=c.skill and s.id=" + u.getid() + ")");
			//c = new Company[4];
			else if(u.getField().equals("mng"))
				rs = st.executeQuery(" select * from Company where Cid in (select c.id from mngComp c,mngStud s where s.skill=c.skill and s.id=" + u.getid() + ")");
			else if(u.getField().equals("both"))
				rs = st.executeQuery(" select * from Company where Cid in (select c.id from bComp c,bstud s where s.skill=c.skill and s.id=" + u.getid() + ")");
			while(rs.next()){
				Company a = new Company();
				//c[i] = new Company();
				a.setName(rs.getString("Cname"));
				a.setid(rs.getInt("Cid"));
				a.setPackage(rs.getInt("package"));
				a.setCount(rs.getInt("count"));
				a.setField(rs.getString("Cfield"));
				//if(rs.getString("appliedStud")!=null)
				a.setSid(rs.getInt("appliedStud"));
				if(rs.getString("appliedStud")==null)
				c.add(a);
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
		return c;
	}

	public static ArrayList<alumni> showAlumni(){
		//Company c[] =  null;
		ArrayList<alumni> a=new ArrayList<alumni>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			rs = st.executeQuery("select * from alumni");
			//c = new Company[4];
			while(rs.next()){
				alumni c = new alumni();
				//c[i] = new Company();
				c.setid(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setBatch(rs.getString("batch"));
				c.setField(rs.getString("field"));
				c.setWork(rs.getString("work"));
				a.add(c);
			}
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe username/password is invalid");
            System.out.println("error in showAlumni method in Student module !!! ");
            //ex.printStackTrace();
        } 
		finally {
            if (currentCon != null) {
                try {
                	currentCon.close();
                	}
                catch (SQLException ex) {
                    //ex.printStackTrace();
                	System.out.println("Closing connection problem in show Alumni  method in Student \n");
            	}
            }
        }
		return a;
	}
	public static ArrayList<Company> show(){
		//Company c[] =  null;
		ArrayList<Company> c=new ArrayList<Company>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			rs = st.executeQuery("select * from Company");
			//c = new Company[4];
			while(rs.next()){
				Company a = new Company();
				//c[i] = new Company();
				a.setName(rs.getString("Cname"));
				a.setid(rs.getInt("Cid"));
				a.setPackage(rs.getInt("package"));
				a.setCount(rs.getInt("count"));
				a.setField(rs.getString("Cfield"));
				//if(rs.getString("appliedStud")!=null)
				a.setSid(rs.getInt("appliedStud"));
				if(rs.getString("appliedStud")==null)
					c.add(a);
			}
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe username/password is invalid");
            System.out.println("error in Show comp method !!!\n");
            // ex.printStackTrace();
        } 
		finally {
            if (currentCon != null) {
                try {
                	currentCon.close();
                	}
                catch (SQLException ex) {
                	System.out.println("Closing connection problem\n");
                	//ex.printStackTrace();
            	}
            }
        }
		return c;
	}
	public static void IncCount(int id,user u){
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			//System.out.println(c.getid());
			Company c = new Company();
			rs = st.executeQuery("select * from Company where cid = " + id);
			while(rs.next()){
				c.setPointer(Float.parseFloat(rs.getString("Cpointer")));
				c.setid(Integer.parseInt(rs.getString("Cid")));
			}
			if(u.getPointer()>=c.getPointer() && u.getCid()!=c.getid()){
				int p = st.executeUpdate("update Company set count=count+1 where Cid = " + id);
			}
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe username/password is invalid");
            System.out.println("error in inc count method in Student !!!\n");
            //ex.printStackTrace();
        } 
		finally {
            if (currentCon != null) {
                try {
                	currentCon.close();
                	}
                catch (SQLException ex) {
                	System.out.println("Closing connection problem in incCount method  in Student\n");
                   // ex.printStackTrace();
            	}
            }
        }
	}
	public static ArrayList<Company> checkPointer(user u){
		ArrayList<Company> c=new ArrayList<Company>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			rs = st.executeQuery("select * from Company,Student where Spointer > Cpointer and username='" + u.getName() + "'");
			//c = new Company[4];
			while(rs.next()){
				Company a = new Company();
				//c[i] = new Company();
				//c[i] = new Company();
				a.setName(rs.getString("Cname"));
				a.setid(rs.getInt("Cid"));
				a.setPackage(rs.getInt("package"));
				a.setCount(rs.getInt("count"));
				a.setField(rs.getString("Cfield"));
				//if(rs.getString("appliedStud")!=null)
				a.setSid(rs.getInt("appliedStud"));
				if(rs.getString("appliedStud")==null && rs.getString("appliedComp")==null)
					c.add(a);
			}
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe username/password is invalid");
            System.out.println("error in checkPointer method in  Student \n");
            //ex.printStackTrace();
        } 
		finally {
            if (currentCon != null) {
                try {
                	currentCon.close();
                	}
                catch (SQLException ex) {
                	System.out.println("Closing connection problem in CheckPonter method in Student\n");
                    //ex.printStackTrace();
            	}
            }
        }
		return c;
	}
	public static ArrayList<Company> checkField(user u){
		ArrayList<Company> c=new ArrayList<Company>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			rs = st.executeQuery("select * from Company,Student where (field = Cfield or field = 'both' or Cfield='both') and username='" + u.getName() + "'");
			//c = new Company[4];
			while(rs.next()){
				Company a = new Company();
				//c[i] = new Company();
				//c[i] = new Company();
				a.setName(rs.getString("Cname"));
				a.setid(rs.getInt("Cid"));
				a.setPackage(rs.getInt("package"));
				a.setCount(rs.getInt("count"));
				a.setField(rs.getString("Cfield"));
				//if(rs.getString("appliedStud")!=null)
				a.setSid(rs.getInt("appliedStud"));
				if(rs.getString("appliedStud")==null && rs.getString("appliedComp")==null)
				c.add(a);
			}
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe username/password is invalid");
            System.out.println("error in checkField method in Student \n");
            //ex.printStackTrace();
        } 
		finally {
            if (currentCon != null) {
                try {
                	currentCon.close();
                	}
                catch (SQLException ex) {
                	System.out.println("Closing connection problem in checkField method in Student \n");
                   // ex.printStackTrace();
            	}
            }
        }
		return c;
	}
	public static ArrayList<alumni> checkAlumniField(user u){
		ArrayList<alumni> a=new ArrayList<alumni>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			rs = st.executeQuery("select * from alumni a,Student s where a.field = s.field and Sid=" + u.getid());
			//c = new Company[4];
			while(rs.next()){
				
					//c[i] = new Company();
				alumni c = new alumni();
				//c[i] = new Company();
				c.setid(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setBatch(rs.getString("batch"));
				c.setField(rs.getString("field"));
				c.setWork(rs.getString("work"));
				a.add(c);
			}
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe username/password is invalid");
            System.out.println("error in checkAlumniField method in Student ");
            //ex.printStackTrace();
        } 
		finally {
            if (currentCon != null) {
                try {
                	currentCon.close();
                	}
                catch (SQLException ex) {
                	System.out.println("Closing connection problem in checkAlumniField method in Student\n");
                   // ex.printStackTrace();
            	}
            }
        }
		return a;
	}
	public static ArrayList<alumni> checkWork(String s){
		ArrayList<alumni> a=new ArrayList<alumni>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			rs = st.executeQuery("select * from alumni where work = '" + s + "'");
			//c = new Company[4];
			while(rs.next()){
				
					//c[i] = new Company();
				alumni c = new alumni();
				//c[i] = new Company();
				c.setid(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setBatch(rs.getString("batch"));
				c.setField(rs.getString("field"));
				c.setWork(rs.getString("work"));
				a.add(c);
			}
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe username/password is invalid");
            System.out.println("error in checkWork method in Student ");
            //ex.printStackTrace();
        } 
		finally {
            if (currentCon != null) {
                try {
                	currentCon.close();
                	}
                catch (SQLException ex) {
                	System.out.println("Closing connection problem in checkWork method in Student\n");
                    //ex.printStackTrace();
            	}
            }
        }
		return a;
	}
	public static ArrayList<Company> seeApllied(user u){
		ArrayList<Company> c=new ArrayList<Company>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			rs = st.executeQuery("select * from Company where Cid in ( select appliedComp from Student where username = '" + u.getName() + "')");
			//c = new Company[4];
			while(rs.next()){
				Company a = new Company();
				//c[i] = new Company();
				//c[i] = new Company();
				a.setName(rs.getString("Cname"));
				a.setid(rs.getInt("Cid"));
				a.setPackage(rs.getInt("package"));
				a.setCount(rs.getInt("count"));
				a.setField(rs.getString("Cfield"));
				//if(rs.getString("appliedStud")!=null)
				a.setSid(rs.getInt("appliedStud"));
				if(rs.getString("appliedStud")==null)
				c.add(a);
			}
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe username/password is invalid");
            System.out.println("error in seeApplied method in Student ");
            //ex.printStackTrace();
        } 
		finally {
            if (currentCon != null) {
                try {
                	currentCon.close();
                	}
                catch (SQLException ex) {
                	System.out.println("Closing connection problem in seeApplied method in Student\n");
                    //ex.printStackTrace();
            	}
            }
        }
		return c;
	}
	
	public static void addComp(user u,int id){
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			Company c = new Company();
			rs = st.executeQuery("select * from Company where cid = " + id);
			if(rs.next()){
				c.setName(rs.getString("Cname"));
				c.setid(rs.getInt("Cid"));
				c.setPackage(rs.getInt("package"));
				c.setCount(rs.getInt("count"));
				c.setField(rs.getString("Cfield"));
				c.setPointer(rs.getFloat("Cpointer"));
				//if(rs.getString("appliedStud")!=null)
				c.setPass(rs.getString("pass"));
				c.setSid(rs.getInt("appliedStud"));
			}
			if(u.getPointer()>=c.getPointer() && u.getCid()!=c.getid()){				
				//c = new Company[4];
				int p1 = userDao.Count() +1;
				int p2 = Cdao.Count() + 2;
				int p = st.executeUpdate("insert into Student values('" + u.getName() + "','" + u.getEmail() +"','" + u.getPass() + "'," + "CURDATE()" + ",'" + u.getPointer() + "','" + u.getField() + "'," + c.getid() + "," + p1 + ")");
				int q = st.executeUpdate("insert into Company values('" + c.getName() + "'," + p2 +"," + c.getPackage() + "," + c.getCount() + "," + c.getPointer() + ",'" + c.getField() + "','" + c.getPass() + "'," + u.getid() + ")");
				//user.count=a;
			}
			else{
				System.out.println("not allowed");
			}
		}
		catch (SQLException ex) {
            System.out.println("An error occurred. Maybe username/password is invalid");
            System.out.println("error in addComp method in Student ");
            ex.printStackTrace();
        } 
		finally {
            if (currentCon != null) {
                try {
                	currentCon.close();
                	}
                catch (SQLException ex) {
                	System.out.println("Closing connection problem in addComp method in Student \n");
                   // ex.printStackTrace();
            	}
            }
        }
	}
	public static ArrayList<Company> showComp(String s,user u){
		ArrayList<Company> currentCompany=new ArrayList<Company>(); 
		if(s.equals("Field")){
			//out.print("red");
			currentCompany = userDao.checkField(u);
		}
		else if(s.equals("Pointer")){
			//out.print("Blue");
			currentCompany = userDao.checkPointer(u);
		}
		else if(s.equals("Applied")){
			//out.print("Pink");
			currentCompany = userDao.seeApllied(u);
		}
		else if(s.equals("skills")){
			currentCompany = userDao.skillComp(u);
		}
		else{
			//out.print("sf");
			currentCompany = userDao.show();
		}
		return currentCompany;
	}
	public static ArrayList<String> showSkills(Company b){
		ArrayList<String> s = new ArrayList<String>();
		try{
			currentCon = ConnectionManager.getConnection();
			Statement st = currentCon.createStatement();
			if(b.getField().equals("tech")){
				rs=st.executeQuery("select * from techComp where id = "+ b.getid());
				while(rs.next()){
					s.add(rs.getString("skill"));
				}
			}
			if(b.getField().equals("both")){
				rs=st.executeQuery("select * from bComp where id = "+ b.getid());
				while(rs.next()){
					s.add(rs.getString("skill"));
				}
			}
			if(b.getField().equals("mng")){
				rs=st.executeQuery("select * from mngComp where id = "+ b.getid());
				while(rs.next()){
					s.add(rs.getString("skill"));
				}
			}
		}
		catch(Exception e){}
		return s;
	}
	/*public static void main(String args[]){
		ArrayList<Company> allComp=new ArrayList<Company>();
		user u = new user();
		u.setPointer((float)8.5);
		u.setName("fff");
		u.setPass("fff");
		u.setEmail("fff@gmail.com");
		u.setField("both");
		//LocalDate d = java.time.LocalDate.now();
		//u.setRegDate(d);
		Company c= new Company();
		c.setCid(1);
		//u=login(u);
		userDao.register(u);
		for(Company b: allComp){
			System.out.println(b.getCname());
		}
		//IncCount(allcomp[0]);
	}*/
}
