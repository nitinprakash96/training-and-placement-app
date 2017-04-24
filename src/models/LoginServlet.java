package models;

import java.io.IOException;


import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Company.Cdao;
import Company.Company;
import Company.bComp;
import Company.mngComp;
import Company.techComp;
import alumni.alumni;
import alumni.research;
import alumni.startup;
import alumni.Adao;
import alumni.Job;
import tpc.tpcHead;
import tpc.tpcMember;
import tpc.userTdao;
/**
 * Servelet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    /*public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPath = request.getServletPath();
		System.out.println(userPath);
		System.out.println(userPath);
		if(userPath.equals("/logout")){
			HttpSession session = request.getSession();
			session.setAttribute("userid", null);
			session.invalidate();
			response.sendRedirect("start.jsp");
		}
		if(userPath.equals("/Sdel")){
			 user Studentuser = new user();
		     Studentuser.setName(request.getParameter("username"));
		     Studentuser.setPass(request.getParameter("password"));
		     userTdao.Sdelete(Studentuser);
		     if(tpcMember.dflag)
		    	 response.sendRedirect("tpcMember.jsp");
		     else
		    	 response.sendRedirect("Sdelete.jsp");
		}
		if(userPath.equals("/Adel")){
			 alumni aluser = new alumni();
		     aluser.setName(request.getParameter("username"));
		     aluser.setPass(request.getParameter("password"));
		     userTdao.Adelete(aluser);
		     if(tpcMember.dflag)
		    	 response.sendRedirect("tpcMember.jsp");
		     else
		    	 response.sendRedirect("Adel.jsp");
		}
		if(userPath.equals("/Tdel")){
			 tpcMember tpuser = new tpcMember();
			 tpuser.setName(request.getParameter("username"));
			 tpuser.setPass(request.getParameter("password"));
		     userTdao.Tdelete(tpuser);
		     if(tpcHead.dflag)
		    	 response.sendRedirect("tpcHead.jsp");
		     else
		    	 response.sendRedirect("Tdel.jsp");
		}
		if(userPath.equals("/Cdel")){
			Company c = new Company();
			c.setName(request.getParameter("username"));
			c.setPass(request.getParameter("password"));
		     userTdao.Cdelete(c);
		     if(tpcMember.dflag)
		    	 response.sendRedirect("tpcMember.jsp");
		     else
		    	 response.sendRedirect("Cdel.jsp");
		}
		if(userPath.equals("/work")){
			HttpSession session = request.getSession();
			alumni u = (alumni)session.getAttribute("currentSessionUser");
			if(u.getWork().equals("research")){
				research al = new research();
				al.setid(u.getid());
				al.setTopic(request.getParameter("Topic"));
				al.setcoreField(request.getParameter("corField"));
				al.setStipend(Integer.parseInt(request.getParameter("Stipend")));
				Adao.register(al);
				response.sendRedirect("Afirst.jsp"); 
			}
			else if(u.getField().equals("startup")){
				startup al = new startup();
				al.setid(u.getid());
				al.setTopic(request.getParameter("Topic"));
				al.setStName(request.getParameter("stName"));
				al.setStipend(Integer.parseInt(request.getParameter("Stipend")));
				al.setvacancy(Integer.parseInt(request.getParameter("vacancy")));
				Adao.register(al);
				response.sendRedirect("Afirst.jsp");
			}
			else{
				Job al = new Job();
				al.setid(u.getid());
				al.setPost(request.getParameter("Post"));
				al.setCompany(request.getParameter("Company"));
				al.setPackage(Integer.parseInt(request.getParameter("Package")));
				Adao.register(al);
				response.sendRedirect("Afirst.jsp");  
			}
		}
		if(userPath.equals("/z")){
			HttpSession session = request.getSession();
			user u = (user)session.getAttribute("currentSessionUser");
			if(u.getField().equals("tech")){
				techStud Student = new techStud();
				Student.setid(u.getid());
				Student.setSkill(request.getParameter("skill"));
				Student.setIntern(request.getParameter("intern"));
				userDao.register(Student);
				response.sendRedirect("student.jsp"); 
			}
			else if(u.getField().equals("mng")){
				mngStud Student = new mngStud();
				Student.setid(u.getid());
				Student.setSkill(request.getParameter("skill"));
				Student.setIntern(request.getParameter("intern"));
				userDao.register(Student);
				response.sendRedirect("student.jsp"); 
			}
			else if(u.getField().equals("both")){
				bstud Student = new bstud();
				Student.setid(u.getid());
				Student.setSkill(request.getParameter("skill"));
				Student.setIntern(request.getParameter("intern"));
				userDao.register(Student);
				response.sendRedirect("student.jsp"); 
			}
		}
		if(userPath.equals("/Cskill")){
			HttpSession session = request.getSession();
			Company c = (Company)session.getAttribute("currentSessionUser");
			if(c.getField().equals("tech")){
				techComp comp = new techComp();
				comp.setid(c.getid());
				comp.setSkill(request.getParameter("skill"));
				Cdao.register(comp);
				response.sendRedirect("Cfirst.jsp");
			}
			else if(c.getField().equals("mng")){
				mngComp comp = new mngComp();
				comp.setid(c.getid());
				comp.setSkill(request.getParameter("skill"));
				Cdao.register(comp);
				response.sendRedirect("Cfirst.jsp");
			}
			else if(c.getField().equals("both")){
				bComp comp = new bComp();
				comp.setid(c.getid());
				comp.setSkill(request.getParameter("skill"));
				Cdao.register(comp);
				response.sendRedirect("Cfirst.jsp");
			}
		}
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userPath = request.getServletPath();
		if(userPath.equals("/Slogin")){
			try {
				user Studentuser = new user();
			     Studentuser.setName(request.getParameter("username"));
			     Studentuser.setPass(request.getParameter("password"));
			     
			     Studentuser = userDao.login(Studentuser);
			     
			     if(Studentuser.isValid()) {
			    	 HttpSession session = request.getSession();	    
				     session.setAttribute("currentSessionUser",Studentuser); 
			         response.sendRedirect("showComp.jsp"); 
			     }
			     else {
			    	 response.sendRedirect("index.jsp");
			     }
			}
			catch (Throwable theException) {
				System.out.println(theException);
			}
		}
		if(userPath.equals("/Tlogin")){
			try {
				tpcMember tpcuser = new tpcMember();
				tpcuser.setName(request.getParameter("username"));
				tpcuser.setPass(request.getParameter("password"));
			     
				tpcuser = userTdao.login(tpcuser);
				if(tpcuser.isValid()){
		    	 HttpSession session = request.getSession();	    
		    	 session.setAttribute("currentSessionUser",tpcuser); 
		         response.sendRedirect("tpcMember.jsp");
				}
				else{
					response.sendRedirect("tLogin.jsp");
				}
			}
			catch (Throwable theException) {
				System.out.println(theException);
			}
		}
		if(userPath.equals("/Clogin")){
			try {
				Company Cuser = new Company();
				Cuser.setName(request.getParameter("username"));
				Cuser.setPass(request.getParameter("password"));
			     
				Cuser = Cdao.login(Cuser);
				if(Cuser.isValid()){
		    	 HttpSession session = request.getSession();	    
		    	 session.setAttribute("currentSessionUser",Cuser); 
		         response.sendRedirect("showCstud.jsp");
				}
				else{
					response.sendRedirect("Clogin.jsp");
				}
			}
			catch (Throwable theException) {
				System.out.println(theException);
			}
		}
		if(userPath.equals("/THlogin")){
			try{
				tpcHead head = new tpcHead();
				head.setName(request.getParameter("username"));
				head.setPass(request.getParameter("password"));
				head = userTdao.login(head);
				if(head.isValid()){
					HttpSession session = request.getSession();	    
			    	 session.setAttribute("currentSessionUser",head); 
			         response.sendRedirect("tpcHead.jsp");
				}
				else{
					response.sendRedirect("THlogin.jsp");
				}
			}
			catch(Exception e){
				System.out.println("error");
			}
		}
		if(userPath.equals("/Alogin")){
			try {
				alumni auser = new alumni();
				auser.setName(request.getParameter("username"));
				auser.setPass(request.getParameter("password"));
			     
				auser = Adao.login(auser);
				if(auser.isValid()){
		    	 HttpSession session = request.getSession();	    
		    	 session.setAttribute("currentSessionUser",auser); 
		         response.sendRedirect("showStud.jsp");
				}
				else{
					response.sendRedirect("Alogin.jsp");
				}
			}
			catch (Throwable theException) {
				System.out.println(theException);
			}
		}
		if(userPath.equals("/Tregister")){
			tpcMember tpcuser = new tpcMember();
			tpcuser.setId(userTdao.Count()+1);
			tpcuser.setName(request.getParameter("name"));
			tpcuser.setPass(request.getParameter("password"));
			tpcuser.setEmail(request.getParameter("email"));
			tpcuser.setFreeSlot(request.getParameter("freSlot"));
			tpcuser.setYear(Integer.parseInt(request.getParameter("year")));
			userTdao.register(tpcuser);
			if(Company.rflag)
				response.sendRedirect("tpcHead.jsp");
			else
				response.sendRedirect("tpcReg.jsp");
		}
		if(userPath.equals("/Cregister")){
			Company Cuser = new Company();
			Cuser.setid(Cdao.Count()+2);
			Cuser.setName(request.getParameter("name"));
			Cuser.setPass(request.getParameter("password"));
			Cuser.setPackage(Integer.parseInt(request.getParameter("Package")));
			Cuser.setField(request.getParameter("Field"));
			Cuser.setPointer(Float.parseFloat(request.getParameter("Pointer")));
			Cdao.register(Cuser);
			if(Company.rflag)
				response.sendRedirect("tpcMember.jsp");
			else
				response.sendRedirect("reg.jsp");
		}
		if(userPath.equals("/y")){
			user Studentuser = new user();
			Studentuser.setid(userDao.Count()+1);		
			Studentuser.setName(request.getParameter("username"));
			Studentuser.setPass(request.getParameter("password"));
			Studentuser.setEmail(request.getParameter("email"));
			Studentuser.setPointer(Float.parseFloat(request.getParameter("Pointer")));
			Studentuser.setField(request.getParameter("Field"));
			//Studentuser.setCid(Integer.parseInt(request.getParameter("appliedComp")));
			userDao.register(Studentuser);
			if(user.rflag)
				response.sendRedirect("tpcMember.jsp");
			else
				response.sendRedirect("Cregister.jsp");
		}
		if(userPath.equals("/Aregister")){
			alumni auser = new alumni();
			auser.setid(Adao.Count()+1);
			auser.setName(request.getParameter("name"));
			auser.setPass(request.getParameter("password"));
			auser.setBatch(request.getParameter("batch"));
			auser.setField(request.getParameter("field"));
			auser.setWork(request.getParameter("work"));
			Adao.register(auser);
			if(alumni.rflag)
				response.sendRedirect("tpcMember.jsp");
			else
				response.sendRedirect("Areg.jsp");
		}
		if(userPath.equals("/apply")){
			int id = Integer.parseInt(request.getParameter("id"));
			HttpSession session = request.getSession();	    
	    	user u = new user();
	    	u=(user)session.getAttribute("currentSessionUser");
			userDao.IncCount(id, u);
			userDao.addComp(u, id);
			response.sendRedirect("showComp.jsp");
		}
		
	}

}
