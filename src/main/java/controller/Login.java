package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Mydao;
import dto.Customer;
//mapping the url
@WebServlet("/login")
public class Login  extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//Receive the value front-End
		
		 String email=req.getParameter("email");
		 String password=req.getParameter("pass");
		 
		 System.out.println(email);
		 //verify if email exists
		 Mydao dao=new Mydao();
		 if(email.equals("admin@jsp.com")&&password .equals("admin")) {
			 resp.getWriter().print("<h1 style='color:green'>login successfully</h1>");
			 //this is the logic to send to nextpage
			 req.getRequestDispatcher("AdminHome.html").include(req, resp);
		 }else {
			 Customer c=dao.fetchByEmail(email);
		 
		
		 
		 if(c==null) {
			 resp.getWriter().print("<h1 style='color:red'> Invalid email </h1>");
			 req.getRequestDispatcher("login.html").include(req, resp);
			 
		 } 
		 else {
			 if(password.equals(c.getPassword())) {
				 resp.getWriter().print("<h1 style='color:green'> Login successful </h1>");
				 //this the logic to send to next page
				 req.getRequestDispatcher("customerHomepage.html").include(req, resp);
			 }
			 else {
				 resp.getWriter().print("<h1 style='color:red'> Invalid Password </h1>");
				 req.getRequestDispatcher("login.html").include(req, resp);
			 }
		 }
	}

}
}
